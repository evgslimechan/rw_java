<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
                <meta charset="utf-8">
                <link rel="stylesheet" href="css/style.css">
                <title>Moscow Raceway | Login</title>
                <div class="logo-head">
                    <img id="logo" src="css/img/logo.png"></img>
                    <a id="title" href="/">Administration System</a>
                    <div class="log-buttons">
                        <a href="register.html" class="register">SIGN UP</a>
                        <span class="vertical-devider"></span>
                        <a href="/login" class="login">SIGN IN</a>
                    </div>
                </div>
    </head>
    <div class="login-box">
        <form:form method="POST" action="/api/login" modelAttribute="user">
        <h1>Login</h1>
         <input type="text" placeholder="Input your login">
         <br>
         <input type="password" placeholder="Input your password">
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