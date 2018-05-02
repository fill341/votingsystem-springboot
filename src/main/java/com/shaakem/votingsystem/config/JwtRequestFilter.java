package com.shaakem.votingsystem.config;

import com.shaakem.votingsystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtRequestFilter extends GenericFilterBean {

    private UserService userService;

    public JwtRequestFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        Authentication authentication = JwtUtils
                .getAuthentication((HttpServletRequest)request, userService);

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        filterChain.doFilter(request,response);
    }
}
