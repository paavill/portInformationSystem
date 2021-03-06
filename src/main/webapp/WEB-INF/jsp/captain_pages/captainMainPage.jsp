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
    <!--data table styles-->
    <link rel="stylesheet" href="css/table.css">
    <link rel="stylesheet" href="css/tableHead.css">
    <!--control elements styles-->
    <link rel="stylesheet" href="css/scrollbars.css">
    <link rel="stylesheet" href="css/buttons.css">

    <link rel="stylesheet" href="css/captainMainPage.css">

    <script src="js/main.js"></script>
</head>
<body onload="setFirstActive()">
    <header>
        <div class="container">
            <a href="FrontController?command=SHOW_MAIN_NO_LOGIN_PAGE" class="logo">Port</a>
            <form id="logOutForm" action="FrontController" method="post">
                <input type="hidden" name="command" value="LOGOUT">
            </form>
            <nav>
                <ul>
                    <li><a>Your role: captain</a></li>
                    <li><a>Your user id: ${user.id}</a></li>
                    <li><input type="submit" form="logOutForm" value="Logout"></li>
                </ul>
            </nav>
        </div>
    </header>
        <div class="main">
            <form id="createStatement" action="FrontController" method="post">
                <input type="hidden" name="command" value="CREATE_STATEMENT"/>
                <input type="hidden" name="userId" value="${user.id}" />
            </form>
            <form id="unloadProduct" action="FrontController" method="post">
                <input type="hidden" name="command" value="SHOW_UNLOAD_PAGE"/>
                <input type="hidden" name="userId" value="${user.id}" />
            </form>
            <form id="loadProduct" action="FrontController" method="post">
                <input type="hidden" name="command" value="SHOW_LOAD_PAGE"/>
                <input type="hidden" name="userId" value="${user.id}" />
            </form>
            <div class="tabs container">
                <button class="tablinks" onclick="openTab(event, 'Old statements to enter')">Old statements to enter</button>
                <button class="tablinks" onclick="openTab(event, 'Old statements to exit')">Old statements to exit</button>
                <div class="buttons">
                    <input class="button" type="submit" value="Create ${statementType} statement" form="createStatement"/>
                    <input class="button" type="${typeOfUnloadingButton}" value="Unload product" form="unloadProduct"/>
                    <input class="button" type="${typeOfLoadingButton}" value="Load product" form="loadProduct"/>
                </div>
            </div>
            <section id="Old statements to enter" class="container mainView"> 
            	<h4>${errorMessage}</h4>
                <c:remove var="errorMessage" scope="application"/>
                <c:forEach var="statement" items="${enterStatements}">
                    <div class="info">
                        <h3>Statement #${statement.id}</h3>
                        <div>
                            <h4>Ship:</h4>
                            <var>${statement.ship}</var>
                        </div>
                        <div>
                            <h4>Pier:</h4>
                            <var>${statement.pier}</var>
                        </div>
                        <div>
                            <h4>Status:</h4>
                            <var>${statement.status.title}</var>
                        </div>
                        <div>
                            <h4>Filing time:</h4>
                            <var>${statement.doDate}</var>
                        </div>
                        <div>
                            <h4>Lead time:</h4>
                            <var>${statement.finishDate}</var>
                        </div>
                    </div> 
                </c:forEach>
            </section>
            <section id="Old statements to exit" class="container mainView">
                <c:forEach var="statement" items="${exitStatements}">
                    <div class="info">
                        <h3>Statement #${statement.id}</h3>
                        <div>
                            <h4>Ship:</h4>
                            <var>${statement.ship}</var>
                        </div>
                        <div>
                            <h4>Pier:</h4>
                            <var>${statement.pier}</var>
                        </div>
                        <div>
                            <h4>Status:</h4>
                            <var>${statement.status.title}</var>
                        </div>
                        <div>
                            <h4>Filing time:</h4>
                            <var>${statement.doDate}</var>
                        </div>
                        <div>
                            <h4>Lead time:</h4>
                            <var>${statement.finishDate}</var>
                        </div>
                    </div> 
                </c:forEach>
            </section>     
        </div>
    <footer></footer>
</body>