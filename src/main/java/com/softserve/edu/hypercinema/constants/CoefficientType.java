package com.softserve.edu.hypercinema.constants;

import java.math.BigDecimal;

public enum CoefficientType {
    PREMIER(1L, BigDecimal.valueOf(2), "PREMIER"),
    END(2L, BigDecimal.valueOf(0.8), "END"),
    VIP(3L, BigDecimal.valueOf(1.3), "VIP"),
    BASE(4L, BigDecimal.valueOf(1.1), "BASE"),
    VIRTUAL(5L, BigDecimal.valueOf(1.1), "VIRTUAL"),
    DEF(6L, BigDecimal.valueOf(1), "DEF");

    private Long id;
    private BigDecimal value;
    private String type;

    CoefficientType(Long id, BigDecimal value, String type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public Long getId(){
        return this.id;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public String getType(){
        return this.type;
    }
}
