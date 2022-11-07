


 
function realizaReglasCargaTDR(idDoc,nroDoc){
	
	console.log("realizaReglasCargaTDR - idDoc: "+idDoc);
	console.log("realizaReglasCargaTDR - nroDoc: "+nroDoc);
	
	// Se debe rescartar los combobox para traer info de Sistradoc !!
	var ejercicio = $('#cbEjercicioTDR option:selected').text();
	var informe = $('#cbInforme option:selected').text();
	var tipoInforme = $('#cbTipoInforme option:selected').text();
	var periodoEjer = $('#cbPeriodosTDR option:selected').val();
	var idTipoInforme = 1;
	
	var action = 'uploadFileTDR?idSistradoc='+idDoc+'&nroSistradoc='+nroDoc+'&ejercicio='+ejercicio+'&informe='+informe+'&tipoInforme='+tipoInforme+'&periodoEjer='+periodoEjer+'&idTipoInforme='+idTipoInforme;	
	
	action = encodeURI(action);
	console.log("action: "+ action);
	
	$("#idFormTDR").ajaxSubmit({
		cache : false,
		type: "POST",
		url: action,
		scriptCharset: 'windows-1252',
		contentType: "application/x-www-form-urlencoded;charset=windows-1252",
		jsonpCallback: 'jsonpCallback',
		data: $(this).serialize(),
		dataType:'html',
		//sizeLimit: (15000 * 1024), no funciona
		beforeSend: function (xhr) {
			$('body').append('<div id="fadeCargaXML" class="overlay" style="display:block"></div>'+
				 '<div id="waitCargaXML" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando archivo</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},
		complete:function( XMLHttpRequest ) {
			console.log("realizaReglasCarga: complete");
			
			$('#waitCargaXML').remove();
			$('#fadeCargaXML').remove();
		},
		success: function(data){
			console.log("realizaReglasCarga: success");
			console.log(data);
			//jAlert("Respuesta",data.informeTDR.estadoSicogen);
			
			$("body").css("cursor", "default");
			$('#tablaDocumentosCarga').html(data);
		}
	 });	
	
}


function obtenerArchivoPI(idEjercicio){

	var action = 'obtenerArchivoPI.action?idEjercicio='+idEjercicio; 
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
				
				//Nose porque hay q ponerlo, pero funciona
				//document.getElementById('fade').style.display='none';
			},error: function(XMLHttpRequest, textStatus, errorThrown){
				alert('Error ' + textStatus);
				alert(errorThrown);
				alert(XMLHttpRequest.responseText);
		    },
			success: function(data,textStatus, request){

				//Bueno pero borrra lo posterior
				console.log("success");
				//console.log(data);
				$('#tablaDocumentosCarga').html(data);
				
				//Bueno pero al usar tag Struts, jquery se cae :/
				//$(data).insertBefore($("#footerCarga"));
		    }
		});
}


function obtenerTablaTDR(){

	var action = 'obtenerTablaTDR.action'; 
	console.log("action : "+action);
	
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "html",cache: false,async:true,
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadeCargaCombo" class="overlay" style="display:block;height: 100%;position:fixed;z-index:3000;"></div>'+
					 '<div id="waitCargaCombo" class = "contEspera modalCarga" style="left:35%;position:fixed;width:350px;height:250px;z-index:3005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
				
			},complete: function () {
				$('#fadeCargaCombo').remove();
				$('#waitCargaCombo').remove();
				
				//Nose porque hay q ponerlo, pero funciona
				//document.getElementById('fade').style.display='none';
			},error: function(XMLHttpRequest, textStatus, errorThrown){
				alert('Error ' + textStatus);
				alert(errorThrown);
				alert(XMLHttpRequest.responseText);
		    },
			success: function(data,textStatus, request){
				//Bueno pero borrra lo posterior
				console.log("success");
				//console.log(data);
				$('#tablaDocumentosCarga').html(data);
				//Bueno pero al usar tag Struts, jquery se cae :/
				//$(data).insertBefore($("#footerCarga"));
		    }
		});
}


function obtenerTablaVacia(){

	var action = 'obtenerTablaVacia.action'; 
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
				//Bueno pero borrra lo posterior
				console.log("success");
				//console.log(data);
				$('#tablaDocumentosCarga').html(data);
				//Bueno pero al usar tag Struts, jquery se cae :/
				//$(data).insertBefore($("#footerCarga"));
		    }
		});
}


function informeCargaSelected(idInformeSelected){
	
	var informeName = $('#cbInforme option:selected').text();
	console.log("informeCargaSelected : "+ idInformeSelected);
	
	var action = 'informeCargaSelected.action?cbInforme='+idInformeSelected+'&informeName='+informeName; 
	
	action = encodeURI(action); 
	
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
				
				$('#administracionFiltros').html(data);
				//alert('idInformeSelected: '+idInformeSelected);
				
				// Aca se debe discriminar y llamar a obtener Tabla de Carga Segun Corresponda (PI o TDR) !!
				if (idInformeSelected == -1) {
					obtenerTablaVacia();
				}else if (idInformeSelected == 1){
					//obtenerArchivoPI(0); 
					obtenerTablaVacia();
				}else{	
					obtenerTablaTDR();
				}
				
		    }
		});
}


function obtenerReportes(){
	
    var idFile = $("#idFileUpload_rv").text();
    var idInformeLP = $("#idInforme_form").text();
//	    alert(idInformeLP);
    if (idInformeLP == 1)
	{
		//alert("Ley de Presupuesto");
		obtieneReporteValPI(idFile); 
	} else if (idInformeLP == 3){
		//alert("Informe Contable");
	    var idFile = $("#idFileUpload_rv").text();
		console.log("idFileUpload_rv "+idFile);
			obtieneReporteValIC(idFile); 
 
	}else if (idInformeLP == 2 || idInformeLP == 11 || idInformeLP == 12 )	{
		obtenerValidacionTDRNew(idFile);
	}
}
	
	
function obtieneReporteValIC(idFileIp){	
	
	var action = 'obtenerValidacionIC.action?idFileUp='+idFileIp;
	console.log(action);
	window.open(action,'_blank','scrollbars=1,resizable=1,height=650,width=1050');		
}
	

function obtenerValidacionTDRNew(idFileIp){	
	
	var idInformeLP = $("#idInforme_form").text();
	var parametros = 'idFileUp='+idFileUpload+'&idInformeLP='+idInformeLP;
	//var parametros='tpInforme='+tpInforme+'&reporte='+reporte+'&ejercicio='+ejercicio+'&periodo='+periodo+'&informe='+informe+'&partida='+partida+'&capitulo='+capitulo;		

	var action = 'obtenerValidacionTDRNew.action?' + parametros;
	console.log(action);
	window.open(action,'_blank','scrollbars=1,resizable=1,height=650,width=1050');		
}
	

function obtenerDescarga(){
//	alert($("#valorTmp").val());
	var parametros = 'idFileUp='+idFileUpload;
	dowFil = 'descargaInforme.action?' + parametros;	
	location.href=dowFil;
}
	

function obtenerDescargaPDF(){
	
	//alert("obtenerDescargaPDF");
	var parametros = 'idFileUp='+idFileUpload;
	dowFil = 'descargarPDF.action?' + parametros;
	location.href=dowFil;
	
}


function obtieneReporteValPISinParametros(){	
	
	//alert("obtieneReporteValPISinParametros");
	//var idFile = $(".linkVerRepValidacion").attr( 'idfile' );
    var idFile = $("#idFileUpload_rv").text();
    //alert("LEY PRESUPUESTO" +idFile);
	console.log("obtieneReporteValPI "+idFile);
	obtieneReporteValPI(idFile); 	
	
}


function obtieneReporteValPI(idFileIp){	
	
	//alert("Obtener Reporte validacion para PI: "+idFileIp);
	//var action = 'obtenerValidacionIC.action?idFileUp='+idFileIp;
	var action = 'obtenerValidacionPI.action?idFileUp='+idFileIp;
	//Nueva Ventana para el Reporte !!
	console.log(action);
	window.open(action,'_blank','scrollbars=1,resizable=1,height=650,width=1050');
	
}
 
	
function obtenerListaTDR(){

	//var analista = $('#cbAnalistaTDR option:selected').val();
	var analista = $('#cbAnalistaTDR option:selected').text();
	var informe = $('#cbInforme option:selected').val();
	//alert("titan"+informe);
	var estado = $('#cbEstadoSicogenTDR option:selected').val();
	var ejercicio = $('#cbEjercicioTDR option:selected').text();
	var periodo = $('#cbPeriodosTDR option:selected').val();
	var usuario =$('#cbAnalistaDAC option:selected').val();
	
	//alert("periodo: "+periodo);
	
	console.log("obtenerListaTDR . analista: "+analista);
	console.log("obtenerListaTDR . estado: "+estado);
	console.log("obtenerListaTDR . ejercicio: "+ejercicio);
	console.log("obtenerListaTDR . usuario: "+usuario);
	
	if (ejercicio == 'Seleccione'){
		jAlert("Debe Seleccionar un Ejercicio para realizar la búsqueda","Informacion");		
		return;
	}

	var periodo = $('#cbPeriodosTDR option:selected').val();
	console.log("cbPeriodosTDR : "+periodo);
 
	
	if (periodo == '-1'){
		jAlert("Debe Seleccionar un Periodo para realizar la búsqueda","Informacion");		
		return;
	}

	var action = 'obtenerListaTDR.action?informe='+informe+'&analista='+analista+'&ejercicio='+ejercicio+'&estadoSicogen='+estado+'&periodo='+periodo;
	
	    action=action+"&usuarioId="+usuario;
	
	    action = encodeURI(action);	
	    
	    console.log("action : "+action);
	
	    $.ajax({
	    	url: action,
		    type: "POST",
		    dataType: "html",
		    cache: false,
		    async:true,
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block;height: 100%;position:fixed;z-index:3000;"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:fixed;width:350px;height:250px;z-index:3005;padding:15px !important;display:block">'+ 
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
				$('#tablaDocumentosCarga').html(data);
			}
	});
		
}


