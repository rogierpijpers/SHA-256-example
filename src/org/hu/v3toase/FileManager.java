/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hu.v3toase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rogier
 */
public class FileManager {
    private static final String CSV_SEPARATOR = ",";
    
    public static Map<String, String> importHashTableFromCSV(String csvFile){
        Map<String, String> dictionary = new HashMap<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(CSV_SEPARATOR);    
                
                // skipping values[0], as this is the index
                // dictionary is filled with hash as key, for easy lookup
                dictionary.put(values[2], values[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}
