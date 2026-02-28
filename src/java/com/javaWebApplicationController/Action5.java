package com.javaWebApplicationController;

import com.javaWebApplication.Model.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Action5", urlPatterns = {"/Action5"})
public class Action5 extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Action5.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");         
            out.println("<link href=\"tableQuery.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("<title>ModeleInaltime</title>");            
            out.println("</head>");
            out.println("<body>");
            
            Database db = new Database();    
         
            try (Connection con = db.makeConnection();
                   PreparedStatement ps = con.prepareStatement("Select M.Nume, M.Prenume, DATEDIFF(day,M.DataNasterii, GETDATE())/365 as Varsta, "
                           + "M.Sex, M.Nationalitate, M.CuloarePar as [Culoare Par], M.CuloareOchi as [Culoare Ochi], "
                           + "C.Nume as Inaltime from Modele M "
                           + "JOIN CategoriiInaltimi C on C.IDCatInaltime = M.IDCatInaltime "
                           + "WHERE M.Nume + ' ' + M.Prenume = ?")){ 
                
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();

                ResultSetMetaData rsmd = rs.getMetaData();
                int colCount = rsmd.getColumnCount();
                
                out.println("<div class=\"text\">");
                
                while(rs.next()){
                    for (int i = 1; i <= colCount; i++){
                        out.println("<p>" + rsmd.getColumnName(i) + ": " + rs.getString(rsmd.getColumnName(i)) + "");
                    }
                }
                
                out.println("</div>");
            
            }
            catch (SQLException e) {  
                logger.log(Level.SEVERE, "Error: ", e.getMessage());  
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
