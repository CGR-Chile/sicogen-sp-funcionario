<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-11-24
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<spring:url value="/resources/css" var="css"/>
<link rel="stylesheet" href="${css}/system.css" type="text/css" />
<style>
    .font-12 {
        font-size: 12px !important;
    }
</style>
<table class="adminlist font-12" style="width: 100%;margin-top: 5px;">
    <thead>
    <tr class="title-grid">
        <th width="30%">ESTADO</th>
        <th width="30%">FECHA</th>
        <th width="40%">USUARIO</th>
    </tr>
    </thead>
    <tbody>
        <tr class="row0">
            <td>Creado</td>
            <td>${bitacora.fchaCreacion}</td>
            <td>${bitacora.userCreacion}</td>
        </tr>
        <c:choose>
            <c:when test="${bitacora.fchaEdicion != null}">
                <tr class="row0">
                    <td>Editado</td>
                    <td>${bitacora.fchaEdicion}</td>
                    <td>${bitacora.userEdicion}</td>
                </tr>
            </c:when>
            <c:when test="${bitacora.fchaDesactivacion != null}">
                <tr class="row0">
                    <td>Desactivado</td>
                    <td>${bitacora.fchaDesactivacion}</td>
                    <td>${bitacora.userEdicion}</td>
                </tr>
            </c:when>
        </c:choose>
    </tbody>
</table>
