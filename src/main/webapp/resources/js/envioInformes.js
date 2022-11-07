
function actualizaObjetosPorValidacionDos(xml){
	
	$('#detInfEnvio1').text('');
	var iNOk=0;
	var mensaje='';
	
	console.log('actualizaObjetosPorValidacionDos');
	console.log(xml);
	
	$.each(xml.salidaEnvioCgr, function(i, item) {
	    clase="";
	    if(item.informeEstado!='3'){
	    	clase="";
			if ((i+1) %2 == 0){ clase= "rwdetInfPar"; }else{ clase= "rwdetInfImp"; }
			row='envInf1_' + item.informeId;
			jQuery('<div/>',{id:row, class:clase }).appendTo('#detInfEnvio1');
			
			jQuery('<div/>',{id:row+'nombreInforme'}).appendTo('#'+row);
			$('#'+row+'nombreInforme').css({width:"505px",float:"left",display:"cell"});
			$('#'+row+'nombreInforme').html( $('#envInf_'+item.informeId+'nombreInforme').html() );
			
			//alert( $('#'+row+'nombreInforme').text() );
			
			jQuery('<div/>', { id: row + 'glsPeriodo', text:item.informePeriodoCod }).appendTo('#'+row);
			$('#'+row+'glsPeriodo').css({width:"80px",float:"left",display:"cell"});
			
			jQuery('<div/>',{ id: row+'glsEjercicio', text:item.informePeriodoCod }).appendTo('#'+row);
			$('#'+row+'glsEjercicio').css({width:"65px",float:"left",display:"cell"});
			
			jQuery('<div/>', { id: row + 'nota', text: item.informeMensaje }).appendTo('#'+row);
			$('#'+row+'nota').css({width:"250px",float:"left",display:"cell"});
			
	    }else{
	    	if (iNOk==0){ mensaje='<div id="erroresEnvio"><div id="TituloErrorEnvio"></div>'; }
	    	mensaje=mensaje+'<div id="errorTitulo">'+ $('#inf0'+ item.informeId).text()+'</div>';
			mensaje=mensaje+'<div id="errorMensaje">'+item.informeMensaje+'</div>';
			iNOk=iNOk+1;
	    }
	});
	if (iNOk>0){
		
		var $dialog = $('<div></div>')
    	.dialog({height: 250,width: 600,zIndex:1005,
    		id: "erroresSelEnvio",
    		title: 'Errores en seleccion de envio',
    		close: function(event, ui){	$(this).remove();}});
		//$dialog.css({'zIndex': 1005});
		$dialog.dialog('open');
		$dialog.html(mensaje);
		
		return;
	}else{
		$("body").css("cursor","default"); $('#fadeComp').css({ display:"block"});informes ='';enviaCertificado();
	}
}


$(document).ready(function() {
	$('#btnValidaInformes_A').click(function(){		
		var estado=enviaInformes();
		if (estado==1){
			return false;
		}
	});	
});



var listaejercicio  = new Array();
var imprime  = new Array();
var informes = '';


function eliminaFormErrores(){
	$('#erroresEnvio').remove(); 
}


