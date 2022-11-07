function cerrarDesactivacion(){
	$("#popupDesactivarCuenta").dialog("close");
}
function cerrarEdicion(){
	$("#popupEditarCuenta").dialog("close");
}
function editarCuenta(){

	var data = $('#frmEditarCuenta').serialize();
	
	$.ajax({
	    url: 'editarCuentaContable',
	    type: 'POST',
	    data: data,
		success: function(data){
	    	
			  if (data.codResp === -1 ){
				  jAlert('No se ha editado', 'Error');
				  
			  } if (data.codResp === -2 ){
				  jAlert('Descripci&oacute;n cuenta no puede ser vac&iacute;o', 'Alerta');
				  
			  }else {
					  
				  var popUp = $("#popupEditarCuenta").dialog("close");
				  
				  var ejercicio = $('#cbEjercicio').val();
				  var idTitulo = $('#cbCtaContTitulo').val();
				  var idGrupo = $('#cbCtaContGrupo').val();
				  var idSubGrupo = $('#cbCtaContSubGrupo').val();
				  var isActivo = $('#cbActivo').val();
					
				  $('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicio,idTitulo:idTitulo,idGrupo:idGrupo,idSubGrupo:idSubGrupo, isActivo:isActivo});					
			  }    	
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				
			}
	});
}

function actualizarCuenta(){   
	$('#frmEditarCuenta').find('[type="submit"]').trigger('click');
}

function postDelete2(){   

	$.ajax({
		  type: "GET",
		  url: "postDesactivarCuentasContables2",

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
			  	$("#popupDesactivarCuenta").dialog("close");
				jAlert('Registros desactivados correctamente', 'Confirmaci&oacute;n');
				
				$('#ExpenseTableContainer').jtable('load', 
						{ 
						  ejercicio:$('#cbEjercicio').val(),
						  idTitulo:$('#cbCtaContTitulo').val(),
						  idGrupo:$('#cbCtaContGrupo').val(),
						  idSubGrupo: $('#cbCtaContSubGrupo').val(), 
						  isActivo:$('#cbActivo').val()
						});
		  }
	});
}

function deleteCuenta2(){  //OK  
	$('#frmDesactivarCuenta2').find('[type="submit"]').trigger('click');
}

function verBitacoraPlanCuenta(idTabla1,idTabla2){
	
	var param = '?idTabla1='+idTabla1+'&idTabla2='+ idTabla2;
	
	$.ajax({
		  type: "GET",
		  url: "verBitacoraPlanCuenta"+param,
		  dataType: "json",
		  success: function(data){
				
			  var tbla = "";
			  $('#tblDesactivarCtasCtables  > tbody').empty();
				
			  jQuery.each(data, function(i, val) {
					tbla = tbla + "<tr><td>" +val.estado + "</td><td>" + val.fecha + "</td><td>" + val.usuario + "</td></tr>"
			   });
				
			  $('#tblDesactivarCtasCtables > tbody').html(tbla);

			  var opt = {
				        autoOpen: true,
				        modal: true,
				        width: 500,
				        show: "blind",
				        title : "Bitácora de Actualizaciones"
			  };
				
			  var popUp = $("#popupBitacoraCuenta").dialog(opt);
			  popUp.dialog("open");
				
			  $(".ui-widget-overlay").addClass("overlay" ); 
			  $(".overlay" ).removeClass("ui-widget-overlay" );
				
			  $(".ui-icon-closethick").css('background-position', '-32px -192px');
			  $(".ui-icon-closethick").css('background-color', '#F2F2F2');
			  
		  }
	});
}
function desactivarCuenta(idTabla1,idTabla2){

	var param = '?idTabla1='+idTabla1+'&idTabla2='+ idTabla2;
	
	$.ajax({
		  type: "GET",
		  url: "getCtaContableByDesactivar"+param,

		  dataType: "json",
		success: function(data){
		    
			try {
				var tbla = "";
				$('#tblDesactivarCtasCtables  > tbody').empty();
				
				$.each(data.listCtasContablesSimple, function(i, item) {  
					tbla = tbla + "<tr><td>" +item['agregacion']+ "."+
					 	item['n1']+ "."+
					 	item['n2']+ "."+
					 	item['n3']+
						"</td>" + "<td>" +item['descripcion']+ "</td></tr>"
			    });
				
				$('#tblDesactivarCtasCtables > tbody').html(tbla);
					
				var padre = data.cuentaContableDetalle.agregacion +"."+
				  data.cuentaContableDetalle.codPrimerNivel +"."+
				  data.cuentaContableDetalle.codSegundoNivel +"."+
				  data.cuentaContableDetalle.codTercerNivel;
				
				  var opt = {
					        autoOpen: true,
					        modal: true,
					        width: 500,
					        show: "blind",
					        title : "Alerta"
				  };
					
				  var popUp = $("#popupDesactivarCuenta").dialog(opt);
				  popUp.dialog("open");
					
				  $(".ui-widget-overlay").addClass("overlay" ); 
				  $(".overlay" ).removeClass("ui-widget-overlay" );
					
				  $(".ui-icon-closethick").css('background-position', '-32px -192px');
				  $(".ui-icon-closethick").css('background-color', '#F2F2F2');
			}
			catch(err) {
				    alert (err.message);
			}
		  }
	});
}

