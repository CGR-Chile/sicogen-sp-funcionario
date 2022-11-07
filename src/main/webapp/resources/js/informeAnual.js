var sessTpEntidad=0;
var sessEntidad=0;
var revColor='#464749';

var dialogoAux;

$(document).ready(function() {


	console.log('$(document).ready - #idFileUpload : ' +$('#idFileUpload').text());

	$.fx.speeds._default = 650;
	$(function() {
		var opt = {
			autoOpen: false,
			modal: true,
			width: 505,
			show: "blind"
		};

		dialogoAux = $("#dialog").dialog(opt);});


	$('#liVerInforme').click( function()   {
		var idFileUp =  idFileUpload;

		console.log("evento en link liVerInforme.");
		var verInf='';
		var ent = "";
		if ($("#cbComuna").length > 0)
		{
			ent=$("#cbComuna option:selected").val();
		}else{
			ent=0;
		}
		var glosaEjercicio = $('#cbEjercicio :selected').text();
		var ejercicioId=$('#cbEjercicio :selected').val();

		var parametros='idFileUp='+idFileUpload+'&periodo='+periodo2+'&tpInforme=0'+'&glosaEjercicio='+glosaEjercicio+'&informe='+informe+'&entidad='+ent+'&ejrId='+ejercicioId;
		verInfPI='verInformePI.action?'+parametros;
		verInfIC='verInformeIC.action?'+parametros;
		verInfTDR='verInformeTDR.action?'+parametros;
		var windowSizeArray = [ "width=200,height=200","width=300,height=400,scrollbars=yes" ];
		var windowName = "popUp";
		var windowSize = windowSizeArray[  $(this).attr("rel")  ];
		if (informe == 1){
			//alert("PI");
			window.open(verInfPI, windowName, windowSize);
		}else if (informe == 3){
			//alert("IC");
			window.open(verInfIC, windowName, windowSize);
		}else if (informe == 2  || informe == 11 || informe == 12){
			window.open(verInfTDR, windowName, windowSize);
		}

	});

	$("#link-certificado-envio").click(function (){

		var parametros= 'certificado='+certificado;
		if (certificado==0) {

			$.confirm({
				title: 'Certificados',
				content: 'No se ha enviado el Informe a Contraloria',
				type: 'blue',
				typeAnimated: true,
				buttons: {
					tryAgain:{
						text:'Aceptar',
						btnClass: 'btn-blue',
						action: function () {
						}
					},

				}
			});


		} else {

			var url='../reportes/certificadoEnvio?certificado='+certificado;
			//location.href = url;
			window.open(url);
		}
	});


	$("#link-ver-bitacora").click( function ()	{
		var parametros=idFileUpload;
		var informeID;
		informeID=$("#idInforme_form").text();

		console.log("idInforme_form:"+$("#idInforme_form").text());
		console.log("informeID:"+informeID);

		if (informeID==1){
			popUpBitacoraCertificado(parametros);
		}else{
			window.parent.popUpBitacora(parametros);
		}
		return false;
	});



	$("#link-ver-resumenErrores").click( function() {
		var informe,idFileUpload;

		informe		= $("#idInforme_form").text();
		idFileUpload= $("#idFileUpload_rv").text();


		if(informe==1)
		{
			loadResumenErroresPI(idFileUpload);
		}else if (informe== 3)
		{
			loadResumenErroresIC(idFileUpload);
		}else if (informe == 12 )
		{
			loadResumenErroresTDR(idFileUpload);

		}
		return false;
	});




	$('#linkVerRepValidacion').click(function (){

		concole.log("entro en jquery click-linkVerRepValidacion");
		var windowSizeArray = [ "width=200,height=200","width=300,height=400,scrollbars=yes" ];
		var windowName = "popUp";
		var windowSize = windowSizeArray[  $(this).attr("rel")  ];
		window.open(repVal, windowName, windowSize);

	});


	$('#link-ver-reporteCuadraturas').click(function(){


		window.parent.CargarReporteDeCuadratura("#diaResumenCuadratura", idFileUpload, informe, periodo, ejercicio );
	});

});


