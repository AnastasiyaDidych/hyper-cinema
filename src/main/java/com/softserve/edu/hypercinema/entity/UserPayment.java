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
public class UserPayment extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String type;
    private String cardName;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private String holderName;
    private boolean defaultPayment;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
}
