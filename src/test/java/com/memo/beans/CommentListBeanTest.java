package com.memo.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.memo.datamodel.DataModel;
import com.memo.datamodel.DataProvider;
import com.memo.entity.Comment;
import com.memo.entity.CommentEntity;
import com.memo.entity.UserEntity;
import com.memo.mock.MockFacesContext;
import com.memo.mock.MockMessageIntegrpolator;
import com.memo.model.data.CommentModel;
import com.memo.service.CommentService;
import com.memo.session.Identity;

public class CommentListBeanTest {

    CommentListBean bean = new CommentListBean();

    CommentService commentService;

    @SuppressWarnings("unchecked")
    @BeforeMethod
    public void setUp() {
        ExternalContext externalContext = Mockito.mock(ExternalContext.class);
        Application application = Mockito.mock(Application.class);
        Identity identity = Mockito.mock(Identity.class);
        Mockito.when(identity.getUser()).thenReturn(new UserEntity());
        Mockito.when(application.evaluateExpressionGet(Matchers.any(FacesContext.class),
                Matchers.anyString(), Matchers.any(Class.class))).thenReturn(identity);
        MockFacesContext.mockFacesContext(externalContext, application);
        MockMessageIntegrpolator.mock();
        commentService = Mockito.mock(CommentService.class);
        bean.commentService = commentService;
    }

    @Test
    public void whenApproveThenCallUpdate() {
        //GIVEN
        Long commentId = 1L;
        boolean approved = false;
        Comment comment = new CommentEntity();
        comment.setApproved(approved);
        Mockito.when(commentService.find(Matchers.anyLong())).thenReturn(comment);

        // WHEN
        bean.approve(commentId);

        // THEN
        Assert.assertNotEquals(comment.isApproved(), approved);
        Mockito.verify(commentService, Mockito.times(1)).update(Matchers.any(Comment.class));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void whenCommentsExistsThenNonEmptyCommentList() {
        //GIVEN
        DataProvider dataProvider = Mockito.mock(DataProvider.class);
        List<CommentModel> list = Arrays.asList(new CommentModel());
        Mockito.when(dataProvider.getItemsByRange(Matchers.any(DataModel.class),
                Matchers.anyInt(), Matchers.anyInt(), Matchers.anyVararg())).thenReturn(list);
        Mockito.when(dataProvider.getRowCount()).thenReturn(1L);
        bean.dataProvider = dataProvider;

        // WHEN
        bean.init();

        // THEN
        Assert.assertNotNull(bean.getCommentList());
        Assert.assertTrue(bean.getCommentList().getRowCount() > 0);
    }

}