function cargaValidacionTDR(idDoc){
 
	console.log("cargaValidacionTDR - idDoc: "+idDoc);
	
	var ejercicio = $('#cbEjercicioTDR option:selected').text();
	var informe = $('#cbInforme option:selected').text();
	var tipoInforme = $('#cbTipoInforme option:selected').text();
	
	// Agregar el campo Periodo a los parametros !!!
	
	var action = 'obtenerCargaTDR.action?idSistradoc='+idDoc+'&ejercicio='+ejercicio+'&informe='+informe+'&tipoInforme='+tipoInforme; 
	action = encodeURI(action);
	
	console.log("action : "+action);
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "html",
		    cache: false,
		    async:true,
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
				$('#tablaDocumentosCarga').html(data);
		    }
	});

};
	
	
function obtieneReporteValTDR(idFileUp,idSistradoc){	
	
	console.log("obtieneReporteValTDR ");
	var informe = $('#cbInforme option:selected').val();
	
	console.log("obtieneReporteValTDR - informe: "+informe);
	console.log("obtieneReporteValTDR - idFileUp: "+idFileUp);
	console.log("obtieneReporteValTDR - idSistradoc: "+idSistradoc);

	var parametros = 'idFileUp=' + idFileUp + '&idSistradoc=' + idSistradoc;
	var action = 'obtenerValidacionTDR?'+parametros;
	//Nueva Ventana para el Reporte !!
	console.log(action);
	window.open(action,'_blank','scrollbars=1,resizable=1,height=650,width=1050');
	
}

function obtieneReporteReValTDR(idFileUp, tipoInforme, idSistradoc){	
	
	console.log("obtieneReporteValTDR ");
	var informe = tipoInforme;
	
	console.log("obtieneReporteValTDR - informe: "+informe);
	console.log("obtieneReporteValTDR - idFileUp: "+idFileUp);
	console.log("obtieneReporteValTDR - idSistradoc: "+idSistradoc);
	
	var parametros = idFileUp+'_'+informe+'_'+idSistradoc;
	var action = 'obtenerValidacionTDR.action?parametros='+parametros;
	//Nueva Ventana para el Reporte !!
	console.log(action);
	window.open(action,'_blank','scrollbars=1,resizable=1,height=650,width=1050');
	
}

function cargarCapituloPartidaEjerRC(){
	var partida,ejercicio,action,capitulo;
	
	action		="getCapitulos.action";
	partida		=$("#cbPartidaRC").val();
	ejercicio	=$("#cbEjercicioTDR").val();
	
	console.log("ejecutandose getCapitulos.");
	
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		data:{idPartida:partida,idEjercicio:ejercicio},
		beforeSend: function (xhr) {
			$('body').append('<div id="fadeCapituloPartidas" class="overlay" style="display:block"></div>'+
				 '<div id="waitCapituloPartidas" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function (data) {
			$('#fadeCapituloPartidas').remove();
			$('#waitCapituloPartidas').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
	        alert('Error: ' + textStatus);
	        alert(XMLHttpRequest.responseText);
	    },
	    success: function(data){
	    		    	
    	    $("#cbCapitulo option").remove();
    	    $("#cbCapitulo").append($(new Option("Selec. Capítulo", "0")).data("codigo","0"));
    	    $.each(data.listaCapitulos,function(indice,elemento){
    	    	
    	    	capitulo=elemento.codCapitulo+ " "+elemento.nombre;
    	    	$("#cbCapitulo").append($(new Option(capitulo, elemento.idCapitulo)).data("codigo",elemento.codCapitulo));
    	    });	         
	    }
	});
}
function cargarInformesPeriodoRC(){
		var periodo,action;
		periodo=$("#cbPeriodosRC").val();
		action="getInformesByPeriodo.action?periodo="+periodo;
		$.ajax({
			url:action,
			type:'Post',
			dataType:'json',
			beforeSend: function (xhr) {
				$('body').append('<div id="fadeinformesCombos" class="overlay" style="display:block"></div>'+
					 '<div id="waitInformesCombos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadeinformesCombos').remove();
				$('#waitInformesCombos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando los informes");
		    	
	    	    $("#cbTipoInf option").remove();
	    	    $("#cbTipoInf").append(new Option("Todos", "-1"));
	    	    $.each(data.listaInformes,function(indice,elemento){
	    	    	
	    	    	$("#cbTipoInf").append(new Option(elemento.nombre, elemento.id));
	    	    });	         
		    }
		});
}
function cargarPartidasEjercicioRC(){
	var ejercicio,action,partida;
	ejercicio=$("#cbEjercicioTDR").val();
	action   ="getPartidasByEjercicio.action?ejercicio="+ejercicio;
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		 beforeSend: function (xhr) {
				$('body').append('<div id="fadePartidasTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPartidasTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePartidasTDR').remove();
				$('#waitPartidasTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    }, success: function(data){		    	
		    	
	    	    $("#cbPartidaRC option").remove();
	            $("#cbPartidaRC").append($(new Option("Selec. Partida", "0")).data("codigo","0")); 
	            
	            $.each(data.listaPartidas, function(indice, elemento) {	
	            	partida=elemento.codigo+" - "+ elemento.nombre;
	            	console.log(elemento);
	                //$("#cbPartidaRC").append($(new Option(partida, elemento.id)).data("codigo",elemento.codigo));
	            	$("#cbPartidaRC").append($(new Option(partida, elemento.codigo)).data("codigo",elemento.codigo));
	            });	            
	            
		    }
		    
	});
	
}
function cargaPeriodosEjercicioRC(){
	
	var ejercicio = $('#cbEjercicioTDR option:selected').val();
	var action = 'getPeriodos.action?ejercicioId=' + ejercicio; 
	$.ajax({
	 	url: action,
	    type: "POST",
	    dataType: "json",
	    beforeSend: function (xhr) {
			$('body').append('<div id="fadePeriodosTDR" class="overlay" style="display:block"></div>'+
				 '<div id="waitPeriodosTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function (data) {
			$('#fadePeriodosTDR').remove();
			$('#waitPeriodosTDR').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
	        alert('Error: ' + textStatus);
	        alert(XMLHttpRequest.responseText);
	    },
	    success: function(data){
	    	console.log("estamos buscando los periodos");
	    	console.log(data);
	    	switch(data.estado){
	    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
	    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
	    	}
	    	// $('#tblUpInformes > tbody').empty();  
    	    $("#cbPeriodosRC").get(0).options.length = 0;
            $("#cbPeriodosRC").get(0).options[0] = new Option("Todos", "-1"); 

            $.each(data.listaPeriodos, function(i, item) {			                	
                $("#cbPeriodosRC").get(0).options[$("#cbPeriodosRC").get(0).options.length] = new Option(item.periodoNombre, item.periodoId);
            });
            $("#cbPartidaRC").val("0");
            $("#cbCapitulo option").remove();
            $("#cbCapitulo").append($(new Option("Selec. Capítulo",0)).data("codigo",0));
            
	    }
	});
	
}


function cargaInformeRC(){
	
	var periodo = $('#cbPeriodosRC option:selected').val();
	var action = 'getTipoInformePorPeriodo.action?periodo=' + periodo; 
	$.ajax({
		 	url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodosTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodosTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodosTDR').remove();
				$('#waitPeriodosTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando los periodos");
		    	console.log(data);
		    	switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}
		    	// $('#tblUpInformes > tbody').empty();  
	    	    $("#cbTipoInf").get(0).options.length = 0;
                $("#cbTipoInf").get(0).options[0] = new Option("Todos", "-1"); 

                $.each(data.listaTipoInformes, function(i, item) {			                	
                    $("#cbTipoInf").get(0).options[$("#cbTipoInf").get(0).options.length] = new Option(item.nombre, item.id);
                });
		    }
		});
}


function cargaPeriodosEjercicio(){
	
	var ejercicio = $('#cbEjercicioTDR option:selected').val();
	console.log("cargaPeriodosEjercicio - cbEjercicioTDR "+ejercicio);
	
	var informe = $('#cbInforme option:selected').val();
	console.log("cargaPeriodosEjercicio - cbInforme "+informe);
	
	var action = 'getPeriodoByInforme.action?ejercicioId='+ejercicio+'&informeId='+informe; 
	
	$.ajax({
		 	url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodosTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodosTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodosTDR').remove();
				$('#waitPeriodosTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("cargaPeriodosEjercicio - success !!");
		    	console.log(data);
		    	switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}
		    	// $('#tblUpInformes > tbody').empty();  
	    	    $("#cbPeriodosTDR").get(0).options.length = 0;
                $("#cbPeriodosTDR").get(0).options[0] = new Option("Selec. Periodo", "-1"); 

                $.each(data.listaPeriodos, function(i, item) {			                	
                    $("#cbPeriodosTDR").get(0).options[$("#cbPeriodosTDR").get(0).options.length] = new Option(item.periodoNombre, item.periodoId);
                });
		    }
		});
}


