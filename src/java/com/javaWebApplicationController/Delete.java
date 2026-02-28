package com.javaWebApplicationController;

import com.javaWebApplication.Model.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class Delete extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Delete.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
            
        Database db = new Database();
        String tableName = request.getParameter("table");
        int row_id = Integer.parseInt(request.getParameter("id"));
        String col_name;

        try (Connection con = db.makeConnection();
          Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            col_name = rsmd.getColumnName(1);
            
            PreparedStatement ps = con.prepareStatement("DELETE FROM " +  tableName + 
                    " WHERE " + col_name + "=?");
            ps.setInt(1, row_id);
            
            ps.executeUpdate();

            con.close(); 
        }catch(SQLException e) {  
            logger.log(Level.SEVERE, "Stergerea a esuat.", e.getMessage());
            }   
        response.sendRedirect("DisplayTable.jsp?table_name=" + tableName);
    }
}
