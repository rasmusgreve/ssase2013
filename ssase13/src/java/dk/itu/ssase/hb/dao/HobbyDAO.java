/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.dao;

import dk.itu.ssase.hb.beans.model.Hobby;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author cly-vs
 */
public class HobbyDAO {
    public Hobby findHobby(String name) {
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Hobby hobby = (Hobby) session.createQuery("SELECT h FROM Hobby h where h.type LIKE :type").uniqueResult();
        session.close();
        
        return hobby;
    }
}
