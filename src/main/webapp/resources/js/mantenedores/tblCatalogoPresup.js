function cerrarA(){
	$("#popupCrearSubtitulo").dialog("close");
	try {$(".ui-dialog-content").dialog("close");}catch(err) {}
}
function cerrarB(){
	$("#popupCrearNivel").dialog("close");
}
function cerrarC(){
	$("#popupEditarCuenta").dialog("close");
}
function cerrarD(){
	$("#popupDesactivarCuenta").dialog("close");
}

function postSubtituloCuentaPresup(){
	
	var data = $('#frmSubtituloCuentaPresup [name!=st-PeriodosHabilitados][name!=st-AsociacionContable]').serialize();
	
	var stPeriodosSelected = []; 
	$('#st-PeriodosHabilitados :selected').each(function(i, selected){ 
		stPeriodosSelected[i] = $(selected).text(); 
	});
	
	var peridosSelected = stPeriodosSelected.toString();
	
	if(peridosSelected !== ''){
		data = data + '&peridosSelected=' + peridosSelected;
	}

	var editAsociacionContable = []; 
	$('#st-AsociacionContable :selected').each(function(i, selected){ 
		editAsociacionContable[i] = $(selected).text(); 
	});
	
	var contablesSelected = editAsociacionContable.toString();
	
	if(contablesSelected !== ''){
		data = data + '&contablesSelected=' + contablesSelected;
	}
	
	data = data + '&idEjercicio='  + $("#cbEjercicio :selected").val();
	
	$.ajax({
	    url: 'postSubtituloCuentaPresup',
	    type: 'POST',
	    data: data,	    
	     beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
		  },complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();

		  },success: function(data){

			  	if (data.idNuevoRegistro == "-1"){
			  		jAlert("La cuenta presupuestaria ya existe para el ejercicio seleccionado","Alerta");
			  	} 
			  	else if (data.idNuevoRegistro == "-2"){
			  		jAlert("Descipci&oacute;n no puede ser vac&iacute;a","Alerta");
			  	} else {
				    var popUp = $("#popupCrearSubtitulo").dialog("close");
					  
				  	getSubtitulosByEjericio();

					jAlert('Cuenta creada correctamente', 'Confirmaci&oacute;n');

			  	}	    	
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				
			}
	});	
}

function crearSubtituloCtaPresup(){
	$('#frmSubtituloCuentaPresup').find('[type="submit"]').trigger('click');		
}

function openPopupCreateSubt(){
	
		var valEjercicio =  $("#cbEjercicio :selected").val() ;
		if (valEjercicio < 0 ){
			jAlert('Debe seleccionar un ejercicio','Alerta');
		} else {
		  	$("#stEjercicio").val(  $("#cbEjercicio :selected").text() ); 
			 
			$.ajax({
			    url: 'openPopupCreateSubt?idEjercicio='+valEjercicio,
			    type: 'GET',	    
			     beforeSend: function (xhr) {
						$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
							 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
							 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
							 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
							 '</div>');
				  },complete: function (data) {
						$('#fadePeriodos').remove();
						$('#waitPeriodos').remove();

				  },success: function(data){

					  $('#stSubt').val('');
					  $('#stDesc').val('');
					  
					  $("[name=stTipoPresu]").prop('checked',false);
					  $("[name=st-InstanciaComp]").prop('checked',false);
					  $("[name=st-CartAfectacion]").prop('checked',false);
					  $("[name=st-CartDevng]").prop('checked',false);
					  $("[name=st-ImpPresup]").prop('checked',false);
			  
					  $("#st-AsociacionContable").get(0).options.length = 0;
					  
					  data.listAsociacionContable.forEach(function (item) {
					  $("#st-AsociacionContable").get(0).options[$("#st-AsociacionContable").get(0).options.length] 
					  		= new Option(item.cuenta , item.cuenta );					
					  });   
					  
					  $("#st-PeriodosHabilitados").get(0).options.length = 0;
					  
					  data.listPeriodos.forEach(function (item) {
					    	$("#st-PeriodosHabilitados").get(0).options[$("#st-PeriodosHabilitados").get(0).options.length] 
								= new Option(item.periodoCodigo , item.periodoCodigo );		
						
							$("#st-PeriodosHabilitados option[value='"+$.trim(item.periodoCodigo)+"']").prop("selected", true);

					  });
					  
					  var opt = {
						        autoOpen: true,
						        modal: true,
						        width: 650,
						        show: "blind",
						        title : "Crear Subtítulo"
					  };
						
					  var popUp = $("#popupCrearSubtitulo").dialog(opt);
					  popUp.dialog("open");
			    	
					}, error: function(XMLHttpRequest, textStatus, errorThrown){
						
					}
			});	
			
			
		}	
}

