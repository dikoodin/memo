package com.memo.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.memo.auth.AuthenticationService;
import com.memo.auth.Identity;
import com.memo.entity.User;
import com.memo.service.UserService;
import com.memo.utils.Component;
import com.memo.utils.Const;
import com.memo.utils.Util;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2185101480769225932L;

    private String username;
    private String password;

    @ManagedProperty("#{authenticationServiceImpl}")
    private AuthenticationService authenticationService;

    @ManagedProperty("#{userServiceImpl}")
    private UserService userService;

    public String login() {
        if (Util.isEmpty(username) || Util.isEmpty(password)) {
            error("username_pwd_empty");
            return null;
        }

        User user = userService.findByUsername(username);
        boolean success = authenticationService.login(username, password);
        Identity identity = (Identity) Component.getInstance(Identity.NAME);
        if (success) {
            identity.init(user, (user.isAdmin() ? Const.ROLE_ADMIN : null),
                    Const.ROLE_USER);
            return "home.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Login or password incorrect."));
            return "login.xhtml";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        authenticationService.logout();
        return "home";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
