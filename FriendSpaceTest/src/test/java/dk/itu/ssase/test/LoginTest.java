package dk.itu.ssase.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.By;

/**
 * Unit test for simple App.
 */
public class LoginTest 
    extends TestCase
{
    String urlPage = "http://localhost:8084/ssase13/f/login.xhtml";
    
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
    public void testLogin()
    {
        open(urlPage);
        $(By.id("login:username")).setValue("admin");
        $(By.id("login:submit")).click();
        $(By.id("loginmess")).shouldHave(text("Login Failed!")); // Waits until element gets text
        assertTrue( true );
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
        $(By.id("login:username")).setValue("<script>alert();</script>");
        $(By.id("login:submit")).click();
        $(By.id("loginmess")).shouldHave(text("Login Failed!")); // Waits until element gets text
        assertTrue( true );
    }
}
