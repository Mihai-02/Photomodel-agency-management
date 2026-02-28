package com.javaWebApplicationController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Update", urlPatterns = {"/Update"})
public class Update extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String tableName = request.getParameter("table");
        
        if("Update".equals(action)){
           String row_id = request.getParameter("id");
            
            if("Modele".equals(tableName))
                response.sendRedirect("UpdateModele.jsp?action=" + action + "&row_id=" + row_id  + "&data_error=0" + "&CNP_error=0" + "&inaltime_error=0");
            else if("Evenimente".equals(tableName))
                response.sendRedirect("UpdateEvenimente.jsp?action=" + action + "&row_id=" + row_id);
            
        }else if("Insert".equals(action)){
            if("Modele".equals(tableName))
                response.sendRedirect("UpdateModele.jsp?action=" + action + "&data_error=0" + "&CNP_error=0" + "&inaltime_error=0");
            else if("Evenimente".equals(tableName))
                response.sendRedirect("UpdateEvenimente.jsp?action=" + action);
        }
        
    }
}
