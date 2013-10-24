/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Hobby;
import dk.itu.ssase.hb.beans.model.Interest;
import dk.itu.ssase.hb.beans.model.Relationship;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.model.StudentView;
import dk.itu.ssase.hb.model.UserSession;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
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
    
    private int hobby;

    public List<Student> getUsers() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("SELECT s FROM Student s").list();
        session.close();
        return students;
    }
    
    public List<StudentView> findNewUsers() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        //List<Student> students = session.createQuery("SELECT s FROM Student s JOIN s.relationshipsForStudent1 r1 JOIN r1.student2 s2 WHERE s.id != :currentstudent AND s2.id != :currentstudent").setInteger("currentstudent", currentSession.getStudentId()).list();
        List<Student> students = session.createQuery("SELECT s FROM Student s WHERE s.id != :currentstudent").setInteger("currentstudent", currentSession.getStudentId()).list();
        List<StudentView> users = new ArrayList<StudentView>();
        for (Student student : students) {
            StudentView view = new StudentView();
            view.setId(student.getId());
            view.setName(student.getName());
            Iterator<Relationship> iter = student.getRelationshipsForStudent1().iterator();
            Relationship rela;
            while(iter.hasNext()) {
                rela = iter.next();
                if(currentSession.getStudentId() == rela.getStudent2().getId()) {
                    view.setFriend(true);
                }
            }
            Iterator<Relationship> iter2 = student.getRelationshipsForStudent2().iterator();
            Relationship rela2;
            while(iter2.hasNext()) {
                rela2 = iter2.next();
                if(currentSession.getStudentId() == rela2.getStudent1().getId()) {
                    view.setFriend(true);
                }
            }
            
            users.add(view);
        }
        
        //List<Student> students = session.createQuery("SELECT s FROM Student s RIGHT OUTER JOIN s.relationshipsForStudent2 r WHERE r IS NULL OR r.student1.id != :currentstudent").setInteger("currentstudent", currentSession.getStudentId()).list();
        session.close();
        return users;
    }
    
    public List<StudentView> findUsers() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<StudentView> users = new ArrayList<StudentView>();
        List<Student> students = session.createQuery("SELECT s FROM Student s").list();        
        Student currentStudent = (Student) session.get(Student.class,currentSession.getStudentId());
        currentStudent.getRelationshipsForStudent1();
        for (Student student : students) {
            StudentView view = new StudentView();
            view.setId(student.getId());
            view.setName(student.getName());
        }
        session.close();
        return users;
    }
    
    public String addHobby() {
        
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Hobby id: " + hobby);
        FacesContext context = FacesContext.getCurrentInstance();
        UserSession currentSession = (UserSession) context.getExternalContext().getSessionMap().get(LoginBean.USER_SESSION_KEY);


        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        tx.begin();
        
        Interest interest = new Interest();
        interest.setHobby((Hobby) session.get(Hobby.class, hobby));
        interest.setStudent((Student) session.get(Student.class,currentSession.getStudentId()));
        session.save(interest);
        
        tx.commit();
        session.close();
        return "success";
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

        
        return hobbies;
    }

    public List<Relationship> getRelationships() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        //Transaction transaction = session.beginTransaction();
        //transaction.begin();
        List<Relationship> students = session.createQuery("SELECT r FROM Relationship r").list();
        session.close();
        return students;
    }

    public List<Relationship> getRelationships(Student student) {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<Relationship> rel = session.createQuery(
                "SELECT r FROM Relationship r WHERE r.student1 = " + student.getId()
                + " OR r.student2 = " + student.getId()).list();
        session.close();
        return rel;
    }

    public String addFriend(int userId) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Student id: " + userId);
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
}
