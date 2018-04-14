package com.shaakem.votingsystem.service;

import com.shaakem.votingsystem.model.Vote;
import com.shaakem.votingsystem.repository.vote.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class VotesCounting {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteRepository voteRepository;

    //    @Scheduled(cron = "0 0 11 * * *")
    @Scheduled(cron = "*/10 * * * * *")
    public void reportCurrentTime() {
        log.info("Votes Counting per today: ", LocalDate.now());

        Map<Integer, Integer> map = new TreeMap<>();

        List<Vote> listVotes = voteRepository.getAllPerToday();
//        List<Integer> list = new ArrayList<>();
//        for (Vote vote : voteRepository.getAllPerToday()) {
//            list.add(vote.getRestaurant().getId());
//            map.put(vote.getRestaurant().getId(), map.get(vote.getRestaurant().getId()) + 1);
//        }

        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            log.info("*** restId: " + pair.getKey() + " count: " + pair.getValue());
        }

        log.info(" Winner is TODO");
    }
}
