package com.javaWebApplication.Model;

import com.javaWebApplication.bean.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DataHandler {
    private static final Logger logger = Logger.getLogger(DataHandler.class.getName());
    
    public void writeData(User user) {
        Database db = new Database();
        Connection con = db.makeConnection();
        
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Users (Nume, Prenume, Username, Password, [E-mail], Telefon)"
                                            + "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, user.getNume());
            stmt.setString(2, user.getPrenume());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPhone());
            stmt.executeUpdate();
            
        }catch(SQLException e){
            logger.log(Level.SEVERE, "Error: ", e);
        }
    }
    
    public String readData(User user) {        
        Database db = new Database();
        Connection con = db.makeConnection();
        ResultSet rez;
        
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT Username, Password from Users "
                    + "WHERE Username=? AND Password=?");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            rez = stmt.executeQuery();
            
            if (rez.next()) {
                return "success";
            }
            
        }catch(SQLException e){
            logger.log(Level.SEVERE, "Error: ", e);
        }

        return "fail";
    } 
    
}


