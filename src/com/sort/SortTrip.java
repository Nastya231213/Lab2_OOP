package com.sort;

import com.model.TouristVoucher;

import java.util.List;

public class SortTrip {
    public static List<TouristVoucher.Tour> sort(List<TouristVoucher.Tour> tours) {
        tours.sort((tour1, tour2) -> tour1.getCost().compareTo(tour2.getCost()));
        return tours;
    }
}
