<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-11-24
  Time: 17:02
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
        <th width="40%">Cuenta</th>
        <th width="60%">Descripción</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cta" items="${listaCtasDependientes}" varStatus="status">
        <tr class="row0">
            <td style="text-align: center;">${cta.codCuenta}</td>
            <td>${cta.descripcion}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
