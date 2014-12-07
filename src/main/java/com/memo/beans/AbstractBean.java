package com.memo.beans;

import java.io.Serializable;

public abstract class AbstractBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1848529284790603330L;

    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}
