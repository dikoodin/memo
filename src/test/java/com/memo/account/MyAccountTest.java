package com.memo.account;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.memo.entity.UserEntity;
import com.memo.mock.MockFacesContext;
import com.memo.session.Identity;

public class MyAccountTest {

    MyAccount myAccount = new MyAccount();

    @SuppressWarnings("unchecked")
    @BeforeMethod
    public void setUp() {
        ExternalContext externalContext = Mockito.mock(ExternalContext.class);
        Application application = Mockito.mock(Application.class);
        Identity identity = Mockito.mock(Identity.class);
        Mockito.when(identity.getUser()).thenReturn(new UserEntity());
        Mockito.when(application.evaluateExpressionGet(Matchers.any(FacesContext.class),
                Matchers.anyString(), Matchers.any(Class.class))).thenReturn(identity);
        MockFacesContext.mockFacesContext(externalContext, application);
    }

    @Test
    public void loadData() {
        // GIVEN
        // WHEN
        myAccount.loadData();

        //THEN
        Assert.assertNotNull(myAccount.getAccountData());
    }

}