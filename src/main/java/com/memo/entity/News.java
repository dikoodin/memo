package com.memo.entity;

import java.util.Date;

import com.memo.base.VersionBase;

public interface News extends VersionBase {

    public Date getStart();

    public void setStart(Date start);

    public Date getEnd();

    public void setEnd(Date end);

    public boolean isActive();

    public void setActive(boolean active);

    public String getName();

    public void setName(String name);

    public String getDesc();

    public void setDesc(String desc);

}