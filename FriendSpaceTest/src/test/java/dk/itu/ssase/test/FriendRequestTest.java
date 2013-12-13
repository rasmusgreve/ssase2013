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
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.By;

/**
 *
 * @author greve
 */
public class FriendRequestTest extends TestCase {

    String loginPage = "http://localhost:8080/ssase13/f/login.xhtml";
    String friendsPage = "http://localhost:8080/ssase13/f/friends.xhtml";
    String userListPage = "http://localhost:8080/ssase13/f/userList.xhtml";
    String friendRequestsPage = "http://localhost:8080/ssase13/f/friendRequests.xhtml";
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
    
    private void login(String username)
    {
        open(loginPage);
        $(By.id("login:username")).setValue(username);
        $(By.id("login:password")).setValue("hiei4e9fuF6G");
        $(By.id("login:submit")).click();
    }
    
    
    public void testFriendRequestsEtc()
    {
        login("rasmusgreve");
        open(friendsPage);
        assertTrue($(By.linkText("chr")).exists());
        
        $(By.linkText("Remove friendship")).click();
        assertFalse($(By.linkText("chr")).exists());
        
        open(userListPage);
        $(By.linkText("Request friendship"),0).click();
        assertFalse($(By.linkText("chr")).exists());
        
        open(friendRequestsPage);
        assertTrue($(By.linkText("chr")).exists());
        
        getWebDriver().manage().deleteAllCookies();
        login("chr");
        open(friendRequestsPage);
        assertTrue($(By.linkText("rasmusgreve")).exists());
        
        $(By.linkText("Accept friendship")).click();
        open(friendsPage);
        assertTrue($(By.linkText("rasmusgreve")).exists());
        
        getWebDriver().manage().deleteAllCookies();
        login("rasmusgreve");
        open(friendsPage);
        assertTrue($(By.linkText("chr")).exists());
        
    }
    
    public void testRomanceFriendship()
    {
        login("rasmusgreve");
        open(friendsPage);
        $(By.linkText("Start romance")).click();
        String source = getWebDriver().getPageSource();
        assertTrue(source.contains("<td>romance</td>"));
        assertFalse(source.contains("<td>friend</td>"));
        
        $(By.linkText("End romance")).click();
        source = getWebDriver().getPageSource();
        assertFalse(source.contains("<td>romance</td>"));
        assertTrue(source.contains("<td>friend</td>"));
        
    }
    
}
