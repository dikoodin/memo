package com.memo.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.datalist.DataList;

import com.memo.datamodel.DataProvider;
import com.memo.datamodel.KDataModel;
import com.memo.model.NewsModel;
import com.memo.utils.Component;

@ViewScoped
@ManagedBean(name = "homeBean")
public class HomeBean extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2066556966063117603L;

    @ManagedProperty(value = "#{newsDataProviderImpl}")
    private DataProvider<NewsModel> newsDataProvider;

    private KDataModel<NewsModel> newsList;

    @PostConstruct
    public void init() {
        newsList = new KDataModel<NewsModel>(newsDataProvider, 10, "news_id",
                DataList.class);
    }

    public String viewNews(Long newsId) {
        if (newsId == null) {
            return null;
        }
        Selection selection = (Selection) Component
                .getInstance("selection");
        selection.setItemId(newsId);
        return "k_news";
    }

    public KDataModel<NewsModel> getNewsList() {
        return newsList;
    }

    public void setNewsDataProvider(DataProvider<NewsModel> newsDataProvider) {
        this.newsDataProvider = newsDataProvider;
    }

}
