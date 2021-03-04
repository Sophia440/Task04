package com.epam.task04.parser.sax;

import com.epam.task04.entity.*;
import com.epam.task04.enums.PlantTagName;
import com.epam.task04.enums.SoilType;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxPlantParserHandler extends DefaultHandler {
    private static final Logger LOGGER = Logger.getLogger(SaxPlantParserHandler.class);
    private static final String STARTING_MESSAGE = "Parsing with Sax started";
    private static final String ENDING_MESSAGE = "Parsing with Sax ended";
    private static final String TREE_TAG = "tree";
    private static final String FLOWER_TAG = "flower";
    private static final String ID_TAG = "id";
    private static final String UNDERSCORE = "_";
    private static final String DASH = "-";
    private static final String LINE_TO_FORM_LOG = "Line ";
    private static final String COLON_TO_FORM_LOG = ": ";
    private List<Plant> plantList;
    private Plant plant;
    private StringBuilder text;

    public SaxPlantParserHandler() {
        this.plantList = new ArrayList<>();
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    @Override
    public void startDocument() {
        LOGGER.info(STARTING_MESSAGE);
    }

    @Override
    public void endDocument() {
        LOGGER.info(ENDING_MESSAGE);
    }

    @Override
    public void startElement(String url, String localName, String qName, Attributes attributes) {
        this.text = new StringBuilder();
        if (qName.equals(TREE_TAG)) {
            String id = attributes.getValue(ID_TAG);
            plant = new Tree(id);
        }
        if (qName.equals(FLOWER_TAG)) {
            String id = attributes.getValue(ID_TAG);
            plant = new Flower(id);
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    @Override
    public void endElement(String url, String localName, String qName) {
        PlantTagName tagName = PlantTagName.valueOf(qName.toUpperCase().replace(DASH, UNDERSCORE));
        switch (tagName) {
            case NAME:
                String name = text.toString();
                plant.setName(name);
                break;
            case ORIGIN:
                String origin = text.toString();
                plant.setOrigin(origin);
                break;
            case SOIL_TYPE:
                String soilTypeAsString = text.toString();
                SoilType soilType = SoilType.valueOf(soilTypeAsString.toUpperCase());
                plant.setSoilType(soilType);
                break;
            case GROWING_TEMPERATURE_CELSIUS:
                String growingTemperatureCelsiusAsString = text.toString();
                double growingTemperatureCelsius = Double.parseDouble(growingTemperatureCelsiusAsString);
                plant.setGrowingTemperatureCelsius(growingTemperatureCelsius);
                break;
            case LEAF_COLOR:
                String leafColor = text.toString();
                ((Tree) plant).setLeafColor(leafColor);
                break;
            case AVERAGE_HEIGHT_METERS:
                String averageHeightMetersAsString = text.toString();
                double averageHeightMeters = Double.parseDouble(averageHeightMetersAsString);
                ((Tree) plant).setAverageHeightMeters(averageHeightMeters);
                break;
            case PETAL_COLOR:
                String petalColor = text.toString();
                ((Flower) plant).setPetalColor(petalColor);
                break;
            case AVERAGE_HEIGHT_CENTIMETERS:
                String averageHeightCentimetersAsString = text.toString();
                double averageHeightCentimeters = Double.parseDouble(averageHeightCentimetersAsString);
                ((Flower) plant).setAverageHeightCentimeters(averageHeightCentimeters);
                break;
            case TREE:
            case FLOWER:
                plantList.add(plant);
                plant = null;
                break;
        }
    }

    @Override
    public void warning(SAXParseException exception) {
        LOGGER.warn(LINE_TO_FORM_LOG + exception.getLineNumber() + COLON_TO_FORM_LOG + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) {
        LOGGER.error(LINE_TO_FORM_LOG + exception.getLineNumber() + COLON_TO_FORM_LOG + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        LOGGER.fatal(LINE_TO_FORM_LOG + exception.getLineNumber() + COLON_TO_FORM_LOG + exception.getMessage());
        throw (exception);
    }
}
