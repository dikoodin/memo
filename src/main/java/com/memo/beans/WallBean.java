package com.memo.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.jsoup.Jsoup;
import org.primefaces.component.datatable.DataTable;

import com.memo.datamodel.DataProvider;
import com.memo.datamodel.KDataModel;
import com.memo.entity.User;
import com.memo.manager.ViewMsgBean;
import com.memo.model.data.CommentModel;
import com.memo.service.CommentService;
import com.memo.session.Identity;
import com.memo.utils.Component;
import com.memo.utils.Const;
import com.memo.utils.Util;

@ManagedBean(name = "wallBean")
@ViewScoped
public class WallBean extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3618231760842465263L;

    public static final String K_NAME = "kWall";
    public static final String TABEL_ID = "comments_";

    @ManagedProperty("#{commentDataProviderImpl}")
    private DataProvider<CommentModel> commentDataProvider;

    @ManagedProperty("#{commentServiceImpl}")
    private CommentService commentService;

    private KDataModel<CommentModel> commentList;
    private String comment;
    private String name;

    @PostConstruct
    public void init() {
        loadComments();
    }

    public void loadComments() {
        commentList = new KDataModel<CommentModel>(commentDataProvider, 10,
                TABEL_ID, DataTable.class);
    }

    private void resetComment() {
        this.comment = null;
        this.name = null;
    }

    public void addComment() {
        Identity identity = (Identity) Component.getInstance(Identity.NAME);
        User user = identity.getUser();

        String cleanComment = Jsoup.parse(comment).text();
        String cleanName = Jsoup.parse(name).text();
        if (Util.isEmpty(cleanComment)
                || (user == null && Util.isEmpty(cleanName))) {
            error(Const.BAD_THING);
            return;
        }
        commentService.sendComment(cleanName, cleanComment, user);
        success();
        resetComment();
        loadComments();
    }

    public KDataModel<CommentModel> getCommentList() {
        return commentList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommentDataProvider(DataProvider<CommentModel> commentDataProvider) {
        this.commentDataProvider = commentDataProvider;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

}
