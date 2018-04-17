package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VR
 * 11:18, 14.04.2018
 */

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_pending")
    private boolean pending;

    @Column(name = "is_confirmed")
    private boolean confirmed;

    @OneToMany(mappedBy = "order")
    private List<TicketEntity> tickets = new ArrayList<>();

}
