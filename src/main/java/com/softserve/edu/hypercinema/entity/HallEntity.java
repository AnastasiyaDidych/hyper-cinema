package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity (name = "Hall")
@Table (name = "hall")
@Getter
@Setter
@NoArgsConstructor
public class HallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "capacity", nullable = false)
    private Integer capasity;

    @Column (name = "type", nullable = false)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
//    private List<SeatEntity> seat_list; /*= new ArrayList<>();*/
    private Set<SeatEntity> seat_list; /*= new HashSet<>();*/


}
