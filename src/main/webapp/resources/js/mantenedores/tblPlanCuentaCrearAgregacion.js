function openPopup(){
	
	  var idEjericio = $('#cbEjercicio').val();
	  
	  if (idEjericio == -1){
		  
		  jAlert('Seleccione un Ejercicio', 'Alerta');
		  
	  } else {
		  
		  $("#agrEjericio").val( $('#cbEjercicio option:selected').text() );
		  var opt = {
			        autoOpen: true,
			        modal: true,
			        width: 600,
			        show: "blind",
			        title : "Crear Agregación"
		  };
			
		  $("#agrTit").val("");
		  $("#agrGru").val("");
		  $("#agrSub").val("");
		  $("#agrDesc").val("");
		  
		  $('input[name=pnNaturaleza]').attr('checked',false);
		  $('input[name=pnClasificacion]').attr('checked',false);
		  $('input[name=pnAux]').attr('checked',false);
		  $('input[name=pnImp]').attr('checked',false);
		  
		  /*$("#agrTit").val();
		  $("#agrTit").val();
		  $("#agrTit").val();
		  $("#agrTit").val();
		  $("#agrTit").val();*/
		  
		  var popUp = $("#popupCrearAgregacion").dialog(opt);
		  popUp.dialog("open");
			
		  $(".ui-widget-overlay").addClass("overlay" ); 
		  $(".overlay" ).removeClass("ui-widget-overlay" );
			
		  $(".ui-icon-closethick").css('background-position', '-32px -192px');
		  $(".ui-icon-closethick").css('background-color', '#F2F2F2');
		  
	  }
	  

}

function cerrarCreacion(){
	$("#popupCrearAgregacion").dialog("close");
}

function crearAgregacion(){

	var data = $('#frmCrearAgregacion').serialize();

	$.ajax({
	    url: 'postAgregacion',
	    type: 'POST',
	    data: data
		,success: function(data){
			  if (data.codido < 0){ // codido = -1 Indica Error: No se ha creado el registro
				 
				  jAlert(data.mensaje, 'Alerta');

			  } else {
				 
				  getTitulosPlanCuenta();
				  
				  $("#popupCrearAgregacion").dialog("close");
				  
				  jAlert("La cuenta fue creada exitosamente.", 'Confirmación');
				  
				  $('#ExpenseTableContainer').jtable('load', { ejercicio:$('#cbEjercicio').val(),
						idTitulo:$('#agrTit').val(),
						idGrupo:$('#agrGru').val(),
						idSubGrupo:$('#agrSub').val()});	
			  }
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				
			}
	});
}

function actualizarCuentaAgregacion(){   
	$('#frmCrearAgregacion').find('[type="submit"]').trigger('click');

}

function postDelete(){   
	$.ajax({
		  type: "GET",
		  url: "postDesactivarCuentasContables",

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
				jAlert('Registros desactivados correctamente 2', 'Confirmaci&oacute;n');
				
				var ejercicio = $('#cbEjercicio').val();
				var idTitulo = $('#cbCtaContTitulo').val();
				var idGrupo = $('#cbCtaContGrupo').val();
				var idSubGrupo = $('#cbCtaContSubGrupo').val();
				var isActivo = $('#cbActivo').val();
				
				$('#ExpenseTableContainer').jtable('load', { ejercicio:ejercicio,idTitulo:idTitulo,idGrupo:idGrupo,idSubGrupo:idSubGrupo, isActivo:isActivo});

		  }
	});
}