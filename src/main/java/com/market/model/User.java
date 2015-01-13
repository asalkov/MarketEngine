package com.market.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id = 0;
    private String email;
    private String passwordHash;

    public void setEmail(String aEmail){
        this.email = aEmail;
    }

    public void setPasswordHash(String hash){
        this.passwordHash = hash;
    }

    public long getId() {
        return id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

    public void setAccount(Account account){
        this.account = account;
    }

    public void addTimeSlot(TimeSlot ts){
        timeSlots.add(ts);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
