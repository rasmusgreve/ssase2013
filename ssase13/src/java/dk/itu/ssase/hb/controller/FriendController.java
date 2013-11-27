/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.controller;

import dk.itu.ssase.hb.beans.LoginBean;
import dk.itu.ssase.hb.beans.model.Hug;
import dk.itu.ssase.hb.beans.model.RelaType;
import dk.itu.ssase.hb.beans.model.Relationship;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.dao.DAOFactory;
import dk.itu.ssase.hb.dao.StudentDAO;
import dk.itu.ssase.hb.model.StudentView;
import dk.itu.ssase.hb.model.UserSession;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import dk.itu.ssase.hb.util.StudentViewGeneratorUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author christian
 */
public class FriendController {
    private StudentDAO studentDAO;
    
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public FriendController() {
        studentDAO = DAOFactory.createStudentDTO();
    }
    
    
    
    public Collection<Student> findNewFriends() {
        TreeMap<Integer, Student> studentsTree = new TreeMap<Integer, Student>();
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        List<Student> students, students2, students3;
        
        if (currentSession != null){
            students = session.createQuery("SELECT s FROM Student s WHERE s.id != :currentstudent AND s.isadmin = false").setInteger("currentstudent", currentSession.getStudentId()).list();
            students2 = session.createQuery("SELECT s2 FROM Relationship r JOIN r.student1 s1 JOIN r.student2 s2 WHERE s1.id = :currentstudent").setInteger("currentstudent", currentSession.getStudentId()).list();
            students3 = session.createQuery("SELECT s1 FROM Relationship r JOIN r.student1 s1 JOIN r.student2 s2 WHERE s2.id = :currentstudent").setInteger("currentstudent", currentSession.getStudentId()).list();
        }
        else //Not logged in
        {
            students = session.createQuery("SELECT s FROM Student s WHERE s.isadmin = false").list();
            students2 = new ArrayList<Student>(0);
            students3 = new ArrayList<Student>(0);
        }
        
        for (Student student : students) {
            studentsTree.put(student.getId(), student);
        }
        
        for (Student student : students2) {
            studentsTree.remove(student.getId());
        }
        
        for (Student student : students3) {
            studentsTree.remove(student.getId());
        }
        logger.log(Level.INFO, "Search for possible friends and found {0} results", studentsTree.size());
        session.close();
        return studentsTree.values();
    }
    
    public List<StudentView> findFriends() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        return studentDAO.findFriends(currentSession.getStudentId());
    }
    
    public String hugFriend(int friendId) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Hug hug = new Hug();
            hug.setStudent1(currentSession.getStudentId());
            hug.setStudent2(friendId);
            java.util.Date now = java.util.Calendar.getInstance().getTime();
            java.util.Date tomorrow = new java.util.Date(now.getTime() + 86400000);
            hug.setExpiration(tomorrow);
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
    

    
    /**
     * Find all the requests for relationships to the current student
     * @return List of students with relationshsip id
     */
    public List<StudentView> findUnapprovedRelationshipRequests() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        Session session = StudentHibernateUtil.getSessionFactory().openSession();        
        
        List<Relationship> relas = session.createQuery("SELECT r FROM Relationship r JOIN r.student1 s1 JOIN r.student2 s2 WHERE s2.id = :currentstudent AND r.approved = false").setInteger("currentstudent", currentSession.getStudentId()).list();         
 
        int currentUserId = currentSession.getStudentId();
        List<StudentView> users = new ArrayList<StudentView>();
        for (Relationship relationship : relas) {
            StudentView view = StudentViewGeneratorUtil.createStudentView(currentUserId, relationship);
            users.add(view);
            logger.log(Level.INFO, "Found friend request from: {0}", view.getName());
        }
        session.close();
        return users;
    }
    
    /**
     * Find all the requests for relationships from the current student
     * @return List of students with relationshsip id
     */
    public List<StudentView> findUnapprovedSendRelationshipRequests() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        Session session = StudentHibernateUtil.getSessionFactory().openSession();        
        
        List<Relationship> relas = session.createQuery("SELECT r FROM Relationship r JOIN r.student1 s1 JOIN r.student2 s2 WHERE s1.id = :currentstudent AND r.approved = false").setInteger("currentstudent", currentSession.getStudentId()).list();         
 
        int currentUserId = currentSession.getStudentId();
        List<StudentView> users = new ArrayList<StudentView>();
        for (Relationship relationship : relas) {
            StudentView view = StudentViewGeneratorUtil.createStudentView(currentUserId, relationship);
            users.add(view);
            logger.log(Level.INFO, "Found friend request from: {0}", view.getName());
        }
        session.close();
        return users;
    }
    
    /**
     * Request a relationship
     * @param userId
     * @return 
     */
    public String requestRelationship(int userId) {
        logger.log(Level.INFO, "Add relationship to student id: {0}", userId);
        Relationship relationship = new Relationship();
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);


        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Student student1 = (Student) session.get(Student.class, currentSession.getStudentId());
            Student student2 = (Student) session.get(Student.class, userId);
            relationship.setStudent1(student1);
            relationship.setStudent2(student2);
            relationship.setApproved(Boolean.FALSE);
            //TODO change to dynamic
            relationship.setType(RelaType.friend);
            session.save(relationship);
            tx.commit();
        } catch(Exception ex) {
            if(tx!=null)
                tx.rollback();
            logger.log(Level.SEVERE, "Requesting relationship failed because: {0}", ex.getMessage());
        } finally {
            session.close();
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Created relationship request with id: {0}", relationship.getId());
        return "success";
    }
    
    public String deleteRequest(int relaId) {
        logger.log(Level.INFO, "Delete relationship id: {0}", relaId);

        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            Relationship relationship = (Relationship) session.load(Relationship.class, relaId);
            
            session.delete(relationship);
            tx.commit();
        } catch(Exception ex) {
            if(tx!=null)
                tx.rollback();
            logger.log(Level.SEVERE, "Deleting relationship failed because: {0}", ex.getMessage());
        } finally {
            session.close();
        }
        return "success";
    }
    
    public String approveFriend(int relaId) {
        logger.log(Level.INFO, "Approve relationship id: {0}", relaId);

        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            Relationship relationship = (Relationship) session.load(Relationship.class, relaId);
            
            relationship.setApproved(Boolean.TRUE);
            
            session.saveOrUpdate(relationship);
            tx.commit();
        } catch(Exception ex) {
            if(tx!=null)
                tx.rollback();
            logger.log(Level.SEVERE, "Approving relationship failed because: {0}", ex.getMessage());
        } finally {
            session.close();
        }
        return "success";
    }
}