//================================================================
//            UPDATE(Modify, Insert) PT TABELA "MODELE"
//================================================================

package com.javaWebApplicationController;

import com.javaWebApplication.Model.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ApplyUpdate", urlPatterns = {"/ApplyUpdate"})
public class ApplyUpdate extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ApplyUpdate.class.getName());

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
        
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String CNP = request.getParameter("CNP");
        String dataNasterii = request.getParameter("DataNasterii");
        String dataAngajarii = request.getParameter("DataAngajarii");
        String sex = request.getParameter("Sex");
        
        float inaltime = Float.parseFloat( request.getParameter("Inaltime") );
        int idCatInaltime = 1;        //completez cand verific categoria
        
        String greutate = request.getParameter("Greutate");
        String nationalitate = request.getParameter("Nationalitate");
        String culoarePar = request.getParameter("CuloarePar");
        String culoareOchi = request.getParameter("CuloareOchi");
        int idImpresar = Integer.parseInt( request.getParameter("Impresar") );
        
        int CNP_error = 0;
        int inaltime_error = 1;
        
        if(dataNasterii.compareTo(dataAngajarii) != -1){
            response.sendRedirect("UpdateModele.jsp?row_id=" + row_id + "&data_error=1" + "&CNP_error=0" + "&inaltime_error=0");
        }
        
        //PrintWriter print = response.getWriter();
        Database db = new Database();          
     
        try (Connection con = db.makeConnection();
                Statement stmt = con.createStatement()){ 
            
            ResultSet rs = stmt.executeQuery("SELECT IDModel, CNP FROM Modele");

            while(rs.next()){
                if(CNP.equals(rs.getString("CNP")) && row_id != rs.getInt("IDModel"))
                    CNP_error = 1;
            }
            
            if(CNP_error == 1)
                response.sendRedirect("UpdateModele.jsp?row_id=" + row_id + "&data_error=0" + "&CNP_error=1" + "&inaltime_error=0");
            
            rs = stmt.executeQuery("SELECT * FROM CategoriiInaltimi");
            
            while(rs.next()){
               if(inaltime >= rs.getFloat("LimitaInferioara") && inaltime <= rs.getFloat("LimitaSuperioara")){
                    idCatInaltime = rs.getInt("IDCatInaltime");
                    inaltime_error = 0;
                    break;
                }
            }
            
            if(inaltime_error == 1)
                response.sendRedirect("UpdateModele.jsp?row_id=" + row_id + "&data_error=0" + "&CNP_error=0" + "&inaltime_error=1");
            
            if("Update".equals(action))
                stmt.executeUpdate("UPDATE Modele " +
                                "SET Nume=\'" + nume +  "\', Prenume=\'" + prenume + "\', CNP=\'" + CNP + 
                                "\', DataNasterii=\'" + dataNasterii + "\', DataAngajarii=\'" + dataAngajarii + 
                                "\', Sex=\'" + sex + "\', IDCatInaltime=" + idCatInaltime + 
                                ", Greutate=\'" + greutate + "\', Nationalitate=\'" + nationalitate + 
                                "\', CuloarePar=\'" + culoarePar + "\', CuloareOchi=\'" + culoareOchi + 
                                "\', IDImpresar=" + idImpresar +
                                " WHERE IDModel=" + row_id);
            else if(("Insert".equals(action)))
                stmt.executeUpdate("INSERT INTO Modele " +
                                "VALUES (\'" + nume +  "\', \'" + prenume + "\', \'" + CNP + 
                                "\', \'" + dataNasterii + "\', \'" + dataAngajarii + 
                                "\', \'" + sex + "\', " + idCatInaltime + 
                                ", \'" + greutate + "\', \'" + nationalitate + 
                                "\',\'" + culoarePar + "\', \'" + culoareOchi + 
                                "\', " + idImpresar + ");");  
        }     
        catch (SQLException e) {  
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        
        response.sendRedirect("DisplayTable.jsp?table_name=" + tableName);
    }

}
