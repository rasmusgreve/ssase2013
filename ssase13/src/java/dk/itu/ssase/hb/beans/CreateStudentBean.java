/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Privacy;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.util.PasswordUtil;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author cly
 */
public class CreateStudentBean {

    private Student studentInput;

    public CreateStudentBean() {
        studentInput = new Student();
    }

    public String createUser() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();

        Transaction txt = session.beginTransaction();
        txt.begin();
        try {
            Student existingStudent = (Student) session.createQuery("select s from Student s where s.name = :username").setString("username", getStudentInput().getName()).uniqueResult();
            if (existingStudent != null) {
                return "fail";
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Query failed with exception " + ex.getMessage());
            session = StudentHibernateUtil.getSessionFactory().openSession();
        }

        Student studentToSave = getStudentInput();

        String salt = PasswordUtil.generateSalt();
        studentToSave.setSalt(salt);
        studentToSave.setPassword(PasswordUtil.hashPassword(getStudentInput().getPassword(), salt));


        session.save(studentToSave);
        txt.commit();
        
        session.close();
        return "success";
    }

    public Privacy[] getPrivacyOptions() {
        return Privacy.values();
    }

    /**
     * @return the studentInput
     */
    public Student getStudentInput() {
        return studentInput;
    }

    /**
     * @param studentInput the studentInput to set
     */
    public void setStudentInput(Student studentInput) {
        this.studentInput = studentInput;
    }
}
