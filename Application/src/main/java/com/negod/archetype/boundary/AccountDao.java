/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype.boundary;

import com.negod.archetype.generic.GenericDao;
import com.negod.archetype.entity.Account;
import com.negod.archetype.boundary.exception.DaoException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@LocalBean
@Stateless
public class AccountDao extends GenericDao<Account> {

    Logger log = LoggerFactory.getLogger(AccountDao.class);

    public AccountDao() throws DaoException {
        super(Account.class);
    }

}
