package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "price")
@NoArgsConstructor
@Getter
@Setter
public class PriceEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @Column(name = "ticket_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private TicketEntity ticket;*/

    @OneToMany(mappedBy = "price", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Column(name = "coefficient_id")
    private List<CoefficientEntity> coefficients;
}
