/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.utils;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Acer
 */
public class HelpUtls implements Serializable{
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String ALPHAUPPERCASE = ALPHA.toUpperCase(); // A-Z
    private static final String DIGITS = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = ALPHA + ALPHAUPPERCASE + DIGITS;
    
    public static boolean checkFormat(String credit, String format) {
        boolean result = true;
        boolean match;
        match = credit.matches(format);
        if (match) {
            result = false;
        }
        return result;
    }
    
    public static String randomNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, DIGITS.length() - 1);
            char ch = DIGITS.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    
    public static String encodePassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }
    
    public static long getRangeOfTwoDate (String checkInDate, String checkOutDate) {
        Date d1 = Date.valueOf(checkInDate);
        Date d2 = Date.valueOf(checkOutDate);
        
        long date =d2.getTime() - d1.getTime();
        
        return TimeUnit.MILLISECONDS.toDays(date);
    }
    
    public static Date convertLongToDate (long date, String checkInDate) {
         Date d1 = Date.valueOf(checkInDate);
         Date checkOutDate = new Date(d1.getTime() + date);
         return checkOutDate;
    }
    
    private static Random generator = new Random();

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
    
    public static String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
}
