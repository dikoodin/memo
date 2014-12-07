package com.memo.beans;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.entity.News;
import com.memo.service.NewsService;

@ManagedBean(name = "newsBean")
@ViewScoped
public class NewsBean extends ViewMsgBean {

    /**
     *
     */
    private static final long serialVersionUID = 8216885765959843504L;

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
