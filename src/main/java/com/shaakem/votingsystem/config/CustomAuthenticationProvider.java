package com.shaakem.votingsystem.config;

import com.shaakem.votingsystem.model.User;
import com.shaakem.votingsystem.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

public class CustomAuthenticationProvider implements AuthenticationManager {

    private UserService userService;

    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken result = null;

        User user = userService.getByName(authentication.getName());

        if (user != null && user.getPassword().equals(authentication.getCredentials().toString())) {
            result = new UsernamePasswordAuthenticationToken(
                    user.getName(), null, new ArrayList<GrantedAuthority>(user.getRoles()));
        }

        return result;
    }
}
