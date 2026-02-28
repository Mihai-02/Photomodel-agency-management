<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.javaWebApplication.Model.Database"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! int i; %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dashboard.css" rel="stylesheet" type="text/css">
        <title>Update</title>
    </head>
    <body>
        <%if( Integer.parseInt( request.getParameter("data_error") ) == 1){%>
            <p>Data nasterii/angajarii nu se potrivesc</p>
        <%}%>
        <%if( Integer.parseInt( request.getParameter("CNP_error") ) == 1){%>
            <p>CNP nu este unic</p>
        <%}%>
        <%if( Integer.parseInt( request.getParameter("inaltime_error") ) == 1){%>
            <p>Inaltimea este invalida/nu se incadreaza in limite</p>
        <%}%> 
        <div class ="form">        
            <form action="ApplyUpdate">
                <label for="nume">Nume</label><br>
                <input type="text" id="nume" name="nume" placeholder="Nume"><br>
                <label for="prenume">Prenume</label><br>
                <input type="text" name="prenume" placeholder="Prenume"><br>
                <label for="CNP">CNP</label><br>
                <input type="text" name="CNP" placeholder="CNP"><br>
                <label for="DataNasterii">Data nasterii</label><br>
                <input type="text" name="DataNasterii" placeholder="Data nasterii"><br>
                <label for="DataAngajarii">Data angajarii</label><br>
                <input type="text" name="DataAngajarii" placeholder="Data angajarii"><br>
                <label for="Sex">Sex</label><br>
                <select name="Sex" id="Sex">
                    <option value="M">M</option>
                    <option value="F">F</option>
                </select><br><br>
                <label for="Inaltime">Inaltime</label><br>
                <input type="text" name="Inaltime" placeholder="Inaltime" value="1.5"><br>
                <label for="Greutate">Greutate</label><br>
                <input type="text" name="Greutate" placeholder="Greutate"><br>
                <label for="Nationalitate">Nationalitate</label><br>
                <input type="text" name="Nationalitate" placeholder="Nationalitate"><br>
                <label for="CuloarePar">Culoare par</label><br>
                <input type="text" name="CuloarePar" placeholder="Culoare par"><br>
                <label for="CuloareOchi">Culoare ochi</label><br>
                <input type="text" name="CuloareOchi" placeholder="Culoare ochi"><br>
            
                <label for="Impresar">Impresar</label><br>
                <select name="Impresar" id="Impresar">
                
                <%
                    PrintWriter print = response.getWriter();
                    Database db = new Database();
                    
                    String row_id = request.getParameter("row_id");
                    String tableName = "Modele";
                    String fullName;
                
                    try (Connection con = db.makeConnection();
                        Statement stmt = con.createStatement()){ 

                        ResultSet rs = stmt.executeQuery("SELECT Nume, Prenume, IDImpresar FROM Impresari");
                        ResultSetMetaData rsmd = rs.getMetaData();

                        while(rs.next()){ 
                            fullName = rs.getString(1) + " " + rs.getString(2); %>
                            <option value="<%=rs.getString(3)%>"><%=fullName%></option>
                        <%}
                    }     
                    catch(SQLException e) {  
                        System.out.println("Error:" + e.getMessage());  
                    }%>
                </select>
                <%if("Update".equals(request.getParameter("action"))){%>
                    <input type="hidden" name="row_id" value="<%=row_id%>"/>
                <%}%>
                <input type="hidden" name="table_name" value="<%=tableName%>"/>
                <input type="hidden" name="action" value="<%=request.getParameter("action")%>"/>
                <input type="submit" value="Apply">
                    
            </form>
                  
        </div>
        

        
                    
    </body>
</html>
