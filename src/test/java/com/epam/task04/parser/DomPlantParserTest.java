package com.epam.task04.parser;

import com.epam.task04.entity.Flower;
import com.epam.task04.entity.Plant;
import com.epam.task04.entity.Tree;
import com.epam.task04.parser.dom.DomPlantParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DomPlantParserTest implements PlantParserTest {
    private final DomPlantParser parser = new DomPlantParser();

    @Test
    public void testParseWithValidXmlFileShouldSucceed() throws ParserException {
        List<Plant> actual = parser.parse(VALID_XML_FILE);
        Assert.assertEquals(EXPECTED, actual);
        Plant actualTree = actual.get(0);
        Plant actualFlower = actual.get(1);
        Assert.assertEquals(TREE_ID, actualTree.getId());
        Assert.assertEquals(TREE_NAME, actualTree.getName());
        Assert.assertEquals(TREE_ORIGIN, actualTree.getOrigin());
        Assert.assertEquals(TREE_SOIL_TYPE, actualTree.getSoilType());
        Assert.assertEquals(TREE_GROWING_TEMPERATURE_CELSIUS, actualTree.getGrowingTemperatureCelsius(), DELTA);
        Assert.assertEquals(TREE_LEAF_COLOR, ((Tree) actualTree).getLeafColor());
        Assert.assertEquals(TREE_AVERAGE_HEIGHT_METERS, ((Tree) actualTree).getAverageHeightMeters(), DELTA);
        Assert.assertEquals(FLOWER_ID, actualFlower.getId());
        Assert.assertEquals(FLOWER_NAME, actualFlower.getName());
        Assert.assertEquals(FLOWER_ORIGIN, actualFlower.getOrigin());
        Assert.assertEquals(FLOWER_SOIL_TYPE, actualFlower.getSoilType());
        Assert.assertEquals(FLOWER_GROWING_TEMPERATURE_CELSIUS, actualFlower.getGrowingTemperatureCelsius(), DELTA);
        Assert.assertEquals(FLOWER_PETAL_COLOR, ((Flower) actualFlower).getPetalColor());
        Assert.assertEquals(FLOWER_AVERAGE_HEIGHT_CENTIMETERS, ((Flower) actualFlower).getAverageHeightCentimeters(),
                DELTA);
    }

    @Test
    public void testParseWithInvalidAttributeShouldFail() throws ParserException {
        List<Plant> actual = parser.parse(VALID_XML_FILE);
        Plant actualTree = actual.get(0);
        Assert.assertEquals(INVALID_TREE_ID, actualTree.getId());
    }
}
