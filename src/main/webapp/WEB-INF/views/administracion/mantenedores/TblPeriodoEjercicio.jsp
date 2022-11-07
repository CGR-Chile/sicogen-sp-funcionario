<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Sistema de Contabilidad General de la Nación</title>

    <link rel="stylesheet" href="${jtable}/themes/lightcolor/blue/jtable.css" type="text/css" />
    <link rel="stylesheet" href="${css}/validationEngine.jquery.css" type="text/css" />

    <script src="${jtable}/jquery.jtable.js" type="text/javascript"></script>
    <script src="${jtable}/localization/jquery.jtable.es.js" type="text/javascript"></script>
    <script src="${js}/SpryStyles/SpryCollapsiblePanel.js" type="text/javascript"></script>
    <script src="${js}/jquery.validationEngine.js" type="text/javascript"></script>
    <script src="${js}/jquery.validationEngine-es.js" type="text/javascript"></script>
    <script src="${js}/mantenedores/tblPeriodoEjercicio.js" type="text/javascript"></script>

</head>
<body>

<div style="font: bold 12px sans-serif;float:left;margin-top: 10px;">Ejercicio: </br>
    <select id="cbEjercicio" class="Selectano" onchange="loadTbl(this.value)" style="width:170px;">
        <option value="-1">Selec. Ejercicio</option>
        <c:forEach var="ejer" items="${listEjercicios}">
            <option value="${ejer.ejercicioId}">${ejer.ejercicioNombre}</option>
        </c:forEach>
    </select>

    <select id="cbPeriodos" class="Selectano" onchange="loadTbl(this.value)" style="width:120px;display:none">
        <option value="-1">Selec</option>
        <c:forEach var="per" items="${listPeriodos}">
            <option value="${per.periodoId}">${per.periodoNombre}</option>
        </c:forEach>
    </select>

</div>

<div id="ExpenseTableContainer" style="margin-top: 60px;position: relative"></div>

</body>
</html>
