//package com.softserve.edu.hypercinema.entity;
//
//import com.softserve.edu.hypercinema.model.DaysModel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalTime;
//import java.util.List;
//
//
//@Entity
//@Table(name = "day_time")
//@Getter
//@Setter
//@NoArgsConstructor
//public class DayTimeEntity extends BaseEntity {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "time")
//    private LocalTime time;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "day")
//    private DaysModel day;
//
//    @OneToMany(mappedBy = "dayTime")
//    private List<ScheduleEntity> schedule;
//
//}