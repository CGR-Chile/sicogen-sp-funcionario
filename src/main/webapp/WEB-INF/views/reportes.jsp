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
    <spring:url value="/resources/css/jquery-confirm.min.css" var="confirm"/>
    <spring:url value="/resources/img/" var="images"/>

    <link type="text/css" href="${system}" rel="stylesheet"/>
    <link type="text/css" href="${template}" rel="stylesheet"/>
    <link type="text/css" href="${rounded}" rel="stylesheet"/>
    <link type="text/css" href="${login}" rel="stylesheet"/>
    <link type="text/css" href="${pestanas}" rel="stylesheet"/>
    <link type="text/css" href="${confirm}" rel="stylesheet"/>

    <script type="text/javascript" src="/sicogen-mf/resources/js/seguimiento/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/jquery-confirm.min.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/reportes.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/ajax.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/net.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/nu.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/functions.js"></script>


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
        <td colspan="20" class="colspan-padding"><b>Tipo de Informe:</b><form:select id="contTipoInformes" path="tipoInforme" onchange="cargaInformexTipoReporte2(this.value);">
            <form:options items="${tipoInforme}"  itemValue="codigo" itemLabel="nombre"/>
        </form:select> <b>&nbsp;Reporte:</b>&nbsp;<form:select id="cbReporte" path="tipoReportes" onchange="seleccionaDivReportes();">
                <form:options items="${tipoReportes}"  itemValue="id" itemLabel="nombre"/>
            </form:select>
    </tr>
    </thead>
</table>
<div id="RC" style="display: none;">
    <table class="adminlist" width="100%">
        <thead>
    <tr>
        <td colspan="20" class="colspan-padding"><b>Ejercicio:</b><form:select id="cbEjercicio" path="ejercicios" onchange="cargaPeriodo(this.value);cargaPartida(this.value);">
            <form:options items="${ejercicios}" itemValue="ejercicioId" itemLabel="ejercicioNombre"/>
        </form:select><b><b>&nbsp;Periodo:<select style="width: 227px;" name="contPeriodos" id="contPeriodos" class="combo" onChange="cargarInformesPeriodoRC(this.value);">
            <option value="-1">Todos</option>
        </select></b>&nbsp;Informe:</b><select style="width: 227px;" name="contInforme" id="contInforme" class="combo">
            <option value="-1">Todos</option>
        </select>&nbsp;<b>Partida:</b><select style="width: 227px;" name="contPartidas" id="contPartidas" class="combo" onChange="cargaCapitulo(this.value);">
                <option value="0">Seleccione Partida</option>
            </select> <b>&nbsp;Cap&iacute;tulo:</b> <select style="width: 227px;" name="contCapitulo" id="contCapitulo" class="combo">
            <option value="0">Seleccione</option>
        </select>&nbsp;<img style="width: 28px; height: 24px;" title="Buscar" onclick="buscarReporteCumpl();" src="${images}icon_lupa.png"/> </td>
    </tr>
    </thead>
</table>
</div>
<div id="RERE" style="display: none;">
    <table class="adminlist" width="100%">
        <thead>
        <tr>
            <td colspan="20" class="colspan-padding"><b>Ejercicio:</b><form:select id="cbEjercicio" path="ejercicios" onchange="cargaPeriodo(this.value);cargaPartida(this.value);">
                <form:options items="${ejercicios}" itemValue="ejercicioId" itemLabel="ejercicioNombre"/>
            </form:select><b><b>&nbsp;Periodo:<select style="width: 227px;" name="contPeriodosRERE" id="contPeriodosRERE" class="combo">
                <option value="-1">Todos</option>
            </select></b>&nbsp;<b>Partida:</b><select style="width: 227px;" name="contPartidasRERE" id="contPartidasRERE" class="combo" onChange="cargaCapitulo(this.value);">
                <option value="0">Seleccione Partida</option>
            </select> <b>&nbsp;Cap&iacute;tulo:</b> <select style="width: 227px;" name="contCapituloRERE" id="contCapituloRERE" class="combo">
                <option value="0">Seleccione</option>
            </select>&nbsp;<img style="width: 28px; height: 24px;" title="Buscar" onclick="buscarReporteEnvio();" src="${images}icon_lupa.png"/></b> </td>
        </tr>
        </thead>
    </table>
