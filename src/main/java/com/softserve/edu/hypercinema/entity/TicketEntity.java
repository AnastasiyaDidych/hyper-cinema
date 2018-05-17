package com.softserve.edu.hypercinema.entity;

import com.softserve.edu.hypercinema.validator.IsTicketAvailable;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
//@IsTicketAvailable
public class TicketEntity extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "order_id")
    private OrderEntity order;

//    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private SessionEntity session;

    @Column(name = "price", columnDefinition = "DECIMAL(6,2)")
    private BigDecimal price;

//    @NotEmpty
    @Column(name = "barcode")
    private String barcode;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "ticket_coefficient",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "coefficient_id"))
    private List<CoefficientEntity> coefficients;

//    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private SeatEntity seat;

}
