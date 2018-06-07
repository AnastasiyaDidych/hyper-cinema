package com.softserve.edu.hypercinema.dto;

import lombok.Data;

@Data
public class PaymentDto extends BaseDto {
    private Long id;
    private String type;
    private String cardName;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private int cvc;
//    private String holderName;
//    private boolean defaultPayment;
}
