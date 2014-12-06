package com.memo.mock;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mockito.Mockito;

public abstract class MockFacesContext extends FacesContext {

    public static FacesContext mockFacesContext(ExternalContext externalContext,
            Application application) {
        FacesContext context = Mockito.mock(FacesContext.class);
        setCurrentInstance(context);
        Mockito.when(context.getExternalContext()).thenReturn(externalContext);
        Mockito.when(context.getApplication()).thenReturn(application);
        return context;
    }

}
