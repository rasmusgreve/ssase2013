/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.model;

import dk.itu.ssase.hb.beans.model.RelaType;
import dk.itu.ssase.hb.beans.model.Relationship;
import dk.itu.ssase.hb.beans.model.Student;

/**
 *
 * @author christian
 */
public class StudentView {
    private int id;
    private String name;
    private Student student;
    private boolean friend;
    private RelaType relatype;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the friend
     */
    public boolean isFriend() {
        return friend;
    }

    /**
     * @param friend the friend to set
     */
    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    /**
     * @return the relatype
     */
    public RelaType getRelatype() {
        return relatype;
    }

    /**
     * @param relatype the relatype to set
     */
    public void setRelatype(RelaType relatype) {
        this.relatype = relatype;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

}
