package com.memo.auth;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.memo.entity.User;
import com.memo.utils.Const;
import com.memo.utils.Util;

@ManagedBean(name = Identity.NAME, eager = true)
@SessionScoped
public class Identity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7011679411342956037L;

    private static final Logger LOG = LoggerFactory.getLogger(Identity.class);
    public static final String NAME = "identity";

    private User user;
    private String avatar;

    private Set<String> roles = new HashSet<String>();

    @PostConstruct
    public void init() {
        LOG.info("=== Init identity ===");
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void init(User user, String... roleNames) {
        this.user = user;
        for (String role : roleNames) {
            if (!Util.isEmpty(role)) {
                roles.add(role);
            }
        }
    }

    public boolean hasRole(String role) {
        if (Util.isEmpty(role)) {
            return false;
        }
        return roles.contains(role);
    }

    public boolean isLoggedIn() {
        return user != null && roles.contains(Const.ROLE_USER);
    }

    public User getUser() {
        return user;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isAdmin() {
        return user != null && user.isAdmin();
    }

    public void logout() {
        user = null;
        roles.clear();
    }

    public Set<String> getRoles() {
        return roles;
    }

}