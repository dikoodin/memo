package com.memo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memo.dao.UserDAO;
import com.memo.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public User find(long userId) {
        return userDAO.find(userId);
    }

    @Transactional
    public User update(User user) {
        return (User) userDAO.update(user);
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public boolean check(String username, String email, long userId) {
        return userDAO.check(username, email, userId);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public List<User> getUsers(int page) {
        return userDAO.getUsers(page);
    }

}
