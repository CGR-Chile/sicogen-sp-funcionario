    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<title>Sistema de Contabilidad General de la Naci�n</title>

		<spring:url value="/resources/css/system.css" var="system"/>
		<spring:url value="/resources/css/template.css" var="template"/>
		<spring:url value="/resources/css/rounded.css" var="rounded"/>
		<spring:url value="/resources/css/login.css" var="login"/>
		<spring:url value="/resources/css/jquery-confirm.min.css" var="confirm"/>
		<spring:url value="/resources/img/" var="images"/>
		<spring:url value="/resources/js" var="scripts"/>

		<link type="text/css" href="${system}" rel="stylesheet"/>
		<link type="text/css" href="${template}" rel="stylesheet"/>
		<link type="text/css" href="${rounded}" rel="stylesheet"/>
		<link type="text/css" href="${login}" rel="stylesheet"/>
		<link type="text/css" href="${confirm}" rel="stylesheet"/>


	    <script type="text/javascript" src="/sicogen-mf/resources/js/filtrosPeriodos.js"></script>
	    <script type="text/javascript" src="/sicogen-mf/resources/js/general.js"></script>
	    <script type="text/javascript" src="/sicogen-mf/resources/js/reporteCuadratura.js"></script>
	    <script type="text/javascript" src="${scripts}/reportes/reporteDeCuadratura.js"></script>
		<script type="text/javascript" src="/sicogen-mf/resources/js/jquery-confirm.min.js"></script>

	<style>
		.botones{
			margin: 40px 0 0 35%;
		}

	</style>
	</head>
	<body>
	<input type="hidden" id="periodoID" value="${periodoID}" name="${periodoID}"/>
	<input type="hidden" id="fileUpload" value="${fileUpload}" name="${fileUpload}"/>
	<input type="hidden" id="informeID" value="${informeID}" name="${informeID}"/>
	<input type="hidden" id="ejercicioID" value="${ejercicioID}" name="${ejercicioID}"/>

		<div style="width:100%;margin:0px auto -5px auto;height: 400px;">		
			<div style="padding:10px;height:35px;">
			<div id="contFiltroInforme" style="float:left;margin: 0 5px 0 0; width: 140px">
					<div style="font: bold 12px sans-serif;">Informe</div>
					<div>
						<select id="cboCuaInforme" disabled="true" class="Selectanoenabled" onchange="CargarCboPeriodoSegunInformeEjercicio(this.value,cboCuaEjercicios.value);CargarCboReporteSegunInforme(this.value);">
							<option value="-1">Seleccione Informe</option>
							<c:forEach var="informe" items="${listaInformes}">
								<c:choose>
									<c:when test="${informeID == informe.id}">
										<option value="${informe.id}" selected>${informe.nombre}</option>
									</c:when>
									<c:otherwise>
										<option value="${informe.id}">${informe.nombre}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>&nbsp;&nbsp;
			 	<div id="contFiltroEjercicios1" style="float:left; margin: 0 30px 30px 38px; width: 100px">
					<div style="font: bold 12px sans-serif;">Ejercicio</div>
					<div>
						<select id="cboCuaEjercicios" disabled="true" class="Selectanoenabled" onchange="CargarCboPeriodoSegunInformeEjercicio(cboCuaInforme.value,this.value);">
							<option value="-1">Seleccione Ejercicio</option>
							<c:forEach var="ejercicio" items="${ejercicios}">
								<c:choose>
									<c:when test="${ejercicioID == ejercicio.ejercicioId}">
										<option value="${ejercicio.ejercicioId}" selected>${ejercicio.ejercicioNombre}</option>
									</c:when>
									<c:otherwise>
										<option value="${ejercicio.ejercicioId}">${ejercicio.ejercicioNombre}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
				   </div>
				</div>&nbsp;&nbsp;&nbsp;
				<div id="contFiltroPeriodo" style="float:left;margin: 0 10px 0 49px; width: 140px">
					<div style="font: bold 12px sans-serif;">Periodo</div>
					<div>
						<select id="comboPeriodos" disabled="true" class="Selectanoenabled">
							<option value="-1">Seleccione Periodo</option>
							<c:forEach var="periodo" items="${listaPeriodos}">
								<c:choose>
									<c:when test="${periodoID == periodo.periodoCodigo}">
										<option value="${periodo.periodoId}" selected>${periodo.periodoNombre}</option>
									</c:when>
									<c:otherwise>
										<option value="${periodo.periodoId}">${periodo.periodoNombre}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>&nbsp;&nbsp;&nbsp;
				<div id="contFiltroReporte" style="float:left;margin: 0 15px 0 40px;width: 160px">
					<div style="font: bold 12px sans-serif;">Reportes</div>
					<div>
						<form:select id="cboCuaReportes" path="listaReportes">
							<form:option value="-1" label="Seleccione Reporte"/>
							<form:options items="${listaReportes}" itemValue="idReporte" itemLabel="nombreReporte"/>
						</form:select>
					</div>
				</div>&nbsp;&nbsp;
				<div id="contFiltroBuscar" class="filEjercicio" style="float:right;margin: 0 0 0 0;">
					<div style="font: bold 12px sans-serif;">  </div>
					<div>
						<input type="button" class="btn btn-primary"  name="Buscar" value="Buscar" onclick="CargarReporteCuaDisponibilidad();"/>

					</div>
				</div>
			</div>
			<br>
			<div style="width:980px;height:400px;background: none repeat scroll 0 0 #FFFFFF;border: 2px solid;border-bottom-left-radius: 25px;color: #333333;cursor: auto;font: bold 12px arial;">
				<div id="repCuadratura" style="width:920;position:absolute; height:375px; overflow-y: scroll; overflow-x:hidden; " >
					
				</div>
			</div>
		</div>
	<br>
		<div id="botones" class="botones" style="clear:both;float:center; margin:40px 0px 0 5px;padding:10px;" align="center">
	   			<button id="btnPdfCuaDisponibilidades"  onclick="descargaReporte();" type="button" class="btn btn-primary" style="cursor:pointer;" title="Ver Reporte Cuadratura Disponibilidades" value="validar_Informe">
				<!-- <img src="images/enviar.png" class="Image25" style="float:left; width: 25px;"></img> -->Pdf
				</button>

				<button  id="btnExcelCuaDisponibilidades" onclick="exportarExcel();" type="button" class="btn btn-primary" name="Btn_enviar" style="margin: 0 0 0 5%;cursor:pointer;"
					title="Envie los informes a contraloria" value="validar_Informe" >Excel</button>
	   	</div>
	</body>
</html>

<script>
	init();
</script>