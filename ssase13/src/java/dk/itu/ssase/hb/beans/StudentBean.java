/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.AlienUser;
import dk.itu.ssase.hb.beans.model.Hobby;
import dk.itu.ssase.hb.beans.model.Hug;
import dk.itu.ssase.hb.beans.model.Interest;
import dk.itu.ssase.hb.beans.model.RelaType;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.dao.DAOFactory;
import dk.itu.ssase.hb.model.StudentView;
import dk.itu.ssase.hb.model.UserSession;
import dk.itu.ssase.hb.service.client.AlienClient;
import dk.itu.ssase.hb.util.JSFActionConstants;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author christian
 */
public class StudentBean {
    
    private int hobby;
    private RelaType relatype;
    private Student currentStudent;

    public StudentBean() {
    }
    
    

    public boolean isLoggedIn()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        return (context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY) != null);
    }
    
    public boolean hasPriviliges()
    {
        boolean isSelf = false;
        boolean isFriends = false;
        if (isLoggedIn()){
            isSelf = getCurrentStudent().getId() == getUser().getId();
            Student currentStudent = getCurrentStudent();
            for (StudentView sv : DAOFactory.createStudentDAO().findFriends(getUser().getId()))
            {
                if (sv.getStudent().getId() == currentStudent.getId()) isFriends = true;
            }
        }
        return isFriends || hasAdmin() || isSelf;
    }
    
    public boolean hasAdmin() {     
        if (!isLoggedIn()) return false;
        return getCurrentStudent().getIsadmin();
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
    
    
    
    public AlienUser getAlienUser()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int userId = Integer.parseInt(request.getParameter("id"));
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        AlienUser user = (AlienUser)session.createQuery("SELECT s FROM AlienUser s WHERE s.id = :id").setInteger("id", userId).uniqueResult();
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
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Search for possible hobbies and found {0} results", remainingHobbies.size());
        return remainingHobbies;
    }
    
    public Student getCurrentStudent()
    {
        Student user;
        if(currentStudent==null) {
            FacesContext context = FacesContext.getCurrentInstance();
            UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
            int userId = currentSession.getStudentId();
            Session session = StudentHibernateUtil.getSessionFactory().openSession();
            user = (Student)session.createQuery("SELECT s FROM Student s WHERE s.id = :id").setInteger("id", userId).uniqueResult();
            currentStudent = user;
            session.close();
        }
        else
            user = currentStudent;
        return user;
    }
    
    public List<Hobby> findCurrentStudentsHobbies() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);

        return findStudentsHobbies(currentSession.getStudentId());
    }
    
    public List<Hobby> findStudentsHobbies(int userId) {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
                
        List<Hobby> hobbies = session.createQuery("SELECT h FROM Interest i JOIN i.student s JOIN i.hobby h WHERE s.id = :student").setInteger("student", userId).list();
        
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Found hobbies of the student: {0}", hobbies.size());
        
        return hobbies;
    }

    
    public String removeHobby(){
        //TODO: Please implement this
        return JSFActionConstants.JSFSuccess;
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
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Adding hobby failed because: {0}", ex.getMessage());
        } finally {
            session.close();
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "User {0} added Hobby id: {1} ", new Integer[] {currentSession.getStudentId(), hobby});
        
        return JSFActionConstants.JSFSuccess;
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
    
    public void removeCurrentInterest(int hobbyId)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        removeInterest(currentSession.getStudentId(), hobbyId);
    }
    
    public void removeInterest(int studentId, int hobbyId) {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("DELETE FROM INTEREST WHERE student = :studentid AND hobby = :hobbyid")
                    .setInteger("studentid", studentId).setInteger("hobbyid", hobbyId).executeUpdate();
            tx.commit();
        } catch(Exception ex) {            
            if(tx!=null)
                tx.rollback();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Removing hobby failed because: {0}", ex.getMessage());
        } finally {
            session.close();
        }

    }
    
    public void unsuspendUser(int userId)
    {
        setSuspension(userId,false);
    }
    
    public void suspendUser(int userId) {
        
        setSuspension(userId,true);
    }
    
    private void setSuspension(int userId, boolean suspended){
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Student student = (Student) session.load(Student.class, userId);
            student.setIssuspended(suspended);
            session.saveOrUpdate(student);
            tx.commit();
        } catch(Exception ex) {            
            if(tx!=null)
                tx.rollback();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "(un)Suspending user: {0}", ex.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * @param currentStudent the currentStudent to set
     */
    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
          
    
    
    public String saveChanges(){
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(currentStudent);
            tx.commit();
            return JSFActionConstants.JSFSuccess;
        } catch(Exception ex) {            
            if(tx!=null)
                tx.rollback();
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Save failed {0}", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Save failed"));
            return JSFActionConstants.JSFFailure;
        } finally {
            session.close();
        }
    }
 
    
    public String importStudents() {
        AlienClient alienClient = new AlienClient();
        
        alienClient.synchronizeWithDatabase();
        return JSFActionConstants.JSFSuccess;
    }
}
