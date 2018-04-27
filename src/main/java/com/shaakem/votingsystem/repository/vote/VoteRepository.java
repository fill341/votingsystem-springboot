package com.shaakem.votingsystem.repository.vote;

import com.shaakem.votingsystem.model.Vote;
import com.shaakem.votingsystem.to.VoteTo;

import java.util.List;

public interface VoteRepository {
    VoteTo save(Vote vote, int userId, int restaurantId);

    Vote get(int id, int userId, int restaurantId);

    List<Vote> getAll(int userId);

    Vote getPerToday(int userId);

    List<Vote> getAllPerToday();
}
