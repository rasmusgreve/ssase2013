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
public class APITest extends TestCase {
    
    String urlPage = "http://localhost:8080/ssase13/api/";
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public APITest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( APITest.class );
    }
    
    public void testUsersList()
    {
        getWebDriver().manage().deleteAllCookies(); //Shouldn't be logged in
        open(urlPage + "users");
        String content = getWebDriver().getPageSource();
        assertTrue(content.startsWith("{")); //Object start
        assertTrue(content.endsWith("}")); //Object end
        assertTrue(content.contains("\"list\":[")); //must have a list
    }
    
    public void testUser()
    {
        getWebDriver().manage().deleteAllCookies(); //Shouldn't be logged in
        open(urlPage + "users/rasmusgreve");
        String content = getWebDriver().getPageSource();
        assertTrue(content.startsWith("{")); //Object start
        assertTrue(content.endsWith("}")); //Object end
        assertTrue(content.contains("\"handle\":\"rasmusgreve\""));
    }
    
    public void testHobbies()
    {
        getWebDriver().manage().deleteAllCookies(); //Shouldn't be logged in
        open(urlPage + "hobbies/1");
        String content = getWebDriver().getPageSource();
        assertTrue(content.startsWith("{")); //Object start
        assertTrue(content.endsWith("}")); //Object end
        assertTrue(content.contains("\"id\":"));
        assertTrue(content.contains("\"type\":"));
    }
}
