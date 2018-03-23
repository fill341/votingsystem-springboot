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

@RestController
@RequestMapping(RestaurantController.REST_URL)
public class RestaurantController {
    static final String REST_URL = "/admin/rest/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

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
