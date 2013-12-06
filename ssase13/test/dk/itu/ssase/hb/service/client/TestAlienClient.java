/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.service.client;

import dk.itu.ssase.hb.dto.alien.UserDTO;
import dk.itu.ssase.hb.dto.alien.UserListDTO;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 *
 * @author cly-vs
 */
public class TestAlienClient  extends TestCase {
    public TestAlienClient() {
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void testAlienClient() {
        AlienClient alienClient = new AlienClient();
        UserListDTO users = alienClient.getData("https://192.237.201.172/ssase13/api/users/0",UserListDTO.class);
        Assert.assertEquals(users.usernames.size(),10);
    }
    
    public void testAlienClient2() {
        AlienClient alienClient = new AlienClient();
        UserDTO user = alienClient.getData("https://192.237.201.172/ssase13/api/user/test",UserDTO.class);
        Assert.assertEquals(user.name,"test testy");
    }
}
