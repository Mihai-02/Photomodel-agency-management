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
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        <div class="bottom_right form">
            <h3> Advanced </h3>
            <form action="Display">
                <select name="table">
                    <option value="Modele">Modele</option>
                    <option value="Impresari">Impresari</option>
                    <option value="Evenimente">Evenimente</option>
                    <option value="CategoriiEvenimente">Categorii Evenimente</option>
                    <option value="CategoriiInaltimi">Categorii Inaltimi</option>
                    <option value="ModeleEvenimente">Modele-Evenimente</option>
                </select>
                <input type="submit" value="Show Table">
            </form>
        </div>
        
        <div class="form">
            <div class="box">
                <p>
                <form action="InformatiiModele.jsp">
                    <input type="submit" value="Informatii despre modele">
                </form>
                <p>
                <form action="InformatiiImpresari.jsp">
                    <input type="submit" value="Informatii despre impresari">
                </form>
                <p>
                <form action="DisponibilitateModele.jsp">
                    <input type="submit" value="Informatii disponibilitate modele">
                </form>
                </div>
            </div>     
    </body>
</html>
