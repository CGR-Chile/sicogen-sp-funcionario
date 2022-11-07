function isOdd(num) { return num % 2;}

function loadInformesStates(ejercicio, periodo,tipo){
	
	clearInterval(intervalo);
	clearInterval(intervaloCarga);
	
	console.log('loadInformesStates loadInformesStates');
	
	$.ajax({
		url: "getStateInf?ejercicioId="+ejercicio+"&periodo="+periodo+"&tipo="+tipo,
		type: "POST",
		dataType: "json",
		beforeSend: function (xhr) {
			$('body').append('<div id="fadeEstPeriodos" class="overlay" style="display:block"></div>'+
				 '<div id="waitEstPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block;">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function (data) {
			$('#fadeEstPeriodos').remove();
			$('#waitEstPeriodos').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('No Ok');
		    console.log('Error ' + textStatus);
		    console.log(errorThrown);
		    console.log(XMLHttpRequest.responseText);
		    //Me permite validar la session 
		    window.location = "cargaDigitacion";
		},success: function(data){
			switch(data.estado){
	    		case -1:jAlert(data.mensaje, "Carga de Informes", function(r){if(r){$(location).attr('href',url='login');}} );break;
	    		case -2:jAlert(data.mensaje, "Carga de Informes", function(r){if(r){$(location).attr('href',url='showFormCarga.action');}} );break;
		    	
	    	}
			$('#tblUpInformes > tbody').empty();
			var classFila;
			
			$.each(data.listaInformes, function(i, itm) {
				
				if(isOdd(i)) {classFila = 'row02';} 
				else {classFila = 'row01';}
				
				var idPeriodo = $('#cbPeriodos').val();
				$('#tblUpInformes > tbody').append('<tr class='+classFila+'>'+
												'<td><div style="float:left;display:inline-block;width:0%;vertical-align:top;visibility: hidden;">'+itm.informeId+'</td>'+
												'<td><div style="float:left;display:inline-block;width:95%;vertical-align:top;">'+itm.informeCodigo+' - '+itm.informeNombre+'</div></td>'+
												'<td><div class="cel04"><input type="text" id="inf0_informeId_filename" class="txtFile" value="" disabled="disabled"/></td>'+
												'<td><div id="inf0_cel03" class="cel03"><form id="idForm'+itm.informeId+'" action="uploadFile.action?inf='+itm.informeId+'&periodo='+idPeriodo+' method="post" class="formFile" enctype="multipart/form-data">'+
														'<input id="fileUpload" onchange=realizaReglasCarga('+itm.informeId+','+itm.informeId+') name="fileUpload" type="file" class="files"/>'+
														'<button class="botonFile transparent" type="button" id="valCarga">Examinar...</button>'+
														'<input type="hidden" name="Language" value="English"/>'+
														'</form></div></td>'+
												'<td><div id="inf0_cel06" class="cel10"><img src="images/check_on.png" class="icoImage" id="inf03_smov" cursor="default"/></div></td>'+
												'<td><div id="inf0_cel06" class="cel11"/></td>'+
												'<td><div id="inf0_cel07" class="cel05"/></td>'+
												'<td><div id="inf0_cel08" class="cel06"/></td>'+
												'<td><div id="inf0_cel09" class="cel11" style="width:6px;"/></td>'+
												'<td><div id="inf0_cel08" class="cel08"/></td>'+
												'<td><div id="inf0_cel09" class="cel09"/></td></tr>');
			});
			
		}/*,
	    success: function(data){
	    	switch(data.estado){
	    		case -1:jAlert(data.mensaje, "Carga de Informes", function(r){if(r){$(location).attr('href',url='login');}} );break;
	    		case -2:jAlert(data.mensaje, "Carga de Informes", function(r){if(r){$(location).attr('href',url='showFormCarga.action');}} );break;
		    	
	    	}
	    	var notMov=[2,3,4,7,8,9];
	    	$.each(data.listaInformes, function(i, itm) {
	    		var form='uploadFile0'+itm.informeId;
	    		$('#inf01_Reportvalid').unbind("click");
	    		$("form#"+form+" :[name='fileUpload']").attr("disabled", "disabled");
	 			$("#btnfileUpload0"+itm.informeId).attr("disabled", "disabled");
	 			$("#valCarga"+itm.informeId).attr("disabled", "disabled");
	 			//$('#inf0'+itm.informeId+'_smov').click(function() { return false; });
	 			$('#inf0'+itm.informeId+'_smov').unbind("click");
	 			$('#inf0'+itm.informeId+'_Reportvalid').click(function() { return false; });
	 			$('#inf0'+itm.informeId+'_Reportvalid').unbind("click");
	 			
	 			$('#inf0'+itm.informeId+'_filename').val(' ');
	 			$('#inf0'+itm.informeId+'_cel06').text('');		//Mensaje del Informe
				$('#inf0'+itm.informeId+'_State').text('0');	//Estado del Informe
				$('#inf0'+itm.informeId+'_corr').text('0');		
				
				$('#inf0'+itm.informeId+'_respCarga').attr("src", 		"images/blanco.png");
				$('#inf0'+itm.informeId+'_validCarga').attr( "src", 	"images/blanco.png");
				$('#inf0'+itm.informeId+'_Reportvalid').attr("src", 	"images/blanco.png");
				$('#inf0'+itm.informeId+'_Reportvalid').css({"cursor": 	"default"});
				$('#inf0'+itm.informeId+'_smov').attr('src',			"images/check_off.png");
				$('#inf0'+itm.informeId+'_smov').attr({"cursor": 		"default"});
	    		
				if(itm.activacion!=0){
	    			var nomov=0;
	    			var est=parseInt(itm.informeEstadoId,10);
	    			if($.inArray(parseInt(itm.informeId,10), notMov)!=-1){
	    				nomov=1;
	    			}
	    			
	    			if(nomov==1){
	    				if((est==6)||(est==7)||(est==10)){
	    					$('#inf0'+itm.informeId+'_smov').unbind("click");
	    					$('#inf0'+itm.informeId+'_smov').attr('src',			"images/check_on.png");
	    				}else{
	    					$('#inf0'+itm.informeId+'_smov').click(function(){		cargaSinMov(this); });
		    				$('#inf0'+itm.informeId+'_smov').attr('src',			"images/check_on.png");
	    				}
	    			}else{
	    				$('#inf0'+itm.informeId+'_smov').unbind("click");
	    				$('#inf0'+itm.informeId+'_smov').attr('src',			"images/check_off.png");
	    			}
	    			
	    			if(est==8){
		    			$('#inf0'+itm.informeId+'_filename').val(' ');
		    			$('#inf0'+itm.informeId+'_smov').click(function(){	cargaSinMov(this); });
	    				$('#inf0'+itm.informeId+'_smov').attr('src',		"images/check_sel.png");
	    				$("form#"+form+" :[name='fileUpload']").removeAttr("disabled");
	    				$("#btnfileUpload0"+itm.informeId).removeAttr("disabled");
	    				$("#valCarga"+itm.informeId).removeAttr("disabled");
		    		}else if(est==10){
		    			$('#inf0'+itm.informeId+'_filename').val(' ');
		    			$('#inf0'+itm.informeId+'_smov').unbind("click");
		    			$('#inf0'+itm.informeId+'_smov').attr('src',		"images/check_sel.png");
		    			$("form#"+form+" :[name='fileUpload']").attr(		"disabled", "disabled");
		    		}else if((est==6)||(est==7)){
		    			$('#inf0'+itm.informeId+'_filename').val(itm.informeArchivo);
		    			$("form#"+form+" :[name='fileUpload']").attr(		"disabled", "disabled");
						$('#btnfileUpload0' + itm.informeId).attr(			"disabled", "disabled");
						$('#valCarga' + itm.informeId).attr(			"disabled", "disabled");
						$('#inf0'+itm.informeId+'_smov').attr('src',		"images/check_on.png");
						$('#inf0'+itm.informeId+'_smov').unbind("click");
		    		}else{
		    			$('#inf0'+itm.informeId+'_filename').val(itm.informeArchivo);
		    			if (parseInt(itm.informeEstado)==0){
							$("form#uploadFile0"+itm.informeId+" :[name='fileUpload']").attr("disabled", "disabled");
							$('#btnfileUpload0' + itm.informeId).attr("disabled", "disabled");
							$('#valCarga' 		+ itm.informeId).attr("disabled", "disabled");
							$('#inf0'+itm.informeId+'_smov').unbind("click");
			    		}else{
				 			$("form#"+form+" :[name='fileUpload']").removeAttr("disabled");
				 			$("#btnfileUpload0"+itm.informeId).removeAttr("disabled");
				 			$("#valCarga"+itm.informeId).removeAttr("disabled");
			    		}
			    	}
		    		
					$('#inf0'+itm.informeId+'_cel06').text(itm.informeMensaje);		//Mensaje del Informe
					$('#inf0'+itm.informeId+'_State').text(itm.informeEstadoId);	//Estado del Informe
					$('#inf0'+itm.informeId+'_corr').text(itm.corrInformeId);		
					
					$('#inf0'+itm.informeId+'_validCarga').attr( "src", 	itm.imgValid);
					$('#inf0'+itm.informeId+'_Reportvalid').attr("src", 	itm.imgRV);	
					if(itm.cursor ==="pointer")	{
						$('#inf0'+itm.informeId+'_Reportvalid').css({"cursor": 	itm.cursor});					
						$('#inf0'+itm.informeId+'_Reportvalid').attr("onclick",	itm.informeAccion);
					}
					else{
						$('#inf0'+itm.informeId+'_Reportvalid').attr({"cursor":"default"});
						$('#inf0'+itm.informeId+'_Reportvalid').unbind("click");
					}
					$('#inf0'+itm.informeId+'_smov').attr('src',			itm.imgNotMov);
	    		}
            });
	    }*/
	});
}


function loadPeriodoEjr(ejercicio){

	if ($.isNumeric(ejercicio)==false ){ejercicio=0;}
	
	var action = 'getPeriodos.action?ejercicioId=' + ejercicio; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando los periodos");
		    	switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}
		    	$('#tblUpInformes > tbody').empty();
	    	    $("#cbPeriodos").get(0).options.length = 0;
                $("#cbPeriodos").get(0).options[0] = new Option("Selec. Periodo", "-1"); 

                $.each(data.listaPeriodos, function(i, item) {
                	console.log(item);
                    $("#cbPeriodos").get(0).options[$("#cbPeriodos").get(0).options.length] = new Option(item.periodoNombre, item.periodoId);
                });
                
		    }
		});
}

