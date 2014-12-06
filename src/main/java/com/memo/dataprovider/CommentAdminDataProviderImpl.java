package com.memo.dataprovider;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.memo.datamodel.DataModel;
import com.memo.datamodel.DataProvider;
import com.memo.entity.Comment;
import com.memo.model.CommentModel;

@Service
public class CommentAdminDataProviderImpl extends AbstractCommentDataProvider
        implements DataProvider<CommentModel> {

    @Override
    public Long getRowCount() {
        return commentDAO.getAdminCommentCount();
    }

    @Override
    public List<CommentModel> getItemsByRange(DataModel<CommentModel> dataModel,
            int first, int numberOfRows, Object... objects) {
        List<Comment> comments = commentDAO.getAdminComments(first, numberOfRows);
        List<CommentModel> list = new LinkedList<CommentModel>();
        for (Comment c : comments) {
            CommentModel model = create(c);
            dataModel.wrapp(String.valueOf(c.getId()), model);
            list.add(model);
        }
        return list;
    }

}
