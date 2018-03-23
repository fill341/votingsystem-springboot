package com.shaakem.votingsystem.config;

import com.shaakem.votingsystem.model.User;
import com.shaakem.votingsystem.repository.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

class JwtUtils {

    private static final long EXPIRATION_TIME = 864000000L;

    private static final String SECRET = "ThisIsASecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    static void addAuthentication(HttpServletResponse res, String username) {

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    static Authentication getAuthentication(HttpServletRequest request, UserRepository userRepository) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            String userName = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            User user = userRepository.getByName(userName);

            return user != null ?
                    new UsernamePasswordAuthenticationToken(userName, null,new ArrayList<GrantedAuthority>(user.getRoles())) :
                    null;
        }

        return null;
    }
}
