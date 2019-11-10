<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Moscow Raceway | Login</title>
        <div class="logo-head">
            <img id="logo" src="css/img/logo.png"></img>
            <a id="title" href="news">Administration System</a>
        </div>
    </head>
    <div class="login-box">
        <form:form method="POST" action="/loginUser" modelAttribute="user">
        <h1>Login</h1>
         <form:input path="login" type="text" placeholder="Input your login"/>
         <br>
         <form:input path="pass" type="password" placeholder="Input your password"/>
         <br>
         <div class="footer">
            <div><input type="checkbox"> Remember me</div>
            <div>Autologin <input type="checkbox"></div>
         </div>
         <br>
         <input type="submit" value="Login">
        </form:form>       
    </div>
</html>