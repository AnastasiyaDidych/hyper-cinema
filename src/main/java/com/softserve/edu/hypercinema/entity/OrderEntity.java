package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_")
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_pended")
    private boolean pended;
  
    @Column(name = "is_confirmed")
    private boolean confirmed;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<TicketEntity> tickets;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name ="sum" )
    private BigDecimal orderTotal;

    @Column(name ="order_number" )
    private String orderNumber;

//    @OneToOne(fetch = FetchType.LAZY,
//    cascade = CascadeType.ALL, mappedBy = "order")
//    private PaymentEntity payment;

}