function postCrearNivelCuentaPresup(){
	
	var data = $('#frmCrearNivelCuentaPresup [name!=nnPeriodosHabilitados][name!=nnAsociacionContable]').serialize();

	var stPeriodosSelected = []; 
	$('#nnPeriodosHabilitados :selected').each(function(i, selected){ 
		stPeriodosSelected[i] = $(selected).text(); 
	});
	
	var peridosSelected = stPeriodosSelected.toString();
	
	if(peridosSelected !== ''){
		data = data + '&peridosSelected=' + peridosSelected;
	}

	var editAsociacionContable = []; 
	$('#nnAsociacionContable :selected').each(function(i, selected){ 
		editAsociacionContable[i] = $(selected).text(); 
	});

	var contablesSelected = editAsociacionContable.toString();
	
	if(contablesSelected !== ''){
		data = data + '&contablesSelected=' + contablesSelected;
	}	

	data = data + '&idEjercicio=' + $("#cbEjercicio :selected").val();
	
	$.ajax({
	    url: 'postCrearNivelCuentaPresup',
	    type: 'POST',
	    data: data,	    
	     beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
		  },complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();

		  },success: function(data){

			  	if (data.idNuevoRegistro == "-1"){
			  		jAlert("La cuenta presupuestaria ya existe para el ejercicio seleccionado","Alerta");
			  	} else if (data.idNuevoRegistro == "-2"){
				  		jAlert("Descripción no puede ser vacía","Alerta");
				
			  	} else {
					var popUp = $("#popupCrearNivel").dialog("close");
					
					jAlert('Registro creado correctamente', 'Confirmaci&oacute;n');

					var ejercicio = $('#cbEjercicio').val();
					var idSubtitulo = $('#cbCtaSubtTitulo').val();
					var idItem =  $('#cbItems').val();
					var activo = $('#cbActivo').val();
					var isItem = $('#nnItem').val();
					
					if((isItem != null) && (isItem.length > 0)){
						getItemsBySubTitulo();
					}
			  	}

				
				//$('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicio,idSubtitulo:idSubtitulo,idItem:idItem,activo:activo});
					    	
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				jAlert("Error del Servidor 404","Alerta");
			}
	});	
}

function crearNivelCuentaPresup(){
	$('#frmCrearNivelCuentaPresup').find('[type="submit"]').trigger('click');	
}

function crearNivel(param){
	
	var opt = {
	        autoOpen: true,
	        modal: true,
	        width: 650,
	        show: "blind",
	        title : "Crear Cuenta Presupuestaria"
	};
		
	var popUp = $("#popupCrearNivel").dialog(opt);
	popUp.dialog("open");
	  
	var idEjercicio =  $("#cbEjercicio :selected").val();
	
	$.ajax({url: 'verCuentaPresup?idCuenta='+param+'&idEjercicio='+idEjercicio, type: "GET", dataType:'json',
		
		beforeSend: function ( data ) {
			console.log("beforeSend");
			
		},
		complete: function ( data ) {
			console.log("complete");
			
		},	
		success: function(data){

			var ctaCod = data.registroSeleccionado.codSubTitulo + "." +
			 		  data.registroSeleccionado.codItem + "." +	
			 		  data.registroSeleccionado.codAsignacion + "." +	
			 		  data.registroSeleccionado.codSubAsignaciones ;

			  $("#nnEjercicio").val( $("#cbEjercicio :selected").text() ); 
			
			  if("000" === data.registroSeleccionado.codSubAsignaciones 
					  && "000" === data.registroSeleccionado.codAsignacion					  
						&& "00" === data.registroSeleccionado.codItem)
			  {
				  
				  document.getElementById("nnSubt").disabled = true; $("#nnSubt").val( data.registroSeleccionado.codSubTitulo ); 
				  document.getElementById("nnItem").disabled = false; $("#nnItem").val( '' ); 
				  document.getElementById("nnAsig").disabled = true; $("#nnAsig").val( '000' ); 
				  document.getElementById("nnSubAsig").disabled = true; $("#nnSubAsig").val( '000' ); 

			  }if("000" === data.registroSeleccionado.codSubAsignaciones 
					  && "000" === data.registroSeleccionado.codAsignacion					  
						&& "00" !== data.registroSeleccionado.codItem){
				  

				  document.getElementById("nnSubt").disabled = true; $("#nnSubt").val( data.registroSeleccionado.codSubTitulo ); 
				  document.getElementById("nnItem").disabled = true; $("#nnItem").val( data.registroSeleccionado.codItem ); 
				  document.getElementById("nnAsig").disabled = false; $("#nnAsig").val( '' ); 
				  document.getElementById("nnSubAsig").disabled = true; $("#nnSubAsig").val( '000' ); 

			  }if("000" === data.registroSeleccionado.codSubAsignaciones 
					  && "000" !== data.registroSeleccionado.codAsignacion					  
						&& "00" !== data.registroSeleccionado.codItem){
				  

				  document.getElementById("nnSubt").disabled = true; $("#nnSubt").val( data.registroSeleccionado.codSubTitulo ); 
				  document.getElementById("nnItem").disabled = true; $("#nnItem").val( data.registroSeleccionado.codItem ); 
				  document.getElementById("nnAsig").disabled = true; $("#nnAsig").val( data.registroSeleccionado.codAsignacion ); 
				  document.getElementById("nnSubAsig").disabled = false; $("#nnSubAsig").val( '' ); 
				  
			  }
			
			  $("#nnDescripcion").val('');
			  $("[name=nnTipoPresu]").prop('checked',false);
			  $("[name=nnInstanciaComp]").prop('checked',false);
			  $("[name=nnCartAfectacion]").prop('checked',false);
			  $("[name=nnCartDevng]").prop('checked',false);
			  $("[name=nnImpPresup]").prop('checked',false);	
			
			$("#nnAsociacionContable").get(0).options.length = 0;

		    data.listAsociacionContable.forEach(function (item) {
				$("#nnAsociacionContable").get(0).options[$("#nnAsociacionContable").get(0).options.length] 
					= new Option(item.cuenta , item.cuenta );					
			});   

			$("#nnPeriodosHabilitados").get(0).options.length = 0;
			
		    data.listPeriodos.forEach(function (item) {
				$("#nnPeriodosHabilitados").get(0).options[$("#nnPeriodosHabilitados").get(0).options.length] 
					= new Option(item.periodoCodigo , item.periodoCodigo );		
				
				$("#nnPeriodosHabilitados option[value='"+$.trim(item.periodoCodigo)+"']").prop("selected", true);
			});   
		     
			  var opt = {
				        autoOpen: true,
				        modal: true,
				        width: 650,
				        show: "blind",
				        title : "Crear Cuenta Presupuestaria"
			  };
				
			  var popUp = $("#popupCrearNivel").dialog(opt);
			  popUp.dialog("open");
				

		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('error');
		}
	});	
	
}

