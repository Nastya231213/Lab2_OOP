//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.10.31 at 03:58:52 AM EET 
//


package com.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for HotelCharacteristicType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HotelCharacteristicType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Stars" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FoodIncluded" type="{}FoodType"/>
 *         &lt;element name="RoomType" type="{}RoomType"/>
 *         &lt;element name="TV" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AirConditioner" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelCharacteristicType", propOrder = {
    "stars",
    "foodIncluded",
    "roomType",
    "tv",
    "airConditioner"
})
public class HotelCharacteristicType {

    @XmlElement(name = "Stars")
    protected int stars;
    @XmlElement(name = "FoodIncluded", required = true)
    @XmlSchemaType(name = "string")
    protected FoodType foodIncluded;
    @XmlElement(name = "RoomType", required = true)
    @XmlSchemaType(name = "string")
    protected RoomType roomType;
    @XmlElement(name = "TV")
    protected boolean tv;
    @XmlElement(name = "AirConditioner")
    protected boolean airConditioner;

    /**
     * Gets the value of the stars property.
     * 
     */
    public int getStars() {
        return stars;
    }

    /**
     * Sets the value of the stars property.
     * 
     */
    public void setStars(int value) {
        this.stars = value;
    }

    /**
     * Gets the value of the foodIncluded property.
     * 
     * @return
     *     possible object is
     *     {@link FoodType }
     *     
     */
    public FoodType getFoodIncluded() {
        return foodIncluded;
    }

    /**
     * Sets the value of the foodIncluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link FoodType }
     *     
     */
    public void setFoodIncluded(FoodType value) {
        this.foodIncluded = value;
    }

    /**
     * Gets the value of the roomType property.
     * 
     * @return
     *     possible object is
     *     {@link RoomType }
     *     
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Sets the value of the roomType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomType }
     *     
     */
    public void setRoomType(RoomType value) {
        this.roomType = value;
    }

    /**
     * Gets the value of the tv property.
     * 
     */
    public boolean isTV() {
        return tv;
    }

    /**
     * Sets the value of the tv property.
     * 
     */
    public void setTV(boolean value) {
        this.tv = value;
    }

    /**
     * Gets the value of the airConditioner property.
     * 
     */
    public boolean isAirConditioner() {
        return airConditioner;
    }

    /**
     * Sets the value of the airConditioner property.
     * 
     */
    @Override
    public String toString() {
        return "HotelCharacteristicType{" +
                "stars=" + stars +
                ", foodIncluded=" + foodIncluded.value() +
                ", roomType=" + roomType.value() +
                ", tv=" + tv +
                ", airConditioner=" + airConditioner +
                '}';
    }
    public void setAirConditioner(boolean value) {
        this.airConditioner = value;
    }

}
