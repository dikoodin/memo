package com.memo.manager;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.memo.interpolator.MessageInterpolator;

public abstract class ViewMsgBean extends AbstractBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4461462161814441971L;

    public void success() {
        addMessage("success_msg", FacesMessage.SEVERITY_INFO);
    }

    public void message(String key, FacesMessage.Severity severity) {
        addMessage(key, severity);
    }

    public void message(String key, FacesMessage.Severity severity,
            String... params) {
        StringBuilder builder = new StringBuilder(MessageInterpolator
                .instance().interpolate(key));
        for (String param : params) {
            builder.append(" ");
            builder.append(param);
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severity, builder.toString(), null));
    }

    public void error(String key) {
        addMessage(key, FacesMessage.SEVERITY_ERROR);
    }

    private void addMessage(String key,
            javax.faces.application.FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severity, MessageInterpolator.instance()
                        .interpolate(key), null));
    }

}
