package com.memo.manager;

import java.io.Serializable;

public abstract class AbstractBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 862318493509410370L;

    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}