function loadTipoInformePorPeridoPresup(ejercicio, periodo, correccion){

	if ($.isNumeric(ejercicio)==false ){ejercicio=0;}
	
	var action = "getTipoInformePorPeriodoPresup.action?ejercicio="+ejercicio+"&periodo="+periodo+"&correccion="+correccion; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
//		    	switch(data.estado){
//		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
//		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
//		    	}

	    	    $("#cbTipoInformePresup").get(0).options.length = 0;
                $("#cbTipoInformePresup").get(0).options[0] = new Option("Selec. Informe", "-1"); 

                $.each(data.listaTipoInformes, function(i, item) {			                	
                    $("#cbTipoInformePresup").get(0).options[$("#cbTipoInformePresup").get(0).options.length] = new Option(item.nombre, item.id);
                });			                
		    }
		});
}

function loadTipoInformePorPerido(ejercicio, periodo, correccion){

	if ($.isNumeric(ejercicio)==false ){ejercicio=0;}
	
	var action = "getTipoInformePorPeriodo.action?ejercicio="+ejercicio+"&periodo="+periodo+"&correccion="+correccion; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}
		    	$('#tblUpInformes > tbody').empty();
	    	    $("#cbTipoInformes").get(0).options.length = 0;
                $("#cbTipoInformes").get(0).options[0] = new Option("Selec. Tipo de Informe", "-1"); 

                $.each(data.listaTipoInformes, function(i, item) {			                	
                    $("#cbTipoInformes").get(0).options[$("#cbTipoInformes").get(0).options.length] = new Option(item.nombre, item.id);
                });			                
		    }
		});
}

