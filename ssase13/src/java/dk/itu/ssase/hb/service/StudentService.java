/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
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
        //Session session = StudentHibernateUtil.getSessionFactory().openSession();
        //List<Student> students = session.createQuery("SELECT s FROM Student s").list();
        //session.close();
        List<String> strings = new ArrayList<String>();
        //Iterator<Student> iter = students.iterator();
        //while (iter.hasNext()) {
        //    Student student = iter.next();
        //    strings.add(student.getName());
        //}
        
        strings.add("yada");
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        
        

        //JSONWriter writer = new JSONWriter();
        
        return gson.toJson(strings);
    }
    
    
}
