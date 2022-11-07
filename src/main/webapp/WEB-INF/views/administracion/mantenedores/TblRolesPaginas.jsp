<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<html>
<head>
    <title>Sistema de Contabilidad General de la Naci�n</title>
    <link rel="stylesheet" type="text/css" href="${css}/validationEngine.jquery.css"/>
    <link rel="stylesheet" type="text/css" href="${css}/mantenedores/TblEntSubArea.css"/>
    <link rel="stylesheet" type="text/css" href="${css}/inputsCss.css"/>
    <link rel="stylesheet" type="text/css" href="${jtable}/themes/lightcolor/blue/jtable.css"/>

    <script type="text/javascript" src="${jtable}/jquery.jtable.js"></script>
    <script type="text/javascript" src="${jtable}/localization/jquery.jtable.es.js"></script>
    <script type="text/javascript" src="${js}/jquery-ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="${js}/mantenedores/tblRolesPaginas.js"></script>
</head>

<body>
<div id="ComboBoxes" class="ComboBoxes">
    <div style="font: bold 12px sans-serif;float:left;margin-top: 10px;">Roles: </br>
        <select id="cbRoles" class="Selectano" onchange="loadData()" style="width: 160px;">
            <option value="-1">Seleccione</option>
            <c:forEach var="rol" items="${listaRoles}">
                <option value="${rol.rolId}">${rol.nombre}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <button id="LoadRecordsButton" type="submit">Load records</button>
    </div>
</div>
<div id="ExpenseTableContainer" style="width: 96%; margin-top: 30px;"></div>
</body>
</html>