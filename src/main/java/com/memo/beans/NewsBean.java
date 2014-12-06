package com.memo.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.entity.News;
import com.memo.manager.ViewMsgBean;
import com.memo.service.NewsService;

@ManagedBean(name = "newsBean")
@ViewScoped
public class NewsBean extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3111988099407754250L;

    private List<News> newsList = new LinkedList<News>();

    @ManagedProperty("#{newsServiceImpl}")
    private NewsService newsService;

    @PostConstruct
    public void init() {
        newsList = newsService.getAll();
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void delete(News news) {
        newsList.remove(news);
        newsService.delete(news);
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

}
