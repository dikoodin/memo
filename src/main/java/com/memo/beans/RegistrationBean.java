package com.memo.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.entity.User;
import com.memo.entity.UserEntity;
import com.memo.service.UserService;
import com.memo.utils.Util;

@ManagedBean(name = "registrationBean")
@ViewScoped
public class RegistrationBean extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5307825474425830753L;

    @ManagedProperty("#{userServiceImpl}")
    private UserService userService;

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    private String firstname;
    private String lastname;

    private boolean acceptTerms = true;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public String save() {
        if (!acceptTerms
                || Util.isEmpty(password)
                || Util.isEmpty(confirmPassword)
                || !password.equals(confirmPassword)
                || Util.isEmpty(firstname)
                || Util.isEmpty(lastname)
                || Util.isEmpty(email)) {
            error("field_values_are_not_correct");
            return null;
        }
        boolean exists = userService.check(username, email, 0);
        if (exists) {
            message("user_exist_msg", FacesMessage.SEVERITY_ERROR);
            return null;
        }
        String criptedPassword = Util.cript(password);
        User user = new UserEntity(username, criptedPassword, email,
                firstname, lastname, null, null, null, null);
        user.setAcceptedTerms(true);
        user = userService.update(user);
        success();
        return "login";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
