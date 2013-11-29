/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.By;

/**
 *
 * @author greve
 */
public class NoLoginProfileTest extends TestCase
{
    String userPage = "http://localhost:8080/ssase13/f/profile.xhtml?id=2";
    String listPage = "http://localhost:8080/ssase13/f/userList.xhtml";
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public NoLoginProfileTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( NoLoginProfileTest.class );
    }
    
    public void testPressUserLink()
    {
        open(listPage);
        //TODO: Logout if possible
        $(By.linkText("rasmusgreve")).click();
        assertTrue((title().equals("Profile")));
    }
    
    public void testNameHidden()
    {
        open(userPage);
        //TODO: Logout if possible
        assertTrue(false);
    }
    
    public void testAddressHidden()
    {
        open(userPage);
        //TODO: Logout if possible
        assertTrue(false);
    }
    
    public void testActivityHidden()
    {
        open(userPage);
        //TODO: Logout if possible
        assertTrue(false);
    }
    
    public void testFriendsVisibleLink()
    {
        open(userPage);
        //TODO: Logout if possible
        assertTrue(false);
    }
    
    public void testFriendsVisibleGravatar()
    {
        open(userPage);
        //TODO: Logout if possible
        assertTrue(false);
    }
    
    public void testClickFriendLink()
    {
        open(userPage);
        //TODO: Logout if possible
        assertTrue(false);
    }
    
}
