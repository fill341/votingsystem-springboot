package com.shaakem.votingsystem.service;

import com.shaakem.votingsystem.AuthorizedUser;
import com.shaakem.votingsystem.model.User;
import com.shaakem.votingsystem.repository.user.UserRepository;
import com.shaakem.votingsystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.shaakem.votingsystem.util.ValidationUtil.checkNotFound;
import static com.shaakem.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    public User getByName(String name)  throws NotFoundException{
        Assert.notNull(name, "name must not be null");
        return checkNotFound(userRepository.getByName(name), "name=" + name);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.getByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User " + name + " is not found");
        }
        return new AuthorizedUser(user);
    }
}