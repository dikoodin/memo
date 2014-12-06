package com.memo.service;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.memo.dao.CommentDAO;
import com.memo.entity.Comment;
import com.memo.entity.CommentEntity;
import com.memo.entity.User;
import com.memo.entity.UserEntity;
import com.memo.service.CommentServiceImpl;

public class CommentServiceImplTest {

    CommentServiceImpl service = Mockito.spy(new CommentServiceImpl());

    CommentDAO commentDAO;

    @BeforeMethod
    public void setUp() {
        commentDAO = Mockito.mock(CommentDAO.class);
        service.setCommentDAO(commentDAO);
    }

    @Test
    public void whenCommentExistsThenFindShouldReturnComment() {
        // GIVEN
        Long commentId = 1L;
        Comment entity = new CommentEntity();
        entity.setId(commentId);
        Mockito.when(commentDAO.find(Matchers.anyLong(),
                Matchers.any(Class.class))).thenReturn(entity);

        // WHEN
        Comment result = service.find(commentId);

        // THEN
        Assert.assertNotNull(result);
    }

    @Test
    public void whenCommentExistsThenGetLastShouldReturnComment() {
        // GIVEN
        Comment entity = new CommentEntity();
        entity.setId(1L);
        Mockito.when(commentDAO.getLast()).thenReturn(entity);

        // WHEN
        Comment result = service.getLast();

        // THEN
        Assert.assertNotNull(result);
    }

    @Test
    public void whenSendCommentWithLoggedInUserThenVerifySetUserId() {
        // GIVEN
        String name = "a";
        String comment = "b";
        Comment entity = Mockito.mock(Comment.class);
        User user = new UserEntity();
        Mockito.doReturn(entity).when(service).create(Matchers.anyString(),
                Matchers.anyString());

        // WHEN
        service.sendComment(name, comment, user);

        // THEN
        Mockito.verify(entity, Mockito.times(1)).setUserId(Matchers.anyLong());
        Mockito.verify(commentDAO, Mockito.times(1)).insert(Matchers.any(Comment.class));
    }

    @Test
    public void whenSendCommentWithNotLoggedInUserThenVerifyNotCalledSetUserId() {
        // GIVEN
        String name = "a";
        String comment = "b";
        Comment entity = Mockito.mock(Comment.class);
        Mockito.doReturn(entity).when(service).create(Matchers.anyString(),
                Matchers.anyString());

        // WHEN
        service.sendComment(name, comment, null);

        // THEN
        Mockito.verify(entity, Mockito.never()).setUserId(Matchers.anyLong());
        Mockito.verify(commentDAO, Mockito.times(1)).insert(Matchers.any(Comment.class));
    }

    @Test
    public void whenCreateCommentThenReturnNotNull() {
        // GIVEN
        String name = "a";
        String comment = "b";

        // WHEN
        Comment entity = service.create(name, comment);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getName(), name);
        Assert.assertEquals(entity.getComment(), comment);
    }

}
