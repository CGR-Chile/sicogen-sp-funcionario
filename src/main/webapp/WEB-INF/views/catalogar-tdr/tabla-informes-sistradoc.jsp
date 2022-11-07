<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-22
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .font-12 {
        font-size: 12px !important;
    }
</style>
<table class="adminlist" id="tblInformesSistra">
    <thead>
    <tr>
        <td colspan="7" class="title-table"><span class="Estilo11"><b>Digitación de Presupuesto</b></span></td>
    </tr>
    <tr class="title-grid">
        <td class="title-grid" width="40%"><div align="center"><span class="font-12"><b>Entidad Remisora</b></span></div></td>
        <td class="title-grid" width="10%"><div align="center"><span class="font-12"><b>Tipo Documento</b></span></div></td>
        <td class="title-grid" width="10%"><div align="center"><span class="font-12"><b>Nº Documento</b></span></div></td>
        <td class="title-grid" width="10%"><div align="center"><span class="font-12"><b>Fecha Documento</b></span></div></td>
        <td class="title-grid" width="10%"><div align="center"><span class="font-12"><b>Fecha Recepción CGR</b></span></div></td>
        <td class="title-grid" width="5%"><div align="center"><span class="font-12"><b>Catalogado</b></span></div></td>
        <td class="title-grid" width="5%"><div align="center"><span class="font-12"><b>Asignar</b></span></div></td>
    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty informesSistradoc}">
        <c:forEach var="inf" items="${informesSistradoc}" varStatus="status">
            <c:choose>
                <c:when test="${status.index % 2 == 0}">
                    <c:set var="rowClass" value="row0"/>
                </c:when>
                <c:otherwise>
                    <c:set var="rowClass" value="row1"/>
                </c:otherwise>
            </c:choose>
            <tr class="<c:out value="${rowClass}"/>">
                <td class="font-12">${inf.entidadEmisora}</td>
                <td class="font-12" style="text-align: center;">${inf.tipoDocumento}</td>
                <td class="font-12" style="text-align: center;">${inf.nroDocumento}</td>
                <td class="font-12" style="text-align: center;">${inf.fechaDocumento}</td>
                <td class="font-12" style="text-align: center;">${inf.fechaRecepcionCGR}</td>
                <c:choose>
                    <c:when test="${inf.estadoSistradoc == 2}">
                        <td class="font-12" style="text-align: center;">SI</td>
                    </c:when>
                    <c:otherwise>
                        <td class="font-12" style="text-align: center;">NO</td>
                    </c:otherwise>
                </c:choose>
                <td class="font-12" style="text-align: center;"><input type="checkbox" id="checkId_${inf.idDocumento}"></td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>