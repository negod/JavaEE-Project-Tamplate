package com.negod.archetype.control;

import com.negod.archetype.boundary.AccountDao;
import com.negod.archetype.entity.Account;
import com.negod.archetype.generic.GenericDao;
import com.negod.archetype.generic.GenericRestService;
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

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
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
     * @responseType java.util.List<com.negod.archetype.entity.Account>
     */
    @GET
    @Path("/list/{listSize}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getFilteredEntityList(@PathParam("listSize") Integer listSize) {
        return super.getFilteredList(listSize);
    }

    /**
     * {@inheritDoc}
     *
     * @responseType java.util.List<com.negod.archetype.entity.Account>
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
     * @responseType com.negod.archetype.entity.Account
     * @param entity
     */
    @PUT
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response updateEntity(Account entity) {
        return super.update(entity);
    }

    /**
     * {@inheritDoc}
     *
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
}