function cargaPeriodosEjercicioCorrecciones(){
	
	var ejercicio = $('#cbEjercicioBit option:selected').val();
//	alert(ejercicio);
	var action = 'getPeriodos.action?ejercicioId=' + ejercicio; 
	$.ajax({
		 	url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodosTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodosTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodosTDR').remove();
				$('#waitPeriodosTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando los periodos");
		    	console.log(data);
		    	switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}
	    	    $("#cbPeriodosCorr").get(0).options.length = 0;
                $("#cbPeriodosCorr").get(0).options[0] = new Option("Todos", "-1"); 

                $.each(data.listaPeriodos, function(i, item) 
                {	                	
                    $("#cbPeriodosCorr").get(0).options[$("#cbPeriodosCorr").get(0).options.length] = new Option(item.periodoNombre, item.periodoId);
                });
                
                $("#cbPartidaRCorr").val("0");
                $("#cbCapituloRCorr option").remove();
                $("#cbCapituloRCorr").append($(new Option("Selec. Capítulo",0)).data("codigo",0));
		    }
	});
};


function cargarPartidasEjercicioCorrecciones(){
	var ejercicio,action,partida;
	ejercicio=$("#cbEjercicioBit").val();
	action   ="getPartidasByEjercicio.action?ejercicio="+ejercicio;
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		 beforeSend: function (xhr) {
				$('body').append('<div id="fadePartidasTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPartidasTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePartidasTDR').remove();
				$('#waitPartidasTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    }, success: function(data){		    	
		    	
	    	    $("#cbPartidaRCorr option").remove();
	            $("#cbPartidaRCorr").append($(new Option("Selec. Partida", "0")).data("codigo","0")); 
	            
	            $.each(data.listaPartidas, function(indice, elemento) {	
	            	partida=elemento.codigo+" - "+ elemento.nombre;
	            	console.log(elemento);
	                $("#cbPartidaRCorr").append($(new Option(partida, elemento.id)).data("codigo",elemento.codigo));
	            });	            
	            
		    }
		    
	});
	
};
function cargarCapituloPartidaEjerCorrecciones(){
	var partida,ejercicio,action,capitulo;
	
	action		="getCapitulos.action";
	partida		=$("#cbPartidaRCorr").val();
	ejercicio	=$("#cbEjercicioBit").val();
	
	console.log("ejecutandose cargarCapituloPartidaEjerRC.");
	
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		data:{idPartida:partida,idEjercicio:ejercicio},
		beforeSend: function (xhr) {
			$('body').append('<div id="fadeCapituloPartidas" class="overlay" style="display:block"></div>'+
				 '<div id="waitCapituloPartidas" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function (data) {
			$('#fadeCapituloPartidas').remove();
			$('#waitCapituloPartidas').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
	        alert('Error: ' + textStatus);
	        alert(XMLHttpRequest.responseText);
	    },
	    success: function(data){
	    		    	
    	    $("#cbCapituloRCorr option").remove();
    	    $("#cbCapituloRCorr").append($(new Option("Selec. Capítulo", "0")).data("codigo","0"));
    	    $.each(data.listaCapitulos,function(indice,elemento){
    	    	
    	    	capitulo=elemento.codCapitulo+ " "+elemento.nombre;
    	    	$("#cbCapituloRCorr").append($(new Option(capitulo, elemento.idCapitulo)).data("codigo",elemento.codCapitulo));
    	    });	         
	    }
	});
};


function cargaPeriodosEjercicioBitAcciones(){
	
	var ejercicio = $('#cbEjercicioBitacora option:selected').val();
//	alert(ejercicio);
	var action = 'getPeriodos.action?ejercicioId=' + ejercicio; 
	$.ajax({
		 	url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodosTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodosTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodosTDR').remove();
				$('#waitPeriodosTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando los periodos");
		    	console.log(data);
		    	switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}
	    	    $("#cbPeriodosBita").get(0).options.length = 0;
                $("#cbPeriodosBita").get(0).options[0] = new Option("Todos", "-1"); 

                $.each(data.listaPeriodos, function(i, item) 
                {	                	
                    $("#cbPeriodosBita").get(0).options[$("#cbPeriodosBita").get(0).options.length] = new Option(item.periodoNombre, item.periodoId);
                });
                
                $("#cbPartidaBit").val("0");
                $("#cbCapituloBit option").remove();
                $("#cbCapituloBit").append($(new Option("Selec. Capítulo",0)).data("codigo",0));
		    }
		});
}


function cargarPartidasEjercicioBitAcciones(){
	var ejercicio,action,partida;
	ejercicio=$("#cbEjercicioBitacora").val();
	action   ="getPartidasByEjercicio.action?ejercicio="+ejercicio;
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		 beforeSend: function (xhr) {
				$('body').append('<div id="fadePartidaBitacora" class="overlay" style="display:block"></div>'+
					 '<div id="waitPartidasBitacora" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePartidaBitacora').remove();
				$('#waitPartidasBitacora').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    }, success: function(data){		    	
		    	
	    	    $("#cbPartidaBit option").remove();
	            $("#cbPartidaBit").append($(new Option("Selec. Partida", "0")).data("codigo","0")); 
	            
	            $.each(data.listaPartidas, function(indice, elemento) {	
	            	partida=elemento.codigo+" - "+ elemento.nombre;
	            	console.log(elemento);
	                //$("#cbPartidaBit").append($(new Option(partida, elemento.id)).data("codigo",elemento.codigo));
	            	$("#cbPartidaBit").append($(new Option(partida, elemento.codigo)).data("codigo",elemento.codigo));
	            });	            
	            
		    }
		    
	});
	
};
function cargarCapituloPartidaEjerBitAcciones(){
	var partida,ejercicio,action,capitulo;
	
	action		="getCapitulos.action";
	partida		=$("#cbPartidaBit").val();
	ejercicio	=$("#cbEjercicioBitacora").val();
	
	console.log("ejecutandose cargarCapituloPartidaEjerBitAcciones.");
	
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		data:{idPartida:partida,idEjercicio:ejercicio},
		beforeSend: function (xhr) {
			$('body').append('<div id="fadeCapituloPartidas" class="overlay" style="display:block"></div>'+
				 '<div id="waitCapituloPartidas" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function (data) {
			$('#fadeCapituloPartidas').remove();
			$('#waitCapituloPartidas').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
	        alert('Error: ' + textStatus);
	        alert(XMLHttpRequest.responseText);
	    },
	    success: function(data){
	    		    	
    	    $("#cbCapituloBit option").remove();
    	    $("#cbCapituloBit").append($(new Option("Selec. Capítulo", "0")).data("codigo","0"));
    	    $.each(data.listaCapitulos,function(indice,elemento){
    	    	
    	    	capitulo=elemento.codCapitulo+ " "+elemento.nombre;
    	    	//$("#cbCapituloBit").append($(new Option(capitulo, elemento.idCapitulo)).data("codigo",elemento.codCapitulo));
    	    	$("#cbCapituloBit").append($(new Option(capitulo, elemento.codCapitulo)).data("codigo",elemento.codCapitulo));
    	    });	         
	    }
	});
};

function cargaPeriodosEjercicioRev(){	
	
	var ejercicio = $('#cbEjercicioRev option:selected').val();
	var action = 'getPeriodos.action?ejercicioId=' + ejercicio; 
	$.ajax({
		 	url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodosTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodosTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodosTDR').remove();
				$('#waitPeriodosTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando los periodos");
		    	console.log(data);
		    	switch(data.estado){
		    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
		    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}
	    	    $("#cbPeriodosRev").get(0).options.length = 0;
                $("#cbPeriodosRev").get(0).options[0] = new Option("Todos", "-1"); 

                $.each(data.listaPeriodos, function(i, item) 
                {	                	
                    $("#cbPeriodosRev").get(0).options[$("#cbPeriodosRev").get(0).options.length] = new Option(item.periodoNombre, item.periodoId);
                });
                
                $("#cbPartidaInf").val("0");
                $("#cbCapituloInf option").remove();
                $("#cbCapituloInf").append($(new Option("Selec. Capítulo",0)).data("codigo",0));
		    }
	});
}

