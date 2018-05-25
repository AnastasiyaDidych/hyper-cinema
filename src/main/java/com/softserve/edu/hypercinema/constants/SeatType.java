package com.softserve.edu.hypercinema.constants;

public enum SeatType {
    VIP("VIP"),
    BASE("base"),
    VIRTUAL("virtual");

    private String type;

    SeatType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
