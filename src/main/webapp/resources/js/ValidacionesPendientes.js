var general=0;
var per_general=0;
var intervalo;
var intervaloCarga;
var ventana=1;
function validaInfPendientesPpal(pPeriodo, ejercicio, tipoArchivo){
	
	console.log('validaInfPendientesPpal');
	var estado=0;
	clearInterval(intervalo);
	clearInterval(intervaloCarga);
	
	$.ajax({
		url:'getValidandoseAnual.action?periodo='+pPeriodo+'&ejercicio='+ejercicio+'&tipoArchivo='+tipoArchivo, 
		type: "GET", 
		dataType:'json',
		async: false,
		cache: false,
		success: function(data){
			var popUpInf="";
			if(data.informesValidandose.informesEstados.length>0){
				for (var i=0;i<data.informesValidandose.informesEstados.length;i++){
					$('.pi'+data.informesValidandose.informesEstados[i].periodoInforme).attr('src',data.informesValidandose.informesEstados[i].imgValid);
				}
			}
			if(data.informesValidandose.informesPendientes.length>0){
				for (var i=0;i<data.informesValidandose.informesPendientes.length;i++){
					
					$('.pi'+data.informesValidandose.informesPendientes[i].periodoInforme).attr('src','images/loader.gif');
				}
				jAlert('En este momento se encuentran Informes en proceso de validaci&oacute;n.');
				
				estado=1;
				general=1;
				$('#formEnvio').hide();
			}else{
				cierraPopUpPendientes();
			}
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){console.log('error');}
	});
	return estado;	
}
function validaInfPendientes(pPeriodo,ejercicio,tipoArchivo){
	console.log('validaInfPendientes');
	var estado=0;
	clearInterval(intervalo);
	clearInterval(intervaloCarga);
	//url:'getValidandose.action?periodo='+pPeriodo,
	$.ajax({
		url:'getValidandoseAnual.action?periodo='+pPeriodo+'&ejercicio='+ejercicio+'&tipoArchivo='+tipoArchivo,
		type: "GET", 
		dataType:'json',
		async: false,
		cache: false,
		success: function(data){
			var popUpInf="";
			
			if(data.informesValidandose.informesEstados.length>0){//Informes Validados o procesados
				for (var i=0;i<data.informesValidandose.informesEstados.length;i++){
					var infId=data.informesValidandose.informesEstados[i].informeId;
					console.log('Accion:'+ data.informesValidandose.informesEstados[i].informeAccion);
					
					$('#inf0'+infId+'_respCarga').attr('src',data.informesValidandose.informesEstados[i].imgCarga);
					$('#inf0'+infId+'_validCarga').attr('src',data.informesValidandose.informesEstados[i].imgValid);
					$('#inf0'+infId+'_Reportvalid').attr('src',data.informesValidandose.informesEstados[i].imgRV);
					$('#inf0'+infId+'_Reportvalid').attr('onclick',data.informesValidandose.informesEstados[i].informeAccion);
					$('#inf0'+infId+'_Reportvalid').css({'cursor':'pointer'});
					$('#inf0'+infId+'_cel06').text(data.informesValidandose.informesEstados[i].informeMensaje);
					$('#inf0'+infId+'_State').text(data.informesValidandose.informesEstados[i].informeEstadoId);
				}
			}
			if(data.informesValidandose.informesPendientes.length>0){
				for (var i=0;i<data.informesValidandose.informesPendientes.length;i++){
					if($("#cbPeriodos").attr('disabled')!==undefined || data.informesValidandose.informesPendientes[i].informePeriodo!==$("#cbPeriodos option:selected").val()){
						return;
					}
					
					var infId=data.informesValidandose.informesPendientes[i].informeId;
					$('#inf0'+infId+'_respCarga').attr('src','images/blanco.png');
					$('#inf0'+infId+'_validCarga').attr('src','images/loader.gif');
					$('#inf0'+infId+'_Reportvalid').attr('src','images/blanco.png');
					$('#inf0'+infId+'_Reportvalid').attr('onclick','').unbind('click').css({'cursor':'default'});
					$('#inf0'+infId+'_Reportvalid').css({'cursor':'default'});
					$('#inf0'+infId+'_cel06').text('');
				}
				jAlert('En este momento se encuentran Informes en proceso de validaci&oacute;n.');
				estado=1;
			}else{
				cierraPopUpPendientes();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){console.log('error');}
	});
	return estado;	
}

function reconsultaPendientes(periodo, ejercicio,tipoArchivo){
	var pend=0;
	$.ajax({url:'getValidandose.action?periodo='+periodo+'&ejercicio='+ejercicio+'&tipoArchivo='+tipoArchivo,
		type: "GET", 
		dataType:'json',
		async: true,
		cache: false,
		success: function(data){
			popUp='';
			if(data.listaInformeValidandose.length > 0){
				for (var i=0;i<data.listaInformeValidandose.length;i++){
					infId=data.listaInformeValidandose[i].informeId;
					if(data.listaInformeValidandose[i].infEstadoFlujo == 2){
						$('#inf0'+infId+'_validCarga').attr('src','images/loader.gif');
						$('#inf0'+infId+'_Reportvalid').attr('src','images/blanco.png');
						$('#inf0'+infId+'_Reportvalid').attr('onclick','').unbind('click').css({'cursor':'default'});
						$('#inf0'+infId+'_cel06').html('');
					}else{
						$('#inf0'+infId+'_respCarga').attr('src',data.listaInformeValidandose[i].imgCarga);
						$('#inf0'+infId+'_validCarga').attr('src',data.listaInformeValidandose[i].imgValid);
						$('#inf0'+infId+'_Reportvalid').attr('src',data.listaInformeValidandose[i].imgRV);
						$('#inf0'+infId+'_Reportvalid').attr('onclick',data.listaInformeValidandose[i].informeAccion);
						$('#inf0'+infId+'_Reportvalid').css({'cursor':'pointer'});
						$('#inf0'+infId+'_cel06').attr('src',data.listaInformeValidandose[i].informeMensaje);
					}
				}
				if (data.estado!=99){
					clearInterval(intervalo);
					clearInterval(intervaloCarga);
				}
					
			}
		}
	});
}
function reconsultaPendientesAnual(periodo, ejercicio,tipoArchivo){
	var pend=0;
	$.ajax({url:'getValidandose.action?periodo='+periodo+'&ejercicio='+ejercicio+'&tipoArchivo='+tipoArchivo,
		type: "GET", 
		dataType:'json',
		async: true,
		cache: false,
		success: function(data){
			popUp='';
			if(data.informesValidandose.informesEstados.length>0){
				for (var i=0;i<data.informesValidandose.informesEstados.length;i++){
					$('.pi'+data.informesValidandose.informesPendientes[i].periodoInforme).attr('src',data.informesValidandose.informesPendientes[i].imgValid);
				}
			}
			if(data.informesValidandose.informesPendientes.length>0){
				for (var i=0;i<data.informesValidandose.informesPendientes.length;i++){
					$('.pi'+data.informesValidandose.informesPendientes[i].periodoInforme).attr('src','images/loader.gif');
				}
			}else{
				 loadPeriodoRefresh();
				clearInterval(intervalo);
				clearInterval(intervaloCarga);

			}
		}
	});
}
function reconsultaPendByPerd(periodo,ejercicio,tpFile){
	console.log('reconsultaPendByPerd');
	var pend=0;
	console.log('reconsultaPendByPerd linea 156 ValidacionesPendientes.js');
	$.ajax({
		url:'getValidandoseAnual.action?periodo='+periodo+'&ejercicio='+ejercicio+'&tipoArchivo='+tpFile,
		//url:'getValidandoseAnual.action',
		type: "POST", 
		dataType:'json',
		//data:{	periodo:periodo,ejercicio:ejercicio,tipoArchivo:tpFile},
		async: true,
		cache: false,
		success: function(data){
			var isValid = (typeof (data) != "undefined"
				&& typeof (data.informesValidandose) != "undefined"
				&& typeof (data.informesValidandose.informesEstados[i]) != "undefined")?(true):(false);			
			if(data.informesValidandose.informesEstados.length>0){//Informes Validados o procesados
				for (var i=0;i<data.informesValidandose.informesEstados.length;i++){
					var infId=data.informesValidandose.informesEstados[i].informeId;
					console.log('Accion:'+ data.informesValidandose.informesEstados[i].informeAccion);
					
					$('#inf0'+infId+'_respCarga').attr('src',data.informesValidandose.informesEstados[i].imgCarga);
					$('#inf0'+infId+'_validCarga').attr('src',data.informesValidandose.informesEstados[i].imgValid);
					$('#inf0'+infId+'_Reportvalid').attr('src',data.informesValidandose.informesEstados[i].imgRV);
					$('#inf0'+infId+'_Reportvalid').attr('onclick',data.informesValidandose.informesEstados[i].informeAccion);
					$('#inf0'+infId+'_Reportvalid').css({'cursor':'pointer'});
					$('#inf0'+infId+'_cel06').text(data.informesValidandose.informesEstados[i].informeMensaje);
					$('#inf0'+infId+'_State').text(data.informesValidandose.informesEstados[i].informeEstadoId);
				}
			}
			if(data.informesValidandose.informesPendientes.length>0){
				for (var i=0;i<data.informesValidandose.informesPendientes.length;i++){
					//console.log(data.informesValidandose.informesEstados[i] !== undefined);
					if(isValid){
						if($("#cbPeriodos").attr('disabled')!== data.informesValidandose.informesEstados[i] && data.informesValidandose.informesEstados[i].informeAccion == $("#cbPeriodos option:selected").val()){
						var infId=data.informesValidandose.informesPendientes[i].informeId;
						$('#inf0'+infId+'_respCarga').attr('src','images/blanco.png');
						$('#inf0'+infId+'_validCarga').attr('src','images/loader.gif');
						$('#inf0'+infId+'_Reportvalid').attr('src','images/blanco.png');
						$('#inf0'+infId+'_Reportvalid').attr('onclick','').unbind('click').css({'cursor':'default'});
						$('#inf0'+infId+'_Reportvalid').css({'cursor':'default'});
						$('#inf0'+infId+'_cel06').text('');
						
						}
					}
				}
				//jAlert('En este momento se encuentran Informes en proceso de validaci&oacute;n.');
				estado=1;
			}else{
				cierraPopUpPendientes();
			}
		}
	});
}
function cierraPopUpPendientes(){
	
	//jAlert('termina la actualizacion automatica del estado los informes ');
	
	clearInterval(intervalo);
	clearInterval(intervaloCarga);
	
	$('.validandoseAlert').remove();
	general=0;	
	$('.overlayCarga').hide();
	$('.overlay').hide();
}