package com.shaakem.votingsystem.service;


import com.shaakem.votingsystem.model.User;
import com.shaakem.votingsystem.util.exception.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void enable(int id, boolean enable);
}