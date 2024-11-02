package com.parser;

import com.model.*;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TouristVoucherStAXParser {
    public static List<TouristVoucher.Tour> parse(String filePath) {
        List<TouristVoucher.Tour> tours = new ArrayList<>();
        TouristVoucher.Tour currentTour = null;
        HotelCharacteristicType currentCharacteristic = null;
        StringBuilder content = new StringBuilder();

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            FileInputStream inputStream = new FileInputStream(filePath);
            XMLEventReader eventReader = factory.createXMLEventReader(inputStream);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();

                    if (elementName.equalsIgnoreCase("Tour")) {
                        currentTour = new TouristVoucher.Tour();
                        currentTour.setId(startElement.getAttributeByName(new QName("id")).getValue());
                    } else if (elementName.equalsIgnoreCase("HotelCharacteristics")) {
                        currentCharacteristic = new HotelCharacteristicType();
                    }
                } else if (event.isCharacters()) {
                    Characters characters = event.asCharacters();
                    content.append(characters.getData());
                } else if (event.isEndElement()) {
                    String elementName = event.asEndElement().getName().getLocalPart();

                    switch (elementName.toLowerCase()) {
                        case "tour":
                            tours.add(currentTour);
                            break;
                        case "country":
                            currentTour.setCountry(content.toString().trim());
                            break;
                        case "numberdaysnights":
                            currentTour.setNumberDaysNights(Integer.parseInt(content.toString().trim())); // Trim the string before parsing
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
                            currentCharacteristic.setTV(Boolean.parseBoolean(content.toString()));
                            break;
                        case "airconditioner":
                            currentCharacteristic.setAirConditioner(Boolean.parseBoolean(content.toString()));
                            break;
                        case "hotelcharacteristics":
                            currentTour.getHotelCharacteristics().add(currentCharacteristic);
                            break;
                    }
                    content.setLength(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tours;
    }


}
