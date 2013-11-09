/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.util.PasswordUtil;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

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

        
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
             
            Student studentToSave = getStudentInput();

            String salt = PasswordUtil.generateSalt();
            studentToSave.setSalt(salt);
            studentToSave.setPassword(PasswordUtil.hashPassword(getStudentInput().getPassword(), salt));
       
            if(studentToSave.getIsadmin()==null)
                studentToSave.setIsadmin(Boolean.FALSE);
            
            session.save(studentToSave);
            tx.commit();          
            session.close();      
        } catch(ConstraintViolationException ex) {
            FacesContext.getCurrentInstance().addMessage("createuser:handle", new FacesMessage("Handle exists"));
            if(tx!=null)
                tx.rollback();
            session.close();
            return "fail";
        
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Query failed with exception " + ex.getMessage());
            if(tx!=null)
                tx.rollback();
            session.close();
            return "fail";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User created"));
        return "success";
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
