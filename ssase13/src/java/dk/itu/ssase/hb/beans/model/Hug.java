package dk.itu.ssase.hb.beans.model;
// Generated Nov 1, 2013 2:16:58 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Hug generated by hbm2java
 */
public class Hug  implements java.io.Serializable {


     private int id;
     private int student1;
     private int student2;
     private Date expiration;
     private Boolean mutual;

    public Hug() {
    }

	
    public Hug(int id, int student1, int student2) {
        this.id = id;
        this.student1 = student1;
        this.student2 = student2;
    }
    public Hug(int id, int student1, int student2, Date expiration, Boolean mutual) {
       this.id = id;
       this.student1 = student1;
       this.student2 = student2;
       this.expiration = expiration;
       this.mutual = mutual;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getStudent1() {
        return this.student1;
    }
    
    public void setStudent1(int student1) {
        this.student1 = student1;
    }
    public int getStudent2() {
        return this.student2;
    }
    
    public void setStudent2(int student2) {
        this.student2 = student2;
    }
    public Date getExpiration() {
        return this.expiration;
    }
    
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    public Boolean getMutual() {
        return this.mutual;
    }
    
    public void setMutual(Boolean mutual) {
        this.mutual = mutual;
    }




}


