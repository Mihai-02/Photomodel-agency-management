//=====================================================================
//             UPDATE(Modify, Insert) PT TABELA "EVENIMENTE"
//=====================================================================

package com.javaWebApplicationController;

import com.javaWebApplication.Model.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ApplyUpdateEvenimente", urlPatterns = {"/ApplyUpdateEvenimente"})
public class ApplyUpdateEvenimente extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ApplyUpdateEvenimente.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");        
        String tableName = request.getParameter("table_name");
        
        int row_id = 0;
        if("Update".equals(action))
            row_id = Integer.parseInt( request.getParameter("row_id") );
        
        String nume = request.getParameter("Nume");
        String descriere = request.getParameter("Descriere");
        int idCategorieEveniment = Integer.parseInt( request.getParameter("CategorieEveniment") );
        float plata = Float.parseFloat( request.getParameter("Plata") );
        String localitate = request.getParameter("Localitate");
        String tara = request.getParameter("Tara");
        String dataInceperii = request.getParameter("DataInceperii");
        int durata = Integer.parseInt( request.getParameter("Durata") );
        String organizator = request.getParameter("Organizator");
        
        Database db = new Database();          
     
        try (Connection con = db.makeConnection();
                Statement stmt = con.createStatement()){ 

            if("Update".equals(action))
                stmt.executeUpdate("UPDATE Evenimente " +
                               "SET Nume=\'" + nume +  "\', Descriere=\'" + descriere +
                                "\', IDCatEveniment=" + idCategorieEveniment + ", Plata=\'" + plata + 
                                "\', Localitate=\'" + localitate + "\', Tara=\'" + tara + 
                                "\', Datainceperii=\'" + dataInceperii + "\', [Durata(zile)]=" + durata + 
                                ", Organizator=\'" + organizator +
                                "\' WHERE IDEveniment=" + row_id);
            else if(("Insert".equals(action)))
                stmt.executeUpdate("INSERT INTO Evenimente " +
                               "VALUES (\'" + nume +  "\', \'" + descriere +
                                "\', " + idCategorieEveniment + ", \'" + plata + 
                                "\', \'" + localitate + "\', \'" + tara + 
                                "\', \'" + dataInceperii + "\', " + durata + 
                                ", \'" + organizator + "\');");
        }     
        catch (SQLException e) {  
            logger.log(Level.SEVERE, "Error: ", e.getMessage());  
        }
        
        response.sendRedirect("DisplayTable.jsp?table_name=" + tableName);
    }

}

