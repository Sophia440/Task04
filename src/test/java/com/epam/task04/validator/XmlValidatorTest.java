package com.epam.task04.validator;

import com.epam.task04.parser.ParserException;
import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {
    private static final String VALID_XSD_PATH = "./src/main/resources/plants.xsd";
    private static final String VALID_XML_FILE = "./src/main/resources/plants.xml";
    private final XmlValidator validatorValidXsd = new XmlValidator(VALID_XSD_PATH);
    private final XmlValidator validatorInvalidXsd = new XmlValidator(VALID_XML_FILE);

    @Test
    public void testIsValidWithValidXmlFileShouldSucceed() throws ParserException {
        Assert.assertTrue(validatorValidXsd.isValid(VALID_XML_FILE));
    }

    @Test(expected = ParserException.class)
    public void testIsValidWithInvalidXsdSchemaShouldSucceed() throws ParserException {
        Assert.assertTrue(validatorInvalidXsd.isValid(VALID_XML_FILE));
    }
}
