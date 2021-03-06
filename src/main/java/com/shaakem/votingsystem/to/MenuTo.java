package com.shaakem.votingsystem.to;

import com.shaakem.votingsystem.model.Menu;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MenuTo implements Serializable {
    @NotBlank
    private Integer id;

    @NotBlank
    private LocalDate localDate;

    @NotBlank
    private Integer restaurantId;

    @NotBlank
    private String restaurantName;

    private List<DishTo> dishes;

    private List<Integer> dishesId;

    public MenuTo() {
    }

    public MenuTo(Menu menu) {
        this.id = menu.getId();
        this.localDate = menu.getLocalDate();
        this.restaurantId = menu.getRestaurant().getId();
        this.restaurantName = menu.getRestaurant().getName();
        this.dishes = menu.getDishes().stream().map(DishTo::new).collect(Collectors.toList());
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

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<DishTo> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishTo> dishes) {
        this.dishes = dishes;
    }

    public List<Integer> getDishesId() {
        return dishesId;
    }

    public void setDishesId(List<Integer> dishes) {
        this.dishesId = dishes;
    }
}