function loadCapitulo(idPartida)
{

	var variable = idPartida.substr(0,2);
	idPartida = variable;
	var idEjercicio = $("#cbEjercicio option:selected").val();
	 
	
	if(variable == "-1"){
		jAlert("Debe seleccionar Partida");
		return false;	
	}
	
	//if ($.isNumeric(idPartida)==false ){idPartida=0;}
 
	var action = 'getCapitulos.action?idPartida=' + idPartida + '&idEjercicio='+ idEjercicio; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando las capitulos");
		    	console.log(data);
 
	    	    $("#cbCapitulo").get(0).options.length = 0;
                $("#cbCapitulo").get(0).options[0] = new Option("Seleccione Capítulo", "-1"); 
 
         		$('#contEstInfAnual').html('<p id="tdc" class="rwdetInfPar" align="center"> No hay informacion para los filtros seleccionados </p>');

                $.each(data.listaCapitulos, function (i, item) {
                    $('#cbCapitulo').append($('<option>', { 
                        value: item.codCapitulo,
                        text : item.codCapitulo +' '+item.nombre ,
                        id   : item.codCapitulo 
                    }));
                });
 
		    }
		});
}

function loadCapituloRepEnv(idPartida)
{	
	var variable = idPartida.substr(0,2);

	
	idPartida = variable;
	//if ($.isNumeric(idPartida)==false ){idPartida=0;}
 
	var action = 'getCapitulos.action?idPartida=' + idPartida; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando las capitulos");
		    	console.log(data);
		    	/*switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}*/
		    	
		    	//$('#tblUpInformes > tbody').empty();
	    	    $("#cbCapituloRepEnvio").get(0).options.length = 0;
                $("#cbCapituloRepEnvio").get(0).options[0] = new Option("Selec. Capítulo", "-1"); 

                /*$.each(data.listaCapitulos, function(i, item) {			                	
                    $("#cbCapitulo").get(0).options[$("#cbCapitulo").get(0).options.length] = new Option(item.codCapitulo +' - '+item.nombre, item.idCapitulo).attr("xxx", "option value");
                   
                });*/	
               
                //$('#contEstInfAnual').html('<p>Tabla vacía<p>');
                
                $.each(data.listaCapitulos, function (i, item) {
                    $('#cbCapituloRepEnvio').append($('<option>', { 
                        value: item.codCapitulo,
                        text : item.codCapitulo +' '+item.nombre ,
                        id   : item.codCapitulo 
                    }));
                });
                
                //$("#cbProgramas").get(0).options.length = 0;
                //$("#cbProgramas").get(0).options[0] = new Option("Selec. Programa", "-1"); 
		    }
		});
}

