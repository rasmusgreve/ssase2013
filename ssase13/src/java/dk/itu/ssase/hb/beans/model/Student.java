package dk.itu.ssase.hb.beans.model;
// Generated Nov 1, 2013 2:16:58 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Student generated by hbm2java
 */
public class Student  implements java.io.Serializable {


     private int id;
     private String name;
     private String surname;
     private String handle;
     private String address;
     private String email;
     private String password;
     private Privacy privacy;
     private String salt;
     private Boolean isadmin;
     private Set interests = new HashSet(0);
     private Set relationshipsForStudent2 = new HashSet(0);
     private Set relationshipsForStudent1 = new HashSet(0);

    public Student() {
    }

	
    public Student(int id) {
        this.id = id;
    }
    public Student(int id, String name, String surname, String handle, String address, String email, String password, Privacy privacy, String salt, Boolean isadmin, Set interests, Set relationshipsForStudent2, Set relationshipsForStudent1) {
       this.id = id;
       this.name = name;
       this.surname = surname;
       this.handle = handle;
       this.address = address;
       this.email = email;
       this.password = password;
       this.privacy = privacy;
       this.salt = salt;
       this.isadmin = isadmin;
       this.interests = interests;
       this.relationshipsForStudent2 = relationshipsForStudent2;
       this.relationshipsForStudent1 = relationshipsForStudent1;
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
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getHandle() {
        return this.handle;
    }
    
    public void setHandle(String handle) {
        this.handle = handle;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Privacy getPrivacy() {
        return this.privacy;
    }
    
    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }
    public String getSalt() {
        return this.salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public Boolean getIsadmin() {
        return this.isadmin;
    }
    
    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }
    public Set getInterests() {
        return this.interests;
    }
    
    public void setInterests(Set interests) {
        this.interests = interests;
    }
    public Set getRelationshipsForStudent2() {
        return this.relationshipsForStudent2;
    }
    
    public void setRelationshipsForStudent2(Set relationshipsForStudent2) {
        this.relationshipsForStudent2 = relationshipsForStudent2;
    }
    public Set getRelationshipsForStudent1() {
        return this.relationshipsForStudent1;
    }
    
    public void setRelationshipsForStudent1(Set relationshipsForStudent1) {
        this.relationshipsForStudent1 = relationshipsForStudent1;
    }




}


