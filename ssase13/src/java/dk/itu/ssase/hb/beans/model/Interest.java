package dk.itu.ssase.hb.beans.model;
// Generated 03-10-2013 19:21:56 by Hibernate Tools 3.2.1.GA



/**
 * Interest generated by hbm2java
 */
public class Interest  implements java.io.Serializable {


     private int id;
     private Student student;
     private Hobby hobby;

    public Interest() {
    }

    public Interest(int id, Student student, Hobby hobby) {
       this.id = id;
       this.student = student;
       this.hobby = hobby;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    public Hobby getHobby() {
        return this.hobby;
    }
    
    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }




}

