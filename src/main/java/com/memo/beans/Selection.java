package com.memo.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "selection")
public class Selection implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3552231431085529259L;

    private long itemId;
    private long dataId;

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

}
