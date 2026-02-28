package com.javaWebApplicationController;

import com.javaWebApplication.Model.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ComplexAction3", urlPatterns = {"/ComplexAction3"})
public class ComplexAction3 extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ComplexAction3.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComplexAction3</title>");            
            out.println("</head>");
            out.println("<link href=\"tableQuery.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("<title>DisponibilitateModel</title>");            
            out.println("<body>");
            
            Database db = new Database();    
         
            try (Connection con = db.makeConnection();
                    PreparedStatement ps = con.prepareStatement(
                            "Select ME.IDEveniment from ModeleEvenimente ME "
                             + "JOIN Modele M on M.IDModel=ME.IDModel "
                             + "JOIN Evenimente E on E.IDEveniment=ME.IDEveniment "
                             + "WHERE M.Nume + ' ' + M.Prenume=? AND ? BETWEEN " 
                             + "(SELECT EV.Datainceperii FROM Evenimente EV WHERE EV.IDEveniment=ME.IDEveniment) "
                             + "AND (SELECT DATEADD(day, EV.[Durata(zile)], EV.Datainceperii) FROM Evenimente EV where EV.IDEveniment=ME.IDEveniment)")){ 

                ps.setString(1, name);
                ps.setString(2, date);
                
                ResultSet rs = ps.executeQuery();
                
                out.println("<div class=\"text\">");
                if(rs.next())
                    out.println(name + " NU este disponibil/a in data de " + date);
                else
                    out.println(name + " este disponibil/a in data de " + date);           
                out.println("</div>");
            }
            catch (Exception e) { 
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
