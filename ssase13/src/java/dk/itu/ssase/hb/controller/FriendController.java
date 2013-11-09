/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.controller;

import dk.itu.ssase.hb.beans.LoginBean;
import dk.itu.ssase.hb.beans.model.RelaType;
import dk.itu.ssase.hb.beans.model.Relationship;
import dk.itu.ssase.hb.beans.model.Student;
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
    
    public Collection<Student> findNewFriends() {
        TreeMap<Integer, Student> studentsTree = new TreeMap<Integer, Student>();
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        //"SELECT r FROM Relationship r JOIN r.student2 s2 WHERE s2.id = :currentstudent AND r.approved = true"
        //List<Student> students = session.createQuery("SELECT s FROM Student s JOIN s.relationshipsForStudent1 r1 JOIN r1.student2 s2 WHERE s.id != :currentstudent AND s2.id != :currentstudent").setInteger("currentstudent", currentSession.getStudentId()).list();
        List<Student> students = session.createQuery("SELECT s FROM Student s WHERE s.id != :currentstudent AND s.isadmin = false").setInteger("currentstudent", currentSession.getStudentId()).list();
        List<Student> students2 = session.createQuery("SELECT s2 FROM Relationship r JOIN r.student1 s1 JOIN r.student2 s2 WHERE s1.id = :currentstudent").setInteger("currentstudent", currentSession.getStudentId()).list();
        List<Student> students3 = session.createQuery("SELECT s1 FROM Relationship r JOIN r.student1 s1 JOIN r.student2 s2 WHERE s2.id = :currentstudent").setInteger("currentstudent", currentSession.getStudentId()).list();
        
        for (Student student : students) {
            studentsTree.put(student.getId(), student);
        }
        
        for (Student student : students2) {
            studentsTree.remove(student.getId());
        }
        
        for (Student student : students3) {
            studentsTree.remove(student.getId());
        }
        //List<Student> students = session.createQuery("SELECT s FROM Student s RIGHT OUTER JOIN s.relationshipsForStudent2 r WHERE r IS NULL OR r.student1.id != :currentstudent").setInteger("currentstudent", currentSession.getStudentId()).list();
        session.close();
        return studentsTree.values();
    }
    
    public List<StudentView> findFriends() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        Session session = StudentHibernateUtil.getSessionFactory().openSession();        
        
        List<Relationship> relas = session.createQuery("SELECT r FROM Relationship r JOIN r.student2 s2 WHERE s2.id = :currentstudent AND r.approved = true").setInteger("currentstudent", currentSession.getStudentId()).list();         
        List<Relationship> relas2 = session.createQuery("SELECT r FROM Relationship r JOIN r.student1 s1 WHERE s1.id = :currentstudent AND r.approved = true").setInteger("currentstudent", currentSession.getStudentId()).list();         
        
        int currentUserId = currentSession.getStudentId();
        List<StudentView> users = new ArrayList<StudentView>();
        for (Relationship relationship : relas) {
            StudentView view = StudentViewGeneratorUtil.createStudentView(currentUserId, relationship);
            users.add(view);
            
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Found friend1: {0}", view.getName());
        }
        for (Relationship relationship : relas2) {
            StudentView view = StudentViewGeneratorUtil.createStudentView(currentUserId, relationship);
            users.add(view);
            
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Found friend2: {0}", view.getName());
        }
        
        session.close();
        return users;
    }
    
    
    public List<StudentView> findFriendRequests() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        Session session = StudentHibernateUtil.getSessionFactory().openSession();        
        
        List<Relationship> relas = session.createQuery("SELECT r FROM Relationship r JOIN r.student1 s1 JOIN r.student2 s2 WHERE s2.id = :currentstudent AND r.approved = false").setInteger("currentstudent", currentSession.getStudentId()).list();         
        
        //List<Student> students = session.createQuery("SELECT s FROM Student s JOIN s.relationshipsForStudent1 r1 JOIN r1.student2 s2 WHERE s2.id = :currentstudent AND r1.approved = false").setInteger("currentstudent", currentSession.getStudentId()).list();         

        int currentUserId = currentSession.getStudentId();
        List<StudentView> users = new ArrayList<StudentView>();
        for (Relationship relationship : relas) {
            StudentView view = StudentViewGeneratorUtil.createStudentView(currentUserId, relationship);
            users.add(view);
        }
        session.close();
        return users;
    }
    

    public String addFriend(int userId) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Add student as friend id: " + userId);
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
            ex.printStackTrace();
        } finally {
            session.close();
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Relationship id: " + relationship.getId());
        return "success";
    }
    
    
    public String approveFriend(int relaId) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Relationship approve id: " + relaId);
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);

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
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return "success";
    }
}