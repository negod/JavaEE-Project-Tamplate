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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Stateless
@Path("/account")
public class AccountService {

    Logger log = LoggerFactory.getLogger(AccountService.class);

    @EJB
    AccountDao accountDao;

    /**
     *
     * @param id
     * @return
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
        try {
            Optional<Account> account = accountDao.getById(id);
            if (account.isPresent()) {
                Account accountById = account.get();
                return Response.ok(accountById, MediaType.APPLICATION_JSON).build();
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
     * @summary Gets a account by its id
     *
     *
     * @return
     */
    @GET
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAccounts() {
        try {
            Optional<List<Account>> accountList = accountDao.getAll();
            if (accountList.isPresent()) {
                List<Account> accountById = accountList.get();
                return Response.ok(accountById, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            log.error("Error when getting account by ID {}", e);
            return Response.serverError().build();
        }
    }

    /**
     *
     * @param account
     * @return
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
        try {
            Optional<Account> accountById = accountDao.persist(account);
            if (accountById.isPresent()) {
                return Response.ok(accountById, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            log.error("Error when creating account {}", account.toString(), e);
            return Response.serverError().build();
        }
    }

}
