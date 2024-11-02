package com.tests;

import com.validation.XMLValidator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XMLValidatorTest {

    private static final String VALID_XML = """
            <?xml version="1.0" encoding="UTF-8"?>
            <TouristVouchers>
                <Tour id="T1">
                    <Country>France</Country>
                    <NumberDaysNights>3</NumberDaysNights>
                    <Transport>Bus</Transport>
                    <Cost>1200.50</Cost>
                    <HotelCharacteristics>
                        <Stars>4</Stars>
                        <RoomType>Double</RoomType>
                        <TV>true</TV>
                        <AirConditioner>true</AirConditioner>
                    </HotelCharacteristics>
                </Tour>
            </TouristVouchers>
            """;

    private static final String INVALID_XML = """
            <?xml version="1.0" encoding="UTF-8"?>
            <TouristVouchers>
                <Tour id="T1">
                    <Country>France</Country>
                    <NumberDaysNights>three</NumberDaysNights> <!-- Invalid number -->
                </Tour>
            </TouristVouchers>
            """;

    private static final String XSD_SCHEMA = """
            <?xml version="1.0" encoding="UTF-8"?>
            <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
                <xs:element name="TouristVouchers">
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name="Tour" maxOccurs="unbounded">
                                <xs:complexType>
                                    <xs:sequence>
                                        <xs:element name="Country" type="xs:string"/>
                                        <xs:element name="NumberDaysNights" type="xs:int"/>
                                        <xs:element name="Transport" type="xs:string"/>
                                        <xs:element name="Cost" type="xs:decimal"/>
                                        <xs:element name="HotelCharacteristics">
                                            <xs:complexType>
                                                <xs:sequence>
                                                    <xs:element name="Stars" type="xs:int"/>
                                                    <xs:element name="RoomType" type="xs:string"/>
                                                    <xs:element name="TV" type="xs:boolean"/>
                                                    <xs:element name="AirConditioner" type="xs:boolean"/>
                                                </xs:sequence>
                                            </xs:complexType>
                                        </xs:element>
                                    </xs:sequence>
                                    <xs:attribute name="id" type="xs:string" use="required"/>
                                </xs:complexType>
                            </xs:element>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xs:schema>
            """;

    private static String tempXmlPath;
    private static String tempXsdPath;

    @BeforeAll
    static void setUp() throws IOException {
        tempXmlPath = createTempFile("test-valid.xml", VALID_XML);
        tempXsdPath = createTempFile("test-schema.xsd", XSD_SCHEMA);
    }

    @Test
    void testValidateValidXML() {
        assertTrue(XMLValidator.validate(tempXmlPath, tempXsdPath), "Valid XML should pass validation");
    }

    @Test
    void testValidateInvalidXML() throws IOException {
        String invalidXmlPath = createTempFile("test-invalid.xml", INVALID_XML);

        assertFalse(XMLValidator.validate(invalidXmlPath, tempXsdPath), "Invalid XML should fail validation");

        new File(invalidXmlPath).delete();
    }

    @AfterAll
    static void cleanUp() {
        new File(tempXmlPath).delete();
        new File(tempXsdPath).delete();
    }

    private static String createTempFile(String fileName, String content) throws IOException {
        File tempFile = File.createTempFile(fileName, null);
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile.getAbsolutePath();
    }
}
