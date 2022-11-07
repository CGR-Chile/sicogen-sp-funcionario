
function load(){		
	Proyectos();	
}


function Proyectos(){	
	$('#ExpenseTableContainer').jtable('load');	
	return false;
}


$(document).ready(function(){
	
	$('#ExpenseTableContainer').jtable({
		title: 'Tabla de Proyectos BIP',
		selecting: true,
		paging: true,
		pageSize: 10,
		sorting: false,
		tableId: "tblProyectosBIP",
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
		},actions:{
			listAction: 'mantenedores/listTblProyectos',
			createAction: 'mantenedores/addTblProyectos',
			updateAction: 'mantenedores/updTblProyectos',
			deleteAction: 'mantenedores/delTblProyectos'
		},fields: {
			proyectoId:	{	title: 'ID',key: true,list: false,create: false,edit:false},
			proyectoCodigo:	{	title: 'Codigo',width: '20%'},
			proyectoDV:	{	title:	'Digito Verificador',width: '15%',list:false,edit:false,create:false},
			proyectoNombre:	{	title:	'Nombre Proyecto',width:	'25%'},
			proyectoNombreEstado:	{	title:	'Estado', width: '25%',edit:false,create:false},
			valor1 : {	title:	'V1',edit:false,create:false,list:false},
			estadoID: {
				title: 'Estado',width: '10%',list: false, 
				options: function(data) {return 'listCmbEstados';}
			},
			anulacion:	{	title: 	'Fecha Anulaci&oacute;n',width: '15%',edit: false,create: false}
 
		},formCreated: function (event, data) {
			
			if (data.formType == "edit"){
 				console.log(data.record.valor1);
 	 			$("#Edit-estadoID").val(data.record.valor1);
 			}
			data.form.find('input[id="Edit-proyectoCodigo"]').addClass('validate[maxSize[18],minSize[8],required]');
 			//data.form.find('input[id="Edit-proyectoDV"]').addClass('validate[maxSize[1],required]');
 			data.form.find('input[id="Edit-proyectoNombre"]').addClass('validate[maxSize[255],minSize[1],required]');
 			
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
		},		
		rowInserted: function (event, data) {
            $('#ExpenseTableContainer').jtable('selectRows', data.row);
		},recordAdded: function(event, data){
			$('#ExpenseTableContainer').jtable('load',{estado:$("#cbEstados option:selected").val()});
		},recordUpdated: function(event, data){
			$('#ExpenseTableContainer').jtable('load');
		}
	});
	
	load();

	$('#tblProyectosBIP').attr('style', "font-size: 11px;");
	
});