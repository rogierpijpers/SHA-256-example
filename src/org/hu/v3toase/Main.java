/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hu.v3toase;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author Rogier
 * This demonstration shows how to implement password authentication using
 * SHA-256 hashed and salted passwords.
 */
public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static void main(String[] args) throws NoSuchAlgorithmException{
        String password = "VeryComplicatedPassword";
        
        // generate a salt. this should be stored in the database, belonging to a specific user.
        String salt = SHA256.createRandomSalt();
        
        // hash the password with the salt. This should be stored in the database as the password
        String hashedPassword = SHA256.hash(password, salt);
        System.out.println("Salt:\t\t\t"+salt);
        System.out.println("Hashed password:\t"+hashedPassword+"\n");
        System.out.println("--------------------------------------");
        
        // We're going to exit the program by entering the correct password.
        boolean passwordVerified = false;
        Scanner scanner = new Scanner(System.in);
        
        while(!passwordVerified){
            // Read a password from the commandline  
            System.out.println("\nPlease enter password:");
            String incomingPassword = scanner.nextLine();

            // Hash the incoming password and compare to the stored value
            String hashedIncomingPassword = SHA256.hash(incomingPassword, salt);
            passwordVerified = hashedIncomingPassword.equals(hashedPassword);
            
            if(!passwordVerified)
                System.out.println(ANSI_RED + "Password incorrect." + ANSI_RESET);
        }
        
        scanner.close();
        System.out.println(ANSI_GREEN + "You are authorized!");
        System.exit(0);
    }
}
