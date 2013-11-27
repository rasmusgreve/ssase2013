/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.dao;

/**
 *
 * @author cly-vs
 */
public class DAOFactory {
    
    public static StudentDAO createStudentDTO() {
        return new StudentDAO();
    }
    
    
    public static HobbyDAO createHobbyDTO() {
        return new HobbyDAO();
    }
}
