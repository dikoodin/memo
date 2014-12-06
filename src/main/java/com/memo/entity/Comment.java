package com.memo.entity;

import com.memo.base.Base;

public interface Comment extends Base {

    public Long getUserId();

    public void setUserId(Long userId);

    public boolean isApproved();

    public void setApproved(boolean approved);

    public String getName();

    public void setName(String name);

    public String getComment();

    public void setComment(String comment);

    public Long getApprovedBy();

    public void setApprovedBy(Long approvedBy);

}