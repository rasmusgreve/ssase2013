/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.itu.ssase.hb.beans.model.Hobby;
import dk.itu.ssase.hb.beans.model.RelaType;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.dao.DAOFactory;
import dk.itu.ssase.hb.dao.StudentDAO;
import dk.itu.ssase.hb.dto.StudentDTO;
import dk.itu.ssase.hb.dto.StudentListDTO;
import dk.itu.ssase.hb.model.StudentView;
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
    private static final int PAGE_SIZE = 10;
    
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
        gson = gsonBuilder.disableHtmlEscaping().create();
        studentDAO = DAOFactory.createStudentDAO();
    }

    /**
     * Retrieves representation of an instance of dk.itu.ssase.hb.service.StudentService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("{studentHandle}")
    public String getJson(@PathParam(value = "studentHandle")String handle) {
        Student user = studentDAO.findStudent(handle);
        List<Hobby> hobbies = studentDAO.findHobbies(user.getId());
        List<StudentView> friends = studentDAO.findFriends(user.getId());
        StudentDTO dto = new StudentDTO();
        dto.handle = user.getHandle();
        for (Hobby h : hobbies)
            dto.hobbies.add("../../hobbies/" + h.getId());
        for (StudentView sv : friends)
            if (sv.getRelatype() == RelaType.friend)
                dto.friends.add("../" + sv.getStudent().getHandle());
            else if (sv.getRelatype() == RelaType.romance)
                dto.romances.add("../" + sv.getStudent().getHandle());
        dto.profile = user.getHandle();
        return gson.toJson(dto);
    }
    
    
    @GET
    @Produces("application/json")
    public String getJson() {
        String param = context.getQueryParameters().getFirst("page");
        int page = 0;
        try {
            page = Integer.parseInt(param);
        } catch (NumberFormatException e) {}
        int count = studentDAO.getStudentCount().intValue();
        List<Student> students = studentDAO.findAllStudents(PAGE_SIZE, PAGE_SIZE * page);
        StudentListDTO dto = new StudentListDTO();
        if (page > 0)
            dto.prev = "?page=" + Integer.toString(page - 1);
        if (PAGE_SIZE * (page + 1) < count)
            dto.next = "?page=" + Integer.toString(page + 1);
        for (Student s : students)
            dto.list.add(s.getHandle());
        return gson.toJson(dto);
    }
}
