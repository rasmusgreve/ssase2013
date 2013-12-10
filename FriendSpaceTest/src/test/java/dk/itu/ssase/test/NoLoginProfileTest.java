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
        getWebDriver().manage().deleteAllCookies();
        open(listPage);
        $(By.linkText("rasmusgreve")).click();
        assertTrue((title().equals("Profile")));
    }
    
    public void testNameHidden()
    {
        getWebDriver().manage().deleteAllCookies();
        open(userPage);
        assertTrue("".equals($(By.id("full_name")).text().trim()));
    }
    
    public void testAddressHidden()
    {
        getWebDriver().manage().deleteAllCookies();
        open(userPage);
        assertTrue("-hidden-".equals($(By.id("address")).text().trim()));
    }
    
    public void testActivityHidden()
    {
        getWebDriver().manage().deleteAllCookies();
        open(userPage);
        assertTrue("".equals($(By.id("recent_activity")).text().trim()));
    }
    
    public void testFriendsVisibleLink()
    {
        getWebDriver().manage().deleteAllCookies();
        open(userPage);
        assertTrue($(By.linkText("chr")).exists());
    }
    
    public void testClickFriendLink()
    {
        getWebDriver().manage().deleteAllCookies();
        open(userPage);
        $(By.linkText("chr")).click();
        assertTrue($(By.linkText("rasmusgreve")).exists());
    }
    
}
