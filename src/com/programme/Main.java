package com.programme;

import com.model.TouristVoucher;
import com.parser.TouristVoucherDOMParser;
import com.parser.TouristVoucherSAXParser;
import com.parser.TouristVoucherStAXParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/resources/trips.xml";
        String schemaPath = "src/resources/tourist_voucher.xsd";

        TouristVoucherSAXParser saxParser = new TouristVoucherSAXParser();
        System.out.println("Парсинг за допомогою SAX:");
        List<TouristVoucher.Tour> saxTours = saxParser.parse(filePath);
        printTours(saxTours);

        System.out.println("\nПарсинг за допомогою DOM:");
        List<TouristVoucher.Tour> domTours = TouristVoucherDOMParser.parse(filePath);
        printTours(domTours);

        System.out.println("\nПарсинг за допомогою StAX:");
        List<TouristVoucher.Tour> staxTours = TouristVoucherStAXParser.parse(filePath);
        printTours(staxTours);
    }

    private static void printTours(List<TouristVoucher.Tour> tours) {
        for (TouristVoucher.Tour tour : tours) {
            System.out.println(tour);
        }
    }
}
