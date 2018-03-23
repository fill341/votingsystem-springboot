package com.shaakem.votingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 5, max = 20)
    private String address;

    public Restaurant() {
    }

    public Restaurant(String name, String address) {
        this(null, name, address);
    }

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                '}';
    }
}
