package com.memo.auth;

import javax.annotation.security.RolesAllowed;

import com.memo.utils.Const;

public interface AuthenticationService {

    boolean login(String username, String password);

    @RolesAllowed({ Const.ROLE_ADMIN, Const.ROLE_USER })
    void logout();
}
