/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.util;

/**
 *
 * @author cly
 */
public class PasswordUtil {
    
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
