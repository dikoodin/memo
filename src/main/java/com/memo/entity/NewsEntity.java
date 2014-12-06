package com.memo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.memo.base.VersionEntity;

@Entity
@Table(name = "news")
public class NewsEntity extends VersionEntity implements News {

    private Date start;
    private Date end;
    private boolean active;
    private String name = "";
    private String desc = "";

    public NewsEntity() {
        super();
    }

    public NewsEntity(Date start, Date end, boolean active) {
        this.start = start;
        this.end = end;
        this.active = active;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Column(name = "active_")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
