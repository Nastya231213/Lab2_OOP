package com.transformer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTTransformer {
    public static void main(String[] args) {
        transformXML("src/resources/trips.xml", "src/resources/trips_by_type.xsl", "src/resources/trips_by_type_output.xml");
    }

    public static void transformXML(String inputXmlPath, String xsltPath, String outputXmlPath) {
        try {
            File inputXml = new File(inputXmlPath);
            File xsltFile = new File(xsltPath);
            File outputXml = new File(outputXmlPath);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            transformer.transform(
                    new StreamSource(inputXml),
                    new StreamResult(outputXml)
            );
            System.out.println("File was saved in " + outputXml.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
