package com.memo.utils;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.datalist.DataList;
import org.primefaces.component.datatable.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSFUtils {

    private static Logger log = LoggerFactory.getLogger(JSFUtils.class);

    public static UIComponent findComponentInRoot(String id) {
        UIComponent component = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (facesContext != null) {
            UIComponent root = facesContext.getViewRoot();
            if (root != null) {
                component = findComponent(root, id);
            }
        }

        return component;
    }

    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId())) {
            log.debug("id {} and base.id {} match each other", id, base.getId());
            return base;
        }
        UIComponent kid = null;
        UIComponent result = null;
        Iterator<UIComponent> kids = base.getFacetsAndChildren();

        while (kids.hasNext() && (result == null)) {
            kid = kids.next();
            if (id.equals(kid.getId())) {
                result = kid;
                break;
            }
            result = findComponent(kid, id);
            if (result != null) {
                break;
            }
        }

        return result;
    }

    @SuppressWarnings("rawtypes")
    public static void resetDataTable(Class clazz, String componentId, int first) {
        UIComponent component = findComponentInRoot(componentId);
        if (component == null) {
            log.warn("Can not find DataTable id {} ", componentId);
            return;
        }
        if (DataTable.class.equals(clazz)) {
            ((DataTable) component).setFirst(first);
        } else if (DataList.class.equals(clazz)) {
            ((DataList) component).setFirst(first);
        }
    }
}
