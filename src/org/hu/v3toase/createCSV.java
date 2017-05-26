/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hu.v3toase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rogier
 */
public class createCSV {
    public static void main(String[] args) throws NoSuchAlgorithmException{
        List<String> hashResults = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("top_100_passwords.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] values = line.split(",");    
                
                String plainPassword = values[1];
                String hash = SHA256.hash(plainPassword);
                
                hashResults.add(values[0] + "," + plainPassword + "," + hash);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try{
            PrintWriter writer = new PrintWriter("top_100_passwords_sha-256_hash.csv", "UTF-8");
            for(String line : hashResults){
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
           // do something
        }
    }
}
