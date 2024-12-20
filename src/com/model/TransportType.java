//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.10.31 at 03:58:52 AM EET 
//


package com.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransportType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransportType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Airplane"/>
 *     &lt;enumeration value="Train"/>
 *     &lt;enumeration value="Car"/>
 *     &lt;enumeration value="Cruise"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransportType")
@XmlEnum
public enum TransportType {

    @XmlEnumValue("Airplane")
    AIRPLANE("Airplane"),
    @XmlEnumValue("Train")
    TRAIN("Train"),
    @XmlEnumValue("Car")
    CAR("Car"),
    @XmlEnumValue("Cruise")
    CRUISE("Cruise");
    private final String value;

    TransportType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransportType fromValue(String v) {
        for (TransportType c: TransportType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
