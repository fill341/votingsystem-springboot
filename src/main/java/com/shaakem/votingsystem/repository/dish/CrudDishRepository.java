package com.shaakem.votingsystem.repository.dish;

import com.shaakem.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Override
    @Transactional
    Dish save(Dish dish);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Query("FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> findAll(@Param("restaurantId") int restaurantId);
}
