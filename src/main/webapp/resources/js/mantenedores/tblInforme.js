function loadTbl(value){
	Informes(value);
}
function Informes(value){
	console.log("Paso 1 Informes");
	$('#ExpenseTableContainer').jtable('load',{ subTipoInf: $('#cbLstSubTipoInforme').val()});	
} 

$(document).ready(function() { 
	
	$('#ExpenseTableContainer').jtable({
		title: 'Tabla de Informes',
		selecting: true, //Enable selecting 
		paging: true, //Enable paging
		pageSize: 10, //Set page size (default: 10)
		sorting: false, //Enable sorting
		tableId: "tblInformes",
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
		},actions: {                  	 
			listAction: 'mantenedores/listTblInforme',
			updateAction: 'mantenedores/updTblInforme',
			deleteAction: 'mantenedores/delTblInforme',
			createAction: 'mantenedores/addTblInforme',
		},fields: {
			id:{			title: 'ID',key: true,list: false,create: false,edit: false},
			subTipoID:{		
				title: 'Sub Tipo',
				width: '15%',
				edit: false,
//				options: { '1': 'Presupuestario', '2': 'Contable', '3': 'Estados Financieros' }
				options: function(data) 
				{
					return 'mantenedores/listCmbSubTipo';
				}},
 		
			codigo: {		title: 'Codigo',width: '15%'},
			nombre: {		title: 'Nombre',width: '15%'},
			descripcion:{	title: 'Descripci&oacute;n',width: '15%'},
			descripcion: {	title: 'Descripci&oacute;n',width: '30%'},
			isValid: {		edit: false,create: false,title: 'Fecha Anulaci&oacute;n',width: '15%'},
			subTipoInf: {	visibility: 'hidden',inputClass: 'InputHidden'},
		},rowInserted: function (event, data){
			$('#ExpenseTableContainer').jtable('selectRows', data.row);
		},recordAdded: function(event, data){
			$('#ExpenseTableContainer').jtable('load',{ subTipoInf: $('#cbLstSubTipoInforme').val()});	
		},recordUpdated: function(event, data){
			$('#ExpenseTableContainer').jtable('load',{ subTipoInf: $('#cbLstSubTipoInforme').val()});	
		},
		
		formCreated: function (event, data) {
			data.form.find('input[id="Edit-subTipoID"]').addClass('validate[required,custom[integer]]');
			data.form.find('input[id="Edit-codigo"]').addClass('validate[required]');
			data.form.find('input[id="Edit-nombre"]').addClass('validate[required]');
			data.form.find('input[id="Edit-descripcion"]').addClass('validate[required]');
			
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
	
    $("#newItem").each(function(i){
    	$(this).attr({'onClick' : 'loadInput()'});
    });

    $('#tblInformes').attr('style', "font-size: 11px;");
    
}); 

function loadInput(){
	var x =  $("#cbLstSubTipoInforme option:selected").val();
    $("#Edit-subTipoInf").val(x);
    $("#Edit-subTipoInf").parent().parent().hide(); 
}
function loadSubTipoInforme(value){
	var action='mantenedores/loadSubTipoInforme?tipoInfo=' + $("#cbtipoInforme option:selected").val();
	$.ajax({url: action,
		type: "POST",
		dataType: "json",	    
		error: function(data){	    	
			$('#cbLstSubTipoInforme').empty();
			$("#cbLstSubTipoInforme").get(0).options[$("#cbLstSubTipoInforme").get(0).options.length] = new Option( 'Selec.',-1);
		},
		success: function(data){
			$('#cbLstSubTipoInforme').empty();
			$("#cbLstSubTipoInforme").get(0).options[$("#cbLstSubTipoInforme").get(0).options.length] = new Option( 'Selec.',-1);
			$.each(data, function(i, itm) {
			$("#cbLstSubTipoInforme").get(0).options[$("#cbLstSubTipoInforme").get(0).options.length] = new Option(itm.nombre,itm.subTipoID );
			});
   	    }
   	});
}