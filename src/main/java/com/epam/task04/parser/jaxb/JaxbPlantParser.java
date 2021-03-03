package com.epam.task04.parser.jaxb;

import com.epam.task04.entity.Plant;
import com.epam.task04.entity.Plants;
import com.epam.task04.parser.ParserException;
import com.epam.task04.parser.PlantParser;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JaxbPlantParser implements PlantParser {
    private static final Logger LOGGER = Logger.getLogger(JaxbPlantParser.class);
    private static final String LOG_START_MESSAGE = "Start parsing XML file with JAXB parser";
    private static final String LOG_END_MESSAGE = "XML file was parsed successfully";

    @Override
    public List<Plant> parse(String file) throws ParserException {
        LOGGER.info(LOG_START_MESSAGE);
        FileReader fileReader = null;
        List<Plant> plantList;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Plants.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            fileReader = new FileReader(file);
            Plants plants = (Plants) unmarshaller.unmarshal(fileReader);
            plantList = plants.getPlants();
        } catch (JAXBException | FileNotFoundException e) {
            throw new ParserException(e.getMessage(), e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        LOGGER.info(LOG_END_MESSAGE);
        return plantList;
    }
}
