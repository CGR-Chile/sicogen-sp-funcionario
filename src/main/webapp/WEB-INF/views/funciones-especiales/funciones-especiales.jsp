<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-22
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/img" var="images"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/css" var="css"/>
<html>
<head>
    <title>Sistema de Contabilidad General de la Naci&oacute;n</title>
    <link type="text/css" href="${css}/system.css" rel="stylesheet"/>
    <link type="text/css" href="${css}/template.css" rel="stylesheet"/>
    <link type="text/css" href="${css}/rounded.css" rel="stylesheet"/>
    <link type="text/css" href="${css}/login.css" rel="stylesheet"/>
    <link type="text/css" href="${css}/jquery-ui/1.12.1/jquery-ui.css" rel="stylesheet"/>
    <link type="text/css" href="${css}/bootstrap.css" rel="stylesheet"/>
    <link type="text/css" href="${css}/css-loader.css" rel="stylesheet"/>

    <script type="text/javascript" src="${js}/jquery/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="${js}/popper.min.js"></script>
    <script type="text/javascript" src="${js}/bootstrap.min.js"></script>
    <script type="text/javascript" src="${js}/funcionesEspeciales.js"></script>
    <script type="text/javascript" src="${js}/jquery-ui.js"></script>
</head>
<body>
<div class="py-3">
    <div class="container-fluid">
        <div class="card bg-secondary">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Función a Realizar</label>
                            <select class="form-control" id="selectFuncion">
                                <option value="-1" selected>Seleccione Función</option>
                                <option value="1">Reprocesos</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="py-1">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12" id="divTbl">
            </div>
        </div>
    </div>
</div>
<div id="loading-spinner" style="z-index: 9999; position: absolute;" class="loader loader-default"
     data-text="Cargando Informaci&oacute;n..."></div>
</body>
</html>