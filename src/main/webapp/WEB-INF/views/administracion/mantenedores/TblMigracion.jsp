<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Sistema de Contabilidad General de la Nación</title>
    <%--!
    <link rel="stylesheet" href="${jtable}/themes/lightcolor/blue/jtable.css" type="text/css" />
    <link rel="stylesheet" href="${css}/validationEngine.jquery.css" type="text/css" />

    <script src="${jtable}/jquery.jtable.js" type="text/javascript"></script>
    <script src="${jtable}/localization/jquery.jtable.es.js" type="text/javascript"></script>
    <script src="${js}/SpryStyles/SpryCollapsiblePanel.js" type="text/javascript"></script>
    <script src="${js}/jquery.validationEngine.js" type="text/javascript"></script>
    <script src="${js}/jquery.validationEngine-es.js" type="text/javascript"></script>
    <script src="${js}/mantenedores/tblMigracion.js" type="text/javascript"></script>
    <script src="${js}/mensaje/jquery.alerts.js"></script>
    <link rel="stylesheet" href="${css}/mensaje/jquery.alerts.css" type="text/css">
--%>


    <link href="${css}/inputsCss.css" rel="stylesheet" type="text/css"></link>
    <link rel="stylesheet" href="${css}/tabla.css" type="text/css" ></link>
    <link rel="stylesheet" href="${css}/PrincipalUser.css" type="text/css" ></link>
    <link rel="stylesheet" href="${css}/EstilosPaginas.css" type="text/css">
    <link rel="stylesheet" href="${css}/Contenedores.css" type="text/css">
    <link rel="stylesheet" href="${css}/cssFiles.css" type="text/css">
    <link rel="stylesheet" href="${css}/cssReporteValidacion.css" type="text/css">
    <link rel="stylesheet" href="${css}/mensaje/jquery.alerts.css" type="text/css">
    <link rel="stylesheet" href="${css}/nyroModal.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${css}/jquery.cluetip.css" type="text/css" />
    <link rel="stylesheet" href="${css}/SpryStyles/SpryTabbedPanels.css" type="text/css" />
    <link rel="stylesheet" href="${css}/SpryStyles/SpryCollapsiblePanel.css" type="text/css" />
    <link rel="stylesheet" href="${css}/carrusel.css" type="text/css"></link>
    <link rel="stylesheet" href="${css}/menuPrincipal.css" type="text/css" ></link>
    <link rel="stylesheet" href="${css}/mensaje/jquery.alerts.css"  type="text/css" />
    <link rel="stylesheet" href="${css}/mantenedores.css" type="text/css" ></link>
    <link rel="stylesheet" href="${jtable}/jquery-ui.css" type="text/css"  />
    <link rel="stylesheet" href="${css}/jquery-ui.css" type="text/css" />

    <link href="${jtable}/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />


    <script src="${js}/jquery-1.7.2.js"></script>
    <script src="${js}/mensaje/jquery.alerts.js"></script>
    <script src="${js}/ui/jquery.ui.core.js" type="text/javascript"></script>
    <script src="${js}/ui/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="${js}/ui/jquery.ui.dialog.js" type="text/javascript"></script>
    <script src="jtable/jquery.jtable.js" type="text/javascript"></script>
    <script src="${js}/ui/jquery.ui.button.js" type="text/javascript"></script>
    <script src="${js}/ui/jquery.ui.menu.js" type="text/javascript"></script>
    <script src="${js}/MenuAdministracion.js" type="text/javascript"></script>
    <script src="${js}/mantenedores/tblMigracion.js" type="text/javascript"></script>
    <script src="jtable/localization/jquery.jtable.es.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${css}/validationEngine.jquery.css" type="text/css" />
    <script src="${js}/jquery.validationEngine.js"></script>
    <script src="${js}/jquery.validationEngine-es.js"></script>




    <script>
        $(document).ready(function (){
            $('.solo-numero').keyup(function (){
                this.value = (this.value + '').replace(/[^0-9]/g, '');

            });
        });
    </script>
</head>
<body>
<div class="filtro" style="margin: 10px auto;">

    <div  class="filTpInforme" style="float:left;margin: 15px 10px 0 0;">
       <div style="font: bold 12px sans-serif;float:left;margin-top: 10px;">Ejercicio Desde:</br>
            <select id="cbEjercicio" class="Selectano" onchange="" style="width:160px;">
                <option value="-1">Selec. Ejercicio</option>
                <c:forEach var="ejer" items="${listEjercicios}">
                    <option value="${ejer.ejercicioId}">${ejer.ejercicioNombre}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div  class="filTpInforme" style="float:left;margin: 15px 10px 0 0;">
        <div style="font: bold 12px sans-serif;float:left;margin-top: 10px;">Ejercicio Hasta:</br>
            <select id="cbEjercicio2" class="Selectano" onchange="" style="width:160px;">
                <option value="-1">Selec. Ejercicio</option>
                <c:forEach var="ejer" items="${listEjercicios}">
                    <option value="${ejer.ejercicioId}">${ejer.ejercicioNombre}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div  class="filTpInforme" style="float:left;margin: 15px 10px 0 0;">

        <div style="width:250px;font: 6px sans-serif;">
            <input id="btnMigrar" class="boton" type="button" value="Migrar" onclick="Migrar(cbEjercicio.value,cbEjercicio2.value)"></input>
        </div>
    </div>

</div>
</body>
</html>
