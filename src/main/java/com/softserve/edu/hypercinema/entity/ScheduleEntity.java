package com.softserve.edu.hypercinema.entity;


import com.softserve.edu.hypercinema.model.DaysModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class ScheduleEntity extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "day_time_id")
    private DayTimeEntity dayTime;

    @OneToMany(mappedBy = "schedule",
            cascade = CascadeType.ALL)
    private List<SessionEntity> sessions;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private HallEntity hall;


    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;



}

