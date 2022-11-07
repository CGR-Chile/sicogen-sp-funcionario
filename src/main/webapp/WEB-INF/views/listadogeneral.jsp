<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Sistema de Contabilidad General de la Naci&oacute;n</title>

    <spring:url value="/resources/css/system.css" var="system"/>
    <spring:url value="/resources/css/template.css" var="template"/>
    <spring:url value="/resources/css/rounded.css" var="rounded"/>
    <spring:url value="/resources/css/nyroModal.css" var="nyroModal"/>
    <spring:url value="/resources/css/pestanas.css" var="pestanas"/>
    <spring:url value="/resources/css/carrusel.css" var="carrusel"/>
    <spring:url value="/resources/css/PrincipalUser.css" var="principalUser"/>
    <spring:url value="/resources/css/css-loader.css" var="loader"/>
    <spring:url value="/resources/css/jquery-confirm.min.css" var="confirm"/>
    <spring:url value="/resources/css/1.12.1/jquery-ui.css" var="ui"/>


    <spring:url value="/resources/img/" var="images"/>
    <spring:url value="/resources/js" var="js"/>

    <link type="text/css" href="${system}" rel="stylesheet"/>
    <link type="text/css" href="${template}" rel="stylesheet"/>
    <link type="text/css" href="${rounded}" rel="stylesheet"/>
    <link type="text/css" href="${nyroModal}" rel="stylesheet"/>
    <link type="text/css" href="${pestanas}" rel="stylesheet"/>
    <link type="text/css" href="${carrusel}" rel="stylesheet"/>
    <link type="text/css" href="${principalUser}" rel="stylesheet"/>
    <link type="text/css" href="${loader}" rel="stylesheet"/>
    <link type="text/css" href="${confirm}" rel="stylesheet"/>
    <link type="text/css" href="${ui}" rel="stylesheet"/>


    <script type="text/javascript" src="${js}/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="${js}/bootstrap3/bootstrap.min.js"></script>
    <script type="text/javascript" src="${js}/reportes/reporteDeValidacion.js"></script>
    <script type="text/javascript" src="${js}/listadoGeneral.js"></script>
    <script type="text/javascript" src="${js}/documentos.js"></script>
    <script type="text/javascript" src="${js}/validacionTDR.js"></script>
    <script type="text/javascript" src="${js}/popUpAmpliado.js"></script>


    <script type="text/javascript" src="${js}/jquery-ui.js"></script>
    <script type="text/javascript" src="${js}/popUpAmpliado.js"></script>
    <script type="text/javascript" src="${js}/informeAnual.js"></script>
    <script type="text/javascript" src="${js}/ajax.js"></script>
    <script type="text/javascript" src="${js}/net.js"></script>
    <script type="text/javascript" src="${js}/nu.js"></script>
    <script type="text/javascript" src="${js}/functions.js"></script>
    <script type="text/javascript" src="${js}/jquery-confirm.min.js"></script>


    <style>

        .panel.with-nav-tabs .panel-heading {
            padding: 10px 10px 0 10px;
            font-size: 12px;
            font-weight: bold;
            color: #0B55C4;
            line-height: 48px;

        }

        .panel.with-nav-tabs .nav-tabs {
            border-bottom: none;
        }

        .panel.with-nav-tabs .nav-justified {
            margin-bottom: -1px;
        }

        /********************************************************************/
        /*** PANEL DEFAULT ***/
        .with-nav-tabs.panel-default .nav-tabs > li > a,
        .with-nav-tabs.panel-default .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-default .nav-tabs > li > a:focus {
            color: #777;
        }

        .with-nav-tabs.panel-default .nav-tabs > .open > a,
        .with-nav-tabs.panel-default .nav-tabs > .open > a:hover,
        .with-nav-tabs.panel-default .nav-tabs > .open > a:focus,
        .with-nav-tabs.panel-default .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-default .nav-tabs > li > a:focus {
            color: #777;
            background-color: #ddd;
            border-color: transparent;
        }

        .with-nav-tabs.panel-default .nav-tabs > li.active > a,
        .with-nav-tabs.panel-default .nav-tabs > li.active > a:hover,
        .with-nav-tabs.panel-default .nav-tabs > li.active > a:focus {
            color: #555;
            background-color: #fff;
            border-color: #ddd;
            border-bottom-color: transparent;
        }

        .with-nav-tabs.panel-default .nav-tabs > li.dropdown .dropdown-menu {
            background-color: #f5f5f5;
            border-color: #ddd;
        }

        .with-nav-tabs.panel-default .nav-tabs > li.dropdown .dropdown-menu > li > a {
            color: #777;
        }

        .with-nav-tabs.panel-default .nav-tabs > li.dropdown .dropdown-menu > li > a:hover,
        .with-nav-tabs.panel-default .nav-tabs > li.dropdown .dropdown-menu > li > a:focus {
            background-color: #ddd;
        }

        .with-nav-tabs.panel-default .nav-tabs > li.dropdown .dropdown-menu > .active > a,
        .with-nav-tabs.panel-default .nav-tabs > li.dropdown .dropdown-menu > .active > a:hover,
        .with-nav-tabs.panel-default .nav-tabs > li.dropdown .dropdown-menu > .active > a:focus {
            color: #fff;
            background-color: #555;
        }

        /********************************************************************/
        /*** PANEL PRIMARY ***/
        .with-nav-tabs.panel-primary .nav-tabs > li > a,
        .with-nav-tabs.panel-primary .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-primary .nav-tabs > li > a:focus {
            color: #fff;
        }

        .with-nav-tabs.panel-primary .nav-tabs > .open > a,
        .with-nav-tabs.panel-primary .nav-tabs > .open > a:hover,
        .with-nav-tabs.panel-primary .nav-tabs > .open > a:focus,
        .with-nav-tabs.panel-primary .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-primary .nav-tabs > li > a:focus {
            color: #fff;
            background-color: #3071a9;
            border-color: transparent;
        }

        .with-nav-tabs.panel-primary .nav-tabs > li.active > a,
        .with-nav-tabs.panel-primary .nav-tabs > li.active > a:hover,
        .with-nav-tabs.panel-primary .nav-tabs > li.active > a:focus {
            color: #428bca;
            background-color: #fff;
            border-color: #428bca;
            border-bottom-color: transparent;
        }

        .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu {
            background-color: #428bca;
            border-color: #3071a9;
        }

        .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > li > a {
            color: #fff;
        }

        .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > li > a:hover,
        .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > li > a:focus {
            background-color: #3071a9;
        }

        .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > .active > a,
        .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > .active > a:hover,
        .with-nav-tabs.panel-primary .nav-tabs > li.dropdown .dropdown-menu > .active > a:focus {
            background-color: #4a9fe9;
        }

        /********************************************************************/
        /*** PANEL SUCCESS ***/
        .with-nav-tabs.panel-success .nav-tabs > li > a,
        .with-nav-tabs.panel-success .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-success .nav-tabs > li > a:focus {
            color: #3c763d;
        }

        .with-nav-tabs.panel-success .nav-tabs > .open > a,
        .with-nav-tabs.panel-success .nav-tabs > .open > a:hover,
        .with-nav-tabs.panel-success .nav-tabs > .open > a:focus,
        .with-nav-tabs.panel-success .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-success .nav-tabs > li > a:focus {
            color: #3c763d;
            background-color: #d6e9c6;
            border-color: transparent;
        }

        .with-nav-tabs.panel-success .nav-tabs > li.active > a,
        .with-nav-tabs.panel-success .nav-tabs > li.active > a:hover,
        .with-nav-tabs.panel-success .nav-tabs > li.active > a:focus {
            color: #3c763d;
            background-color: #fff;
            border-color: #d6e9c6;
            border-bottom-color: transparent;
        }

        .with-nav-tabs.panel-success .nav-tabs > li.dropdown .dropdown-menu {
            background-color: #dff0d8;
            border-color: #d6e9c6;
        }

        .with-nav-tabs.panel-success .nav-tabs > li.dropdown .dropdown-menu > li > a {
            color: #3c763d;
        }

        .with-nav-tabs.panel-success .nav-tabs > li.dropdown .dropdown-menu > li > a:hover,
        .with-nav-tabs.panel-success .nav-tabs > li.dropdown .dropdown-menu > li > a:focus {
            background-color: #d6e9c6;
        }

        .with-nav-tabs.panel-success .nav-tabs > li.dropdown .dropdown-menu > .active > a,
        .with-nav-tabs.panel-success .nav-tabs > li.dropdown .dropdown-menu > .active > a:hover,
        .with-nav-tabs.panel-success .nav-tabs > li.dropdown .dropdown-menu > .active > a:focus {
            color: #fff;
            background-color: #3c763d;
        }

        /********************************************************************/
        /*** PANEL INFO ***/
        .with-nav-tabs.panel-info .nav-tabs > li > a,
        .with-nav-tabs.panel-info .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-info .nav-tabs > li > a:focus {
            color: #31708f;
        }

        .with-nav-tabs.panel-info .nav-tabs > .open > a,
        .with-nav-tabs.panel-info .nav-tabs > .open > a:hover,
        .with-nav-tabs.panel-info .nav-tabs > .open > a:focus,
        .with-nav-tabs.panel-info .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-info .nav-tabs > li > a:focus {
            color: #31708f;
            background-color: #bce8f1;
            border-color: transparent;
        }

        .with-nav-tabs.panel-info .nav-tabs > li.active > a,
        .with-nav-tabs.panel-info .nav-tabs > li.active > a:hover,
        .with-nav-tabs.panel-info .nav-tabs > li.active > a:focus {
            color: #31708f;
            background-color: #fff;
            border-color: #bce8f1;
            border-bottom-color: transparent;
        }

        .with-nav-tabs.panel-info .nav-tabs > li.dropdown .dropdown-menu {
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        .with-nav-tabs.panel-info .nav-tabs > li.dropdown .dropdown-menu > li > a {
            color: #31708f;
        }

        .with-nav-tabs.panel-info .nav-tabs > li.dropdown .dropdown-menu > li > a:hover,
        .with-nav-tabs.panel-info .nav-tabs > li.dropdown .dropdown-menu > li > a:focus {
            background-color: #bce8f1;
        }

        .with-nav-tabs.panel-info .nav-tabs > li.dropdown .dropdown-menu > .active > a,
        .with-nav-tabs.panel-info .nav-tabs > li.dropdown .dropdown-menu > .active > a:hover,
        .with-nav-tabs.panel-info .nav-tabs > li.dropdown .dropdown-menu > .active > a:focus {
            color: #fff;
            background-color: #31708f;
        }

        /********************************************************************/
        /*** PANEL WARNING ***/
        .with-nav-tabs.panel-warning .nav-tabs > li > a,
        .with-nav-tabs.panel-warning .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-warning .nav-tabs > li > a:focus {
            color: #8a6d3b;
        }

        .with-nav-tabs.panel-warning .nav-tabs > .open > a,
        .with-nav-tabs.panel-warning .nav-tabs > .open > a:hover,
        .with-nav-tabs.panel-warning .nav-tabs > .open > a:focus,
        .with-nav-tabs.panel-warning .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-warning .nav-tabs > li > a:focus {
            color: #8a6d3b;
            background-color: #faebcc;
            border-color: transparent;
        }

        .with-nav-tabs.panel-warning .nav-tabs > li.active > a,
        .with-nav-tabs.panel-warning .nav-tabs > li.active > a:hover,
        .with-nav-tabs.panel-warning .nav-tabs > li.active > a:focus {
            color: #8a6d3b;
            background-color: #fff;
            border-color: #faebcc;
            border-bottom-color: transparent;
        }

        .with-nav-tabs.panel-warning .nav-tabs > li.dropdown .dropdown-menu {
            background-color: #fcf8e3;
            border-color: #faebcc;
        }

        .with-nav-tabs.panel-warning .nav-tabs > li.dropdown .dropdown-menu > li > a {
            color: #8a6d3b;
        }

        .with-nav-tabs.panel-warning .nav-tabs > li.dropdown .dropdown-menu > li > a:hover,
        .with-nav-tabs.panel-warning .nav-tabs > li.dropdown .dropdown-menu > li > a:focus {
            background-color: #faebcc;
        }

        .with-nav-tabs.panel-warning .nav-tabs > li.dropdown .dropdown-menu > .active > a,
        .with-nav-tabs.panel-warning .nav-tabs > li.dropdown .dropdown-menu > .active > a:hover,
        .with-nav-tabs.panel-warning .nav-tabs > li.dropdown .dropdown-menu > .active > a:focus {
            color: #fff;
            background-color: #8a6d3b;
        }

        /********************************************************************/
        /*** PANEL DANGER ***/
        .with-nav-tabs.panel-danger .nav-tabs > li > a,
        .with-nav-tabs.panel-danger .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-danger .nav-tabs > li > a:focus {
            color: #a94442;
        }

        .with-nav-tabs.panel-danger .nav-tabs > .open > a,
        .with-nav-tabs.panel-danger .nav-tabs > .open > a:hover,
        .with-nav-tabs.panel-danger .nav-tabs > .open > a:focus,
        .with-nav-tabs.panel-danger .nav-tabs > li > a:hover,
        .with-nav-tabs.panel-danger .nav-tabs > li > a:focus {
            color: #a94442;
            background-color: #ebccd1;
            border-color: transparent;
        }

        .with-nav-tabs.panel-danger .nav-tabs > li.active > a,
        .with-nav-tabs.panel-danger .nav-tabs > li.active > a:hover,
        .with-nav-tabs.panel-danger .nav-tabs > li.active > a:focus {
            color: #a94442;
            background-color: #fff;
            border-color: #ebccd1;
            border-bottom-color: transparent;
        }

        .with-nav-tabs.panel-danger .nav-tabs > li.dropdown .dropdown-menu {
            background-color: #f2dede; /* bg color */
            border-color: #ebccd1; /* border color */
        }

        .with-nav-tabs.panel-danger .nav-tabs > li.dropdown .dropdown-menu > li > a {
            color: #a94442; /* normal text color */
        }

        .with-nav-tabs.panel-danger .nav-tabs > li.dropdown .dropdown-menu > li > a:hover,
        .with-nav-tabs.panel-danger .nav-tabs > li.dropdown .dropdown-menu > li > a:focus {
            background-color: #ebccd1; /* hover bg color */
        }

        .with-nav-tabs.panel-danger .nav-tabs > li.dropdown .dropdown-menu > .active > a,
        .with-nav-tabs.panel-danger .nav-tabs > li.dropdown .dropdown-menu > .active > a:hover,
        .with-nav-tabs.panel-danger .nav-tabs > li.dropdown .dropdown-menu > .active > a:focus {
            color: #fff; /* active text color */
            background-color: #a94442; /* active bg color */
        }

        #scrollarea-invalid1 {
            overflow-y: scroll;
            height: 277px;
        }

        #scrollarea-content1 {
            min-height: 101%;
        }

        #scrollarea-invalid2 {
            overflow-y: scroll;
            height: 277px;
        }

        #scrollarea-content2 {
            min-height: 101%;
        }


    </style>
    <script>


        function cargando() {
            $("#loading-spinner").addClass("is-active")
        }

        function stop() {
            $("#loading-spinner").removeClass("is-active");
        }

        function modalClose() {
            $("#links-informe").dialog('close');
        }

        function nobackbutton() {

            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button" //chrome
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            }

        }


    </script>
