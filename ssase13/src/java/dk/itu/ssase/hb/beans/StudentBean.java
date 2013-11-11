/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Hobby;
import dk.itu.ssase.hb.beans.model.Hug;
import dk.itu.ssase.hb.beans.model.Interest;
import dk.itu.ssase.hb.beans.model.RelaType;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.model.UserSession;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author christian
 */
public class StudentBean {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private int hobby;
    private RelaType relatype;

    public List<Student> getUsers() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("SELECT s FROM Student s").list();
        session.close();
        return students;
    }
    
    
    public List<Hobby> findAvailableHobbies() {
        List<Hobby> remainingHobbies = new ArrayList<Hobby>();
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        remainingHobbies = session.createQuery("SELECT h FROM Hobby h").list();
        session.close();
        return remainingHobbies;
    }
    
    public List<Hobby> findCurrentStudentsHobbies() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);

        Session session = StudentHibernateUtil.getSessionFactory().openSession();
                
        List<Hobby> hobbies = session.createQuery("SELECT h FROM Interest i JOIN i.student s JOIN i.hobby h WHERE s.id = :student").setInteger("student", currentSession.getStudentId()).list();
        
        logger.log(Level.INFO, "Found hobbies of the student: {0}", hobbies.size());
        
        return hobbies;
    }

            
    public String addHobby() {        
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
       
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
        
            Interest interest = new Interest();
            interest.setHobby((Hobby) session.get(Hobby.class, hobby));
            interest.setStudent((Student) session.get(Student.class,currentSession.getStudentId()));
            session.save(interest);
            tx.commit();
        } catch(Exception ex) {            
            if(tx!=null)
                tx.rollback();
            logger.log(Level.SEVERE, "Adding hobby failed because: {0}", ex.getMessage());
        } finally {
            session.close();
        }

        logger.log(Level.INFO, "User {0} added Hobby id: {1} ", new int[] {currentSession.getStudentId(), hobby});
        
        return "success";
    }

    
    /**
     * @return the hobby
     */
    public int getHobby() {
        return hobby;
    }

    /**
     * @param hobby the hobby to set
     */
    public void setHobby(int hobby) {
        this.hobby = hobby;
    }
    
    public String hugFriend(int friendId) {
        // If hug entry (friend -> this) already exists:
            // Set hug to mutual.
        // Else:
            // Create new entry (this -> friend) (mutual = 0)
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Hug hug = getHug(friendId);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (hug != null && hug.getStudent1() == friendId) {
                // Hug back.
                hug.setMutual(Boolean.TRUE);
            } else {
                // New hug.
                hug = new Hug();
                hug.setStudent1(currentSession.getStudentId());
                hug.setStudent2(friendId);
                hug.setMutual(Boolean.FALSE);
                java.util.Date now = java.util.Calendar.getInstance().getTime();
                java.util.Date tomorrow = new java.util.Date(now.getTime() + 86400000);
                hug.setExpiration(tomorrow);
            }
            session.save(hug);
            tx.commit();
        } catch (Exception ex) {
            if (tx!=null)
                tx.rollback();
            logger.log(Level.SEVERE, "Adding hug failed because: {0}", ex.getMessage());
        } finally {
            session.close();
        }
        return "success";
    }
    
    public Hug getHug(int friendId) {
        // Return hug entry (friend -> this) or (this -> friend).
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Hug hug = (Hug)session.createQuery("SELECT g FROM  Hug g WHERE "
                + "(g.student1 = :me AND g.student2 = :you) OR "
                + "(g.student1 = :you AND g.student2 = :me")
                .setInteger("me", currentSession.getStudentId())
                .setInteger("you", friendId)
                .uniqueResult();
        session.close();
        return hug;
    }
    
    public void cancelHug(/*Hug hug*/) {
        // If hug satisfies (this -> friend):
            // Delete hug entry.
        // Else:
            // Set hug to NOT mutual.
    }
}
