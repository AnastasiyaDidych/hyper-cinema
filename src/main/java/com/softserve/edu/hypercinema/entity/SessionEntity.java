package com.softserve.edu.hypercinema.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session")
public class SessionEntity extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "start_time")
    private LocalTime startTime;


    @Column (name = "end_time")
    private LocalTime endTime;


    private Boolean active;



    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity scheduleo;

//    @OneToOne(mappedBy = "ticket")
//    private Ticket tiket;












}
