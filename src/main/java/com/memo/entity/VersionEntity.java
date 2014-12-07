package com.memo.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class VersionEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 3402583274035014342L;

    private long version;

    public VersionEntity() {
        super();
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}