package com.shaakem.votingsystem.repository.user;

import com.shaakem.votingsystem.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    void update(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    User getByName(String name);

    List<User> getAll();
}
