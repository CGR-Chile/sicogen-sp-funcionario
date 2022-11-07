<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 05-08-20
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Ver Informe TDR</title>

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
    <script type="text/javascript" src="${scripts}reporteValidacionTDRMP.js"></script>
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
            <td colspan="3" class="title-rv">TDR Modificación Presupuestaria</td>
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
                        <td>${datosInforme.estado}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>TIPO DE INFORME</strong></td>
                        <td>${datosInforme.tipoInforme}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>INFORME</strong></td>
                        <td>${datosInforme.informe}</td>
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
                        <td>${datosInforme.entidad}</td>
                    </tr>
                    </tbody>
                </table>
                <input type="hidden" id="idFile" value="${idFileUp}"/>
            </td>
        </tr>
        </thead>
    </table>
    <br/>
    <table class="adminlist">
        <thead>
        <tr style="height: 118px;">
            <td style="width:100%">
                <table class="adminlist">
                    <thead>
                    <tr>
                        <td colspan="20" class="title-grid"><div align="center"><span class="Estilo11"><b>Información SISTRADOC</b></span></div></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="row0">
                        <td style="width: 20%;"><strong>ANALISTA SISTRADOC</strong></td>
                        <td>${datosSistradoc.analista}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>ESTADO SICOGEN</strong></td>
                        <td class="row1">${datosSistradoc.estadoSicogen}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>INFORME</strong></td>
                        <td>${datosSistradoc.informe}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>ENTIDAD EMISORA</strong></td>
                        <td>${datosSistradoc.entidadEmisora}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>TIPO DOCUMENTO</strong></td>
                        <td>${datosSistradoc.tipoDocumento}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>NRO. DOCUMENTO</strong></td>
                        <td>${datosSistradoc.nroDocumento}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>FECHA DOCUMENTO</strong></td>
                        <td>${datosSistradoc.fechaDocumento}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>FECHA RECEPCIÓN CGR</strong></td>
                        <td>${datosSistradoc.fechaRecepcionCGR}</td>
                    </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        </thead>
    </table>
    <br/>
    <table class="adminlist" width="100%">
        <thead>
        <tr>
            <td colspan="20" class="title-grid"><div align="center"><span class="Estilo11"><b>Información General XML</b></span></div></td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <table class="adminlist">
                    <tr class="row0">
                        <td style="width: 40%;"><strong>Ejercicio</strong></td>
                        <td>2018</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>Mes</strong></td>
                        <td class="row1">01</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>Dia</strong></td>
                        <td>01</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>SECTOR RESPONSABLE</strong></td>
                        <td>98</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>MONTO TOTAL</strong></td>
                        <td>39</td>
                    </tr>
                </table>
            </td>
            <td>
                <table class="adminlist">
                    <tr class="row0">
                        <td style="width: 40%;"><strong>ID DECRETO</strong></td>
                        <td>9601</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>TIPO DE REGISTRO</strong></td>
                        <td class="row1">02</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>NUMERO REGISTRO</strong></td>
                        <td>aaa</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>FECHA REGISTRO</strong></td>
                        <td>19000000</td>
                    </tr>
                    <tr class="row0">
                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </td>
        </tr>
        </tbody>
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

        <c:forEach items="${datosXML.detalle}" var="detalle">
            <table class="adminlist" width="100%">
                <tbody>
                <table class="adminlist" width="100%">
                    <tbody>
                    <tr class="row0">
                        <td style="width: 20%;"><strong>PARTIDA</strong></td>
                        <td>${detalle.partida}</td>
                    </tr>
                    <tr class="row1">
                        <td><strong>CAPITULO</strong></td>
                        <td>${detalle.capitulo}</td>
                    </tr>
                    <tr class="row0">
                        <td><strong>PROGRAMA</strong></td>
                        <td>${detalle.programa}</td>
                    </tr>
                    </tbody>
                </table>

                <br/>
                <br/>

                <table class="display tablaCuentas" style="width: 100%">
                    <thead>
                    <tr>
                        <th>SUBTITULO</th>
                        <th>ITEM</th>
                        <th>ASIGNACION</th>
                        <th>SUBASIGNACION</th>
                        <th>DENOMINACION</th>
                        <th>INCREMENTO</th>
                        <th>REDUCCION</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="cuenta" items="${detalle.cuentasTDRMP}">
                        <tr>
                            <td style="text-align: center;">${cuenta.subtitulo}</td>
                            <td style="text-align: center;">${cuenta.item}</td>
                            <td style="text-align: center;">${cuenta.asignacion}</td>
                            <td style="text-align: center;">${cuenta.subasignacion}</td>
                            <td style="text-align: left;">${cuenta.denominacion}</td>
                            <td style="text-align: right;">${cuenta.incremento}</td>
                            <td style="text-align: right;">${cuenta.reduccion}</td>
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
            <button id="excelVer" class="boton">Excel</button>
            <button id="cerrar" class="boton" onClick="window.close()">Salir</button>
        </div>
        <div id="pie" class="Pauta_texto">
            <span>Teatinos 56 , Santiago de Chile, Tel&eacutefono 56-2 24021100 -&nbsp;C&oacutedigo Postal: 8340521</span></br>
            <span id="reqmin">Sitio web optimizado para ser visualizado en una resolución de pantalla de 1024 x 768 píxeles, en los navegadores iExplorer 10 o superior, Firefox 3.6 o superior y Chrome 8 o superior</span>
        </div>
    </div>

    <input id="idArchivo" value="${idFileUp}" style="display: none;"/>
    <input id="idSistradoc" value="${idSistradoc}" style="display: none;"/>
    <div id="dialogoDescarga" title="Generando documento...">
        <div class="progress-label" style="margin-top: 5%"><strong>Este proceso puede durar varios segundos...</strong></div>
        <div id="progressbar"></div>
    </div>

</div>
</body>
</html>