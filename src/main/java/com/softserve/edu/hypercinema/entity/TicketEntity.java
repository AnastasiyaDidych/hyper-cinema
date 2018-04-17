package com.softserve.edu.hypercinema.entity;

import javax.persistence.*;

@Entity
@Table (name = "tickets")
public class TicketEntity extends BaseEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // + fucking shit
    private Integer id;


    //+ some fucking shit for mapping with another table
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
