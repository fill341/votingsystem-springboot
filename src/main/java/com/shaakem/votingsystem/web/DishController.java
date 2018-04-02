package com.shaakem.votingsystem.web;

import com.shaakem.votingsystem.model.Dish;
import com.shaakem.votingsystem.repository.dish.DishRepository;
import com.shaakem.votingsystem.repository.menu.MenuRepository;
import com.shaakem.votingsystem.to.DishTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.shaakem.votingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(DishController.REST_URL)
public class DishController {
    static final String REST_URL = "/admin/rest/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DishRepository dishRepository;

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Dish save(@PathVariable("restaurantId") int restaurantId, @RequestBody Dish dish) {
        log.info("Save dish: " + dish);
        checkNew(dish);
        return dishRepository.save(dish, restaurantId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") int id) {
        log.info("Delete dish by id: " + id);
        dishRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DishTo get(@PathVariable("id") int id, @RequestParam("restaurantId") int restaurantId) {
        log.info("Get dish by id: " + id + ". Restaurant id: " + restaurantId);
        return new DishTo(dishRepository.get(id, restaurantId));
    }

//    @GetMapping(params = "restaurantId", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public List<DishTo> getAll(
//            @RequestParam(value = "restaurantId") int restaurantId
//    ) {
//        log.info("Get all dishes with restaurantId: " + restaurantId);
//
//        List<DishTo> list = new ArrayList<>();
//        for (Dish dish : dishRepository.getAll(restaurantId)) {
//            DishTo dishTo = new DishTo(dish);
//            list.add(dishTo);
//        }
//
//        return list;
//    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<DishTo> getByMenuId(
            @RequestParam(value = "menuId") int menuId,
            @RequestParam(value = "restaurantId") int restaurantId
    ) {
        log.info("Get all dishes with menuId: " + menuId);

        List<DishTo> list = new ArrayList<>();
        for (Dish dish : menuRepository.get(menuId, restaurantId).getDishes()) {
            DishTo dishTo = new DishTo(dish);
            list.add(dishTo);
        }

        return list;
    }
}
