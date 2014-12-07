package com.memo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 172884317688281353L;

    protected Long id;
    protected Date created;
    protected Date modified;
    protected boolean checked;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    public Date getCreated() {
        return created == null ? null : new Date(created.getTime());
    }

    public void setCreated(Date created) {
        this.created = created == null ? null : new Date(created.getTime());
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified")
    public Date getModified() {
        return modified == null ? null : new Date(modified.getTime());
    }

    public void setModified(Date modified) {
        this.modified = modified == null ? null : new Date(modified.getTime());
    }

    @Transient
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

}
