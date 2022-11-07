<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <spring:url value="/resources/css/system.css" var="system"/>
    <spring:url value="/resources/css/template.css" var="template"/>
    <spring:url value="/resources/css/rounded.css" var="rounded"/>
    <spring:url value="/resources/css/login.css" var="login"/>
    <spring:url value="/resources/css/pestanas.css" var="pestanas"/>
    <spring:url value="/resources/img" var="images"/>
    <spring:url value="/resources/js" var="js"/>
    <spring:url value="/resources/css/css-loader.css" var="loader"/>
    <spring:url value="/resources/css/jquery-ui/1.12.1/jquery-ui.css" var="ui"/>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>

    <link type="text/css" href="${system}" rel="stylesheet"/>
    <link type="text/css" href="${template}" rel="stylesheet"/>
    <link type="text/css" href="${rounded}" rel="stylesheet"/>
    <link type="text/css" href="${login}" rel="stylesheet"/>
    <link type="text/css" href="${bootstrap}" rel="stylesheet"/>
    <link type="text/css" href="${loader}" rel="stylesheet"/>
    <link type="text/css" href="${ui}" rel="stylesheet"/>

    <script type="text/javascript" src="${js}/ajax.js"></script>
    <script type="text/javascript" src="${js}/net.js"></script>
    <script type="text/javascript" src="${js}/nu.js"></script>
    <script type="text/javascript" src="${js}/functions.js"></script>
    <script type="text/javascript" src="${js}/dom-drag.js"></script>
    <script type="text/javascript" src="${js}/jquery/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="${js}/popper.min.js"></script>
    <script type="text/javascript" src="${js}/bootstrap.min.js"></script>
    <script type="text/javascript" src="${js}/cargaInforme.js"></script>
    <script type="text/javascript" src="${js}/jquery-ui.js"></script>
    <style type="text/css">
        .icoImage18 {
            width: 18px;
            height: 18px;
        }
    </style>
</head>
<body>
    <div class="py-3">
        <div class="container-fluid">
            <div class="card bg-secondary">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-2">
                            <div class="form-group">
                                <label>Tipo Informe</label>
                                <select class="form-control" id="selectTipoInforme">
                                    <option value="-1">Presupuestario-Contable</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label>Informe</label>
                                <select class="form-control" id="selectInforme">
                                    <option value="-1">Seleccione Informe</option>
                                    <c:forEach var="informe" items="${informesPresupuestarios}">
                                        <option value="${informe.id}">${informe.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-8" id="divFiltrosSelect">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="divCargaDocumento">
        <div class="py-3">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <table class="adminlist">
                            <thead>
                            <tr>
                                <td colspan="1" class="title-table"><span class="Estilo11"><b>Carga de Documentos</b></span></td>
                            </tr>
                            <tr class="title-grid">
                                <td class="title-grid"></td>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="divContenido">
    </div>
    <div id="dialogValidacion" title="Información">
    </div>
    <div id="loading-spinner" style="z-index: 9999; position: absolute;" class="loader loader-default"
         data-text="Cargando Informaci&oacute;n..."></div>
</body>
</html>
