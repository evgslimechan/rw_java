<%@ page import="java.lang.String" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/style.css">

        <script src = "js/jquery-3.4.1.min.js"></script>

        <title>Moscow Raceway | Home</title>
        <div class="logo-head">
            <img id="logo" src="css/img/logo.png" oncontextmenu="return false" ondragstart="return false"></img>
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
    <body>

        <div class="main-wrapper">
            <div class="left-menu">
                <h1>MENU</h1>
                <table>
                    <tr>
                        <td class="active" onclick="menuClick(this)">News <img class="icon" src="css/img/icon/home.png" oncontextmenu="return false" ondragstart="return false"/></td>
                    </tr>
                    <tr>
                        <td onclick="menuClick(this)">Mail <img class="icon" src="css/img/icon/mail.png" oncontextmenu="return false" ondragstart="return false"/></td>
                    </tr>
                    <tr>
                        <td onclick="menuClick(this)">Tracks <img class="icon" src="css/img/icon/track.png" oncontextmenu="return false" ondragstart="return false"/></td>
                    </tr>
                    <tr>
                        <td onclick="menuClick(this)">Events <img class="icon" src="css/img/icon/calendar.png" oncontextmenu="return false" ondragstart="return false"/></td>
                    </tr>
                </table>
            </div>

            <div class="main-block">
                <div class="block-content" id="news" hidden>
                    <div class="news-row">
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>
                                             У вас загорелся CHECK
                                        </p>
                                </div>
                            </div>
                            <div class="news-block">
                                    <div class="news-info">
                                            T i t l e
                                            <p hidden>Text for news</p>
                                    </div>
                            </div>
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                </div>
                            </div>
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                </div>
                            </div>
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                </div>
                            </div>
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                </div>
                            </div>
                    </div>
                    <div class="news-row">
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                </div>
                            </div>
                            <div class="news-block">
                                    <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                    </div>
                            </div>
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                </div>
                            </div>
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                </div>
                            </div>
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                </div>
                            </div>
                            <div class="news-block">
                                <div class="news-info">
                                        T i t l e
                                        <p hidden>Text for news</p>
                                </div>
                            </div>
                    </div>
                    <div class="news-row">
                            <div class="news-block">
                                    <div class="news-info">
                                            T i t l e
                                            <p hidden>Text for news</p>
                                    </div>
                            </div>
                            <div class="news-block add">+</div>
                    </div>
                </div>
                <div class="block-content" id="mail" hidden>
                    <div class="mail-message">
                        <h3 class="title">Message theme <a class="author"> from TEstUser</a></h3>
                        <a class="body">First 255 symbols of message</a>
                        <a class="date">25.05.10</a>
                    </div>
                    <div class="mail-message">
                        <h3 class="title">Message theme <a class="author"> from TEstUser</a></h3>
                        <a class="body">First 255 symbols of message</a>
                        <a class="date">25.05.10</a>
                    </div>
                    <div class="mail-message">
                        <h3 class="title">Message theme <a class="author"> from TEstUser</a></h3>
                        <a class="body">First 255 symbols of message</a>
                        <a class="date">Дата: 25.05.10</a>
                    </div>
                </div>
                <div class="block-footer">
                    <div class="page-slider">
                            <a><-</a>
                            <a>1</a>
                            <a>2</a>
                            <a>...</a>
                            <a>-></a>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/news.js"></script>
        <script src="js/menu.js"></script>
        <script>
        
            
        
        </script>
    </body>
    
</html>

