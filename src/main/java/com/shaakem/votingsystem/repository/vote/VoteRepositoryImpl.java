package com.shaakem.votingsystem.repository.vote;

import com.shaakem.votingsystem.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {
    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }

    @Override
    public List<Vote> getAllPerToday() {
        LocalDateTime today = LocalDateTime.now();
        return crudVoteRepository.getAllPerToday(today);
    }
}
