package com.memo.datamodel;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.memo.utils.JSFUtils;

public class KDataModel<E extends Model> extends LazyDataModel<E> implements
        DataModel<E> {

    /**
     *
     */
    private static final long serialVersionUID = -6929477453431395795L;

    private String dataTableId;
    private DataProvider<E> dataProvider;
    private Map<String, E> wrappedData = new HashMap<String, E>();
    private Set<String> wrappedKeys = new HashSet<String>();
    private Integer rowCount;
    private Class<?> clazz;

    public KDataModel() {
        super();
    }

    public KDataModel(DataProvider<E> dataProvider, int rows, String dataTableId,
            Class<?> clazz) {
        this.dataProvider = dataProvider;
        this.dataTableId = dataTableId;
        this.clazz = clazz;
        JSFUtils.resetDataTable(this.clazz, dataTableId, 0);
        setPageSize(rows);
        setRowCount(getRowCount());
    }

    @Override
    public List<E> load(int first, int max, String sortField,
            SortOrder sortOrder, Map<String, String> filters) {
        return dataProvider.getItemsByRange(this, first, max, sortField,
                sortOrder, filters);
    }

    public Collection<E> getCollection() {
        return wrappedData.values();
    }

    public int getSize() {
        return wrappedData.values().size();
    }

    @Override
    public int getRowCount() {
        if (rowCount == null) {
            rowCount = dataProvider.getRowCount().intValue();
        }
        return rowCount;
    }

    public void remove(E model) {
        wrappedData.remove(model.getId().toString());
        wrappedKeys.remove(model.getId().toString());
    }

    public String getDataTableId() {
        return dataTableId;
    }

    public void setDataTableId(String dataTableId) {
        this.dataTableId = dataTableId;
    }

    public Map<String, E> getWrappedData() {
        return wrappedData;
    }

    public void setWrappedData(Map<String, E> wrappedData) {
        this.wrappedData = wrappedData;
    }

    public Set<String> getWrappedKeys() {
        return wrappedKeys;
    }

    public void setWrappedKeys(Set<String> wrappedKeys) {
        this.wrappedKeys = wrappedKeys;
    }

    public void wrapp(String key, E value) {
        wrappedData.put(key, value);
        wrappedKeys.add(key);
    }

    public void setDataProvider(DataProvider<E> dataProvider) {
        this.dataProvider = dataProvider;
    }

}
