/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hu.v3toase;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 *
 * @author Rogier
 * This demonstration shows the importance of salting your passwords, by making
 * an attempt to crack the password with unsalted vs. salted.
 * 
 * The passwords are borrowed from http://www.passwordrandom.com/most-popular-passwords.
 * We've taking the first 100 values, as this should be enough to make a point, 
 * and created a .csv file containing the plain password and the SHA-256 hash.
 */
public class CrackPasswordMain {
    
    public static void main(String[] args) throws NoSuchAlgorithmException{
        String password = "batman";
        String hash = SHA256.hash(password);
     
        crackPassword(hash);
        
        // now try the same with a salted password
        String salt = SHA256.createRandomSalt();
        String saltedHash = SHA256.hash(password, salt);
        
        crackPassword(saltedHash);
        
    }
    
    public static void crackPassword(String hash) throws NoSuchAlgorithmException{        
        Map<String, String> hashTable = FileManager.importHashTableFromCSV("top_100_passwords_sha-256_hash.csv");

        String crackedPassword = hashTable.get(hash);
        
        String output = crackedPassword != null ? "Cracked Password: " + crackedPassword : "Password not found in hashtable";    
        System.out.println(output);
    }
}
