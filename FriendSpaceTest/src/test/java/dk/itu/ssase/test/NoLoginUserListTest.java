/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.By;

/**
 *
 * @author greve
 */
public class NoLoginUserListTest extends TestCase
{
    String loginPage = "http://localhost:8080/ssase13/f/login.xhtml";
    String page = "http://localhost:8080/ssase13/f/userList.xhtml";
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public NoLoginUserListTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( NoLoginUserListTest.class );
    }
    
    public void testNoLogin()
    {
        open(loginPage);
        $(By.id("login:nologin_link")).click();
        assertTrue((title().equals("User list")));
    }
    
    public void testNoFriendRequests(){
        open(page);
        //TODO: Logout if possible
        assertFalse($(By.className("befriend_button")).exists());
    }
    
    public void testNoSuspendButton(){
        open(page);
        //TODO: Logout if possible
        assertFalse($(By.className("suspend_button")).exists());
    }
    
    public void testNoUnsuspendButton(){
        open(page);
        //TODO: Logout if possible
        assertFalse($(By.className("unsuspend_button")).exists());
    }
    
    public void testGravatarAvailable(){
        open(page);
        //TODO: Logout if possible
        assertTrue($(By.className("gravatar")).exists());
    }
    
    public void testLinkAvailable(){
        open(page);
        //TODO: Logout if possible
        assertTrue($(By.className("user_link")).exists());
    }
}
