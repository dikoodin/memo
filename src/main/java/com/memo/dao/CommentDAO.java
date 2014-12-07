package com.memo.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.memo.entity.Comment;

@Repository
public class CommentDAO extends DAO {

    public Comment getLast() {
        try {
            return (Comment) getEntityManager().createQuery("SELECT e FROM Comment e ORDER BY e.id DESC")
                    .setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            // Entity not found.
            return null;
        }
    }

    public Comment getById(long id) {
        try {
            return (Comment) getEntityManager().createQuery("SELECT e FROM Comment e WHERE e.id :id")
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Entity not found.
            return null;
        }
    }

    public Long getApprovedCommentsCount() {
        return (Long) getEntityManager()
                .createQuery("SELECT COUNT(e.id) FROM Comment e WHERE e.id > 0 AND e.approved = true")
                .setMaxResults(1)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getApprovedComments(int first, int numberOfRows) {
        return getEntityManager()
                .createQuery("SELECT e FROM Comment e WHERE e.id > 0 AND e.approved = true ORDER BY e.id DESC")
                .setFirstResult(first).setMaxResults(numberOfRows)
                .getResultList();
    }

    public Long getAdminCommentCount() {
        return (Long) getEntityManager()
                .createQuery("SELECT COUNT(e.id) FROM Comment e WHERE e.id > 0 ")
                .setMaxResults(1)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getAdminComments(int first, int numberOfRows) {
        return getEntityManager()
                .createQuery("SELECT e FROM Comment e WHERE e.id > 0 ORDER BY e.approved, e.id DESC")
                .setFirstResult(first).setMaxResults(numberOfRows)
                .getResultList();
    }

}
