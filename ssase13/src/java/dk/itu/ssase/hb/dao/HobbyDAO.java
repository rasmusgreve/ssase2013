/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.dao;

import dk.itu.ssase.hb.beans.model.Hobby;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author cly-vs
 */
public class HobbyDAO {
    public Hobby findHobby(int hobbyId) {
        
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Hobby hobby = (Hobby) session.createQuery("SELECT h FROM Hobby h where h.id = :id").setInteger("id", hobbyId).uniqueResult();
        session.close();
        
        return hobby;
    }
}
