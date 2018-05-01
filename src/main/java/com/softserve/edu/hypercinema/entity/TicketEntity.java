package com.softserve.edu.hypercinema.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class TicketEntity extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

//    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id",  nullable = false)
    private SessionEntity session;

    @Column(name = "price", columnDefinition = "DECIMAL(6,2)")
    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "ticket_coefficient",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "coefficient_id"))
    private List<CoefficientEntity> coefficients;

//    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private SeatEntity seat;

}