function abrePopUpEvent(event)
{
	$("#contBitacora").hide();

	informe = event.data.informe;
	periodo = event.data.periodo;
	ejercicio = event.data.ejercicio;
	usuario = event.data.usuario;
	fecha = event.data.fecha;
	periodo2 = event.data.periodo2;
	var nombreInf = "";

	certificado = event.data.certificado;

	estado = event.data.estado;
	idFileUpload =  event.data.idFileUpload;

	var fec=fecha.substr(0, 10);
	var hor=fecha.substr(11, 8);

	if (informe == 1)
	{
		nombreInf = "Ley de presupuesto";
        $("#usuarioInforme").text(usuario);
        $("#fechaModal").text( fec.substr(8, 2)+"-"+fec.substr(5, 2)+"-"+fec.substr(0, 4) + ' a las ' + hor.substr(0, 5));
		$("#links-informe .link-reporte-val").attr("href","../validacion/reportes/informes/pi?idFileUp=" + idFileUpload);
		$('#estadoInforme').text( getState(''+estado) );
        $("#links-informe .link-descarga-archivo").attr("href","../documentos/descargaDoc?idArchivo=" +idFileUpload);
		$("#links-informe .link-informe-contable").attr("href","../seguimiento/verInformePI?idFileUp=" + idFileUpload);
		mostrarDiv("contableInforme");
		mostrarDiv("validacionReporte");
		mostrarDiv("archivoDescarga");
		//$("#links-informe .link-descarga-archivo").show();
		if(estado == 3 )
		{
			mostrarDiv("erroresResumen");
			//$("links-informe .link-ver-resumenErrores").show();
		}else{
			ocultarDiv("erroresResumen");
			//$("links-informe .link-ver-resumenErrores").hide();
		}
		ocultarDiv("pdfArchivo");
		//$("#links-informe .link-descarga-archivoPDF").hide();
		ocultarDiv("cuadraturaReportes");
		//$("#links-informe .liVerReporteCuadraturas").hide();
		ocultarDiv("envioCertificado");
		//$("#links-informe .link-certificado-envio").hide();
		//$("#linkVerCertEnvio").hide();
		mostrarDiv("bitacoraVer");
		//$("#links-informe .link-ver-bitacora").show();
	}

	if (informe == 3)
	{
		nombreInf = "Informe Contable";
		$("#usuarioInforme").text(usuario);
		$("#fechaModal").text( fec.substr(8, 2)+"-"+fec.substr(5, 2)+"-"+fec.substr(0, 4) + ' a las ' + hor.substr(0, 5));
		$('#estadoInforme').text( getState(''+estado) );
		$("#certificado").text("Numero de Envio: "+ certificado)
		$("#links-informe .link-informe-contable").attr("href","../seguimiento/verInformeIC?idFileUp=" + idFileUpload);
		$("#links-informe .link-reporte-val").attr("href","../validacion/obtenerValidacionIC?idFileUp=" + idFileUpload);
		$("#links-informe .link-descarga-archivo").attr("href","../documentos/descargaDoc?idArchivo=" +idFileUpload);

		mostrarDiv("contableInforme");
		mostrarDiv("validacionReporte");
		mostrarDiv("archivoDescarga");

		if(estado == 3 )
		{
			mostrarDiv("erroresResumen");
			//mostrarDiv("archivoDescarga");
			//$("#links-informe .link-ver-resumenErrores").show();
		}else{
			ocultarDiv("erroresResumen");
			//$("#links-informe .link-ver-resumenErrores").hide();
		}
		ocultarDiv("pdfArchivo");
		//$("#links-informe .link-descarga-archivoPDF").hide();
		mostrarDiv("cuadraturaReportes");
		//$("#links-informe .liVerReporteCuadraturas").show();


		if(estado == 6 || estado == 7 || estado == 10)
		{
			$("#links-informe .link-certificado-envio").attr("href","../reportes/certificadoEnvio?certificado=" + certificado);
			mostrarDiv("envioCertificado");

			//$("#links-informe .link-certificado-envio").show();

		}else{

			ocultarDiv("envioCertificado");
			//$("#links-informe .link-certificado-envio").hide();
		}
		mostrarDiv("bitacoraVer");
		//$("#links-informe .link-ver-bitacora").show();


		if (informe == 5 ||informe == 6 || informe == 7 || informe == 8 || informe == 9 || informe == 10)
		{
            nombreInf = "TDR";
            $("#usuarioInforme").text(usuario);
            $("#fechaModal").text( fec.substr(8, 2)+"-"+fec.substr(5, 2)+"-"+fec.substr(0, 4) + ' a las ' + hor.substr(0, 5));
			//alert("PDF");
			ocultarDiv("contableInforme");
			//$("#links-informe .link-informe-contable").hide();
			ocultarDiv("validacionReporte");
			//$("#links-informe .link-reporte-val").hide();
			//$("#links-informe .link-descarga-archivo").hide();
			mostrarDiv("pdfArchivo");
			//$("#links-informe .link-descarga-archivoPDF").show();
			ocultarDiv("erroresResumen");
			//$("#links-informe .link-ver-resumenErrores").hide();
			ocultarDiv("cuadraturaReportes");
			//$("#links-informe .link-ver-reporteCuadraturas").hide();
			ocultarDiv("envioCertificado");
			//$("#links-informe .link-certificado-envio").hide();
			mostrarDiv("bitacoraVer");
			//$("#links-informe .link-ver-bitacora").show();
		}
	}
	if (informe == 5 ||informe == 6 || informe == 7 || informe == 8 || informe == 9 || informe == 10)
	{
        nombreInf = "TDR";
        $("#usuarioInforme").text(usuario);
        $("#fechaModal").text( fec.substr(8, 2)+"-"+fec.substr(5, 2)+"-"+fec.substr(0, 4) + ' a las ' + hor.substr(0, 5));
        $("#links-informe .link-descarga-archivo").attr("href","../documentos/descargaDoc?idArchivo=" +idFileUpload);
		//alert("PDF");
		ocultarDiv("contableInforme");
		//$("#links-informe .link-informe-contable").hide();
		ocultarDiv("validacionReporte");
		//$("#links-informe .link-reporte-val").hide();
		ocultarDiv("archivoDescarga");
		//$("#links-informe .link-descarga-archivo").hide();
		mostrarDiv("pdfArchivo");
		//$("#links-informe .link-descarga-archivoPDF").show();
		ocultarDiv("erroresResumen");
		//$("#links-informe .link-ver-resumenErrores").hide();
		ocultarDiv("cuadraturaReportes");
		//$("#links-informe .link-ver-reporteCuadraturas").hide();
		ocultarDiv("envioCertificado");
		//$("#links-informe .link-certificado-envio").hide();
		mostrarDiv("bitacoraVer");
		//$("#links-informe .link-ver-bitacora").show();
	}

	try {
		$(".ui-dialog-content").dialog("close");
	}
	catch(err) {}

	var popUp = $("#links-informe").dialog({
		autoOpen: false,
		title: nombreInf.toUpperCase(),
		buttons: [
/*			{
				text: "Cerrar",
				click: function () {
					$(this).dialog("close");
				}
			}*/
		],
		open: function(event, ui) {
			$(".ui-dialog-titlebar-close", ui.dialog || ui).hide();
		}
	});

	popUp.dialog("open");

	/*$("span.ui-dialog-title").text($('#inf'+informe).text().toUpperCase());
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');*/


}

