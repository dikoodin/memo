package com.memo.datamodel;

import java.util.Map;
import java.util.Set;

public interface DataModel<E> {

    public String getDataTableId();

    public void setDataTableId(String dataTableId);

    public Map<String, E> getWrappedData();

    public void setWrappedData(Map<String, E> wrappedData);

    public Set<String> getWrappedKeys();

    public void setWrappedKeys(Set<String> wrappedKeys);

    public void wrapp(String key, E value);

}