function actualizaObjetosPorValidacion(xml){
	console.dirxml(xml);
	$('#detInfEnvioError').text("");
	$('#detInfEnvio').text("");
	var iOk=0;
	var iNOk=0;
	
	$.each(xml.salidaEnvioCgr, function(i, item) {
	    console.log(this);
	    
	    clase="";
	    if(item.informeEstado!='3'){
	    	console.log('ok Estado: ');
	    	if ((iOk+1) %2 == 0){ clase= "rwdetInfPar"; }else{clase= "rwdetInfImp"; }
    	    iOk=iOk+1;
    	    console.log('infOk'+iOk);
    	    		
    	    row='envInf_' + item.informeId;
	
    	    jQuery('<div/>', { id: row, class: clase }).appendTo('#detInfEnvio');		
    	    jQuery('<div/>', { id: row}).appendTo('#'+row);
    	    jQuery('<div/>', { id: row + '_chk' }).appendTo('#'+row);
	
    	    $('#' + row + '_chk').css({ width: "30px", float: "left"});				
    	    $('#' + row + '_chk').append(
			$(document.createElement('input')).attr({  
				id: 'chk_'+row ,value: 'chk_' + row,
				name: item.informeId+"/"+item.informeNombre+"/"+item.informeEstado+"/"+item.informePeriodoCod+"/"+item.informePeriodo+"/"+
				item.informeEjercicioCod+"/"+item.informeEjercicio+"/"+item.informeTipo+"/"+ item.informeMensaje+"/",
				type:  'checkbox'}));
	    }else{
	    	if ((iOk+1) %2 == 0){ clase= "rwdetInfPar"; }else{clase= "rwdetInfImp"; }
	    	iNOk=iNOk+1;
	    	row='envInf_' + $(this).find("idInforme").text();
			jQuery('<div/>', {id: row, class: clase }).appendTo('#detInfEnvioError');
	    }
		jQuery('<div/>', { id:row+'nombreInforme', text: item.informeNombre }).appendTo('#'+row);
		$('#'+row+'nombreInforme').css({width: "480px", float: "left", display: "cell"});
		
		jQuery('<div/>', { id:row+'glsPeriodo', text: item.informePeriodo }).appendTo('#'+row);
		$('#'+row+'glsPeriodo').css({width: "80px", float: "left", display: "cell"});
		
		jQuery('<div/>', { id:row+'glsEjercicio', text: item.informeEjercicioCod }).appendTo('#'+row);
		$('#'+row+'glsEjercicio').css({width: "85px", float: "left", display: "cell"});
		
		jQuery('<div/>', { id:row+'nota', text: item.informeMensaje }).appendTo('#'+row);
		if ( $.browser.mozilla == true && $.browser.version < '3.0' ) {
			console.log("mozilla");
			$('#'+row+'nota').css({width: "245px", float: "left", display: "cell"});
		  }
		else{
			console.log("Other browser");
			$('#'+row+'nota').css({width: "145px", float: "left", display: "cell"});
		}
	});
	if (iOk!=0){$('#notInformForSend').css({ display: "none"}); $('#Btn_enviar').removeAttr("disabled");}
	if (iNOk!=0){$('#fila_no_detalle_error').css({ display: "none"});}
	
	console.log('infOk: '+iOk);
	if(iOk==0){
		$('#notInformForSend').css({display: "block"});
		
		$('#Btn_enviar').attr('disabled','disabled');
	}else{
		$('#Btn_enviar').removeAttr("disabled");
	}
	
	if(iNOk==0){
		jQuery('<div/>', {
		    id: 'fila_no_detalle_error',				    
			class: 'rowEnvInfImp'
		}).appendTo('#detInfEnvioError');
		$('#fila_no_detalle_error').text("No Hay Informes Con errores");
	}
}


