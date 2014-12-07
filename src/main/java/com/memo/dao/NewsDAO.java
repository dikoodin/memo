package com.memo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.memo.entity.News;

@Repository
public class NewsDAO extends DAO {

    @SuppressWarnings("unchecked")
    public List<News> getAll() {
        return getEntityManager().createQuery("SELECT e FROM News e ORDER BY e.id DESC").getResultList();
    }

    public Long getNewsCount(Date date) {
        return (Long) getEntityManager()
                .createQuery("SELECT COUNT(a.id) FROM News a  WHERE a.start <= :date AND a.end >= :date AND a.active = TRUE")
                .setParameter("date", date).setMaxResults(1).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<News> getNewsForDate(Date date, int first, int numberOfRows) {
        return getEntityManager()
                .createQuery("SELECT a FROM News a WHERE a.start <= :date AND a.end >= :date AND a.active = TRUE ORDER BY a.created DESC, a.start DESC")
                .setParameter("date", date)
                .setFirstResult(first)
                .setMaxResults(numberOfRows).getResultList();
    }

}
