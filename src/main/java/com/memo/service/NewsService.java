package com.memo.service;

import java.util.List;

import com.memo.entity.News;

public interface NewsService {

    public List<News> getAll();

    public News find(Long newsId);

    public News update(News news);

    public void delete(News news);

}