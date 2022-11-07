function fncNombreArchivo(objeto, obsMensaje, estado){
	$(objeto).html(obsMensaje);
}

var flagCierraPopUpPendientes = false;


function validarInforme(tipo){
	
	clearInterval(intervalo);
	clearInterval(intervaloCarga);
	
	var tipoArchivo=1; var periodo=-1;
	if($("#cbPeriodos").attr('disabled') == 'disabled'){ tipoArchivo=2; periodo=$("#cbComplPeriodos option:selected").val(); }
	else{  tipoArchivo = 1; periodo = $("#cbPeriodos option:selected").val(); }
	
	if (periodo < 0){
		jAlert('Debe seleccionar un periodo valido para el informe', titulo);
		$("body").css("cursor", "default");
		return false;
	}
	
	var tpFile=0;
	if($("#cbPeriodos").attr('disabled') == 'disabled'){ 
		tpFile=$("#cbComplPeriodos option:selected").val(); 
	}else{  
		tpFile= 0; 
	}
	
	var pendientes=validaInfPendientes(periodo,0,tpFile);
	
	if (pendientes==1){
		console.log("Hay pendientes");
		
		clearInterval(intervalo);
		intervalo=setInterval(function(){
			reconsultaPendByPerd(periodo,0,tpFile);
			},5000);
		
		
		return false;
	}
	else{
		cierraPopUpPendientes();
	}
	
	per_general=periodo;
	var nameobj='';
	if (tipo==1){ nameobj='inf0'; }else{nameobj='2inf0';}
	
	var cantValid=0;
	ventana=2;
	var infValid="<ul id='liPendientes' style='margin:15px;list-style:none;'>";
	var vInfVal=0;
	var vInfAct=0;
	
	for (var i=0;i<9; i++) {
		var estado=parseInt($('#inf0'+(i+1)+'_State').text(),10);
		if ((estado==1)||(estado==2)){
			cantValid=cantValid+1;			
			infValid=infValid+"<li><img id='estado_"+(i+1)+"' src='images/loader.gif' style='width:15px;height:15px;' /> "+
					  "<span style='font-weight:bold;'>"+$('#inf0'+(i+1)+'_cel02').text()+"</span></li>";
		}
		
		if(estado>2){vInfVal+=1;}
		if(!$("#valCarga"+(i+1)).is(':disabled')) { vInfAct+=1;}
	}
	
	var faltan=false;
	if(vInfVal+cantValid<vInfAct){
		faltan=true;
	}
	
	if (cantValid==0){
		jAlert('No existen informes pendientes de validacion',titulo);
		return false;
	}
	
	setTimeout(function(){cierraPopUpPendientes();}, 90000);
	
	$('#estadoForm').html('');
	
	var conf='No se han cargado todos los informes; &iquest;Desea continuar?';
	var titulo='Validaci&oacute;n de Informes';
	if(faltan==true){
		jConfirm(conf, titulo, function(r) {
			if(r){
				gatillaValidacion(periodo,tipoArchivo);
			}else{
				resp=false;
			}
		});
	}else{
		gatillaValidacion(periodo,tipoArchivo);
	}
}


function gatillaValidacion(periodo,tipoArchivo){
	//$('#fade').show();
	$('.validandoseAlert').remove();
	
	for (var i=0;i<9; i++) {
		var objeto='#inf0'+(i+1)+'_State';
		if (($(objeto).text()==1)||($(objeto).text()==2)){
			
			$('#inf0'+(i+1)+'_validCarga').attr('src','images/loader.gif');
			$('#inf0'+(i+1)+'_Reportvalid').attr('src','images/blanco.png');
			$('#inf0'+(i+1)+'_Reportvalid').attr('onclick','').unbind('click');
			$('#inf0'+(i+1)+'_cel06').html('');
			
			if($("#cbPeriodos").attr('disabled') == 'disabled'){ 
				tpFile=$("#cbComplPeriodos option:selected").val(); 
			}else{  
				tpFile= 0; 
			}

			clearInterval(intervalo);
			intervalo=setInterval(function(){
							reconsultaPendByPerd(periodo,0,tpFile);
						},15000);

			
			//var action = 'validateInforms.action?informe='+(i+1)+'&periodo='+periodo+'&tipoArchivo='+tipoArchivo;
			$.ajax({
				url:		'validateInforms.action?informe='+(i+1)+'&periodo='+periodo+'&tipoArchivo='+tipoArchivo,
				type: 		'GET', 
				dataType:	'json',
				//data:{		informe:(i+1),periodo:		periodo,tipoArchivo:	tipoArchivo	},
				//timeout:15000,
				async: true, 
				cache: false,
				success: function(data){
					console.log("Suceess consolelogi");
					console.log(data);
					clearInterval(intervalo);
					intervalo=setInterval(function(){
							reconsultaPendByPerd(periodo,0,tpFile);
						},6000);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					console.log("Error consolelogi");
					console.log(data);
					
				}
			});
		}
	}
}


function muestraRC(){
	
	var periodo=-1;var ejercicio=0;var tipoArchivo=0;
	if($("#cbPeriodos").attr('disabled')=='disabled'){ tipoArchivo=2; periodo=$("#cbComplPeriodos option:selected").val(); }
	else{  tipoArchivo=1; periodo=$("#cbPeriodos option:selected").val(); ejercicio=$("#cbEjercicio option:selected").val();}
	
	var parametros='?periodo='+periodo+'&tpInforme='+tipoArchivo+'&informe=0&entidad=0&ejrId='+ejercicio;
	
	$('body').append('<div id="fadeRC" class="overlay"></div>'+
			 '<div id="popup1" class = "contenedorEnvioArchivo modalCarga" style="display:none;left:5%;position:absolute;width:1075px;height:550px;z-index:2005;padding:15px !important">'+ 
			 '    <div id="cerrar" style="float:rigth;">'+
			 '		 <img src="images/close.png" class="icoImage25" style="float:right;border: 0px none;" onclick="cierraReporteCuadratura()"></img>'+
			 '	  </div>'+
			 '	  <div id ="popRepCuadr" style="width:1005px;height:550px;border:0px none;float:left;"></div>'+
			 '</div>');
	$("body").css("cursor", "wait");
	$.ajax({url: 'repCuadratura'+parametros,
		type: "POST",
		dataType: "html",
		async: true,
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$("body").css("cursor", "default");
			console.log('error');
		},
		success: function(data){
			$('#popRepCuadr').html(data);
			$("body").css("cursor", "default");
		}
	});
	
	$('#fadeRC').show();
	$('#popup1').show();
}