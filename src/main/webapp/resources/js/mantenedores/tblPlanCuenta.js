$( document ).ajaxStart(function() {
	$("#loading-spinner-mant").addClass("is-active");
});

$( document ).ajaxStop(function() {
	$("#loading-spinner-mant").removeClass("is-active");
});

function actualizarAsocPresupGasto(){
	$('#frmEditarGastos').find('[type="submit"]').trigger('click');
}

function actualizarAsocPresupIngre(){
	$('#frmEditarIngresos').find('[type="submit"]').trigger('click');
}

function cerrarEditCtaPresup(){
	$("#popupEditarCtasGastos").dialog("close");
}
function editarCuentaGastos(){
	
	var pnCtasGastosSelected = []; 
	$('#pnCtasGastos :selected').each(function(i, selected){ 
		pnCtasGastosSelected[i] = $(selected).text(); 
	});
	
	$.ajax({
	    url: 'editarCuentaGastos?pnCtasGastosSelected='+pnCtasGastosSelected.toString(),
	    type: 'POST',
	    data: null
		,success: function(data){
	    	
			  $("#popupEditarCtasGastos").dialog("close");

			  var ejercicioSelected = $('#cbEjercicio').val();
			  var titleSelected = $('#cbCtaContTitulo').val();
			  var grupoSelected = $('#cbCtaContGrupo').val();
			  var subGrupoSelected = $('#cbCtaContSubGrupo').val();

			  $('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicioSelected,
	    													idTitulo:titleSelected,
	    													idGrupo:grupoSelected,
	    													idSubGrupo:subGrupoSelected});	
			  
			  jAlert('Periodos actualizados correctamente ', 'Confirmaci&oacute;n');
	    	
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				
			}
	});
}

function editarCuentaIngresos(){
	
	var pnCtasIngresosSelected = []; 
	$('#pnCtasIngresos :selected').each(function(i, selected){ 
		pnCtasIngresosSelected[i] = $(selected).text(); 
	});
	
	$.ajax({
	    url: 'editarCuentaIngresos?pnCtasIngresosSelected='+pnCtasIngresosSelected.toString(),
	    type: 'POST',
	    data: null,
		success: function(data){
	    	
			  $("#popupEditarCtasIngresos").dialog("close");

			  var ejercicioSelected = $('#cbEjercicio').val();
			  var titleSelected = $('#cbCtaContTitulo').val();
			  var grupoSelected = $('#cbCtaContGrupo').val();
			  var subGrupoSelected = $('#cbCtaContSubGrupo').val();

			  $('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicioSelected,
	    													idTitulo:titleSelected,
	    													idGrupo:grupoSelected,
	    													idSubGrupo:subGrupoSelected});	
			  
			  jAlert('Periodos actualizados correctamente ', 'Confirmaci&oacute;n');
	    	
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				
			}
	});
}

function openPopupGastos(idTabla1, idTabla2){

	var param = 'idTabla1='+idTabla1+'&idTabla2='+ idTabla2;
	
	$.ajax({
		  type: "GET",
		  url: "getCtaContable?"+param,
		  success: function(data){
			  
				$("#pnCtasGastos").get(0).options.length = 0;
				
			    data.ctasPresupuestariasVO.listCtasPresupDeGastos.forEach(function (item) {
					$("#pnCtasGastos").get(0).options[$("#pnCtasGastos").get(0).options.length] 
						= new Option(item.cuenta , item.cuenta );					
				});   
			     
			    if(data.cuentaContableDetalle.asociacionGasto != null ){
				    data.cuentaContableDetalle.asociacionGasto.split(",").forEach(function (item) {

				    	$("#pnCtasGastos option[value='"+$.trim(item)+"']").prop("selected", true);
					}); 			    	
			    }

		  }
	});	  
	
	var opt = {
	        autoOpen: true,
	        modal: true,
	        width: 305,
	        show: "blind",
	        title : "Editar Cuentas de Gastos"
	};
	
	var popUp = $("#popupEditarCtasGastos").dialog(opt);
	popUp.dialog("open");
	
	$(".ui-widget-overlay").addClass("overlay" ); 
	$(".overlay" ).removeClass("ui-widget-overlay" );
	
	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	
}

