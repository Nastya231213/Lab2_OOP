package com.tests;

import com.model.HotelCharacteristicType;
import com.model.TouristVoucher;
import com.parser.TouristVoucherSAXParser;
import com.logger.LoggerUtility;  // Import LoggerUtility
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
        if (filePath == null) {
            LoggerUtility.error("Failed to create temp XML file.");
            fail("Failed to create temp XML file.");
        }

        TouristVoucherSAXParser parser = new TouristVoucherSAXParser();
        List<TouristVoucher.Tour> tours = null;

        try {
            tours = parser.parse(filePath);
        } catch (Exception e) {
            LoggerUtility.error("Error parsing XML file: " + filePath, e);
            fail("Error parsing XML file: " + e.getMessage());
        }

        assertNotNull(tours, "Tours list should not be null");
        assertFalse(tours.isEmpty(), "Tours list should not be empty");


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
            LoggerUtility.error("Could not create temp XML file.", e);
            return null;
        }
    }

    private void validateTour(TouristVoucher.Tour tour, String expectedId, String expectedCountry,
                              int expectedDaysNights, BigDecimal expectedCost,
                              int expectedStars, boolean expectedTV,
                              boolean expectedAirConditioner, String expectedRoomType) {
        try {
            assertEquals(expectedId, tour.getId(), "Tour ID does not match");
            assertEquals(expectedCountry, tour.getCountry(), "Country does not match");
            assertEquals(expectedDaysNights, tour.getNumberDaysNights(), "Number of days/nights does not match");
            assertEquals(expectedCost, tour.getCost(), "Cost does not match");

            List<HotelCharacteristicType> characteristics = tour.getHotelCharacteristics();
            assertNotNull(characteristics, "Hotel characteristics should not be null");
            assertEquals(1, characteristics.size(), "There should be exactly one hotel characteristic");

            HotelCharacteristicType characteristic = characteristics.get(0);
            assertEquals(expectedStars, characteristic.getStars(), "Stars count does not match");
            assertEquals(expectedTV, characteristic.isTV(), "TV availability does not match");
            assertEquals(expectedAirConditioner, characteristic.isAirConditioner(), "Air conditioner availability does not match");
            assertEquals(expectedRoomType, characteristic.getRoomType().value(), "Room type does not match");
        } catch (AssertionError e) {
            LoggerUtility.error("Validation failed for tour: " + tour.getId(), e);
            throw e;
        }
    }
}
