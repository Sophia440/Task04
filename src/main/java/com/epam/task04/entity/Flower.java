package com.epam.task04.entity;

import com.epam.task04.enums.SoilType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flower", propOrder = {"petalColor", "averageHeightCentimeters"})

public class Flower extends Plant {
    @XmlElement(name = "petal-color", namespace = "https://plants.com", required = true)
    private String petalColor;
    @XmlElement(name = "average-height-centimeters", namespace = "https://plants.com", required = true)
    private double averageHeightCentimeters;

    public Flower() {

    }

    public Flower(String id) {
        super(id);
    }

    public Flower(String name, String origin, SoilType soilType, double growingTemperatureCelsius, String petalColor,
                  double averageHeightCentimeters, String id) {
        super(name, origin, soilType, growingTemperatureCelsius, id);
        this.petalColor = petalColor;
        this.averageHeightCentimeters = averageHeightCentimeters;
    }

    public void setPetalColor(String petalColor) {
        this.petalColor = petalColor;
    }

    public void setAverageHeightCentimeters(double averageHeightCentimeters) {
        this.averageHeightCentimeters = averageHeightCentimeters;
    }

    public String getPetalColor() {
        return petalColor;
    }

    public double getAverageHeightCentimeters() {
        return averageHeightCentimeters;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        Flower flower = (Flower) object;
        return Double.compare(flower.averageHeightCentimeters, averageHeightCentimeters) == 0 &&
                Objects.equals(petalColor, flower.petalColor);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + petalColor.hashCode() + Double.hashCode(averageHeightCentimeters);
    }

    @Override
    public String toString() {
        return super.toString() + ",\ntype - flower, petal color - " + petalColor
                + ", average height = " + averageHeightCentimeters + " centimeters";
    }
}
