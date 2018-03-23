package com.shaakem.votingsystem.repository.user;

import com.shaakem.votingsystem.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Override
    @Transactional
    User save(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<User> findById(Integer id);

    User getByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name=:name")
    User getByName(@Param("name") String name);

    @Override
    List<User> findAll(Sort sort);
}
