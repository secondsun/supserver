/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.List;
import javax.ejb.Stateless;
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
import org.jboss.aerogear.sup.supserver.vo.Person;

/**
 *
 * @author summers
 */
@Stateless
@Path("person")
public class PersonFacadeREST extends AbstractFacade<Person> {
    @PersistenceContext(unitName = "org.jboss.aerogear.sup_supserver_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    public PersonFacadeREST() {
        super(Person.class);
    }


    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Person find(@PathParam("id") Long id) {
        return super.find(id);
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
    
}
