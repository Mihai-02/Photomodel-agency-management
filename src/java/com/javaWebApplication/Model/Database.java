package com.javaWebApplication.Model;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Database {
        private static final Logger logger = Logger.getLogger(Database.class.getName());
        private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PASSWORD;
        
        static {
            Properties credentials = new Properties();
            String url = null;
            String user = null;
            String pass = null;
            
            System.out.println(Database.class.getClassLoader().getResource("."));
            
            try(InputStream inStream = Database.class.getClassLoader().getResourceAsStream("credentials.properties")){
                credentials.load(inStream);
                url = credentials.getProperty("db.url");
                user = credentials.getProperty("db.user");
                pass = credentials.getProperty("db.password");
            }
            catch (IOException e){
                logger.log(Level.SEVERE, "Nu s-a putut realiza o conexiune cu baza de date: fisier credentiale negasit");
            }
            DB_URL = url;
            DB_USER = user;
            DB_PASSWORD = pass;
        }
        
        Connection con;
			
        public Connection makeConnection() {
            try{
		Class.forName(DB_DRIVER);
            } catch(ClassNotFoundException e) {
                logger.log(Level.SEVERE, "Eroare incarcare driver!");
            }
            try{
                con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch(SQLException e) {
                logger.log(Level.SEVERE, "Conexiune esuata!");
            }
            return con;
        }
}


