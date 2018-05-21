package com.softserve.edu.hypercinema.constants;

public enum SeatStatus {
    VIP("VIP"),
    BASE("base"),
    VIRTUAL("virtual");

    private String status;

    SeatStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
