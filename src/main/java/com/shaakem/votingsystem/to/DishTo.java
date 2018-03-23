package com.shaakem.votingsystem.to;

import com.shaakem.votingsystem.model.Dish;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class DishTo implements Serializable {
    @NotBlank
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private Integer price;

//    private List<MenuTo> menus;

    public DishTo() {
    }

    public DishTo(Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.price = dish.getPrice();
//        this.menus = dish.getMenus().stream().map(MenuTo::new).collect(Collectors.toList());
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
//
//    public List<MenuTo> getMenus() {
//        return menus;
//    }
//
//    public void setMenus(List<MenuTo> menus) {
//        this.menus = menus;
//    }
}
