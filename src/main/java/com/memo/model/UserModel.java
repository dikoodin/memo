package com.memo.model;

import java.util.Date;

import com.memo.datamodel.Model;

public class UserModel implements Model {

    /**
     *
     */
    private static final long serialVersionUID = -3950065003202277757L;

    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String address;
    private String tel;
    private String mob;
    private Date birthday;

    public UserModel() {
        super();
    }

    public UserModel(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.address = builder.address;
        this.tel = builder.tel;
        this.mob = builder.mob;
        this.birthday = builder.birthday;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public String getMob() {
        return mob;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String username;
        private String email;
        private String firstname;
        private String lastname;
        private String address;
        private String tel;
        private String mob;
        private Date birthday;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder tel(String tel) {
            this.tel = tel;
            return this;
        }

        public Builder mob(String mob) {
            this.mob = mob;
            return this;
        }

        public Builder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserModel build() {
            return new UserModel(this);
        }
    }

}
