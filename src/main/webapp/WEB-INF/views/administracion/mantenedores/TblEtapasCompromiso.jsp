<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<html>
<head>
    <link href="${jtable}/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${css}/validationEngine.jquery.css" type="text/css" />

    <script src="${jtable}/jquery.jtable.js" type="text/javascript"></script>
    <script src="${jtable}/localization/jquery.jtable.es.js" type="text/javascript"></script>
    <script src="${js}/jquery.validationEngine.js" type="text/javascript"></script>
    <script src="${js}/jquery.validationEngine-es.js" type="text/javascript"></script>
    <script src="${js}/mantenedores/tblEtapasCompromiso.js" type="text/javascript"></script>

</head>
<body >

<div id="ExpenseTableContainer" style="position: relative;"></div>

</body>
</html>