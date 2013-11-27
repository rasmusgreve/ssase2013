package dk.itu.ssase.hb.beans.model;
// Generated Nov 8, 2013 9:21:27 AM by Hibernate Tools 3.2.1.GA


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Student generated by hbm2java
 */
public class Student  implements java.io.Serializable {


     private int id;
     private String name;
     private String surname;
     private String handle;
     private String address;
     private String password;
     private String salt;
     private Boolean isadmin;
     private Boolean deleted;
     private Set interests = new HashSet(0);
     private Set relationshipsForStudent2 = new HashSet(0);
     private Set relationshipsForStudent1 = new HashSet(0);

    public Student() {
    }

	
    public Student(int id) {
        this.id = id;
    }
    public Student(int id, String name, String surname, String handle, String address, String password, String salt, Boolean isadmin, Set interests, Set relationshipsForStudent2, Set relationshipsForStudent1) {
       this.id = id;
       this.name = name;
       this.surname = surname;
       this.handle = handle;
       this.address = address;
       this.password = password;
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
    
    public String getGravatar(int size){
        String id = ""; 
        try {
             byte[] t = MessageDigest.getInstance("MD5").digest(getHandle().toLowerCase().getBytes());
             StringBuilder sb = new StringBuilder();
             for (int i = 0; i < t.length; ++i) {
               sb.append(Integer.toHexString((t[i] & 0xFF) | 0x100).substring(1,3));
             }
             id = sb.toString();
         } catch (NoSuchAlgorithmException ex) {
             Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
         }
        return "http://www.gravatar.com/avatar/" + id + "?s="+size+"&d=identicon&f=y";
    }
    
    public String getSmallGravatar(){
        return getGravatar(50);
    }
    
    public String getBigGravatar(){
        return getGravatar(200);
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
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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


    public Boolean getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


}


