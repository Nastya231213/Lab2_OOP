package com.parser;

import com.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TouristVoucherSAXParser extends DefaultHandler {
    private static List<TouristVoucher.Tour> tours;
    private TouristVoucher.Tour currentTour;
    private HotelCharacteristicType currentCharacteristic;
    private static StringBuilder content;

    public  List<TouristVoucher.Tour> parse(String filePath) {
        tours = new ArrayList<>();
        content = new StringBuilder();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(filePath), this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tours;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Tour")) {
            currentTour = new TouristVoucher.Tour();
            currentTour.setId(attributes.getValue("id"));
        } else if (qName.equalsIgnoreCase("HotelCharacteristics")) {
            currentCharacteristic = new HotelCharacteristicType();
        }
        content.setLength(0);
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content.append(ch, start, length);
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "tour":
                tours.add(currentTour);
                break;
            case "country":
                currentTour.setCountry(content.toString().trim());
                break;
            case "numberdaysnights":
                currentTour.setNumberDaysNights(Integer.parseInt(content.toString().trim()));
                break;
            case "transport":
                currentTour.setTransport(TransportType.fromValue(content.toString().trim()));
                break;
            case "cost":
                currentTour.setCost(new BigDecimal(content.toString().trim()));
                break;
            case "stars":
                currentCharacteristic.setStars(Integer.parseInt(content.toString().trim()));
                break;
            case "foodincluded":
                currentCharacteristic.setFoodIncluded(FoodType.fromValue(content.toString().trim()));
                break;
            case "roomtype":
                currentCharacteristic.setRoomType(RoomType.fromValue(content.toString().trim()));
                break;
            case "tv":
                currentCharacteristic.setTV(Boolean.parseBoolean(content.toString().trim()));
                break;
            case "airconditioner":
                currentCharacteristic.setAirConditioner(Boolean.parseBoolean(content.toString().trim()));
                break;
            case "hotelcharacteristics":
                currentTour.getHotelCharacteristics().add(currentCharacteristic);
                break;

        }
    }


}
