/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Relationship;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author christian
 */
public class StudentBean {

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
    
    public List<Relationship> getRelationships(Student student){
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<Relationship> rel = session.createQuery(
                "SELECT r FROM Relationship r WHERE r.student1 = " + student.getId() 
                + " OR r.student2 = " + student.getId()).list();
        session.close();
        return rel;
    }
    
    public String addFriend(int userId) {
         Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Student id: "+userId);
        return "fail";
    }
    
}
