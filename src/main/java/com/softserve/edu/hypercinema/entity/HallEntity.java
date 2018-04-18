package com.softserve.edu.hypercinema.entity;


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
    private Integer capacity;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hall")
    private List<SeatEntity> seats;

    @OneToMany(mappedBy = "hallEntity",
            cascade = CascadeType.ALL)
    List<ScheduleEntity> scheduleEntities;

    
//      мій холл може не знати про розклад, йому цього не треба
//      тут не потрібен bidirectional звязок


}
