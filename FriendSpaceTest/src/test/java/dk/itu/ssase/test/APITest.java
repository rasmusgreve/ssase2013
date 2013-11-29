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
        open(urlPage + "users");
        //TODO: Parse json?
        assertTrue(false);
    }
}
