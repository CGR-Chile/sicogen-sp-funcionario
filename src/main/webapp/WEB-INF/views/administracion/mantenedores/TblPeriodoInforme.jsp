<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Sistema de Contabilidad General de la Nación</title>
    <link rel="stylesheet" type="text/css" href="${css}/validationEngine.jquery.css"/>
    <link rel="stylesheet" type="text/css" href="${jtable}/themes/lightcolor/blue/jtable.css"/>

    <script type="text/javascript" src="${jtable}/jquery.jtable.js"></script>
    <script type="text/javascript" src="${js}/mantenedores/tblPeriodoInforme.js"></script>
    <script type="text/javascript" src="${jtable}/localization/jquery.jtable.es.js"></script>
    <script type="text/javascript" src="${js}/jquery.validationEngine.js"></script>
    <script type="text/javascript" src="${js}/jquery.validationEngine-es.js"></script>
</head>
<body>
<div style="font: bold 12px sans-serif;float:left;margin-top: 10px;">Ejercicio: </br>
    <select id="cbEjercicio" class="Selectano" onchange="loadInformesyPeriodos(this.value)" style="width: 160px;">
        <option value="-1">Selec. Tipo Informes</option>
        <c:forEach var="ejercicio" items="${listaEjercicios}">
            <option value="${ejercicio.ejercicioId}">${ejercicio.ejercicioNombre}</option>
        </c:forEach>
    </select>
</div>

<div style="font: bold 12px sans-serif; float:left;margin-top: 10px;margin-left: 10px;">Informe:</br>
    <select name="listaSubTipoInf" id="cbInformes" class="Selectano" onchange="loadTbl(cbEjercicio.value, this.value)"
            style="width: 160px;">
        <option value="-1">Selec.</option>
    </select>
</div>

<div style="display:none;">Periodo:</br>
    <select id="cbPeriodos" class="Selectano" style="display:none;">
    <option value="-1">Selec. Periodo</option>
</select></div>

<div id="ExpenseTableContainer" style="width:900px; margin-top: 35px; padding-top: 20px;"></div>

</body>
</html>