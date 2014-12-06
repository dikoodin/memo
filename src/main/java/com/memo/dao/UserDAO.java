package com.memo.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.memo.entity.User;
import com.memo.entity.UserEntity;
import com.memo.model.UserModel;
import com.memo.utils.Const;

@Repository
public class UserDAO extends DAO {

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    public UserEntity find(long userId) {
        try {
            return (UserEntity) getEntityManager()
                    .createQuery("SELECT u FROM UserEntity u WHERE u.id = :id")
                    .setParameter("id", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findByUsername(String username) {
        try {
            return (User) getEntityManager()
                    .createQuery("SELECT u FROM UserEntity u WHERE u.username = :username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean check(String username, String email, long userId) {
        StringBuffer buffer = new StringBuffer(
                "SELECT 1 FROM UserEntity u WHERE (u.username = :username or u.email=:email) ");
        if (userId > 0) {
            buffer.append(" AND u.id <> " + userId);
        }
        Query query = getEntityManager().createQuery(buffer.toString());
        query.setParameter("username", username);
        query.setParameter("email", email);
        boolean exist = false;
        try {
            Integer one = (Integer) query.getSingleResult();
            return isResultOne(one);
        } catch (NoResultException e) {
            LOG.debug("UserEntity with usernaem {} and email {} is not found!",
                    username, email);
        }
        return exist;
    }

    public User findByEmail(String email) {
        try {
            return (User) getEntityManager()
                    .createQuery("SELECT u FROM UserEntity u WHERE u.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getUsers(int page) {
        return getEntityManager().createQuery("SELECT u FROM UserEntity u ")
                .setFirstResult(page * Const.DEFAULT_RESULTS)
                .setMaxResults(Const.DEFAULT_RESULTS)
                .getResultList();
    }

    public Long getUserCount() {
        return (Long) getEntityManager()
                .createQuery("SELECT COUNT(e.id) FROM UserEntity e WHERE e.id > 0 ")
                .setMaxResults(1)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsersByRange(int first, int numberOfRows) {
        return getEntityManager()
                .createQuery("SELECT e FROM UserEntity e WHERE e.id > 0 ORDER BY e.id DESC")
                .setFirstResult(first).setMaxResults(numberOfRows)
                .getResultList();
    }

    public UserModel convert(User user) {
        UserModel model = UserModel.builder().id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .address(user.getAddress())
                .tel(user.getTel())
                .mob(user.getMob())
                .birthday(user.getBirthday())
                .build();
        return model;
    }
}
