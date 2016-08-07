package com.negod.archetype.generic;

import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 * @param <T>
 * @exclude
 *
 */
public abstract class GenericRestService<T extends GenericEntity> implements RestService<T> {

    Logger log = LoggerFactory.getLogger(GenericRestService.class);

    public abstract GenericDao getDao();

    /**
     * Gets an entity by the external id.
     *
     * @param id
     * @return
     */
    public Response getById(String id) {
        try {
            log.debug("Getting " + getDao().getClassName() + " by id: {}", id);
            Optional<T> entity = getDao().getById(id);
            if (entity.isPresent()) {
                return Response.ok(entity.get(), MediaType.APPLICATION_JSON).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            log.debug("Error when getting " + getDao().getClassName() + " by id: {}", id);
            return Response.serverError().build();
        }
    }

    /**
     * Gets a list of the entity with filtration
     *
     * @param listSize
     * @return
     */
    public Response getFilteredList(Integer listSize) {
        try {
            log.debug("Getting all " + getDao().getClassName() + " with listSize " + listSize);
            Optional<List<T>> responseList = getDao().getAll(listSize);
            if (responseList.isPresent()) {
                List<T> entityList = responseList.get();
                return Response.ok(entityList, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            log.error("Error when getting filtered list {}", e);
            return Response.serverError().build();
        }
    }

    /**
     * Gets all entities
     *
     * @return
     */
    public Response getAll() {
        log.debug("Getting all " + getDao().getClassName() + "s");
        try {
            Optional<List<T>> responseList = getDao().getAll();
            if (responseList.isPresent()) {
                List<T> entityList = responseList.get();
                return Response.ok(entityList, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            log.error("Error when getting " + getDao().getClassName() + "s" + " {}", e);
            return Response.serverError().build();
        }
    }

    /**
     * Creates an entity
     *
     * @param entity
     * @return
     */
    public Response create(T entity) {
        log.debug("Creating " + getDao().getClassName() + " {}", entity.toString());
        try {
            Optional<T> createdEntity = getDao().persist(entity);
            if (createdEntity.isPresent()) {
                return Response.ok(createdEntity.get(), MediaType.APPLICATION_JSON).build();
            } else {
                return Response.serverError().build();
            }
        } catch (Exception e) {
            log.error("Error when creating " + getDao().getClassName() + " {}", entity.toString(), e);
            return Response.serverError().build();
        }
    }

    /**
     * Updates an entity
     *
     * @param entity
     * @return
     */
    public Response update(T entity) {
        log.debug("Updating " + getDao().getClassName() + " {}", entity.toString());
        try {
            Optional<T> updatedEntity = getDao().update(entity);
            if (updatedEntity.isPresent()) {
                return Response.ok(updatedEntity.get(), MediaType.APPLICATION_JSON).build();
            } else {
                return Response.serverError().build();
            }
        } catch (Exception e) {
            log.error("Error when updating " + getDao().getClassName() + " {}", entity.toString(), e);
            return Response.serverError().build();
        }
    }

    /**
     * Deletes an entity
     *
     * @param id
     */
    public void delete(String id) {
        log.debug("Deleting " + getDao().getClassName() + " with ID {}", id);
        try {
            getDao().delete(id);
        } catch (Exception e) {
            log.error("Error when deleting " + getDao().getClassName() + " with id {}", id, e);
        }
    }

}