function editarCuentaPresup(){

	var data = $('#frmEditarCuentaPresup [name!=editPeriodosHabilitados][name!=editAsociacionContable]').serialize();
	
	var stPeriodosSelected = []; 
	$('#editPeriodosHabilitados :selected').each(function(i, selected){ 
		stPeriodosSelected[i] = $(selected).text(); 
	});
	
	var peridosSelected = stPeriodosSelected.toString();
	
	if(peridosSelected !== ''){
		data = data + '&peridosSelected=' + peridosSelected;
	}

	var editAsociacionContable = []; 
	$('#editAsociacionContable :selected').each(function(i, selected){ 
		editAsociacionContable[i] = $(selected).text(); 
	});

	var contablesSelected = editAsociacionContable.toString();
	
	if(contablesSelected !== ''){
		data = data + '&contablesSelected=' + contablesSelected;
	}	
	
	data = data + '&idEjercicio=' + $("#cbEjercicio").val();
	$.ajax({
	    url: 'editarCuentaPresupuestaria',
	    type: 'POST',
	    data: data,	    
	     beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
		  },complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();

		  },success: function(data){

			  if(data.codResp == -2){
				  jAlert('Descripcion no puede ser vacia', 'Confirmaci&oacute;n');
			  } else {
					var popUp = $("#popupEditarCuenta").dialog("close");
					
					jAlert('Registro actualizado correctamente', 'Confirmaci&oacute;n');

					var ejercicio = $('#cbEjercicio').val();
					var idSubtitulo = $('#cbCtaSubtTitulo').val();
					var idItem =  $('#cbItems').val();
					var activo = $('#cbActivo').val();
					
					$('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicio,idSubtitulo:idSubtitulo,idItem:idItem,activo:activo});
					  
			  }	    	
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				
			}
	});
}

function actualizarCuenta(){   
	$('#frmEditarCuentaPresup').find('[type="submit"]').trigger('click');
}


//CREAR FUNCION
function verPeriodosMarcados(param){
	
	console.log("verPeriodosMarcados - param: "+param);
	
	var idEjercicio =  $("#cbEjercicio :selected").val();
	console.log("verPeriodosMarcados - idEjercicio: "+idEjercicio);
	
	$.ajax({
		url: 'verCuentaPresup?idCuenta='+param+'&idEjercicio='+idEjercicio, 
		type: "GET", 
		dataType:'json',
		
		beforeSend: function ( data ) {
			console.log("beforeSend");
			
		},
		complete: function ( data ) {
			console.log("complete");
			
		},	
		success: function(data){
			
			console.log(data);
			
			//$("#editPeriodosHabilitados").get(0).options.length = 0;
			$("#pnPeriodos").get(0).options.length = 0;
			
		    data.listPeriodos.forEach(function (item) {
				//$("#editPeriodosHabilitados").get(0).options[$("#editPeriodosHabilitados").get(0).options.length] 
		    	$("#pnPeriodos").get(0).options[$("#pnPeriodos").get(0).options.length]
					= new Option(item.periodoCodigo , item.periodoCodigo );					
			});   
		     
		    if(data.listPeriodos != null ){
		    	
		    	console.log("verPeriodosMarcados - data.listPeriodos not null !! ");
		    	
		    	if(data.registroSeleccionado.periodosHabilitados != null){
		    		
		    		console.log("verPeriodosMarcados - data.registroSeleccionado.periodosHabilitados not null !! ");
		    		
				    data.registroSeleccionado.periodosHabilitados.split(",").forEach(function (item) {
				    	
				    	console.log("verPeriodosMarcados - item: "+item);
				    	
				    	//$("#editPeriodosHabilitados option[value='"+$.trim(item)+"']").prop("selected", true);
				    	$("#pnPeriodos option[value='"+$.trim(item)+"']").prop("selected", true);
					}); 	
		    	}			    	
		    }
			
			  var opt = {
				        autoOpen: true,
				        modal: true,
				        width: 650,
				        show: "blind",
				        title : "Editar Periodo"
			  };
				
			  var popUp = $("#popupEditarPeriodo").dialog(opt);
			  popUp.dialog("open");
				

		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('error');
		}
	});	
	
	
}

