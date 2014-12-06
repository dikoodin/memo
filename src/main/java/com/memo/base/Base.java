package com.memo.base;

import java.util.Date;

public interface Base {

    public Long getId();

    public void setId(Long id);

    public Date getCreated();

    public void setCreated(Date created);

    public Date getModified();

    public void setModified(Date modified);

    public boolean isChecked();

    public void setChecked(boolean checked);

    public int hashCode();

}