package com.market.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "service_provider")
public class ServiceProvider extends User {

    @Column(name = "service_price")
    private double servicePrice;


    public void setServicePrice(double price){
        this.servicePrice = price;
    }


}
