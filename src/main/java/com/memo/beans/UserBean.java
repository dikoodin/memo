package com.memo.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.entity.User;
import com.memo.service.UserService;
import com.memo.utils.Component;
import com.memo.utils.Const;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4961646080546501375L;

    private User user;

    @ManagedProperty("#{userServiceImpl}")
    private UserService userService;

    @PostConstruct
    public void init() {
        Long userId = Component.getParamLong("userId");
        if (userId == null || userId <= 0) {
            error(Const.BAD_THING);
            return;
        }
        user = userService.find(userId);
    }

    public User getUser() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}