<%@ page import="com.slimechan.raceway_system.model.NewsModel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.slimechan.raceway_system.model.User" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.io.IOException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style.css">

    <script src = "js/jquery-3.4.1.min.js"></script>
    <script>var token = '<%= session.getAttribute("token") %>'</script>
    <script src = "js/addpanel.js"></script>

    <title>Moscow Raceway | Home</title>

</head>
    <body>
        <div class="shadow" id="shadow" hidden></div>
        <div class="add-data" id="add-panel" hidden>
            <span class="close" onclick="closePanel()">+</span>
            <h3 class="title">Добавление новости</h3>
            <table>
                <tr name="idinfo" hidden><td>Id</td><td name="id"></td></tr>
                <tr><td>Title</td><td><input type="text" name="title"></td></tr>
                <tr><td>Text</td><td><textarea name="text">aye</textarea></td></tr>
                <tr><td>Image</td><td><input type="file" name="file"/></td></tr>
                <tr><td><input type="button" value="Send"></td></tr>
            </table>
        </div>
        <div class="logo-head">
                <img id="logo" src="css/img/logo.png" oncontextmenu="return false" ondragstart="return false"></img>
                <a id="title" href="news">Administration System</a>
            <% User u = (User)session.getAttribute("user");
                if(u!=null){
                    out.println(
                            "<div class=\"user-info\">\n" +
                            "<a>\n"+u.getRole().toString()+" "+u.getFIO()+
                            "</a><br>\n" +
                            "<a href =\"logout\"><button class=\"exit\"></button></a>\n" +
                            "</div>");
                }else{
                    out.println("<div class=\"log-buttons\">\n" +
                            "                    <a href=\"login.html\" class=\"login\">SIGN IN</a>\n" +
                            "                </div>\n");
                }
            %>

        </div>
        <div class="main-wrapper">
            <div class="left-menu">
                <h1>MENU</h1>
                <table>
                    <tr>
                        <td class="active" onclick="menuClick(this)">News <img class="icon" src="css/img/icon/home.png" oncontextmenu="return false" ondragstart="return false"/></td>
                    </tr>
                    <tr>
                        <td onclick="menuClick(this)">Tracks <img class="icon" src="css/img/icon/track.png" oncontextmenu="return false" ondragstart="return false"/></td>
                    </tr>
                    <%
                        if(u.getRole()== User.Role.MANAGER||u.getRole()== User.Role.ADMIN)
                            out.println(
                                    "<tr>\n" +
                                            "                        <td onclick=\"menuClick(this)\">Personal <img class=\"icon\" src=\"css/img/icon/user.png\" oncontextmenu=\"return false\" ondragstart=\"return false\"/></td>\n" +
                                            "                    </tr>"
                            );
                    %>
                </table>
            </div>

            <div class="main-block">
                <div class="block-content" id="news">
                    <% List<List<NewsModel>> news = (List<List<NewsModel>>)request.getAttribute("news");
                        for(int y =0; y<news.size();y++){
                            out.println("<div class='news-row'>");
                            int count = 0;
                            for(NewsModel nm : news.get(y))
                            {
                                if(nm==null) return;
                                    out.println("<div class=\"news-block\" style='background-image:url(data:image/jpg;base64,"+nm.getBase64Image()+");background-repeat: no-repeat;" +
                                            "background-size: cover;'>" +
                                            "<button class=\"remove\" onclick=\"tryRemove(this)\"></button>" +
                                            "<div class=\"news-info\">" +
                                            nm.getTitle().substring(0, nm.getTitle().length()>40?40:nm.getTitle().length()) +
                                            "<p hidden>" +
                                            nm.getText().substring(0, nm.getText().length()>256?256:nm.getText().length()) +
                                            "</p>" +
                                            "<button class=\"edit\" onclick=\"edit(this)\" hidden />" +
                                            "<a name=\"id\" hidden>" + nm.getId() + "</a>" +
                                            "</div>" +
                                            "</div>");
                                    count++;
                            };

                            if(count==6)out.println("</div>");else{
                                out.println("<div class=\"news-block add\" onclick=\"add()\">+</div>");
                                out.println("</div>");
                            }
                        }
                        if(news.get(0).size()*news.size()%6==0){
                            out.println("<div class='news-row'>");
                            out.println("<div class=\"news-block add\" onclick=\"add()\">+</div>");

                            out.println("</div>");
                        }


                    %>
                </div>
            </div>
        </div>
        <script src="js/news.js"></script>
        <script src="js/menu.js"></script>
    </body>

