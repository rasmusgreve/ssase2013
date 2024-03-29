package dk.itu.ssase.hb.beans.model;
// Generated 29-11-2013 11:17:13 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * AlienUser generated by hbm2java
 */
public class AlienUser  implements java.io.Serializable {


     private int id;
     private String name;
     private String username;
     private String country;
     private String hobbies;
     private String profile;
     private Set alienRelationsForAlien1 = new HashSet(0);
     private Set alienRelationsForAlien2 = new HashSet(0);

    public AlienUser() {
    }

	
    public AlienUser(int id) {
        this.id = id;
    }
    public AlienUser(int id, String name, String username, String country, String hobbies, String profile, Set alienRelationsForAlien1, Set alienRelationsForAlien2) {
       this.id = id;
       this.name = name;
       this.username = username;
       this.country = country;
       this.hobbies = hobbies;
       this.profile = profile;
       this.alienRelationsForAlien1 = alienRelationsForAlien1;
       this.alienRelationsForAlien2 = alienRelationsForAlien2;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getHobbies() {
        return this.hobbies;
    }
    
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
    public String getProfile() {
        return this.profile;
    }
    
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public Set getAlienRelationsForAlien1() {
        return this.alienRelationsForAlien1;
    }
    
    public void setAlienRelationsForAlien1(Set alienRelationsForAlien1) {
        this.alienRelationsForAlien1 = alienRelationsForAlien1;
    }
    public Set getAlienRelationsForAlien2() {
        return this.alienRelationsForAlien2;
    }
    
    public void setAlienRelationsForAlien2(Set alienRelationsForAlien2) {
        this.alienRelationsForAlien2 = alienRelationsForAlien2;
    }




}


