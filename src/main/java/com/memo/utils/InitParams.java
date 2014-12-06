package com.memo.utils;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean(name = "initParam", eager = true)
public class InitParams implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2279318520328599763L;

    @PostConstruct
    public void init() {
        // Get init parameters from web.xml
        // ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance()
        //         .getExternalContext().getContext();
        System.out.println("==================================");
        System.out.println("=====       INIT PARAMS      =====");
        System.out.println("==================================");
    }

}