function mostrarDiv(div){

	document.getElementById(div).style.display = "block";
	document.getElementById(div).style.visibility = "visible";

}

function ocultarDiv(div){

	document.getElementById(div).style.display = "none";
	document.getElementById(div).style.visibility = "hidden";
}
function CrearNuevoDiv(){
	console.log('Creando Nuevo div para tabla Seguimiento !!')
	jQuery('#datosAccordion').html('<div id="datosAccordion">\n' +
		'                            <table id="tablaDatosEmitidos" class="adminlist">\n' +
		'                                <thead>\n' +
		'                                    <tr class="title-grid">\n' +
		'                                        <td align="center" width="20%">Entidad Remisora</td>\n' +
		'                                        <td align="center" width="10%">Tipo Documento</td>\n' +
		'                                        <td align="center" width="10%">N° Doc</td>\n' +
		'                                        <td align="center" width="10%">Ingreso CGR</td>\n' +
		'                                        <td align="center" width="10%">Fecha Doc</td>\n' +
		'                                        <td align="center" width="10%">Estado Sistradoc</td>\n' +
		'                                        <td align="center" width="10%">Estado Sicogen</td>\n' +
		'                                        <td align="center" width="10%">Fecha Tramitacion</td>\n' +
		'                                        <td align="center" width="10%">Reproceso</td>\n' +
		'                                    </tr>\n' +
		'                                </thead>\n' +
		'                            </table>\n' +
		'                        </div>');
	console.log('Retornando Nuevo div para tabla Seguimiento !!')
};



