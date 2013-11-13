/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import junit.framework.TestCase;
import org.openqa.selenium.By;

/**
 *
 * @author christian
 */
public class SignupTest extends TestCase {
    
    public void testSignup() {
        
        open("http://localhost:8084/ssase13/f/signup.xhtml");
        $(By.id("create:name")).setValue("test_user");
        $(By.id("create:surname")).setValue("test_user");
        $(By.id("create:handle")).setValue("test_user");
        $(By.id("create:password")).setValue("test_user");
        $(By.id("create:address")).setValue("test_user");
        $(By.id("create:submit")).click();
        $(By.id("createmess")).shouldHave(text("User created")); // Waits until element gets text
    }
}
