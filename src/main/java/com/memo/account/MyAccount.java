package com.memo.account;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.entity.User;
import com.memo.manager.ViewMsgBean;
import com.memo.service.UserService;
import com.memo.session.Identity;
import com.memo.utils.Component;
import com.memo.utils.Const;
import com.memo.utils.Util;

@ManagedBean(name = "myAccount")
@ViewScoped
public class MyAccount extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5307825474425830753L;

    private AccountData accountData;
    private User user;

    @ManagedProperty("#{userServiceImpl}")
    protected UserService userService;

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
        user = identity.getUser();
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
                user.getId());
        if (exists) {
            error("user_exist_msg");
            return;
        }
        user.updateData(accountData);
        try {
            user = userService.update(user);
            success();
        } catch (Exception e) {
            e.printStackTrace();
            error(Const.BAD_THING);
        }
        Identity identity = (Identity) Component.getInstance(Identity.NAME);
        identity.init(user);
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}