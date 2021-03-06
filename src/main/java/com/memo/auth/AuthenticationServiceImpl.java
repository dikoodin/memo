package com.memo.auth;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.memo.utils.Util;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager; // specific for Spring Security

    @Override
    public boolean login(String username, String password) {
        try {
            if (Util.isEmpty(username) || Util.isEmpty(password)) {
                return false;
            }
            String criptedPsw = Util.cript(password);
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, criptedPsw));
            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                return true;
            }
        } catch (AuthenticationException e) {
            log.error("AuthenticationException with message " + e.getMessage());
        }
        return false;
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
    }

}