function openPopupIngresos(idTabla1, idTabla2){

	var param = 'idTabla1='+idTabla1+'&idTabla2='+ idTabla2;
	
	$.ajax({
		  type: "GET",
		  url: "getCtaContable?"+param,
		  success: function(data){
			  
				$("#pnCtasIngresos").get(0).options.length = 0;				
				data.ctasPresupuestariasVO.listCtasPresupDeIngreso.forEach(function (item) {
					
					$("#pnCtasIngresos").get(0).options[$("#pnCtasIngresos").get(0).options.length] 
							= new Option(item.cuenta , item.cuenta );	

				}); 
				
				if(data.cuentaContableDetalle.asociacionIngreso != null){
				    data.cuentaContableDetalle.asociacionIngreso.split(",").forEach(function (item) {
				    	$("#pnCtasIngresos option[value='"+$.trim(item)+"']").prop("selected", true);
					});  					
				}
    
		  }
	});	  
	
	var opt = {
	        autoOpen: true,
	        modal: true,
	        width: 305,
	        show: "blind",
	        title : "Editar Cuentas de Ingresos"
	};
	
	var popUp = $("#popupEditarCtasIngresos").dialog(opt);
	popUp.dialog("open");
	
	$(".ui-widget-overlay").addClass("overlay" ); 
	$(".overlay" ).removeClass("ui-widget-overlay" );
	
	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	
}

function actualizarPeriodosHabilitado(){
	$('#frmEditarPeriodo').find('[type="submit"]').trigger('click');
}

function cerrarPopupEditPeriodos(){
	$("#popupEditarPeriodo").dialog("close");
}

function editarCuentaPeriodo(){
	
	var periodosSelected = []; 
	$('#pnPeriodos :selected').each(function(i, selected){ 
		periodosSelected[i] = $(selected).text(); 
	});
	
	$.ajax({
	    url: 'postPeriodosHabilitado?periodos='+periodosSelected.toString(),
	    type: 'POST',
	    data: null,
		success: function(data){
	    	
			  $("#popupEditarPeriodo").dialog("close");

			  var ejercicioSelected = $('#cbEjercicio').val();
			  var titleSelected = $('#cbCtaContTitulo').val();
			  var grupoSelected = $('#cbCtaContGrupo').val();
			  var subGrupoSelected = $('#cbCtaContSubGrupo').val();

			  $('#ExpenseTableContainer').jtable('load', {  ejercicio:ejercicioSelected,
	    													idTitulo:titleSelected,
	    													idGrupo:grupoSelected,
	    													idSubGrupo:subGrupoSelected});	
			  
			  jAlert('Periodos actualizados correctamente ', 'Confirmaci&oacute;n');
	    	
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				
			}
	});
}

