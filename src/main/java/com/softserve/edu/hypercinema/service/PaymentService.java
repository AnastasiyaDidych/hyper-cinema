package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.PaymentEntity;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.List;

public interface PaymentService {

    void createPayment(PaymentEntity payment);

    PaymentEntity getPayment(Long id);

    List<PaymentEntity> getPayments(Authentication authentication);

    PaymentEntity getPayment(Long id, Authentication authentication);
//    for future
//    void updatePayment(PaymentEntity userPaymentEntity);
//
//    void deletePayment(Long id);
}