function verCuentaPresup(param){
	
	var idEjercicio =  $("#cbEjercicio :selected").val();
	
	$.ajax({url: 'verCuentaPresup?idCuenta='+param+'&idEjercicio='+idEjercicio, type: "GET", dataType:'json',
		
		beforeSend: function ( data ) {
			console.log("beforeSend");
			
		},
		complete: function ( data ) {
			console.log("complete");
			
		},	
		success: function(data){

			var ctaCod = data.registroSeleccionado.codSubTitulo + "." +
			 		  data.registroSeleccionado.codItem + "." +	
			 		  data.registroSeleccionado.codAsignacion + "." +	
			 		  data.registroSeleccionado.codSubAsignaciones ;
			
			$("#pnCuenta").val( ctaCod );
			$("#pnDesc").val(data.registroSeleccionado.nomCuenta);
			$("#pnEjercicio").val( $("#cbEjercicio :selected").text() ); 
			
			if (data.registroSeleccionado.tipoPresupuesto == 'INGRESO'){ $("#tp-Ingreso").prop('checked',true); }
			if (data.registroSeleccionado.tipoPresupuesto == 'GASTO'){ $("#tp-Gasto").prop('checked',true); }
			if (data.registroSeleccionado.tipoPresupuesto == null||
					data.registroSeleccionado.tipoPresupuesto == 'NA'){ $("#tp-na").prop('checked',true); }
			
			if (data.registroSeleccionado.instanciaCompromiso == 'NO'){  $("#insComp-no").prop('checked',true); }
			if (data.registroSeleccionado.instanciaCompromiso == 'SI'){  $("#insComp-si").prop('checked',true); }
			if (data.registroSeleccionado.instanciaCompromiso == null||
					data.registroSeleccionado.instanciaCompromiso == 'NA'){ $("#insComp-na").prop('checked',true); }

			if (data.registroSeleccionado.carteraAfectacion == 'NO'){  $("#cartAfect-no").prop('checked',true); }
			if (data.registroSeleccionado.carteraAfectacion == 'SI'){  $("#cartAfect-si").prop('checked',true); }
			if (data.registroSeleccionado.carteraAfectacion == null ||
					data.registroSeleccionado.carteraAfectacion == 'NA'){  $("#cartAfect-na").prop('checked',true); }

			if (data.registroSeleccionado.carteraDevengo == 'NO'){  $("#cartDevng-no").prop('checked',true); }
			if (data.registroSeleccionado.carteraDevengo == 'SI'){  $("#cartDevng-si").prop('checked',true); }
			if (data.registroSeleccionado.carteraDevengo == null||
					data.registroSeleccionado.carteraDevengo == 'NA'){  $("#cartDevng-na").prop('checked',true); }
			
			if (data.registroSeleccionado.imputacionPresupuestaria == '0'){  $("#impPres-no").prop('checked',true); }
			if (data.registroSeleccionado.imputacionPresupuestaria == '1'){  $("#impPres-si").prop('checked',true); }
			if (data.registroSeleccionado.imputacionPresupuestaria == null||
					data.registroSeleccionado.imputacionPresupuestaria == 'NA'){  $("#impPres-na").prop('checked',true); }			

			
			//Selector Asociacion Contable
			$("#editAsociacionContable").get(0).options.length = 0;
			
		    data.listAsociacionContable.forEach(function (item) {
				$("#editAsociacionContable").get(0).options[$("#editAsociacionContable").get(0).options.length] 
					= new Option(item.cuenta , item.cuenta );
			});   
		     
		    if(data.registroSeleccionado.asociacionContable != null ){
			    data.registroSeleccionado.asociacionContable.split(",").forEach(function (item) {

			    	$("#editAsociacionContable option[value='"+$.trim(item)+"']").prop("selected", true);
				}); 			    	
		    }

			//Selector Periodos habilitados
			$("#editPeriodosHabilitados").get(0).options.length = 0;
			
		    data.listPeriodos.forEach(function (item) {
				$("#editPeriodosHabilitados").get(0).options[$("#editPeriodosHabilitados").get(0).options.length] 
					= new Option(item.periodoCodigo , item.periodoCodigo );					
			});   
		     
		    if(data.listPeriodos != null ){
		    	
		    	if(data.registroSeleccionado.periodosHabilitados != null){
		    		
				    data.registroSeleccionado.periodosHabilitados.split(",").forEach(function (item) {
				    	$("#editPeriodosHabilitados option[value='"+$.trim(item)+"']").prop("selected", true);
					}); 	
		    	}			    	
		    }
			
			  var opt = {
				        autoOpen: true,
				        modal: true,
				        width: 650,
				        show: "blind",
				        title : "Editar Cuenta"
			  };
				
			  var popUp = $("#popupEditarCuenta").dialog(opt);
			  popUp.dialog("open");
				

		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('error');
		}
	});	
	
	
}

