package com.memo.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.datatable.DataTable;

import com.memo.datamodel.DataProvider;
import com.memo.datamodel.KDataModel;
import com.memo.model.UserModel;

@ViewScoped
@ManagedBean(name = "userListBean")
public class UserListBean extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -978038977440805560L;

    public static final String TABEL_ID = "ut_id";

    private KDataModel<UserModel> userList;

    @ManagedProperty("#{userModelDataProviderImpl}")
    private DataProvider<UserModel> userModelDataProvider;

    @PostConstruct
    public void init() {
        userList = new KDataModel<UserModel>(userModelDataProvider, 10,
                TABEL_ID, DataTable.class);
    }

    public KDataModel<UserModel> getUserList() {
        return userList;
    }

    public void setUserModelDataProvider(DataProvider<UserModel> userModelDataProvider) {
        this.userModelDataProvider = userModelDataProvider;
    }

}