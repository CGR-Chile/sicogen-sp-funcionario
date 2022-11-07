<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Sistema de Contabilidad General de la Nación - Informes</title>

    <link rel="stylesheet" href="${jtable}/themes/lightcolor/blue/jtable.css" type="text/css" />
    <link rel="stylesheet" href="${css}/validationEngine.jquery.css" type="text/css" />

    <script src="${jtable}/jquery.jtable.js" type="text/javascript"></script>
    <script src="${jtable}/localization/jquery.jtable.es.js" type="text/javascript"></script>
    <script src="${js}/SpryStyles/SpryCollapsiblePanel.js" type="text/javascript"></script>
    <script src="${js}/jquery.validationEngine.js" type="text/javascript"></script>
    <script src="${js}/jquery.validationEngine-es.js" type="text/javascript"></script>
    <script src="${js}/mantenedores/tblInforme.js" type="text/javascript"></script>
</head>
<body >
<div style="font: bold 12px sans-serif;float:left;margin-top: 10px;">Tipo de Informe: </br>
    <select id="cbtipoInforme" class="Selectano" onchange="loadSubTipoInforme(this.value)" style="width: 160px;">
        <option value="-1">Selec. Tipo Informes</option>
        <c:forEach var="tipInf" items="${listTipoInformes}">
            <option value="${tipInf.id}">${tipInf.nombre}</option>
        </c:forEach>
    </select>
</div>

<div style="font: bold 12px sans-serif; float:left;margin-top: 10px;margin-left: 10px;">Sub Tipo de Informes:</br>
    <select name="listaSubTipoInf" id="cbLstSubTipoInforme" class="Selectano" onchange="loadTbl(this.value)" style="width: 160px;">
        <option value="-1">Selec.</option>
    </select>
</div>

<div id="ExpenseTableContainer" style="margin-top: 70px; position: relative;"></div>

</body>
</html>