package com.epam.task04.factory;

import com.epam.task04.enums.ParserType;
import com.epam.task04.parser.ParserException;
import com.epam.task04.parser.dom.DomPlantParser;
import com.epam.task04.parser.jaxb.JaxbPlantParser;
import com.epam.task04.parser.PlantParser;
import com.epam.task04.parser.sax.SaxPlantParser;
import org.apache.log4j.Logger;

public class PlantParserFactory {
    private static final String PARSER_TYPE_EXCEPTION_MESSAGE = "Parser type not recognized";
    private static final Logger LOGGER = Logger.getLogger(PlantParserFactory.class);

    public PlantParser createParser(ParserType parserType) throws ParserException {
        LOGGER.info("Creating " + parserType.name() + " parser to parse XML file.");
        switch (parserType) {
            case SAX:
                return new SaxPlantParser();
            case DOM:
                return new DomPlantParser();
            case JAXB:
                return new JaxbPlantParser();
            default:
                LOGGER.error(PARSER_TYPE_EXCEPTION_MESSAGE);
                throw new ParserException(PARSER_TYPE_EXCEPTION_MESSAGE);
        }
    }
}
