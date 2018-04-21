package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "coefficient")
@NoArgsConstructor

@Getter
@Setter
public class CoefficientEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "value", columnDefinition = "DECIMAL(3,2)")
    private BigDecimal value;

    @ManyToMany
    @JoinTable(name = "ticket_coefficient",
            joinColumns = @JoinColumn(name = "coefficient_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private List<TicketEntity> tickets;

}
