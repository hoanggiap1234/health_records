package com.viettel.etc.dto;

import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.TeleCareException;

import java.util.Arrays;

public enum IndexType {
    BLOOD_SUGAR("bloodSugar", "bloodSugar"),
    SPO2_SCORE("spo2Score", "spo2Score"),
    BLOOD_PRESSURE("bloodPressure", "bloodPressure"),
    HEART_BEAT("heartBeat", "heartBeat"),
    TEMPERATURE("temperature", "temperature"),
    HEIGHT("height", "height"),
    WEIGHT("weight", "weight"),
    BMI("bmi", "bmi");

    private IndexType(final String type, final String column) {
        this.type = type;
        this.column = column;
    }

    private String type;
    private String column;

    public String getType() {
        return type;
    }

    public String getColumn() {
        return column;
    }

    public static IndexType valueOfIgnoreCase(String name) throws TeleCareException {
        return Arrays.stream(IndexType.values()).filter(indexType -> indexType.type.equalsIgnoreCase(name)).findFirst().orElseThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
    }

}
