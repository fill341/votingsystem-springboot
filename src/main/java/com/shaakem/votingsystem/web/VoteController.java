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

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Vote save(@PathVariable("restaurantId") int restaurantId, @RequestBody Vote vote) {
        log.info("Save vote: " + vote);
        checkNew(vote);

        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!! " + vote.getDateTime());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        int userId = userService.getByName(name).getId();
        return voteRepository.save(vote, userId, restaurantId);
//        return null;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthorizedUser authorizedUser) {

//        int userId = 100001;
//        log.info("Get all votes by userId: " + userId);

//

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
