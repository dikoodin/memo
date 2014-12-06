package com.memo.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.datatable.DataTable;

import com.memo.auth.Identity;
import com.memo.datamodel.DataProvider;
import com.memo.datamodel.KDataModel;
import com.memo.entity.Comment;
import com.memo.entity.User;
import com.memo.model.CommentModel;
import com.memo.service.CommentService;
import com.memo.utils.Component;

@ViewScoped
@ManagedBean(name = "commentListBean")
public class CommentListBean extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3111988099407754250L;

    public static final String TABEL_ID = "ut_id";

    private KDataModel<CommentModel> commentList;

    @ManagedProperty("#{commentAdminDataProviderImpl}")
    protected DataProvider<CommentModel> dataProvider;

    @ManagedProperty("#{commentServiceImpl}")
    protected CommentService commentService;

    @PostConstruct
    public void init() {
        try {
            loadComments();
        } catch (Exception e) {
            e.printStackTrace();
            super.setError(true);
        }
    }

    private void loadComments() {
        commentList = new KDataModel<CommentModel>(dataProvider, 50, TABEL_ID,
                DataTable.class);
    }

    public KDataModel<CommentModel> getCommentList() {
        return commentList;
    }

    public void approve(Long commentId) {
        Identity identity = (Identity) Component.getInstance(Identity.NAME);
        User user = identity.getUser();
        Comment comm = commentService.find(commentId);
        comm.setApproved(!comm.isApproved());
        comm.setApprovedBy(user.getId());
        commentService.update(comm);
        success();
    }

    public void setDataProvider(DataProvider<CommentModel> dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

}
