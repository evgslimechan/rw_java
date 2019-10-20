<html>
    <head>
                <meta charset="utf-8">
                <link rel="stylesheet" href="css/style.css">
                <title>Moscow Raceway | Login</title>
                <div class="logo-head">
                    <img id="logo" src="css/img/logo.png"></img>
                    <a id="title" href="index.html">Administration System</a>
                    <div class="log-buttons">
                         <% 
                            if(((String)pageContext.findAttribute("name")).isEmpty()) {

                                out.println("<a href='/register' class='register'>SIGN UP</a>");
                                out.println("<span class='vertical-devider'></span>");
                                out.println("<a href='/login' class='login'>SIGN IN</a>");
                        
                            }else{
                                out.println("<a>"+pageContext.findAttribute("name")+"</a>");
                            }
                        %>
                    </div>
                </div>
    </head>
    <div class="login-box">
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
         <input type="button" value="Login">
                
    </div>
</html>