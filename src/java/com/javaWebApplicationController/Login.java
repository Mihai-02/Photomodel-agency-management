package com.javaWebApplicationController;

import com.javaWebApplication.Model.DataHandler;
import com.javaWebApplication.bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        String username =  request.getParameter("username");
        String password = request.getParameter("password");
        
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        DataHandler dh = new DataHandler();
        
        String state = dh.readData(newUser);
        
        if(state.equalsIgnoreCase("success")){
            response.sendRedirect("Dashboard.jsp");
        }
        else {
            response.sendRedirect("LoginPage.jsp?error=1");
            out.println("Username sau parola gresita");
        }   
    }
}
