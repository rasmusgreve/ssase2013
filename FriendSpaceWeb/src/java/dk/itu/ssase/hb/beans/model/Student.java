package dk.itu.ssase.hb.beans.model;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author greve
 */
public class Student implements Serializable {
    private int id;
    private String name, password, address, email, salt;
    private Privacy privacy;
    private boolean isadmin;
    private Date birthdate;

     private Set interests = new HashSet(0);
     private Set relationshipsForStudent2 = new HashSet(0);
     private Set relationshipsForStudent1 = new HashSet(0);

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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the privacy
     */
    public Privacy getPrivacy() {
        return privacy;
    }

    /**
     * @param privacy the privacy to set
     */
    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the isadmin
     */
    public boolean isIsadmin() {
        return isadmin;
    }

    /**
     * @param isadmin the isadmin to set
     */
    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    /**
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the interests
     */
    public Set getInterests() {
        return interests;
    }

    /**
     * @param interests the interests to set
     */
    public void setInterests(Set interests) {
        this.interests = interests;
    }

    /**
     * @return the relationshipsForStudent2
     */
    public Set getRelationshipsForStudent2() {
        return relationshipsForStudent2;
    }

    /**
     * @param relationshipsForStudent2 the relationshipsForStudent2 to set
     */
    public void setRelationshipsForStudent2(Set relationshipsForStudent2) {
        this.relationshipsForStudent2 = relationshipsForStudent2;
    }

    /**
     * @return the relationshipsForStudent1
     */
    public Set getRelationshipsForStudent1() {
        return relationshipsForStudent1;
    }

    /**
     * @param relationshipsForStudent1 the relationshipsForStudent1 to set
     */
    public void setRelationshipsForStudent1(Set relationshipsForStudent1) {
        this.relationshipsForStudent1 = relationshipsForStudent1;
    }
        
}

