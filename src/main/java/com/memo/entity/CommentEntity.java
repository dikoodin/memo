package com.memo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.memo.base.BaseEntity;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity implements Comment {

    private Long userId;

    private String name;
    private boolean approved;
    private String comment;
    private Long approvedBy;

    public CommentEntity() {
        super();
    }

    public CommentEntity(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public CommentEntity(Long userId, String name, String comment) {
        this(name, comment);
        this.userId = userId;
    }

    @Column(name = "user_id", updatable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "approved", nullable = false)
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "comment", nullable = false)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "approved_by")
    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

}
