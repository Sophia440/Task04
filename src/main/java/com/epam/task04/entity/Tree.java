package com.epam.task04.entity;

import com.epam.task04.enums.SoilType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tree", propOrder = {"leafColor", "averageHeightMeters"})

public class Tree extends Plant {
    @XmlElement(name = "leaf-color", namespace = "https://plants.com", required = true)
    private String leafColor;
    @XmlElement(name = "average-height-meters", namespace = "https://plants.com", required = true)
    private double averageHeightMeters;

    public Tree() {

    }

    public Tree(String id) {
        super(id);
    }

    public Tree(String name, String origin, SoilType soilType, double growingTemperatureCelsius, String leafColor,
                double averageHeightMeters, String id) {
        super(name, origin, soilType, growingTemperatureCelsius, id);
        this.leafColor = leafColor;
        this.averageHeightMeters = averageHeightMeters;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public void setAverageHeightMeters(double averageHeightMeters) {
        this.averageHeightMeters = averageHeightMeters;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public double getAverageHeightMeters() {
        return averageHeightMeters;
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
        Tree tree = (Tree) object;
        return Double.compare(tree.averageHeightMeters, averageHeightMeters) == 0 &&
                leafColor.equals(tree.leafColor);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + leafColor.hashCode() + Double.hashCode(averageHeightMeters);
    }

    @Override
    public String toString() {
        return super.toString() + ",\ntype - tree, leaf color - " + leafColor + ", average height = "
                + averageHeightMeters + " meters";
    }
}
