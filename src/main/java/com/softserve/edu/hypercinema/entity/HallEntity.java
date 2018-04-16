package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "hall")
@Getter
@Setter
@NoArgsConstructor
public class HallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "capacity", nullable = false)
    private Integer capasity;

    @Column (name = "type", nullable = false)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hallEntity")

    private List<SeatEntity> seatEntities = new ArrayList<>();

}
