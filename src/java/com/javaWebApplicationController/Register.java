package com.javaWebApplicationController;

import com.javaWebApplication.Model.DataHandler;
import com.javaWebApplication.bean.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email =  request.getParameter("email");
        String phone =  request.getParameter("phonenumber");
        
        User newUser = new User();
        newUser.setNume(nume);
        newUser.setPrenume(prenume);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        
        DataHandler dh = new DataHandler();
        dh.writeData(newUser);
        
        //todo some constraints on the fields
        response.sendRedirect("RegistrationSuccess.jsp?username=" + username);
    }
}
