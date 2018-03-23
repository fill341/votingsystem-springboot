package com.shaakem.votingsystem.repository.dish;

import com.shaakem.votingsystem.model.Dish;
import com.shaakem.votingsystem.repository.restaurant.CrudRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Predicate;

import static com.shaakem.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Repository
public class DishRepositoryImpl implements DishRepository{
    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(crudDishRepository.save(dish), dish.getId());
    }

    @Override
    public boolean delete(int id) {
        return crudDishRepository.delete(id) !=0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id).filter(new Predicate<Dish>() {
            @Override
            public boolean test(Dish dish) {
                return dish.getRestaurant().getId() == restaurantId;
            }
        }).orElse(null);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.findAll(restaurantId);
    }
}
