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
        <title>Informatii despre modele</title>
    </head>
    <body>
        <h1>Informatii despre modele</h1>
        <div class="form">
            <div class="box">
                <form action="Action5">
                    <select name="name">
                    <% 
                        Database db = new Database();
                        try { 
                            Connection con = db.makeConnection();
              
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT * from Modele");
                        while(rs.next()){ %>
                        <option value="<%=rs.getString("Nume") + ' ' + rs.getString("Prenume")%>"><%=rs.getString("Nume") + ' ' + rs.getString("Prenume")%></option>
                    
                    <%}%>
                    </select>
                    <input type="submit" value="Informatii generale despre modelul selectat">
                </form>
                <p>
                <form action="Action1">
                    <input type="submit" value="Afiseaza modelele si numele evenimentelor la care participa">
                </form>
                <p>
                <form action="Action2">
                    <label for="plata">Plata</label>
                    <input type="number" id="plata" name="plata" min="0" value="0">
                    <select name="comp" id="comp">
                        <option value="<">Mai putin decat</option>
                        <option value="=">Egala cu</option>
                        <option value=">">Mai mult decat</option>
                    </select>
                    <input type="submit" value="Afiseaza modelele ale caror salariu respecta conditia">
                </form>
                <p>
                <form action="ComplexAction4">
                    <select name="culoarePar">
                    <% rs = stmt.executeQuery("SELECT DISTINCT CuloarePar from Modele");
                        while(rs.next()){ %>
                        <option value="<%=rs.getString("CuloarePar")%>"><%=rs.getString("CuloarePar")%></option>
                    
                    <%}%>
                    </select>
                    <%
                    con.close();  
                        }     
                        catch(Exception e) {  
                            out.println("Error:" + e);  
                        }%>
                    <input type="submit" value="Afiseaza evenimentele la care participa modele cu culoarea parului selectata">
                </form>
            </div>
        </div>
    </body>
</html>
