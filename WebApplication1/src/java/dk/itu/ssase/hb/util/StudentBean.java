/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.util;

import dk.itu.ssase.hb.beans.Relationship;
import dk.itu.ssase.hb.beans.Student;
import java.util.Date;
import java.util.List;
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

    public String createUser() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        
        Transaction txt = session.beginTransaction();
        //Generate salt
        //UnixCrypt.crypt(salt, original)
        Student student = new Student();
        student.setName("testuser");
        student.setBirthdate(new Date());
        student.setSalt(generateSalt());
        
        student.setPassword(new String(MD5Digest.encode("testuser".getBytes(), "password".getBytes(), student.getSalt().getBytes())));
        student.setAddress("1234");
        student.setEmail("1234");
        student.setIsadmin(true);
        student.setPrivacy("lala");
        session.save(student);
        txt.commit();
        session.flush();
        session.close();
        return "login";
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
