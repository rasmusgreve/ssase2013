/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.dto;

import dk.itu.ssase.hb.beans.model.Hobby;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class StudentDTO {
    public String handle;
    public List<String> hobbies = new ArrayList<String>();
    public List<String> friends = new ArrayList<String>();
    public List<String> romances = new ArrayList<String>();
    public String profile;
}
