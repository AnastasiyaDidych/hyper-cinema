package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VR
 * 13:59, 14.04.2018
 */

@Entity
@Table(name = "seat")
@NoArgsConstructor
@Getter
@Setter
public class SeatEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row")
    private Integer row;

    @Column(name = "number")
    private Integer number;

    @OneToMany(mappedBy = "seat")
    private List<TicketEntity> tickets;

}
