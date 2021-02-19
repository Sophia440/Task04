package com.epam.task04.parser;

import com.epam.task04.entity.Plant;

import java.util.List;

public interface PlantParser {

    List<Plant> parse(String file) throws ParserException;

}
