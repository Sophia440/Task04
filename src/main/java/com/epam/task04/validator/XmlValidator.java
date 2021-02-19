package com.epam.task04.validator;

import com.epam.task04.parser.ParserException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XmlValidator {
    private static final Logger LOGGER = Logger.getLogger(XmlValidator.class);
    private static final String LOG_START_MESSAGE = "Start validating XML file";
    private static final String LOG_END_MESSAGE = "XML file is valid";
    private static final String PATH_TO_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";
    private String xsdFile;

    public XmlValidator(String xsdFile) {
        this.xsdFile = xsdFile;
    }

    public boolean isValid(String xmlFile) throws ParserException {
        LOGGER.info(LOG_START_MESSAGE);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(PATH_TO_SCHEMA_LANGUAGE);
        File schemaLocation = new File(xsdFile);
        Schema schema;
        try {
            schema = schemaFactory.newSchema(schemaLocation);
        } catch (SAXException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ParserException(e.getMessage(), e);
        }
        Validator validator = schema.newValidator();
        Source source = new StreamSource(xmlFile);
        try {
            validator.validate(source);
            LOGGER.info(LOG_END_MESSAGE);
            return true;
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ParserException(e.getMessage(), e);
        }
    }
}