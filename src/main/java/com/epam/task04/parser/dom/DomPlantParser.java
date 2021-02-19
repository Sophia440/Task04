package com.epam.task04.parser.dom;

import com.epam.task04.entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.task04.enums.SoilType;
import com.epam.task04.parser.ParserException;
import com.epam.task04.parser.PlantParser;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomPlantParser implements PlantParser {
    private static final Logger LOGGER = Logger.getLogger(DomPlantParser.class);
    private static final String LOG_START_MESSAGE = "Start parsing XML file with DOM parser";
    private static final String LOG_END_MESSAGE = "XML file was parsed successfully";
    private static final String TREE_TAG = "tree";
    private static final String FLOWER_TAG = "flower";
    private static final String ID_TAG = "id";
    private static final String NAME_TAG = "name";
    private static final String ORIGIN_TAG = "origin";
    private static final String SOIL_TYPE_TAG = "soil-type";
    private static final String GROWING_TEMPERATURE_CELSIUS_TAG = "growing-temperature-celsius";
    private static final String LEAF_COLOR_TAG = "leaf-color";
    private static final String PETAL_COLOR_TAG = "petal-color";
    private static final String AVERAGE_HEIGHT_METERS_TAG = "average-height-meters";
    private static final String AVERAGE_HEIGHT_CENTIMETERS_TAG = "average-height-centimeters";
    private static final String UNKNOWN_PLANT_TAG_EXCEPTION_MESSAGE = "Unknown plant tag";

    @Override
    public List<Plant> parse(String file) throws ParserException {
        LOGGER.info(LOG_START_MESSAGE);
        List<Plant> plantList = new ArrayList<>();
        try {
            DOMParser domParser = new DOMParser();
            domParser.parse(file);
            Document document = domParser.getDocument();
            Element root = document.getDocumentElement();
            NodeList plantNodes = root.getChildNodes();
            for (int i = 0; i < plantNodes.getLength(); i++) {
                Node plantNode = plantNodes.item(i);
                if (plantNode.getNodeType() != Node.ELEMENT_NODE) continue;
                Plant plant = buildPlantFromNode(plantNode);
                plantList.add(plant);
            }
        } catch (IOException | SAXException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ParserException(e.getMessage(), e);
        }
        LOGGER.info(LOG_END_MESSAGE);
        return plantList;
    }

    private Plant buildPlantFromNode(Node plantNode) throws ParserException {
        Element plantElement = (Element) plantNode;
        String plantElementTagName = plantElement.getTagName();
        Plant plant = null;
        if (plantElementTagName.equals(TREE_TAG)) {
            plant = buildTreeFromElement(plantElement);
        } else if (plantElementTagName.equals(FLOWER_TAG)) {
            plant = buildFlowerFromElement(plantElement);
        }
        String plantName = getSingleChild(plantElement, NAME_TAG).getTextContent().trim();
        String plantOrigin = getSingleChild(plantElement, ORIGIN_TAG).getTextContent().trim();
        String plantGrowingTemperatureCelsiusAsString = getSingleChild(plantElement, GROWING_TEMPERATURE_CELSIUS_TAG)
                .getTextContent().trim();
        double plantGrowingTemperatureCelsius = Double.parseDouble(plantGrowingTemperatureCelsiusAsString);
        String plantSoilTypeAsString = getSingleChild(plantElement, SOIL_TYPE_TAG).getTextContent().trim();
        SoilType plantSoilType = SoilType.valueOf(plantSoilTypeAsString.toUpperCase());
        if (plant != null) {
            plant.setName(plantName);
            plant.setOrigin(plantOrigin);
            plant.setGrowingTemperatureCelsius(plantGrowingTemperatureCelsius);
            plant.setSoilType(plantSoilType);
        } else {
            LOGGER.error(UNKNOWN_PLANT_TAG_EXCEPTION_MESSAGE);
            throw new ParserException(UNKNOWN_PLANT_TAG_EXCEPTION_MESSAGE);
        }
        return plant;
    }

    private Tree buildTreeFromElement(Element plantElement) {
        String id = plantElement.getAttribute(ID_TAG);
        Tree tree = new Tree(id);
        String leafColor = getSingleChild(plantElement, LEAF_COLOR_TAG).getTextContent().trim();
        String averageHeightMetersAsString = getSingleChild(plantElement, AVERAGE_HEIGHT_METERS_TAG)
                .getTextContent().trim();
        double averageHeightMeters = Double.parseDouble(averageHeightMetersAsString);
        tree.setLeafColor(leafColor);
        tree.setAverageHeightMeters(averageHeightMeters);
        return tree;
    }

    private Flower buildFlowerFromElement(Element plantElement) {
        String id = plantElement.getAttribute(ID_TAG);
        Flower flower = new Flower(id);
        String petalColor = getSingleChild(plantElement, PETAL_COLOR_TAG).getTextContent().trim();
        String averageHeightCentimetersAsString = getSingleChild(plantElement, AVERAGE_HEIGHT_CENTIMETERS_TAG)
                .getTextContent().trim();
        double averageHeightCentimeters = Double.parseDouble(averageHeightCentimetersAsString);
        flower.setPetalColor(petalColor);
        flower.setAverageHeightCentimeters(averageHeightCentimeters);
        return flower;
    }

    private Element getSingleChild(Element element, String childName) {
        NodeList nodelist = element.getElementsByTagName(childName);
        return (Element) nodelist.item(0);
    }
}

