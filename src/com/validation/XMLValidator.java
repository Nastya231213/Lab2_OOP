package com.validation;

import com.logger.LoggerUtility;
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
                    LoggerUtility.warn("Warning: " + e.getMessage());
                }

                @Override
                public void error(SAXParseException e) throws SAXException {
                    LoggerUtility.error("Error: " + e.getMessage());
                    throw e;
                }

                @Override
                public void fatalError(SAXParseException e) throws SAXException {
                    LoggerUtility.error("Fatal error: " + e.getMessage());
                    throw e;
                }
            });

            validator.validate(new StreamSource(new File(xmlFilePath)));
            LoggerUtility.info("XML file successfully passed validation.");
            return true;

        } catch (IOException e) {
            LoggerUtility.error("IO error: " + e.getMessage());
            return false;
        } catch (SAXException e) {
            LoggerUtility.error("XML validation error: " + e.getMessage());
            return false;
        }
    }
}
