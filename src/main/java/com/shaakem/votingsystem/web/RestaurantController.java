package com.shaakem.votingsystem.web;

import com.shaakem.votingsystem.model.Restaurant;
import com.shaakem.votingsystem.repository.restaurant.RestaurantRepository;
import com.shaakem.votingsystem.to.RestaurantTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.shaakem.votingsystem.util.ValidationUtil.assureIdConsistent;
import static com.shaakem.votingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(RestaurantController.REST_URL)
public class RestaurantController {
    static final String REST_URL = "/admin/rest/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Restaurant save(@RequestBody Restaurant restaurant) {
        log.info("Save restaurant: " + restaurant);
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        log.info("Update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantRepository.update(restaurant);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") int id) {
        log.info("Delete restaurant by id: " + id);
        restaurantRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestaurantTo get(@PathVariable("id") int id) {
        log.info("Get restaurant by id: " + id);
        return new RestaurantTo(restaurantRepository.get(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RestaurantTo> getAll() {
        log.info("Get all restaurants.");

        List<RestaurantTo> list = new ArrayList<>();
        for (Restaurant restaurant : restaurantRepository.getAll()) {
            RestaurantTo restaurantTo = new RestaurantTo(restaurant);
            list.add(restaurantTo);
        }

        return list;
    }
}