//Cuentas Presup
function postDelete(){
	
	$.ajax({url: 'postDesactivarCuentasContables', type: "GET", dataType:'json',
		
		beforeSend: function ( data ) {
			console.log("beforeSend");
		},
		complete: function ( data ) {
			console.log("complete");
		},	
		success: function(data){
	    	
			  $("#popupDesactivarCuenta").dialog("close");

				var ejercicio = $('#cbEjercicio').val();
				var idSubtitulo = $('#cbCtaSubtTitulo').val();
				var idItem =  $('#cbItems').val();
				var activo = $('#cbActivo').val();
				
				$('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicio,idSubtitulo:idSubtitulo,idItem:idItem,activo:activo});
			  
    			jAlert('Registros desactivados correctamente ', 'Confirmaci&oacute;n');
			  
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('error');
		}
	});
}


function deleteCuenta(){   
	$('#frmDesactivarCuenta').find('[type="submit"]').trigger('click');
}


function desactivarCuenta(pk){

	console.log("desactivarCuenta - pk: "+pk);
	var idEjercicio = $("#cbEjercicio").val();
	
	$.ajax({
		  type: "GET",
		  url: "getCtaPresupDependientes?idCta="+pk+"&idEjercicio="+idEjercicio,

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
 
		  },success: function(data){
		    
			try {
				var tbla = "";
				$('#tblDesactivarCtasCtables  > tbody').empty();
				
				$.each(data.listDependientes, function(i, item) {  
					tbla = tbla + "<tr><td>" +item['codSubTitulo']+ "."+
										 	  item['codItem']+ "."+
										 	  item['codAsignacion']+ "."+
										 	  item['codSubAsignaciones']+
											"</td>" + "<td>" +item['nomCuenta']+ "</td></tr>"
			    });
				
				$('#tblDesactivarCtasCtables > tbody').html(tbla);

				  var opt = {
					        autoOpen: true,
					        modal: true,
					        width: 500,
					        show: "blind",
					        title : "Alerta"
				  };
					
				  var popUp = $("#popupDesactivarCuenta").dialog(opt);
				  popUp.dialog("open");
					
//				  $(".ui-widget-overlay").addClass("overlay" ); 
//				  $(".overlay" ).removeClass("ui-widget-overlay" );
//					
//				  $(".ui-icon-closethick").css('background-position', '-32px -192px');
//				  $(".ui-icon-closethick").css('background-color', '#F2F2F2');
//				  $(".ui-icon-closethick").css('top', '0px');
//				  $(".ui-icon-closethick").css('left', '0px');
			}
			catch(err) {
				    alert (err.message);
			}
		  }
	});
}

function verBitacoraCatalogoPresup(param){
	
	var idEjercicio = $("#cbEjercicio").val();
	$.ajax({
		  type: "GET",
		  url: "verBitacoraCatalogoPresup?idCta="+param+"&idEjercicio="+idEjercicio,
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

		  },success: function(data){
				
			  var tbla = "";
			  $('#tblDesactivarCtasCtables  > tbody').empty();
				
			  jQuery.each(data.cambiosBitacoraVO, function(i, val) { 
					tbla = tbla + "<tr><td>" +val.estado + "</td><td>" + val.fecha + "</td><td>" + val.usuario + "</td></tr>"
			   });
				
			  $('#tblDesactivarCtasCtables > tbody').html(tbla);

			  var opt = {
				        autoOpen: true,
				        modal: true,
				        width: 500,
				        show: "blind",
				        title : "Bit&aacute;cora de Actualizaciones"
			  };
				
			  var popUp = $("#popupBitacoraCuenta").dialog(opt);
			  popUp.dialog("open");
				
  
		  }
	});
	
}


function getSubtitulosByEjericio(){
	
	action='getSubtitulosByEjericio?ejercicio='+$('#cbEjercicio').val();	

	$("#cbCtaSubtTitulo").get(0).options.length = 0;
	
	$.ajax({url: action, type: "GET", dataType:'json',
	
		beforeSend: function ( data ) {
			console.log("beforeSend");
		
		},
		complete: function ( data ) {
			console.log("complete");
		
		},	
		success: function(data){
			$('#cbCtaSubtTitulo').empty();
			$("#cbCtaSubtTitulo").get(0).options.length = 0;
			$("#cbCtaSubtTitulo").get(0).options[0] = new Option("Select", "-1"); 
			
			$.each(data.listSubTitulos, function(i, item) { 
			
				var pkCuenta	 	= item["pkCuenta"];
				var codSubtitulo	= item["codSubtitulo"];
				var nomCuenta		= item["nomCuenta"];

				$("#cbCtaSubtTitulo").get(0).options[$("#cbCtaSubtTitulo").get(0).options.length] = new Option(codSubtitulo+" "+nomCuenta,pkCuenta );
			});
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('error');
		}
	});
}

