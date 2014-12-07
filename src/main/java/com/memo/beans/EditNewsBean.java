package com.memo.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.entity.News;
import com.memo.service.NewsService;
import com.memo.utils.Component;

@ManagedBean(name = "editNewsBean")
@ViewScoped
public class EditNewsBean extends ViewMsgBean {

    /**
     *
     */
    private static final long serialVersionUID = 8698280689138322494L;

    @ManagedProperty("#{newsServiceImpl}")
    protected NewsService newsService;

    private News news;

    @PostConstruct
    public void init() {
        try {
            Long newsId = Component.getParamLong("newsId");
            if (newsId != null && newsId > 0) {
                news = newsService.find(newsId);
            } else {
                news = new News();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String save() {
        newsService.update(news);
        success();
        return "/pages/admin/news.xhtml?faces-redirect=true";
    }

    public News getNews() {
        return news;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

}
