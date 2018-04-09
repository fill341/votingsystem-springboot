package com.shaakem.votingsystem.to;

import com.shaakem.votingsystem.model.Vote;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

public class VoteTo implements Serializable {
    @NotBlank
    private Integer id;

    @NotBlank
    private LocalDateTime dateTime;

    @NotBlank
    private Integer userId;

    @NotBlank
    private Integer restaurantId;

    public VoteTo() {
    }

    public VoteTo(Vote vote) {
        this.id = vote.getId();
        this.dateTime = vote.getDateTime();
        this.userId = vote.getUser().getId();
        this.restaurantId = vote.getRestaurant().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
