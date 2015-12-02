package com.memo.model;

import java.util.Date;

import com.memo.datamodel.Model;

public class CommentModel implements Model {

    /**
     *
     */
    private static final long serialVersionUID = 6016133759925550879L;

    private boolean marked;
    private Long commentId;
    private Long userId;
    private String comment;
    private String name;
    private Date created;
    private boolean approved;

    public CommentModel() {
        super();
    }

    public CommentModel(Builder builder) {
        this.commentId = builder.commentId;
        this.userId = builder.userId;
        this.comment = builder.comment;
        this.name = builder.name;
        this.created = builder.created == null
                       ? null
                       : new Date(builder.created.getTime());
        this.approved = builder.approved;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public String getComment() {
        return comment;
    }

    public Long getId() {
        return commentId;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getCreated() {
        return created;
    }

    public boolean isApproved() {
        return approved;
    }

    public boolean isUserAvb() {
        return userId != null && userId > 0;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long commentId;
        private Long userId;
        private String comment;
        private String name;
        private Date created;
        private boolean approved;

        public Builder commentId(Long commentId) {
            this.commentId = commentId;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder created(Date created) {
            this.created = created;
            return this;
        }

        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }

        public CommentModel build() {
            return new CommentModel(this);
        }
    }

}
