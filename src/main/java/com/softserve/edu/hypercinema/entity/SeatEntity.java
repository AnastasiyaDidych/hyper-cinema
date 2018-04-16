package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity (name = "Seat")
@Table (name = "seat")
@Getter
@Setter
@NoArgsConstructor
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer id;

    @Column (name = "row")
    private Integer row;

    @Column (name = "number")
    private Integer number;

    /*??????????????????????????????????????*/
    @ManyToOne (cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Integer hall_id;

}
