package com.shaakem.votingsystem.web;

import com.shaakem.votingsystem.AuthorizedUser;
import com.shaakem.votingsystem.model.Vote;
import com.shaakem.votingsystem.repository.vote.VoteRepository;
import com.shaakem.votingsystem.service.UserService;
import com.shaakem.votingsystem.to.VoteTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.shaakem.votingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(VoteController.REST_URL)
public class VoteController {
    static final String REST_URL = "/profile/rest/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public VoteTo save(@PathVariable("restaurantId") int restaurantId) {
        Vote vote = new Vote(LocalDate.now());
        log.info("Save vote: " + vote);

        checkNew(vote);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        int userId = userService.getByName(name).getId();

        return voteRepository.save(vote, userId, restaurantId);
    }

    @GetMapping(value = "/isVoteAvailable", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Boolean isVoteAvailable() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        int userId = userService.getByName(name).getId();

        return voteRepository.getPerToday(userId) == null;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthorizedUser authorizedUser) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        int userId = userService.getByName(name).getId();

        log.info("Get all votes by userId: " + userId);

        List<VoteTo> list = new ArrayList<>();
        for (Vote vote : voteRepository.getAll(userId)) {
            VoteTo voteTo = new VoteTo(vote);
            list.add(voteTo);
        }

        return list;
    }
}
