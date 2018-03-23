package com.shaakem.votingsystem.repository.menu;

import com.shaakem.votingsystem.model.Menu;

import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu, int restaurantId);

    void update(Menu menu);

    boolean delete(int id);

    Menu get(int id, int restaurantId);

    List<Menu> getAll(int restaurantId);
}
