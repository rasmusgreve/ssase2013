/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Relationship;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.util.PasswordUtil;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;

/**
 *
 * @author christian
 */
public class StudentBean {

    public static final String USER_SESSION_KEY = "user";
    
    private String username;
    private String password;

    public List<Student> getUsers() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        //Transaction transaction = session.beginTransaction();
        //transaction.begin();
        List<Student> students = session.createQuery("SELECT s FROM Student s").list();
        session.close();
        return students;

    }

    public List<Relationship> getRelationships() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        //Transaction transaction = session.beginTransaction();
        //transaction.begin();
        List<Relationship> students = session.createQuery("SELECT r FROM Relationship r").list();
        session.close();
        return students;

    }

    // Validate the user
    public String validateUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Student student = (Student)session.createQuery("select s from Student s where s.name = :username").setText("username", username).uniqueResult();
        if(student!=null) {
            String encodedPassword = PasswordUtil.hashPassword(password, student.getSalt());
                Logger.getLogger("dk.itu.ssase").log(Level.INFO, username + " password " + "\n"+encodedPassword+"\n"+student.getPassword());
            if(encodedPassword.equals(student.getPassword())) {      
                context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, student);
                
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

        /**
     * <p>When invoked, it will invalidate the user's session
     * and move them to the login view.</p>
     *
     * @return <code>login</code>
     */
    public String logout() {
        HttpSession session = (HttpSession)
             FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";
        
    }
    
    public String addFriend() {
        return "fail";
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
}
