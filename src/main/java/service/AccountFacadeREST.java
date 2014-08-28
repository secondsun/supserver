/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.jboss.aerogear.sup.supserver.vo.Account;

/**
 *
 * @author summers
 */
@Stateless
@Path("account")
public class AccountFacadeREST extends AbstractFacade<Account> {
    @PersistenceContext(unitName = "org.jboss.aerogear.sup_supserver_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Context
    private SecurityContext securityContext;
    
    public AccountFacadeREST() {
        super(Account.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Account entity) {
        entity.setPrinciple(securityContext.getUserPrincipal().getName());
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Account entity) {
        confirm(id);
        entity.setId(id);
        entity.setPrinciple(getPrinciple());
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        confirm(id);
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Account find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Account> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Account> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private void confirm(Long id) {
        Account oldAccount = getEntityManager().find(Account.class, id);
        String principle = getPrinciple();
        assert oldAccount.getPrinciple().equals(principle);        
    }

    private String getPrinciple() {
        return securityContext.getUserPrincipal().getName();
    }
    
}
