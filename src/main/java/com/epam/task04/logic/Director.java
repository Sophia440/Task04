package com.epam.task04.logic;

import com.epam.task04.enums.ParserType;
import com.epam.task04.parser.ParserException;
import com.epam.task04.entity.Plant;
import com.epam.task04.factory.PlantParserFactory;
import com.epam.task04.parser.PlantParser;
import com.epam.task04.validator.XmlValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class Director {
    private static final Logger LOGGER = Logger.getLogger(Director.class);
    private static final String LOG_START_MESSAGE = "Class Director called parse method";
    private static final String LOG_END_MESSAGE = "Class Director finished working";
    private XmlValidator validator;
    private PlantParser parser;

    public Director(String xsdFile, ParserType parserType) {
        this.validator = new XmlValidator(xsdFile);
        createParser(parserType);
    }

    private void createParser(ParserType parserType) {
        PlantParserFactory parserFactory = new PlantParserFactory();
        try {
            this.parser = parserFactory.createParser(parserType);
        } catch (ParserException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    List<Plant> parse(String xmlFile) throws ParserException {
        LOGGER.info(LOG_START_MESSAGE);
        List<Plant> plantList = null;
        if (validator.isValid(xmlFile)) {
            plantList = parser.parse(xmlFile);
        }
        LOGGER.info(LOG_END_MESSAGE);
        return plantList;
    }
}
