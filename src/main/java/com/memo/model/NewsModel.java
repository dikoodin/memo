package com.memo.model;

import java.util.Date;

import com.memo.datamodel.Model;

public class NewsModel implements Model {

    /**
     *
     */
    private static final long serialVersionUID = 71761168197936334L;

    private Long id;
    private Date start;
    private Date end;
    private boolean active;
    private String shortDescription;
    private String name = "";
    private String desc = "";

    public NewsModel() {
        super();
    }

    public NewsModel(Builder builder) {
        this.id = builder.id;
        this.start = builder.start == null ? null : new Date(builder.start.getTime());
        this.end = builder.end == null ? null : new Date(builder.end.getTime());
        this.active = builder.active;
        this.shortDescription = builder.shortDescription;
        this.name = builder.name;
        this.desc = builder.desc;
    }

    public Long getId() {
        return id;
    }

    public Date getStart() {
        return start == null ? null : new Date(start.getTime());
    }

    public Date getEnd() {
        return end == null ? null : new Date(end.getTime());
    }

    public boolean isActive() {
        return active;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Date start;
        private Date end;
        private boolean active;
        private String shortDescription;
        private String name = "";
        private String desc = "";

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder start(Date start) {
            this.start = start == null ? null : new Date(start.getTime());;
            return this;
        }

        public Builder end(Date end) {
            this.end = end == null ? null : new Date(end.getTime());;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder shortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public NewsModel build() {
            return new NewsModel(this);
        }
    }

}
