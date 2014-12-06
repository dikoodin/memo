package com.memo.service;

import com.memo.entity.Comment;
import com.memo.entity.User;

public interface CommentService {

    public Comment getLast();

    public void sendComment(String name, String comment, User user);

    public Comment find(Long commentId);

    public Comment update(Comment comm);

}