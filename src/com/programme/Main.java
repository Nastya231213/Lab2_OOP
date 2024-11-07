package com.programme;

import com.model.TouristVoucher;
import com.parser.TouristVoucherDOMParser;
import com.parser.TouristVoucherSAXParser;
import com.parser.TouristVoucherStAXParser;
import com.sort.SortTrip;
import com.logger.LoggerUtility;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/resources/trips.xml";
        String schemaPath = "src/resources/tourist_voucher.xsd";

        LoggerUtility.info("Парсинг за допомогою SAX:");
        TouristVoucherSAXParser saxParser = new TouristVoucherSAXParser();
        List<TouristVoucher.Tour> saxTours = saxParser.parse(filePath);
        printTours(saxTours);

        LoggerUtility.info("Парсинг за допомогою DOM:");
        List<TouristVoucher.Tour> domTours = TouristVoucherDOMParser.parse(filePath);
        printTours(SortTrip.sort(domTours));

        LoggerUtility.info("Парсинг за допомогою StAX:");
        List<TouristVoucher.Tour> staxTours = TouristVoucherStAXParser.parse(filePath);
        printTours(staxTours);
    }

    private static void printTours(List<TouristVoucher.Tour> tours) {
        for (TouristVoucher.Tour tour : tours) {
            LoggerUtility.info(tour.toString());
        }
    }
}
