package entities;
// Generated 29-11-2013 11:17:13 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Hobby generated by hbm2java
 */
public class Hobby  implements java.io.Serializable {


     private int id;
     private String type;
     private Set interests = new HashSet(0);

    public Hobby() {
    }

	
    public Hobby(int id) {
        this.id = id;
    }
    public Hobby(int id, String type, Set interests) {
       this.id = id;
       this.type = type;
       this.interests = interests;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public Set getInterests() {
        return this.interests;
    }
    
    public void setInterests(Set interests) {
        this.interests = interests;
    }




}