</head>

<body onload="nobackbutton();" id="minwidth-body">
<div id="header-box" style="background: #ffffff;">
    <div id="module-status">
        <span class="logout"><a href="./">Cerrar Sesion</a></span>
    </div>
    <div id="module-status">
        <span style="background-image:url(${images}menu/icon-16-user.png);background-repeat:no-repeat ">Usuario:<a
                href="#"> ${usuario.userLogin}</a></span>
    </div>

    <div id="module-status">
        <span class="">|&nbsp;&nbsp;&nbsp;&nbsp;</span>
    </div>

    <div id="module-status">
        <span style="background-image:url(${images}menu/icon-16-static.png);background-repeat:no-repeat "><a href="#" target="_blank">Manual de reglas de negocio</a></span>
    </div>
    <div id="module-status">
        <span style="background-image:url(${images}menu/icon-16-static.png);background-repeat:no-repeat "><a href="#" target="_blank">Manual de usuario</a></span>
    </div>

    <div id="module-menu">
        <%@ include file="menu.jsp" %>
    </div>
    <div class="clr"></div>
</div>
<div id="content-box">
    <div class="border">
        <div class="padding">
            <div id="toolbar-box">
                <div>
                    <div>
                        <div></div>
                    </div>
                </div>
                <div>
                    <div class="toolbar" id="toolbar">
                    </div>
                    <div class="header logo"></div>
                    <div class="clr">
                        <span style="color: #fa0000;"> Error: ${error}</span>
                    </div>
                </div>
                <div>
                    <div>
                        <div></div>
                    </div>
                </div>
            </div>
            <div class="clr"></div>
            <div id="submenu-box">
                <div class="t">
                    <div class="t">
                        <div class="t"></div>
                    </div>
                </div>
                <div class="m">
                    <%@ include file="infoEntidad.jsp" %>

                    <div class="col100"></div>
                    <div class="clr"></div>
                </div>
                <div class="b">
                    <div class="b">
                        <div class="b"></div>
                    </div>
                </div>
            </div>
            <div class="panel with-nav-tabs panel-default" style="position: absolute; z-index: 1; height: 75%; right: 0.65%; left: 0.65%;">
                <div class="panel-heading">
                    <ul class="nav nav-tabs">
                        <li class="active" id="mfInicio"><a href="#tab1default" data-toggle="tab"><font
                                color="#000">Inicio</font></a></li>

                        <c:forEach items="${usuario.paginasAutorizadas}" var="item">

                            <c:if test="${item=='mfSeguimiento'}">
                                <li id="mfSeguimiento"><a href="#tab2default" data-toggle="tab"><font
                                        color="#000">Seguimiento</font></a></li>
                            </c:if>
                            <c:if test="${item=='mfReportes'}">
                                <li id="mfReportes"><a href="#tab3default" data-toggle="tab"><font
                                        color="#000">Reportes</font></a></li>
                            </c:if>
                            <c:if test="${item=='mfAdministracion'}">
                                <li><a href="#tab4default" data-toggle="tab"><font color="#000">Administraci&oacute;n</font></a>
                                </li>
                            </c:if>
                            <c:if test="${item=='mfRevalidacionTDR'}">
                                <li><a href="#tab5default" data-toggle="tab"><font color="#000">Validaci&oacute;n
                                    TDR</font></a></li>
                            </c:if>
                            <c:if test="${item=='mfBuscadorDocumentos'}">
                                <li><a href="#tab6default" data-toggle="tab"><font color="#000">Buscar
                                    Documentos</font></a></li>
                            </c:if>
                            <c:if test="${item=='mfCarga'}">
                                <li id="mfCarga"><a href="#tab7default" data-toggle="tab"><font color="#000">Carga
                                    Informes</font></a></li>
                            </c:if>
                            <c:if test="${item=='funcionesEspeciales'}">
                                <li id="funcionesEspeciales"><a href="#tab8default" data-toggle="tab"><font
                                        color="#000">Funciones Especiales</font></a></li>
                            </c:if>
                            <c:if test="${item=='mfAsignacionTDR'}">
                                <li id="mfAsignacionTDR"><a href="#tab9default" data-toggle="tab"><font
                                        color="#000">Catalogar TDR</font></a></li>
                            </c:if>
                            <c:if test="${item=='reportesFinancierosMf'}">
                                <li id="reportesFinancierosMf"><a href="#tab10default" data-toggle="tab"><font
                                        color="#000">Reportes Financieros</font></a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>

                <div class="tab-content">
                    <div style="height: 100%; position: absolute; z-index: 1; right: 20px; left: 20px; top: 60px; bottom: 0;" class="tab-pane fade in active"
                         id="tab1default">
                        <iframe src="./inicio/noticias" id="iframeInicio " height="148" width="100%"
                                frameborder="0" scrolling="no" align="center">
                        </iframe>
                    </div>

                    <c:forEach items="${usuario.paginasAutorizadas}" var="item2">
                        <c:if test="${item2=='mfSeguimiento'}">
                            <div class="iframe-tab-content tab-pane fade" id="tab2default">
                                <iframe src="./seguimiento/cargaSeguimiento" id="iframeSeguimiento" height="100%" width="100%" frameborder="0" scrolling="no" align="center">
                                </iframe>
                            </div>
                        </c:if>
                        <c:if test="${item2=='mfReportes'}">
                            <div style="height: 362px; position: relative; z-index: 1" class="tab-pane fade"
                                 id="tab3default">
                                <iframe src="./reportes/cargaReportes" id="iframeReportes" height="362px;"
                                        width="100%" frameborder="0" scrolling="no" align="center">
                                </iframe>
                            </div>
                        </c:if>
                        <c:if test="${item2=='mfAdministracion'}">
                            <div class="iframe-tab-content tab-pane fade" id="tab4default">
                                <iframe src="./administracion/" id="iframeAdministracion" height="100%" width="100%" frameborder="0" scrolling="yes" align="center">
                                </iframe>
                            </div>

                        </c:if>
                        <c:if test="${item2=='mfRevalidacionTDR'}">
                            <div style="height: 362px; position: relative;" class="tab-pane fade"
                                 id="tab5default">
                                <iframe src="./validacion/cargaValidacion" id="iframeValidacion" height="362px;"
                                        width="100%" frameborder="0" scrolling="no" align="center">
                                </iframe>
                            </div>
                        </c:if>
                        <c:if test="${item2=='mfBuscadorDocumentos'}">
                            <div style="height: 362px; position: relative; z-index: 1" class="tab-pane fade"
                                 id="tab6default">
                                <iframe src="./documentos/cargaDocumentos" id="iframeDocumentos" height="362px;"
                                        width="100%" frameborder="0" scrolling="no" align="center">
                                </iframe>
                            </div>
                        </c:if>
                        <c:if test="${item2=='mfCarga'}">
                            <div class="iframe-tab-content tab-pane fade" id="tab7default">
                                <iframe src="./carga/cargaInformes" id="iframeCargaInforme" height="100%" width="100%" frameborder="0" scrolling="yes" align="center">
                                </iframe>
                            </div>
                        </c:if>
                        <c:if test="${item2=='funcionesEspeciales'}">
                            <div class="iframe-tab-content tab-pane fade" id="tab8default">
                                <iframe src="./funciones-especiales/" id="iframeFuncionesEspeciales" height="100%" width="100%" frameborder="0" scrolling="yes" align="center">
                                </iframe>
                            </div>
                        </c:if>
                        <c:if test="${item2=='mfAsignacionTDR'}">
                            <div class="iframe-tab-content tab-pane fade" id="tab9default">
                                <iframe src="./catalogar-tdr/inicio" id="iframeAsignacionTDR" height="100%" width="100%" frameborder="0" scrolling="yes" align="center">
                                </iframe>
                            </div>
                        </c:if>
                        <c:if test="${item2=='reportesFinancierosMf'}">
                            <div style="height: 700px; position: relative; z-index: 1" class="tab-pane fade"  id="tab10default">
                                <iframe src="/sicogen-reportes-mf/home" id="iframeReportesFinancierosTDR" height="100%" width="100%" frameborder="0" scrolling="yes" align="center">
                                </iframe>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div id="loading-spinner" style="z-index: 9999; position: absolute;" class="loader loader-default"
                 data-text="Cargando Informaci&oacute;n..."></div>
            <div class="Acceso" id="divPopUpAmpliadoTDR" style="width:70%;margin-top: 0px; max-height:40%;z-index:1900; display:none;">
                <!--                        <div>
                                            <spam>TDR PRESUPUESTO INICIATIVAS DE INVERSION</spam>
                                        </div>-->
                <br>
                <input id="codigo" style="display: none;" type="text" name="codigo">
                <input id="ejercicio" style="display: none;" type="text" name="ejercicio">
                <input id="periodo2" style="display: none;" type="text" name="periodo2">
                <input id="informe" style="display: none;" type="text" name="informe" value="">
                Programas
                <select id="cbDatosCombo" class="Selectano" onchange="getDatos()" style="width:285px;">
                    <option id="-1" value="Seleccione" data-inputs="-1">Selec. Combo</option>

                </select>
                <br>
                <br>
                <div id="datosAccordion">
                </div>
            </div>

            <div class="desc-informe" id="diaResumenCuadratura" title="Prop"
                 style="border: #0000FF; display:none;width:600px; height:400px;z-index:9999;">
                <div id="contResumenCuadratura"></div>
            </div>

            <div class="Acceso" id="dialogBitacora" title="Bit&aacute;cora"
                 style="width:600px;max-height:400px;z-index:1900; display:none;" class="TextoNombre">
                <div id="" class="TextoPopupPrincipal"></div>
                <div style="width:660px;max-height:400px; margin:auto 0;overflow-y:scroll ;">
                    <div style="float:left;margin:0px 0 0 15px;"></div>
                    <div style="clear: both;display: block; margin: 0px 0 0 15px;">
                        <div style="clear: both;width:620px;float:left;">
                            <label id="estadoSendCGRError" style="display:inline;"> </label>

                        </div>
                    </div>
                    <div style="float:left;margin:-1px 0 0 15px;"></div>
                    <div style="clear: both;display: block; margin: 0 0 20px 15px;height: 300px; ">
                        <div style="clear: both;" class="grillaInformes">
                            <div style="clear: both;" class="rwEncInf">
                                <div style="width:99px;text-align:center;"
                                     class="tituloInfColError">Estado
                                </div>
                                <div style="width:99px;text-align:center;"
                                     class="tituloInfColErrorFinal">Usuario
                                </div>
                                <div style="width:189px;text-align:center;"
                                     class="tituloInfColError">Fecha Procesamiento
                                </div>
                                <div id="divColFechaTramitacion"
                                     style="width:99px;text-align:center;"
                                     class="tituloInfColErrorFinal">Fecha Tramitación
                                </div>
                                <div id="divColFechaEnvio"
                                     style="width:99px;text-align:center;"
                                     class="tituloInfColErrorFinal" style="Display:none; ">Nº de Envio
                                </div>
                            </div>
                            <div class="contEstInfAnual" id="contBitacora"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="Acceso" id="dialogErrorCGF" title="Resumen de Errores"
                 style="width:760px; height:auto;max-height:400px;z-index:2001; display:none;"
                 class="TextoNombre">
                <div id="" class="TextoPopupPrincipal" style="width: 760px;"></div>
                <div style="width:760px;max-height:400px; margin:auto 0;">
                    <div style="float:left;margin:5px 0 0 15px; width: 760px;"></div>
                    <div style="clear: both;display: block; margin: 10px 0 0 15px; width: 760px;">
                    </div>
                    <div style="float:left;margin:5px 0 0 15px; width: 760px;"></div>
                    <div style="clear: both;display: block; margin: 0 0 20px 15px; height: 300px; width: 760px;">
                        <div style="clear: both; width: 760px;" class="grillaInformes">
                            <div style="clear: both;" class="rwEncInf">
                                <div class="tituloInfColError">Tipo</div>
                                <div class="tituloInfColErrorFinal">Error</div>
                            </div>
                            <div class="contEstInfAnual" id="divResumenError" style="overflow:auto;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <noscript>
                ¡Advertencia! JavaScript debe estar habilitado para un correcto funcionamiento de la Administración
            </noscript>
            <div class="clr"></div>
        </div>
        <div class="clr"></div>
    </div>
</div>

<div id="footer" class="footer-main">
    <p class="copyright">
        <a href="#" target="_blank">Contraloria General de la Rep&uacute;blica</a>
        Sistema de Contabilidad General de la Naci&oacute;n<br/>
        Sitio web optimizado para ser visualizado en una resoluci&oacute;n de pantalla de 1024 x 768 p&iacute;xeles, en
        los navegadores iExplorer 10 o superior, Firefox 3.6 o superior y Chrome 8 o superior
        Versi&oacute;n 1.0
    </p>
</div>
</td>
</tr>
</table>
</div>
</body>
</html>


		
