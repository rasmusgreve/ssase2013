/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.dao;

import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author cly-vs
 */
public class StudentDAO {
    public List<Student> findAllStudents() {
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("SELECT s FROM Student s").list();
        session.close();
        
        return students;
    }
}
