package com.softserve.edu.hypercinema.constants;

public enum HallTypes {

    STATIC("STATIC"),
    VIRTUAL("VIRTUAL"),
    PERSONAL("PERSONAL");

    private String type;

    HallTypes(String type){
        this.type = type;
    }

}
