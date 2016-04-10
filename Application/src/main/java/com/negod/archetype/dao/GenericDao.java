/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype.dao;

import com.negod.archetype.entity.GenericEntity;
import com.negod.archetype.entity.GenericEntity_;
import com.negod.archetype.exception.DaoException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 * @param <T> The entity to handle
 */

public abstract class GenericDao<T extends GenericEntity> {

    @PersistenceContext(unitName = "persistancePU")
    private EntityManager em;

    private final Class<T> entityClass;

    public GenericDao(Class entityClass) throws DaoException {
        if (entityClass == null) {
            throw new DaoException("Entity class cannot be null in constructor when instantiating GenericDao");
        } else {
            this.entityClass = entityClass;
        }
    }

    protected CriteriaQuery<T> getCriteriaQuery() throws DaoException {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            return criteriaBuilder.createQuery(entityClass);
        } catch (Exception e) {
            throw new DaoException("Error when getting Criteria Query ", e);
        }
    }

    public T persist(T entity) throws DaoException {
        try {
            
            em.persist(entity);
            return entity;
        } catch (Exception e) {
            throw new DaoException("Error when persisting entity ", e);
        }
    }

    public T update(T entity) throws DaoException {
        try {
            return em.merge(entity);
        } catch (Exception e) {
            throw new DaoException("Error when updating entity ", e);
        }
    }

    public abstract void delete(String externalId);

    protected void delete(T entity) throws DaoException {
        try {
            em.remove(entity);
        } catch (Exception e) {
            throw new DaoException("Error when deleting entity ", e);
        }
    }

    public T getById(String id) throws DaoException {
        try {
            CriteriaQuery<T> cq = this.getCriteriaQuery();
            Root<T> entity = cq.from(entityClass);
            cq.where(entity.get(GenericEntity_.id).in(id));
            return get(cq);
        } catch (Exception e) {
            throw new DaoException("Error when getting entity by id ", e);
        }
    }

    protected T get(CriteriaQuery<T> query) throws DaoException {
        try {
            TypedQuery<T> typedQuery = em.createQuery(query);
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            throw new DaoException("Error when gettting entity " + query.getResultType(), e);
        }
    }

    protected List<T> getList(CriteriaQuery<T> query) throws DaoException {
        try {
            TypedQuery<T> typedQuery = em.createQuery(query);
            return typedQuery.getResultList();
        } catch (Exception e) {
            throw new DaoException("Error when gettting entity list " + query.getResultType(), e);
        }
    }

}