function actualizaObjetosPorValidacionCertifica(xml){
	
	console.log('carga informes');
	console.log(xml);
	$('#detInfEnvioPdf').text('');
	$('.detInfEnvioPdf').text('');
	
	$('#detInfEnvioPdf').html('');
	$('.detInfEnvioPdf').html('');
	var iOk=0;
	var iNOk=0;
	$.each(xml.salidaEnvioCgr, function(i, item){
		console.log('Nombre: '+item.informeNombre);
		console.log('Periodo: '+item.informePeriodo);
		console.log('Ejercicio: '+item.informeEjercicioCod);
		console.log('Ejercicio2: '+item.informeEjercicio);
		console.log('Nota: '+item.msg);
		
		$('#fechaPreEnv').text(xml.fecha);
		if(item.informeEstado!='3'){
			var clase="";
			if ((i+1) %2 == 0){ clase= "rwdetInfPar"; }else{ clase= "rwdetInfImp"; }			
			var row='envInf_' + item.informeId;

			jQuery('<div/>', { id: row, Class: clase+' '+row }).appendTo('.detInfEnvioPdf');			
			jQuery('<div/>', { id: row + 'nombreInforme', Class:row + 'nombreInforme' }).appendTo('.' + row);
			$('.'+row+'nombreInforme').text($('#envio_'+item.informeId).text());
			$('.'+row+'nombreInforme').css({width: "405px", float: "left", display: "cell"});
			
			
			jQuery('<div/>', { id: row + 'glsPeriodo', Class:row + 'glsPeriodo', text: item.informePeriodoCod }).appendTo('.'+row);			
			$('.' + row + 'glsPeriodo').css({width: "80px", float: "left" , display: "cell"});
			
			jQuery('<div/>', { id: row + 'glsEjercicio', Class:row + 'glsEjercicio', text: item.informeEjercicioCod }).appendTo('.'+row);
			$('.' + row + 'glsEjercicio').css({width: "65px", float: "left" , display: "cell"});
			
			jQuery('<div/>', { id: row + 'nota', Class:row + 'nota', text: item.informeMensaje }).appendTo('.'+row);
			$('.' + row + 'nota').css({width: "225px", float: "left" , display: "cell"});
			if ( $.browser.mozilla == true && $.browser.version < '3.0' ) {
				console.log("mozilla");
				$('.' + row + 'nota').css({width: "350px", float: "left", display: "cell" });
			  }
			else{console.log("other Browser");
				
				$('.' + row + 'nota').css({width: "300px", float: "left", display: "cell" });
			}
		}else{
			if (iNOk==0){ 
				mensaje='<div id="erroresEnvio"><div id="TituloErrorEnvio"></div>'; 
			}
	    	mensaje=mensaje+'<div id="errorTitulo">'+ $('#inf0'+ item.informeId).text()+'</div>';
			mensaje=mensaje+'<div id="errorMensaje">'+item.informeMensaje+'</div>';
			iNOk=iNOk+1;
		}
	});
	
	console.log('infOk: '+iOk);
	console.log('iNOk: '+iNOk);
	if(iNOk>0){
		var $dialog = $('<div></div>')
    	.dialog({height: 250,width: 600,zIndex:10090,
    		id: "erroresSelEnvio",
    		title: 'Errores en seleccion de envio',
    		close: function(event, ui){	$(this).remove();}});
		//$dialog.css({'zIndex': 1005});
		$dialog.dialog('open');
		$dialog.html(mensaje);
		
	}else{
		$('#formEnvio').hide();
		$('#formEnvio1').hide();
		$('#formEnvio2').show();
		
	}
}


function enviaInformesDos(){
	informes='';
	var infSends = [];
	$("#detInfEnvio input:checked").each(
		function(index){
			var infs=[];
			if(jQuery(this).is(":checked")){
				infs=$(this).attr('name').split("/");
			}
			infSends.push(infs);
	});
	
	console.log(infSends);
	
	if(infSends.length== ''){ jAlert("Debe seleccionar un informe para enviar ",'Envio de Informes'); informes =''; return; }
	var action = 'showInforSendInfDos.action';	 
	
	$('body').css('cursor', 'wait');
	$.ajax({url: action,type:'POST',dataType:'json',data: {'infSends':JSON.stringify(infSends)},
	    success: function(data){
	    	switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='home'); return false;}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');return false;}} );break;
	    	}
	    	if((data.estado!=-1)&&(data.estado!=-2)){ 
	    		actualizaObjetosPorValidacionCertifica(data);
	    		//actualizaObjetosPorValidacionDos(data);
				/*
				$('#fadeComp').css({ display: "block"});
				$('#formEnvio1').css({ display: "none"});
				$('#formEnvio').css({ display: "block"});
				*/
				$("body").css("cursor", "default");
				$('#fadeComp').css({ display: "block"});
				
				
	    	}
	    	$("body").css("cursor", "default");
	    },
	    error: function(XMLHttpRequest, textStatus, errorThrown){		    	
			
	    }
	});
	$('body').css('cursor', 'auto');
}


function procesarArchivoPI(){
	
	var idFileUpload = $("#idFileUpload").val();
	var anioSelected = $('#cbEjercicio option:selected').val();
	
	var action = 'procesarArchivoPI.action?idFileUpload='+idFileUpload+'&anioSelected='+anioSelected;
	console.log("action : "+action);

	$.ajax({
		url: action,
	    type: "POST",
	    dataType: "html",cache: false,async:true,
	    beforeSend: function (xhr) {
			$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
				 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function () {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();

		},error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('Error ' + textStatus);
			alert(errorThrown);
			alert(XMLHttpRequest.responseText);
	    },
		success: function(data,textStatus, request){
			console.log("success");
			$('#tablaDocumentosCarga').html(data);
	    }
	});	
	
}