function loadCapituloRCorr(idPartida)
{	
 
	var variable = idPartida.substr(0,2)
	idPartida = variable;
 
	var action = 'getCapitulos.action?idPartida=' + idPartida; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando las capitulos");
		    	console.log(data);
 
	    	    $("#cbCapituloRCorr").get(0).options.length = 0;
                $("#cbCapituloRCorr").get(0).options[0] = new Option("Selec. Capítulo", "-1"); 
 
                //$('#contEstInfAnual').html('<p>Tabla vacía<p>');
                
                $.each(data.listaCapitulos, function (i, item) {
                    $('#cbCapituloRCorr').append($('<option>', { 
                        value: item.codCapitulo,
                        text : item.codCapitulo +' '+item.nombre ,
                        id   : item.codCapitulo 
                    }));
                });
 
		    }
		});
}

	
function loadCapituloBit(idPartida)
{	
	var variable = idPartida.substr(0,2)
	idPartida = variable;
 
	var action = 'getCapitulos.action?idPartida=' + idPartida; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando las capitulos");
		    	console.log(data);
		    	/*switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}*/
		    	
		    	//$('#tblUpInformes > tbody').empty();
	    	    $("#cbCapituloBit").get(0).options.length = 0;
                $("#cbCapituloBit").get(0).options[0] = new Option("Selec. Capítulo", "-1"); 

                /*$.each(data.listaCapitulos, function(i, item) {			                	
                    $("#cbCapitulo").get(0).options[$("#cbCapitulo").get(0).options.length] = new Option(item.codCapitulo +' - '+item.nombre, item.idCapitulo).attr("xxx", "option value");
                   
                });*/	
                
                //$('#contEstInfAnual').html('<p>Tabla vacía<p>');
                
                $.each(data.listaCapitulos, function (i, item) {
                    $('#cbCapituloBit').append($('<option>', { 
                        value: item.codCapitulo,
                        text : item.codCapitulo +' '+item.nombre ,
                        id   : item.codCapitulo 
                    }));
                });
                
                //$("#cbProgramas").get(0).options.length = 0;
                //$("#cbProgramas").get(0).options[0] = new Option("Selec. Programa", "-1"); 
		    }
		});
}

