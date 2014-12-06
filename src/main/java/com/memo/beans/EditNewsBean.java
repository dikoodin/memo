package com.memo.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.memo.entity.News;
import com.memo.entity.NewsEntity;
import com.memo.manager.ViewMsgBean;
import com.memo.service.NewsService;
import com.memo.utils.Component;

@ManagedBean(name = "editNewsBean")
@ViewScoped
public class EditNewsBean extends ViewMsgBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4184026689307942094L;

    @ManagedProperty("#{newsServiceImpl}")
    protected NewsService newsService;

    private News news;

    @PostConstruct
    public void init() {
        try {
            Long newsId = Component.getParamLong("newsId");
            if (newsId == null) {
                newsId = new Long(0);
            }
            if (newsId > 0) {
                news = newsService.find(newsId);
            } else {
                news = new NewsEntity();
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
