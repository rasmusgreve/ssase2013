package entities;
// Generated 03-10-2013 19:21:56 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Student generated by hbm2java
 */
public class Student  implements java.io.Serializable {


     private int id;
     private String name;
     private String address;
     private String email;
     private String password;
     private Date birthdate;
     private Integer privacy;
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
    public Student(int id, String name, String address, String email, String password, Date birthdate, Integer privacy, String salt, Boolean isadmin, Set interests, Set relationshipsForStudent2, Set relationshipsForStudent1) {
       this.id = id;
       this.name = name;
       this.address = address;
       this.email = email;
       this.password = password;
       this.birthdate = birthdate;
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
    public Date getBirthdate() {
        return this.birthdate;
    }
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public Integer getPrivacy() {
        return this.privacy;
    }
    
    public void setPrivacy(Integer privacy) {
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


