//package com.softserve.ua.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name="movie_rent")
//public class MovieRentEntity extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "start_rent")
//    private LocalDate startRent;
//
//    // @Temporal(TemporalType.DATE)
//    @Column(name = "end_rent")
//    private LocalDate endRent;
//
//    @OneToOne(mappedBy = "movieRent", cascade = CascadeType.ALL)
//    private MovieEntity movie;
//
//    public MovieRentEntity(LocalDate startRent, LocalDate endRent) {
//        this.startRent = startRent;
//        this.endRent = endRent;
//    }
//}