function demo(event){

	informe = event.data.informe;
	console.log("demo - informe: "+informe);

	if (informe == 2 || informe == 11 || informe == 12 ){

		try {
			$(".ui-dialog-content").dialog("close");
		}catch(err) {}

		var tmpPartida = document.getElementById("contPartida").value;
		//console.log("demo - tmpPartida: "+tmpPartida);
		var partida = tmpPartida.substring(0,2);
		//console.log("demo - partida: "+partida);
		var capitulo = document.getElementById("contCapitulo").value;
		//console.log("demo - capitulo: "+capitulo);
		var codigo = event.data.periodo;
		//console.log("demo - codigo: "+codigo);
		var ejercicio = event.data.ejercicio;
		//console.log("demo - ejercicio: "+ejercicio);
		var periodo2 = event.data.periodo2;
		//console.log("demo - periodo2: "+periodo2);
		var tipoInforme = document.getElementById("contTipoInformes").value;
		//console.log("demo - tipoInforme: "+tipoInforme);

		//console.log("llamando a getCboVentana !!!!");
		window.parent.getCboVentana(partida, capitulo, tipoInforme, ejercicio, informe, periodo2, codigo,window.parent.cargaPopupTDR(informe));


	}else{
		abrePopUpEvent(event);
	}

	return true;

}

function liVerInformeAccordion(idFileUp, idSistraDoc){
	var parametros = 'idFileUp=' + idFileUp + '&idSistradoc=' + idSistraDoc;
	var action = 'seguimiento/verInformeTDR?'+ parametros;
	console.log(action);
	window.open(action,'_blank', 'scrollbars=1,resizable=1');
}

function liVerRepValidacionAccordion(idFileUp,idSistraDoc){
	var parametros = 'idFileUp=' + idFileUp + '&idSistradoc=' + idSistraDoc;
	var action = 'validacion/obtenerValidacionTDR?'+parametros;
	console.log(action);
	window.open(action,'_blank','scrollbars=1,resizable=1');
}

function liVerDescargaArchivoAccordion(idFileUp){
	dowFil = '/sicogen-mf/documentos/descargaDoc?idArchivo=' + idFileUp;
	location.href=dowFil;
}



function liVerResumenErroresAccordion(informe){
	//var parametros=informe;

	window.parent.popUpAcordion(informe);

	$("#dialog").dialog( "close" );
	$("#dialog").css('width','680px');
	$("#dialog").css('left','300px');


	return false;

}


function liVerBitAccordion(idFileUp){

	console.log("liVerBitAccordion: "+idFileUp);

	var parametros=idFileUp;
	$("#divColFechaTramitacion"	,"#dialogBitacora").show();
	$("#divColFechaEnvio"		,"#dialogBitacora").hide();

	popUpBitacora(parametros);




	return false;
}


function cierraBitacoras(){
	$('#fadeBitacora').remove();
	$('#contBitacora').remove();
}





function getState(state){
	var estado='';
	switch(state){
		case '3' :	estado="Informe con error bloqueante cargado ";break;
		case '4' :	estado="Validado en CGR ";break;
		case '5' :	estado="Validado con observaciones en CGR ";break;
		//case '6' :	estado="Enviado a CGR ";break;
		//case '7' :	estado="Enviado con observaciones a CGR ";break;
		case '6' :	estado="Procesado ";break;
		case '7' :	estado="Procesado con observaciones ";break;
		case '8' :	estado="Validado sin movimiento en CGR ";break;
		case '10':	estado="Enviado sin movimiento a CGR ";break;
	}
	return estado;
}




