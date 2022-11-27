/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        FileReader reader = null;
        try {
            File configFile = new File("settings/Configurations.properties");
            reader = new FileReader(configFile);
            Properties propertyFile = new Properties();
            propertyFile.load(reader);
            String host = propertyFile.getProperty("host");
            String user = propertyFile.getProperty("user");
            System.out.println("Host is :" + host);
            System.out.println("User Name is :" + user);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
