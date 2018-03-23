package com.shaakem.votingsystem.repository.dish;

import com.shaakem.votingsystem.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int restaurantId);

    void update(Dish dish);

    boolean delete(int id);

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);
}
