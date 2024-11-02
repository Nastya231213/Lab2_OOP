package com.validation;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {

    public static boolean validate(String xmlFilePath, String xsdFilePath) {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();

            validator.setErrorHandler(new DefaultHandler() {
                @Override
                public void warning(SAXParseException e) throws SAXException {
                    System.out.println("Warning: " + e.getMessage());
                }

                @Override
                public void error(SAXParseException e) throws SAXException {
                    System.out.println("Error: " + e.getMessage());
                    throw e;
                }

                @Override
                public void fatalError(SAXParseException e) throws SAXException {
                    System.out.println("Fatal error: " + e.getMessage());
                    throw e;
                }
            });
            validator.validate(new StreamSource(new File(xmlFilePath)));
            System.out.println("XML файл успішно пройшов валідацію.");
            return true;

        } catch (IOException e) {
            System.out.println("IO помилка: " + e.getMessage());
            return false;
        } catch (SAXException e) {
            System.out.println("XML валідація помилка: " + e.getMessage());
            return false;
        }
    }
}
