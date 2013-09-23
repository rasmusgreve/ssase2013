/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

/**
 *
 * @author christian
 */
public class Relationship {
    private int id;
    private Student student1;
    private Student student2;
    private String type;

    /**
     * @return the student1
     */
    public Student getStudent1() {
        return student1;
    }

    /**
     * @param student1 the student1 to set
     */
    public void setStudent1(Student student1) {
        this.student1 = student1;
    }

    /**
     * @return the student2
     */
    public Student getStudent2() {
        return student2;
    }

    /**
     * @param student2 the student2 to set
     */
    public void setStudent2(Student student2) {
        this.student2 = student2;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

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
    
}
