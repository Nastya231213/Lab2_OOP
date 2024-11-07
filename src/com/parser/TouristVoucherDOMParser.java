package com.parser;

import com.model.*;
import com.logger.LoggerUtility; // Import LoggerUtility
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TouristVoucherDOMParser {

    public static List<TouristVoucher.Tour> parse(String filePath) {
        List<TouristVoucher.Tour> tours = new ArrayList<>();


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            File xmlFile = new File(filePath);
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList tourList = doc.getElementsByTagName("Tour");


            for (int i = 0; i < tourList.getLength(); i++) {
                Node tourNode = tourList.item(i);
                if (tourNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tourElement = (Element) tourNode;
                    TouristVoucher.Tour tour = createTourFromElement(tourElement);
                    if (tour != null) {
                        tours.add(tour);
                    }
                }
            }
        } catch (Exception e) {
            LoggerUtility.error("Error while parsing XML file: " + filePath, e); // Log exception
        }

        return tours;
    }

    private static TouristVoucher.Tour createTourFromElement(Element tourElement) {
        TouristVoucher.Tour tour = new TouristVoucher.Tour();


        try {
            tour.setId(tourElement.getAttribute("id"));
            tour.setCountry(tourElement.getElementsByTagName("Country").item(0).getTextContent());
            tour.setNumberDaysNights(Integer.parseInt(tourElement.getElementsByTagName("NumberDaysNights").item(0).getTextContent()));
            tour.setTransport(TransportType.fromValue(tourElement.getElementsByTagName("Transport").item(0).getTextContent()));
            tour.setCost(new BigDecimal(tourElement.getElementsByTagName("Cost").item(0).getTextContent()));

            NodeList hotelCharacteristics = tourElement.getElementsByTagName("HotelCharacteristics");
            List<HotelCharacteristicType> characteristicsList = new ArrayList<>();

            for (int i = 0; i < hotelCharacteristics.getLength(); i++) {
                Element characteristicElement = (Element) hotelCharacteristics.item(i);
                HotelCharacteristicType characteristic = new HotelCharacteristicType();
                characteristic.setStars(Integer.parseInt(characteristicElement.getElementsByTagName("Stars").item(0).getTextContent()));

                FoodType foodType = FoodType.fromValue(characteristicElement.getElementsByTagName("FoodIncluded").item(0).getTextContent());
                characteristic.setFoodIncluded(foodType);
                String roomTypeValue = characteristicElement.getElementsByTagName("RoomType").item(0).getTextContent();
                RoomType roomType = RoomType.fromValue(roomTypeValue);

                characteristic.setRoomType(roomType);
                characteristic.setTV(Boolean.parseBoolean(characteristicElement.getElementsByTagName("TV").item(0).getTextContent()));
                characteristic.setAirConditioner(Boolean.parseBoolean(characteristicElement.getElementsByTagName("AirConditioner").item(0).getTextContent()));
                characteristicsList.add(characteristic);
            }

            tour.setHotelCharacteristics(characteristicsList);

        } catch (Exception e) {
            LoggerUtility.error("Error while creating a tour from element: " + tourElement.getAttribute("id"), e);
        }

        return tour;
    }
}
