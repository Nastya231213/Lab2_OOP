//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.10.31 at 03:58:52 AM EET 
//


package com.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tour" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Type" type="{}TourType"/>
 *                   &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="NumberDaysNights" type="{}PositiveInteger"/>
 *                   &lt;element name="Transport" type="{}TransportType"/>
 *                   &lt;element name="HotelCharacteristics" type="{}HotelCharacteristicType" maxOccurs="unbounded"/>
 *                   &lt;element name="Cost" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tour"
})
@XmlRootElement(name = "TouristVoucher")
public class TouristVoucher {

    @XmlElement(name = "Tour", required = true)
    protected List<Tour> tour;

    /**
     * Gets the value of the tour property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tour property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTour().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tour }
     * 
     * 
     */
    public List<Tour> getTour() {
        if (tour == null) {
            tour = new ArrayList<Tour>();
        }
        return this.tour;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tourist Voucher:\n");
        for (Tour tour : tour) {
            sb.append(tour.toString()).append("\n");
        }
        return sb.toString();
    }
    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Type" type="{}TourType"/>
     *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="NumberDaysNights" type="{}PositiveInteger"/>
     *         &lt;element name="Transport" type="{}TransportType"/>
     *         &lt;element name="HotelCharacteristics" type="{}HotelCharacteristicType" maxOccurs="unbounded"/>
     *         &lt;element name="Cost" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *       &lt;/sequence>
     *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "type",
        "country",
        "numberDaysNights",
        "transport",
        "hotelCharacteristics",
        "cost"
    })
    public static class Tour {

        @XmlElement(name = "Type", required = true)
        @XmlSchemaType(name = "string")
        protected TourType type;
        @XmlElement(name = "Country", required = true)
        protected String country;
        @XmlElement(name = "NumberDaysNights")
        protected int numberDaysNights;
        @XmlElement(name = "Transport", required = true)
        @XmlSchemaType(name = "string")
        protected TransportType transport;
        @XmlElement(name = "HotelCharacteristics", required = true)
        protected List<HotelCharacteristicType> hotelCharacteristics;
        @XmlElement(name = "Cost", required = true)
        protected BigDecimal cost;
        @XmlAttribute(name = "id", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link TourType }
         *     
         */
        public TourType getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link TourType }
         *     
         */
        public void setType(TourType value) {
            this.type = value;
        }

        /**
         * Gets the value of the country property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCountry() {
            return country;
        }

        /**
         * Sets the value of the country property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCountry(String value) {
            this.country = value;
        }

        /**
         * Gets the value of the numberDaysNights property.
         * 
         */
        public int getNumberDaysNights() {
            return numberDaysNights;
        }

        /**
         * Sets the value of the numberDaysNights property.
         * 
         */
        public void setNumberDaysNights(int value) {
            this.numberDaysNights = value;
        }

        /**
         * Gets the value of the transport property.
         * 
         * @return
         *     possible object is
         *     {@link TransportType }
         *     
         */
        public TransportType getTransport() {
            return transport;
        }

        /**
         * Sets the value of the transport property.
         * 
         * @param value
         *     allowed object is
         *     {@link TransportType }
         *     
         */
        public void setTransport(TransportType value) {
            this.transport = value;
        }

        /**
         * Gets the value of the hotelCharacteristics property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the hotelCharacteristics property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHotelCharacteristics().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link HotelCharacteristicType }
         * 
         * 
         */
        public List<HotelCharacteristicType> getHotelCharacteristics() {
            if (hotelCharacteristics == null) {
                hotelCharacteristics = new ArrayList<HotelCharacteristicType>();
            }
            return this.hotelCharacteristics;
        }

        public void setHotelCharacteristics(List<HotelCharacteristicType> hotelCharacteristics) {
            this.hotelCharacteristics = hotelCharacteristics;
        }

        /**
         * Gets the value of the cost property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getCost() {
            return cost;
        }

        /**
         * Sets the value of the cost property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setCost(BigDecimal value) {
            this.cost = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Tour ID: ").append(id)
                    .append(", Country: ").append(country)
                    .append(", Days/Nights: ").append(numberDaysNights)
                    .append(", Transport: ").append(transport.value())
                    .append(", Cost: ").append(cost)
                    .append(", Hotel Characteristics: ").append(hotelCharacteristics);
            return sb.toString();
        }
    }

}