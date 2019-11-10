<%@ page import="com.slimechan.raceway_system.model.NewsModel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.slimechan.raceway_system.model.User" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.io.IOException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/style.css">

        <script src = "js/jquery-3.4.1.min.js"></script>
        <script>var token = '<%= session.getAttribute("token") %>'</script>
        <script src = "js/personal.js"></script>

        <title>Moscow Raceway | Personal</title>

    </head>
    <body>
    <div class="shadow" id="shadow" hidden></div>
    <div class="add-data" id="add-panel" hidden>
        <span class="close" onclick="closePanel()">+</span>
        <h3 class="title">Редактирование пользоввателя</h3>
        <table>
            <tr name="idinfo" hidden><td>Id</td><td name="id"></td></tr>
            <tr><td>Password</td><td><input type="text" name="password"></td></tr>
            <tr><td>FIO</td><td><input type="text" name="fio"></td></tr>
            <tr><td>Phone</td><td><input type="phone" name="phone"/></td></tr>
            <tr><td>Role</td><td><select name="role">
                <%
                    for (User.Role role : User.Role.values()) {
                        out.println("<option value=\""+role.toString().toLowerCase()+"\">"+role+"</option>");
                    }%>
            </select></td></tr>
            <tr><td><input type="button" value="Send" onclick="tryEditPerson(this)"></td></tr>
        </table>
    </div>
    <div class="logo-head">
        <img id="logo" src="css/img/logo.png" oncontextmenu="return false" ondragstart="return false"></img>
        <a id="title" href="news">Administration System</a>
        <% User usr = (User)session.getAttribute("user");
            if(usr!=null){
                out.println(
                        "<div class=\"user-info\">\n" +
                                "<a>\n"+usr.getRole().toString()+" "+usr.getFIO()+
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
                        <td  onclick="menuClick(this)">News <img class="icon" src="css/img/icon/home.png" oncontextmenu="return false" ondragstart="return false"/></td>
                    </tr>
                    <tr>
                        <td onclick="menuClick(this)">Tracks <img class="icon" src="css/img/icon/track.png" oncontextmenu="return false" ondragstart="return false"/></td>
                    </tr>
                    <tr>
                        <td class="active" onclick="menuClick(this)">Personal <img class="icon" src="css/img/icon/user.png" oncontextmenu="return false" ondragstart="return false"/></td>
                    </tr>
                </table>
            </div>

            <div class="main-block">
            <div class="block-content" id="personal">
                <% List<User> users = (List<User>)request.getAttribute("users");
                    for(User u : users){
                        out.println("<div class=\"person-block\">\n" +
                                "<a name='id' hidden>"+u.getLogin()+"</a>"+
                                "                    <h3 class=\"fio\">"+u.getFIO()+"</h3>\n" +
                                "                    <a class=\"group\">Group:"+u.getRole()+"</a>\n" +
                                "                    <div class=\"buttons\">\n" +
                                "                        <button class=\"edit\" onclick='edit(this)'></button>\n" +
                                "                        <button class=\"remove\" onclick='tryRemove(this)'></button>\n" +
                                "                    </div>\n" +
                                "                </div>");
                    }
                %>
                <button class="add" onclick="add()">Add user</button>
            </div>
            
        </div>
    </div>
    <script src="js/news.js"></script>
    <script src="js/menu.js"></script>

   </body>

</html>