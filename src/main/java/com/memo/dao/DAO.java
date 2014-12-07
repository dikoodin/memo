package com.memo.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.memo.entity.BaseEntity;
import com.memo.utils.Util;

public abstract class DAO {

    private static final Logger LOG = LoggerFactory.getLogger(DAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Transactional(readOnly = true)
    public boolean exists(Object id, Class clazz) {
        try {
            Object object = getEntityManager().find(clazz, id);
            return object != null;
        } catch (PersistenceException e) {
            // Entity not found.
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public <E extends BaseEntity> E find(Object id, Class<?> clazz) {
        try {
            return (E) getEntityManager().find(clazz, id);
        } catch (PersistenceException e) {
            // Entity not found.
            return null;
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Object merge(Object base) {
        return entityManager.merge(base);
    }

    public void remove(Object base) {
        entityManager.remove(base);
    }

    public void persist(Object base) {
        entityManager.persist(base);
    }

    public void refresh(Object entity) {
        entityManager.refresh(entity);
    }

    public void delete(BaseEntity base) {
        if (base == null) {
            LOG.error("Call indsert with null.");
            return;
        }
        base = (BaseEntity) merge(base);
        remove(base);
    }

    public void insert(BaseEntity base) {
        if (base == null) {
            LOG.error("Call insert with null.");
            return;
        }
        base.setCreated(Util.getNow());
        persist(base);
    }

    public BaseEntity update(BaseEntity base) {
        if (base == null) {
            LOG.error("Call update with null.");
            return null;
        }
        Date moment = Util.getNow();
        if (base.getCreated() == null) {
            base.setCreated(moment);
        } else {
            base.setModified(moment);
        }
        base = (BaseEntity) merge(base);
        return base;
    }

    public boolean isResultOne(Integer num) {
        return num != null && num == 1;
    }

}
