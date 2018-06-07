package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.PaymentConverter;
import com.softserve.edu.hypercinema.dto.PaymentDto;
import com.softserve.edu.hypercinema.entity.PaymentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverterImpl implements PaymentConverter {

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PaymentEntity convertToEntity(PaymentDto paymentDto) {

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setCardName(paymentDto.getCardName());
        paymentEntity.setCardNumber(paymentDto.getCardNumber());
        paymentEntity.setExpiryMonth(paymentDto.getExpiryMonth());
        paymentEntity.setExpiryYear(paymentDto.getExpiryYear());
        paymentEntity.setType(paymentDto.getType());

        return paymentEntity;
    }

    @Override
    public PaymentDto convertToDto(PaymentEntity paymentEntity) {

        PaymentDto paymentDto = modelMapper.map(paymentEntity, PaymentDto.class);
        paymentDto.setCardName(paymentEntity.getCardName());
        paymentDto.setCardNumber(paymentEntity.getCardNumber());
        paymentDto.setExpiryMonth(paymentEntity.getExpiryMonth());
        paymentDto.setExpiryYear(paymentEntity.getExpiryYear());
        paymentDto.setType(paymentEntity.getType());

        return paymentDto;
    }
}
