/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.util;

import dk.itu.ssase.hb.beans.model.RelaType;
import dk.itu.ssase.hb.beans.model.Relationship;
import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.model.StudentView;
import java.util.Iterator;

/**
 *
 * @author christian
 */
public class StudentViewGeneratorUtil {
    
    public static Relationship areUsersFriends(int currentUserId, Student student) {
        Iterator<Relationship> iter = student.getRelationshipsForStudent1().iterator();
            Relationship rela;
            while(iter.hasNext()) {
                rela = iter.next();
                if(currentUserId == rela.getStudent2().getId()) {
                    return rela;
                }
            }
            Iterator<Relationship> iter2 = student.getRelationshipsForStudent2().iterator();
            Relationship rela2;
            while(iter2.hasNext()) {
                rela2 = iter2.next();
                if(currentUserId == rela2.getStudent1().getId()) {
                    return rela2;
                }
            }
        return null;
    }
    
    public static StudentView createStudentView(int currentUserId, Relationship rela) {
            StudentView view = new StudentView();
        if(rela.getStudent1().getId()==currentUserId) {
            Student student = rela.getStudent2();
            view.setId(rela.getId());
            view.setName(student.getHandle());
        } else if(rela.getStudent2().getId()==currentUserId) {
            Student student = rela.getStudent1();
            view.setId(rela.getId());
            view.setName(student.getHandle());
        }   
        view.setFriend(rela.getApproved());
        view.setRelatype(rela.getType());
        return view;
    }
}
