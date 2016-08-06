/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype.control;

import com.negod.archetype.boundary.AccountDao;
import com.negod.archetype.entity.Account;
import java.util.List;
import java.util.Optional;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Path("/account")
@Stateless
public class AccountService {

    Logger log = LoggerFactory.getLogger(AccountService.class);

    @EJB
    AccountDao accountDao;

    /**
     *
     * @param id
     * @return The requested account
     * @responseType com.negod.archetype.entity.Account
     *
     * @responseMessage 200 Account successfully retrieved
     * @responseMessage 500 Error when retrieving the account
     *
     * @summary Gets a account by its id
     *
     *
     */
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAccountById(@PathParam("id") String id) {
        log.debug("Getting account by id: {}", id);
        try {
            Optional<Account> account = accountDao.getById(id);
            if (account.isPresent()) {
                return Response.ok(account.get(), MediaType.APPLICATION_JSON).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            log.error("Error when getting account by ID {}", id, e);
            return Response.serverError().build();
        }
    }

    /**
     * @responseType java.util.List<com.negod.archetype.entity.Account>
     *
     * @responseMessage 200 Account successfully retrieved
     * @responseMessage 500 Error when retrieving the account
     *
     * @summary Get all accounts
     *
     *
     * @return All accounts
     */
    @GET
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAccounts() {
        log.debug("Getting all accounts");
        try {
            Optional<List<Account>> accountList = accountDao.getAll();
            if (accountList.isPresent()) {
                List<Account> accountById = accountList.get();
                return Response.ok(accountById, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            log.error("Error when getting accounts {}", e);
            return Response.serverError().build();
        }
    }

    /**
     *
     * @param account
     * @return The created account
     *
     * @responseType com.negod.archetype.entity.Account
     *
     * @responseMessage 200 Acocunt successfully persisted
     * @responseMessage 500 Error when persisting account
     *
     * @summary Persists a account to database
     *
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Account account) {
        log.debug("Creating account {}", account.toString());
        try {
            Optional<Account> accountById = accountDao.persist(account);
            if (accountById.isPresent()) {
                return Response.ok(accountById.get(), MediaType.APPLICATION_JSON).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            log.error("Error when creating account {}", account.toString(), e);
            return Response.serverError().build();
        }
    }

    /**
     *
     * @param account
     * @return The created account
     *
     * @responseType com.negod.archetype.entity.Account
     *
     * @responseMessage 200 Acocunt successfully updated
     * @responseMessage 500 Error when updating account
     *
     * @summary Updates an Account
     *
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(Account account) {
        log.debug("Updating account {}", account.toString());
        try {
            Optional<Account> accountById = accountDao.update(account);
            if (accountById.isPresent()) {
                return Response.ok(accountById.get(), MediaType.APPLICATION_JSON).build();
            } else {
                return Response.serverError().build();
            }
        } catch (Exception e) {
            log.error("Error when updating account {}", account.toString(), e);
            return Response.serverError().build();
        }
    }

    /**
     *
     * @param id
     * @responseType
     *
     * @responseMessage 200 Acocunt successfully deleted
     * @responseMessage 500 Error when deleting account
     *
     * @summary Deletes an account
     *
     */
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        log.debug("Deleting account with ID {}", id);
        try {
            accountDao.delete(id);
        } catch (Exception e) {
            log.error("Error when deleting account with id {}", id, e);
        }
    }

}
