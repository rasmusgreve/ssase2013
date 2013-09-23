/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.util;

import dk.itu.ssase.hb.beans.Relationship;
import dk.itu.ssase.hb.beans.Student;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        return students;
        
    }
    
    public List<Relationship> getRelationships() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        //Transaction transaction = session.beginTransaction();
        //transaction.begin();
        List<Relationship> students = session.createQuery("SELECT r FROM Relationship r").list();
        return students;
        
    }
}