function popUpBitacoraCertificado(parametros){

	console.log("popUpBitacora: "+parametros);

	$('#contBitacoraCert').html('');

	$.ajax({
		url:'verBitacoraInforme?parametros='+parametros,
		type: "POST",
		dataType:"json",
		async: false,
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('popUpBitacora - error');
			$("#dialogBitacoraCert").dialog({}).dialog("close");
			jAlert('No existen registros para mostrar', "Bitácora Informe");
		},
		success: function(data){
			console.log("popUpBitacora - success");
			var fila='';

			$.each(data.listaReporteBitacora, function(i, item){

				console.log(item);

				if(i%2==0){
					fila='';
					fila= '<div class="rwdetInfImp">'+
						'<div style="width:99px" class="detalleInfColError">'+item.estado+'</div>'+
						'<div style="width:99px"  class="detalleInfColErrorFinal">'+item.usuario +'</div>'+
						'<div style="width:189px;text-align:center"  class="detalleInfColError">'+item.fechaEnvio+'</div>'+
						'<div style="width:99px" class="detalleInfColErrorFinal">'+item.certificado +'</div>'+
						'</div>';
					$('#contBitacoraCert').append(fila);
				}else{
					fila='';
					fila ='<div class="rwdetInfPar">'+
						'<div style="width:99px" class="detalleInfColError">'+item.estado+'</div>'+
						'<div style="width:99px"  class="detalleInfColErrorFinal">'+item.usuario +'</div>'+
						'<div style="width:189px;text-align:center"  class="detalleInfColError">'+item.fechaEnvio+'</div>'+
						'<div style="width:99px" class="detalleInfColErrorFinal">'+item.certificado +'</div>'+
						'</div>';
					$('#contBitacoraCert').append(fila);
				}
			});


			$("#contBitacoraCert").show();
			//$("#dialogBitacora").dialog("open");


			$dlgBitacora= $("#dialogBitacoraCert").dialog();

			$("#dialog").dialog( "close" );
			$("#dialog").css('width','680px');
			$("#dialog").css('left','300px');
			$("#dialog").css('height','200px');
			$("#dialogBitacoraCert").parent().css('width','680px');
			$("#dialogBitacoraCert").parent().css('left','25%');
			$("#dialogBitacoraCert").parent().css('top','8%');
			$("#dialogBitacoraCert").parent().css('scroll','hidden');
			$("#ui-id-2").text($("#inf0" + informe).text());
			$(".ui-dialog-titlebar-close").css('background-color', '#F2F2F2');
			$("span.ui-dialog-title",$dlgBitacora.parent()).text("Bitácora");

		}
	});

	return true;
}





