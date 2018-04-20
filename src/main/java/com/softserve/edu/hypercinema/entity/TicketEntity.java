package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@Getter
@Setter
public class TicketEntity extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

//    @NotEmpty
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "session_id",  nullable = false)
    private SessionEntity session;

//    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
//    private PriceEntity price;

    @Column(name = "price", columnDefinition = "DECIMAL(6,2)")
    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "ticket_coefficient",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "coefficient_id"))
    private List<CoefficientEntity> coefficients;

//    @NotEmpty
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "seat_id")
    private SeatEntity seat;

}
