package com.javaWebApplicationController;

import com.javaWebApplication.Model.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
        

@WebServlet(name = "Action1", urlPatterns = {"/Action1"})
public class Action1 extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Action1.class.getName());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"tableQuery.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("<title>DisplayQuery</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=1>");
            
            Database db = new Database();    
         
        try (Connection con = db.makeConnection();
             Statement stmt = con.createStatement()){ 

            ResultSet rs = stmt.executeQuery("Select M.Nume+' '+M.Prenume as 'Nume Model', E.Nume as 'Nume Eveniment'" +
            "From Modele M JOIN ModeleEvenimente ME on M.IDModel = ME.IDModel JOIN Evenimente E on E.IDEveniment = ME.IDEveniment");
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            
            int ok = 0;
            while(rs.next()){
                if(ok == 0){
                    out.println("<tr>");
                    for (int i = 1; i <= colCount; i++)
                        out.println("<th>" + rsmd.getColumnName(i) + "</th>");
                    out.println("</tr>");
                }
                ok = 1;
                out.println("<tr>");
                for(int i = 1; i <= colCount; i++)
                    out.println("<td>" + rs.getString(rsmd.getColumnName(i)) + "</td>");      
                out.println("</tr>");
            }
            if(ok == 0){
                out.println("<div class=\"text\">");
                out.println("<h3> Cererea apelata nu a returnat nici un rezultat</h3>");
                out.println("</div>");
            }    
        }
        catch (Exception e) {  
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
            out.println("</table>");
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