function cargarPartidasEjercicioRev(){
	var ejercicio,action,partida;
	ejercicio=$("#cbEjercicioRev").val();
	action   ="getPartidasByEjercicio.action?ejercicio="+ejercicio;
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		 beforeSend: function (xhr) {
				$('body').append('<div id="fadePartidasTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPartidasTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePartidasTDR').remove();
				$('#waitPartidasTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    }, success: function(data){		    	
		    	
	    	    $("#cbPartidaInf option").remove();
	            $("#cbPartidaInf").append($(new Option("Selec. Partida", "0")).data("codigo","0")); 
	            
	            $.each(data.listaPartidas, function(indice, elemento) {	
	            	partida=elemento.codigo+" - "+ elemento.nombre;
	            	console.log(elemento);
	                $("#cbPartidaInf").append($(new Option(partida, elemento.id)).data("codigo",elemento.codigo));
	            });	            
	            
		    }
		    
	});
	
};
function cargarCapituloPartidaEjerRev(){
	var partida,ejercicio,action,capitulo;
	
	action		="getCapitulos.action";
	partida		=$("#cbPartidaInf").val();
	ejercicio	=$("#cbEjercicioRev").val();
	
	console.log("ejecutandose cargarCapituloPartidaEjerRC.");
	
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		data:{idPartida:partida,idEjercicio:ejercicio},
		beforeSend: function (xhr) {
			$('body').append('<div id="fadeCapituloPartidas" class="overlay" style="display:block"></div>'+
				 '<div id="waitCapituloPartidas" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function (data) {
			$('#fadeCapituloPartidas').remove();
			$('#waitCapituloPartidas').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
	        alert('Error: ' + textStatus);
	        alert(XMLHttpRequest.responseText);
	    },
	    success: function(data){
	    		    	
    	    $("#cbCapituloInf option").remove();
    	    $("#cbCapituloInf").append($(new Option("Selec. Capítulo", "0")).data("codigo","0"));
    	    $.each(data.listaCapitulos,function(indice,elemento){
    	    	
    	    	capitulo=elemento.codCapitulo+ " "+elemento.nombre;
    	    	$("#cbCapituloInf").append($(new Option(capitulo, elemento.idCapitulo)).data("codigo",elemento.codCapitulo));
    	    });	         
	    }
	});
};
function cargarPartidasEjercicioReporteEnvio(){
	var ejercicio,action,partida;
	ejercicio=$("#cbEjercicioReporteEnvio").val();
	action   ="getPartidasByEjercicio.action?ejercicio="+ejercicio;
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		 beforeSend: function (xhr) {
				$('body').append('<div id="fadePartidasTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPartidasTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePartidasTDR').remove();
				$('#waitPartidasTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    }, success: function(data){		    	
		    	
	    	    $("#cbPartida2 option").remove();
	            $("#cbPartida2").append($(new Option("Selec. Partida", "0")).data("codigo","0")); 
	            
	            $.each(data.listaPartidas, function(indice, elemento) {	
	            	partida=elemento.codigo+" - "+ elemento.nombre;
	            	console.log(elemento);
	                //$("#cbPartida2").append($(new Option(partida, elemento.id)).data("codigo",elemento.codigo));
	            	$("#cbPartida2").append($(new Option(partida, elemento.codigo)).data("codigo",elemento.codigo));
	            });	            
	            
		    }
		    
	});
	
}

function cargarCapituloPartidaEjerReporteEnvio(){
	var partida,ejercicio,action,capitulo;
	
	action		="getCapitulos.action";
	partida		=$("#cbPartida2").val();
	ejercicio	=$("#cbEjercicioReporteEnvio").val();
	
	console.log("ejecutandose cargarCapituloPartidaEjerRC.");
	
	$.ajax({
		url:action,
		type:'Post',
		dataType:'json',
		data:{idPartida:partida,idEjercicio:ejercicio},
		beforeSend: function (xhr) {
			$('body').append('<div id="fadeCapituloPartidas" class="overlay" style="display:block"></div>'+
				 '<div id="waitCapituloPartidas" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function (data) {
			$('#fadeCapituloPartidas').remove();
			$('#waitCapituloPartidas').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
	        alert('Error: ' + textStatus);
	        alert(XMLHttpRequest.responseText);
	    },
	    success: function(data){
	    		    	
    	    $("#cbCapituloRepEnvio option").remove();
    	    $("#cbCapituloRepEnvio").append($(new Option("Selec. Capítulo", "0")).data("codigo","0"));
    	    $.each(data.listaCapitulos,function(indice,elemento){
    	    	
    	    	capitulo=elemento.codCapitulo+ " "+elemento.nombre;
    	    	$("#cbCapituloRepEnvio").append($(new Option(capitulo, elemento.idCapitulo)).data("codigo",elemento.codCapitulo));
    	    });	         
	    }
	});
}


function cargaPerEjerReporteEnvio(){	
	
	var ejercicio = $('#cbEjercicioReporteEnvio option:selected').val();
	var action = 'getPeriodos.action?ejercicioId=' + ejercicio; 
	$.ajax({
		 	url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodosTDR" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodosTDR" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodosTDR').remove();
				$('#waitPeriodosTDR').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log("estamos buscando los periodos");
		    	console.log(data);
		    	switch(data.estado)
		    	{
			    	case -2:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='showFormCarga.action?abreCarga=0');}} );break;
			    	case -1:jAlert(data.mensaje, "Carga Manual", function(r){if(r){$(location).attr('href',url='login');}} );break;
		    	}		    	 
	    	    $("#cbPeriodosRepEnvio").get(0).options.length = 0;
                $("#cbPeriodosRepEnvio").get(0).options[0] = new Option("Todos", "-1"); 

                $.each(data.listaPeriodos, function(i, item) {			                	
                    $("#cbPeriodosRepEnvio").get(0).options[$("#cbPeriodosRepEnvio").get(0).options.length] = new Option(item.periodoNombre, item.periodoId);
                });
                
                $("#cbPartida2").val("0");
                $("#cbCapituloRepEnvio option").remove();
                $("#cbCapituloRepEnvio").append($(new Option("Selec. Capítulo",0)).data("codigo",0));
		    }
	});
}


/************  PI  ***************/
function loadCapituloDigitacionTDR(idPartida){
	
	console.log("loadCapituloDigitacionTDR ");
	
	var variable = idPartida.substr(0,2);	
	idPartida = variable;
	var action = 'getCapitulosDigitacionTDR.action?idPartida=' + idPartida; 
	
	$.ajax({
		 url: action,
		    type: "POST",
		    data: {idPartida:idPartida},
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block; height:300%"></div>'+
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
		    	console.log("estamos buscando las capitulos para digitacion");
		    	console.log(data);
		    
	    	    $("#cbCapitulo").get(0).options.length = 0;
                $("#cbCapitulo").get(0).options[0] = new Option("Selec. Capítulo", "-1"); 
               	               
                $.each(data.listaCapitulos, function (i, item) {
                    $('#cbCapitulo').append($('<option>', { 
                        value: item.codCapitulo,
                        text : item.codCapitulo +' '+item.nombre ,
                        id   : item.idCapitulo 
                    }));
                }); 
                
                $('#cbProgramas option:eq(0)').prop('selected',true);
                
                // Limpiar ComboBox de Programa !!
                console.log("Limpiando ComboBox de Programa...");
                $("#cbProgramas").get(0).options.length = 0;
                $("#cbProgramas").get(0).options[0] = new Option("Selec. Programa", "-1"); 
                
		    }
		});
}


function loadProgramasDigitacionTDR(idPartida, idCapitulo){

	console.log("loadProgramasDigitacionTDR ");
	
	console.log("idPartida->"+idPartida);
	console.log("idCapitulo->"+idCapitulo);
	
	if ($.isNumeric(idPartida)==false ){idPartida=0;}
	
	var action = 'getProgramasDigitacionTDR.action?idPartida=' + idPartida+'&idCapitulo=' + idCapitulo; 
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block; height:300%"></div>'+
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
		    
	    	    $("#cbProgramas").get(0).options.length = 0;
                $("#cbProgramas").get(0).options[0] = new Option("Selec. Programa", "-1"); 

                $.each(data.listaProgramas, function(i, item) {			                	
                    $("#cbProgramas").get(0).options[$("#cbProgramas").get(0).options.length] = new Option(item.codPrograma +' - '+item.nombre, item.idPrograma);
                });			                
		    }
		});
}


function agregarPIDigitacionTDR(idPartida, idCapitulo, idPrograma){
	
	console.log("agregarPIDigitacionTDR ");
	
	// Agregar Validaciones para ComboBox sin Seleccionar !!!
	console.log("idPartida->"+idPartida);
	console.log("idCapitulo->"+idCapitulo);
	console.log("idPrograma->"+idPrograma);
	
	//if(idPartida == 'Seleccione'){
	if(idPartida == '-1'){
		jAlert('Debe Seleccionar una Partida v&aacute;lida','Alerta');
		return;
	}
	
	if(idCapitulo == ''){
		jAlert('Debe Seleccionar un Cap&iacute;tulo v&aacute;lido','Alerta');
		return;
	}
	
	if(idPrograma == '-1'){
		jAlert('Debe Seleccionar un Programa v&aacute;lido','Alerta');
		return;
	}
	
	
	//var variable = idPartida.substr(0,2);	
	//idPartida = variable;	
	var ejercicio = $('#txtEjercicio').val();
	var numDoc = $('#txtNumeroDocumento').val();	
	var idPeriodo = $("#cbPeriodosTDR option:selected").val();
	var idInforme = $('#cbInforme option:selected').val();
	var tipoDocumento = $('#txtTipoDocumento').val();
	
	var action = 'insertPIDigitacionTDR.action?idPartida='+idPartida+'&idCapitulo='+idCapitulo+'&idPrograma='+idPrograma+'&ejercicio='+ejercicio+'&numDoc='+numDoc+'&idPeriodo='+idPeriodo+'&idInforme='+idInforme+'&tipoDoc='+tipoDocumento; 
	
	console.log("ejercicio->"+ejercicio);	
	console.log("numDoc->"+numDoc);
	console.log("idPeriodo->"+idPeriodo);
	console.log("idInforme->"+idInforme);
	console.log("tipoDocumento->"+tipoDocumento);
	console.log("action->"+action);
		
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json", cache: false, async:true,
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block; height:300%"></div>'+
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
		    	console.log("data.codigoInsert->"+data.codigoInsert);
		    	console.log("data.mensajeInsert->"+data.mensajeInsert);
		    			    			    	
		    	if(data.codigoInsert == -2){
		    		jAlert(data.mensajeInsert, "Alerta");
		    	}
		    	if(data.codigoInsert >= -1){                
		    		var estado = data.codigoInsert;
		    		buscarPIDigitacionTDR(ejercicio, idPartida, idCapitulo, idPrograma, estado, numDoc, idPeriodo, idInforme, tipoDocumento);		    	              
		    	}
		    }
		});	
}


