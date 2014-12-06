package com.memo.datamodel;

import java.util.List;

public interface DataProvider<E> {

    public List<E> getItemsByRange(DataModel<E> dataModel, int first,
            int numberOfRows, Object... objects);

    public Long getRowCount();

}
