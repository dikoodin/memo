package com.memo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 8151958106131080310L;

    private Long userId;

    private String name;
    private boolean approved;
    private String comment;
    private Long approvedBy;

    public Comment() {
        super();
    }

    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public Comment(Long userId, String name, String comment) {
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
