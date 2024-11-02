package com.tests;

import com.model.HotelCharacteristicType;
import com.model.TouristVoucher;
import com.parser.TouristVoucherDOMParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TouristVoucherDOMParserTest {

    @Test
    void testParseValidXML() {
        String filePath = "src/resources/trips.xml";

        List<TouristVoucher.Tour> tours = TouristVoucherDOMParser.parse(filePath);

        assertNotNull(tours);

        assertFalse(tours.isEmpty());

        TouristVoucher.Tour firstTour = tours.get(0);
        assertEquals("T1", firstTour.getId());
        assertEquals("France", firstTour.getCountry());
        assertEquals(3, firstTour.getNumberDaysNights());

        List<HotelCharacteristicType> characteristics = firstTour.getHotelCharacteristics();
        assertNotNull(characteristics);
        assertEquals(1, characteristics.size());

        HotelCharacteristicType firstCharacteristic = characteristics.get(0);
        assertEquals(4, firstCharacteristic.getStars());
        assertTrue(firstCharacteristic.isTV());
        assertTrue(firstCharacteristic.isAirConditioner());
        assertEquals("Double", firstCharacteristic.getRoomType().value());
    }

    @Test
    void testParseEmptyXML() {
        String filePath = "src/test/resources/empty_trips.xml";

        List<TouristVoucher.Tour> tours = TouristVoucherDOMParser.parse(filePath);

        assertNotNull(tours);
        assertTrue(tours.isEmpty());
    }

    @Test
    void testParseInvalidXML() {
        String filePath = "src/test/resources/invalid_trips.xml";

        Exception exception = assertThrows(Exception.class, () -> {
            TouristVoucherDOMParser.parse(filePath);
        });

        assertNotNull(exception);
    }

}
