/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.dao;

import dk.itu.ssase.hb.beans.model.Relationship;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.model.StudentView;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import dk.itu.ssase.hb.util.StudentViewGeneratorUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author cly-vs
 */
public class StudentDAO {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    public List<Student> findAllStudents() {
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("SELECT s FROM Student s").list();
        session.close();
        
        return students;
    }
    
        
    public List<StudentView> findFriends(int userId) {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();        
        
        List<Relationship> relas = session.createQuery("SELECT r FROM Relationship r JOIN r.student2 s2 WHERE s2.id = :currentstudent AND r.approved = true").setInteger("currentstudent", userId).list();         
        List<Relationship> relas2 = session.createQuery("SELECT r FROM Relationship r JOIN r.student1 s1 WHERE s1.id = :currentstudent AND r.approved = true").setInteger("currentstudent", userId).list();         
        
        List<StudentView> users = new ArrayList<StudentView>();
        for (Relationship relationship : relas) {
            StudentView view = StudentViewGeneratorUtil.createStudentView(userId, relationship);
            users.add(view);
            
            logger.log(Level.INFO, "Found relationship with: {0}", view.getName());
        }
        for (Relationship relationship : relas2) {
            StudentView view = StudentViewGeneratorUtil.createStudentView(userId, relationship);
            users.add(view);
            
            logger.log(Level.INFO, "Found relationship with: {0}", view.getName());
        }
        
        session.close();
        return users;
    }
}
