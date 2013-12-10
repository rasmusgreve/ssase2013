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
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.By;

/**
 *
 * @author greve
 */
public class FriendRequestTest extends TestCase {

    String urlPage = "http://localhost:8080/ssase13/api/";
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FriendRequestTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FriendRequestTest.class );
    }
    
    public void testFriendRequestsEtc()
    {
        //Login as rasmusgreve
        //open friends.xhtml
        //Assert chr friend
        
        //Press Remove friendship
        //assert no friends
        
        //open userList
        //Press request friendship (the right one)
        //Assert no longer on list
        
        //open friendRequests
        //Assert chr on "Your friend requests" list
        
        //logout
        //Login as chr
        //open friendRequests
        //Assert rasmusgreve on "Friend requests" list
        
        //Press Accept friendship (the right one)
        //open friends.xhtml
        //Assert rasmusgreve on list
        
        //logout
        //Login as rasmusgreve
        //open friends.xhtml
        //Assert chr on list
        
    }
    
    public void testRomanceFriendship()
    {
        //login
        //open friends.xhtml
        //press start romance
        //assert romance
        
        //press end romance
        //assert friends
    }
    
}