function procesarArchivoTDR(nroDoc){
	
	var idFileUpload = $("#idFileUpload").val();
	var ejercicio = $('#cbEjercicioTDR option:selected').text();
	var idInforme = $('#cbInforme option:selected').val();
	var tipoInforme = $('#cbTipoInforme option:selected').text();
	var informe = $('#cbInforme option:selected').text();
	
	var action = 'procesarArchivoTDR.action?idFileUpload='+idFileUpload+'&ejercicio='+ejercicio+'&idInforme='+idInforme+"&tipoInforme="+tipoInforme+"&informe="+informe+'&nroSistradoc='+nroDoc;
	console.log("action : "+action);

	$.ajax({
		url: action,
	    type: "POST",
	    dataType: "html",cache: false,async:true,
	    beforeSend: function (xhr) {
			$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
				 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function () {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();

		},error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('Error ' + textStatus);
			alert(errorThrown);
			alert(XMLHttpRequest.responseText);
	    },
		success: function(data,textStatus, request){
			console.log("success");
			$('#tablaDocumentosCarga').html(data);
	    }
	});	
	
}






function validarInformeTDR(nroDoc , idDoc){

	console.log("nroDoc : "+nroDoc);
	
	var idFileUpload = $("#idFileUpload").val();
	var ejercicio = $('#cbEjercicioTDR option:selected').text();
	var idInforme = $('#cbInforme option:selected').val();
	var tipoInforme = $('#cbTipoInforme option:selected').text();
	var informe = $('#cbInforme option:selected').text();
	
	var action = 'validarNegocioTDR.action?idFileUpload='+idFileUpload+'&ejercicio='+ejercicio+'&idInforme='+idInforme+"&tipoInforme="+tipoInforme+"&informe="+informe+'&nroSistradoc='+nroDoc;
	console.log("action : "+action);
	
	var validandose = 0;
	
	$.ajax({
		url: action, 
		type: "GET", 
		dataType:'json',
		async: true,
		cache: false,
		beforeSend: function (xhr){
			var loadImage = '<img id="estado_'+idFileUpload+'" src="images/loader.gif" style="width:25px;height:25px;" />';
			$('#imgEstado').html(loadImage);},
		complete: function (data) {
			console.log("complete");
			$('#fadeSelEnvio').remove();
			$('#waitSelEnvio').remove();
		},
		success: function(data){
			console.log("success : estadoSicogen : "+data.informeTDR.estadoSicogen);
			
			var loadImageRV = '<img  src="images/rv.png" style="float:left; margin-left:16px;" title="Reporte de Validación"' +
                              ' onclick="obtieneReporteValTDR('+idFileUpload+','+idDoc+')"/>';
			
			var iconEstado = "";
			
			 switch (data.informeTDR.estadoSicogen) {
	            case "3":
	                iconEstado = 'images/error.png';
	                $('#imgRVTDR').html(loadImageRV);
	                break;
	            case "4":
	            	iconEstado = 'images/Validado.png';
	            	$('#imgRVTDR').html(loadImageRV);
	                break;
	            case "5":
	            	iconEstado = 'images/ValidadoOBS.png';
	            	$('#imgRVTDR').html(loadImageRV);
	                break;
	            case "6":
	            	$('#imgRVTDR').html(loadImageRV);
	                break;
	            case "7":
	            	$('#imgRVTDR').html(loadImageRV);
	                break;    
	            default:
	            	console.log("default");
                	break;
	        }
			console.log("estadoSicogen "+data.informeTDR.estadoSicogen);
			
			var loadImage = '<img id="estado_'+idFileUpload+'" src="'+iconEstado+'" style="width:25px;height:25px;" />';
			$('#imgEstado').html(loadImage);
			
			console.log("nroDocumento "+data.informeTDR.nroDocumento);
			
			//obtenerArchivoTDR(nroDoc);
			},
		error: function(XMLHttpRequest, textStatus, errorThrown){
				console.log('error');
				alert(XMLHttpRequest);
				alert(textStatus);
				alert(errorThrown);
			}
	});
		
	return validandose;	
		
}