function loadCapituloInf(idPartida)
{	
	var variable = idPartida.substr(0,2);
	idPartida = variable; 
	var action = 'getCapitulos.action?idPartida=' + idPartida; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando las capitulos");
		    	console.log(data);
 
	    	    $("#cbCapituloInf").get(0).options.length = 0;
                $("#cbCapituloInf").get(0).options[0] = new Option("Selec. Capítulo", "-1"); 
 
                //$('#contEstInfAnual').html('<p>Tabla vacía<p>');
                
                $.each(data.listaCapitulos, function (i, item) {
                    $('#cbCapituloInf').append($('<option>', { 
                        value: item.codCapitulo,
                        text : item.codCapitulo +' '+item.nombre ,
                        id   : item.codCapitulo 
                    }));
                });
  		    }
		});
}

function loadProgramas(idPartida, idCapitulo){

	if ($.isNumeric(idPartida)==false ){idPartida=0;}
	
	var action = 'getProgramas.action?idPartida=' + idPartida+'&idCapitulo=' + idCapitulo; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("success buscando los Programas");
		    	/*switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}*/
		    	
		    	//$('#tblUpInformes > tbody').empty();
	    	    $("#cbProgramas").get(0).options.length = 0;
                $("#cbProgramas").get(0).options[0] = new Option("Selec. Programa", "-1"); 

                $.each(data.listaProgramas, function(i, item) {			                	
                    $("#cbProgramas").get(0).options[$("#cbProgramas").get(0).options.length] = new Option(item.codPrograma +' - '+item.nombre, item.idPrograma);
                });			                
		    }
		});
}

