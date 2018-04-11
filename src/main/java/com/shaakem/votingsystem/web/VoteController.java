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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(VoteController.REST_URL)
public class VoteController {
    static final String REST_URL = "/profile/rest/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthorizedUser authorizedUser) {

        int userId = 100001;
        log.info("Get all votes by userId: " + userId);

        int uId = authorizedUser.get().getId();
        log.info("Authorized uer Id: "+ uId);

        List<VoteTo> list = new ArrayList<>();
        for (Vote vote : voteRepository.getAll(userId)) {
            VoteTo voteTo = new VoteTo(vote);
            list.add(voteTo);
        }

        return list;
    }
}
