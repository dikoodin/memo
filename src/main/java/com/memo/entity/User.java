package com.memo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.memo.account.AccountData;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "email" }),
        @UniqueConstraint(columnNames = { "username" }) })
public class User extends VersionEntity {

    /**
     *
     */
    private static final long serialVersionUID = -7793142160309960461L;

    private String username;
    private String password;
    private String email;

    private String firstname;
    private String lastname;
    private String address;

    private String tel;
    private String mob;

    private Date birthday;

    private boolean admin;
    private boolean acceptedTerms;

    public User() {
        super();
    }

    public User(String username, String password, String email, String firstname,
            String lastname, String address, String tel, String mob, Date birthday) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.tel = tel;
        this.mob = mob;
        this.birthday = birthday;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "firstname", nullable = false)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "lastname", nullable = false)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "mob")
    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "admin")
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Column(name = "accepted_terms", nullable = false)
    public boolean isAcceptedTerms() {
        return acceptedTerms;
    }

    public void setAcceptedTerms(boolean acceptedTerms) {
        this.acceptedTerms = acceptedTerms;
    }

    @Transient
    public String getFullName() {
        return firstname + " " + lastname;
    }

    @Transient
    public void updateData(AccountData data) {
        this.firstname = data.getFirstname();
        this.lastname = data.getLastname();
        this.address = data.getAddress();
        this.tel = data.getTel();
        this.mob = data.getMob();
        this.birthday = data.getBirthday() == null ? null : new Date(data.getBirthday().getTime());
    }

}