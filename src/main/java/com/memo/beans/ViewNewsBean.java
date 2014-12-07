package com.memo.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.entity.News;
import com.memo.service.NewsService;
import com.memo.utils.Component;

@ManagedBean(name = "viewNewsBean")
@ViewScoped
public class ViewNewsBean extends ViewMsgBean {

    /**
     *
     */
    private static final long serialVersionUID = 7177381865862213906L;

    @ManagedProperty("#{newsServiceImpl}")
    private NewsService newsService;

    private News news;

    @PostConstruct
    public void init() {
        Long newsId = Component.getParamLong("newsId");
        if (newsId == null || newsId <= 0) {
            setError(true);
            return;
        }
        news = newsService.find(newsId);
    }

    public News getNews() {
        return news;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

}