function editarCuentaContable(idTabla1,idTabla2){

	var param = '?idTabla1='+idTabla1+'&idTabla2='+ idTabla2;

	
	$.ajax({
		  type: "GET",
		  url: "getCtaContable"+param,

		  dataType: "json",
		  success: function(data){
		    
			try {
				  var padre = data.cuentaContableDetalle.agregacion +"."+
				  data.cuentaContableDetalle.codPrimerNivel +"."+
				  data.cuentaContableDetalle.codSegundoNivel +"."+
				  data.cuentaContableDetalle.codTercerNivel +" "+ 
				  data.cuentaContableDetalle.descripcion ;
	  
				  $("#nvlAgr").val(data.cuentaContableDetalle.agregacion);
				  $("#editN1").val(data.cuentaContableDetalle.codPrimerNivel);
				  $("#editN2").val(data.cuentaContableDetalle.codSegundoNivel);
				  $("#editN3").val(data.cuentaContableDetalle.codTercerNivel);
				  $("#editDescripcion").val(data.cuentaContableDetalle.descripcion);
				  
				  $("#edEjercicio").val( $('#cbEjercicio option:selected').text() );
				  
				  if (data.cuentaContableDetalle.imputacionPresup != null && 
						  data.cuentaContableDetalle.imputacionPresup == '1')
				  {
					  $("#imp-si").prop('checked',true);
				  } else {  $("#imp-no").prop('checked',true); }
				  
				  if (data.cuentaContableDetalle.usoAuxiliar != null && 
						  data.cuentaContableDetalle.usoAuxiliar == 'SI')
				  {
					  $("#aux-si").prop('checked',true);
				  } else {  $("#aux-no").prop('checked',true); }
				  
				  if (data.cuentaContableDetalle.clasificacion!=null && data.cuentaContableDetalle.clasificacion == 'Activo'){ $("#clas-act").prop('checked',true); }
				  if (data.cuentaContableDetalle.clasificacion!=null && data.cuentaContableDetalle.clasificacion == 'Pasivo'){ $("#clas-pas").prop('checked',true); }
				  if (data.cuentaContableDetalle.clasificacion!=null && data.cuentaContableDetalle.clasificacion == 'Patrimonio'){ $("#clas-pat").prop('checked',true); }
				  if (data.cuentaContableDetalle.clasificacion!=null && data.cuentaContableDetalle.clasificacion == 'Ingreso Patromonial'){ $("#clas-ing").prop('checked',true); }
				  if (data.cuentaContableDetalle.clasificacion!=null && data.cuentaContableDetalle.clasificacion == 'Gasto Patrimonial '){ $("#clas-gas").prop('checked',true); }
			
				  if (data.cuentaContableDetalle.cartera != null && data.cuentaContableDetalle.cartera == 'Financiera'){ $("#cart-fin").prop('checked',true); }
				  if (data.cuentaContableDetalle.cartera != null && data.cuentaContableDetalle.cartera == 'Bancaria'){ $("#cart-ban").prop('checked',true); }
				  if (data.cuentaContableDetalle.cartera != null &&	data.cuentaContableDetalle.cartera == 'Bienes'){ $("#cart-bie").prop('checked',true); }
			
				  $("#sdo-deudor").prop('checked',false); 
				  $("#sdo-acreedor").prop('checked',false);
				  
				  if (data.cuentaContableDetalle.tipoSaldo != null &&
						  data.cuentaContableDetalle.tipoSaldo.includes('Deudor')){
					  $("#sdo-deudor").prop('checked',true); 
				  }
				  if (data.cuentaContableDetalle.tipoSaldo != null &&
						  data.cuentaContableDetalle.tipoSaldo.includes('Acreedor')){
					  $("#sdo-acreedor").prop('checked',true); 
				  }
				  
					var opt = {
					        autoOpen: true,
					        modal: true,
					        width: 705,
					        show: "blind",
					        title : "Editar Cuenta"
					};
					
					var popUp = $("#popupEditarCuenta").dialog(opt);
					popUp.dialog("open");
					
					$(".ui-widget-overlay").addClass("overlay" ); 
					$(".overlay" ).removeClass("ui-widget-overlay" );
					
					$(".ui-icon-closethick").css('background-position', '-32px -192px');
					$(".ui-icon-closethick").css('background-color', '#F2F2F2');
			}
			catch(err) {
				    alert (err.message);
			}
				

		
		  }
	});
}
