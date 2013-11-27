/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.dao.DAOFactory;
import dk.itu.ssase.hb.dao.StudentDAO;
import dk.itu.ssase.hb.dto.StudentListDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author cly-vs
 */
@Path("users")
public class StudentService {
    private GsonBuilder gsonBuilder;
    private Gson gson;
    private StudentDAO studentDAO;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StudentService
     */
    public StudentService() {        
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        studentDAO = DAOFactory.createStudentDTO();
    }

    /**
     * Retrieves representation of an instance of dk.itu.ssase.hb.service.StudentService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("{studentHandle}")
    public String getJson(@PathParam(value = "studentHandle")String studentID) {
        return "student: 'blabla'";
    }
    
    
    @GET
    @Produces("application/json")
    public String getJson() {
        List<Student> students = studentDAO.findAllStudents(10, 2);
        StudentListDTO dto = new StudentListDTO();
        for (Student s : students)
            dto.list.add(s.getHandle());
        return gson.toJson(dto);
    }
}
