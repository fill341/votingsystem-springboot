package com.shaakem.votingsystem.to;

import com.shaakem.votingsystem.model.Menu;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class MenuTo implements Serializable {
    @NotBlank
    private Integer id;

    @NotBlank
    private LocalDateTime dateTime;

//    private List<DishTo> dishes;
private List<Integer> dishes;

    public MenuTo() {
    }

    public MenuTo(Menu menu) {
        this.id = menu.getId();
        this.dateTime = menu.getDateTime();
//        this.dishes = menu.getDishes().stream().map(DishTo::new).collect(Collectors.toList());
    }

    ///////
    public MenuTo(int id, LocalDateTime dateTime, List<Integer> dishes) {
        this.id = id;
        this.dateTime = dateTime;
        this.dishes = dishes;
    }
    ///////

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

//    public List<DishTo> getDishes() {
//        return dishes;
//    }
//
//    public void setDishes(List<DishTo> dishes) {
//        this.dishes = dishes;
//    }


    public List<Integer> getDishes() {
        return dishes;
    }

    public void setDishes(List<Integer> dishes) {
        this.dishes = dishes;
    }
}
