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
    <title>Reporte de Validacion TDRII</title>

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
            width: 80%;
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
                    <td colspan="3" class="title-rv">REPORTE DE VALIDACIÓN</td>
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
                                    <td><strong>ENTIDAD EMISORA</strong></td>
                                    <td>${datosSistradoc.entidadEmisora}</td>
                                </tr>
                                <tr class="row1">
                                    <td><strong>TIPO DOCUMENTO</strong></td>
                                    <td>${datosSistradoc.tipoDocumento}</td>
                                </tr>
                                <tr class="row0">
                                    <td><strong>NRO. DOCUMENTO</strong></td>
                                    <td>${datosSistradoc.nroDocumento}</td>
                                </tr>
                                <tr class="row1">
                                    <td><strong>FECHA DOCUMENTO</strong></td>
                                    <td>${datosSistradoc.fechaDocumento}</td>
                                </tr>
                                <tr class="row0">
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
        <table class="adminlist">
            <thead>
                <tr style="height: 118px;">
                    <td style="width:100%">
                        <table class="adminlist">
                            <thead>
                            <tr>
                                <td colspan="20" class="title-grid"><div align="center"><span class="Estilo11"><b>Errores Genéricos</b></span></div></td>
                            </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${fn:length(datosXML.erroresGenericos) gt 0}">
                                        <c:forEach items="${datosXML.erroresGenericos}" var="errorGenerico" varStatus="loopStatus">
                                            <c:choose>
                                                <c:when test="${loopStatus.index % 2 == 0}">
                                                    <tr class="row0">
                                                        <c:choose>
                                                            <c:when test="${errorGenerico.tipoError eq 1}">
                                                                <td><img src="${images}Bloqueante1.png"/></td>
                                                                <td>${errorGenerico.mensaje}</td>
                                                            </c:when>
                                                            <c:when test="${errorGenerico.tipoError eq 2}">
                                                                <td><img src="${images}Bloqueante2.png"/></td>
                                                                <td>${errorGenerico.mensaje}</td>
                                                            </c:when>
                                                            <c:when test="${errorGenerico.tipoError eq 3}">
                                                                <td><img src="${images}Bloqueante3.png"/></td>
                                                                <td>${errorGenerico.mensaje}</td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td>${errorGenerico.mensaje}</td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr class="row1">
                                                        <c:choose>
                                                            <c:when test="${errorGenerico.tipoError eq 1}">
                                                                <td class="erroresEncabezados1">${errorGenerico.mensaje}</td>
                                                            </c:when>
                                                            <c:when test="${errorGenerico.tipoError eq 2}">
                                                                <td class="erroresEncabezados2">${errorGenerico.mensaje}</td>
                                                            </c:when>
                                                            <c:when test="${errorGenerico.tipoError eq 3}">
                                                                <td class="erroresEncabezados3">${errorGenerico.mensaje}</td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td>${errorGenerico.mensaje}</td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </tr>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="row0">
                                            <td>El informe no presenta errores genéricos</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </thead>
        </table>

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

            <c:forEach var="decreto" items="${objXmlII.decreto_II}">
                <table class="adminlist" width="100%">
                    <tbody>
                        <table class="adminlist" width="100%">
                            <tbody>
                                <tr class="row0">
                                    <td style="width: 20%;"><strong>PARTIDA</strong></td>
                                    <td>${decreto.codigoPartida} - ${decreto.nombrePartida}</td>
                                </tr>
                                <tr class="row1">
                                    <td><strong>CAPITULO</strong></td>
                                    <td>${decreto.codigoCapitulo} - ${decreto.nombreCapitulo}</td>
                                </tr>
                                <tr class="row0">
                                    <td><strong>PROGRAMA</strong></td>
                                    <td>${decreto.codigoPrograma} - ${decreto.nombrePrograma}</td>
                                </tr>
                            </tbody>
                        </table>

                        <br/>
                        </br>

                        <c:forEach var="ident" items="${decreto.identificacionProyectos}">
                            <table class="adminlist" width="100%">
                                <tbody>
                                    <tr class="row0">
                                        <td><strong>IDENTIFICACIÓN</strong></td>
                                    </tr>
                                </tbody>
                            </table>
                            </br>
                            <table class="adminlist" width="100%">
                                <tbody>
                                    <tr class="row0">
                                        <td style="width: 33%;"><strong>SUBTITULO: </strong>${ident.codigoSubtitulo}</td>
                                        <td style="width: 33%;"><strong>ITEM: </strong>${ident.codigoItem}</td>
                                        <td style="width: 33%;"><strong>MONEDA: </strong>${ident.moneda}</td>
                                    </tr>
                                </tbody>
                            </table>
                            <table class="adminlist" width="100%">
                                <thead>
                                    <tr>
                                        <th class="title-grid">BIP</th>
                                        <th class="title-grid">DENOMINACIÓN</th>
                                        <c:forEach var="asig" items="${ident.detalleIdentificacionProyectos[0].montosAsignacion}">
                                            <th class="title-grid">${asig.codigoAsignacion}</th>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th class="title-grid"></th>
                                        <th class="title-grid"></th>
                                        <c:forEach var="asig" items="${ident.detalleIdentificacionProyectos[0].montosAsignacion}">
                                            <th class="title-grid">${asig.nombreAsignacion}</th>
                                        </c:forEach>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="detIdent" items="${ident.detalleIdentificacionProyectos}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${status.index % 2 == 0}">
                                                <c:set var="rowClass" value="row0"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="rowClass" value="row1"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${empty listaErrores.erroresDetalle}">
                                                <tr class="<c:out value="${rowClass}"/>">
                                                    <td>${detIdent.codigoBIP}</td>
                                                    <td>${detIdent.denominacion}</td>
                                                    <c:forEach var="asig" items="${detIdent.montosAsignacion}">
                                                        <td style="text-align: right;">${asig.monto}</td>
                                                    </c:forEach>
                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach var="asig" items="${detIdent.montosAsignacion}">
                                                    <c:forEach var="error" items="${listaErrores.erroresDetalle.errores}">
                                                        <c:set var="cuenta" value="${ident.codigoSubtitulo}.${ident.codigoItem}.${asig.codigoAsignacion}.000" />
                                                        <c:if test="${error.cuenta eq cuenta and
                                                                error.codigoPartida eq decreto.codigoPartida and
                                                                error.codigoCapitulo eq decreto.codigoCapitulo and
                                                                error.codigoPrograma eq decreto.codigoPrograma}">
                                                            <c:set var="mensajesError" value="${mensajesError}${empty mensajesError ? '' : '&nbsp;'}&#8226; ${error.mensaje}" />
                                                            <c:set var="tipoError" value="${error.tipoError}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${!empty tipoError}">
                                                        <c:choose>
                                                            <c:when test="${tipoError eq 1}">
                                                                <c:set var="rowClass" value="rowErr1"></c:set>
                                                            </c:when>
                                                            <c:when test="${tipoError eq 2}">
                                                                <c:set var="rowClass" value="rowErr2"></c:set>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="rowClass" value="rowErr3"></c:set>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:if>
                                                </c:forEach>
                                                <c:choose>
                                                    <c:when test="${empty mensajesError}">
                                                        <tr class="<c:out value="${rowClass}"/>">
                                                            <td>${detIdent.codigoBIP}</td>
                                                            <td>${detIdent.denominacion}</td>
                                                            <c:forEach var="asig" items="${detIdent.montosAsignacion}">
                                                                <td style="text-align: right;">${asig.monto}</td>
                                                            </c:forEach>
                                                        </tr>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <tr class="<c:out value="${rowClass}"/> detalleError" data-tip="<c:out value="${mensajesError}"/>">
                                                            <td>${detIdent.codigoBIP}</td>
                                                            <td>${detIdent.denominacion}</td>
                                                            <c:forEach var="asig" items="${detIdent.montosAsignacion}">
                                                                <td style="text-align: right;">${asig.monto}</td>
                                                            </c:forEach>
                                                        </tr>
                                                        <c:set var="mensajesError" value=""/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </tbody>
                            </table>

                            </br>
                            </br>

                            <c:if test="${!empty ident.compromisosIdentificaciones.limiteMaximoCompromisos}">
                                <table class="adminlist" width="100%">
                                    <tbody>
                                    <tr class="row0">
                                        <td><strong>LIMITE MAXIMO IDENTIFICACIÓN</strong></td>
                                    </tr>
                                    </tbody>
                                </table>

                                </br>

                                <table class="adminlist" width="100%">
                                    <thead>
                                        <tr>
                                            <th class="title-grid">BIP</th>
                                            <th class="title-grid">DENOMINACIÓN</th>
                                            <th class="title-grid">AÑO</th>
                                        </tr>
                                        <tr>
                                            <th class="title-grid"></th>
                                            <th class="title-grid"></th>
                                            <c:forEach var="comFut" items="${ident.compromisosIdentificaciones.limiteMaximoCompromisos[0].montosAnio}">
                                                <th class="title-grid">${comFut.anio}</th>
                                            </c:forEach>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="rowClass" value="row0"/>
                                        <c:forEach var="detproy" items="${ident.detalleIdentificacionProyectos}">
                                            <c:forEach var="lim" items="${ident.compromisosIdentificaciones.limiteMaximoCompromisos}">
                                                <c:if test="${detproy.codigoBIP == lim.codigoBIP}">
                                                    <tr class="<c:out value="${rowClass}"/>">
                                                        <td>${lim.codigoBIP}</td>
                                                        <td>${lim.denominacion}</td>
                                                        <c:forEach var="mon" items="${lim.montosAnio}">
                                                            <td style="text-align: right;">${mon.monto}</td>
                                                        </c:forEach>
                                                    </tr>
                                                    <c:choose>
                                                        <c:when test="${rowClass eq 'row0'}">
                                                            <c:set var="rowClass" value="row1"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set var="rowClass" value="row0"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </c:forEach>
                        </br>
                        </br>
                        <c:forEach var="modi" items="${decreto.modificacionProyectos}">
                            <table class="adminlist" width="100%">
                                <tbody>
                                <tr class="row0">
                                    <td><strong>MODIFICACIÓN</strong></td>
                                </tr>
                                </tbody>
                            </table>
                            </br>
                            <table class="adminlist" width="100%">
                                <tbody>
                                <tr class="row0">
                                    <td style="width: 33%;"><strong>SUBTITULO: </strong>${modi.codigoSubtitulo}</td>
                                    <td style="width: 33%;"><strong>ITEM: </strong>${modi.codigoItem}</td>
                                    <td style="width: 33%;"><strong>MONEDA: </strong>${modi.moneda}</td>
                                </tr>
                                </tbody>
                            </table>
                            <table class="adminlist" width="100%">
                                <thead>
                                <tr>
                                    <th class="title-grid">BIP</th>
                                    <th class="title-grid">DENOMINACIÓN</th>
                                    <c:forEach var="asig" items="${modi.detalleModificacionProyectos[0].montosAsignacion}">
                                        <th class="title-grid">${asig.codigoAsignacion}</th>
                                    </c:forEach>
                                </tr>
                                <tr>
                                    <th class="title-grid"></th>
                                    <th class="title-grid"></th>
                                    <c:forEach var="asig" items="${modi.detalleModificacionProyectos[0].montosAsignacion}">
                                        <th class="title-grid">${asig.nombreAsignacion}</th>
                                    </c:forEach>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="detModi" items="${modi.detalleModificacionProyectos}" varStatus="status">
                                    <c:choose>
                                        <c:when test="${status.index % 2 == 0}">
                                            <c:set var="rowClass" value="row0"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="rowClass" value="row1"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${empty listaErrores.erroresDetalle}">
                                            <tr class="<c:out value="${rowClass}"/>">
                                                <td>${detModi.codigoBIP}</td>
                                                <td>${detModi.denominacion}</td>
                                                <c:forEach var="asig" items="${detModi.montosAsignacion}">
                                                    <td style="text-align: right;">${asig.monto}</td>
                                                </c:forEach>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="asig" items="${detModi.montosAsignacion}">
                                                <c:forEach var="error" items="${listaErrores.erroresDetalle.errores}">
                                                    <c:set var="cuenta" value="${modi.codigoSubtitulo}.${modi.codigoItem}.${asig.codigoAsignacion}.000" />
                                                    <c:if test="${error.cuenta eq cuenta and
                                                                error.codigoPartida eq decreto.codigoPartida and
                                                                error.codigoCapitulo eq decreto.codigoCapitulo and
                                                                error.codigoPrograma eq decreto.codigoPrograma}">
                                                        <c:set var="mensajesError" value="${mensajesError}${empty mensajesError ? '' : '&nbsp;'}&#8226; ${error.mensaje}" />
                                                        <c:set var="tipoError" value="${error.tipoError}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${!empty tipoError}">
                                                    <c:choose>
                                                        <c:when test="${tipoError eq 1}">
                                                            <c:set var="rowClass" value="rowErr1"></c:set>
                                                        </c:when>
                                                        <c:when test="${tipoError eq 2}">
                                                            <c:set var="rowClass" value="rowErr2"></c:set>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set var="rowClass" value="rowErr3"></c:set>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>
                                            </c:forEach>
                                            <c:choose>
                                                <c:when test="${empty mensajesError}">
                                                    <tr class="<c:out value="${rowClass}"/>">
                                                        <td>${detModi.codigoBIP}</td>
                                                        <td>${detModi.denominacion}</td>
                                                        <c:forEach var="asig" items="${detModi.montosAsignacion}">
                                                            <td style="text-align: right;">${asig.monto}</td>
                                                        </c:forEach>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr class="<c:out value="${rowClass}"/> detalleError" data-tip="<c:out value="${mensajesError}"/>">
                                                        <td>${detModi.codigoBIP}</td>
                                                        <td>${detModi.denominacion}</td>
                                                        <c:forEach var="asig" items="${detModi.montosAsignacion}">
                                                            <td style="text-align: right;">${asig.monto}</td>
                                                        </c:forEach>
                                                    </tr>
                                                    <c:set var="mensajesError" value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                </tbody>
                            </table>

                            </br>
                            </br>

                            <c:if test="${!empty modi.compromisosModificaciones.limiteMaximoCompromisos}">
                                <table class="adminlist" width="100%">
                                    <tbody>
                                    <tr class="row0">
                                        <td><strong>LIMITE MAXIMO IDENTIFICACIÓN</strong></td>
                                    </tr>
                                    </tbody>
                                </table>

                                </br>

                                <table class="adminlist" width="100%">
                                    <thead>
                                    <tr>
                                        <th class="title-grid">BIP</th>
                                        <th class="title-grid">DENOMINACIÓN</th>
                                        <th class="title-grid">AÑO</th>
                                    </tr>
                                    <tr>
                                        <th class="title-grid"></th>
                                        <th class="title-grid"></th>
                                        <c:forEach var="comFut" items="${modi.compromisosModificaciones.limiteMaximoCompromisos[0].montosAnio}">
                                            <th class="title-grid">${comFut.anio}</th>
                                        </c:forEach>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="rowClass" value="row0"/>
                                    <c:forEach var="detproy" items="${modi.detalleModificacionProyectos}">
                                        <c:forEach var="lim" items="${modi.compromisosModificaciones.limiteMaximoCompromisos}">
                                            <c:if test="${detproy.codigoBIP == lim.codigoBIP}">
                                                <tr class="<c:out value="${rowClass}"/>">
                                                    <td>${lim.codigoBIP}</td>
                                                    <td>${lim.denominacion}</td>
                                                    <c:forEach var="mon" items="${lim.montosAnio}">
                                                        <td style="text-align: right;">${mon.monto}</td>
                                                    </c:forEach>
                                                </tr>
                                                <c:choose>
                                                    <c:when test="${rowClass eq 'row0'}">
                                                        <c:set var="rowClass" value="row1"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="rowClass" value="row0"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </c:forEach>
        </div>

        <div style="text-align:center;width:100%">
            <div id="contBotones">
                <hr class="separador"/>
                <button id="pdf" class="boton">PDF</button>
                <button id="excel" class="boton">Excel</button>
                <button id="cerrar" class="boton" onClick="window.close()">Salir</button>
            </div>
            <div id="pie" class="Pauta_texto">
                <span>Teatinos 56 , Santiago de Chile, Tel&eacutefono 56-2 24021100 -&nbsp;C&oacutedigo Postal: 8340521</span></br>
                <span id="reqmin">Sitio web optimizado para ser visualizado en una resolución de pantalla de 1024 x 768 píxeles, en los navegadores iExplorer 10 o superior, Firefox 3.6 o superior y Chrome 8 o superior</span>
            </div>
        </div>

    </div>
    <input id="idArchivo" value="${idFileUp}" style="display: none;"/>
    <input id="idSistradoc" value="${idSistradoc}" style="display: none;"/>
    <div id="dialogoDescarga" title="Generando documento...">
        <div class="progress-label" style="margin-top: 5%"><strong>Este proceso puede durar varios segundos...</strong></div>
        <div id="progressbar"></div>
    </div>
</body>
</html>
