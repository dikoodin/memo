package com.memo.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.dao.NewsDAO;
import com.memo.datamodel.DataModel;
import com.memo.datamodel.DataProvider;
import com.memo.entity.News;
import com.memo.model.data.NewsModel;
import com.memo.utils.Util;

@Service
public class NewsDataProviderImpl implements DataProvider<NewsModel> {

    @Autowired
    private NewsDAO newsDAO;

    @Override
    public Long getRowCount() {
        return newsDAO.getNewsCount(Util.getNow());
    }

    @Override
    public List<NewsModel> getItemsByRange(DataModel<NewsModel> dataModel, int first,
            int numberOfRows, Object... objects) {
        List<NewsModel> result = new ArrayList<NewsModel>();
        List<News> list = newsDAO.getNewsForDate(Util.getNow(), first, numberOfRows);
        for (News news : list) {
            result.add(create(news));
        }
        return result;
    }

    public NewsModel create(News news) {
        NewsModel model = NewsModel.builder()
                .id(news.getId())
                .start(news.getStart())
                .end(news.getEnd())
                .active(news.isActive())
                .name(news.getName())
                .desc(news.getDesc())
                .shortDescription(getShortDescr(news.getDesc())).build();
        return model;
    }

    public String getShortDescr(String desc) {
        String shortDescription = "";
        if (!Util.isEmpty(desc)) {
            shortDescription = Jsoup.parse(desc).text();
            if (shortDescription.length() > 255) {
                shortDescription = shortDescription.substring(0, 255)
                        .concat("...");
            }
        } else {
            shortDescription = "";
        }
        return shortDescription;
    }

}
