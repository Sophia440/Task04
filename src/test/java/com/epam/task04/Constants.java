package com.epam.task04;

import com.epam.task04.entity.Flower;
import com.epam.task04.entity.Plant;
import com.epam.task04.entity.Tree;
import com.epam.task04.enums.SoilType;

import java.util.Arrays;
import java.util.List;

public interface Constants {
    String VALID_XML_FILE = "./src/test/resources/test_plants.xml";
    String INVALID_TREE_ID = "ID2";
    String TREE_ID = "ID1";
    String TREE_NAME = "Red Maple";
    String TREE_ORIGIN = "Asia";
    SoilType TREE_SOIL_TYPE = SoilType.SAND;
    double TREE_GROWING_TEMPERATURE_CELSIUS = 20.0;
    String TREE_LEAF_COLOR = "Red";
    double TREE_AVERAGE_HEIGHT_METERS = 30.0;
    String FLOWER_ID = "ID4";
    String FLOWER_NAME = "Chamomile";
    String FLOWER_ORIGIN = "Germany";
    SoilType FLOWER_SOIL_TYPE = SoilType.CLAY;
    double FLOWER_GROWING_TEMPERATURE_CELSIUS = 20.0;
    String FLOWER_PETAL_COLOR = "White";
    double FLOWER_AVERAGE_HEIGHT_CENTIMETERS = 91.0;
    Tree EXPECTED_TREE = new Tree(TREE_NAME, TREE_ORIGIN, TREE_SOIL_TYPE,
            TREE_GROWING_TEMPERATURE_CELSIUS, TREE_LEAF_COLOR, TREE_AVERAGE_HEIGHT_METERS, TREE_ID);
    Flower EXPECTED_FLOWER = new Flower(FLOWER_NAME, FLOWER_ORIGIN, FLOWER_SOIL_TYPE,
            FLOWER_GROWING_TEMPERATURE_CELSIUS, FLOWER_PETAL_COLOR, FLOWER_AVERAGE_HEIGHT_CENTIMETERS, FLOWER_ID);
    List<Plant> EXPECTED = Arrays.asList(EXPECTED_TREE, EXPECTED_FLOWER);
    double DELTA = 0.00000000001;
}
