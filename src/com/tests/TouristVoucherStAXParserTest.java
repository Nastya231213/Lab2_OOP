package com.tests;

import com.model.*;
import com.parser.TouristVoucherStAXParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class TouristVoucherStAXParserTest {

    @Test
    public void testParseValidXML() {
        String filePath = "src/resources/trips.xml";

        List<TouristVoucher.Tour> tours = TouristVoucherStAXParser.parse(filePath);

        assertNotNull(tours);
        assertFalse(tours.isEmpty(), "No tours found in the XML file");

        TouristVoucher.Tour firstTour = tours.get(0);
        assertEquals("T1", firstTour.getId());
        assertEquals("France", firstTour.getCountry());
        assertEquals(3, firstTour.getNumberDaysNights());

        List<HotelCharacteristicType> characteristics = firstTour.getHotelCharacteristics();
        assertNotNull(characteristics);
        assertEquals(1, characteristics.size());

        HotelCharacteristicType firstCharacteristic = characteristics.get(0);
        assertEquals(4, firstCharacteristic.getStars());
        assertFalse(firstCharacteristic.isTV());
        assertFalse(firstCharacteristic.isAirConditioner());
        assertEquals("Double", firstCharacteristic.getRoomType().value());
    }

    @Test
    public void testParseInvalidXML() {
        String filePath = "src/test/resources/invalid_tours.xml";

        List<TouristVoucher.Tour> tours = TouristVoucherStAXParser.parse(filePath);

        assertNotNull(tours);
        assertTrue(tours.isEmpty(), "Tours should not be parsed from an invalid XML");
    }

    @Test
    public void testParseEmptyXML() {
        String filePath = "src/test/resources/empty_tours.xml";

        List<TouristVoucher.Tour> tours = TouristVoucherStAXParser.parse(filePath);
        assertNotNull(tours);
        assertTrue(tours.isEmpty(), "No tours should be parsed from an empty XML");
    }
}
