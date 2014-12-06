package com.memo.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;

import com.memo.dao.CommentDAO;
import com.memo.dao.UserDAO;
import com.memo.entity.Comment;
import com.memo.entity.User;
import com.memo.model.CommentModel;

public abstract class AbstractCommentDataProvider {

    @Autowired
    protected CommentDAO commentDAO;

    @Autowired
    protected UserDAO userDAO;

    public CommentModel create(Comment comm) {
        Long userId = null;
        if (comm.getUserId() != null) {
            User user = userDAO.find(comm.getUserId());
            userId = user.getId();
        }
        CommentModel model = CommentModel.builder()
                .commentId(comm.getId())
                .comment(comm.getComment())
                .created(comm.getCreated())
                .approved(comm.isApproved())
                .name(comm.getName())
                .userId(userId).build();
        return model;
    }

}
