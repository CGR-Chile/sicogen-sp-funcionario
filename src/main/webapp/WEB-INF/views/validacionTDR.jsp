<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<style>
    #scrollarea-invalid {
        overflow-y: scroll;
        height: 277px;
    }
    #scrollarea-content{
        min-height:101%;
    }
</style>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <spring:url value="/resources/css/system.css" var="system"/>
    <spring:url value="/resources/css/template.css" var="template"/>
    <spring:url value="/resources/css/rounded.css" var="rounded"/>
    <spring:url value="/resources/css/login.css" var="login"/>
    <spring:url value="/resources/css/pestanas.css" var="pestanas"/>
    <spring:url value="/resources/css/jquery-ui.css" var="jqueryui"/>
    <spring:url value="/resources/css/jquery-confirm.min.css" var="confirm"/>
    <spring:url value="/resources/img/" var="images"/>

    <link type="text/css" href="${system}" rel="stylesheet"/>
    <link type="text/css" href="${template}" rel="stylesheet"/>
    <link type="text/css" href="${rounded}" rel="stylesheet"/>
    <link type="text/css" href="${login}" rel="stylesheet"/>
    <link type="text/css" href="${pestanas}" rel="stylesheet"/>
    <link type="text/css" href="${jqueryui}" rel="stylesheet"/>
    <link type="text/css" href="${confirm}" rel="stylesheet"/>

    <script src="/sicogen-mf/resources/js/jquery-1.11.1.min.js"></script>
    <script src="/sicogen-mf/resources/js/bootstrap.min.js"></script>
    <script src="/sicogen-mf/resources/js/reportes/reporteDeValidacion.js"></script>

    <script type="text/javascript" src="/sicogen-mf/resources/js/popUpAmpliado.js"></script>

    <script type="text/javascript" src="/sicogen-mf/resources/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/validacionTDR.js"></script>

    <script type="text/javascript" src="/sicogen-mf/resources/js/ajax.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/net.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/nu.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/functions.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/jquery-confirm.min.js"></script>


</head>
<body>
<table class="adminlist" width="100%">
    <thead>
    <tr>
        <td colspan="20"><b>A&ntilde;o del documento*:</b>
            <input id="bdAnoDocumento" type="text"/><b>&nbsp;N&uacute;mero Documento:</b>&nbsp;<input id="bdNumDocumento" type="text"/><b>&nbsp;Entidad Emisora:</b><form:select id="bdEntidadEmisora" path="entidadEmisora">
                <form:option value="-1" label="Seleccione Entidad"/>
                <form:options items="${entidadEmisora}" itemValue="nombre" itemLabel="nombre"/>
            </form:select>
            <b>Tipo Documento:</b> <form:select id="bdTipoDocumentoTDR" path="tipoDocumentoTDR">
                <form:option value="-1" label="Seleccione Tipo Documento"/>
                <form:options items="${tipoDocumentoTDR}" itemValue="nombre" itemLabel="nombre"/>
            </form:select> &nbsp;<img style="width: 28px; height: 24px;" title="Buscar" onclick="buscarValidacionTDR();" src="${images}icon_lupa.png"/> </td>
    </tr>
    <tr class="title-grid">
        <td width="8%" class="title-grid"><div align="center"><span class="Estilo11"><b>N&deg; Documento</b></span></div></td>
        <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Entidad Emisora</b></span></div></td>
        <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Tipo Documento</b></span></div></td>
        <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Fecha Documento</b></span></div></td>
        <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Estado</b></span></div></td>
        <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Fecha Validaci&oacute;n</b></span></div></td>
        <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>RV</b></span></div></td>
        <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Validar</b></span></div></td>
    </tr>
    </thead>
</table>
<div id='scrollarea-invalid'>
    <div id="scrollarea-content" style="height:300px; width:100%;position:static; -webkit-shape-margin: initial;"></div>
</div>
<div align="center" id="registros" style="height:30px;visibility:hidden;top:10px;">
    <font class="adminlist">No Existen Registros Asociados a la Consulta....</font>
</div>
</body>
</html>


