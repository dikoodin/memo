package com.memo.utils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean(name = "initParam", eager = true)
public class InitParams {

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
