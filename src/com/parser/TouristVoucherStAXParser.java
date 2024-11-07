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
import com.logger.LoggerUtility; 

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
                            try {
                                currentTour.setNumberDaysNights(Integer.parseInt(content.toString().trim()));
                            } catch (NumberFormatException e) {
                                LoggerUtility.error("Error parsing number of days/nights for tour: " + currentTour.getId(), e);
                            }
                            break;
                        case "transport":
                            try {
                                currentTour.setTransport(TransportType.fromValue(content.toString().trim()));
                            } catch (IllegalArgumentException e) {
                                LoggerUtility.error("Invalid transport type for tour: " + currentTour.getId(), e);
                            }
                            break;
                        case "cost":
                            try {
                                currentTour.setCost(new BigDecimal(content.toString().trim()));
                            } catch (NumberFormatException e) {
                                LoggerUtility.error("Invalid cost format for tour: " + currentTour.getId(), e);
                            }
                            break;
                        case "stars":
                            try {
                                currentCharacteristic.setStars(Integer.parseInt(content.toString().trim()));
                            } catch (NumberFormatException e) {
                                LoggerUtility.error("Invalid stars value for hotel in tour: " + currentTour.getId(), e);
                            }
                            break;
                        case "foodincluded":
                            try {
                                currentCharacteristic.setFoodIncluded(FoodType.fromValue(content.toString().trim()));
                            } catch (IllegalArgumentException e) {
                                LoggerUtility.error("Invalid food type value for hotel in tour: " + currentTour.getId(), e);
                            }
                            break;
                        case "roomtype":
                            try {
                                currentCharacteristic.setRoomType(RoomType.fromValue(content.toString().trim()));
                            } catch (IllegalArgumentException e) {
                                LoggerUtility.error("Invalid room type value for hotel in tour: " + currentTour.getId(), e);
                            }
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
            LoggerUtility.error("Error parsing the XML file: " + filePath, e);  // Log general errors
        }

        LoggerUtility.info("Completed parsing XML file: " + filePath); // Log the completion of parsing
        return tours;
    }
}