function verComplementosEnviados(codigoPartida, codigoCapitulo, idEjercicio){

	var usuario=$('#txtEntidad').text()+' - '+$('#txtUsuario').text();
	var capitulo=$('#txtEntidad2').text()+' - '+$('#txtUsuario2').text();

	//$('#txtUsuario').text();

	$('body').append('<div id="fadeComp" class="overlay" ></div>'+
		'<div id="formComplemento" class="contenedorEnvio modal" style="left:10%; width:1000px; overflow-y: scroll;">'+
		'  	<div id="userLog" style="clear: both; font: bold 12px sans-serif; margin-bottom: 10px;">'+usuario+'</div>'+
		'  	<div id="userLog2" style="clear: both; font: bold 12px sans-serif; margin-bottom: 10px;">'+capitulo+'</div>'+

		'		<div style="clear: both; font: bold 14px sans-serif;" >INFORMES DE CORRECCI&Oacute;N</div>'+
		//			 '		<div id="nombreInforme" style="clear: both; font: bold 14px sans-serif;" ></div>'+
		'<table id="tblComplemento" style="font:bold 12px arial;width:100%;">'+
		'<thead style="background-color:#0066b8;color:#fff;"><tr>'+
		'<th style="width:60px;padding:5px; display:none;">idFileU</th>'+
		'<th style="width:60px;padding:5px; display:none;">ejercicioNombre</th>'+
		'<th style="width:60px;padding:5px; display:none;">periodoEjercicioId</th>'+
		'<th style="width:60px;padding:5px; display:none;">ejercicioId</th>'+
		'<th style="width:60px;padding:5px; display:none;">informeId</th>'+
		'<th style="width:60px;padding:5px; display:none;">tipoInformeId</th>'+
		'<th style="width:60px;padding:5px; display:none;">entidadId</th>'+
		'<th style="width:60px;padding:5px;">Periodo</th>'+
		'<th style="width:120px;padding:5px;">Usuario</th>'+
		'<th style="width:50px;padding:5px;">Fecha</th>'+

		'<th style="width:90px;padding:5px;">Estado</th>'+
		'<th style="width:55px;padding:5px;">Informe</th>'+
		'<th style="width:80px;padding:5px;">Reporte de Validaci&oacuten</th>'+
		'<th style="width:80px;padding:5px; display:none;">Reporte de Cuadratura</th>'+
		'<th style="width:60px;padding:5px;">Certificado de Env&iacute;o</th>'+
		'<th style="width:60px;padding:5px;">Respaldo</th>'+
		'<th style="width:200px;padding:5px;">Comentario</th>'+
		'</tr></thead><tbody></tbody></table>'+

		'<div style="margin: 20px 0; padding:0 auto;float:right" >'+
		'	<button value="validar_Informe" onclick="printResumenCompl();" style="cursor:pointer;margin:0 15px;float:left;" class="boton" type="button" id="Btn_imprimirCorr">'+
		'		</img>Imprimir</button>'+
		//			 '	<button  id="Btn_PdfCorr" value="validar_Informe" style="cursor:pointer;margin:0 15px;float:left;" class="boton" type="button">'+
		//			 '		Guardar</button>'+
		'	<button value="cierra_VtnCorrec" onclick="cierraComplementos()" style="cursor:pointer;margin:0;float:left;" class="boton" type="button" id="btn_cerrarVtnCorr">'+
		'		Cerrar</button>'+
		'</div>'+
		'</div>');

	var ent="";
	/**
	 if ($("#cbComuna").length > 0){
		ent=$("#cbComuna option:selected").val();
	}else{
		ent=0;
	}
	 */

//	$('#Btn_PdfCorr').click({ejr:ejercicio,inf:informe,ent:ent,ninf:nombreInf,nejr:nomEjer },function(e){	getPDFResumenCorreccion(e);	});
	$('#fadeComp').show();
	$('#formComplemento').show();
//	$('#nombreInforme').text(nombreInf + " - EJERCICIO " + nomEjer);

	//$('#userLogComplemento').text(nombreInf + " - EJERCICIO " + nomEjer);


	//var action='getCorrecionesEjer.action?ejercicioId='+ejercicio+'&informe='+informe+'&entidad='+ent;

	$('body').css('cursor', 'wait');

	$.ajax({
		url:'getCorrecionesEjer2.action',
		type:'POST',
		dataType:'json',
		data:{codigoPartida:codigoPartida,codigoCapitulo:codigoCapitulo,idEjercicio:idEjercicio},
		error: function(XMLHttpRequest, textStatus, errorThrown){},
		success: function(data){
//	    	switch(data.estado){
//		    	case -2:jAlert(data.mensaje, "Ver Complementos", function(r){if(r){$(location).attr('href',url='home.action');}} );break;
//		    	case -1:jAlert(data.mensaje, "Ver Complementos", function(r){if(r){$(location).attr('href',url='login');}} );break;
//
//	    	}

			if(data.listaCorrecciones.length>0){
				$('#contComplementos').append('<div id="contDetalleComplementos" class="contDetalleComplementos"></div>');
			}

			$.each(data.listaCorrecciones, function(i, item) {
				var row;
				var clase='';
				if(i%2==0){clase='rwdetInfPar';}else{clase='rwdetInfImp';}
				row='<tr id="rw_Compl'+i+'" class="'+clase+'" style="display: table-row;font:10px arial;">';

				row=row+'<td style="width:70px;padding:5px; display:none;">'+item.archivoId+'</td>';
				row=row+'<td style="width:70px;padding:5px; display:none;">'+item.ejercicioNombre+'</td>';
				row=row+'<td style="width:70px;padding:5px; display:none;">'+item.periodo+'</td>';
				row=row+'<td style="width:70px;padding:5px; display:none;">'+item.ejercicioId+'</td>';
				row=row+'<td style="width:70px;padding:5px; display:none;">'+item.informeId+'</td>';
				row=row+'<td style="width:70px;padding:5px; display:none;">'+item.tipoInformeId+'</td>';
				row=row+'<td style="width:70px;padding:5px; display:none;">'+item.entidadId+'</td>';
				row=row+'<td style="width:70px;padding:5px;">'+item.periodoNombre+'</td>';
				row=row+'<td style="width:120px;padding:5px;">'+item.usuario+'</td>';
				row=row+'<td style="width:50px;padding:5px;">'+item.fecha+'</td>';
				row=row+'<td style="width:90px;padding:5px;">'+item.estadoDescripcion+'</td>';
				if(item.archivoId!=0){
					row=row+'<td style="width:80px;padding:5px;"><a style="text-decoration: underline;" id="showInforme'+i+'">Ver Informe</a></td>';
					row=row+'<td style="width:100px;padding:5px;"><a style="text-decoration: underline;" id="showReporte'+i+'">Ver Validacion</a></td>';
					row=row+'<td style="width:100px;padding:5px; display:none;"><a id="showCuadratura'+i+'">Ver Cuadratura</a></td>';

					if (item.certificado>0){ row=row+'<td style="padding:5px;"><a style="text-decoration: underline;" id="showCertificado'+i+'">Ver Certificado</a></td>';}
					else{row=row+'<td></td>';}
				}else{
					row=row+'<td></td><td></td><td></td><td></td>';
				}

				if(item.urlFile !== null && item.urlFile !== undefined){
					row=row+'<td style="cursor:pointer;padding:5px;"><a style="text-decoration: underline;" id="showRespaldo'+i+'">Ver Respaldo</a></td>';
				}else{
					row=row+'<td></td>';
				}

				row=row+'<td style="width:200px;padding:5px;">'+item.comentario+'</td>';
				row=row+'</tr>';
				$('#tblComplemento tbody').append(row);

				if(item.urlFile !== null && item.urlFile !== undefined){
					$('#showRespaldo'+i).click({corr:item.correccionId },function(e){
						document.location.href='descargaArchivoRespaldo.action?idFileUp='+item.archivoId;
					});
				}

				if(item.archivoId!=0){
//		    		var parametros=item.corrInfId+',"'+ item.informe +'",'+item.informeId+','+nomEjer+','+item.corrInfId;
					var parametros=item.archivoId+','+item.periodo+','+item.tipoInformeId+','+item.ejercicioNombre+','+item.informeId+','+item.entidadId+','+item.ejercicioId;
					console.log(parametros+" cristianParametros");
//		    		var parametros2=item.correccionId+',"'+ item.informe +'",'+item.informeId+','+nomEjer+','+item.correccionId;

					$('#showInforme'+i).attr('onclick','muestraInformeCorreccion('+parametros+')');
					$('#showInforme'+i).css({'cursor':'pointer'});
					$('#showReporte'+i).attr('onclick','muestraValidacionCorreccion('+parametros+')');
					$('#showReporte'+i).css({'cursor':'pointer'});
					if (item.certificado>0){$('#showCertificado'+i).attr('onclick','muestraCertificadoCorreccion('+item.certificado+')');$('#showCertificado'+i).css({'cursor':'pointer'});}

					$('#showCuadratura'+i).css({'cursor':'pointer'});

//		    		$('#Btn_PdfCorr').click({ejr:ejercicio,inf:informe,ent:ent,ninf:nombreInf,nejr:nomEjer },function(e){	getPDFResumenCorreccion(e);	});
//		    		$('#showCuadratura'+i).click({ejr:ejercicio,inf:informe,ent:ent,ninf:nombreInf,nejr:nomEjer,per:item.periodoId, tpInf:item.correccionId },function (e){showReportCuadrCompl(e);});
				}
			});
			$('body').css('cursor', 'default');
		}
	});

}

