package com.shaakem.votingsystem.repository.menu;

import com.shaakem.votingsystem.model.Menu;
import com.shaakem.votingsystem.repository.restaurant.CrudRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Predicate;

import static com.shaakem.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Repository
public class MenuRepositoryImpl implements MenuRepository {
    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);
    }

    @Override
    public void update(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(crudMenuRepository.save(menu), menu.getId());
    }

    @Override
    public boolean delete(int id) {
        return crudMenuRepository.delete(id) !=0;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id).filter(new Predicate<Menu>() {
            @Override
            public boolean test(Menu menu) {
                return menu.getRestaurant().getId() == restaurantId;
            }
        }).orElse(null);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return crudMenuRepository.findAll(restaurantId);
    }
}
