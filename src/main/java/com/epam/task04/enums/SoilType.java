package com.epam.task04.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "soil-type")
@XmlEnum
public enum SoilType {
    @XmlEnumValue("Clay")
    CLAY,
    @XmlEnumValue("Sand")
    SAND,
    @XmlEnumValue("Silt")
    SILT,
    @XmlEnumValue("Loams")
    LOAMS
}