function cierraComplementos(){

	$('#fadeComp').remove();
	$('#formComplemento').remove();
}

function printResumenCompl(){

	//var imagen = document.getElementById("logo").innerHTML;
	var printContentsEnc = document.getElementById("formComplemento").innerHTML;
	var printContents = printContentsEnc;

	var originalContents = document.body.innerHTML;
	document.body.innerHTML = printContents;

	if (confirm("¿Imprimir página?"))
	{
		$("#pdf").css({ display: "none"});
		// $('#detalle').css({ width: "1743px"});
		// document.getElementById('detalle').style.overflow="hidden";
		window.print();
	}
	document.body.innerHTML = originalContents;

}



function muestraInformeCorreccion(idFileU,periodo,tpInforme,glosaEjercicio,informeId,entidad,ejrId){

	var parametros = 'idFileUp='+idFileU+'&periodo='+periodo+'&tpInforme='+tpInforme+'&glosaEjercicio='+glosaEjercicio+'&informe='+informeId+'&entidad='+entidad+'&ejrId='+ejrId;

	url = 'verInforme.action?'+parametros;

	var windowSizeArray = [ "width=200,height=200", "width=300,height=400,scrollbars=yes" ];
	var windowName = "popUp";
	var windowSize = windowSizeArray[  $(this).attr("rel")  ];

	window.open(url, windowName, windowSize);
}

