package com.memo.dataprovider;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.dao.UserDAO;
import com.memo.datamodel.DataModel;
import com.memo.datamodel.DataProvider;
import com.memo.entity.User;
import com.memo.model.UserModel;

@Service
public class UserModelDataProviderImpl implements
        DataProvider<UserModel> {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Long getRowCount() {
        return userDAO.getUserCount();
    }

    @Override
    public List<UserModel> getItemsByRange(DataModel<UserModel> dataModel,
            int first, int numberOfRows, Object... objects) {
        List<User> records = userDAO.getUsersByRange(first, numberOfRows);
        List<UserModel> list = new LinkedList<UserModel>();
        for (User e : records) {
            UserModel model = userDAO.convert(e);
            dataModel.wrapp(String.valueOf(e.getId()), model);
            list.add(model);
        }
        return list;
    }

}
