/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.itu.ssase.test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.By;

/**
 *
 * @author greve
 */
public class CreateUserTest  extends TestCase
{
    String userEditPage = "http://localhost:8080/ssase13/f/userEdit.xhtml";
    String loginPage = "http://localhost:8080/ssase13/f/login.xhtml";
    String signUpPage = "http://localhost:8080/ssase13/f/signup.xhtml";
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CreateUserTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( dk.itu.ssase.test.CreateUserTest.class );
    }
    
    public void testSignupLink(){
        open(loginPage);
        $(By.linkText("Sign up")).click();
        assertTrue(title().equals("Create user"));
    }
    
    public void testCaptchaInvalid()
    {
        open(signUpPage);
        $(By.id("create:name")).val("test");
        $(By.id("create:surname")).val("test");
        $(By.id("create:handle")).val("rasmusgreve");
        $(By.id("create:password")).val("testTEST123");
        $(By.id("create:address")).val("test");
        $(By.id("recaptcha_response_field")).val("test");
        $(By.id("create:submit")).click();
        assertTrue(title().equals("Create user"));
    }
    
    public void testPasswordInvalid()
    {
        open(signUpPage);
        $(By.id("create:name")).val("test");
        $(By.id("create:surname")).val("test");
        $(By.id("create:handle")).val("rasmusgreve");
        $(By.id("create:password")).val("test"); //Must have uppercase and num
        $(By.id("create:address")).val("test");
        $(By.id("recaptcha_response_field")).val("test");
        $(By.id("create:submit")).click();
        assertTrue(title().equals("Create user"));
    }
    
    
    
}

