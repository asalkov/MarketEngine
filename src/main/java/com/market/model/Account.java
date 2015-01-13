package com.market.model;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private long id;
    private double amount;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private User user = null;

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }
}