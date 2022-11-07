function realizaCargaSaldosIniciales(){
	
	console.log("realizaCargaSaldosIniciales "+ 	$("#formCargaSaldos").serialize() );
	
	$("#formCargaSaldos").ajaxSubmit({
				cache : false,
				type: "POST",
				url: 'mantenedores/cargaExcelSaldosInicial',
				scriptCharset: 'windows-1252',
				contentType: "application/x-www-form-urlencoded;charset=windows-1252",
				jsonpCallback: 'jsonpCallback',
				data: $(this).serialize(),
				dataType:'json',
				beforeSend: function (xhr) {
					$('body').append('<div id="fadeCargaXML" class="overlay" style="display:block"></div>'+
						 '<div id="waitCargaXML" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:350px;z-index:2005;padding:15px !important;display:block">'+ 
						 '	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando archivo</div>'+
						 '    <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
						 '</div>');
					
					$('#errorCargaSaldosMensaje p').remove();
				},
				complete:function( XMLHttpRequest ) {
					console.log("realizaCargaSaldosIniciales: complete");
					
					$('#waitCargaXML').remove();
					$('#fadeCargaXML').remove();
					$('#fileUpload').val("");
					load();
				},
				success: function(data)
				{				
					$('#errorCargaSaldosMensaje').empty();
					
					// Carga Exitosa, MUESTRA RESUMEN TABLA
					$('#errorCargaSaldosMensaje').append('<table>');
					$('#errorCargaSaldosMensaje').append('<thead>');
					$("#errorCargaSaldosMensaje").append("<tr>");                         
                    $("#errorCargaSaldosMensaje").append("<th><label style='text-align: left;'>RESUMEN</label></th>");
                    $("#errorCargaSaldosMensaje").append("<th></th>");
	                $("#errorCargaSaldosMensaje").append("</tr>");
		            $("#errorCargaSaldosMensaje").append("</thead>");
		            $("#errorCargaSaldosMensaje").append("<tbody>");

					$.each(data.resultadoLogCarga, function( index, value ) {
						var monto = value.cantRegistro.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,").replace(/,/g , ".");
						$('#errorCargaSaldosMensaje').append("<tr><td>"+value.estado +"</td><td><label class='formatoCLP' style='text-align: right;width: 100px'>"+monto +"</label></td></tr>");	
					});
					$('#errorCargaSaldosMensaje').append('</tbody></table>');
					$('#errorCargaSaldosMensaje').append('<br>');
					$('#errorCargaSaldosMensaje').append('<button id="csvErrores" type="button" onclick="obtenerCSVErrores()" class="botonFile transparent"> Exportar Errores</button>');

					
					// error extencion archivo
					if(data.errorCargaSaldos.length > 0){
						$.each(data.errorCargaSaldos, function( index, value ) {
							  $('#errorCargaSaldosMensaje').append('<p>'+value+'</p>');
						});
					}
					
					verErroresCargaSaldosIniciales();
				}
	});	
}
		
function verErroresCargaSaldosIniciales(){
	
	var errores = $('#errorCargaSaldosMensaje').clone();
	console.log('errores : '+errores);
	
	dialog = $('<div></div>')
    	.dialog({modal: true, width: '400px',
    		title: 'Resultado de Carga',
    		close: function(event, ui){	$(this).remove();}});
	dialog.dialog('open');
	
	dialog.html(errores);
	
	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');
	
}


function load(){	SaldoInicial();}

function SaldoInicial(){
	$('#ExpenseTableContainer').jtable('load');	
	return false;
} 


$(document).ready(function() {  
	
	$('#ExpenseTableContainer').jtable({
		title: 'Tabla de Saldo Inicial',
		selecting: true, //Enable selecting 
		paging: true, //Enable paging
		pageSize: 10, //Set page size (default: 10)
		sorting: false, //Enable sorting
		messages: {
			serverCommunicationError: 'Se ha producido un error al comunicarse con el servidor.',
    	    loadingMessage: 'Cargando Registros ...',
    	    noDataAvailable: 'No hay registros!',
    	    addNewRecord: 'Crear Registro',
    	    editRecord: 'Editar',
    	    areYouSure: 'Alerta',
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
        },
		actions: {
			listAction:   'mantenedores/listTblSaldoInicial',
			createAction: 'mantenedores/addTblSaldoInicial',
			updateAction: 'mantenedores/updTblSaldoInicial',
			deleteAction: 'mantenedores/delTblSaldoInicial'},
		fields: {
			id: 					{width:'3%',title:'ID', key: true,list: false, create: false, edit: false },
			ejercicio:				{width:'4%',edit:true,title:'Ejercicio'}, 
			
			moneda:					{width:'4%',edit:true,title:'Moneda'},
			
			codPartida:					{width:'4%',edit:true,title:'Cod. Partida'},
			codCapitulo:				{width:'4%',edit:true,title:'Cod. Capitulo'},
			codPrograma:				{width:'4%',edit:true,title:'Cod. Programa'},
			
			cuentaAgregacion:		{width:'4%',edit:true,title:'Cta. Agregación'},
			cuentaN1:				{width:'4%',edit:true,title:'Cta. Nivel 1'},
			cuentaN2:				{width:'4%',edit:true,title:'Cta. Nivel 2'},
			cuentaN3:				{width:'4%',edit:true,title:'Cta. Nivel 3'},
			
			saldoDeudor:			{width:'4%',edit:true,title:'Saldo Deudor', listClass: 'alignRight'},
			saldoAcreedor:			{width:'4%',edit:true,title:'Saldo Acreedor', listClass: 'alignRight'},
			
		},rowInserted: function (event, data) { $('#ExpenseTableContainer').jtable('selectRows', data.row);},
		recordAdded: function(event, data){ $('#ExpenseTableContainer').jtable('load');},
		recordUpdated: function(event, data){ $('#ExpenseTableContainer').jtable('load');
		},
		formCreated: function (event, data) 
		{
			data.form.css('width', '220px');
			data.form.find('input[id="Edit-saldoDeudor"]').addClass('validate[required,custom[integer]]');
			data.form.find('input[id="Edit-saldoAcreedor"]').addClass('validate[required,custom[integer]]');			
			data.form.find('input[id="Edit-ejercicio"]').addClass('validate[required,custom[integer]]');
			data.form.find('input[id="Edit-entid"]').addClass('validate[required,custom[integer]]');			
			data.form.find('input[id="Edit-cuentaAgregacion"]').addClass('validate[required,custom[integer]]');
			data.form.find('input[id="Edit-cuentaN1"]').addClass('validate[required,custom[integer]]');			
			data.form.find('input[id="Edit-cuentaN2"]').addClass('validate[required,custom[integer]]');
			data.form.find('input[id="Edit-cuentaN3"]').addClass('validate[required,custom[integer]]');			
 
			data.form.validationEngine('attach', {
				relative: true,
				overflownDIV:"#divOverflown",
				promptPosition:"bottomLeft"
			});
		},formSubmitting: function (event, data) {
			return data.form.validationEngine('validate');
		},formClosed: function (event, data) {
			data.form.validationEngine('hide');
			data.form.validationEngine('detach');
		}
		
	});
	load();
	loadData();
	
});  


function loadData(){
	$('#ExpenseTableContainer').jtable('load');	
	return false;
}


function obtenerCSVErrores(){
	dowFil = 'mantenedores/obtenerCSVErrores';
		location.href= dowFil;
}