function buscarPIDigitacionTDR(ejercicio, idPartida, idCapitulo, idPrograma, estado, numDoc, idPeriodo, idInforme, tipoDocumento){
		
	console.log("buscarPIDigitacionTDR ");
	
	var action = 'buscarPIDigitacionTDR.action?idPartida=' + idPartida+'&idCapitulo=' + idCapitulo+'&idPrograma=' + idPrograma+'&ejercicio='+ejercicio+'&estado='+estado+'&numDoc='+numDoc+'&idPeriodo='+idPeriodo+'&idInforme='+idInforme+'&tipoDocumento='+tipoDocumento; 
	
	console.log("numDoc->"+numDoc);
	console.log("idPeriodo->"+idPeriodo);
	console.log("idInforme->"+idInforme);
	console.log("tipoDocumento->"+tipoDocumento);
	console.log("action->"+action);
	
    $.ajax({
		 url: action,
		    type: "POST",
		    dataType: "html",
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
		    	console.log("success insertado los registros PI");	
               $("#tblDigitacionPresupuestoInicial").html(data);
		    }
		});	  		
}
		 

function eliminarFilaDigitacionTDRMP(idFila){
	
	console.log("eliminarFilaDigitacionTDR # MP #");
	console.log("idFIla-> : "+idFila);
	
	var FechaRecep = $('#txtFechaRecepcionCGR').val();
 	jConfirm('&iquest;Est&aacute; seguro que desea eliminar el registro?', 'Alerta', function(r) 
 	{
		if(r){
			var idDoc = $('#txtIdDoc').val(); 
			var nroDoc = $('#txtNumeroDocumento').val();
			var tipDoc = $('#txtTipoDocumento').val();
			var analista =  $('#txtAnalista').val();
			var estado = $('#txtEstadoSicogen').val();
			var entEmisora = $('#txtEntidadEmisora').val();
			var fechaDcoumento = $('#txtFechaDocumento').val();
			var idTblSistradoc = $('#txtIdDoc').val(); 
			var idEjercicio = $('#txtEjercicio').val();
			var idEjercicio = $("#cbEjercicioTDR option:selected").val();
 
// 			var action = 'deleteMPDigitacionTDR.action?idFila=' + idFila +'&idEjercicio='+idEjercicio+'&idPartida='+idPartida+'&idCapitulo='+idCapitulo+'&idPrograma='+idPrograma+'&numDoc='+numDoc+'&idPeriodo='+idPeriodo+'&tipoDocumento='+tipoDocumento; 
			var action = 'deleteMPDigitacionTDR.action?idFila=' + idFila +'&idEjercicio='+idEjercicio; 

// 			console.log("action : "+action);	
 			$.ajax({
				 url: action,
				    type: "POST",
				    dataType: "html",
				    cache: false,
				    async:true,
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
//						alert('Error ' + textStatus);
//						alert(errorThrown);
//						alert(XMLHttpRequest.responseText);
				    }, success: function(data,textStatus, request)
					{
						console.log(data);
//				    	if (data.listaReporteCumplimiento.length == 0 )
//				    	{
//				    		$("#tblDigitacionPresupuestoInicial").append(
//				    				'<p id="tdc" class="rwdetInfPar" align="center"> No hay informacion para los filtros seleccionados <p>');
//				    	}else{
//							console.log(data);
//							$('#tblDigitacionPresupuestoInicial').html(data);
//							$('#tblDigitacionMP').html(data);
//				    	}

 
				    }
			});
 			cargaDigitacionTDR( idDoc, nroDoc, tipDoc, analista, estado, entEmisora, fechaDcoumento, idTblSistradoc, FechaRecep); //ACA
		}
 	});
}
	
 
function eliminarFilaDigitacionTDR(idFila){
	
	console.log("eliminarFilaDigitacionTDR ");
	console.log("idFIla-> : "+idFila);
	
	jConfirm('&iquest;Est&aacute; seguro que desea eliminar el registro?', 'Alerta', function(r) {
		if(r){
			
			var idEjercicio = $('#txtEjercicio').val();
			var idPartida = $('#cbPartida').val();
			var idCapitulo = $('#cbCapitulo').val();
			var idPrograma = $('#cbProgramas').val();	
			var numDoc = $('#txtNumeroDocumento').val();	
			var idPeriodo = $("#cbPeriodosTDR option:selected").val();
			var tipoDocumento = $('#txtTipoDocumento').val();
			
			console.log("idEjercicio-> : "+idEjercicio);
			console.log("idPartida-> : "+idPartida);
			console.log("idCapitulo-> : "+idCapitulo);
			console.log("idPrograma-> : "+idPrograma);
			console.log("numDoc-> : "+numDoc);
			console.log("idPeriodo-> : "+idPeriodo);
			console.log("tipoDocumento-> : "+tipoDocumento);
				
			var action = 'deletePIDigitacionTDR.action?idFila=' + idFila +'&idEjercicio='+idEjercicio+'&idPartida='+idPartida+'&idCapitulo='+idCapitulo+'&idPrograma='+idPrograma+'&numDoc='+numDoc+'&idPeriodo='+idPeriodo+'&tipoDocumento='+tipoDocumento; 
			console.log("action : "+action);		
				
			$.ajax({
				 url: action,
				    type: "POST",
				    dataType: "html",
				    cache: false,
				    async:true,
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
						$('#tblDigitacionPresupuestoInicial').html(data);
				    }
			});
		}
	});
	
}


var dialog;
var popUp;


function verEditarPopUp(idFila, totalIngresosCLP, totalGastosCLP, totalIngresosUSD, totalGastosUSD, capitulo){
	
	console.log("verEditarPopUp");
	
	console.log('verEditarPopUp : id_fila->'+idFila);
	console.log('verEditarPopUp : totalIngresosCLP->'+totalIngresosCLP);
	console.log('verEditarPopUp : totalGastosCLP->'+totalGastosCLP);
	console.log('verEditarPopUp : totalIngresosUSD->'+totalIngresosUSD);
	console.log('verEditarPopUp : totalGastosUSD->'+totalGastosUSD);
	console.log('verEditarPopUp : capitulo->'+capitulo);

	$('input#ValorTipoMoneda').val('CLP');
	$('#ValorIdPresupuesto').val(idFila);
	$('#ValorIdCapitulo').val(capitulo);
	$('#txtCodigoCuenta').val('');
	$('#txtMonto').val('');
	$('#txtDescripcionCuenta').val('');

	$('#txtTotalIngresosCLP').text(totalIngresosCLP);
	$('#txtTotalIngresosUSD').text(totalIngresosUSD);
	$('#txtTotalGastosCLP').text(totalGastosCLP);
	$('#txtTotalGastosUSD').text(totalGastosUSD);

	$('#panelTotalesUSD').hide();
	$("#panelTotalesCLP").show();

	/**
	var editar='';
	editar=$('#popUpClone');
	console.log('popUpEditar : '+editar);
	
	var dialog = $('<div id="popUpEditar"></div>')
    	.dialog({height: 320,width: 620, modal: true,
    		title: 'Digitación',
    		close: function(event, ui){	$(this).hide();}});

	dialog.dialog('open');
	dialog.html(editar);
	*/
	
	 var opt = {
				autoOpen: true,
				modal: true,
				width: 620,
				height: 320,
				show: "blind",
				title: "Digitación TDR PI"
			   };
					
	popUp = $("#popUpClone").dialog(opt);
	popUp.dialog("open");
		
	
	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');	
	
	buscarDetalle();
	
}


function closeDialog(){
	
	console.log("closeDialog ");
	
	if (typeof dialog != "undefined"){
		dialog.dialog('close');		
	}
	
}


function showUSD() {
	
	console.log("showUSD ");
	
	$('#ValorTipoMoneda').val('USD');
	$('div#panelTotalesCLP').hide();
	$('div#panelTotalesUSD').show();
	 
}


function showCLP() {

	console.log("showCLP ");
	
	$('#ValorTipoMoneda').val('CLP');
	$('div#panelTotalesCLP').show();
	$('div#panelTotalesUSD').hide();
	
}


function asignarValorMonto(valor){	
	
	console.log("asignarValorMonto ");
	$('#txtMonto').val(valor);	
	
}


var codigoTemp = '';


function validarFormato(valor){
	
	console.log(valor);
	var regExp = /^\d{2}\.\d{2}\.\d{3}$/;
	
	if(!regExp.test(valor.trim())){
		
		console.log('NO corresponde al formato de Cuenta ..test !!');
		//$('input#txtDescripcionCuenta').val("");
		//$('input#txtCodigoCuenta').val("");
		
		jAlert("El Codigo de la Cuenta  debe ser numerico y tener el formato nn.nn.nnn ", "Informacion");
		return;
		 
	}else{
		var val1 = valor.substring(0, 2);
		var val2 = valor.substring(3,5);
		var val3 = valor.substring(10, 6);
		valor = val1 +"."+val2+"."+val3;
		buscarDescripcionCuentaMP(valor);
	}
	
}



function buscarDescripcionCuentaMP(valor){
 
	var idEjercicio = $('#cbEjercicioTDR option:selected').val();
	console.log("idEjercicio-> : "+idEjercicio);

	var action = 'buscarCuentaPIDigitacionTDRDetalle.action?idCuenta=' + valor +'&idEjercicio='+idEjercicio; 
	console.log("action : "+action);		
	
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json", cache: false, async:true,
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
				alert(data);
		    },
			success: function(data){
				console.log("success buscando descripcion cuenta");	
				console.log("data->"+data.descripcionCuenta);
				if(data!=null ){
					
					console.log("buscando.....");
					console.log(data.estadoConsulta);
					
					if(data.estadoConsulta){
						$('input#txtDescripcionCuenta').val(data.descripcionCuenta);
						$('input#txtCodigoCuenta').val(valor);	
						
					}else{
						
						jAlert("La Cuenta ingresada: "+$('input#txtCodigoCuenta').val()+" No existe en el Catálogo !!", "Informacion",function(){
							$('input#txtCodigoCuenta').focus();
						});
						
					}
				}
																
		    }
	});	
}


