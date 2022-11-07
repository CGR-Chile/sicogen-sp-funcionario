<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<html>
<head>
    <title>Sistema de Contabilidad General de la Nación</title>
    <link rel="stylesheet" type="text/css" href="${css}/validationEngine.jquery.css"/>
    <link rel="stylesheet" type="text/css" href="${jtable}/themes/lightcolor/blue/jtable.css"/>

    <script type="text/javascript" src="${jtable}/jquery.jtable.js"></script>
    <script type="text/javascript" src="${js}/mantenedores/tblProvincias.js"></script>
    <script type="text/javascript" src="${jtable}/localization/jquery.jtable.es.js"></script>
    <script type="text/javascript" src="${js}/jquery.validationEngine.js"></script>
    <script type="text/javascript" src="${js}/jquery.validationEngine-es.js"></script>

</head>
<body>
    <div style="font: bold 12px sans-serif;float:left;margin-top: 10px;position: absolute;top: 1%;bottom: 0;left: 1%;right: 0;">
        Region: </br>
        <select id="cbRegion" class="Selectano" onchange="loadTbl(this.value)" style="width: 160px;">
            <option value="-1">Selec</option>
            <c:forEach var="regs" items="${listRegiones}">
                <option value="${regs.regId}">${regs.nombre}</option>
            </c:forEach>
        </select>
    </div>

    <div id="ExpenseTableContainer"></div>
</body>
</html>