</div>
<div id="RIR" style="display: none;">
    <table class="adminlist" width="100%">
        <thead>
        <tr>
            <td colspan="20" class="colspan-padding"><b>Ejercicio:</b><form:select id="cbEjercicio" path="ejercicios" onchange="cargaPeriodo(this.value);cargaPartida(this.value);">
                <form:options items="${ejercicios}" itemValue="ejercicioId" itemLabel="ejercicioNombre"/>
            </form:select><b><b>&nbsp;Informe:</b><select style="width: 227px;" name="contInformeRIR" id="contInformeRIR" class="combo">
                <option value="-1">Todos</option>
            </select>&nbsp;Periodo:<select style="width: 227px;" name="contPeriodosRIR" id="contPeriodosRIR" class="combo" onChange="cargarInformesPeriodoRC(this.value);">
                <option value="-1">Todos</option>
            </select></b>&nbsp;<b>Partida:</b><select style="width: 227px;" name="contPartidasRIR" id="contPartidasRIR" class="combo" onChange="cargaCapitulo(this.value);">
                <option value="0">Seleccione Partida</option>
            </select> <b>&nbsp;Cap&iacute;tulo:</b> <select style="width: 227px;" name="contCapituloRIR" id="contCapituloRIR" class="combo">
                <option value="0">Seleccione</option>
            </select></td>
        </tr>
        </thead>
    </table>

</div>

<div id="RCORR" style="display: none;">
    <table class="adminlist" width="100%">
        <thead>
        <tr>
            <td colspan="20" class="colspan-padding"><b>Ejercicio:</b><form:select id="cbEjercicio" path="ejercicios" onchange="cargaPeriodo(this.value);cargaPartida(this.value);">
                <form:options items="${ejercicios}" itemValue="ejercicioId" itemLabel="ejercicioNombre"/>
            </form:select><b><b>&nbsp;Informe:</b><select style="width: 227px;" name="contInformeRCORR" id="contInformeRCORR" class="combo">
                <option value="-1">Todos</option>
            </select>&nbsp;Periodo:<select style="width: 227px;" name="contPeriodosRCORR" id="contPeriodosRCORR" class="combo" onChange="cargarInformesPeriodoRC(this.value);">
                <option value="-1">Todos</option>
            </select></b>&nbsp;<b>Partida:</b><select style="width: 227px;" name="contPartidasRCORR" id="contPartidasRCORR" class="combo" onChange="cargaCapitulo(this.value);">
                <option value="0">Seleccione Partida</option>
            </select> <b>&nbsp;Cap&iacute;tulo:</b> <select style="width: 227px;" name="contCapituloRCORR" id="contCapituloRCORR" class="combo">
                <option value="0">Seleccione</option>
            </select></td>
        </tr>
        </thead>
    </table>
</div>

<div id="RB" style="display: none;">
    <table class="adminlist" width="100%">
        <thead>
        <tr>
            <td colspan="20" class="colspan-padding"><b>Ejercicio:</b><form:select id="cbEjercicio" path="ejercicios" onchange="cargaPeriodo(this.value);cargaPartida(this.value);">
                <form:options items="${ejercicios}" itemValue="ejercicioId" itemLabel="ejercicioNombre"/>
            </form:select><b>&nbsp;Informe:</b><select style="width: 227px;" name="contInformeRB" id="contInformeRB" class="combo">
                <option value="-1">Todos</option>
            </select>&nbsp;Periodo:<select style="width: 227px;" name="contPeriodosRB" id="contPeriodosRB" class="combo" onChange="cargarInformesPeriodoRC(this.value);">
                <option value="-1">Todos</option>
            </select></b>&nbsp;<b>Partida:</b><select style="width: 227px;" name="contPartidasRB" id="contPartidasRB" class="combo" onChange="cargaCapitulo(this.value);">
                <option value="0">Seleccione Partida</option>
            </select> <b>&nbsp;Cap&iacute;tulo:</b> <select style="width: 227px;" name="contCapituloRB" id="contCapituloRB" class="combo">
                <option value="0">Seleccione</option>
            </select>&nbsp;<b>&nbsp;Tipo:</b> <select id="cbTipo"  class="Selectano" style="width:77px;">
                <option value="0">Original</option>
                <option value="1">Correcciones</option>
                </select></td>
        </tr>
        </thead>
    </table>