function reValidarInformeTDR(idFileU, ejercicio, idInforme, tipoInforme, nroDoc){	
	
	var action = 'validarNegocioTDR.action?idFileUpload='+idFileU+'&ejercicio='+ejercicio+'&idInforme='+idInforme+"&tipoInforme="+tipoInforme+"&informe="+idInforme+'&nroSistradoc='+nroDoc;
	console.log("action : "+action);
	
	var validandose = 0;
	
	$.ajax({
		url: action, 
		type: "GET", 
		dataType:'json',
		async: true,
		cache: false,
		beforeSend: function (xhr){
			$('body').append('<div id="fadePeriodosTDR" class="overlay" style="position:fixed;display:block"></div>'+
					 '<div id="waitPeriodosTDR" class = "contEspera modalCarga" style="left:35%;position:fixed;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Validando informe</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},
		complete: function (data) {
			console.log("complete");
			$('#fadePeriodosTDR').remove();
			$('#waitPeriodosTDR').remove();
		},
		success: function(data){
			console.log("success : estadoSicogen : "+data.informeTDR.estadoSicogen);
			$("#btnBuscarReval").click();			
			},
		error: function(XMLHttpRequest, textStatus, errorThrown){
				console.log('error');
				alert(XMLHttpRequest);
				alert(textStatus);
				alert(errorThrown);
			}
	});
		
	return validandose;	
		
}


function enviaCertificado(){
	informes="";
	var infSends=[];
	$("#detInfEnvio input:checked").each(function(index){
		var infs=[];
		if(jQuery(this).is(":checked")){
			infs=$(this).attr('name').split("/");
		} 
		infSends.push(infs);
	});

	console.log(infSends);
	var action = 'showInforSendInfTres';
	$('body').css('cursor', 'wait');
	$.ajax({url: action,type: "POST",dataType: "json",data: {'infSends':JSON.stringify(infSends)},
	    success: function(data){
	    	console.log(data);
			actualizaObjetosPorValidacionCertifica(data);
			$("body").css("cursor", "default");
			$('#fadeComp').css({ display: "block"});
			
			$('#formEnvio').hide();
			$('#formEnvio1').hide();
			$('#formEnvio2').show();
			//$('#formEnvio').css({ display: "none"});
			//$('#formEnvio1').css({ display: "none"});
			//$('#formEnvio2').css({ display: "block"});
	    },
	    error: function(XMLHttpRequest, textStatus, errorThrown){
	    	console.log('Error');
	    }
	});
	$('body').css('cursor', 'auto');
}

function enviaCertificado1(){
	var infSends=[];
	$("#detInfEnvio input:checked").each(function(index){
		var infs=[];
		if(jQuery(this).is(":checked")){infs=$(this).attr('name').split("/");} 
		infSends.push(infs);
	});
	console.log(infSends);
	$('#fadeComp').css({ display: "none"});
	$('#formEnvio').css({ display: "none"});
	$('#formEnvio1').css({ display: "none"});
	$('#formEnvio2').css({ display: "none"});
	
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, width=1100px, height=600px,top=0, left=0";
	window.open("enviaInformes?infSends="+JSON.stringify(infSends),"Browse",opciones);
	
	setTimeout(function(){loadPeriodo();}, 5000);
	
}

function enviaCertificado21(){	
	imprimir();
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, width=900px, height=550px, top=85px, left=140px";
	window.open( "enviaInformes?listaejercicio=" + listaejercicio,"Browse",opciones);
}

function cierraEnvio_1(){
	$('#fadeComp').remove();
	$('#formEnvio').remove();
	$('#formEnvio1').remove();
	$('#formEnvio2').remove();
}

function cierraEnvio_2(){
	$('#formEnvio2').hide();
	$('#formEnvio').css({'display':'block'});
	$('#fadeComp').css({'display':'block'});
}


function pasaRegistro(){
	var selecion =null;
	$('#detInfEnvio input:checked').each(function() {
		  if (jQuery(this).is(":checked")){
			  selecion = null;  selecion = $(this).attr('name')+",";
			  console.log('listaEjercicio'+listaejercicio); console.log('seleccion: '+selecion);
			  listaejercicio.push(selecion);
       }  
	});
}

function certificadoPDF(){
	location.href='getCertificadoEnvioPDF.action?cert='+$('#Ncertificado').text();
}
