<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
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
       
        <div class ="form">        
            <form action="ApplyUpdateEvenimente">
                <label for="Nume">Nume</label><br>
                <input type="text" id="Nume" name="Nume" placeholder="Nume"><br>
                <label for="prenume">Descriere</label><br>
                <input type="text" name="Descriere" placeholder="Descriere"><br>
 
                <label for="CategorieEveniment">Categorie Eveniment</label><br>
                <select name="CategorieEveniment" id="CategorieEveniment">
                <%
                    PrintWriter print = response.getWriter();
                    Database db = new Database();
                    
                    String row_id = request.getParameter("row_id");
                    String tableName = "Evenimente";
                    try { 
                        Connection con = db.makeConnection();
              
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT IDCatEveniment, Nume FROM CategoriiEvenimente");
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int colCount = rsmd.getColumnCount();

                        while(rs.next()){ %>
                            <option value="<%=rs.getString("IDCatEveniment")%>"><%=rs.getString("Nume")%></option>
                <%}
                        con.close();  
                    }     
                    catch(Exception e) {  
                        out.println("Error:" + e);  
                    }%>
                </select>
                <br><br>
                
                <label for="Plata">Plata</label><br>
                <input type="text" name="Plata" placeholder="Plata"><br>
                
                <label for="Localitate">Localitate</label><br>
                <input type="text" name="Localitate" placeholder="Localitate"><br>
                
                <label for="Tara">Tara</label><br>
                <input type="text" name="Tara" placeholder="Tara"><br>
                
                <label for="DataInceperii">Data Inceperii</label><br>
                <input type="text" name="DataInceperii" placeholder="Data Inceperii"><br>
                
                <label for="Durata">Durata(zile)</label><br>
                <input type="text" name="Durata" placeholder="Durata"><br>
                
                <label for="Organizator">Organizator</label><br>
                <input type="text" name="Organizator" placeholder="Organizator"><br>
                
               
                
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
