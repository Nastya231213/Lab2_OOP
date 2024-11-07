package com.transformer;

import com.logger.LoggerUtility;  // Import LoggerUtility
import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTTransformer {

    public static void main(String[] args) {
        transformXML("src/resources/trips.xml", "src/resources/trips_by_type.xsl", "src/resources/trips_by_type_output.xml");
    }

    public static void transformXML(String inputXmlPath, String xsltPath, String outputXmlPath) {
        try {
            LoggerUtility.info("Starting XML transformation:");
            LoggerUtility.info("Input XML file: " + inputXmlPath);
            LoggerUtility.info("XSLT file: " + xsltPath);
            LoggerUtility.info("Output XML file: " + outputXmlPath);

            File inputXml = new File(inputXmlPath);
            File xsltFile = new File(xsltPath);
            File outputXml = new File(outputXmlPath);

            if (!inputXml.exists()) {
                LoggerUtility.error("Input XML file not found: " + inputXmlPath);
                return;
            }

            if (!xsltFile.exists()) {
                LoggerUtility.error("XSLT file not found: " + xsltPath);
                return;
            }

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            transformer.transform(
                    new StreamSource(inputXml),
                    new StreamResult(outputXml)
            );

            LoggerUtility.info("File was successfully saved to: " + outputXml.getPath());
        } catch (Exception e) {
            LoggerUtility.error("An error occurred during the transformation", e);
        }
    }
}

