<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head lang="US">
    <meta charset="UTF-8">
    <link rel="icon" href="images/icon.png" type="image/png">
    <title>Port</title>
    <!--pages are controled main classes-->
    <link rel="stylesheet" href="css/main.css">
    <!--head of sight styles-->
    <link rel="stylesheet" href="css/header.css">
    <!--control elements styles-->
    <link rel="stylesheet" href="css/scrollbars.css">
    <link rel="stylesheet" href="css/buttons.css">
    <link rel="stylesheet" href="css/enterFieldsStyles.css">

    <link rel="stylesheet" href="css/createShipPage.css">
</head>
<body>
    <header>
        <div class="container">
            <a href="FrontController?command=SHOW_MAIN_NO_LOGIN_PAGE" class="logo">Port</a>
            <form id="logOutForm" action="FrontController" method="post">
                <input type="hidden" name="command" value="LOGOUT">
            </form>
            <nav>
                <ul>
                    <li><a>Your role: administrator</a></li>
                    <li><a>Your user id: ${user.id}</a></li>
                    <li><input type="submit" form="logOutForm" value="Logout"></li>
                </ul>
            </nav>
        </div>
    </header>
        <div class="main">
            <section class="container mainView"> 
                <div class="info">
                    <h3>Creating ship</h3>
                    <div class="enters">
                        <div class="entersTitles">
                            <h4 class="title">Captain:</h4>
                            <h4 class="title">Title:</h4>
                        </div>
                        <form id="userDataForm" class="entersFields" action="FrontController" method="post">
                            <input type="hidden" name="command" value="CREATE_SHIP">
                            <select class="enter" name="captainId">
                                <c:forEach var="id" items="${captainsIds}">
                                    <option>${id}</option>
                                </c:forEach>
                            </select>
                            <input class="enter" required name="shipTitle" type="text">
                        </form>
                    </div>
                    <section class="buttons">
                        <input class="button" type="submit" form="userDataForm" value="Create">
                        <a class="button" href="FrontController?command=SHOW_MAIN_ADMIN_PAGE">Cancel</a>
                    </section>
                    <h4>${errorMessage}</h4>
                    <c:remove var="errorMessage" scope="application"/>
                </div> 
            </section>
        </div>
    <footer></footer>
</body>