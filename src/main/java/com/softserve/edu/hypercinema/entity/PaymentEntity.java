package com.softserve.edu.hypercinema.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaymentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String cardName;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
//    private String holderName;
//    private boolean defaultPayment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable = false)
    private OrderEntity order;
}
