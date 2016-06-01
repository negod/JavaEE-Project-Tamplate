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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 * @param <T> The entity to handle
 */
public abstract class GenericDao<T extends GenericEntity> {

    Logger log = LoggerFactory.getLogger(GenericDao.class);

    @PersistenceContext(unitName = "persistancePU")
    private EntityManager em;

    private final Class<T> entityClass;

    public GenericDao(Class entityClass) throws DaoException {
        log.debug("Instantiating GenericDao for entity class " + entityClass.getName());
        if (entityClass == null) {
            log.error("Entity class cannot be null in constructor when instantiating GenericDao");
            throw new DaoException("Entity class cannot be null in constructor when instantiating GenericDao");
        } else {
            this.entityClass = entityClass;
        }
    }

    private Optional<CriteriaQuery<T>> getCriteriaQuery() throws DaoException {
        log.debug("Getting criteria query for " + entityClass.getName());
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            return Optional.ofNullable(criteriaBuilder.createQuery(entityClass));
        } catch (Exception e) {
            log.error("Error when getting Criteria Query in Generic Dao");
            throw new DaoException("Error when getting Criteria Query ", e);
        }
    }

    public Optional<T> persist(T entity) throws DaoException {
        log.debug("Persisting entity of type {} with values {} ", entityClass.getName(), entity.toString());
        try {
            em.persist(entity);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            log.error("Error when persisting entity in Generic Dao");
            throw new DaoException("Error when persisting entity ", e);
        }
    }

    public Optional<T> update(T entity) throws DaoException {
        log.debug("Updating entity of type {} with values {} ", entityClass.getName(), entity.toString());
        try {
            return Optional.ofNullable(em.merge(entity));
        } catch (Exception e) {
            log.error("Error when updating entity in Generic Dao");
            throw new DaoException("Error when updating entity ", e);
        }
    }

    public abstract Boolean delete(String externalId);

    protected void delete(T entity) throws DaoException {
        log.debug("Deleting entity of type {} with values {} ", entityClass.getName(), entity.toString());
        try {
            em.remove(entity);
        } catch (Exception e) {
            log.error("Error when deleting entity in Generic Dao");
            throw new DaoException("Error when deleting entity ", e);
        }
    }

    public Optional<T> getById(String id) throws DaoException {
        log.debug("Getting entity of type {} with id {} ", entityClass.getName(), id);
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
            log.error("Error when getting entity by id: {} in Generic Dao", id);
            throw new DaoException("Error when getting entity by id ", e);
        }
    }

    public Optional<List<T>> getAll() throws DaoException {
        log.debug("Getting all values of type {} ", entityClass.getName());
        try {

            Optional<CriteriaQuery<T>> data = this.getCriteriaQuery();

            if (data.isPresent()) {
                CriteriaQuery<T> cq = data.get();
                Root<T> rootEntity = cq.from(entityClass);
                CriteriaQuery<T> allQuery = cq.select(rootEntity);
                return getList(allQuery);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            log.error("Error when getting all in Generic Dao");
            throw new DaoException("Error when getting entity by id ", e);
        }
    }

    protected Optional<T> get(CriteriaQuery<T> query) throws DaoException {
        log.debug("Executing query {} ", query.toString());
        try {
            TypedQuery<T> typedQuery = em.createQuery(query);
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (Exception e) {
            log.error("Error when gettting entity" + query.getResultType());
            throw new DaoException("Error when gettting entity " + query.getResultType(), e);
        }
    }

    protected Optional<List<T>> getList(CriteriaQuery<T> query) throws DaoException {
        log.debug("Getting list of type {} with query {} ", entityClass.getName(), query.toString());
        try {
            TypedQuery<T> typedQuery = em.createQuery(query);
            List<T> resultList = typedQuery.getResultList();
            return Optional.ofNullable(resultList);
        } catch (Exception e) {
            log.error("Error when gettting entity list " + query.getResultType());
            throw new DaoException("Error when gettting entity list " + query.getResultType(), e);
        }
    }

}
