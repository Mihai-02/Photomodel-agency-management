<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="index.css" rel="stylesheet" type="text/css">
        <title>Registration Complete</title>                       
    </head>
    <body>
        <h1 align = "center" style="font-family:helvetica; color:white;">Welcome, <%= request.getParameter("username") %></h1>
        <div class="form">
            <form action="LoginPage.jsp">
                <input type="submit" value="Go to Login">
            </form>
        </div>
    </body>
</html>
