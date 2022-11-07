<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<spring:url value="/resources/img" var="img"/>
<html>
<head>
    <title>Plan de Cuentas Contable</title>
    <link rel="stylesheet" href="${css}/tabla.css" type="text/css" />
    <link rel="stylesheet" href="${css}/inputsCss.css" type="text/css" />
    <link rel="stylesheet" href="${css}/EstilosPaginas.css" type="text/css" />
    <link rel="stylesheet" href="${css}/jquery-ui/1.12.1/jquery-ui.css" type="text/css" />
    <link rel="stylesheet" href="${css}/mensaje/jquery.alerts.css" type="text/css" />
    <link type="text/css" href="${css}/css-loader.css" rel="stylesheet"/>
    <link href="${jtable}/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${js}/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${js}/jquery-ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="${js}/mensaje/jquery.alerts.js"></script>
    <script src="${jtable}/jquery.jtable.js" type="text/javascript"></script>
    <script src="${jtable}/localization/jquery.jtable.es.js" type="text/javascript"></script>
    <script type="text/javascript" src="${js}/mantenedores/tblPlanCuenta.js"></script>
    <script type="text/javascript" src="${js}/mantenedores/tblPlanCuentaEdicion.js"></script>
    <script type="text/javascript" src="${js}/mantenedores/tblPlanCuentaCrearAgregacion.js"></script>
    <script type="text/javascript" src="${js}/mantenedores/tblProgramaPresupuestario.js"></script>
</head>
<body>
<div style="margin-left:10px; margin-top: 10px; position: absolute;">
    <div style="font: bold 12px sans-serif; float:left;">Ejercicio : <br/>
        <select id="cbEjercicio" class="Selectano" onchange="getTitulosPlanCuenta()" style="width: 160px;">
            <option value="-1">Selec</option>
            <c:forEach var="ejercc" items="${listaEjercicios}">
                <option value="${ejercc.ejercicioId}">${ejercc.ejercicioNombre}</option>
            </c:forEach>
        </select>
    </div>
    <div style="font: bold 12px sans-serif; float:left;">Titulo : <br/>
        <select id="cbCtaContTitulo" class="Selectano" onchange="getGruposPlanCuenta()" style="width: 160px;">
            <option value="-1">Selec</option>
            <c:forEach var="tit" items="${listTitulos}">
                <option value="${tit.idCtaContable}">${tit.desTituloCta}</option>
            </c:forEach>
        </select>
    </div>
    <div style="font: bold 12px sans-serif; float:left;">Grupo : <br/>
        <select id="cbCtaContGrupo" class="Selectano" onchange="getSubGruposPlanCuenta()" style="width: 160px;">
            <option value="-1">Selec</option>
            <c:forEach var="grp" items="${listGrupos}">
                <option value="${grp.idCtaContable}">${grp.desGruposCta}</option>
            </c:forEach>
        </select>
    </div>
    <div style="font: bold 12px sans-serif; float:left;">Sub Grupo : <br/>
        <select id="cbCtaContSubGrupo" class="Selectano" onchange="getPlanCuentasContable()" style="width: 160px;">
            <option value="-1">Selec</option>
            <c:forEach var="subgrp" items="${listSubGrupos}">
                <option value="${subgrp.idCtaContable}">${subgrp.desSubGruposCta}</option>
            </c:forEach>
        </select>
    </div>
    <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Activo : <br/>
        <select id="cbActivo" class="Selectano" style="width:150px;" onchange="getPlanCuentasContable()">
            <option value="1">Si</option>
            <option value="0">No</option>
        </select>
    </div>
</div>
<input type="button" value="Crear Agregación" onclick="openPopup(); return false;"
       style="margin-top: 45px; position: relative;float: right;"/>
<div id="ExpenseTableContainer" style="margin-top: 70px; margin-left:10px; position: absolute;">
</div>
<div id="loading-spinner-mant" style="z-index: 9999; position: absolute;" class="loader loader-default"
     data-text="Cargando Informaci&oacute;n..."></div>
<input id="rutaImagenes" value="${img}" type="hidden"/>
<input id="rutaJtable" value="${jtable}" type="hidden"/>
<%@ include file="planCuentas/crearAgregacion.jsp" %>
<%@ include file="planCuentas/agregarNivel.jsp" %>
<%@ include file="planCuentas/editarCuenta.jsp" %>
<%@ include file="planCuentas/desactivarCuenta.jsp" %>
<%@ include file="planCuentas/bitacoraCuenta.jsp" %>
<%@ include file="planCuentas/editarPeriodo.jsp" %>
<%@ include file="planCuentas/editarCtasPresup.jsp" %>
</body>
</html>