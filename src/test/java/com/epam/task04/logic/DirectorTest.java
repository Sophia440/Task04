package com.epam.task04.logic;

import com.epam.task04.Constants;
import com.epam.task04.entity.Plant;
import com.epam.task04.enums.ParserType;
import com.epam.task04.parser.ParserException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DirectorTest implements Constants {
    private static final String XSD_FILE = "./src/main/resources/plants.xsd";
    private static final ParserType SAX_PARSER_TYPE = ParserType.SAX;
    private static final ParserType DOM_PARSER_TYPE = ParserType.DOM;
    private static final ParserType JAXB_PARSER_TYPE = ParserType.JAXB;
    private final Director directorSax = new Director(XSD_FILE, SAX_PARSER_TYPE);
    private final Director directorDom = new Director(XSD_FILE, DOM_PARSER_TYPE);
    private final Director directorJaxb = new Director(XSD_FILE, JAXB_PARSER_TYPE);

    @Test
    public void testParseWithSaxParserTypeShouldSucceed() throws ParserException {
        List<Plant> actual = directorSax.parse(VALID_XML_FILE);
        Assert.assertEquals(EXPECTED, actual);
    }

    @Test
    public void testParseWithDomParserTypeShouldSucceed() throws ParserException {
        List<Plant> actual = directorDom.parse(VALID_XML_FILE);
        Assert.assertEquals(EXPECTED, actual);
    }

    @Test
    public void testParseWithJaxbParserTypeShouldSucceed() throws ParserException {
        List<Plant> actual = directorJaxb.parse(VALID_XML_FILE);
        Assert.assertEquals(EXPECTED, actual);
    }
}
