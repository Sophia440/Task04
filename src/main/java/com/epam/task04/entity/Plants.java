package com.epam.task04.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "plants", namespace = "https://plants.com")
public class Plants {
    @XmlElements({
            @XmlElement(name = "tree", namespace = "https://plants.com", type = Tree.class),
            @XmlElement(name = "flower", namespace = "https://plants.com", type = Flower.class)
    })
    private List<Plant> plantStorage;
    private static final String NEW_LINE = "\n";


    public Plants() {
        this.plantStorage = new ArrayList<>();
    }

    public void addPlant(Plant plant) {
        plantStorage.add(plant);
    }

    @XmlTransient
    public List<Plant> getPlants() {
        return plantStorage;
    }


    public void addPlants(List<Plant> plants) {
        this.plantStorage.addAll(plants);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Plants plantsToCompare = (Plants) object;
        return Objects.equals(plantStorage, plantsToCompare.plantStorage);
    }

    @Override
    public int hashCode() {
        return plantStorage.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Plant currentPlant : plantStorage) {
            stringBuilder.append(currentPlant);
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder.toString();
    }
}

