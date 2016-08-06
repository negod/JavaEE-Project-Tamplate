/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype.generic;

import com.negod.archetype.boundary.exception.DaoException;
import java.util.Date;
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
        log.trace("Instantiating GenericDao for entity class {} ", entityClass.getSimpleName());
        if (entityClass == null) {
            log.error("Entity class cannot be null in constructor when instantiating GenericDao");
            throw new DaoException("Entity class cannot be null in constructor when instantiating GenericDao");
        } else {
            this.entityClass = entityClass;
        }
    }

    /**
     *
     * @return Criteria builder created by Entity Manager
     * @throws DaoException
     */
    private Optional<CriteriaQuery<T>> getCriteriaQuery() throws DaoException {
        log.trace("Getting criteria query for {}", entityClass.getSimpleName());
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            return Optional.ofNullable(criteriaBuilder.createQuery(entityClass));
        } catch (Exception e) {
            log.error("Error when getting Criteria Query in Generic Dao");
            throw new DaoException("Error when getting Criteria Query ", e);
        }
    }

    /**
     * Persist the Entity to DB
     *
     * @param entity The entity to persist
     * @return The persisted entity
     * @throws DaoException
     */
    public Optional<T> persist(T entity) throws DaoException {
        log.debug("Persisting entity of type {} with values {} ", entityClass.getSimpleName(), entity.toString());
        try {
            em.persist(entity);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            log.error("Error when persisting entity in Generic Dao");
            throw new DaoException("Error when persisting entity ", e);
        }
    }

    /**
     *
     * Updates the selected Entity
     *
     * @param entity The entity to update
     * @return The updated entity
     * @throws DaoException
     */
    public Optional<T> update(T entity) throws DaoException {
        try {
            Optional<T> entityToUpdate = getById(entity.getId());
            
            if (entityToUpdate.isPresent()) {
                log.debug("Updating entity of type {} with values {} ", entityClass.getSimpleName(), entity.toString());
                em.detach(entityToUpdate.get());
                entity.setInternalId(entityToUpdate.get().getInternalId());
                entity.setUpdatedDate(new Date());
            } else {
                return Optional.empty();
            }
            
            return Optional.ofNullable(em.merge(entity));
        } catch (Exception e) {
            log.error("Error when updating entity in Generic Dao");
            throw new DaoException("Error when updating entity ", e);
        }
    }

    /**
     *
     * @param externalId
     * @return true or false depenent on the success of the deletion
     */
    public Boolean delete(String externalId) {
        try {
            Optional<T> entity = getById(externalId);
            if (entity.isPresent()) {
                return delete(entity.get());
            } else {
                log.error("No entity of type: {} found with id: {}", entityClass.getSimpleName(), externalId);
            }
        } catch (DaoException ex) {
            log.error("Error when deleting entity of type: {} with id: {}. ErrorMessage: {}", entityClass.getSimpleName(), externalId, ex.getMessage());
        }
        return Boolean.FALSE;
        
    }

    /**
     *
     * Deletes the selected Entity
     *
     * @param entity The entity to delete
     * @return true or false depenent on the success of the deletion
     * @throws DaoException
     */
    public Boolean delete(T entity) throws DaoException {
        log.debug("Deleting entity of type {} with values {} ", entityClass.getSimpleName(), entity.toString());
        try {
            em.remove(entity);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("Error when deleting entity in Generic Dao");
            throw new DaoException("Error when deleting entity ", e);
        }
        
    }

    /**
     * Get an entity by its external id
     *
     * @param id The external id (GUID) of the entity
     * @return The entity that matches the id
     * @throws DaoException
     */
    public Optional<T> getById(String id) throws DaoException {
        log.debug("Getting entity of type {} with id {} ", entityClass.getSimpleName(), id);
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

    /**
     *
     * Gets all entities that are persisted to the database
     *
     * @return All persisted entities
     * @throws DaoException
     */
    public Optional<List<T>> getAll() throws DaoException {
        log.debug("Getting all values of type {} ", entityClass.getSimpleName());
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

    /**
     *
     * Gets an entity based on a query
     *
     * @param query The query to execute
     * @return The queried entity
     * @throws DaoException
     */
    protected Optional<T> get(CriteriaQuery<T> query) throws DaoException {
        log.trace("Getting object of type {}", entityClass.getSimpleName());
        try {
            TypedQuery<T> typedQuery = em.createQuery(query);
            return executeTypedQuery(typedQuery);
        } catch (Exception e) {
            log.error("Error when gettting entity" + query.getResultType());
            throw new DaoException("Error when gettting entity " + query.getResultType(), e);
        }
    }

    /**
     *
     * Gets a list of entities based on a query
     *
     * @param query The query to execute
     * @return The queried entity list
     * @throws DaoException
     */
    protected Optional<List<T>> getList(CriteriaQuery<T> query) throws DaoException {
        log.debug("Getting list of type {}", entityClass.getSimpleName());
        try {
            return executeTypedQueryList(em.createQuery(query));
        } catch (Exception e) {
            log.error("Error when gettting entity list " + query.getResultType());
            throw new DaoException("Error when gettting entity list " + query.getResultType(), e);
        }
    }

    /**
     *
     * Executes a Typed Query
     *
     * @param query The query to execute
     * @return The queried entity
     * @throws DaoException
     */
    protected Optional<List<T>> executeTypedQueryList(TypedQuery<T> query) throws DaoException {
        log.trace("Executing list query for type {} with query: [ {} ]", entityClass.getSimpleName(), query.unwrap(org.hibernate.Query.class).getQueryString());
        try {
            List<T> resultList = query.getResultList();
            return Optional.ofNullable(resultList);
        } catch (Exception e) {
            log.error("Error when gettting entity list " + entityClass.getSimpleName());
            throw new DaoException("Error when gettting entity list " + entityClass.getSimpleName(), e);
        }
    }

    /**
     *
     * Executes a Typed Query
     *
     * @param query The query to execute
     * @return The queried entity list
     * @throws DaoException
     */
    protected Optional<T> executeTypedQuery(TypedQuery<T> query) throws DaoException {
        log.trace("Executing query for type {} with query: [ {} ]", entityClass.getSimpleName(), query.unwrap(org.hibernate.Query.class).getQueryString());
        try {
            T result = query.getSingleResult();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            log.error("Error when gettting entity list " + entityClass.getSimpleName());
            throw new DaoException("Error when gettting entity list " + entityClass.getSimpleName(), e);
        }
    }
    
}
