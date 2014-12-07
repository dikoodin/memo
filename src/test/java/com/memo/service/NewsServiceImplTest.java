package com.memo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.memo.dao.NewsDAO;
import com.memo.entity.News;

public class NewsServiceImplTest {

    NewsServiceImpl newsService = new NewsServiceImpl();

    NewsDAO newsDAO;
    EntityManager em;

    @BeforeMethod
    public void setUp() {
        newsDAO = Mockito.spy(new NewsDAO());
        em = Mockito.mock(EntityManager.class);
        Mockito.when(newsDAO.getEntityManager()).thenReturn(em);
        newsService.newsDAO = newsDAO;
    }

    @Test
    public void whenNewsExistsThenGetAllShouldReturnNews() {
        // GIVEN
        List<News> news = new ArrayList<News>();
        News entity = new News();
        entity.setId(1L);
        news.add(entity);
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery(Matchers.anyString())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(news);
//        Mockito.when(newsDAO.getAll()).thenReturn(news);

        // WHEN
        List<News> list = newsService.getAll();

        // THEN
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void whenNewsExistsThenFindShouldReturnEntity() {
        // GIVEN
        Mockito.when(em.find(Matchers.any(Class.class), Matchers.any())).thenReturn(new News());
        Long newsId = 123L;

        // WHEN
        News entity = newsService.find(newsId);

        // THEN
        Assert.assertNotNull(entity);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void whenNewsNotExistsThenFindShouldReturnNull() {
        // GIVEN
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(newsDAO.getEntityManager()).thenReturn(em);
        Mockito.when(em.find(Matchers.any(Class.class), Matchers.any())).thenThrow(PersistenceException.class);

        Long newsId = 123L;

        // WHEN
        News entity = newsService.find(newsId);

        // THEN
        Assert.assertNull(entity);
    }

}
