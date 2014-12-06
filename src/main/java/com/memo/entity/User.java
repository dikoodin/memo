package com.memo.entity;

import java.util.Date;

import com.memo.account.AccountData;
import com.memo.base.VersionBase;

public interface User extends VersionBase {

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public String getEmail();

    public void setEmail(String email);

    public String getFirstname();

    public void setFirstname(String firstname);

    public String getLastname();

    public void setLastname(String lastname);

    public String getAddress();

    public void setAddress(String address);

    public String getTel();

    public void setTel(String tel);

    public String getMob();

    public void setMob(String mob);

    public Date getBirthday();

    public void setBirthday(Date birthday);

    public boolean isAdmin();

    public void setAdmin(boolean admin);

    public boolean isAcceptedTerms();

    public void setAcceptedTerms(boolean acceptedTerms);

    public void updateData(AccountData data);

    public String getFullName();

}