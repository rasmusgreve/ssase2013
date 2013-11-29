/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static junit.framework.Assert.assertTrue;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.By;

/**
 *
 * @author greve
 */
public class MyProfileTest  extends TestCase
{
    String userEditPage = "http://localhost:8080/ssase13/f/userEdit.xhtml";
    String loginPage = "http://localhost:8080/ssase13/f/login.xhtml";
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MyProfileTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MyProfileTest.class );
    }
    
    private void login()
    {
        open(loginPage);
        $(By.id("login:username")).setValue("rasmusgreve");
        $(By.id("login:password")).setValue("hiei4e9fuF6G");
        $(By.id("login:submit")).click();
    }
    
    public void testChangeName()
    {
        login();
        $(By.id("useredit:name")).setValue("");
        $(By.id("useredit:user_edit_save_button")).click();
        //TODO: Test value
        $(By.id("useredit:name")).setValue("Rasmus");
        $(By.id("useredit:user_edit_save_button")).click();
        //TODO: Test value
        assertTrue(false);
    }
    
    public void testChangeSurName()
    {
        login();
        $(By.id("useredit:surname")).setValue("");
        $(By.id("useredit:user_edit_save_button")).click();
        //TODO: Test value
        $(By.id("useredit:surname")).setValue("Greve");
        $(By.id("useredit:user_edit_save_button")).click();
        //TODO: Test value
        assertTrue(false);
    }
    
    public void testChangeAddress()
    {
        login();
        $(By.id("useredit:address")).setValue("");
        $(By.id("useredit:user_edit_save_button")).click();
        //TODO: Test value
        $(By.id("useredit:address")).setValue("Byvej 11");
        $(By.id("useredit:user_edit_save_button")).click();
        //TODO: Test value
        assertTrue(false);
    }
    
}
