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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {

    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws SQLException, ClassNotFoundException {
        FileReader reader = null;
        try {
            File configFile = new File("settings/Configurations.properties");
            reader = new FileReader(configFile);

            Properties propertyFile = new Properties();
            propertyFile.load(reader);

            String host = propertyFile.getProperty("host");
            String user = propertyFile.getProperty("user");
            String pasword = propertyFile.getProperty("password");
            String database = propertyFile.getProperty("database");
            String port = propertyFile.getProperty("port");
            String url = propertyFile.getProperty("url");
            String driver = propertyFile.getProperty("driver");
            String final_url = url+host+":"+port+"/"+database;

            Class.forName(driver);
            connection = DriverManager.getConnection(final_url, user, pasword);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static DBConnection getDBConnection() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }

}
