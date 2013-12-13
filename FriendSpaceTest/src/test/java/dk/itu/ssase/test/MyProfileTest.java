/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
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
        $(By.id("login:password")).setValue("testTEST123");
        $(By.id("login:submit")).click();
    }
    
    public void testChangeName()
    {
        login();
        open(userEditPage);
        $(By.id("user_edit:name")).setValue("");
        $(By.id("user_edit:user_edit_save_button")).click();
        assertTrue("".equals($(By.id("user_edit:name")).val()));
        open(userEditPage);
        $(By.id("user_edit:name")).setValue("Rasmus");
        $(By.id("user_edit:user_edit_save_button")).click();
        assertTrue("Rasmus".equals($(By.id("user_edit:name")).val()));
    }
    
    public void testXSSName()
    {
        login();
        open(userEditPage);
        String oldValue = $(By.id("user_edit:name")).val();
        $(By.id("user_edit:name")).setValue("<script>alert('xss');</script>");
        $(By.id("user_edit:user_edit_save_button")).click();
        open(userEditPage);
        assertTrue(oldValue.equals($(By.id("user_edit:name")).val()));
    }
    
    public void testChangeSurName()
    {
        login();
        open(userEditPage);
        $(By.id("user_edit:surname")).setValue("");
        $(By.id("user_edit:user_edit_save_button")).click();
        assertTrue("".equals($(By.id("user_edit:surname")).val()));
        //TODO: Test value
        open(userEditPage);
        $(By.id("user_edit:surname")).setValue("Greve");
        $(By.id("user_edit:user_edit_save_button")).click();
        assertTrue("Greve".equals($(By.id("user_edit:surname")).val()));
    }
    
    public void testXSSSurname()
    {
        login();
        open(userEditPage);
        String oldValue = $(By.id("user_edit:surname")).val();
        $(By.id("user_edit:surname")).setValue("<script>alert('xss');</script>");
        $(By.id("user_edit:user_edit_save_button")).click();
        open(userEditPage);
        assertTrue(oldValue.equals($(By.id("user_edit:surname")).val()));
    }
    
    public void testChangeAddress()
    {
        login();
        open(userEditPage);
        $(By.id("user_edit:address")).setValue("");
        $(By.id("user_edit:user_edit_save_button")).click();
        assertTrue("".equals($(By.id("user_edit:address")).val()));
        open(userEditPage);
        $(By.id("user_edit:address")).setValue("Byvej 11");
        $(By.id("user_edit:user_edit_save_button")).click();
        assertTrue("Byvej 11".equals($(By.id("user_edit:address")).val()));
    }
    
    public void testXSSAddress()
    {
        login();
        open(userEditPage);
        String oldValue = $(By.id("user_edit:address")).val();
        $(By.id("user_edit:address")).setValue("<script>alert('xss');</script>");
        $(By.id("user_edit:user_edit_save_button")).click();
        open(userEditPage);
        assertTrue(oldValue.equals($(By.id("user_edit:address")).val()));
    }
    
    public void testAddRemoveHobby()
    {
        login();
        open(userEditPage);
        assertFalse($(By.linkText("Remove hobby")).exists());
        $(By.id("add:hobby")).selectOptionByValue("1"); //Fishing
        $(By.linkText("Add hobby")).click();
        assertTrue($(By.linkText("Remove hobby")).exists());
        $(By.linkText("Remove hobby")).click();
        assertFalse($(By.linkText("Remove hobby")).exists());
    }
    
    //TODO: Injection tests
    
}
