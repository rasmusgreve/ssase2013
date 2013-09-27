/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cly
 */
public class PasswordUtil {
    
    public static String hashPassword(String password, String salt) {
        try {
            String passwordSalt = password+salt;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(passwordSalt.getBytes());
     
            byte byteData[] = md.digest();
     
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
             sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String generateSalt() {
        char[] salt = new char[4];
        for (int i = 0; i < 4; i++) {
            salt[i] = getRandomChar();
        }
        return new String(salt);
    }

    
    	/* Generate a random character between fromChar and toChar */
	// Beginning of char getRandomChar(char, char)
	public static char getRandomChar(char fromChar, char toChar) {

		// Get the Unicode of the character
		int unicode = fromChar + (int)((toChar - fromChar + 1) * Math.random());

		// Return the character
		return (char)unicode;
	
	} // End of char getRandomChar(char, char)

	/* Generate a random character */
	// Beginning of char getRandomChar()
	public static char getRandomChar() {
		
		return getRandomChar('\u0000', '\uFFFF');
	
	} // of char getRandomChar()
    
}
