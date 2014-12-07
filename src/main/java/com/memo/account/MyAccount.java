package com.memo.account;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.auth.Identity;
import com.memo.beans.ViewMsgBean;
import com.memo.entity.User;
import com.memo.service.UserService;
import com.memo.utils.Component;
import com.memo.utils.Const;
import com.memo.utils.Util;

@ManagedBean(name = "myAccount")
@ViewScoped
public class MyAccount extends ViewMsgBean {

    /**
     *
     */
    private static final long serialVersionUID = 1832958031886695765L;

    private AccountData accountData;

    @ManagedProperty("#{userServiceImpl}")
    private UserService userService;

    @PostConstruct
    public void init() {
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            setError(true);
        }
    }

    protected void loadData() {
        Identity identity = (Identity) Component.getInstance(Identity.NAME);
        User user = identity.getUser();
        if (user == null) {
            setError(true);
            return;
        }
        accountData = new AccountData(user);
    }

    public void save() {
        if (Util.isEmpty(accountData.getFirstname())
                || Util.isEmpty(accountData.getLastname())
                || Util.isEmpty(accountData.getEmail())) {
            error("field_values_are_not_correct");
        }
        boolean exists = userService.check(accountData.getUsername(),
                accountData.getEmail(),
                accountData.getUserId());
        if (exists) {
            error("user_exist_msg");
            return;
        }
        try {
            User user = userService.find(accountData.getUserId());
            user.updateData(accountData);
            user = userService.update(user);
            Identity identity = (Identity) Component.getInstance(Identity.NAME);
            identity.setUser(user);
            success();
        } catch (Exception e) {
            e.printStackTrace();
            error(Const.BAD_THING);
        }
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}