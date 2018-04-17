package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "price")
@NoArgsConstructor
@Getter
@Setter
public class PriceEntity extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
  
    @OneToMany(mappedBy = "price", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Column(name = "coefficient_id")
    private List<CoefficientEntity> coefficients;
  

    @OneToOne(mappedBy = "price", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private TicketEntity ticket;
 
}
