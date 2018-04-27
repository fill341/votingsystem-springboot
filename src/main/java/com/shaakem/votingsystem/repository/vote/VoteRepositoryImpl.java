package com.shaakem.votingsystem.repository.vote;

import com.shaakem.votingsystem.model.Vote;
import com.shaakem.votingsystem.repository.restaurant.CrudRestaurantRepository;
import com.shaakem.votingsystem.repository.user.CrudUserRepository;
import com.shaakem.votingsystem.to.VoteTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class VoteRepositoryImpl implements VoteRepository {
    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public VoteTo save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.getId(), userId, restaurantId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return new VoteTo(crudVoteRepository.save(vote));
    }

    @Override
    public Vote get(int id, int userId, int restaurantId) {
        return crudVoteRepository.findById(id).filter(new Predicate<Vote>() {
            @Override
            public boolean test(Vote vote) {
                return vote.getUser().getId() == userId && vote.getRestaurant().getId() == restaurantId;
            }
        }).orElse(null);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }

    @Override
    public Vote getPerToday(int userId) {
        LocalDateTime today = LocalDateTime.now();
        return crudVoteRepository.getPerToday(userId, today);
    }

    @Override
    public List<Vote> getAllPerToday() {
        LocalDateTime today = LocalDateTime.now();
        return crudVoteRepository.getAllPerToday(today);
    }
}
