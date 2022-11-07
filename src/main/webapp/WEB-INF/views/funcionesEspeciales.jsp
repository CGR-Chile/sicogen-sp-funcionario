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
    <spring:url value="/resources/css/mensaje/jquery.alerts.css" var="jqueryal"/>
    <spring:url value="/resources/css/jquery-ui.css" var="jqueryui"/>
    <spring:url value="/resources/img/" var="images"/>

    <link type="text/css" href="${system}" rel="stylesheet"/>
    <link type="text/css" href="${template}" rel="stylesheet"/>
    <link type="text/css" href="${rounded}" rel="stylesheet"/>
    <link type="text/css" href="${login}" rel="stylesheet"/>
    <link type="text/css" href="${pestanas}" rel="stylesheet"/>
    <link type="text/css" href="${jqueryal}" rel="stylesheet"/>
    <link type="text/css" href="${jqueryui}" rel="stylesheet"/>

    <script type="text/javascript" src="/sicogen-mf/resources/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/mensaje/jquery.alerts.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/funcionesEspeciales.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/ajax.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/net.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/nu.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/functions.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/dom-drag.js"></script>

    <style>
        #scrollarea-invalid {
            overflow-y: scroll;
            height: 277px;
        }
        #scrollarea-content{
            min-height:101%;
        }
    </style>

</head>

<body>
<table class="adminlist" width="100%">
    <thead>
    <tr>
        <td colspan="20"><b>Funci&oacute;n a Realizar:</b> <select id="cbFuncionRealizar"
                                                                class="Selectano"
                                                                onchange="seleccionaTabla(this.value)"
                                                                style="width:155px;">
        <option id="-1" value="1" data-inputs="-1">Reprocesos</option>
        </select>
        </td>
    </tr>
    </thead>
</table>
<table class="adminlist" width="100%">
        <thead>
        <tr>
            <td colspan="10"><div align="center"><span class="Estilo11"><b>Reprocesos Enviados</b></span></div></td>
        </tr>
        <tr class="title-grid">
            <td width="30%" class="title-grid"><div align="center"><span class="Estilo11"><b>Partida</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Capitulo</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Informe</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Periodo</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Ejercicio</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Autorizar</b></span></div></td>
        </tr>
        </thead>
    </table>


<div id='scrollarea-invalid'>
    <div id="scrollarea-content" style="height:300px; width:100%;position:static; -webkit-shape-margin: initial;"></div>
</div>
<div id="root" style="display:none; position: absolute; z-index: 2; top: 100px; left: 620px; width: 240px; height: 200px; cursor:move;">
    <table class="adminform" width="100%">
        <thead>
        <tr>
            <th align="center" colspan="2" bgcolor="#3366CC" id="handle" onMouseOver="this.style.cursor='move'">
                <div id="tituloMsg" align="center" class="blancobold14"></div>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="40">
                <img id="imagenMsg" border="0" src=""/></td>
            <td>
                <p class="azul11nobold" id="textoMsg"></p>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="2">
                <table width="100%">
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <div class="button_holder">
                                <div class="button1">
                                    <div class="next">
                                        <a onclick="javascript:ocultarDiv('root');">Aceptar</a>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        </tfoot>
    </table>
</div>
    <div align="center" id="registros" style="overflow:auto;height:30px;visibility:hidden;top:180px;">
        <font class="adminlist">No Existen Registros Asociados a la Consulta....</font>
    </div>
</body>
</html>
<script>
    var theHandle = document.getElementById("handle");
    var theRoot   = document.getElementById("root");
    Drag.init(theHandle, theRoot);
</script>
