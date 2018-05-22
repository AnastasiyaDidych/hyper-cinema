package com.softserve.edu.hypercinema.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "seat")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatEntity extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row")
    private int row;

    @Column(name = "number")
    private int number;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(mappedBy = "seat")
    private List<TicketEntity> tickets;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private HallEntity hall;

}
