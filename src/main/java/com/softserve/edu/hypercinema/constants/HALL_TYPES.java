package com.softserve.edu.hypercinema.constants;

public enum HALL_TYPES {
    STATIC("STATIC"),
    VIRTUAL("VIRTUAL"),
    PERSONAL("PERSONAL");

    private String type;
    private HALL_TYPES(String type){
        this.type = type;
    }
}
