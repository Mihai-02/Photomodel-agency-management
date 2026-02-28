package com.javaWebApplicationController;

import com.javaWebApplication.Model.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Action2", urlPatterns = {"/Action2"})
public class Action2 extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Action2.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Float pay = Float.valueOf(request.getParameter("plata"));
        String operator = request.getParameter("comp");
        
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"tableQuery.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("<title>DisplayPay</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=1>");
            
            Database db = new Database();    
         
        try (Connection con = db.makeConnection();
                PreparedStatement ps = con.prepareStatement(
                "SELECT M.Nume + ' ' + M.Prenume as Nume , SUM(E.Plata) as Plata "
                        + "FROM Modele M INNER JOIN ModeleEvenimente ME on ME.IDModel = M.IDModel "
                        + "JOIN Evenimente E on ME.IDEveniment = E.IDEveniment "
                        + "GROUP BY M.IDModel, M.Nume, M.Prenume "
                        + "HAVING SUM(E.Plata) " + operator + " ?")) { 
            // check operator validity and add exception
            ps.setFloat(1, pay);
            ResultSet rs = ps.executeQuery();
            
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
        catch (SQLException e) {  
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        catch(NumberFormatException e) {
            logger.log(Level.WARNING, "Format numar invalid", e.getMessage());
//            response.sendError(400, "Invalid input");
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
