
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


@WebServlet(name = "Action4", urlPatterns = {"/Action4"})
public class Action4 extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Action4.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String numeImpresar = request.getParameter("NumeImpresar");
        
        try ( PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"tableQuery.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("<title>EvenimenteGestionate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=1>");
            
            Database db = new Database();    
         
            try (Connection con = db.makeConnection();
                    PreparedStatement ps = con.prepareStatement("Select E.Nume from Evenimente E "
                            + "JOIN CategoriiEvenimente CE on E.IDCatEveniment = CE.IDCatEveniment "
                            + "JOIN Impresari I on CE.IDResponsabil = I.IDImpresar "
                            + "WHERE I.Nume + ' ' + I.Prenume = ?")){ 
                
                ps.setString(1, numeImpresar);
                
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
