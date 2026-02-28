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
        <title>Disponibilitate modele</title>
    </head>
    <body>
        <h1>Disponibilitate modele</h1>
        <div class="form">
            <div class="box">
                <form action="ComplexAction2">
                    <input type="submit" value="Afiseaza evenimentele la care inca nu participa nici un model">
                </form>
        
                <%Database db = new Database();
                 try { 
                    Connection con = db.makeConnection();
                    Statement stmt = con.createStatement();
                    ResultSet rs;
                %>
                <p><br>
                <form action="ComplexAction1">
                    <select name="Categorie">
                    <% rs = stmt.executeQuery("SELECT Nume, LimitaInferioara from CategoriiInaltimi ORDER BY LimitaInferioara");
                        while(rs.next()){ %>
                            <option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
                        <%}%>
                    </select>            
                    <p>
                    <select name="comp" id="comp">
                        <option value="<">Mai mica</option>
                        <option value="=">Egala</option>
                        <option value=">">Mai mare</option>
                    </select>
                    <br><br>
                    <input type="submit" value="Afiseaza modelele a caror inaltime respecta conditia">
                </form>
                <p><br>
                <form action="ComplexAction3">
                    <select name="name">
                        <% rs = stmt.executeQuery("SELECT Nume + ' ' + Prenume from Modele");
                            while(rs.next()){ %>
                            <option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
                        <%}%>
                 </select>
                    <%       con.close();  
                            }     
                            catch(Exception e) {  
                                out.println("Error:" + e);  
                            }%>
                    <input type="date" id="start" name="date" value="2023-01-20" min="2018-01-01" max="2029-12-31">
                    <input type="submit" value="Verifica disponibilitate model la data introdusa">
                </form>
            </div>
        </div>
        
        
        
        
    </body>
</html>
