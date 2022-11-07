<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Ver Informe PI</title>

    <spring:url value="/resources/css/" var="styles"/>
    <spring:url value="/resources/img/" var="images"/>
    <spring:url value="/resources/js/" var="scripts"/>

    <link type="text/css" href="${styles}rv.css" rel="stylesheet"/>
    <link type="text/css" href="${styles}system.css" rel="stylesheet"/>
    <link type="text/css" href="${styles}template.css" rel="stylesheet"/>
    <link type="text/css" href="${styles}tipr/tipr.css" rel="stylesheet"/>
    <link type="text/css" href="${styles}data-tables/jquery.dataTables.min.css" rel="stylesheet"/>
    <link type="text/css" href="${styles}jquery-confirm/3.3.2/jquery-confirm.min.css" rel="stylesheet"/>
    <link type="text/css" href="${styles}jquery-ui/1.12.1/jquery-ui.css" rel="stylesheet"/>
    <link type="text/css" href="${styles}reporteValidacion/cssInformeBase.css" rel="stylesheet"/>

    <script type="text/javascript" src="${scripts}jquery/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="${scripts}tipr/tipr.min.js"></script>
    <script type="text/javascript" src="${scripts}data-tables/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${scripts}jquery.fileDownload.js"></script>
    <script type="text/javascript" src="${scripts}jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
    <script type="text/javascript" src="${scripts}jquery-ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="${scripts}reporteValidacionPI.js"></script>
    <style type="text/css">
        .contenedor {
            width: 60%;
            height: 100%;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>

<body>
<div class="contenedor">
    <table class="adminlist">
        <thead>
        <tr style="height: 120px;">
            <td class="sicogenlogo" colspan="6"><img src="${images}sicogenii_logo.png" style="height:96px;width:322px;"/></td>
        </tr>
        <tr></tr>
        <tr style="background-color: #d7d7d9;text-align:center;">
            <td colspan="3" class="title-rv">LEY DE PRESUPUESTO</td>
        </tr>
        <tr style="height: 118px;">
            <td style="width:100%">
                <table class="adminlist" width="100%">
                    <thead>
                    <tr>
                        <td colspan="20" class="title-grid"><div align="center"><span class="Estilo11"><b>Información General</b></span></div></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="row0">
                        <td style="width: 20%;"><strong>ESTADO DE VALIDACIÓN</strong></td>
                        <td>${datosInforme.estadoValidacion}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>TIPO DE INFORME</strong></td>
                        <td class="row1">${datosInforme.tipoInforme}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>INFORME</strong></td>
                        <td>${datosInforme.nombreInforme}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>PERÍODO</strong></td>
                        <td>${datosInforme.periodo}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>EJERCICIO</strong></td>
                        <td>${datosInforme.ejercicio}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>USUARIO</strong></td>
                        <td>${datosInforme.usuario}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>ENTIDAD</strong></td>
                        <td>${datosInforme.entdad}</td>
                    </tr>
                    </tbody>
                </table>
                <input type="hidden" id="idFile" value="${idFileUp}"/>
            </td>
        </tr>
        </thead>
    </table>
    <br/>

    <div style="text-align: right;">
        <a href="javascript:void(0)" onclick="toggleDetalle('${images}');"><img class="toggleImg" src="${images}notice-alert.png" style="width: 20px; height: 20px;"/></a>
    </div>

    <div class="detalleInformeDiv">
        <table class="adminlist" width="100%">
            <thead>
            <tr>
                <td colspan="20" class="title-grid"><div align="center"><span class="Estilo11"><b>Detalle de Archivo Cargado</b></span></div></td>
            </tr>
            </thead>
        </table>

        <c:forEach items="${datosInforme.listaEntidades}" var="entidad">
            <table class="adminlist">
                <tbody>
                <table class="adminlist">
                    <tbody>
                    <tr class="row0">
                        <td style="width: 20%;"><strong>PARTIDA</strong></td>
                        <td>${entidad.partida}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>CAPITULO</strong></td>
                        <td>${entidad.capitulo}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>PROGRAMA</strong></td>
                        <td>${entidad.programa}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>TOTAL INGRESOS CLP</strong></td>
                        <td>${entidad.totalIngresosCLP}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>TOTAL GASTOS CLP</strong></td>
                        <td>${entidad.totalGastosCLP}</td>
                    </tr>
                    </tbody>
                </table>

                <br/>
                <br/>

                <table class="display tablaCuentas" style="width: 100%">
                    <thead>
                    <tr>
                        <th>CUENTA</th>
                        <th>TIPO CUENTA</th>
                        <th>DENOMINACIÓN DE LA CUENTA</th>
                        <th>GLOSAS</th>
                        <th>MONTO CLP</th>
                        <th>MONTO USD</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="cuenta" items="${entidad.listaDetalleCuentas}">
                        <tr>
                            <td style="text-align: center;">${cuenta.cuenta}</td>
                            <td style="text-align: center;">${cuenta.tipoCuenta}</td>
                            <td style="text-align: left;">${cuenta.denominacionCuenta}</td>
                            <td style="text-align: center;">${cuenta.glosas}</td>
                            <td style="text-align: right;">${cuenta.montoCLP}</td>
                            <td style="text-align: right;">${cuenta.montoUSD}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </tbody>
            </table>

            <br/>
            <br/>
            <br/>
        </c:forEach>
    </div>

    <div style="text-align:center;width:100%">
        <div id="contBotones">
            <hr class="separador"/>
            <button id="cerrar" class="boton" onClick = "window.close()">Salir</button>
        </div>
        <div id="pie" class="Pauta_texto">
            <span>Teatinos 56 , Santiago de Chile, Tel&eacutefono 56-2 24021100 -&nbsp;C&oacutedigo Postal: 8340521</span></br>
            <span id="reqmin">Sitio web optimizado para ser visualizado en una resolución de pantalla de 1024 x 768 píxeles, en los navegadores iExplorer 10 o superior, Firefox 3.6 o superior y Chrome 8 o superior</span>
        </div>
    </div>

</div>
</body>
</html>