function llenaEntidad(idPartida, idCapitulo){

	//Load...
	$('body').append('<div id="fadeChangeTab" class="overlay" style="display:block"></div>'+
			 '<div id="popEspera" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important">'+ 
			 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
			 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
			 '</div>');
	
	$('body').css('cursor','wait');
	$('#fadeChangeTab').show();
	$('#popEspera').show();
	
	$("#ui-datepicker-div").remove();
	//Fin Load...
	
	var idEjerc = $("#cbEjercicio").val();
	var idParti = $("#cbPartida").val();
	var idCapit = $("#cbCapitulo").val();
	var idProgram = $("#cbProgramas").val();
	var usaDolares = null;//$("#cbCapitulo").data('foo');
	
	var valEjerc = $("#cbEjercicio option:selected").text();
	var valParti = $("#cbPartida option:selected").text();
	var valCapit = $("#cbCapitulo option:selected").text();
	var valProgr = $("#cbProgramas option:selected").text();
	
	console.log('att: '+idEjerc);
	console.log('att: '+idParti);
	console.log('att: '+idCapit);
	console.log('att: '+idProgram);
	
	var action = 'setEncabezadoPI.action?';
	
	action = action + 'idEjerc='+idEjerc;
	action = action + '&valEjerc='+valEjerc;
	action = action + '&idParti='+idParti;
	action = action + '&valParti='+valParti;
	action = action + '&valCapit='+valCapit;
	action = action + '&valProgr='+valProgr;
	action = action + '&idCapit='+idCapit;
	action = action + '&idProgram='+idProgram;
	action = action + '&usaUSD='+usaDolares;
	
	/*var dataPost = {
			idEjerc: idEjerc,
			valEjerc: valEjerc,
			idParti: idParti,
			valParti: valParti,
			idCapit: idCapit,
			valCapit: valCapit
	       };*/
	
	//var dataPost = 'json = {"idEjerc":'+idEjerc+',"valEjerc":'+valEjerc+', "idParti":'+idParti+',"valParti":'+valParti+', "idCapit":'+idCapit+',"valCapit":'+valCapit+'}';
	
	//var dataPost = 'json = {"idEjerc":'+idEjerc+',"valEjerc":'+valEjerc+', "idParti":'+idParti+',"valParti":'+valParti+', "idCapit":'+idCapit+',"valCapit":'+valCapit+'}';
	
	console.log(action);
	
	$.ajax( {url: action,type: "POST",dataType: "html",cache: false,async:true, 
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log("llenaEntidad error");
			alert('Error ' + textStatus);
			alert(errorThrown);
			alert(XMLHttpRequest.responseText);
			
			$('body').css('cursor','default');
			$('#fadeChangeTab').remove();
			$('#popEspera').remove();
		},
		success: function(data,textStatus, request){
			console.log("llenaEntidad success");

			//window.location.reload(false);
			$('#panelDigitacion').html(data);
			//$('#administracion').html(data);
			//$('#cuerpoCargManual').html(data);
			//$('#encDigitacion').html(data);
			
			$('body').css('cursor','default');
			$('#fadeChangeTab').remove();
			$('#popEspera').remove();
		}, complete: function( XMLHttpRequest ) {
			console.log("complete OK ");
			$('body').css('cursor','default');
			$('#fadeChangeTab').remove();
			$('#popEspera').remove();
		}
	});
	
	/*$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos pintando el encabezado");
		    	console.log(data);
		    	$('#txtInformeName').val('Presupuesto Inicial');
		    	$('#txtInformeEjerc').val(data.documentoPI.ejericio);
		    	
		    }
		});
		*/
}

	function searchCuenta(){
		$.ajax({url: 'loadAccountPresupByCodigo.action',
			type:'POST',
			dataType:'json',
			data:{
				ejercicio: $('#cbEjercicio').val(),
				codigoCuenta: $('#txtCodigo').val()
			},
		beforeSend: function (xhr){
			console.log('#searchCuenta beforeSend');
			$('body').append('<div id="fadeCargaCuentas" class="overlay" style="display:block"></div>'+
					'<div id="waitCargaCuentas" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block;">'+
					' <div id="mensajeCargaManual" style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando Plan de cuentas Presupuestario</div>'+
					' <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					'</div>');
		},complete: function (data) {
			console.log('#searchCuenta complete');
			$('#fadeCargaCuentas').remove();
			$('#waitCargaCuentas').remove();
		},success: function(data){
			
			var $dialog = $('<div class="alert"></div>')
			.dialog({height: 200,width: 500,zIndex: 20001,
				id :'popCuentas',
				modal: true,
				title: 'Seleccione Cuenta',
				buttons: {
			        "Aceptar": function() {
			        	var idCuenta=$('input[class="radioCuentas"]:checked').attr('id');
			        	var nomCuenta=$('input[class="radioCuentas"]:checked').attr('data-id');
			        	var esCtaIngresos=$('input[class="radioCuentas"]:checked').attr('data-ingreso');
			        	
			        	var codCuenta=$('input[class="radioCuentas"]:checked').attr('value');
			        	
			        	
			        	
						console.log('clickeado esCtaIngresos '+esCtaIngresos+" - "+idCuenta + ' - '+ nomCuenta);
						$("#txtDenominacion").val(nomCuenta);
						$("#txtIdCuenta").val(idCuenta);
						$("#txtCodCuenta").val(codCuenta);
						$("#flgIsCtaIngreso").val(esCtaIngresos);
						
						$( this ).dialog( "close" );
						
			        }
			      },
				close: function(event, ui){	$(this).remove();}});
			
			$dialog.attr('id','popCuentas');
			$dialog.dialog('open');

			var tableOpciones = '<table id="tblAreasPop" class="tablaAreas" style="display_bock;text-transform: uppercase;padding:5px;">'+
			'<thead><tr><th style="text-align:center; width: 300px">Denominaci&oacute;n</th><th style="text-align:center; width: 100px">Codigo Cuenta</th><th style="text-align:center; width: 100px">Seleccionar</th></tr></thead><tbody/></table>';

			    if ( data.listaCtasPresupuestaria.length === 0 ) {
			    	
			    	$('#popCuentas').append('<p>No se encontró la cuenta ingresada</p>');
					
			    } else {
			    	
			    	$("#popCuentas").append('<form>');
			    	$dialog.html(tableOpciones);
			    	
			    	$.each(data.listaCtasPresupuestaria, function(i,rw){
						
						var newRowContent = "<tr><td style='text-align:center'>" + rw.nombre +"</td><td style='text-align:center'>" +rw.codigo + "</td><td style='text-align:center'><input type=\"radio\" id=\""+rw.id+"\" name='cuenta' class='radioCuentas' data-ingreso='"+ rw.ingreso+"' data-id='"+ rw.nombre+"' value=\"" + rw.codigo  + "\"></td></tr>";

						$("#tblAreasPop tbody").append(newRowContent); 

			    	});
			    	$("#popCuentas").append('</form>');
			    }
			
			},error: function(XMLHttpRequest, textStatus, errorThrown){		    	
		    	console.log('#searchCuenta error');
		    }
		});

	}
	
	function addLinePI(){
		var vtitulo="Error";
		var vEjerInv='Debe ingresar un Ejercicio v&aacute;lido';
		var vCapInv='Debe seleccionar una Entidad v&aacute;lida';
		var vCuentaInv='Debe ingresar una cuenta presupuestaria v&aacute;lida';	
		var vAumInv='El Monto asignado no es numérico';
		
		var monto = $("#txtAumento").val();
		var cuentaID = $("#txtIdCuenta").val();
		var cuentaCod = $("#txtCodCuenta").val();
		var nomCta = $("#txtDenominacion").val();
		var flgIsCtaIngreso = $("#flgIsCtaIngreso").val();
		
		
		if($("#txtIdCapitulo").val()==null || $("#txtIdCapitulo").val()==""){
			jAlert(vCapInv,vtitulo);
			return;
		}
		if(nomCta==null || nomCta==""){
			jAlert(vCuentaInv,vtitulo);
			return;
		}
		
		if(monto==null || monto=="" || $.isNumeric(monto)==false){
			jAlert(vAumInv,vtitulo);
			return;
		}
		
		var action = 'addLineaPI.action?cta='+cuentaCod+'&denom='+nomCta+'&moneda=CLP&monto='+monto+'&flgIsCtaIngreso='+flgIsCtaIngreso
		$.ajax({url: action,
		    type: 'POST',
		    dataType: 'html',cache: false,async:true,
		    error: function(data){
		    	alert("Ocurrio un problema insertaCabecera");
		    	document.getElementById('fade').style.display='none';
			    document.getElementById('formEnvio').style.display='none';
		    },success: function(data,textStatus, request){
		    	
		    		console.log('addLineaPI success');
		    		
		    		$('#panelDigitacion').html(data);

		    },complete: function() {
		    	$('.numbersMils').each(function () {
		    		$(this).mask('999.099.999.999', {reverse: true, maxlength: false});
		    	}); 
		    }
		});
	}
	
	function addLineGlosa(codCuenta){
		var $dialog = $('<div class="alert"></div>')
		.dialog({height: 200,width: 500,zIndex: 20001,
			id :'popCuentas',
			modal: true,
			title: 'Agregar Glosa',
			buttons: {
		        "Aceptar": function() {
		        	
		        	var areaGlosa = $("#areaGlosa").val();
					var cuentaGlosa = codCuenta;
					
					// To do: Validar si "areaGlosa" es vacia
					
					var action = 'addLineGlosa.action?areaGlosa='+areaGlosa+'&cuentaGlosa='+cuentaGlosa;
					console.log('action : '+action);
					
						$.ajax({url: action,
						    type: 'POST',
						    dataType: 'html',cache: false,async:true,
						    error: function(data){
						    	alert("Ocurrio un problema al cargar presupuesto inicial");
						    	document.getElementById('fade').style.display='none';
							    document.getElementById('formEnvio').style.display='none';
						    },success: function(data,textStatus, request){
						    	console.log('cargaDigitacionPI success: ');
						    	$('#administracion').html(data);
						    }
						});
					
					$( this ).dialog( "close" );
					
		        },"Cancelar": function() {
		        	$( this ).dialog( "close" );
		        }
		      },
			close: function(event, ui){	$(this).remove();}});
		
		$dialog.attr('id','popCuentas');
		$dialog.dialog('open');
		
    	$("#popCuentas").append('<form>');
    	
    	//Info: Si existe glosa, mostrla en el textArea
    	var glosaAnterior = $("#gl_"+codCuenta).attr('title');
    	if(glosaAnterior==null){
    		var newRowContent = "<textarea id='areaGlosa' placeholder='Ingrese una glosa aquí' style='width: 100%; height: 100%'></textarea>";
        	
    	}else{
    		var newRowContent = "<textarea id='areaGlosa' placeholder='Ingrese una glosa aquí' style='width: 100%; height: 100%'>"+glosaAnterior+"</textarea>";
        	
    	}
    	$dialog.html(newRowContent);
    	//$("#tblAreasPop tbody").append(newRowContent); 
    	$("#popCuentas").append('</form>');
	}	
	
	function cargaDigitacionPI(){
		var action = 'cargaDigitacionPI.action'
		$.ajax({url: action,
		    type: 'POST',
		    dataType: 'html',cache: false,async:true,
		    error: function(data){
		    	alert("Ocurrio un problema al cargar presupuesto inicial");
		    	document.getElementById('fade').style.display='none';
			    document.getElementById('formEnvio').style.display='none';
		    },success: function(data,textStatus, request){
		    	
		    		console.log('cargaDigitacionPI success: ');
		    		$('#administracion').html(data);
		    }
		});
	}
	
/*Es necesario para darle formato a la tabla	$('#listaIngresos').dataTable({
		 "oLanguage": {
		     "sEmptyTable":"Tabla vacía"
		 },
		 "paging":   false,
	     //"ordering": false,
	     "info":     false,
		 "bRetrieve":true,
		 "bDestroy": true,
		 "aoColumns": [
		                  { "asSorting": [  ] },
		                  { "asSorting": [  ] },
		                  { "asSorting": [  ] },
		                  { "asSorting": [  ] },
		                  { "asSorting": [  ] },
		                  { "asSorting": [  ] },
		                  { "asSorting": [  ] },
		                  { "asSorting": [  ] }]
   } );*/
	