</div>
<input type="hidden" id="oculto"/>
<div id="tablaUno">
<table class="adminlist" width="100%">
    <thead>
          <tr>
            <td colspan="20" class="title-table"><div align="center"><span class="Estilo11"><b>Reporte de Cumplimiento</b></span></div></td>
        </tr>
        <tr class="title-grid">
            <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Informe</b></span></div></td>
            <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Periodo Contable</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Partida</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Capitulo</b></span></div></td>
            <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Enviado Por</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Fecha de Env&iacute;o</b></span></div></td>
        </tr>
    </thead>
</table>
</div>
    <div id="tablaDos" style="display: none;">
        <table class="adminlist" width="100%">
            <thead>
        <tr>
            <td colspan="20" class="title-table"><div align="center"><span class="Estilo11"><b>Reporte de Env&iacute;os Realizados por Entidad</b></span></div></td>
        </tr>
        <tr class="title-grid">
            <td width="8%" class="title-grid"><div align="center"><span class="Estilo11"><b>N&ordm; Transacci&oacute;n</b></span></div></td>
            <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Periodo Contable</b></span></div></td>
            <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Fecha de Env&iacute;o</b></span></div></td>
            <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Enviado por</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Certificado de Env&iacute;o</b></span></div></td>

        </tr>
            </thead>
        </table>
    </div>
    <div id="tablaCuatro" style="display: none;">
        <table class="adminlist" width="100%">
            <thead>
        <tr>
            <td colspan="10" class="title-table"><div align="center"><span class="Estilo11"><b>Reporte de Correcciones</b></span></div></td>
        </tr>
        <tr class="title-grid">
            <td width="8%" class="title-grid"><div align="center"><span class="Estilo11"><b>Informe</b></span></div></td>
            <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Periodo Contable</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Partida</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Capitulo</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Fecha de Correcci&oacute;n</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Enviado por</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Comentario</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Informaci&oacute;n Respaldo</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Certificado Env&iacute;o</b></span></div></td>

        </tr>
            </thead>
        </table>
    </div>
    <div id="tablaSeis" style="display: none;">
        <table class="adminlist" width="100%">
            <thead>
        <tr>
            <td colspan="10" class="title-table"><div align="center"><span class="Estilo11"><b>Reporte de Informes Reversados</b></span></div></td>
        </tr>
        <tr class="title-grid">
            <td width="8%" class="title-grid"><div align="center"><span class="Estilo11"><b>Informe</b></span></div></td>
            <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Periodo Contable</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Partida</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Capitulo</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Ejercicio</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Fecha de reverso</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Informaci&oacute;n Respaldo</b></span></div></td>


        </tr>
            </thead>
        </table>
    </div>

    <div id="tablaCinco" style="display: none;">
        <table class="adminlist" width="100%">
            <thead>
        <tr>
            <td colspan="10" class="title-table"><div align="center"><span class="Estilo11"><b>Reporte de Bit&aacute;cora de Acciones</b></span></div></td>
        </tr>
        <tr class="title-grid">
            <td width="8%" class="title-grid"><div align="center"><span class="Estilo11"><b>Informe</b></span></div></td>
            <td width="20%" class="title-grid"><div align="center"><span class="Estilo11"><b>Periodo Contable</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Partida</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Capitulo</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Tipo</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Estado</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Usuario</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>Fecha Proc</b></span></div></td>
            <td width="10%" class="title-grid"><div align="center"><span class="Estilo11"><b>N&ordm; de Envio</b></span></div></td>


        </tr>
            </thead>
        </table>
    </div>
<div id='scrollarea-invalid'>
    <div id="scrollarea-content" style="display:none;height:300px; width:100%;position:static; -webkit-shape-margin: initial;"></div>
</div>
    <div align="center" id="registros" style="overflow:auto;height:30px;visibility:hidden;top:180px;">
        <font class="adminlist">No Existen Registros Asociados a la Consulta....</font>
    </div>
</body>
</html>
<script>
    initial();
</script>
