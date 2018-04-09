package com.shaakem.votingsystem.repository.vote;

import com.shaakem.votingsystem.model.Vote;

import java.util.List;

public interface VoteRepository {
    List<Vote> getAll(int userId);
}