function buscarDescripcionCuenta(valor){

	console.log("buscarDescripcionCuenta ");
	console.log("valor-> : "+valor);	
	
	var dateReg = /^\d{2}\.\d{2}\.\d{3}$/;
	
	if(!dateReg.test(valor.trim())){
		console.log('NO corresponde al formato de Cuenta ..test !!');
		jAlert("El Codigo de la Cuenta 1 debe tener el formato nn.nn.nnn ", "Informacion");
		return;
	}
	
	/**
	var val1 = valor.substring(0,2);
	var val2 = valor.substring(3,5);
	var val3 = valor.substring(10,6);

	console.log("buscarDescripcionCuenta - valores: "+val1+val2+val3);

	valor = val1 +"."+val2+"."+val3;
	console.log("buscarDescripcionCuenta - valor: "+valor);
	*/
	
	
	var idEjercicio = $('#cbEjercicioTDR option:selected').val();
	console.log("idEjercicio-> : "+idEjercicio);

	var action = 'buscarCuentaPIDigitacionTDRDetalle.action?idCuenta=' + valor.trim() +'&idEjercicio='+idEjercicio; 
	console.log("action : "+action);	
	
	
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "json", cache: false, async:true,
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
				alert(data);
		    },
			success: function(data){
				console.log("success buscando descripcion cuenta");	
				console.log("data->"+data.descripcionCuenta);
				
				if(data.descripcionCuenta == null){
					jAlert("La Cuenta ingresada: "+valor.trim()+" No existe en el Catálogo !!","Informacion");
					return false;
				}
				
				$('input#txtDescripcionCuenta').val(data.descripcionCuenta);
				$('input#txtCodigoCuenta').val(valor);													
		    }
	});	
}


function agregarDetalle(){
		
	console.log("agregarDetalle ");
	
	var codigoCuenta = document.getElementById("txtCodigoCuenta").value;
	var monto = document.getElementById("txtMonto").value;
	var descripcionCuenta = document.getElementById("txtDescripcionCuenta").value;
	var tipoMoneda = $('#ValorTipoMoneda').val();
	var idEjercicio = $('#cbEjercicioTDR option:selected').val();
	var idPresupuesto = $('#ValorIdPresupuesto').val();
	var capitulo = $('#ValorIdCapitulo').val();
//	var idPartida = $('#cbPartida').val();
//	var idCapitulo = $('#cbCapitulo').val();
			
	console.log('codigoCuenta->'+codigoCuenta);		
	console.log('monto->'+monto);		
	console.log('descripcionCuenta->'+descripcionCuenta);		
	console.log('tipoMoneda->'+tipoMoneda);		
	console.log("idEjercicio-> : "+idEjercicio);
	console.log("idPresupuesto-> : "+idPresupuesto);
//	console.log("idPartida-> : "+idPartida);
//	console.log("idCapitulo-> : "+idCapitulo);
	console.log("capitulo-> : "+capitulo);
	
	var codCuenta =  $('input#txtCodigoCuenta').val();	
	var montoCuenta = $('input#txtMonto').val();	
	
	if(codCuenta.trim() != ""){
		if(montoCuenta.trim() != ""){
			if ($.isNumeric(montoCuenta)==false ){
				jAlert("El monto debe ser Numérico", "Alerta");
			}else{
				if (montoCuenta <= 0){
					jAlert("El monto debe ser Mayor a Cero", "Alerta");
				}else{
					
					var action = 'insertDetallePIDigitacionTDR.action?codigoCuenta=' + codigoCuenta+'&monto=' + monto +'&tipoMoneda=' + tipoMoneda +'&idEjercicio='+idEjercicio +'&idPresupuesto='+idPresupuesto
					//+'&capitulo='+capitulo+'&idPartida='+idPartida; 
			
					console.log("action->"+action);
						
					$.ajax({
						url: action,
					    type: "POST",
					    data:{codigoCuenta:codigoCuenta,monto:monto,tipoMoneda:tipoMoneda,idEjercicio:idEjercicio,idPresupuesto:idPresupuesto,capitulo:capitulo},
					    dataType: "html",
					    beforeSend: function (xhr) {
							$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
								 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
								 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
								 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
								 '</div>');
						},complete: function (data) {
					    	dataPI = data;
					    	$('div#grilladetalle').html(dataPI.responseText);
							
							$('#fadePeriodos').remove();
							$('#waitPeriodos').remove();
						},error: function(XMLHttpRequest, textStatus, errorThrown){
					        alert('Error: ' + textStatus);
					        alert(XMLHttpRequest.responseText);
					    },
					    success: function(data, textStatus, request){
					    	console.log("success insertado los registros de detalle PI");	
					    	console.log(data.responseText);
					    	$('div#grilladetalle').html(data.responseText);
					    	$('input#txtCodigoCuenta').val('');
							$('input#txtMonto').val('');
							$('input#txtDescripcionCuenta').val('');	
							sumaTotales();
					    }
					});	
				}
				
			}
		}else{
			jAlert('Falta agregar el monto', "Alerta");
		}	
	}else{
		jAlert('No existe el codigo de cuenta', "Alerta");
	}	
	
	//sumaTotales();
}


function sumaTotales(){
	
	// Sumar el total de Ingresos y Gastos para despliegue !!
	console.log("Sumar el total de Ingresos y Gastos para despliegue !!");
	
	var tipoMoneda = $('#ValorTipoMoneda').val();
	var idPresupuesto = $('#ValorIdPresupuesto').val();
	
	var action = 'obtieneTotalesPIDigitacionTDR.action?tipoMoneda='+tipoMoneda+'&idPresupuesto='+idPresupuesto; 
	
	console.log("action->"+action);
	
	$.ajax({
		url: action,
	    type: "POST",
	    data:{tipoMoneda:tipoMoneda,idPresupuesto:idPresupuesto},
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
	    },success: function(data, textStatus, request){
	    	console.log("success Actualizando Totales PopUp !!");	
	    	//console.log(data);
	    	console.log("Total Ingreso CLP: "+data.digitacionPIBO.totalIngresosCLP);
	    	$('#txtTotalIngresosCLP').text(data.digitacionPIBO.totalIngresosCLP);
	    	
	    	console.log("Total Gasto CLP: "+data.digitacionPIBO.totalGastosCLP);
	    	$('#txtTotalGastosCLP').text(data.digitacionPIBO.totalGastosCLP);
	    	
	    	console.log("Total Ingreso USD: "+data.digitacionPIBO.totalIngresosUSD);
	    	$('#txtTotalIngresosUSD').text(data.digitacionPIBO.totalIngresosUSD);
	    	
	    	console.log("Total Gasto USD: "+data.digitacionPIBO.totalGastosUSD);
	    	$('#txtTotalGastosUSD').text(data.digitacionPIBO.totalGastosUSD);
	    	
	    }
	});	
	
}


function buscarDetalle(){
	
	console.log("buscarDetalle ");
	
	var ejercicio = $('#txtEjercicio').val();
	var idPresupuesto = $('#ValorIdPresupuesto').val();		
	
	var action = 'buscarDetallePIDigitacionTDR.action?&ejercicio='+ejercicio +'&idPresupuesto='+idPresupuesto; 

	console.log("action->"+action);	
		
	$.ajax({
		url: action,
	    type: "POST",
	    data:{ejercicio:ejercicio,idPresupuesto:idPresupuesto},
	    dataType: "html",
	    beforeSend: function (xhr) {
			$('body').append('<div id="fadePeriodos" class="overlay" style="display:block;height:300%"></div>'+
				 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
				 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
				 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
				 '</div>');
		},complete: function (data){
	    	dataPI = data;
	    	$('div#grilladetalle').html(dataPI.responseText);
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
	        alert('Error: ' + textStatus);
	        alert(XMLHttpRequest.responseText);
	    },success: function(data, textStatus, request){
	    	console.log("success buscar los registros de detalle PI");
	    	console.log(data);
	    	$('div#grilladetalle').html(data.responseText);	
	    	
	    	sumaTotales();
	    }
	    
	});		
	
}


function eliminarFilaDetalleDigitacionTDRMP(idDigitacionDetalleMP){
	
	var idDigitacionDetalleMP = idDigitacionDetalleMP;
	var idFila = $('#valorOculto').val();
	
// 	alert("Antes: " + idDigitacionDetalleMP);
	var action = 'deleteDetalleDigitacionTDRMP.action?idDigitacionDetalleMP='+idDigitacionDetalleMP; 
	console.log("action : "+action);		
//	var ejercicio = $('#txtEjercicio').val(); console.log(ejercicio);	
//	var idPresupuesto = $('#ValorIdPresupuesto').val();	console.log(idPresupuesto);		
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "html",
		    cache: false,
		    async:true,
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
				console.log("success eliminar registros de detalle PI");	   	
				buscarDetalleMP(idFila);
		    }
	});
	
	
}


