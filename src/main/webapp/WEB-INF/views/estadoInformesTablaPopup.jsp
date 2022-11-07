<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<spring:url value="/resources/img/" var="images"/>


<style>
	body{
		background: #ecf0f1;
	}

	#container-main{
		margin:0;
		width:95%;
		height: 95%;
		text-align: center;
	}

	#container-main h1{
		font-size: 40px;
		text-shadow:4px 4px 5px #16a085;
	}

	.accordion-container {
		width: 100%;
		margin: 0 0 0px;
		clear:both;
	}

	.accordion-titulo {
		font-size: 12px;
		font-weight: 100;
		color: #fff;
		text-decoration: none;
	}
	.accordion-titulo.open {
		background: #E7E3E3;
		color: #333333;
	}
	.accordion-titulo:hover {
		background: #B8B8B6;
	}

	.accordion-titulo span.toggle-icon:before {
		content:"+";
	}

	.accordion-titulo.open span.toggle-icon:before {
		content:"-";
	}

	.accordion-titulo span.toggle-icon {
		position: absolute;
		top: 0;
		right: 0;
		font-size: 28px;
		font-weight:bold;
	}

	.accordion-content {
		display: none;
		padding: 0;
		overflow: auto;
	}

	.accordion-content p{
		margin:0;
	}

	.accordion-content img {
		display: block;
		float: left;
		margin: 0 0 0 0;
		width: 100%;
		height: auto;
	}


	@media (max-width: 800px) {
		.accordion-content {
			padding: 0 0;
		}
	}

	.clickeable-row {
		cursor: pointer;
	}
</style>

<script>
	$(".research tr:not(.accordion)").hide();
	$(".research tr:first-child").show();
	$(".research tr.accordion").click(function(){
		$(this).nextAll("tr").fadeToggle(100);
	});

	$('.clickeable-row').click(function(){
		window.location = $(this).data('href');
		return false;
	});
</script>



</head>
<body>

