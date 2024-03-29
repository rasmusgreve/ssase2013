package entities;
// Generated 29-11-2013 11:17:13 by Hibernate Tools 3.2.1.GA



/**
 * Relationship generated by hbm2java
 */
public class Relationship  implements java.io.Serializable {


     private int id;
     private Student studentByStudent2;
     private Student studentByStudent1;
     private int type;
     private Boolean approved;

    public Relationship() {
    }

	
    public Relationship(int id, Student studentByStudent2, Student studentByStudent1, int type) {
        this.id = id;
        this.studentByStudent2 = studentByStudent2;
        this.studentByStudent1 = studentByStudent1;
        this.type = type;
    }
    public Relationship(int id, Student studentByStudent2, Student studentByStudent1, int type, Boolean approved) {
       this.id = id;
       this.studentByStudent2 = studentByStudent2;
       this.studentByStudent1 = studentByStudent1;
       this.type = type;
       this.approved = approved;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Student getStudentByStudent2() {
        return this.studentByStudent2;
    }
    
    public void setStudentByStudent2(Student studentByStudent2) {
        this.studentByStudent2 = studentByStudent2;
    }
    public Student getStudentByStudent1() {
        return this.studentByStudent1;
    }
    
    public void setStudentByStudent1(Student studentByStudent1) {
        this.studentByStudent1 = studentByStudent1;
    }
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    public Boolean getApproved() {
        return this.approved;
    }
    
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }




}


