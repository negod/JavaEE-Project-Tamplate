package com.negod.archetype.generic;

import com.negod.archetype.generic.search.GenericFilter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 * @param <T>
 * @exclude
 *
 */
@Slf4j
public abstract class GenericRestService<T extends GenericEntity> implements RestService<T> {

    public abstract GenericDao getDao();

    /**
     * Gets all searchfields available in the entity
     *
     * @return
     */
    public Response getSearchFields() {
        Set<String> searchFields = getDao().getSearchFields();
        return Response.ok(searchFields, MediaType.APPLICATION_JSON).build();
    }

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
     * @param filter
     * @return
     */
    public Response getFilteredList(GenericFilter filter) {
        try {
            log.debug("Getting all " + getDao().getClassName() + " with filter " + filter);
            Optional<List<T>> responseList = getDao().getAll(filter);
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

    /**
     * Creates a Lucene index for the entity
     *
     * @return
     */
    public Response indexEntity() {
        return Response.ok(getDao().indexEntity(), MediaType.WILDCARD_TYPE).build();
    }

}
