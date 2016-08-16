package com.negod.archetype.generic;

import com.negod.archetype.generic.search.GenericFilter;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 * @param <T>
 */
public interface RestService<T extends GenericEntity> {

    /**
     * @return The entitys searchfields
     * @responseMessage 200 Success
     * @responseMessage 500 Error
     * @summary Gets all searchable fieldnames
     */
    public Response getEntitySearchFields();

    /**
     * @param id The external id of the entity
     * @return the requested entity
     * @responseMessage 200 Success
     * @responseMessage 500 Error
     * @summary Gets the entity by its id
     */
    public Response getEntityById(String id);

    /**
     * @param filter The filter for the search
     * @return the filtered list
     * @responseMessage 200 Success
     * @responseMessage 500 Error
     * @summary Get all entitites with a fixed listsize
     */
    public Response getFilteredEntityList(GenericFilter filter);

    /**
     *
     * @param entity The entity to persist
     * @return The created entity
     * @responseMessage 200 Success
     * @responseMessage 500 Error
     * @summary Persists an entity to database
     */
    public Response createEntity(T entity);

    /**
     *
     * @param entity the entity to update
     * @return The created entity
     * @responseMessage 200 Success
     * @responseMessage 500 Error
     * @summary Updates an entity
     */
    public Response updateEntity(T entity);

    /**
     * @param id the external id of the entity to delete
     * @responseMessage 200 Success
     * @responseMessage 500 Error
     * @summary Deletes an entity
     */
    public void deleteEntity(String id);

}
