package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class MovieEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String title;

    @Column(nullable = false)
    @NotEmpty
    private String description;

    private Duration duration;

    private String genre;

    @Column(name = "start_rent")
    private LocalDate startRent;

    // @Temporal(TemporalType.DATE)
    @Column(name = "end_rent")
    private LocalDate endRent;

    @Column(name = "age_rating")
    private int ageRating;

    @Column(columnDefinition = "DECIMAL(5,2)")
    private BigDecimal price;


//    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "movie_rent_id", nullable = true)
//    private MovieRentEntity movieRent;


}

