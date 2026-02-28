package com.javaWebApplicationController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Display", urlPatterns = {"/Display"})
public class Display extends HttpServlet {    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String tableName = request.getParameter("table");
            response.sendRedirect("DisplayTable.jsp?table_name=" + tableName);
    }
}
