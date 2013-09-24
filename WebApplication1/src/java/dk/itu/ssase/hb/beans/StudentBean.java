/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Relationship;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.util.MD5Digest;
import org.postgresql.util.UnixCrypt;

/**
 *
 * @author christian
 */
public class StudentBean {

    private String username;
    private String password;

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

    public String validateUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Student student = (Student)session.createQuery("select s from Student s where s.name = :username").setText("username", username).uniqueResult();
        String encodedPassword = new String(MD5Digest.encode(username.getBytes(), password.getBytes(), student.getSalt().getBytes()));
        if(encodedPassword.equals(student.getPassword()))
                return "login";
        else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Failed!",
                    "Username '"
                    + username
                    +
                    "' does not exist.");
            context.addMessage(null, message);
            return null;
        }
    }
    
    public String createUser() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        
        Transaction txt = session.beginTransaction();
        Student student = new Student();
        student.setName(username);
        student.setBirthdate(new Date());
        student.setSalt(generateSalt());        
        student.setPassword(new String(MD5Digest.encode(username.getBytes(), password.getBytes(), student.getSalt().getBytes())));
        student.setAddress("1234");
        student.setEmail("1234");
        student.setIsadmin(true);
        student.setPrivacy("lala");
        session.save(student);
        txt.commit();
        session.flush();
        session.close();
        return "create";
    }

    public String generateSalt() {
        char[] salt = new char[4];
        for (int i = 0; i < 4; i++) {
            salt[i] = getRandomChar();
        }
        return new String(salt);
    }

    
    	/* Generate a random character between fromChar and toChar */
	// Beginning of char getRandomChar(char, char)
	public static char getRandomChar(char fromChar, char toChar) {

		// Get the Unicode of the character
		int unicode = fromChar + (int)((toChar - fromChar + 1) * Math.random());

		// Return the character
		return (char)unicode;
	
	} // End of char getRandomChar(char, char)

	/* Generate a random character */
	// Beginning of char getRandomChar()
	public static char getRandomChar() {
		
		return getRandomChar('\u0000', '\uFFFF');
	
	} // of char getRandomChar()
    
    
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
}
