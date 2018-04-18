package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    @Column(name = "value")
    private Double value;

    public CoefficientEntity(String type, Double value) {
        this.type = type;
        this.value = value;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private PriceEntity price;

}
