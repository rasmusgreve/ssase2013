/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author cly-vs
 */
@Path("users")
public class StudentService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StudentService
     */
    public StudentService() {
    }

    /**
     * Retrieves representation of an instance of dk.itu.ssase.hb.service.StudentService
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces("application/json")
//    @Path("{studentHandle}")
//    public String getJson(@PathParam(value = "studentHandle")String studentID) {
//        return "student: 'blabla'";
 //   }
    
    
    @GET
    @Produces("application/json")
    public String getJson() {
        return "student: 'blabla'";
    }
}
