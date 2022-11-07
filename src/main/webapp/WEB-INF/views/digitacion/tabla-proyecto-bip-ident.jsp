<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-29
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/img" var="images"/>
<table class="adminlist">
    <thead>
    <tr>
        <td colspan="5" class="title-table"><span class="Estilo11"><b>Identifiquese los siguientes proyectos</b></span></td>
    </tr>
    <tr class="title-grid">
        <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Código BIP</b></span></div></td>
        <td class="title-grid" width="40%"><div align="center"><span class="Estilo11"><b>Denominación</b></span></div></td>
        <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Compromiso futuro</b></span></div></td>
        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Eliminar</b></span></div></td>
        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Editar</b></span></div></td>
    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty ident.detalleIdentificacionProyectos}">
        <c:forEach var="detproy" items="${ident.detalleIdentificacionProyectos}" varStatus="status">
            <c:choose>
                <c:when test="${status.index % 2 == 0}">
                    <c:set var="rowClass" value="row0"/>
                </c:when>
                <c:otherwise>
                    <c:set var="rowClass" value="row1"/>
                </c:otherwise>
            </c:choose>
            <tr class="<c:out value="${rowClass}"/>">
                <td style="text-align: center;">${detproy.codigoBIP}</td>
                <td>${detproy.denominacion}</td>
                <td style="text-align: center;"><img src="${images}/calendar.jpg" alt="" class="icoImage18" style="cursor: pointer;"/></td>
                <td style="text-align: center;"><img src="${images}/delete.png" alt="" class="icoImage18" style="cursor: pointer;"/></td>
                <td style="text-align: center;"><img src="${images}/edit.png" alt="" class="icoImage18" style="cursor: pointer;"/></td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
