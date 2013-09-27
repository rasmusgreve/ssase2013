/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.util.PasswordUtil;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.util.MD5Digest;

/**
 *
 * @author cly
 */
public class CreateStudentBean {
    private String username;
    private String password;
    private Date date;
    private String address;
    private String email;
    private Boolean admin;
    
    
    
    public String createUser() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        
        Transaction txt = session.beginTransaction();
        
        try {
            session.createQuery("SELECT s FROM student s where s.name = :name").setString(":name", username).uniqueResult();
                        
            Student student = new Student();
            student.setName(getUsername());

            student.setBirthdate(date);
            student.setSalt(PasswordUtil.generateSalt());        
            student.setPassword(new String(MD5Digest.encode(getUsername().getBytes(), getPassword().getBytes(), student.getSalt().getBytes())));
            student.setAddress(getAddress());
            student.setEmail(getEmail());
            student.setIsadmin(getAdmin());
            student.setPrivacy("lala");
            session.save(student);
            txt.commit();
            session.flush();
            session.close();
            return "create";
        } catch(HibernateException ex) {
            return "fail";
        }         
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the admin
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
