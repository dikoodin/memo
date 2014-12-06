package com.memo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memo.dao.NewsDAO;
import com.memo.entity.News;
import com.memo.entity.NewsEntity;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    protected NewsDAO newsDAO;

    @Transactional(readOnly = true)
    public List<News> getAll() {
        return newsDAO.getAll();
    }

    @Transactional(readOnly = true)
    public News find(Long newsId) {
        return newsDAO.find(newsId, NewsEntity.class);
    }

    public News update(News news) {
        return (News) newsDAO.update(news);
    }

    public void delete(News news) {
        newsDAO.delete(news);
    }

}
