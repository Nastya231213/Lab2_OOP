package com.tests;

import com.transformer.XSLTTransformer;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class XSLTTransformerTest {

    @Test
    public void testTransformXML() {
        String inputXmlPath = "src/resources/test_trips.xml";
        String xsltPath = "src/resources/trips_by_type.xsl";
        String outputXmlPath = "src/resources/trips_by_type_output.xml";

        XSLTTransformer.transformXML(inputXmlPath, xsltPath, outputXmlPath);

        File outputFile = new File(outputXmlPath);
        assertTrue(outputFile.exists(), "Output file should exist after transformation.");
    }
}
