package com.memo.datamodel;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.memo.auth.Identity;
import com.memo.entity.UserEntity;
import com.memo.mock.MockFacesContext;
import com.memo.mock.MockMessageIntegrpolator;
import com.memo.model.CommentModel;

public class KDataModelTest {

    KDataModel dataModel = new KDataModel();

    DataProvider dataProvider;

    @SuppressWarnings("unchecked")
    @BeforeMethod
    public void setUp() {
        dataProvider = Mockito.mock(DataProvider.class);
        ExternalContext externalContext = Mockito.mock(ExternalContext.class);
        Application application = Mockito.mock(Application.class);
        Identity identity = Mockito.mock(Identity.class);
        Mockito.when(identity.getUser()).thenReturn(new UserEntity());
        Mockito.when(application.evaluateExpressionGet(Matchers.any(FacesContext.class),
                Matchers.anyString(), Matchers.any(Class.class))).thenReturn(identity);
        MockFacesContext.mockFacesContext(externalContext, application);
        MockMessageIntegrpolator.mock();
        dataModel.setDataProvider(dataProvider);
    }

    @Test
    public void whenEntityExistsThenLoadShouldReturnNonEmptyList() {
        //GIVEN
        List<CommentModel> list = Arrays.asList(new CommentModel());
        Mockito.when(dataProvider.getItemsByRange(Matchers.any(DataModel.class),
                Matchers.anyInt(), Matchers.anyInt(), Matchers.anyVararg())).thenReturn(list);

        // WHEN
        List result = dataModel.load(1, 1, null, null, null);

        // THEN
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void whenRemoveModelThenRemoveFromWrappedDataAndKeys() {
        //GIVEN
        CommentModel model = CommentModel.builder().commentId(1L).build();
        dataModel.wrapp(model.getId().toString(), model);

        // WHEN
        dataModel.remove(model);

        // THEN
        Assert.assertEquals(dataModel.getRowCount(), 0);
        Assert.assertEquals(dataModel.getSize(), 0);
    }

    @Test
    public void whenWrappThenWrappedDataAndKeysNotBeEmpty() {
        //GIVEN
        CommentModel model = CommentModel.builder().commentId(1L).build();

        // WHEN
        dataModel.wrapp(model.getId().toString(), model);

        // THEN
        Assert.assertEquals(dataModel.getSize(), 1);
        Assert.assertEquals(dataModel.getWrappedKeys().size(), 1);
    }

    @Test
    public void whenRowCountNullThenCallDataProvider() {
        //GIVEN
        KDataModel<Model> dataModel = new KDataModel<Model>();
        dataModel.setDataProvider(dataProvider);

        // WHEN
        dataModel.getRowCount();

        // THEN
        Mockito.verify(dataProvider, Mockito.times(1)).getRowCount();
    }

}
