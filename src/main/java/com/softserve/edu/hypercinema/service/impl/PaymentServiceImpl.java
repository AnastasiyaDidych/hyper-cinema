package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.PaymentEntity;
import com.softserve.edu.hypercinema.entity.UserEntity;
import com.softserve.edu.hypercinema.exception.AccessViolationException;
import com.softserve.edu.hypercinema.exception.PaymentNotFoundException;
import com.softserve.edu.hypercinema.repository.PaymentRepository;
import com.softserve.edu.hypercinema.service.OrderService;
import com.softserve.edu.hypercinema.service.PaymentService;
import com.softserve.edu.hypercinema.service.UserService;
import com.softserve.edu.hypercinema.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private static final String ACCESS_VIOLATION_MESSAGE = "You don't have permission to view this payment";
    private static final String PAYMENT_NOT_FOUND_MESSAGE = "Could not find payment with id=";


    @Autowired
    PaymentRepository paymentRepository;


    @Override
    public void createPayment(PaymentEntity payment) {
        paymentRepository.save(payment);

    }


    // for some test and reasons
    @Override
    public PaymentEntity getPayment(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new PaymentNotFoundException(PAYMENT_NOT_FOUND_MESSAGE + id));
    }


    @Override
    public List<PaymentEntity> getPayments(Authentication authentication) {
        if (AuthUtil.isManager(authentication)) {
            return paymentRepository.findAll();

        } else throw new AccessViolationException(ACCESS_VIOLATION_MESSAGE);

    }


    @Override
    public PaymentEntity getPayment(Long id, Authentication authentication) {
        if (AuthUtil.isManager(authentication)) {
            return paymentRepository.findById(id).orElseThrow(() ->
                    new PaymentNotFoundException(PAYMENT_NOT_FOUND_MESSAGE + id));

        } else throw new AccessViolationException(ACCESS_VIOLATION_MESSAGE);

    }
}
