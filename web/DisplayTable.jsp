<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.javaWebApplication.Model.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! int i; %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="table.css" rel="stylesheet" type="text/css">
        <title>DisplayTable</title>
    </head>
    <body>
        <table border=1>
            <%
                Database db = new Database();
                String tableName = request.getParameter("table_name");
                
                try (Connection con = db.makeConnection();
                    Statement stmt = con.createStatement()){ 
             
                    ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int colCount = rsmd.getColumnCount();
                
                %>
            <tr>
                <% if("ModeleEvenimente".equals(tableName)){%>
                    <th> <%= rsmd.getColumnName(1) %> </th>
                <%}%>
                <%for (i = 2; i <= colCount; i++){%>
                 <th> <%= rsmd.getColumnName(i) %> </th>
                <%}%>
                <% if("Modele".equals(tableName) || "Evenimente".equals(tableName)){ %>
                 <th></th>
                 <th></th>
                <%}%>
            </tr>
            <% while(rs.next()){ %>
            <tr>
                <% if("ModeleEvenimente".equals(tableName)){%>
                    <td> <%=rs.getInt(rsmd.getColumnName(1))%></td>
                <%}%>
                <% for(i = 2; i <= colCount; i++){ 
                    if("int".equals(rsmd.getColumnTypeName(i).toLowerCase())) {%>
                        <td> <%=rs.getInt(rsmd.getColumnName(i))%></td>    
                    <%}else if("numeric".equals(rsmd.getColumnTypeName(i).toLowerCase())){%>
                        <td> <%=rs.getFloat(rsmd.getColumnName(i))%></td>  
                    <%}else{%>
                        <td> <%=rs.getString(rsmd.getColumnName(i))%></td>  
                 <%}}%>
                <% if("Modele".equals(tableName) || "Evenimente".equals(tableName)){ %>
                 <td>
                    <form action="Delete">
                        <input type="hidden" name="id" value="<%=rs.getInt(rsmd.getColumnName(1))%>"/>
                        <input type="hidden" name="table" value="<%=tableName%>"/>
                        <input type="submit" value="Delete">
                  </form>
                </td>
                <td>
                    <form action="Update">
                        <input type="hidden" name="id" value="<%=rs.getInt(rsmd.getColumnName(1))%>"/>
                        <input type="hidden" name="table" value="<%=tableName%>"/>
                        <input type="hidden" name="action" value="Update"/>
                        <input type="submit" value="Modify">
                   </form>
                </td>
                <%}%>
            </tr>
            <%}%>        
            <%  
            }  
            catch(Exception e) {  
                e.printStackTrace();
            } %>
        </table>
        <% if("Modele".equals(tableName) || "Evenimente".equals(tableName)){ %>
        <form action="Update">
           <input type="hidden" name="table" value="<%=tableName%>"/>
           <input type="hidden" name="action" value="Insert"/>
           <input type="submit" value="Insert">
        </form>
        <%}%>
    </body>
</html>    
                
                
             