<table id="tablaDatosEmitidos" class="adminlist">
	<thead>
	<tr class="title-grid">
		<td align="center" width="20%">Entidad Remisora</td>
		<td align="center" width="10%">Tipo Documento</td>
		<td align="center" width="10%">N° Doc</td>
		<td align="center" width="10%">Ingreso CGR</td>
		<td align="center" width="10%">Fecha Doc</td>
		<td align="center" width="10%">Estado Sistradoc</td>
		<td align="center" width="10%">Estado Sicogen</td>
		<td align="center" width="10%">Fecha Tramitacion</td>
		<td align="center" width="10%">Reproceso</td>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${listaDatosEmitidos}" var="item">
		<table class="research adminlist">
			<tbody>
			<tr class="accordion clickeable-row row0" data-href="javascript:void(0);">
				<td id="soyTD" align="left" width="20%">${item.entidadEmisora}</td>
				<td align="center" width="10%">${item.tipoDoc}</td>
				<td align="center" width="10%">${item.numeroDoc}</td>
				<td align="center" width="10%">${item.fechaDoc}</td>
				<td align="center" width="10%">${item.fechaRecep}</td>
				<td align="center" width="10%">${item.estadoSistradoc}</td>
				<td align="center" width="10%">
					<c:if test="${item.estadoSicogen=='3'}">
						CON ERRORES
					</c:if>
					<c:if test="${item.estadoSicogen=='4'}">
						VALIDADO
					</c:if>
					<c:if test="${item.estadoSicogen=='5'}">
						VALIDADO CON OBSERVACION
					</c:if>
					<c:if test="${item.estadoSicogen=='6'}">
						PROCESADO
					</c:if>
					<c:if test="${item.estadoSicogen=='7'}">
						PROCESADO CON OBSERVACION
					</c:if>
					<c:if test="${item.estadoSicogen=='8'}">
						SIN MOVIMIENTO
					</c:if>
					<c:if test="${item.estadoSicogen=='10'}">
						PROCESADO SIN MOV
					</c:if>
				</td>
				<td align="center" width="10%">
					<c:choose>
						<c:when test="${item.fechaTramitacion==null}">
							-
						</c:when>
						<c:otherwise>
							${item.fechaTramitacion}
						</c:otherwise>
					</c:choose>
				</td>
				<td align="center" width="10%">
					<c:choose>
						<c:when test="${item.estadoSistradoc != 'TOMADO DE RAZON' && item.estadoSistradoc != 'TOMADO DE RAZON CON ALCANCES'}">
							<button type="button" class="btn btn-primary" id="btnValidarTDR" onclick="reprocesoTDR(${item.fileID})">Validar</button>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<div class="accordion-content">
				<tr class="fold">
					<td id="liVerInforme" onclick="liVerInformeAccordion(${item.fileID},${item.documentoID})" colspan="9">
						<p style='margin-left: 7em'>
							<a class="linkVerRepValidacion" href="javascript:void(0);">
								<font color="#0066b8"> * Ver Informe</font>
							</a>
						</p>
					</td>
				</tr>

				<tr class="fold">
					<td id="liVerRepValidacionAccordion" onclick="liVerRepValidacionAccordion(${item.fileID},${item.documentoID},${infSegTDRMP})" colspan="9">
						<p style='margin-left: 7em'>
							<a class="linkVerRepValidacion" idfile="" idfileupload="" href="javascript:void(0);">
								<font color="#0066b8"> * Ver Reporte de Validaci&oacute;n</font>
							</a>
						</p>
					</td>
				</tr>

				<tr class="fold">
					<td id="liVerDescargaArchivoAccordion" onclick="liVerDescargaArchivoAccordion(${item.fileID})" colspan="9">
						<p style='margin-left: 7em'>
							<a id="linkVerArchivo" class="linkVerRepValidacion"  idfile="" idfileupload="" href="javascript:void(0);">
								<font color="#0066b8"> * Descargar Archivo</font>
							</a>
						</p>
					</td>
				</tr>

				<c:if test="${item.estadoSicogen != '7' && item.estadoSicogen != '6'}">
					<tr class="fold">
						<td id="liVerResumenErrores" onclick="verErroresTDR(${item.fileID})" colspan="9">
							<p style='margin-left: 7em'>
								<a class="linkVerRepValidacion" href="javascript:void(0);">
									<font color="#0066b8"> * Ver Resumen de Errores</font>
								</a>
							</p>
						</td>
					</tr>
				</c:if>

				<tr class="fold">
					<td id="liVerBitAccordion" onclick="liVerBitAccordion(${item.fileID})" colspan="9">
						<p style='margin-left: 7em'>
							<a class="linkVerRepValidacion" href="javascript:void(0);">
								<font color="#0066b8"> * Ver Bit&aacute;cora</font>
							</a>
						</p>
					</td>
				</tr>
			</div>
			</tbody>
		</table>

	</c:forEach>
	</tbody>
</table>

<div class="Acceso" id="dialogAccordion" title="Prop" style="width:900px; height:auto;max-height:400px;z-index:2001; display:none;" class="TextoNombre">
	<div id="divDlgContenido" class="TextoPopupPrincipal"></div>
	<div style="width:660px;max-height:400px; margin:auto 0;overflow-y:scroll ;">
		<div style="float:left;margin:5px 0 0 15px;"></div>
		<div style="clear: both;display: block; margin: 10px 0 0 15px;">
			<div style="clear: both;width:620px;float:left;">
				<label id="estadoSendCGRError" style="display:inline;"> </label> 
			</div>
		</div>
		<div style="float:left;margin:5px 0 0 15px;"></div>
		<div style="clear: both;display: block; margin: 40px 0 20px 15px;">
			<div style="clear: both;" class="grillaInformes">
				<div style="clear: both;" class="rwEncInf">
					<div style="width:99px;text-align:center" class="tituloInfColError">Tipo</div>
					<div style="width:99px;text-align:center"  class="tituloInfColErrorFinal">Error</div>
				</div>						
				<div class="contEstInfAnual" id="divResumenAccordion"></div>
			</div>
		</div>
	</div>
</div>
<div id="dialogReprocesoTDRMP" title="Reproceso TDRMP"></div>
<script>
	var dialogReprocesoTDRMP;

	$(document).ready(function () {
		dialogReprocesoTDRMP =$( "#dialogReprocesoTDRMP" ).dialog({
			autoOpen: false,
			modal: true,
			buttons: {
				Cerrar: reloadDatosTDRMP
			},
			close: reloadDatosTDRMP
		});
	})
</script>
</body>
</html>
