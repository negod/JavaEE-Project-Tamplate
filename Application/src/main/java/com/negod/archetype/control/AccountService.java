package com.negod.archetype.control;

import com.negod.archetype.boundary.AccountDao;
import com.negod.archetype.entity.Account;
import com.negod.archetype.generic.GenericDao;
import com.negod.archetype.generic.GenericRestService;
import com.negod.archetype.generic.search.GenericFilter;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
@Path("/account")
@Stateless
public class AccountService extends GenericRestService<Account> {

    @EJB
    AccountDao accountDao;

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public GenericDao getDao() {
        return accountDao;
    }

    /**
     * {@inheritDoc}
     *
     * @summary Get all Accounts
     * @responseType java.lang.Boolean
     */
    @GET
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getAllEntities() {
        return super.getAll();
    }

    /**
     * {@inheritDoc}
     *
     * @summary Creates an Account
     * @responseType com.negod.archetype.entity.Account
     * @param entity
     */
    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response createEntity(Account entity) {
        return super.create(entity);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Updates an Account
     * @responseType com.negod.archetype.entity.Account
     * @param entity
     */
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response updateEntity(@PathParam("id") String id, Account entity) {
        return super.update(id, entity);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Deletes an Account
     * @param id
     */
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    @Override
    public void deleteEntity(@PathParam("id") String id) {
        super.delete(id);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Gets an Account by id
     * @responseType com.negod.archetype.entity.Account
     */
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getEntityById(@PathParam("id") String id) {
        return super.getById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Gets a filtered Acocunt list
     * @responseType java.util.List<com.negod.archetype.entity.Account>
     */
    @POST
    @Path("/list")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getFilteredEntityList(GenericFilter filter) {
        return super.getFilteredList(filter);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Gets all searchable fields
     * @responseType java.lang.String
     */
    @GET
    @Path("/search/fields")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getEntitySearchFields() {
        return super.getSearchFields();
    }

    /**
     * {@inheritDoc}
     *
     * @summary Reindexes all Accounts
     * @responseType java.lang.Boolean
     */
    @GET
    @Path("/index")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response indexEntity() {
        return super.indexEntity();
    }

}
