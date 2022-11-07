<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-02
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/img" var="images"/>
<div class="py-3">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <table class="adminlist">
                    <thead>
                    <tr>
                        <td colspan="8" class="title-table"><span class="Estilo11"><b>Tomas de razón - Documentos SISTRADOC</b></span></td>
                    </tr>
                    <tr class="title-grid">
                        <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Entidad Remisora</b></span></div></td>
                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Tipo Documento</b></span></div></td>
                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Nº Documento</b></span></div></td>
                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Fecha Documento</b></span></div></td>
                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Fecha Recepción CGR</b></span></div></td>
                        <td class="title-grid" width="15%"><div align="center"><span class="Estilo11"><b>Analista Sistradoc</b></span></div></td>
                        <td class="title-grid" width="15%"><div align="center"><span class="Estilo11"><b>Estado SICOGEN</b></span></div></td>
                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Digitación</b></span></div></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${!empty informes}">
                            <c:forEach var="informe" items="${informes}" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.index % 2 == 0}">
                                        <c:set var="rowClass" value="row0"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="rowClass" value="row1"/>
                                    </c:otherwise>
                                </c:choose>
                                <tr class="<c:out value="${rowClass}"/>">
                                    <td>${informe.idDocumento} - ${informe.entidadEmisora}</td>
                                    <td style="text-align:center;">${informe.tipoDocumento}</td>
                                    <td style="text-align:center;">${informe.nroDocumento}</td>
                                    <td style="text-align:center;">${informe.fechaDocumento}</td>
                                    <td style="text-align:center;">${informe.fechaRecepcionCGR}</td>
                                    <td style="text-align:center;">${informe.analista}</td>
                                    <c:choose>
                                        <c:when test="${informe.estadoSicogen == 'SIN CARGAR'}">
                                            <td style="text-align:center;">SIN DIGITAR</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td style="text-align:center;">${informe.estadoSicogen}</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td style="text-align:center;"><img src="${images}/1_digitar.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="cargaDigitacionTDR(${informe.idDocumento}, ${informe.nroDocumento}, '${informe.tipoDocumento}', '${informe.estadoSicogen}', '${informe.entidadEmisora}', '${informe.fechaDocumento}', '${informe.fechaRecepcionCGR}')"/></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr class="row0">
                                <td colspan="8" style="text-align:center;">No hay registros para los filtros seleccionados.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>