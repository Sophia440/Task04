package com.epam.task04.entity;

import com.epam.task04.enums.SoilType;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Plant", propOrder = {
        "name",
        "origin",
        "soilType",
        "growingTemperatureCelsius",
})

@XmlSeeAlso({
        Tree.class,
        Flower.class,
})

public abstract class Plant {
    @XmlElement(namespace = "https://plants.com", required = true)
    private String name;
    @XmlElement(namespace = "https://plants.com", required = true)
    private String origin;
    @XmlElement(name = "soil-type", namespace = "https://plants.com", required = true)
    private SoilType soilType;
    @XmlElement(name = "growing-temperature-celsius", namespace = "https://plants.com", required = true)
    private double growingTemperatureCelsius;

    @XmlAttribute(required = true)
    @XmlID
    private String id;

    public Plant() {

    }

    public Plant(String id) {
        this.id = id;
    }

    public Plant(String name, String origin, SoilType soilType, double growingTemperatureCelsius, String id) {
        this.name = name;
        this.origin = origin;
        this.soilType = soilType;
        this.growingTemperatureCelsius = growingTemperatureCelsius;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public double getGrowingTemperatureCelsius() {
        return growingTemperatureCelsius;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }

    public void setGrowingTemperatureCelsius(double growingTemperatureCelsius) {
        this.growingTemperatureCelsius = growingTemperatureCelsius;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Plant plant = (Plant) object;
        return Double.compare(plant.growingTemperatureCelsius, growingTemperatureCelsius) == 0 &&
                name.equals(plant.name) &&
                origin.equals(plant.origin) &&
                soilType == plant.soilType &&
                id.equals(plant.id);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + origin.hashCode() + soilType.hashCode() + id.hashCode()
                + Double.hashCode(growingTemperatureCelsius);
    }

    @Override
    public String toString() {
        return id + ": " + name + ", origin - " + origin + ", soil type - " + soilType +
                ", growing temperature = " + growingTemperatureCelsius + " C";
    }
}