function muestraValidacionCorreccion(idFileU,periodo,tpInforme,glosaEjercicio,informeId,entidad,ejrId){

	var parametros = 'idFileUp='+idFileU+'&periodo='+periodo+'&tpInforme='+tpInforme+'&glosaEjercicio='+glosaEjercicio+'&informe='+informeId+'&entidad='+entidad+'&ejrId='+ejrId;

	url= 'getValidacionInforme.action?'+parametros;

	var windowSizeArray = [ "width=200,height=200", "width=300,height=400,scrollbars=yes" ];
	var windowName = "popUp";
	var windowSize = windowSizeArray[  $(this).attr("rel")  ];

	window.open(url, windowName, windowSize);
}


function muestraCertificadoCorreccion(certificado){

	url='certificadoEnvio2.action?certificado='+ certificado;
	var windowSizeArray = [ "width=200,height=200","width=300,height=400,scrollbars=yes" ];
	var windowName = "popUp";
	var windowSize = windowSizeArray[  $(this).attr("rel")  ];

	window.open(url, windowName, windowSize);
}

function verErrores(){

	if(informe==1){
		window.parent.loadResumenErroresPI(idFileUpload);
	}else if(informe==3){
		window.parent.loadResumenErroresIC(idFileUpload);
	}else if(informe==12){
		window.parent.loadResumenErroresTDR(idFileUpload);
	}

}

function verErroresTDR(idFileUpload){
	window.parent.loadResumenErroresTDR(idFileUpload);
}

function reprocesoTDR(idFileUpload) {
	$("#loading-spinner").addClass("is-active");
	$.post('./seguimiento/reprocesoTDRMP', {
		idFileUp: idFileUpload
	}).done(function (data) {
		$("#loading-spinner").removeClass("is-active");
		dialogReprocesoTDRMP.html('<p>' + data.msgEjec + '</p>');
		dialogReprocesoTDRMP.dialog('open');
		dialogReprocesoTDRMP.dialog('option', 'width', '320');
	});
}

function reloadDatosTDRMP() {
	$("#loading-spinner").addClass("is-active");
	$.get('./seguimiento/getDatosTablaDetallePostVal', function (data) {
		$('#datosAccordion').html(data);
		$("#loading-spinner").removeClass("is-active");
	});
	dialogReprocesoTDRMP.dialog('close');
}