function getItemsBySubTitulo(){
	
	action='getItemsBySubTitulo?ejercicio='+$('#cbEjercicio').val()+'&idSubtitulo='+$('#cbCtaSubtTitulo').val();	
	
	$("#cbItems").get(0).options.length = 0;
	
	$.ajax({url: action, type: "GET", dataType:'json',
	
		beforeSend: function ( data ) {
			console.log("beforeSend");
		
		},
		complete: function ( data ) {
			console.log("complete");
		
		},	
		success: function(data){
			$('#cbItems').empty();
			$("#cbItems").get(0).options.length = 0;
			$("#cbItems").get(0).options[0] = new Option("Select", "-1"); 
			
			$.each(data.listItems, function(i, item) { 
			
				var pkCuenta	= item["pkCuenta"];
				var codItem		= item["codItem"];
				var nomCuenta	= item["nomCuenta"];

				$("#cbItems").get(0).options[$("#cbItems").get(0).options.length] = new Option(codItem+" "+nomCuenta,pkCuenta );
			});
			
		  	getCatalogoPresupByFiltros();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('error');
		}
	});
}

function getCatalogoPresupByFiltros(){
	
	var ejercicio = $('#cbEjercicio').val();
	var idSubtitulo = $('#cbCtaSubtTitulo').val();
	var idItem =  $('#cbItems').val();
	var activo = $('#cbActivo').val();
	
	$('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicio,idSubtitulo:idSubtitulo,idItem:idItem,activo:activo});

}




//****************PRUEBA VER CON LINK*********************
function openPopupPeriodo(pk){

	var param = 'pk='+pk;
	
	$.ajax({
		  type: "GET",
		  url: "getCtaContable?"+param,
		  success: function(data){
			  
				$("#pnPeriodos").get(0).options.length = 0;
				
			    data.listPeriodos.forEach(function (item) {
					$("#pnPeriodos").get(0).options[$("#pnPeriodos").get(0).options.length] 
						= new Option(item.periodoCodigo , item.periodoCodigo );					
				});   
			    
			    data.cuentaContableDetalle.periodidosHabilitados.split(",").forEach(function (item) {
			    	$('#pnPeriodos option:eq('+$.trim(item)+')').prop('selected',true);	
				});  
		  }
	});	  
	
	var opt = {
	        autoOpen: true,
	        modal: true,
	        width: 305,
	        show: "blind",
	        title : "Editar Periodo"
	};
	
	var popUp = $("#popupEditarPeriodo").dialog(opt);
	popUp.dialog("open");
	
	$(".ui-widget-overlay").addClass("overlay" ); 
	$(".overlay" ).removeClass("ui-widget-overlay" );
	
	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');
	
}






$(document).ready(function() {


	var myTable = $('#ExpenseTableContainer').jtable({
		title: 'Clasificados Cuentas Presupuestarias',
		selecting: true, //Enable selecting 
		paging: true, //Enable paging
		pageSize: 10, //Set page size (default: 10)
		sorting: false, //Enable sorting
		actions: {
			listAction: 'getCatalogoPresupByFiltros',
		},messages: {
			serverCommunicationError: 'Se ha producido un error al comunicarse con el servidor.',
			loadingMessage: 'Cargando Registros ...',
			noDataAvailable: 'Seleccione los filtros!',
			addNewRecord: 'Crear Registro',
			editRecord: 'Editar',
			areYouSure: '&iquest;Est&aacute; seguro?',
			deleteConfirmation: 'Se eliminar&aacute; este registro. &iquest;Est&aacute; seguro? ',
			save: 'Guardar',
			saving: 'Guardando',
			cancel: 'Cancelar',
			deleteText: 'Eliminar',
			deleting: 'Borrado',
			error: 'Alerta',
			close: 'Cerrar',
			cannotLoadOptionsFor: 'No se puede cargar las opciones para el campo {0}',
			pagingInfo: 'Mostrando: {0}-{1}, de: {2}',
			pageSizeChangeLabel: 'Numero de Registros',
			gotoPageLabel: 'ir a la p&aacute;gina',
			canNotDeletedRecords: '�No se puede eliminar {0} de {1} registro',
			deleteProggress: 'Eliminado {0} de {1} archivos, procesamiento de ...'
		}, fields: {
			idTabla1:	{	title: '1',key: true,list: false,create: false,edit:false,width: '2%'},
			idTabla2:	{	title: '2',key: true,list: false,create: false,edit:false,width: '2%'},
			pkCuenta: {
            	title: 'pk',
                width: '4%',
                list: false
            },
            codSubTitulo: {
            	title: 'ST',
                width: '3%'
            },
            codItem: {
            	title: 'IT',
                width: '3%'
            },
            codAsignacion: {
            	title: 'AS',
                width: '4%'
            },
            codSubAsignaciones: {
            	title: 'SA',
                width: '4%'
            },
            nomCuenta: {
            	title: 'Descripci&oacute;n',
                width: '20%'
            },
            tipoPresupuesto: {
            	title: 'Tipo Presupuesto',
                width: '6%'
            },
            instanciaCompromiso: {
            	title: 'Instancia Compromiso',
                width: '6%'
            },
            carteraAfectacion: {
            	title: 'Cartera Afectaci&oacute;n',
                width: '6%'
            },
            carteraDevengo: {
            	title: 'Cartera Devengo',
                width: '6%'
            },
            asociacionContable: {
            	title: 'Asociaci&oacute;n Contable',
                width: '6%'
            },
            periodidosHabilitados: {
            	title: 'Periodos habilitados',
            	width: '6%',
            	display: function (data) {
            	var pk = data.record.pkCuenta;
            	
            		if(data.record.periodosHabilitados != null){
            		
            			var $img = $('<a title="'+data.record.periodosHabilitados+'" href="javascript: void(0);" onclick="verPeriodosMarcados('+pk+')" > Ver </a>');
                		$img.tooltip();
                		return $img;
            		}
            		
                }
            }
            /*
            periodidosHabilitados: {
            	title: 'Per',
            	width: '6%',
            	display: function (data) {
            		var idTabla1 = data.record.idTabla1;
            		var idTabla2 = data.record.idTabla2;
            		
            		if (!idTabla1.trim()) {idTabla1 = "null";}
            		if (!idTabla2.trim()) {idTabla2 = "null";}
            		
            		var $img = $('<a title="'+ data.record.periodidosHabilitados +'" href="javascript: void(0);" onclick="openPopupPeriodo('+idTabla1+','+idTabla2+')">Ver</a>');
            		$img.tooltip();
            		return $img;
                }
            }
            */
            ,
            fechaDesactivacion: {
            	title: 'Activo',
                width: '6%',
                display: function (data) {
                	if(data.record.fechaDesactivacion != null){
                		return $('<label title="Inactivo" >Inactivo</label>');
                	} else {
                		return $('<label title="Activo" >Activo</label>');
                	}
                }
            }, 
            imputacionPresupuestaria: {
            	title: 'Imputación',
                width: '6%',
                display: function (data) {
                	if("0" == data.record.imputacionPresupuestaria ){
                		return $('<label >NO</label>');
                	} else if( "1" == data.record.imputacionPresupuestaria){
                		return $('<label >SI</label>');
                	}
                }
            },
            add_nivel: {
            	title: 'Opciones',
            	width: '18%',
            	display: function (data) {
            		if ("000" != data.record.codSubAsignaciones){
            			
            			var codigo1 = '<button type="button" onclick="verBitacoraCatalogoPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
        				'  <img src="images/ver_icon.png" height="10"/>'+
        				'</button>'+
        				'<button title="Editar cuenta" type="button" onclick="verCuentaPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
        				'  <img src="jtable/themes/lightcolor/edit.png" height="15"/>'+
        				'</button>'+
        				'<button title="Deshabilitar cuenta" type="button" onclick="desactivarCuenta('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
        				'  <img src="jtable/themes/lightcolor/delete.png" height="15"/>'+
        				'</button>';
						
            			var codigo2 = '<button type="button" onclick="verBitacoraCatalogoPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
        				'  <img src="images/ver_icon.png" height="10"/>'+
        				'</button>'+
        				'<button title="Editar cuenta" type="button" onclick="verCuentaPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
        				'  <img src="jtable/themes/lightcolor/edit.png" height="15"/>'+
        				'</button>';
            			
            			if(data.record.fechaDesactivacion == null){
            				// Esta Activo.. Se puede deshabilitar !!
            				return $(codigo1);
            			}else{
            				// Esta Inctivo.. NO se puede deshabilitar !!
            				return $(codigo2);
            			}
						/**
                		return $(
                				'<button type="button" onclick="verBitacoraCatalogoPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="images/ver_icon.png" height="10"/>'+
                				'</button>'+
                				'<button title="Editar cuenta" type="button" onclick="verCuentaPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="jtable/themes/lightcolor/edit.png" height="15"/>'+
                				'</button>'+
                				'<button title="Deshabilitar cuenta" type="button" onclick="desactivarCuenta('+data.record.pkCuenta+','+data.record.fechaDesactivacion+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="jtable/themes/lightcolor/delete.png" height="15"/>'+
                				'</button>');  
                		*/		       			
            		} else {
            			var codigo1 = '<button type="button" onclick="verBitacoraCatalogoPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
        							  '  <img src="images/ver_icon.png" height="10"/>'+
			        				  '</button>'+
			        				  '<button title="Agregar nivel" type="button" onclick="crearNivel('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
		                				'  <img src="images/plus_icon.ico" height="15"/>'+
		                				'</button>'+
			        				  '<button title="Editar cuenta" type="button" onclick="verCuentaPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
			        				  '  <img src="jtable/themes/lightcolor/edit.png" height="15"/>'+
			        				  '</button>'+
			        				  '<button title="Deshabilitar cuenta" type="button" onclick="desactivarCuenta('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
			        				  '  <img src="jtable/themes/lightcolor/delete.png" height="15"/>'+
			        				  '</button>';	
            			
            			var codigo2 = '<button type="button" onclick="verBitacoraCatalogoPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
						  			  '  <img src="images/ver_icon.png" height="10"/>'+
						  			  '</button>'+
			        				  '<button title="Editar cuenta" type="button" onclick="verCuentaPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
			        				  '  <img src="jtable/themes/lightcolor/edit.png" height="15"/>'+
			        				  '</button>';
            			
            			if(data.record.fechaDesactivacion == null){
            				// Esta Activo.. Se puede deshabilitar !!
            				return $(codigo1);
            			}else{
            				// Esta Inctivo.. NO se puede deshabilitar !!
            				return $(codigo2);
            			}
            			
            			/**
                		return $(
                				'<button type="button" onclick="verBitacoraCatalogoPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="images/ver_icon.png" height="10"/>'+
                				'</button>'+
                				'<button title="Agregar nivel" type="button" onclick="crearNivel('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="images/plus_icon.ico" height="15"/>'+
                				'</button>'+
                				'<button title="Editar cuenta" type="button" onclick="verCuentaPresup('+data.record.pkCuenta+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="jtable/themes/lightcolor/edit.png" height="15"/>'+
                				'</button>'+
                				'<button title="Deshabilitar cuenta" type="button" onclick="desactivarCuenta('+data.record.pkCuenta+','+data.record.fechaDesactivacion+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="jtable/themes/lightcolor/delete.png" height="15"/>'+
                				'</button>');         
                		*/  			
                 }
            }
            }
        },formCreated: function (event, data){
			data.form.find('input[name="codigo"]').attr('maxlength','10');
			data.form.find('input[name="codigo2"]').attr('maxlength','5');
			data.form.find('input[name="nombre"]').attr('maxlength','50');
		},formSubmitting: function (event, data) {
            var aux = data.form.validationEngine('validate');
            if (data.form.find('input[name="codigo"]').val().length==0){
            	jAlert("Debe ingresar un codigo para la comuna",'Mantenedor de Comunas');						return false;
            }
            if (data.form.find('input[name="codigo2"]').val().length==0){
            	jAlert("Debe ingresar un codigo ini para la comuna",'Mantenedor de Comunas');					return false;
            }
            if (data.form.find('input[name="nombre"]').val().length==0){
            	jAlert("Debe ingresar un nombre para la comuna",'Mantenedor de Comunas');						return false;
            }
            if ((data.form.find('input[name=padreId]').val()==-1) || (data.form.find('input[name=padreId]').val().length==0)){
            	jAlert("Debe seleccionar la provincia a la cual pertenecera la comuna",'Mantenedor de Comunas');return false;
            }
            return aux;
         },rowInserted: function (event, data) {$('#ExpenseTableContainer').jtable('selectRows', data.row);
        },recordAdded: function(event, data){	$('#ExpenseTableContainer').jtable('load', { padreId:$("#cbProvincia option:selected").val()});
        },recordUpdated: function(event, data){	$('#ExpenseTableContainer').jtable('load', { padreId:$("#cbProvincia option:selected").val()});},
	});

	$(document).on("keydown","input.numero2",function(e){
		
		if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
	             // Allow: Ctrl+A, Command+A
	            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
	             // Allow: home, end, left, right, down, up
	            (e.keyCode >= 35 && e.keyCode <= 40)) {
	                 // let it happen, don't do anything
	                 return;
	        }
	        // Ensure that it is a number and stop the keypress
	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
	            e.preventDefault();
	        }
	
	});	
});


//EDITAR PERIODOS NUEVO

function actualizarPeriodosHabilitado(){
	$('#frmEditarPeriodo').find('[type="submit"]').trigger('click');
}

function editarCuentaPeriodo(){
	
	var periodosSelected = []; 
	$('#pnPeriodos :selected').each(function(i, selected){ 
		periodosSelected[i] = $(selected).text(); 
	});
	
	$.ajax({
	    url: 'postPeriodosHabilitados?periodos='+periodosSelected.toString(),
	    type: 'POST',
	    data: null,	    
	     beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
		  },complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();

		  },success: function(data){
	    	
			  $("#popupEditarPeriodo").dialog("close");
			  
			    var ejercicio = $('#cbEjercicio').val();
				var idSubtitulo = $('#cbCtaSubtTitulo').val();
				var idItem =  $('#cbItems').val();
				var activo = $('#cbActivo').val();
			  
			  $('#ExpenseTableContainer').jtable('load', { 	ejercicio:ejercicio,idSubtitulo:idSubtitulo,idItem:idItem,activo:activo});
			  
			  jAlert('Periodos actualizados correctamente ', 'Confirmaci&oacute;n');
			  
	   	  }, error: function(XMLHttpRequest, textStatus, errorThrown){
				
			}
	});
}
