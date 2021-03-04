package com.epam.task04.parser.sax;

import com.epam.task04.parser.ParserException;
import com.epam.task04.entity.Plant;
import com.epam.task04.parser.PlantParser;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxPlantParser implements PlantParser {
    private static final Logger LOGGER = Logger.getLogger(SaxPlantParser.class);
    private static final String LOG_START_MESSAGE = "Start parsing XML file with SAX parser";
    private static final String LOG_END_MESSAGE = "XML file was parsed successfully";

    @Override
    public List<Plant> parse(String file) throws ParserException {
        LOGGER.info(LOG_START_MESSAGE);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SaxPlantParserHandler xmlHandler = new SaxPlantParserHandler();
        File xmlFile = new File(file);
        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(xmlFile, xmlHandler);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ParserException(e.getMessage(), e);
        }
        LOGGER.info(LOG_END_MESSAGE);
        return xmlHandler.getPlantList();
    }
}
