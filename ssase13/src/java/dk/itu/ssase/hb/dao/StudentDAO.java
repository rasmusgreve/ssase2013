/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.dao;

import dk.itu.ssase.hb.beans.model.Hobby;
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
    
    public static String FILTER_STUDENTS = "s.isadmin = false AND s.issuspended = false";
    
    public Number getStudentCount() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        return (Number)session.createQuery("SELECT COUNT(s) FROM Student s").uniqueResult();
    }
    
    public List<Student> findAllStudents(int limit, int offset) {    
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("SELECT s FROM Student s WHERE "+FILTER_STUDENTS)
                .setFirstResult(offset).setMaxResults(limit).list();
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
            
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Found relationship with: {0}", view.getName());
        }
        for (Relationship relationship : relas2) {
            StudentView view = StudentViewGeneratorUtil.createStudentView(userId, relationship);
            users.add(view);
            
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Found relationship with: {0}", view.getName());
        }        
        session.close();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Found {0} friends for user with id: {1}", new Integer[]{users.size(),userId});
        return users;
    }
    
    public List<Hobby> findHobbies(int userId) {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        List<Hobby> hobbies = session.createQuery("SELECT h FROM Interest i JOIN i.student s JOIN i.hobby h WHERE s.id = :id").setInteger("id", userId).list();
        session.close();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Found {0} hobbies for user with id: {1}", new Integer[]{hobbies.size(),userId});
        return hobbies;
    }
    
    public Student findStudent(int userId) {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Student user = (Student)session.createQuery("SELECT s FROM Student s WHERE s.id = :id AND "+FILTER_STUDENTS).setInteger("id", userId).uniqueResult();
        session.close();
        return user;
    }
    
    
    public Student findStudent(String userHandle) {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Student user = (Student)session.createQuery("SELECT s FROM Student s WHERE s.handle = :handle AND "+FILTER_STUDENTS).setString("handle", userHandle).uniqueResult();
        session.close();
        return user;
    }
}