function openPopupPeriodo(idTabla1, idTabla2){

	var param = 'idTabla1='+idTabla1+'&idTabla2='+ idTabla2;
	
	$.ajax({
		  type: "GET",
		  url: "getCtaContable?"+param,
		  success: function(data){
				$("#pnPeriodos").get(0).options.length = 0;
				console.log(data);
				
			    data.listPeriodos.forEach(function (item) {
					$("#pnPeriodos").get(0).options[$("#pnPeriodos").get(0).options.length] 
						= new Option(item.periodoNombre , item.periodoNombre );
				});   

//			    data.cuentaContableDetalle.periodidosHabilitados.split(",").forEach(function (item) {
//			    	$('#pnPeriodos option:eq('+$.trim(item)+')').prop('selected',false);	
//				}); 
			    
//			    if(data.cuentaContableDetalle.asociacionIngreso != null){			    
			    data.cuentaContableDetalle.periodidosHabilitados.split(",").forEach(function (item) {
			    	$("#pnPeriodos option[value='"+$.trim(item)+"']").prop("selected", true);	
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
	
}

function cerrar(){
	$("#popupCrearNivel").dialog("close");
}


function completeAndRedirect(){

	var data = $('#frmCrearNivel').serialize();
	console.log("completeAndRedirect - data: "+data);
	
	$.ajax({
	    url: 'crearNuevoNivel',
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
	    	
			  //if (data.codResp === -1 ){
			  
			  if (data.respuestaPair.codido < 0){ 
				  jAlert(data.respuestaPair.mensaje, 'Alerta');
				  
			  } else {
				  
				  var popUp = $("#popupCrearNivel").dialog("close");
				  
				  jAlert("La cuenta fue creada exitosamente.", 'Confirmaci&oacute;n');
				  
				  var ejercicioSelected = $('#cbEjercicio').val();
				  var titleSelected = $('#cbCtaContTitulo').val();
				  var grupoSelected = $('#cbCtaContGrupo').val();
				  var subGrupoSelected = $('#cbCtaContSubGrupo').val();
				  
				  
				  $('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicioSelected,
		    													idTitulo:titleSelected,
		    													idGrupo:grupoSelected,
		    													idSubGrupo:subGrupoSelected});			  
			  }

	    	
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				
			}
	});
}


function login()
{   
	$('#frmCrearNivel').find('[type="submit"]').trigger('click');
}


function getTitulosPlanCuenta(){

	action='getTitulosPlanCuenta?ejercicio='+$('#cbEjercicio').val();
	
	$("#cbCtaContTitulo").get(0).options.length = 0;
    $("#cbCtaContTitulo").get(0).options[0] = new Option("Leyendo titulos", "-1"); 

	$('#cbCtaContGrupo').empty();
	$("#cbCtaContGrupo").get(0).options.length = 0;
    $("#cbCtaContGrupo").get(0).options[0] = new Option("Select", "-1"); 
    
	$('#cbCtaContSubGrupo').empty();
	$("#cbCtaContSubGrupo").get(0).options.length = 0;
    $("#cbCtaContSubGrupo").get(0).options[0] = new Option("Select", "-1"); 
    
	$.ajax({url: action, type: "GET", dataType:'json',
		
		beforeSend: function ( data ) {
			console.log("beforeSend");
			
		},
		complete: function ( data ) {
			console.log("complete");
			
		},	
		success: function(data){
			
			$('#cbCtaContTitulo').empty();
			 $("#cbCtaContTitulo").get(0).options.length = 0;
             $("#cbCtaContTitulo").get(0).options[0] = new Option("Select", "-1"); 
             
			$.each(data, function(i, item) {
		    	
		    	var idCtaContable 	= item["idCtaContable"];
		        var codTituloCta	= item["codTituloCta"];
		        var desTituloCta	= item["desTituloCta"];
		        
		        console.log(idCtaContable + " "+codTituloCta +" "+desTituloCta);
		        
		        $("#cbCtaContTitulo").get(0).options[$("#cbCtaContTitulo").get(0).options.length] = new Option(codTituloCta+" "+desTituloCta,codTituloCta );

			});
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('error');
		}
	});
}


function getSubGruposPlanCuenta(){
	action='getSubGruposPlanCuenta?ejercicio='+$('#cbEjercicio').val()
							+'&idTitulo='+$('#cbCtaContTitulo').val()
							+'&idGrupo='+$('#cbCtaContGrupo').val();	

	$("#cbCtaContSubGrupo").get(0).options.length = 0;
    $("#cbCtaContSubGrupo").get(0).options[0] = new Option("Leyendo Subgrupos", "-1"); 
    
	$.ajax({url: action, type: "GET", dataType:'json',
		
		beforeSend: function ( data ) {
			console.log("beforeSend");
			
		},
		complete: function ( data ) {
			console.log("complete");
			
		},	
		success: function(data){
			$('#cbCtaContSubGrupo').empty();
			$("#cbCtaContSubGrupo").get(0).options.length = 0;
            $("#cbCtaContSubGrupo").get(0).options[0] = new Option("Select", "-1"); 
            
			$.each(data, function(i, item) {
		    	var idCtaContable 	= item["idCtaContable"];
		        var codSubGrupoCta	= item["codSubGrupoCta"];
		        var desSubGrupoCta	= item["desSubGrupoCta"];		        
		        $("#cbCtaContSubGrupo").get(0).options[$("#cbCtaContSubGrupo").get(0).options.length] = new Option(codSubGrupoCta+" "+desSubGrupoCta,codSubGrupoCta );
			});
			
			getPlanCuentasContable();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('error');
		}
	});
	
}

function getGruposPlanCuenta(){
	action='getGruposPlanCuenta?ejercicio='+$('#cbEjercicio').val()+'&idTitulo='+$('#cbCtaContTitulo').val();
	
	$("#cbCtaContGrupo").get(0).options.length = 0;
    $("#cbCtaContGrupo").get(0).options[0] = new Option("Leyendo Grupos", "-1"); 

	$('#cbCtaContSubGrupo').empty();
	$("#cbCtaContSubGrupo").get(0).options.length = 0;
    $("#cbCtaContSubGrupo").get(0).options[0] = new Option("Select", "-1"); 
    
	$.ajax({url: action, type: "GET", dataType:'json',
		
		beforeSend: function ( data ) {
			console.log("beforeSend");
		},
		complete: function ( data ) {
			console.log("complete");			
		},	
		success: function(data){
			
			$('#cbCtaContGrupo').empty();
			 $("#cbCtaContGrupo").get(0).options.length = 0;
             $("#cbCtaContGrupo").get(0).options[0] = new Option("Select", "-1"); 
             
			$.each(data, function(i, item) {
		    	
		    	var idCtaContable 	= item["idCtaContable"];
		        var codGrupooCta	= item["codGrupooCta"];
		        var desGrupoCta	= item["desGrupoCta"];
		        
		        console.log(idCtaContable + " "+codGrupooCta +" "+desGrupoCta);
		        
		        $("#cbCtaContGrupo").get(0).options[$("#cbCtaContGrupo").get(0).options.length] = new Option(codGrupooCta+" "+desGrupoCta,codGrupooCta );

			});
			
			getPlanCuentasContable();
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('error');
		}
	});
}


function getPlanCuentasContable(){
	
	console.log("getPlanCuentasContable");
	
	var ejercicio = $('#cbEjercicio').val();
	console.log("getPlanCuentasContable - ejercicio: "+ejercicio);
	
	var idTitulo = $('#cbCtaContTitulo').val();
	console.log("getPlanCuentasContable - idTitulo: "+idTitulo);
	
	var idGrupo = $('#cbCtaContGrupo').val();
	console.log("getPlanCuentasContable - idGrupo: "+idGrupo);
	
	var idSubGrupo = $('#cbCtaContSubGrupo').val();
	console.log("getPlanCuentasContable - idSubGrupo: "+idSubGrupo);
	
	var isActivo = $('#cbActivo').val();
	console.log("getPlanCuentasContable - isActivo: "+isActivo);
	
	$('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicio,idTitulo:idTitulo,idGrupo:idGrupo,idSubGrupo:idSubGrupo,isActivo:isActivo});
	
} 


function crearNivel(idTabla1,idTabla2){

	var param = '?idTabla1='+idTabla1+'&idTabla2='+ idTabla2;
	console.log(2);
	
	$.ajax({
		  type: "GET",
		  url: "getCtaContable"+param,

		  dataType: "json",
		  beforeSend: function (xhr) {
			  $("#pnN1").val("");
			  $("#pnN2").val("");
			  $("#pnN3").val("");
			  $("#pnN4").val("");
			  $("#descripcion").val("");
			  $('#pntipoSaldoDeudor :checked').removeAttr('checked');
			  $('#pntipoSaldoAcreedor :checked').removeAttr('checked');
			  
			  
			  $('input[name=pnNaturaleza]').attr('checked',false);
			  $('input[name=pnClasificacion]').attr('checked',false);
			  $('input[name=pnAux]').attr('checked',false);
			  $('input[name=pnImp]').attr('checked',false);
			  
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					 '<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					 '</div>');
		  },complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
 
		  },success: function(data){
		    	
			  $("#nnEjercicio").val( $("#cbEjercicio :selected").text() ); 
			  
			  var padre = data.cuentaContableDetalle.agregacion +"."+
						  data.cuentaContableDetalle.codPrimerNivel +"."+
						  data.cuentaContableDetalle.codSegundoNivel +"."+
						  data.cuentaContableDetalle.codTercerNivel +" "+ 
						  data.cuentaContableDetalle.descripcion ;
			  
			  $("#pnDesc").val(padre);
			  
			  if("00" === data.cuentaContableDetalle.codPrimerNivel //Add N1
					  && "00" === data.cuentaContableDetalle.codSegundoNivel ){
//				  alert("if 1");
				  document.getElementById("pnN1").disabled = false;
				  document.getElementById("pnN2").disabled = true;
				  document.getElementById("pnN3").disabled = true;
//				  document.getElementById("pnN4").disabled = true;

			  }if("00" === data.cuentaContableDetalle.codSegundoNivel // add N2
					  && "00" === data.cuentaContableDetalle.codTercerNivel
					  	&& "00" !== data.cuentaContableDetalle.codPrimerNivel){
//				  alert("if 2");

				  document.getElementById("pnN1").disabled = true; $("#pnN1").val(data.cuentaContableDetalle.codPrimerNivel);
				  document.getElementById("pnN2").disabled = false;
				  document.getElementById("pnN3").disabled = true; 
//				  document.getElementById("pnN4").disabled = true; 

			  }if("00" !== data.cuentaContableDetalle.codSegundoNivel // add N3
					 && "00" !== data.cuentaContableDetalle.codPrimerNivel){
//				  alert("if 3");
				  document.getElementById("pnN1").disabled = true; $("#pnN1").val(data.cuentaContableDetalle.codPrimerNivel);
				  document.getElementById("pnN2").disabled = true; $("#pnN2").val(data.cuentaContableDetalle.codSegundoNivel);
				  document.getElementById("pnN3").disabled = false;
//				  document.getElementById("pnN4").disabled = true;
				  
//			  }if("00" !== data.cuentaContableDetalle.codSegundoNivel // add N4
//					  && "000" !== data.cuentaContableDetalle.codTercerNivel
//					  	&& "00" !== data.cuentaContableDetalle.codPrimerNivel){
//				  alert("if 4");
//				  document.getElementById("pnN1").disabled = true; $("#pnN1").val(data.cuentaContableDetalle.codPrimerNivel);
//				  document.getElementById("pnN2").disabled = true; $("#pnN2").val(data.cuentaContableDetalle.codSegundoNivel);
//				  document.getElementById("pnN3").disabled = true; $("#pnN3").val(data.cuentaContableDetalle.codTercerNivel);
//				  document.getElementById("pnN4").disabled = false;			 

			  }
			  
			  
				var opt = {
				        autoOpen: true,
				        modal: true,
				        width: 705,
				        show: "blind",
				        title : "Agregar Nivel"
				};
				
				var popUp = $("#popupCrearNivel").dialog(opt);
				popUp.dialog("open");
				
				$(".ui-widget-overlay").addClass("overlay" ); 
				$(".overlay" ).removeClass("ui-widget-overlay" );
				
				$(".ui-icon-closethick").css('background-position', '-32px -192px');
				$(".ui-icon-closethick").css('background-color', '#F2F2F2');
		
		  }
	});
}

$(document).ready(function() {

	var rutaImagenes = $('#rutaImagenes').val();
	var rutaJtable = $('#rutaJtable').val();

	$('#ExpenseTableContainer').jtable({
		title: 'Tabla de Plan Cuentas',
		selecting: true, //Enable selecting 
		paging: true, //Enable paging
		pageSize: 10, //Set page size (default: 10)
		sorting: false, //Enable sorting
		actions: {
			listAction: 'getPlanCuentasContable',
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
			idFather:	{	title: '3',key: true,list: false,create: false,edit:false,width: '2%'},
			agregacion: {
            	title: 'Agr',
                width: '4%'
            },
            codPrimerNivel: {
            	title: 'N1',
                width: '3%'
            },
            codSegundoNivel: {
            	title: 'N2',
                width: '3%'
            },
            codTercerNivel: {
            	title: 'N3',
                width: '4%'
            },
            descripcion: {
            	title: 'Descripci&oacute;n',
                width: '10%'
            },
            asociacionGasto: {
            	title: 'Gasto',
                width: '6%',
            	display: function (data) {
            		var idTabla1 = data.record.idTabla1;
            		var idTabla2 = data.record.idTabla2;
            		
            		if (!idTabla1.trim()) {idTabla1 = "null";}
            		if (!idTabla2.trim()) {idTabla2 = "null";}
            		
            		if((data.record.asociacionGasto)){
            			var $img = $('<a title="'+ data.record.asociacionGasto +'" href="javascript: void(0);" onclick="openPopupGastos('+idTabla1+','+idTabla2+')">'+ data.record.asociacionGasto +'</a>');           
            			$img.tooltip();
                		return $img;
                	} else {
            			var $img = $('<a title="Agregar" href="javascript: void(0);" onclick="openPopupGastos('+idTabla1+','+idTabla2+')">Agregar</a>');             		               		
            			$img.tooltip();
                		return $img;
            		}
                }
            },
            asociacionIngreso: {
            	title: 'Ingreso',
                width: '6%',
                display: function (data) {
                	var idTabla1 = data.record.idTabla1;
            		var idTabla2 = data.record.idTabla2;
            		
            		if (!idTabla1.trim()) {idTabla1 = "null";}
            		if (!idTabla2.trim()) {idTabla2 = "null";}
      		
            		if(!(data.record.asociacionIngreso)){
            			var $img = $('<a title="Agregar" href="javascript: void(0);" onclick="openPopupIngresos('+idTabla1+','+idTabla2+')">Agregar</a>');
                		$img.tooltip();
                		return $img;
                	} else {
                		var $img = $('<a title="'+ data.record.asociacionIngreso +'" href="javascript: void(0);" onclick="openPopupIngresos('+idTabla1+','+idTabla2+')">'+ data.record.asociacionIngreso +'</a>');
                		$img.tooltip();
                		return $img;
                	}

                }
            },
            usoAuxiliar: {
            	title: 'Aux',
                width: '6%'
            },
            imputacionPresup: {
               	title: 'Imp',
                width: '6%',
                display: function (data) {
                	if(data.record.imputacionPresup == '0'){
                		return $('<label >NO</label>');
                	} else if(data.record.imputacionPresup == '1') {
                		return $('<label >SI</label>');
                	} else {
                		return $('<label >NOSE</label>');
                	}
                }
            },
            cartera: {
            	title: 'Cartera',
                width: '6%'
            },
            clasificacion: {
            	title: 'Clas',
                width: '6%'
            },
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
            },
            tipoSaldo: {
            	title: 'T Saldo',
                width: '6%'
            },fechaDesactivacion: {
            	title: 'Activo',
                width: '6%',
                display: function (data) {
                	if(data.record.fechaDesactivacion !== ''){
                		return $('<label title="Inactivo" >Inactivo</label>');
                	} else {
                		return $('<label title="Activo" >Activo</label>');
                	}
                }
            },
            add_nivel: {
            	title: 'Opc.',
            	width: '20%',
            	display: function (data) {
            		
            		var idTabla1 = data.record.idTabla1;
            		var idTabla2 = data.record.idTabla2;
            		
            		if (!idTabla1.trim()) {
            			idTabla1 = "null";
            		}if (!idTabla2.trim()) {
            			idTabla2 = "null";
            		}
            		if ("00" != data.record.codTercerNivel){
            			if(data.record.fechaDesactivacion !== ''){
            				return $(
                    				'<button type="button" onclick="verBitacoraPlanCuenta('+idTabla1+','+idTabla2+'); return false;" style="width: 15px; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaImagenes + '/ver_icon.png" height="10"/>'+
                    				'</button>'+
                    				'<button title="Agregar nivel" type="button" onclick="crearNivel('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaImagenes + '/plus_icon.ico" height="15"/>'+
                    				'</button>'+
                    				'<button title="Editar cuenta" type="button" onclick="editarCuentaContable('+idTabla1+','+idTabla2+'); return false;" style="margin-left: 15px; width: 15px; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaJtable + '/themes/lightcolor/edit.png" height="15"/>'+
                    				'</button>');     
            			}else{
            				return $(
                    				'<button type="button" onclick="verBitacoraPlanCuenta('+idTabla1+','+idTabla2+'); return false;" style="width: 15px; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaImagenes + '/ver_icon.png" height="10"/>'+
                    				'</button>'+
//                    				'<button title="Agregar nivel" type="button" onclick="crearNivel('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
//                    				'  <img src="images/plus_icon.ico" height="15"/>'+
//                    				'</button>'+
                    				'<button title="Editar cuenta" type="button" onclick="editarCuentaContable('+idTabla1+','+idTabla2+'); return false;" style="margin-left: 15px; width: 15px; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaJtable + '/themes/lightcolor/edit.png" height="15"/>'+
                    				'</button>'+
                    				'<button title="Deshabilitar cuenta" type="button" onclick="desactivarCuenta('+idTabla1+','+idTabla2+'); return false;" style="margin-left: 15px; width: 15px; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaJtable + '/themes/lightcolor/delete.png" height="15"/>'+
                    				'</button>');     
            			}
            			/**
                		return $(
                				'<button type="button" onclick="verBitacoraPlanCuenta('+idTabla1+','+idTabla2+'); return false;" style="width: 15px; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="images/ver_icon.png" height="10"/>'+
                				'</button>'+
                				'<button title="Agregar nivel" type="button" onclick="crearNivel('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="images/plus_icon.ico" height="15"/>'+
                				'</button>'+
                				'<button title="Editar cuenta" type="button" onclick="editarCuentaContable('+idTabla1+','+idTabla2+'); return false;" style="margin-left: 15px; width: 15px; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="jtable/themes/lightcolor/edit.png" height="15"/>'+
                				'</button>'+
                				'<button title="Deshabilitar cuenta" type="button" onclick="desactivarCuenta('+idTabla1+','+idTabla2+'); return false;" style="margin-left: 15px; width: 15px; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="jtable/themes/lightcolor/delete.png" height="15"/>'+
                				'</button>');     
                		*/       			
            		} else {
            			if(data.record.fechaDesactivacion !== ''){
            				return $(
                    				'<button type="button" onclick="verBitacoraPlanCuenta('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaImagenes + '/ver_icon.png" height="10"/>'+
                    				'</button>'+
                    				'<button title="Agregar nivel" type="button" onclick="crearNivel('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaImagenes + '/plus_icon.ico" height="15"/>'+
                    				'</button>'+
                    				'<button title="Editar cuenta" type="button" onclick="editarCuentaContable('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaJtable + '/themes/lightcolor/edit.png" height="15"/>'+
                    				'</button>');       
            			}else{
            				return $(
                    				'<button type="button" onclick="verBitacoraPlanCuenta('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaImagenes + '/ver_icon.png" height="10"/>'+
                    				'</button>'+
                    				'<button title="Agregar nivel" type="button" onclick="crearNivel('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaImagenes + '/plus_icon.ico" height="15"/>'+
                    				'</button>'+
                    				'<button title="Editar cuenta" type="button" onclick="editarCuentaContable('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaJtable + '/themes/lightcolor/edit.png" height="15"/>'+
                    				'</button>'+
                    				'<button title="Deshabilitar cuenta" type="button" onclick="desactivarCuenta('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                    				'  <img src="' + rutaJtable + '/themes/lightcolor/delete.png" height="15"/>'+
                    				'</button>');       
            			}
            			
            			/**
                		return $(
                				'<button type="button" onclick="verBitacoraPlanCuenta('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="images/ver_icon.png" height="10"/>'+
                				'</button>'+
                				'<button title="Agregar nivel" type="button" onclick="crearNivel('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="images/plus_icon.ico" height="15"/>'+
                				'</button>'+
                				'<button title="Editar cuenta" type="button" onclick="editarCuentaContable('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
                				'  <img src="jtable/themes/lightcolor/edit.png" height="15"/>'+
                				'</button>'+
                				'<button title="Deshabilitar cuenta" type="button" onclick="desactivarCuenta('+idTabla1+','+idTabla2+'); return false;" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"> '+
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
	
});
