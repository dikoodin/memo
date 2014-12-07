package com.memo.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.entity.User;
import com.memo.service.UserService;
import com.memo.utils.Component;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean extends ViewMsgBean {

    /**
     *
     */
    private static final long serialVersionUID = -2487531296968351343L;

    private User user;

    @ManagedProperty("#{userServiceImpl}")
    private UserService userService;

    @PostConstruct
    public void init() {
        Long userId = Component.getParamLong("userId");
        if (userId != null && userId > 0) {
            user = userService.find(userId);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}