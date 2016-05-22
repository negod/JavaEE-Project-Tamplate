/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype.generic;

import com.negod.archetype.boundary.exception.DaoException;
import java.util.List;
import java.util.Optional;
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

    private Optional<CriteriaQuery<T>> getCriteriaQuery() throws DaoException {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            return Optional.ofNullable(criteriaBuilder.createQuery(entityClass));
        } catch (Exception e) {
            throw new DaoException("Error when getting Criteria Query ", e);
        }
    }

    public Optional<T> persist(T entity) throws DaoException {
        try {
            em.persist(entity);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            throw new DaoException("Error when persisting entity ", e);
        }
    }

    public Optional<T> update(T entity) throws DaoException {
        try {
            return Optional.ofNullable(em.merge(entity));
        } catch (Exception e) {
            throw new DaoException("Error when updating entity ", e);
        }
    }

    public abstract Boolean delete(String externalId);

    protected void delete(T entity) throws DaoException {
        try {
            em.remove(entity);
        } catch (Exception e) {
            throw new DaoException("Error when deleting entity ", e);
        }
    }

    public Optional<T> getById(String id) throws DaoException {
        try {

            Optional<CriteriaQuery<T>> data = this.getCriteriaQuery();

            if (data.isPresent()) {
                CriteriaQuery<T> cq = data.get();
                Root<T> entity = cq.from(entityClass);
                cq.where(entity.get(GenericEntity_.id).in(id));
                return get(cq);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            throw new DaoException("Error when getting entity by id ", e);
        }
    }

    public Optional<List<T>> getAll() throws DaoException {
        try {

            Optional<CriteriaQuery<T>> data = this.getCriteriaQuery();

            if (data.isPresent()) {
                CriteriaQuery<T> cq = data.get();
                Root<T> entity = cq.from(entityClass);
                return getList(cq);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            throw new DaoException("Error when getting entity by id ", e);
        }
    }

    protected Optional<T> get(CriteriaQuery<T> query) throws DaoException {
        try {
            TypedQuery<T> typedQuery = em.createQuery(query);
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (Exception e) {
            throw new DaoException("Error when gettting entity " + query.getResultType(), e);
        }
    }

    protected Optional<List<T>> getList(CriteriaQuery<T> query) throws DaoException {
        try {
            TypedQuery<T> typedQuery = em.createQuery(query);
            List<T> resultList = typedQuery.getResultList();
            return Optional.ofNullable(resultList);
        } catch (Exception e) {
            throw new DaoException("Error when gettting entity list " + query.getResultType(), e);
        }
    }

}
