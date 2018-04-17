package com.softserve.edu.hypercinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VR
 * 11:21, 14.04.2018
 */

@Entity
@Table(name = "session")
@NoArgsConstructor
@Getter
@Setter
public class SessionEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "session")
    List<TicketEntity> tickets = new ArrayList<>();
}
