package com.shaakem.votingsystem.to;

import com.shaakem.votingsystem.model.Restaurant;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class RestaurantTo implements Serializable {
    @NotBlank
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    public RestaurantTo() {
    }

    public RestaurantTo(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address= restaurant.getAddress();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
