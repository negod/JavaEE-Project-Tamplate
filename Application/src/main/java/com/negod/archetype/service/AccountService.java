/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype.service;

import com.negod.archetype.dao.AccountDao;
import com.negod.archetype.entity.Account;
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
    public Response getAccounts(@PathParam("id") String id) {
        try {
            Account accountById = accountDao.getById(id);
            return Response.ok(accountById, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            log.error("Error when getting account by ID {}", id, e);
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
            Account accountById = accountDao.persist(account);
            return Response.ok(accountById, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            log.error("Error when creating account {}", account.toString(), e);
            return Response.serverError().build();
        }
    }

}
