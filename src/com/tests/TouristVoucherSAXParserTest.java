package com.tests;

import com.model.HotelCharacteristicType;
import com.model.TouristVoucher;
import com.parser.TouristVoucherSAXParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TouristVoucherSAXParserTest {

    private final String validXml = """
            <?xml version="1.0" encoding="UTF-8"?>
            <TouristVouchers>
                <Tour id="T1">
                    <Country>France</Country>
                    <NumberDaysNights>3</NumberDaysNights>
                    <Transport>Train</Transport>
                    <Cost>1200.50</Cost>
                    <HotelCharacteristics>
                        <Stars>4</Stars>
                        <RoomType>Double</RoomType>
                        <TV>true</TV>
                        <AirConditioner>true</AirConditioner>
                        <FoodIncluded>HB</FoodIncluded>
                    </HotelCharacteristics>
                </Tour>
                <Tour id="T2">
                    <Country>Italy</Country>
                    <NumberDaysNights>5</NumberDaysNights>
                    <Transport>Airplane</Transport>
                    <Cost>1500.00</Cost>
                    <HotelCharacteristics>
                        <Stars>5</Stars>
                        <RoomType>Single</RoomType>
                        <TV>true</TV>
                        <AirConditioner>true</AirConditioner>
                        <FoodIncluded>BB</FoodIncluded>
                    </HotelCharacteristics>
                </Tour>
            </TouristVouchers>
            """;

    @Test
    void testParseValidXML() {
        String filePath = createTempXmlFile(validXml);

        TouristVoucherSAXParser parser = new TouristVoucherSAXParser();
        List<TouristVoucher.Tour> tours = parser.parse(filePath);

        assertNotNull(tours);
        assertFalse(tours.isEmpty());

        TouristVoucher.Tour firstTour = tours.get(0);
        validateTour(firstTour, "T1", "France", 3, new BigDecimal("1200.50"), 4, true, true, "Double");

        TouristVoucher.Tour secondTour = tours.get(1);
        validateTour(secondTour, "T2", "Italy", 5, new BigDecimal("1500.00"), 5, true, true, "Single");
    }

    private String createTempXmlFile(String xmlContent) {
        try {
            Path tempFile = Files.createTempFile("test-", ".xml");
            Files.write(tempFile, xmlContent.getBytes());
            return tempFile.toString();
        } catch (Exception e) {
            fail("Could not create temp XML file: " + e.getMessage());
            return null;
        }
    }

    private void validateTour(TouristVoucher.Tour tour, String expectedId, String expectedCountry,
                              int expectedDaysNights, BigDecimal expectedCost,
                              int expectedStars, boolean expectedTV,
                              boolean expectedAirConditioner, String expectedRoomType) {
        assertEquals(expectedId, tour.getId());
        assertEquals(expectedCountry, tour.getCountry());
        assertEquals(expectedDaysNights, tour.getNumberDaysNights());
        assertEquals(expectedCost, tour.getCost());

        List<HotelCharacteristicType> characteristics = tour.getHotelCharacteristics();
        assertNotNull(characteristics);
        assertEquals(1, characteristics.size());

        HotelCharacteristicType characteristic = characteristics.get(0);
        assertEquals(expectedStars, characteristic.getStars());
        assertEquals(expectedTV, characteristic.isTV());
        assertEquals(expectedAirConditioner, characteristic.isAirConditioner());
        assertEquals(expectedRoomType, characteristic.getRoomType().value());
    }

}
