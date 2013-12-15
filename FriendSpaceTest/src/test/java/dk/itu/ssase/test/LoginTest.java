package dk.itu.ssase.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.By;

/**
 * Unit test for simple App.
 */
public class LoginTest 
    extends TestCase
{
    String urlPage = "http://localhost:8080/ssase13/f/login.xhtml";
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LoginTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( LoginTest.class );
    }
    
    public void testLoginSQLInjection()
    {
        open(urlPage);
        $(By.id("login:username")).setValue("';DROP TABLE student; --");
        $(By.id("login:password")).setValue("';DROP TABLE student; --");
        $(By.id("login:submit")).click();
        $(By.id("loginmess")).shouldHave(text("Login Failed!")); // Waits until element gets text
        assertTrue( true );
    }
    
    public void testLoginXSS()
    {
        open(urlPage);
        $(By.id("login:username")).setValue("<script>alert();</script>");
        $(By.id("login:submit")).click();
        $(By.id("loginmess")).shouldHave(text("Login Failed!")); // Waits until element gets text
        assertTrue( true );
    }
    
    public void testLoginFail1()
    {
        open(urlPage);
        $(By.id("login:username")).setValue("unknown");
        $(By.id("login:password")).setValue("testTEST123");
        $(By.id("login:submit")).click();
        assertFalse((title().equals("My profile")));
    }
    
    public void testLoginFail2()
    {
        open(urlPage);
        $(By.id("login:username")).setValue("rasmusgreve");
        $(By.id("login:password")).setValue("wrongpassword");
        $(By.id("login:submit")).click();
        assertFalse((title().equals("My profile")));
    }
    
    public void testLoginSuccess()
    {
        open(urlPage);
        $(By.id("login:username")).setValue("rasmusgreve");
        $(By.id("login:password")).setValue("testTEST123");
        $(By.id("login:submit")).click();
        assertTrue((title().equals("My profile")));
    }
}
