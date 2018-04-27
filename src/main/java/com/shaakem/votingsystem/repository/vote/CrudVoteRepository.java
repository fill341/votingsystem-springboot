package com.shaakem.votingsystem.repository.vote;

import com.shaakem.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.localDate DESC")
    List<Vote> getAll(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.localDate=:today")
    Vote getPerToday(@Param("userId") int userId, @Param("today") LocalDate today);

    @Query("FROM Vote v WHERE v.localDate=:today")
    List<Vote> getAllPerToday(@Param("today") LocalDateTime today);
}
