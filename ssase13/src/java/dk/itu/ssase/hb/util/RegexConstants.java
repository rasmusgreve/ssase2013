/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.util;

/**
 *
 * @author cly-vs
 */
public class RegexConstants {
    public static final String STRING_REGEX = "^\\w+$";
    public static final String WORDS_REGEX = "^(\\w+\\s?)+$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$";
}
