package com.memo.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.memo.dao.UserDAO;
import com.memo.entity.User;
import com.memo.utils.Const;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private HashMap<String, org.springframework.security.core.userdetails.User> users =
            new HashMap<String, org.springframework.security.core.userdetails.User>();

    @Autowired
    private UserDAO userDAO;

    @SuppressWarnings("deprecation")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User kuser = userDAO.findByUsername(username);

        if (kuser == null) {
            throw new UsernameNotFoundException("UserAccount for name \"" + username
                    + "\" not found.");
        }
        Collection<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
        adminAuthorities.add(new GrantedAuthorityImpl(Const.ROLE_USER));
        if (kuser.isAdmin()) {
            adminAuthorities.add(new GrantedAuthorityImpl(Const.ROLE_ADMIN));
        }
        org.springframework.security.core.userdetails.User user =
                new org.springframework.security.core.userdetails.User(username,
                        kuser.getPassword(), adminAuthorities);

        return user;
    }

    @SuppressWarnings("deprecation")
    @PostConstruct
    public void init() {

        // sample roles
        Collection<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
        adminAuthorities.add(new GrantedAuthorityImpl(Const.ROLE_ADMIN));

        Collection<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
        userAuthorities.add(new GrantedAuthorityImpl(Const.ROLE_USER));

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        // sample users with roles set
        users.put(Const.ADMIN,
                new org.springframework.security.core.userdetails.User(
                        Const.ADMIN, Const.ADMIN, enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        adminAuthorities));

        users.put(Const.USER,
                new org.springframework.security.core.userdetails.User(
                        Const.USER, Const.USER, enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        userAuthorities));
    }

}