function eliminarFilaDetalleDigitacionTDR(idFila, idFilaDetalle, tipoMoneda, monto, codigoCuenta){
	
	console.log("eliminarFilaDetalleDigitacionTDR ");
	console.log("idFIla-> : "+idFila);
	
	var idEjercicio = $('#txtEjercicio').val();
	var numDoc = $('#txtNumeroDocumento').val();	
	var idPeriodo = $("#cbPeriodosTDR option:selected").val();
	var tipoDocumento = $('#txtTipoDocumento').val();
	
	console.log("idEjercicio-> : "+idEjercicio);
	console.log("numDoc-> : "+numDoc);
	console.log("idPeriodo-> : "+idPeriodo);
	console.log("tipoDocumento-> : "+tipoDocumento);
	console.log("idFilaDetalle-> : "+idFilaDetalle);
	console.log("tipoMoneda-> : "+tipoMoneda);
	console.log("monto-> : "+monto);
	console.log("codigoCuenta-> : "+codigoCuenta);
		
	var action = 'deleteDetallePIDigitacionTDR.action?idFila='+idFila+'&idFilaDetalle='+idFilaDetalle+'&idEjercicio='+idEjercicio+'&tipoMoneda='+tipoMoneda+'&monto='+monto+'&codigoCuenta='+codigoCuenta+'&numDoc='+numDoc+'&idPeriodo='+idPeriodo+'&tipoDocumento='+tipoDocumento; 
	console.log("action : "+action);		
		
	$.ajax({
		 url: action,
		    type: "POST",
		    dataType: "html",
		    cache: false,
		    async:true,
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
				console.log("success eliminar registros de detalle PI");	   	
				buscarDetalle();
		    }
	});
	
	sumaTotales();
	
}


function habilitarEditarMontoPopUp(fila){
	
	console.log("habilitarEditarMontoPopUp ");
	
	$('label#montoActual_'+fila).hide();	
	$('input#actualizarMonto_'+fila).show();

}


function actualizarMontoPopUp(montoActual, fila, idDetalle){
	
	console.log("actualizarMontoPopUp ");
	
	console.log("fila! ->"+fila);		
	console.log("Cambiar a monto ! ->"+montoActual);	
	console.log("idDetalle! ->"+idDetalle);	
	
	var idEjercicio = $('#txtEjercicio').val();
	console.log("idEjercicio-> : "+idEjercicio);
	
	if ($.isNumeric(montoActual)==false ){
		jAlert("El monto debe ser Numérico", "Alerta");	
	}else{
		
		if(montoActual <= 0){
			jAlert("El monto debe ser Mayor a Cero", "Alerta");	
		}else{
			
			var action = 'updateDetallePIDigitacionTDR.action?idDetalle=' + idDetalle+'&montoActual=' + montoActual+'&idEjercicio='+idEjercicio; 
				
			$.ajax({
				 url: action,
				    type: "POST",
				    data:{idDetalle:idDetalle,montoActual:montoActual,idEjercicio:idEjercicio},
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
				    	console.log("success update monto detalle PI");
				    	$('label#montoActual_'+fila).val(montoActual);	
				    	$('label#montoActual_'+fila).text(montoActual);
				    }
				});
					
			$('label#montoActual_'+fila).show();	
			$('input#actualizarMonto_'+fila).hide();
			
			sumaTotales();
			
		}
	}
}



function refrescarGrillaPIDigitacionTDR(){
	
	console.log("refrescarGrillaPIDigitacionTDR ");
	
	// TODO: comentado por pruebas !!
	//$(".ui-dialog-content").dialog("close");
	
	//$("#popUpClone").closest('.ui-dialog-content').dialog('close');
	$(popUp).closest('.ui-dialog-content').dialog('close');
	
	var idPrograma = $('#cbProgramas').val();
	console.log("refrescarGrillaPIDigitacionTDR - idPrograma: "+idPrograma);
	
	actualizarDatosGrillaPIDigitacionTDR(idPrograma);
	
}


function refrescarGrillaMPDigitacionTDR(){
	
	console.log("refrescarGrillaMPDigitacionTDR ");
	var porId=document.getElementById("txtIdDoc").value;
	
	//$(".ui-dialog-content").dialog("close");
 	var idPrograma = -1; //$('#cbProgramas').val();
	console.log("idPrograma-> : "+idPrograma);

	actualizarDatosGrillaMPDigitacionTDR(idPrograma, porId);
	
}


function validarPIDigitacionTDR(){
	
	//alert("3: " + porId);
	console.log("validarPIDigitacionTDR");
	
	// Se debe validar que la tabla no este vacia para poder ejecutar la validacion !!
	console.log("Validando si la Tabla esta vacia !!!");
	
	var nFilas = $("#tblDigitacionPresupuestoInicial >tbody >tr").length;
	console.log("Nro de Filas en la Tabla: "+nFilas);
	
	if(nFilas == 0){
		
		console.log("nFilas = 0");
		
		jAlert("NO puede validar el Informe sin tener registros en la Tabla","Informacion");
		return;
		
	}
	
	console.log("Fin Validando si la Tabla esta vacia !!!");
	
	//var montoTotalDig = $('#montoTotalDigitado').val();
	var montoTotalDig = 1000;
	var strMensaje;
	var idPartida = $('#cbPartida').val();
	var idCapitulo = $('#cbCapitulo').val();
	var idPrograma = $('#cbProgramas').val();	
	var idPeriodoPI = $("#cbPeriodosTDR option:selected").val();
	var idInforme = $('#cbInforme option:selected').val();
	var tipoDocumento = $('#txtTipoDocumento').val();	
	
	var idDocumento = $('#txtIdDoc').val();	
	
	console.log("montoTotalDig->"+montoTotalDig);
	console.log("idPartida->"+idPartida);
	console.log("idCapitulo->"+idCapitulo);
	console.log("idPrograma->"+idPrograma);
	console.log("idPeriodo->"+idPeriodoPI);
	console.log("idInforme->"+idInforme);
	console.log("tipoDocumento->"+tipoDocumento);
	
	console.log("idDocumento->"+idDocumento);
	
	/**
	if (montoTotalDig.trim() == ''){
		jAlert("Debe digitar el Monto Total", "Alerta");
		return;
	}
	
	
	if ($.isNumeric(montoTotalDig)==false ){
		jAlert("El monto debe ser Numérico", "Alerta");	
		return;
	}else{
	*/
			
	var ejercicio = $('#txtEjercicio').val();
	var numDoc = $('#txtNumeroDocumento').val(); //numero documento
	var fechaDoc = $('#txtFechaDocumento').val(); //fecha documento
	var diaMes = fechaDoc.split("-");	
	var dia = diaMes[0]; //dia
	var mes = diaMes[1];//mes
					
	console.log("ejercicio->"+ejercicio);
	console.log("numDoc->"+numDoc);	
	console.log("dia->"+dia);	
	console.log("mes->"+mes);
	
	var action = 'validarPIDigitacionTDR.action?ejercicio='+ejercicio+'&numDoc='+numDoc+'&dia='+dia+'&mes='+mes+'&sumMontoTotal='+montoTotalDig+'&idPartida='+idPartida+'&idCapitulo='+idCapitulo+'&idPrograma='+idPrograma+'&idPeriodoPI='+idPeriodoPI+'&idInforme='+idInforme+'&tipoDocumento='+tipoDocumento+'&idDocumento='+idDocumento; 
			
	$.ajax({
		 	url: action,
		    type: "POST",
		    data:{ejercicio:ejercicio,numDoc:numDoc,dia:dia,mes:mes,sumMontoTotal:montoTotalDig,idPartida:idPartida,idCapitulo:idCapitulo,idPrograma:idPrograma,idPeriodoPI:idPeriodoPI,idInforme:idInforme,tipoDocumento:tipoDocumento,idDocumento:idDocumento},
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
		    	console.log("success validacion PI");				    	
		    	console.log("codigo->"+data.codigoRespuestaServicio);
		    	console.log("mensaje->"+data.mensajeCargaServicio);
		    	
		    	console.log(data);
		    	
		    	if(data.codigoRespuestaServicio == 1){
		    					    			
		    			if(data.informeTDR.idFileUpload !=null && data.informeTDR.nroDocumento!=null){
		    				strMensaje=data.mensajeCargaServicio;
				    		strMensaje=strMensaje+"<br>\n";
				    		strMensaje=strMensaje+"Pulse aceptar para ver reporte de validacion.";
				    		 jAlert(strMensaje,"Informacion",function(){
				    			 
				    			 var action = 'obtenerValidacionTDR.action?parametros='+data.informeTDR.idFileUpload+'_12_'+data.informeTDR.nroDocumento;
				    			window.open(action,'_blank','scrollbars=1,resizable=1,height=650,width=1050');
				    			
				    		 })
		    			}else{
		    				 jAlert(data.mensajeCargaServicio,"Informacion");
		    			}
		    			    		
		    	}else if(data.codigoRespuestaServicio == -2){
		    		jAlert("Debe ingresar al menos un registro con total de gastos o ingresos", "Informacion");
		    	}else{
		    		console.log("data.archivoXml->"+data.archivoXml);
		    		descargarXml(data.archivoXml);
		    		
		    		var errores = ' Motivo: ';			    		
		    		$.each(data.validacionReglaBO, function(i, item) {			                	
		    			console.log("Mensaje Error->"+item.mensajeError);
		    			errores = errores + item.mensajeError;
		    		});			    
		    		console.log("errores->"+errores);
		    		
		    		if(data.informeTDR.idFileUpload !=null && data.informeTDR.nroDocumento!=null){
	    				strMensaje=data.mensajeCargaServicio;
			    		strMensaje=strMensaje+"<br>\n";
			    		strMensaje=strMensaje+"Pulse aceptar para ver reporte de validacion.";
			    		 jAlert(strMensaje,"Informacion",function(){
			    			 
			    			 var action = 'obtenerValidacionTDR.action?parametros='+data.informeTDR.idFileUpload+'_12_'+data.informeTDR.nroDocumento;
			    			window.open(action,'_blank','scrollbars=1,resizable=1,height=650,width=1050');
			    			
			    		 })
	    			}else{
	    				 jAlert(data.mensajeCargaServicio,"Informacion");
	    			}
		    		
		    	
		    	}
		    	//$('#montoTotalDigitado').val('');
		    	refrescarGrillaPIDigitacionTDR();
		    }
		});				
	//}
}


