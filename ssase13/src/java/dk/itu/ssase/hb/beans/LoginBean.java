/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.model.UserSession;
import dk.itu.ssase.hb.util.PasswordUtil;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;

/**
 *
 * @author cly-vs
 */
public class LoginBean {
    
    public static final String USER_SESSION_KEY = "user";
    
    private String username;
    private String password;
    
    private boolean validated = false;

    // Validate the user
    public String validateUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Student student = (Student)session.createQuery("select s from Student s where s.handle = :username").setText("username", getUsername()).uniqueResult();
        if(student!=null) {
            String encodedPassword = PasswordUtil.hashPassword(getPassword(), student.getSalt());
            if(encodedPassword.equals(student.getPassword())) {                    
                UserSession user = new UserSession();
                user.setStudentId(student.getId());
                user.setAdmin(student.getIsadmin());
                context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, user);
                setValidated(true);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Authenticated user "+student.getName());           
                return "success";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Failed!",
                        "error");
                context.addMessage(null, message);
                return null;
            }
        } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Failed!",
                        "error");
                context.addMessage(null, message);
                return null;
            }
    }

    public String logout() {
        HttpSession session = (HttpSession)
             FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "logout";
        
    }
    
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the validated
     */
    public boolean isValidated() {
        return validated;
    }

    /**
     * @param validated the validated to set
     */
    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
