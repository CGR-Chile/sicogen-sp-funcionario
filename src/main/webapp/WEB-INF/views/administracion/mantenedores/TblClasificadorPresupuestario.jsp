<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-11-19
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<spring:url value="/resources/img" var="images"/>
<html>
<head>
    <link rel="stylesheet" href="${css}/inputsCss.css" type="text/css" />
    <link rel="stylesheet" href="${css}/EstilosPaginas.css" type="text/css" />
    <link rel="stylesheet" href="${css}/jquery-ui/1.12.1/jquery-ui.css" type="text/css" />
    <link type="text/css" href="${css}/css-loader.css" rel="stylesheet"/>
    <link href="${jtable}/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${js}/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${js}/jquery-ui/1.12.1/jquery-ui.js"></script>
    <script src="${jtable}/jquery.jtable.js" type="text/javascript"></script>
    <script src="${jtable}/localization/jquery.jtable.es.js" type="text/javascript"></script>
    <script type="text/javascript" src="${js}/jquery.inputmask.min.js"></script>
    <script type="text/javascript" src="${js}/mantenedores/tblClasificadorPresupuestario.js"></script>
    <script type="text/javascript" src="${js}/jquery.numeric.js"></script>
</head>
<body>
<div style="position: absolute; margin-left: 10px;margin-top: 10px;">

    <div style="font: bold 12px sans-serif; float:left;">Ejercicio<br />
        <select id="slctEjercicio" class="Selectano" style="width:150px;">
            <c:forEach var="ejer" items="${listaEjercicios}">
                <option value="${ejer.ejercicioId}">${ejer.ejercicioNombre}</option>
            </c:forEach>
        </select>
    </div>

    <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">* Partida<br />
        <select id="slctPartida" class="Selectano" style="width:250px;">
            <option value="-1">Seleccione Partida</option>
            <c:forEach var="part" items="${listaPartidas}">
                <option value="${part.idPartida}">${part.nombrePartida}</option>
            </c:forEach>
        </select>
    </div>

    <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">* Capítulo<br/>
        <select id="slctCapitulo" class="Selectano" style="width:250px;">
            <option value="-1">Selecciona Capítulo</option>
        </select>
    </div>

    <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Programa<br />
        <select  id="slctPrograma" class="Selectano" style="width:250px;">
            <option value="-1">Todos</option>
        </select>
    </div>

    <div style="margin-left:15px;float: left; width: 250px;text-align: center;">
        <button class="boton" style="float: initial;margin-top: 10px;" id="btnBuscarCuentas" onclick="loadTable();">Buscar</button><br/>
        <span>Los filtros marcados con * son obligatorios</span>
    </div>
    <div style="margin-left:15px;float: left; width: 250px;text-align: center;">
        <button class="boton" style="float: initial;margin-top: 10px;" id="btnCrearCuenta" onclick="openDialogCreateCtaParticular();">Crear</button><br/>
    </div>
</div>

<div id="ExpenseTableContainer" style="position: absolute; margin-top: 70px;"></div>

<div id="loading-spinner-mant" style="z-index: 9999; position: absolute;" class="loader loader-default"
     data-text="Cargando Informaci&oacute;n..."></div>
<div id="dialogInfo" title="Información"></div>
<div id="dialogCreate" title="Información">
    <p>Cuenta creada con éxito.</p>
</div>
<div id="dialogCreateCuenta" title="Crear Cuenta Particular">
    <div style="position: absolute; margin-left: 10px;margin-top: 10px;">

        <div style="font: bold 12px sans-serif; float:left;">Ejercicio: <br />
            <select id="slctEjercicioCreate" class="Selectano" style="width:150px;">
                <c:forEach var="ejer" items="${listaEjercicios}">
                    <option value="${ejer.ejercicioId}">${ejer.ejercicioNombre}</option>
                </c:forEach>
            </select>
        </div>

        <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Partida: <br />
            <select id="slctPartidaCreate" class="Selectano" style="width:250px;">
                <option value="-1">Seleccione Partida</option>
                <c:forEach var="part" items="${listaPartidas}">
                    <option value="${part.idPartida}">${part.nombrePartida}</option>
                </c:forEach>
            </select>
        </div>

        <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Capítulo: <br/>
            <select id="slctCapituloCreate" class="Selectano" style="width:250px;">
                <option value="-1">Selecciona Capítulo</option>
            </select>
        </div>

        <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Programa: <br />
            <select  id="slctProgramaCreate" class="Selectano" style="width:250px;">
                <option value="-1">Seleccione Programa</option>
            </select>
        </div>
    </div>
    <div style="position: absolute; margin-left: 10px;margin-top: 60px;">

        <div style="font: bold 12px sans-serif; float:left;">Código Cuenta: <br />
            <input type="text" class="Inputs" id="codCtaParticularCreate" style="width:250px;"/>
        </div>

        <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Descripción: <br />
            <input type="text" class="Inputs" id="descCtaParticularCreate" style="width:400px;"/>
        </div>

        <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Imputación Presupuestaria: <br>
            <input type="radio" name="pnImpPresup" value="1" class="checkImp" checked> Si<br>
            <input type="radio" name="pnImpPresup" value="0" class="checkImp"> No<br>
        </div>
    </div>

    <div style="position: absolute; margin-left: 10px;margin-top: 120px;">

        <div style="font: bold 12px sans-serif; float:left;">Origen: <br>
            <input type="radio" name="checkOrigenCtaPart" value="0" class="checkOrigenCtaPart" checked> Ley de Presupuesto<br>
            <input type="radio" name="checkOrigenCtaPart" value="1" class="checkOrigenCtaPart"> Decreto<br>
        </div>

        <div id="datosDecretoCreateCtaPart" style="float: left;display: none;">
            <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Nº de Documento: <br />
                <input type="text" class="Inputs" id="nroDocumentoCtaPart" style="width:100px;"/>
            </div>

            <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Año: <br />
                <input type="text" class="Inputs" id="anioDocumentoCtaPart" style="width:50px;"/>
            </div>
        </div>
    </div>
</div>
<div id="dialogEditCuenta" title="Editar Cuenta Particular">
</div>
<div id="dialogUpdate" title="Información">
    <p>Cuenta actualizada con éxito.</p>
</div>
<div id="dialogDesCta" title="Información">
    <p>¿Está seguro que desea desactivar el registro seleccionado? También se desactivarán sus registros dependientes, en caso de existir.</p>
    <div id="divTblCtasDepenDes"></div>
</div>
<div id="dialogActCta" title="Información">
    <p>¿Está seguro que desea activar el registro seleccionado? También se activarán sus registros dependientes, en caso de existir.</p>
    <div id="divTblCtasDepenAct"></div>
</div>
<div id="dialogBitacora" title="Bitácora de actualizaciones">
</div>
<input type="hidden" value="${jtable}" id="rutaImagenes">
</body>
</html>
