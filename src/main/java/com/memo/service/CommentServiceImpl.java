package com.memo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memo.dao.CommentDAO;
import com.memo.entity.Comment;
import com.memo.entity.CommentEntity;
import com.memo.entity.User;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    protected CommentDAO commentDAO;

    @Transactional(readOnly = true)
    public Comment getLast() {
        return commentDAO.getLast();
    }

    @Transactional
    public void sendComment(String name, String comment, User user) {
        Comment newcomment = create(name, comment);
        if (user != null) {
            newcomment.setUserId(user.getId());
        }
        commentDAO.insert(newcomment);
    }

    @Transactional(readOnly = true)
    public Comment find(Long commentId) {
        return commentDAO.find(commentId, CommentEntity.class);
    }

    @Transactional
    public Comment update(Comment comm) {
        return (Comment) commentDAO.update(comm);
    }

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    protected Comment create(String name, String comment) {
        return new CommentEntity(name, comment);
    }

}
