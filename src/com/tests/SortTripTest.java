package com.sort;

import com.model.TouristVoucher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortTripTest {

    private List<TouristVoucher.Tour> tours;

    @BeforeEach
    public void setUp() {
        tours = new ArrayList<>();
        tours.add(createTour("1", new BigDecimal("1200.50")));
        tours.add(createTour("2", new BigDecimal("800.25")));
        tours.add(createTour("3", new BigDecimal("1500.75")));
    }

    private TouristVoucher.Tour createTour(String id, BigDecimal cost) {
        TouristVoucher.Tour tour = new TouristVoucher.Tour();
        tour.setId(id);
        tour.setCost(cost);
        return tour;
    }

    @Test
    public void testSortToursByCostAscending() {
        List<TouristVoucher.Tour> sortedTours = SortTrip.sort(tours);

        assertNotNull(sortedTours);
        assertEquals(3, sortedTours.size(), "The list size is incorrect");

        assertEquals(new BigDecimal("800.25"), sortedTours.get(0).getCost(), "The first tour should have the lowest cost");
        assertEquals(new BigDecimal("1200.50"), sortedTours.get(1).getCost(), "The second tour should have the middle cost");
        assertEquals(new BigDecimal("1500.75"), sortedTours.get(2).getCost(), "The third tour should have the highest cost");
    }


    @Test
    public void testSortToursWithSingleItem() {
        tours.clear();
        tours.add(createTour("1", new BigDecimal("1200.50")));

        List<TouristVoucher.Tour> sortedTours = SortTrip.sort(tours);

        assertNotNull(sortedTours);
        assertEquals(1, sortedTours.size(), "The list size is incorrect");
        assertEquals(new BigDecimal("1200.50"), sortedTours.get(0).getCost(), "The cost of the only tour should be 1200.50");
    }

    @Test
    public void testSortEmptyList() {
        tours.clear();
        List<TouristVoucher.Tour> sortedTours = SortTrip.sort(tours);
        assertNotNull(sortedTours);
        assertTrue(sortedTours.isEmpty(), "The sorted list should be empty");
    }
}
