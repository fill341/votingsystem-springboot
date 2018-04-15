package com.shaakem.votingsystem.to;

import com.shaakem.votingsystem.model.Vote;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

public class VoteTo implements Serializable {
    @NotBlank
    private Integer id;

    @NotBlank
    private LocalDate localDate;

    @NotBlank
    private Integer userId;

    @NotBlank
    private String restaurant;

    public VoteTo() {
    }

    public VoteTo(Vote vote) {
        this.id = vote.getId();
        this.localDate = vote.getLocalDate();
        this.userId = vote.getUser().getId();
        this.restaurant = vote.getRestaurant().getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
