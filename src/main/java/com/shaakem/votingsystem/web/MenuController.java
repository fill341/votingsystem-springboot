package com.shaakem.votingsystem.web;

import com.shaakem.votingsystem.model.Dish;
import com.shaakem.votingsystem.model.Menu;
import com.shaakem.votingsystem.repository.dish.DishRepository;
import com.shaakem.votingsystem.repository.menu.MenuRepository;
import com.shaakem.votingsystem.to.MenuTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.shaakem.votingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(MenuController.REST_URL)
public class MenuController {
    static final String REST_URL = "/admin/rest/menus";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DishRepository dishRepository;

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Menu save(@PathVariable("restaurantId") int restaurantId, @RequestBody MenuTo menuTo) {

        Menu menu = new Menu(menuTo.getDateTime());

        Set<Dish> dishes = new HashSet<>();
        for (int dishId : menuTo.getDishes()) {
            dishes.add(dishRepository.get(dishId, restaurantId));
        }

        menu.setDishes(dishes);

        log.info("Save menu: " + menu);
        checkNew(menu);
        return menuRepository.save(menu, restaurantId);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MenuTo get(@PathVariable("id") int id, @RequestParam("restaurantId") int restaurantId) {
        log.info("Get menu by id: " + id + ". Restaurant id: " + restaurantId);
        return new MenuTo(menuRepository.get(id, restaurantId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MenuTo> getAll(@RequestParam("restaurantId") int restaurantId) {
        log.info("Get all menus with restaurantId: " + restaurantId);

        List<MenuTo> list = new ArrayList<>();
        for (Menu menu : menuRepository.getAll(restaurantId)) {
            MenuTo menuTo = new MenuTo(menu);
            list.add(menuTo);
        }

        return list;
    }

}
