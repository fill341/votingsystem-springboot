package com.shaakem.votingsystem.service;

import com.shaakem.votingsystem.model.Vote;
import com.shaakem.votingsystem.repository.vote.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class VotesCounting {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteRepository voteRepository;

    //    @Scheduled(cron = "0 0 11 * * *")
    @Scheduled(cron = "*/10 * * * * *")
    public void reportCurrentTime() {
        log.info("Votes Counting per today: " + LocalDate.now());

        Map<Integer, Integer> restaurantIdWithVotesCount = new HashMap<>();

        for (Vote vote : voteRepository.getAllPerToday())
            if (restaurantIdWithVotesCount.containsKey(vote.getRestaurant().getId())) {
                restaurantIdWithVotesCount.put(vote.getRestaurant().getId(), restaurantIdWithVotesCount.get(vote.getRestaurant().getId()) + 1);
            } else {
                restaurantIdWithVotesCount.put(vote.getRestaurant().getId(), 1);
            }

        Stream<Map.Entry<Integer,Integer>> sortedRestaurantIdWithVotesCount =
                restaurantIdWithVotesCount.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        log.info(" Winner is restaurant with id: " + sortedRestaurantIdWithVotesCount.findFirst().get().getKey());
    }
}
