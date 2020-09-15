package com.redenergy.simplenem12.model;

public enum RecordType {

    START_REC("100"),
    METER_READ_REC("200"),
    METER_VOLUME_REC("300"),
    END_REC("900");

    private final String value;

    RecordType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
