package com.memo.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "selection")
public class Selection {

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
