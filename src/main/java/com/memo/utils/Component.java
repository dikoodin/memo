package com.memo.utils;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Component {

    private static Logger log = LoggerFactory.getLogger(Component.class);

    public static Object getInstance(String name) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return FacesContext.getCurrentInstance().getApplication()
                .evaluateExpressionGet(facesContext, "#{" + name + "}",
                        Object.class);
    }

    public static Long getParamLong(String name) {
        String param = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get(name);
        try {
            return Long.valueOf(param);
        } catch (NumberFormatException e) {
            log.error("Can not create Long from param: {}", param);
        }
        return null;
    }

}
