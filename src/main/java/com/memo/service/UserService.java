package com.memo.service;

import java.util.List;

import com.memo.entity.User;
import com.memo.entity.UserEntity;

public interface UserService {

    public User find(long userId);

    public User update(User user);

    public User findByUsername(String username);

    public boolean check(String username, String email, long userId);

    public User findByEmail(String email);

    public List<UserEntity> getUsers(int page);

}