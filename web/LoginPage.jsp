<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="index.css" rel="stylesheet" type="text/css">
        <title>Login</title>
    </head>
    <body>
        <div class="bannerimage"></div>
        <h1 align = "center" style="font-family:helvetica; color:white;">Login</h1>
        <% if("1".equals(request.getParameter("error"))) { %>
            <h2 align="center" style="font-family:helvetica; color:red;">Username sau parola gresita!</h2>
        <%}%>
        <div class="form">
            <div class="box">
                <form action="Login" method="POST">
                    <label for="username">Username</label><br>
                    <input type="text" name="username" placeholder="Username"><br>
                    <label for="password">Parola</label><br>
                    <input type="password" name="password" placeholder="Parola"><br>
                    <input type="submit" value="Login">
                </form>
            </div>
        </div>
        
        <p>
        <div class="form">
            <form action="index.html">
                <input type="submit" value="Inregistrare">
            </form>
        </div>
        
    </body>
</html>