function actualizarDatosGrillaMPDigitacionTDR(idPrograma, porId){
	
	console.log("actualizarDatosGrillaMPDigitacionTDR ");
	 
	var idCapitulo = -1; //$('#cbCapitulo').val();
	var idPartida = -1; //$('#cbPartida').val();
	var ejercicio = $('#txtEjercicio').val();
	var tipoDocumento = $('#txtTipoDocumento').val();
	var idPeriodoPI = $("#cbPeriodosTDR option:selected").val();
	
	//***Mantener Datos Panel***//
	var tipoInforme = $('#txtTipoInforme').val();
	var informe = $('#txtInforme').val();
	var analista = $('#txtAnalista').val();
	var estadoSicogen = $('#txtEstadoSicogen').val();
	var txtEjercicio = $('#txtEjercicio').val();
	var entidadEmisora = $('#txtEntidadEmisora').val();	
	var tipoDocumento = $('#txtTipoDocumento').val();
	var numDoc = $('#txtNumeroDocumento').val();
	var fechaDoc = $('#txtFechaDocumento').val();
	var fechaRec = $('#txtFechaRecepcionCGR').val();	
	var partidaSeleccionada = $('#cbPartida').prop('selectedIndex');
	var capituloSeleccionada = $('#cbCapitulo').prop('selectedIndex');
	var programaSeleccionada = $('#cbProgramas').prop('selectedIndex');
	var partidaData = $('#cbPartida').html();
	var capituloData = $('#cbCapitulo').html();
	var programaData = $('#cbProgramas').html();	
	//var montoTotalDigitado = $('#montoTotalDigitado').val();
	var montoTotalDigitado = 1000;
	
	//**************************//			
	console.log("idPrograma->"+idPrograma);
	console.log("idCapitulo->"+idCapitulo);	
	console.log("idPartida->"+idPartida);
	console.log("ejercicio->"+ejercicio);	
	console.log("numDoc->"+numDoc);	
	console.log("tipoDocumento->"+tipoDocumento);	
	console.log("idPeriodo->"+idPeriodoPI);	
	var action = 'actualizarDatosGrillaMPDigitacionTDR.action?tipoInforme='+tipoInforme+'&informe='+informe+'&analista='+analista+'&estadoSicogen='+estadoSicogen+'&entidadEmisora='+entidadEmisora+'&ejercicio='+ejercicio+'&idPartida='+idPartida+'&idCapitulo='+idCapitulo+'&idPrograma='+idPrograma+'&numDoc='+numDoc+'&tipoDocumento='+tipoDocumento+'&fechaDoc='+fechaDoc+'&fechaRec='+fechaRec+'&idPeriodoPI='+idPeriodoPI; 
	console.log(action);
	
	$.ajax({
		 url: action,
		    type: "POST",
//		    data:{ejercicio:ejercicio,idPartida:idPartida,idCapitulo:idCapitulo,idPrograma:idPrograma,numDoc:numDoc,tipoDocumento:tipoDocumento,idPeriodoPI:idPeriodoPI},
		    dataType: "html",
		    beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodosGrillaMP" class="overlay" style="display:block;z-index:4000;height: 100%;position:fixed;"></div>'+
					 '<div id="waitPeriodosGrillaMP" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:4005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
			},complete: function (data) {
				$('#fadePeriodosGrillaMP').remove();
				$('#waitPeriodosGrillaMP').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){
		    	console.log(data);
		    	console.log("success poblar grilla MP");	
		    	$('#tablaDocumentosCarga').html(data); //MALO
		    	$('#txtTipoInforme').val(tipoInforme);
		 		$('#txtInforme').val(informe);
		    	$('#txtAnalista').val(analista);
		    	$('#txtEstadoSicogen').val(estadoSicogen);
		    	$('#txtEjercicio').val(txtEjercicio);
		    	$('#txtEntidadEmisora').val(entidadEmisora);	
		    	$('#txtTipoDocumento').val(tipoDocumento);
		    	$('#txtNumeroDocumento').val(numDoc);
		    	$('#txtFechaDocumento').val(fechaDoc);
		    	$('#txtFechaRecepcionCGR').val(fechaRec);		    	
		    	$('#cbPartida').html(partidaData);
		    	$('#cbCapitulo').html(capituloData);
		    	$('#cbProgramas').html(programaData);	
		    	$('#txtIdDoc').val(porId);
		    	
		    	$(".ui-dialog-content").dialog("close");
//		    	$('#cbPartida option:eq('+partidaSeleccionada+')').prop('selected',true);
//		    	$('#cbCapitulo option:eq('+capituloSeleccionada+')').prop('selected',true);
//		    	$('#cbProgramas option:eq('+programaSeleccionada+')').prop('selected',true);		    	    	
		    }
		    
		});	
 
}


function actualizarDatosGrillaPIDigitacionTDR(idPrograma){	
	
	console.log("actualizarDatosGrillaPIDigitacionTDR ");
	
	var idCapitulo = $('#cbCapitulo').val();
	var idPartida = $('#cbPartida').val();
	var ejercicio = $('#txtEjercicio').val();
	var tipoDocumento = $('#txtTipoDocumento').val();
	var idPeriodoPI = $("#cbPeriodosTDR option:selected").val();
	
	//***Mantener Datos Panel***//
	var tipoInforme = $('#txtTipoInforme').val();
	var informe = $('#txtInforme').val();
	var analista = $('#txtAnalista').val();
	var estadoSicogen = $('#txtEstadoSicogen').val();
	var txtEjercicio = $('#txtEjercicio').val();
	var entidadEmisora = $('#txtEntidadEmisora').val();	
	var tipoDocumento = $('#txtTipoDocumento').val();
	var numDoc = $('#txtNumeroDocumento').val();
	var fechaDoc = $('#txtFechaDocumento').val();
	var fechaRec = $('#txtFechaRecepcionCGR').val();	
	var partidaSeleccionada = $('#cbPartida').prop('selectedIndex');
	var capituloSeleccionada = $('#cbCapitulo').prop('selectedIndex');
	var programaSeleccionada = $('#cbProgramas').prop('selectedIndex');
	var partidaData = $('#cbPartida').html();
	var capituloData = $('#cbCapitulo').html();
	var programaData = $('#cbProgramas').html();	
	//var montoTotalDigitado = $('#montoTotalDigitado').val();
	var montoTotalDigitado = 1000;
	
	//**************************//			
	console.log("idPrograma->"+idPrograma);
	console.log("idCapitulo->"+idCapitulo);	
	console.log("idPartida->"+idPartida);
	console.log("ejercicio->"+ejercicio);	
	console.log("numDoc->"+numDoc);	
	console.log("tipoDocumento->"+tipoDocumento);	
	console.log("idPeriodo->"+idPeriodoPI);	
		
	var action = 'actualizarDatosGrillaPIDigitacionTDR.action?ejercicio='+ejercicio+'&idPartida='+idPartida+'&idCapitulo='+idCapitulo+'&idPrograma='+idPrograma+'&numDoc='+numDoc+'&tipoDocumento='+tipoDocumento+'&idPeriodoPI='+idPeriodoPI; 
			
	$.ajax({
		 url: action,
		    type: "POST",
		    data:{ejercicio:ejercicio,idPartida:idPartida,idCapitulo:idCapitulo,idPrograma:idPrograma,numDoc:numDoc,tipoDocumento:tipoDocumento,idPeriodoPI:idPeriodoPI},
		    dataType: "html",
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
		    	console.log("success poblar grilla PI");		    	
		    	$('#tablaDocumentosCarga').html(data);
		    	$('#txtTipoInforme').val(tipoInforme);
		    	$('#txtInforme').val(informe);
		    	$('#txtAnalista').val(analista);
		    	$('#txtEstadoSicogen').val(estadoSicogen);
		    	$('#txtEjercicio').val(txtEjercicio);
		    	$('#txtEntidadEmisora').val(entidadEmisora);	
		    	$('#txtTipoDocumento').val(tipoDocumento);
		    	$('#txtNumeroDocumento').val(numDoc);
		    	$('#txtFechaDocumento').val(fechaDoc);
		    	$('#txtFechaRecepcionCGR').val(fechaRec);		    	
		    	$('#cbPartida').html(partidaData);
		    	$('#cbCapitulo').html(capituloData);
		    	$('#cbProgramas').html(programaData);		    		
		    	$('#cbPartida option:eq('+partidaSeleccionada+')').prop('selected',true);
		    	$('#cbCapitulo option:eq('+capituloSeleccionada+')').prop('selected',true);
		    	$('#cbProgramas option:eq('+programaSeleccionada+')').prop('selected',true);		    	    	
		    }
		});				
}


function descargarXml(xml){
	
	console.log("descargarXml ");
	
	var parametros = 'xml='+xml;
	dowFil = 'descargarArchivoXML.action?' + parametros;	
	location.href=dowFil;	
	
}


