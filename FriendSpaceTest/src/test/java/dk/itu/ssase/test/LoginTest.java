package dk.itu.ssase.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.openqa.selenium.By;

/**
 * Unit test for simple App.
 */
public class LoginTest 
    extends TestCase
{
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

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        open("http://localhost:8084/ssase13/faces/login.xhtml");
        $(By.id("login:username")).setValue("admin");
        $(By.id("login:submit")).click();
        //$(".loading_progress").should(disappear); // Waits until element disappears
        $(By.id("javax_faces_developmentstage_messages")).shouldHave(text("Login Failed!")); // Waits until element gets text
        assertTrue( true );
    }
}
