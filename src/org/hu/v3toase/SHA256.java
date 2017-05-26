/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hu.v3toase;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Rogier
 */
public class SHA256 {
    private static final Random RANDOM = new SecureRandom();
    
    public static String createRandomSalt(){
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return DatatypeConverter.printBase64Binary(salt);
    }
    
    public static String hash(String plainText, String salt) throws NoSuchAlgorithmException{
        String saltedPlainText = salt + plainText;
        
        return hash(saltedPlainText);
    }
    
    public static String hash(String plainText) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printBase64Binary(hash);
    }
}
