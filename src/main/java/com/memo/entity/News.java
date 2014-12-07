package com.memo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "news")
public class News extends VersionEntity {

    /**
     *
     */
    private static final long serialVersionUID = 3871402249959064921L;

    private Date start;
    private Date end;
    private boolean active;
    private String name = "";
    private String desc = "";

    public News() {
        super();
    }

    public News(Date start, Date end, boolean active) {
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
    @Column(name = "startdate")
    public Date getStart() {
        return start == null ? null : new Date(start.getTime());
    }

    public void setStart(Date start) {
        this.start = start == null ? null : new Date(start.getTime());;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enddate")
    public Date getEnd() {
        return end == null ? null : new Date(end.getTime());
    }

    public void setEnd(Date end) {
        this.end = end == null ? null : new Date(end.getTime());;
    }

    @Column(name = "active_")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
