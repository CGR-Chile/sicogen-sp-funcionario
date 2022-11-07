<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Sistema de Contabilidad General de la Nación</title>

    <link rel="stylesheet" href="${css}/SpryStyles/SpryTabbedPanels.css" type="text/css" />
    <link rel="stylesheet" href="${css}/SpryStyles/SpryCollapsiblePanel.css" type="text/css" />
    <link rel="stylesheet" href="${css}/inputsCss.css" type="text/css" />
    <link rel="stylesheet" href="${css}/carrusel.css" type="text/css" />
    <link rel="stylesheet" href="${css}/menuPrincipal.css" type="text/css" />
    <link rel="stylesheet" href="${css}/jquery-ui/1.12.1/jquery-ui.css" type="text/css" />
    <link type="text/css" href="${css}/css-loader.css" rel="stylesheet"/>

    <script type="text/javascript" src="${js}/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${js}/jquery-ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="${js}/MenuAdministracion.js"></script>
</head>
<body>
<div id="TabbedPanels1" class="TabbedPanels" style="clear: both; margin:0;">
    <div id="TabbedPanelsContentGroup1" class="TabbedPanelsContentGroup">
        <div id="TabbedPanelsContent05" class="TabbedPanelsContent TabbedPanelsContentVisible menuPrincipal">
            <div style="margin-bottom: 5px;">Mantenedor
                <select id="cbTablas" class="Selectano" onchange="ChangeMantenedor(this.value)" style="width: 300px;">
                    <option value="-1">Selec. Mantenedor</option>
                    <c:forEach var="mant" items="${listaMant}">
                        <option value="${mant.mantAccion}">${mant.mantNombre}</option>
                    </c:forEach>
                </select>
            </div>

            <div id="contAdministracion" style="width:1024px;height:500px;overflow-x: hidden;overflow-y: auto;">

            </div>
        </div>
    </div>
</div>
<div id="loading-spinner" style="z-index: 9999; position: absolute;" class="loader loader-default"
     data-text="Cargando Informaci&oacute;n..."></div>
</body>
</html>