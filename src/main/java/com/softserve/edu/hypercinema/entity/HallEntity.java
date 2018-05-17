package com.softserve.edu.hypercinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hall")
@Getter
@Setter
@NoArgsConstructor
public class HallEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "tech", nullable = false)
    private String tech;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hall")
    private List<SeatEntity> seats;

    @OneToMany(mappedBy = "hall")
    private List<SessionEntity> sessions;


}
