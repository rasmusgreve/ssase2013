/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.model;

/**
 *
 * @author christian
 */
public class StudentView {
    private int id;
    private String name;
    private boolean friend;

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
     * @return the friend
     */
    public boolean isFriend() {
        return friend;
    }

    /**
     * @param friend the friend to set
     */
    public void setFriend(boolean friend) {
        this.friend = friend;
    }
}
