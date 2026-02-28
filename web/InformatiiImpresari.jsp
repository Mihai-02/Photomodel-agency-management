<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.javaWebApplication.Model.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dashboard.css" rel="stylesheet" type="text/css">
        <title>Informatii despre impresari</title>
        </head>
        <body>
            <h1>Informatii despre impresari</h1>
         <% 
            Database db = new Database();
            try { 
                Connection con = db.makeConnection();
              
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * from Impresari"); %>
        <div class="form">
            <div class="box">
                <form action="Action6">       
                    <select name="name">
                    <%while(rs.next()){ %>
                        <option value="<%=rs.getString("Nume") + ' ' + rs.getString("Prenume")%>"><%=rs.getString("Nume") + ' ' + rs.getString("Prenume")%></option>
                    <%}%>   
                    </select>        
                    <input type="submit" value="Informatii generale despre impresarul selectat">
                </form>
                    <p>
                <form action="Action3">
                    <select name="NumeImpresar">
                        <%rs = stmt.executeQuery("SELECT Nume + ' ' + Prenume from IMPRESARI");
                         while(rs.next()){ %>
                            <option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
                        <%}%>
                    </select>
                    <input type="submit" value="Afiseaza modelele care se afla in intretinerea impresarului selectat">
                </form>
                <p>
                <form action="Action4">
                    <select name="NumeImpresar">
                    <% rs = stmt.executeQuery("SELECT Nume + ' ' + Prenume from IMPRESARI");
                       while(rs.next()){ %>
                            <option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
                     <%}%>   
                </select>
                    <input type="submit" value="Afiseaza evenimentele gestionate de impresarul selectat">
                </form>
            </div>
        </div>
        <%con.close();  
        }     
        catch(Exception e) {  
            out.println("Error:" + e);  
        }%>
    </body>
</html>
