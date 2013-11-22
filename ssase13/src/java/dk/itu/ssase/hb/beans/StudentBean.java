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
import javax.servlet.http.HttpServletRequest;
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
    
    public Student getUser()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int userId = Integer.parseInt(request.getParameter("id"));
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Student user = (Student)session.createQuery("SELECT s FROM Student s WHERE s.id = :id").setInteger("id", userId).uniqueResult();
        session.close();
        return user;
    }
    
    public List<Hobby> findAvailableHobbies() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        List<Hobby> remainingHobbies = new ArrayList<Hobby>();
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        remainingHobbies = session.createQuery("SELECT h FROM Hobby h WHERE h.id NOT IN (SELECT h.id FROM Hobby h JOIN h.interests i JOIN i.student s WHERE s.id = :student)").setInteger("student", currentSession.getStudentId()).list();
        session.close();
        return remainingHobbies;
    }
    
    public List<Hobby> findCurrentStudentsHobbies() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);

        return findStudentsHobbies(currentSession.getStudentId());
    }
    
    public List<Hobby> findStudentsHobbies(int userId) {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
                
        List<Hobby> hobbies = session.createQuery("SELECT h FROM Interest i JOIN i.student s JOIN i.hobby h WHERE s.id = :student").setInteger("student", userId).list();
        
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
    
    public List<Hug> loadActivity()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        return loadActivity(currentSession.getStudentId());
    }
            
    
    public List<Hug> loadActivity(int userId) {
    
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<Hug> result = session.createQuery("SELECT g FROM Hug g WHERE "
                + "g.student1 = :me OR g.student2 = :me")
                .setInteger("me", userId)
                .list();
        session.close();
        return result;
    }
}
