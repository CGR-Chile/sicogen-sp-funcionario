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

    <link rel="stylesheet" href="${css}/formCarga.css" type="text/css" />
    <link rel="stylesheet" href="${css}/EstilosPaginas.css" type="text/css" />
    <link rel="stylesheet" href="${css}/cssFiles.css" type="text/css" />
    <link rel="stylesheet" href="${css}/cssReporteValidacion.css" type="text/css" />
    <link rel="stylesheet" href="${css}/EstilosPaginas.css" type="text/css" ></link>
    <link rel="stylesheet" href="${css}/PrincipalUser.css" type="text/css" ></link>
    <link rel="stylesheet" href="${css}/nyroModal.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${css}/jquery.cluetip.css" type="text/css" />
    <link rel="stylesheet" href="${css}/SpryStyles/SpryTabbedPanels.css" type="text/css" />
    <link rel="stylesheet" href="${css}/SpryStyles/SpryCollapsiblePanel.css" type="text/css" />
    <link href="${css}/carrusel.css" rel="stylesheet" type="text/css"></link>
    <link rel="stylesheet" href="${css}/menuPrincipal.css" type="text/css" ></link>
    <link href="${css}/mensaje/jquery.alerts.css"  rel="stylesheet" type="text/css" />
    <link href="jtable/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${css}/jquery-ui.css" type="text/css" />
    <link href="${jtable}/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />

    <script src="${js}/filtrosPeriodos.js" type="text/javascript"></script>
    <script src="${js}/mensaje/jquery.alerts.js"></script>
    <script src="${js}/reporteCuadratura.js"></script>
    <script src="${js}/jquery/jquery-1.9.1.js" type="text/javascript"></script>

    <script src="${js}/ui/jquery.ui.core.js" type="text/javascript"></script>
    <script src="${js}/ui/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="${js}/ui/jquery.ui.dialog.js" type="text/javascript"></script>
    <script src="${jtable}/jquery.jtable.js" type="text/javascript"></script>
    <script src="${js}/ui/jquery.ui.button.js" type="text/javascript"></script>
    <script src="${js}/ui/jquery.ui.menu.js" type="text/javascript"></script>
    <script src="${js}/jquery/dwn/ui-1.10.3-jquery-ui.js"></script>
    <script src="${js}/MenuAdministracion.js" type="text/javascript"></script>
    <script src="${js}/mantenedores/tblSaldoInicial.js" type="text/javascript"></script>
    <script src="${jtable}/localization/jquery.jtable.es.js" type="text/javascript"></script>
    <script type="text/javascript" src="${js}/jquery.form.js"></script>

    <link rel="stylesheet" href="${css}/validationEngine.jquery.css" type="text/css" />
    <script src="${js}/jquery.validationEngine.js"></script>
    <script src="${js}/jquery.validationEngine-es.js"></script>

    <style>
        .alignRight{text-align: right;}
    </style>
</head>
<body onload="load();">
<div class="filtering" style="margin: 10px auto;">
    <table class="tblUpInformes" style="clear:both;border-collapse:collapse;width:100%;">
        <thead style="background-color:#3F87C1;color:#FFFFFF;font: bold 10px arial;height:20px;">
        <tr style="height: 10px;text-align:left;">
            <th style="background: #0066B8" colspan="11"></th>
        </tr>
        <tr style="height: 20px;text-align:left;">
            <th colspan="1"><div style="width:10px"></div></th>
            <th colspan="10" ><div>CARGA SALDOS INICIAL</div></th>
        </tr>
        </thead>
        <tbody>
        <tr style="height: 10px;text-align:left;">
            <td colspan="1"><div class="cel04" style="width:10px"></div></td>
            <td colspan="3"><div class="cel04">Saldos Iniciales (Excel)</div></td>
            <td colspan="1"><div class="cel04" style="width:50px"><input type="text" id="" class="txtFile" value="<s:property value='informePI.nombreArchivo'/>" disabled="disabled" /></div></td>
            <td colspan="1"><div class="cel03" id="inf0_cel03">
                <form id="formCargaSaldos" enctype="multipart/form-data" class="formFile" action="" method="post">
                    <input type="file" class="files" name="fileUpload" onchange="realizaCargaSaldosIniciales()" id="fileUpload">
                    <button id="valCarga" type="button" class="botonFile transparent">Examinar...</button>
                </form></div>

            </td>
            <td colspan="5">
                <div style="margin-left:10px;" id="estadoCargaSaldoInicial"/>

            </td>
        </tr>
        </tbody>
    </table>
</div>


<div id="errorCargaSaldos" style="display: none;">
    <div id="errorCargaSaldosMensaje"/>
</div>

<!-- div class="filtering" style="margin: 10px auto;">
				Ejercicio: <s:select id="cbEjercicio"
							 headerKey="-1"
							 cssClass="Selectano"
							 list="listaEjercicios"
							 listValue="ejercicioNombre"
							 listKey="ejercicioId"
							 onchange="loadPartida(this.value)"
							 style="width:60px;"
							 theme="simple" />

				Partida:
			   			  <select id="cbPartida" class="Selectano" style="width:250px;" theme="simple" onchange="loadCapitulo(this.value)">
			   			   <option value="-1" >Seleccione</option>
						   <s:iterator value="listaPartida" status="stat">
						      <option data-cod="<s:property value="codPartida"/>" value="<s:property value="codPartida"/>">
						      		<s:property value="nombrePartida"/>
						      </option>

						   </s:iterator>
						</select>

			 	Capitulo: <s:select id="cbCapitulo"
						  headerKey="-1"
						  headerValue="Selec"
						  cssClass="Selectano"
			   			  list="listaCapitulo"
			   			  listValue="nombreCapitulo"
			   			  listKey="idCapitulo"
			   			  onchange="load(cbEjercicio.value,cbPartida.value,this.value)"
			   			  style="width:150px;"
			   			  theme="simple" />

		</div> -->

<div id="ExpenseTableContainer"></div>

<script>
    $(".formatoCLP").each(function(){
        $(this).html(
            $(this).html().toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,").
            replace(/,/g , ".")
        );
    });
</script>

</html>
