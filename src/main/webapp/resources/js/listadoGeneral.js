function CargarReporteDeCuadratura(contenedorCapa, idFile, informe, periodo, ejercicio){
	var $dataDiv,$dataCont;

	$dataDiv=$(contenedorCapa).dialog({title:'Reporte de Cuadraturas - Informe Contable'}).dialog("open");
	$(contenedorCapa).parent().css('width','1000px');
	$(contenedorCapa).parent().css('height','540px');
	$(contenedorCapa).parent().css('zIndex','8888');
	$(contenedorCapa).parent().css('position','absolute');
	$(contenedorCapa).parent().css('left','15%');
	$(contenedorCapa).parent().css('top','13%');
	$(contenedorCapa).parent().css('scroll','hidden');
	$(".ui-dialog-titlebar-close").css('background-color', '#F2F2F2');
	$dataCont=$("#contResumenCuadratura",contenedorCapa);

	$('#divResumenError').html('');

	$('#fadeChangeTab').show();
	$('#popEspera').show();
	$("#ui-datepicker-div").remove();

	cargando();
	$.ajax({
		url: './seguimiento/reporteCuadratura?fileUploadID='+idFile+"&reporteID="+0+"&periodoID="+periodo+"&ejercicioID="+ejercicio+"&informeID="+informe,
		type: "POST",
		dataType:"html",
		async: true
		,error: function(XMLHttpRequest, textStatus, errorThrown)
		{
			stop();
			$(contenedorCapa).dialog({}).dialog("close");

			$.confirm({
				title: 'Reporte de Cuadraturas',
				content: 'No existen registros para mostrar',
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
		}
		,success: function(data) {
			stop();
			$dataCont.html(data);
			$("#repCuadratura").html("");
			$('body').css('cursor','default');
			$('#fadeChangeTab').remove();

			//$('#popEspera').remove();
		}
	});


	$dataCont=$("#contResumenCuadratura",$dataDiv);

}


function popUpBitacora(parametros){


	mostrarDiv("divColFechaEnvio");
	ocultarDiv("divColFechaTramitacion");

	$('#contBitacora').html('');

	$.ajax({
		url:'./seguimiento/getBitacoras?idFileUpload='+parametros,
		type: "POST",
		dataType:"json",
		async: false,
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('popUpBitacora - error');
			$("#dialogBitacora").dialog({}).dialog("close");

			$.confirm({
				title: 'Bitácora Informe',
				content: 'No existen registros para mostrar',
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
		},
		success: function(data){
			console.log("popUpBitacora - success");
			var fila='';


			var fechaBit;
			console.log(data.listaReporteBitacora);

			$.each(data, function(i, item){

				if($("#divColFechaTramitacion"	,"#dialogBitacora").css('display')!='none'){
					fechaBit=item.fechaTramitacion;

				}else if( $("#divColFechaEnvio"		,"#dialogBitacora").css('display')!='none'){
					fechaBit=item.certificado;
				}

				if(i%2==0){
					fila='';
					fila= '<div class="rwdetInfImp">'+
						'<div style="width:99px" class="detalleInfColError">'+item.estado+'</div>'+
						'<div style="width:99px"  class="detalleInfColErrorFinal">'+item.usuario +'</div>'+
						'<div style="width:189px;text-align:center"  class="detalleInfColError">'+item.fechaEnvio+'</div>'+
						'<div style="width:99px" class="detalleInfColErrorFinal">'+fechaBit +'</div>'+
						'</div>';
					$('#contBitacora').append(fila);
				}else{
					fila='';
					fila ='<div class="rwdetInfPar">'+
						'<div style="width:99px" class="detalleInfColError">'+item.estado+'</div>'+
						'<div style="width:99px"  class="detalleInfColErrorFinal">'+item.usuario +'</div>'+
						'<div style="width:189px;text-align:center"  class="detalleInfColError">'+item.fechaEnvio+'</div>'+
						'<div style="width:99px" class="detalleInfColErrorFinal">'+fechaBit +'</div>'+
						'</div>';
					$('#contBitacora').append(fila);
				}
			});


			$("#contBitacora").show();

			$dlgBitacora= $("#dialogBitacora").dialog({
				maxWidth:680,
				maxHeight: 400,
				width:680,
				height:400,
				title: 'Bitácora'
			});

			$("#dialog").dialog( "close" );
			$("#dialog").css('width','680px');
			$("#dialog").css('left','300px');
			$("#dialogBitacora").parent().css('zIndex','8887');
			$("#dialogBitacora").parent().css('position','absolute');
			$("#dialogBitacora").parent().css('width','680px');
			$("#dialogBitacora").parent().css('left','25%');
			$("#dialogBitacora").parent().css('top','8%');
			$("#dialogBitacora").parent().css('scroll','hidden');
			$("#dialogBitacora").css('overflow','hidden');
			$("#ui-id-2").text($("#inf0" + informe).text());
			$(".ui-dialog-titlebar-close").css('background-color', '#F2F2F2');

		}
	});

	return true;
}

function loadResumenErroresIC(idFileUpload)
{
	var ent="";
	$('#divResumenError').html('');

	$.ajax({
		url: './carga/getResumenErrores?idFileUpload='+idFileUpload,
		type: "POST",
		dataType:"json",
		async: false
		,error: function(XMLHttpRequest, textStatus, errorThrown)
		{
			console.log('error');
			$("#dialogErrorCGF").dialog({}).dialog("close");

			$.confirm({
				title: 'Resumen errores',
				content: 'No existen registros para mostrar',
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
		}
		,success: function(data) {

			$('#divResumenError').text("");
			$('#divResumenError').empty();
			console.log("1");
			var fila='';
			$.each(data, function(i, item) {


				if(i%2==0){
					fila='';
					fila= '<div class="rwdetInfImp">'+
						'<div class="detalleInfColError">'+item.tipoError+'</div>'+
						'<div class="detalleInfColErrorFinal">'+item.mensajeError +'</div>'+
						'</div>';
					$('#divResumenError').append(fila);
				}else{
					fila='';
					fila ='<div class="rwdetInfPar">'+
						'<div class="detalleInfColError" id="inf02_Dic">'+item.tipoError+'</div>'+
						'<div class="detalleInfColErrorFinal">'+item.mensajeError+' </div>'+
						'</div>';
					$('#divResumenError').append(fila);
				}
			});

			$dlgResumen= $("#dialogErrorCGF").dialog();
			$("#dialog").dialog( "close" );
			$("#dialog").css('width','760px');
			$("#dialog").css('left','300px');
			$("#dialogErrorCGF").parent().css('width','760px');
			$("#dialogErrorCGF").parent().css('left','25%');
			$("#dialogErrorCGF").parent().css('top','10%');
			$("#dialogErrorCGF").parent().css('scroll','hidden');
			$("#ui-id-2").text($("#inf0" + informe).text());
			$(".ui-dialog-titlebar-close").css('background-color', '#F2F2F2');
			$("span.ui-dialog-title",$dlgResumen.parent()).text("Resumen de Errores");
		}
	});
}

function loadResumenErroresPI(informe){
	var ent="";

	console.log("loadResumenErroresPI");

	$('#divResumenError').html('');
	$.ajax({
		url: './carga/getResumenErroresPI?idFileUpload='+informe,
		type: "POST",
		dataType:"json",
		async: false
		,error: function(XMLHttpRequest, textStatus, errorThrown)
		{
			console.log('error');
			$("#dialogErrorCGF").dialog({}).dialog("close");
			$.confirm({
				title: 'Resumen errores',
				content: 'No existen registros para mostrar',
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
		}
		,success: function(data) {
			$('#divResumenError').text("");
			$('#divResumenError').empty();
			console.log("1");
			var fila='';
			$.each(data, function(i, item) {


				if(i%2==0){
					fila='';
					fila= '<div class="rwdetInfImp">'+
						'<div class="detalleInfColError">'+item.tipoError+'</div>'+
						'<div class="detalleInfColErrorFinal">'+item.mensajeError +'</div>'+
						'</div>';

					$('#divResumenError').append(fila);
				}
				else{
					fila='';
					fila ='<div class="rwdetInfPar">'+
						'<div class="detalleInfColError" id="inf02_Dic">'+item.tipoError+'</div>'+
						'<div class="detalleInfColErrorFinal">'+item.mensajeError+' </div>'+
						'</div>';
					$('#divResumenError').append(fila);
				}


			});

			$dlgResumen= $("#dialogErrorCGF").dialog();
			$("#dialog").dialog( "close" );
			$("#dialog").css('width','680px');
			$("#dialog").css('left','300px');
			$("#dialogErrorCGF").parent().css('width','680px');
			$("#dialogErrorCGF").parent().css('left','25%');
			$("#dialogErrorCGF").parent().css('top','10%');
			$("#dialogErrorCGF").parent().css('scroll','hidden');
			$("#ui-id-2").text($("#inf0" + informe).text());
			$(".ui-dialog-titlebar-close").css('background-color', '#F2F2F2');
			$("span.ui-dialog-title",$dlgResumen.parent()).text("Resumen de Errores");

		}
	});
}

function loadResumenErroresTDR(informe)
{
	var ent="";
	console.log("loadResumenErroresTDR");
	$('#divResumenError').html('');
	$.ajax({
		url: './carga/getResumenErroresTDR?idFileUpload='+informe,
		type: "POST",
		dataType:"json",
		async: false
		,error: function(XMLHttpRequest, textStatus, errorThrown)
		{
			console.log('error');
			$("#dialogErrorCGF").dialog({}).dialog("close");

			$.confirm({
				title: 'Resumen errores',
				content: 'No existen registros para mostrar',
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
		}
		,success: function(data) {
			$('#divResumenError').text("");
			$('#divResumenError').empty();
			console.log("1-->");
			var fila='';
			$.each(data, function(i, item) {


				if(i%2==0){
					fila='';
					fila= '<div class="rwdetInfImp">'+
						'<div class="detalleInfColError">'+item.tipoError+'</div>'+
						'<div class="detalleInfColErrorFinal">'+item.mensajeError +'</div>'+
						'</div>';

					$('#divResumenError').append(fila);
				}
				else{
					fila='';
					fila ='<div class="rwdetInfPar">'+
						'<div class="detalleInfColError" id="inf02_Dic">'+item.tipoError+'</div>'+
						'<div class="detalleInfColErrorFinal">'+item.mensajeError+' </div>'+
						'</div>';
					$('#divResumenError').append(fila);
				}
			});

			$dlgResumen= $("#dialogErrorCGF").dialog();
			$("#dialog").dialog( "close" );
			$("#dialog").css('width','680px');
			$("#dialog").css('left','300px');
			$("#dialogErrorCGF").parent().css('width','680px');
			$("#dialogErrorCGF").parent().css('left','25%');
			$("#dialogErrorCGF").parent().css('top','10%');
			$("#dialogErrorCGF").parent().css('scroll','hidden');
			$("#ui-id-2").text($("#inf0" + informe).text());
			$(".ui-dialog-titlebar-close").css('background-color', '#F2F2F2');
			$("span.ui-dialog-title",$dlgResumen.parent()).text("Resumen de Errores");

		}
	});
}

function cargaPopupTDR(informe){

	var popUp;
	var dialogTitle;

	switch (informe) {
		case 12:
			dialogTitle = 'TDR PRESUPUESTO INICIAL OTRAS ENTIDADES'
			break
		case 11:
			dialogTitle = 'TDR MODIFICACION DE PRESUPUESTO'
			break
		case 2:
			dialogTitle = 'TDR PRESUPUESTO INICIATIVAS DE INVERSIÓN'
			break
		default:
			dialogTitle = 'DESCONOCIDO'
	}

	var opt = {
		autoOpen: true,
		modal: true,
		width: 923,
		show: "blind",
		title: dialogTitle
	};

	console.log("cargaPopupTDR.");
	$("#divPopUpAmpliadoTDR").css({'left':'0px'});
	$("#divPopUpAmpliadoTDR").css({'margin-top':'0px'});
	popUp = $("#divPopUpAmpliadoTDR").dialog(opt);
	popUp.dialog("open");
	$("span.ui-dialog-title").css({'color': '#fff'});
	//$("span.ui-dialog-title").text($('#inf'+informe).text().toUpperCase());

	$(".ui-widget-overlay").css({'background':'#000'});
	//$(".ui-widget-overlay").css({'background':'#000 url(images/ui-bg_flat_0_aaaaaa_40x100.png)  50% 100% repeat-x ;'});
}

var datoPartida = "";
var datoCapitulo = "";
var datoInforme = "";
var datoEjercicio = "";
var datoCodigo = "";
function getCboVentana(partida, capitulo, tipoInforme, ejercicio, informe, periodo2, codigo,funcionConfirmacion){

	datoPartida = partida;
	datoCapitulo = capitulo;
	datoInforme = informe;
	datoEjercicio = ejercicio;
	datoCodigo = codigo;

	$('input:text[name=codigo]').val(codigo);
	//console.log('getCboVentana: codigo'+codigo);
	$('input:text[name=ejercicio]').val(ejercicio);
	//console.log('getCboVentana: ejercicio'+ejercicio);
	$('input:text[name=periodo2]').val(periodo2);
	//console.log('getCboVentana: periodo2'+periodo2);

	var action = './cargacombos/combosTDR?partida='+partida+'&capitulo='+capitulo+'&tipoInforme='+tipoInforme+'&ejercicio='+ejercicio+'&informe='+informe+'&periodo2='+periodo2;
	$.ajax({
		url: action,
		type: "POST",
		dataType: "json",
		beforeSend: function (xhr) {

			if ( $('#fadePeriodos.overlay').length < 1 ){
				window.parent.cargando();
			}
		},complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
			window.parent.stop();

			$.confirm({
				title: 'Error',
				content: textStatus,
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
		},
		success: function(data){
			window.parent.stop();


			console.log("getCboVentana: Cargando Combo !!!");
			$("#cbDatosCombo").get(0).options.length = 0;
			$("#cbDatosCombo").get(0).options[0] = new Option("Seleccione", "-1");

			$.each(data, function (i, item) {
				$('#cbDatosCombo').append($('<option>', {
					value: item.codPrograma, //item.fileId,
					text : item.codPrograma + ' ' + item.nombreCombo, //item.codCapitulo +' '+item.nombre ,
					id   : item.codPrograma //item.idCapitulo
				}));
			});

			$('#opciones').hide();
			$('input:text[name=informe]').val(informe);
			$('#button').remove();
			CrearNuevoDiv();
		}
	});

}

function getDatos(){

	console.log("getDatos()");

	var tmpPartida = datoPartida;
	console.log("getDatos() - tmpPartida: "+tmpPartida);

	var partida = tmpPartida.substring(0,2);
	console.log("getDatos() - partida: "+partida);

	var capitulo = datoCapitulo;
	console.log("getDatos() - capitulo: "+capitulo);

	var codigo = document.getElementById("cbDatosCombo").value; //$('input:text[name=codigo]').val();
	console.log("getDatos() - codigo: "+codigo);

	var ejercicio =  $('input:text[name=ejercicio]').val(); //event.data.ejercicio;
	console.log("getDatos() - ejercicio: "+ejercicio);

	var tipoInforme = datoInforme;
	console.log("getDatos() - tipoInforme: "+tipoInforme);

	var periodo2 = $('input:text[name=periodo2]').val(); //event.data.periodo2;
	console.log("getDatos() - periodo2: "+periodo2);

	if (codigo == -1){

		$('#button').remove();
		CrearNuevoDiv();

	}else{

		var url =    './seguimiento/getDatosTablaDetalle?partida='+partida+'&capitulo='+capitulo+'&codigo='+codigo+'&ejercicio='+ejercicio+'&informe='+tipoInforme+'&periodo2='+periodo2;

		$.ajax({
			url: url,
			type: "POST",
			dataType: "html",
			beforeSend: function (xhr) {
				cargando();
			},complete: function (xhr, status) {
				if (status === 'error' || !xhr.responseText) {
                    stop();
					$.confirm({
						title: 'Error',
						content: "Error en la llamada",
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
				}
				else {
					var data = xhr.responseText;
					$('#fadePeriodosTDR').remove();
					$('#waitPeriodosTDR').remove();
				}

			},error: function(XMLHttpRequest, textStatus, errorThrown){

                stop();
				$.confirm({
					title: 'Error',
					content: textStatus,
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
			},success: function(data)
			{
				stop();
				$('#datosAccordion').html(data);
			}
		});
	}




}


function mostrarDiv(div){

	document.getElementById(div).style.display = "block";
	document.getElementById(div).style.visibility = "visible";

}

function ocultarDiv(div){

	document.getElementById(div).style.display = "none";
	document.getElementById(div).style.visibility = "hidden";
}