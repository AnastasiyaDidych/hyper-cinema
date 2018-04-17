package com.softserve.edu.hypercinema.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // + fucking shit
    private Integer id;

    @Column(name = "is_pending")
    private boolean pending;

    @Column(name = "is_confirming")
    private boolean confirming;

    //+ some fucking shit for mapping with another table

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    private List<TicketEntity> ticketList;


    public OrderEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isConfirming() {
        return confirming;
    }

    public void setConfirming(boolean confirming) {
        this.confirming = confirming;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<TicketEntity> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketEntity> ticketList) {
        this.ticketList = ticketList;
    }
}
