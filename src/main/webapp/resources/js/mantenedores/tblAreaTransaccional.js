function AreaTransaccional(ejercicio,sector,institucion){
	$('#ExpenseTableContainer').jtable('load',{ejercicio: ejercicio, sector: sector, institucion: institucion});
}

function load(ejercicio, sector, institucion){

	sector = $("#cbSector option:selected").val();
	institucion = $("#cbInstitucion option:selected").attr('data-cod');


	console.log("sector :"+ sector);
	console.log("institucionxxx :"+ institucion);

	if (institucion === undefined){
		console.log("institucion no esta definida")
		institucion = -1;
	}

	/*
	sector = $("#cbSector option:selected").val();
	var sectorOk1 = sector.split("-");
	var sectorOKK = sectorOk1[0].trim();
	institucion = $("#cbInstitucion option:selected").val();
*/


	AreaTransaccional(ejercicio,sector,institucion);
}

function loadSector(value){
	console.log("loadSector - value: " + value);
	var action='mantenedores/loadSector?ejercicioId=' + $("#cbEjercicio option:selected").val();
	$.ajax({
		url:action,
		type:"POST",
		dataType: "json",
		error: function(data){
			$('#cbSector').empty();
			$("#cbSector").get(0).options[$("#cbSector").get(0).options.length] = new Option('Selec.',-1);
			document.getElementById('fade').style.display='none';
			document.getElementById('formEnvio').style.display='none';
		},
		success: function(data){
			console.log('data: ');
			console.log(data);
			$('#cbSector').empty();
			$("#cbSector").get(0).options[$("#cbSector").get(0).options.length] = new Option( 'Selec.',-1);
			$.each(data, function(i, itm) {
				var optionSelect = new Option(itm.nombreSector, itm.idSector);
				$("#cbSector").get(0).options[$("#cbSector").get(0).options.length] = optionSelect;
				optionSelect.setAttribute("data-cod", itm.idSector2);

					//new Option(itm.nombreSector,itm.idSectorEjercicio   );

				//console.log("sector 1 "+ itm.nombreSector);
				//console.log("sector 1 "+ itm.idSectorEjercicio);

			});

		}
	});
	console.log("loadSector - FIN ");
}

function loadInstitucion(value){
/*
	var idEjercicio = $("#cbEjercicio").val();
	var idSector = $("#cbSector option:selected").val();
	console.log("SECTOR: " + idSector);
	var sectorOk1 = idSector.split("-");
	console.log("SECTOROK :"+ sectorOk1);
	var sectorOKK = sectorOk1[0].trim();
	console.log("SECTOROKK :"+ sectorOKK);
	console.log("tblAreaTransaccional - loadInstitucion()");
*/

	var idEjercicio = $("#cbEjercicio").val();
	var idSector = $("#cbSector option:selected").attr('data-cod');

	var idSector2 = $("#cbSector option:selected").val();


	console.log("idEjercicio: " + idEjercicio);
	console.log("idSector: " + idSector);
	console.log("idSector2: " + idSector2);

	var action = 'mantenedores/loadInstitucion?sectorId2=' + idSector2 + '&ejercicioId=' + idEjercicio + '&sectorId=' + idSector;
	$.ajax({
		url: action,
		type: "POST",
		dataType: "json",
		error: function (data) {
			$('#cbInstitucion').empty();
			$("#cbInstitucion").get(0).options[$("#cbInstitucion").get(0).options.length] = new Option('Selec.', -1);
			document.getElementById('fade').style.display = 'none';
			document.getElementById('formEnvio').style.display = 'none';
		},
		success: function (data) {
			console.log('data: ');
			console.log(data);

			$('#cbInstitucion').empty();
			$("#cbInstitucion").get(0).options[$("#cbInstitucion").get(0).options.length] = new Option('Selec.', -1);

			$.each(data, function (i, itm) {
				var optionSelect = new Option(itm.nombreInstitucion, itm.idInstitucion);
				$("#cbInstitucion").get(0).options[$("#cbInstitucion").get(0).options.length] = optionSelect;
				optionSelect.setAttribute("data-cod", itm.idInstitucion);
			});

/*
			var cbInstitucion = $("#cbInstitucion option:selected").val();
			console.log("Institucion: " + cbInstitucion);
*/




			$('#ExpenseTableContainer').jtable('load',
				{
					ejercicio: $("#cbEjercicio option:selected").val(),
					sector: $("#cbSector option:selected").val(),
					institucion: $("#cbInstitucion option:selected").val()
				});
		}
	});
	console.log("loadInstitucion - FIN: ");
}

$(document).ready(function () {
	$('#ExpenseTableContainer').jtable({
		title: 'Tabla de Areas Transaccionales',
		selecting: true, //Enable selecting
		paging: true, //Enable paging
		pageSize: 10, //Set page size (default:10)
		sorting: false, //Enable sorting
		tableId: "tblATrans",
		messages: {
			serverCommunicationError: 'Se ha producido un error al comunicarse con el servidor.',
			loadingMessage: 'Cargando Registros ...',
			noDataAvailable: 'No hay registros!',
			addNewRecord: 'Crear Sector',
			editRecord: 'Editar',
			areYouSure: '&iquest;Est&aacute; seguro?',
			deleteConfirmation: 'Se eliminar&aacute; este registro. &iquest;Est&aacute; seguro? ',
			save: 'Guardar',
			saving: 'Guardando',
			cancel: 'Cancelar',
			deleteText: 'Deshabilitar',
			deleting: 'Borrado',
			error: 'Alerta',
			close: 'Cerrar',
			cannotLoadOptionsFor: 'No se puede cargar las opciones para el campo {0}',
			pagingInfo: 'Mostrando: {0}-{1}, de: {2}',
			pageSizeChangeLabel: 'Numero de Registros',
			gotoPageLabel: 'ir a la p&aacute;gina',
			canNotDeletedRecords: 'No se puede eliminar {0} de {1} registro',
			deleteProggress: 'Eliminado {0} de {1} archivos, procesamiento de ...'
		}, actions: {
			listAction: 'mantenedores/listTblAT',
			createAction: 'mantenedores/addTblAT'
			//updateAction: 'updTblAT',
			//deleteAction: 'delTblAT'
		}, fields: {
			idAT: {
				title: 'ID',
				key: true,
				list: false,
				create: false,
				edit: false
			},
			ejercicio: {create: true, type: 'hidden'},
			sector: {
				title: 'Sector', width: '10%',
				input: function (data) {
					if (data.record) {
						return '<input type="text" maxlength="2"  name="sector" value="{0}" class="solo-numero numero2"  pattern="[0-9][0-9]" title="Debe ingresar sólo 2 caracteres">'.replace("{0}", data.record.sector);
					} else {
						return '<input type="text" maxlength="2"  name="sector" class="solo-numero numero2"  pattern="[0-9][0-9]" title="Debe ingresar sólo 2 caracteres">';
					}
				}
			},
			institucion: {title: 'Institucion', width: '10%', create: false, edit: false},
			at: {title: 'AT', width: '10%', create: false, edit: false},
			nombre: {title: 'Descripción', width: '35%'},
			rut: {title: 'Rut', width: '20%', create: false, edit: false},
			tipo: {title: 'Tipo', width: '20%', create: false, edit: false},
			estado: {title: 'Estado', width: '20%', create: false, edit: false},
			bitacora: {
				title: 'Bitácora', width: '10%', create: false, edit: false,
				display: function (data) {
					console.log("data:");
					console.log(data);
					if (data.record.at == " " && data.record.institucion == " " && data.record.sector != " ") {
						return $(
							"<button type='button' onclick='verBitacoraSector(\"" + data.record.idAT + "\")' style='background-color:transparent; border-color:transparent;'> " +
							'  <img src="../resources/img/bitacora.png" height="20"/>' +
							'</button>');
					} else if (data.record.at == " " && data.record.institucion != " " && data.record.sector != " ") {
						return $(
							"<button type='button' onclick='verBitacoraInstitucion(\"" + data.record.idAT + "\")' style='background-color:transparent; border-color:transparent;'> " +
							'  <img src="../resources/img/bitacora.png" height="20"/>' +
							'</button>');
					} else {
						return $(
							"<button type='button' onclick='verBitacoraAT(\"" + data.record.idAT + "\")' style='background-color:transparent; border-color:transparent;'> " +
							'  <img src="../resources/img/bitacora.png" height="20"/>' +
							'</button>');
					}
				}
			},
			niveles: {
				title: 'Niveles', width: '10%', create: false, edit: false,
				display: function (data) {
					if (data.record.at == " " && data.record.institucion == " " && data.record.sector != " ") {
						return $(
							"<button type='button' onclick='crearInstitucion(\""+data.record.idAT+"\",\""+data.record.sector+"\")' style='background-color:transparent; border-color:transparent;'> " +
							'  <img src="../resources/img/agregar_nivel.png" height="10"/>' +
							'</button>');
					} else if (data.record.at == " " && data.record.institucion != " " && data.record.sector != " ") {
						return $(
							"<button type='button' onclick='crearAT(\""+data.record.idAT+"\",\""+data.record.institucion+"\",\""+data.record.sector+"\")' style='background-color:transparent; border-color:transparent;'> " +
							'  <img src="../resources/img/agregar_nivel.png" height="10"/>' +
							'</button>');
					} else {
						return " ";
					}
				}
			},
			editar: {
				title: 'Editar', width: '10%', create: false,
				display: function (data) {
					console.log("data:");
					console.log(data);

					if (data.record.at == " " && data.record.institucion == " " && data.record.sector != " ") {
						return $(
							"<button type='button' onclick='editarSector(\""+data.record.idAT+"\")' style='background-color:transparent; border-color:transparent;'> " +
							'  <img src="../resources/jtable/themes/lightcolor/edit.png" height="15"/>' +
							'</button>');
					} else if (data.record.at == " " && data.record.institucion != " " && data.record.sector != " ") {
						return $(
							"<button type='button' onclick='editarInstitucion(\""+data.record.idAT+"\")' style='background-color:transparent; border-color:transparent;'> " +
							'  <img src="../resources/jtable/themes/lightcolor/edit.png" height="15"/>' +
							'</button>');
					} else {
						return $(
							"<button type='button' onclick='editarAT(\""+data.record.idAT+"\",\""+data.record.at+"\",\""+data.record.institucion+"\",\""+data.record.sector+"\",\""+data.record.rut+"\")' style='background-color:transparent; border-color:transparent;'> " +
							'  <img src="../resources/jtable/themes/lightcolor/edit.png" height="15"/>' +
							'</button>');
					}
				}
			},
			eliminar: {
				title: 'Deshabilitar', width: '10%', create: false,
				display: function (data) {
					if (data.record.at == " " && data.record.institucion == " " && data.record.sector != " ") {
						if (data.record.estado == "ACTIVO") {
							return $(
								"<button type='button' onclick='eliminarSector(\"" + data.record.idAT + "\",\"" + data.record.estado + "\"); return false;' style='background-color:transparent; border-color:transparent;'> " +
								'  <img src="../resources/jtable/themes/lightcolor/delete.png" height="15"/>' +
								'</button>');
						}
					} else if (data.record.at == " " && data.record.institucion != " " && data.record.sector != " ") {
						if (data.record.estado == "ACTIVO") {
							return $(
								"<button type='button' onclick='eliminarInstitucion(\"" + data.record.idAT + "\",\"" + data.record.estado + "\"); return false;' style='background-color:transparent; border-color:transparent;'> " +
								'  <img src="../resources/jtable/themes/lightcolor/delete.png" height="15"/>' +
								'</button>');
						}
					} else {
						if (data.record.estado == "ACTIVO") {
							return $(
								"<button type='button' onclick='eliminarAT(\"" + data.record.idAT + "\",\"" + data.record.estado + "\"); return false;' style='background-color:transparent; border-color:transparent;'> " +
								'  <img src="../resources/jtable/themes/lightcolor/delete.png" height="15"/>' +
								'</button>');
						}
					}
				}
			}
		}, rowInserted: function (event, data) {
			$('#ExpenseTableContainer').jtable('selectRows', data.row);
		}, recordAdded: function (event, data) {
			$('#ExpenseTableContainer').jtable('load', {
					ejercicio: $("#cbEjercicio option:selected").val(),
					sector: $("#cbSector option:selected").val(),
					institucion: $("#cbInstitucion option:selected").val()
				}
			);
			console.log($("#cbEjercicio option:selected").val());
			loadSector($("#cbEjercicio option:selected").val());
		}, recordUpdated: function (event, data) {
			$('#ExpenseTableContainer').jtable('load', {
				ejercicio: $("#cbEjercicio option:selected").val(),
				sector: $("#cbSector option:selected").val(),
				institucion: $("#cbInstitucion option:selected").val()
			});
		}, formCreated: function (event, data) {
			data.form.find('input[id="Edit-sector"]').addClass('validate[minSize[2],maxSize[2],required,custom[integer]]');
			data.form.find('#Edit-ejercicio').val($("#cbEjercicio").val());
			data.form.validationEngine('attach', {
				relative: true,
				overflownDIV: "#divOverflown",
				promptPosition: "bottomLeft"
			});
		}, formSubmitting: function (event, data) {
			return data.form.validationEngine('validate');
		}, formClosed: function (event, data) {
			data.form.validationEngine('hide');
			data.form.validationEngine('detach');
		}
	});
	$(document).on("keydown", "input.numero2", function (e) {
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
	$('#tblATrans').css('font-size', '11px');
});



function formato2Numero(elemento){
	var valDato,valPatron;

	valPatron	= new RegExp('/^[0-9]/');
	valDato		= $(elemento).val();

	console.log(valPatron.test(valDato));

}

function verBitacoraSector(id){

	console.log("tblAreaTransaccional - verBitacoraSector()");

	var param = '?idAT='+id;

	console.log("id: "+ id);
	$.ajax({
		type: "GET",
		url: "mantenedores/verBitacoraSector"+param,
		dataType: "json",
		complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		},success: function(data){
			var tbla = "";
			$('#tblDesactivarCtasCtables  > tbody').empty();
			jQuery.each(data, function(i, val) {

				console.log("data1: ");
				console.log(data);

				tbla = tbla + "<tr><td>" +val.estado + "</td><td>" + val.fecha + "</td><td>" + val.usuario + "</td></tr>"
			});
			$('#tblDesactivarCtasCtables > tbody').html(tbla);
			var opt = {
				autoOpen: true,
				modal: true,
				width: 500,
				show: "blind",
				title : "Bitacora de Actualizaciones"
			};
			var popUp = $("#popupBitacoraCuenta").dialog(opt);
			popUp.dialog("open");
			$(".ui-widget-overlay").addClass("overlay" );
			$(".overlay" ).removeClass("ui-widget-overlay" );
			$(".ui-icon-closethick").css('background-position', '-32px -192px');
			$(".ui-icon-closethick").css('background-color', '#F2F2F2');
			$(".ui-icon-closethick").css('top', '0px');
			$(".ui-icon-closethick").css('left', '0px');
		}
	});

	var opt = {
		autoOpen: true,
		modal: true,
		width: 505,
		show: "blind",
		title : "Bitácora de Actualizaciones"
	};

	try {
		$(".ui-dialog-content").dialog("close");
	}
	catch(err) {}

	$("#divPopUpAmpliado").css({'left':'0px'});
	var popUp = $("#divPopUpAmpliado").dialog(opt);
	popUp.dialog("open");
	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');

}


function verBitacoraInstitucion(idAT){
    console.log("verBitacoraInstitucion - idAT: " + idAT);
	var param = '?idAT=' + idAT;
	$.ajax({
		type: "GET",
		url: "mantenedores/verBitacoraInstitucion" + param,
		dataType: "json",
		complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		}, success: function (data) {
			var tbla = "";
			$('#tblDesactivarCtasCtables  > tbody').empty();
			jQuery.each(data, function (i, val) {
				tbla = tbla + "<tr><td>" + val.estado + "</td><td>" + val.fecha + "</td><td>" + val.usuario + "</td></tr>"
			});
			$('#tblDesactivarCtasCtables > tbody').html(tbla);
			var opt = {
				autoOpen: true,
				modal: true,
				width: 500,
				show: "blind",
				title: "Bitacora de Actualizaciones"
			};
			var popUp = $("#popupBitacoraCuenta").dialog(opt);
			popUp.dialog("open");
			$(".ui-widget-overlay").addClass("overlay");
			$(".overlay").removeClass("ui-widget-overlay");
			$(".ui-icon-closethick").css('background-position', '-32px -192px');
			$(".ui-icon-closethick").css('background-color', '#F2F2F2');
			$(".ui-icon-closethick").css('top', '0px');
			$(".ui-icon-closethick").css('left', '0px');
		}
	});

	var opt = {
		autoOpen: true,
		modal: true,
		width: 505,
		show: "blind",
		title : "Bitácora de Actualizaciones"
	};

	try {
		$(".ui-dialog-content").dialog("close");
	}
	catch(err) {}

	$("#divPopUpAmpliado").css({'left':'0px'});
	var popUp = $("#divPopUpAmpliado").dialog(opt);
	popUp.dialog("open");
	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');
	console.log("verBitacoraInstitucion - FIN: ");
}


function verBitacoraAT(idAT){
    console.log("verBitacoraAT - idAT: " + idAT);
	var param = '?idAT=' + idAT;
	$.ajax({
		type: "GET",
		url: "mantenedores/verBitacoraAT" + param,
		dataType: "json",
		complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		}, success: function (data){
			var tbla = "";
			$('#tblDesactivarCtasCtables  > tbody').empty();
			jQuery.each(data, function (i, val) {
				tbla = tbla + "<tr><td>" + val.estado + "</td><td>" + val.fecha + "</td><td>" + val.usuario + "</td></tr>"
			});
			$('#tblDesactivarCtasCtables > tbody').html(tbla);
			var opt = {
				autoOpen: true,
				modal: true,
				width: 500,
				show: "blind",
				title: "Bitacora de Actualizaciones"
			};
			var popUp = $("#popupBitacoraCuenta").dialog(opt);
			popUp.dialog("open");
			$(".ui-widget-overlay").addClass("overlay");
			$(".overlay").removeClass("ui-widget-overlay");
			$(".ui-icon-closethick").css('background-position', '-32px -192px');
			$(".ui-icon-closethick").css('background-color', '#F2F2F2');
			$(".ui-icon-closethick").css('top', '0px');
			$(".ui-icon-closethick").css('left', '0px');
		}
	});

	var opt = {
		autoOpen: true,
		modal: true,
		width: 505,
		show: "blind",
		title: "Bitácora de Actualizaciones"
	};

	try {
		$(".ui-dialog-content").dialog("close");
	} catch (err) {
    }

	$("#divPopUpAmpliado").css({'left':'0px'});

	var popUp = $("#divPopUpAmpliado").dialog(opt);
	popUp.dialog("open");
	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');

	console.log("verBitacoraAT - FIN: ");
}


function crearInstitucion(idAT, codSector){

	console.log("crearInstitucion - idAT: " + idAT);
	var action = 'mantenedores/getCodigoSectorById';
	var sector = $('#cbSector option:selected').text();
	//var codSector = $('#cbSector option:selected').val();

	$("#txtSector").val(codSector);
	//$("#txtCodSector").val(codSector);

	document.getElementById('txtCodSector').style.display = 'none';
	$("#txtCodigoInstitucion").val("");
	$('#txtNombreInstitucion').val("");

	$.ajax({
		type: "POST",
		url: action,
		data: {
			idAT: idAT, idEjercicio: $('#cbEjercicio').val()
		},
		datatype: 'json',
		success: function (data) {
			$('#txtSector').val(data);
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log('Error');
		}
	});

	var opt = {
		autoOpen: true,
		modal: true,
		width: 505,
		show: "blind",
		title: "Crear Institucion"
	};

	try {
		$(".ui-dialog-content").dialog("close");
	} catch (err) {
	}

	$("#divPopUpInstitucion").css({'left': '0px'});

	var popUp = $("#divPopUpInstitucion").dialog(opt);
	popUp.dialog("open");

	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');
	$("#Btn_crearInstitucion").attr("onclick", "setInstitucion(\"" + idAT + "\")");
	$("#Btn_CancelarCrearInstitucion").attr("onclick", "botonCancelarCrearIns()");
	console.log("crearInstitucion - FIN: ");







}

function putINST(){
	$('#frmCrearINST').find('[type="submit"]').trigger('click');
}

function setInstitucion(idAT){
    console.log("setInstitucion - idAT: " + idAT);
    //idAT = $("#txtCodSector").val();
	console.log("tblAreaTransaccional - setInstitucion()");
    //var sector = idAT                      //ok
    var codInstitucion = $('#txtCodigoInstitucion').val();     //ok
    var nomInstitucion = $('#txtNombreInstitucion').val();      //ok
    var ejercicio = $('#cbEjercicio option:selected').val();    //ok
    var codigoPadre = $('#txtSector').val();

    //console.log("sector "+ sector);
    console.log("codInstitucion "+ codInstitucion);
    console.log("nomInstitucion "+ nomInstitucion);
    console.log("ejercicio "+ ejercicio);
    console.log("codigoPadre "+ codigoPadre);

	var codSec = $('#txtSector').val();
	var codIns = $('#txtCodigoInstitucion').val();
	var nomIns = $('#txtNombreInstitucion').val();


	var action = 'mantenedores/addTblInstitucion';
	$.ajax({
		type: "POST",
		url: action,
		data: {
            codInstitucion: codInstitucion,
            nomInstitucion: nomInstitucion,
            codigoPadre: codigoPadre,
            sector: idAT,
            ejercicio: ejercicio
        },
		datatype: 'json',
		complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();

		},success: function(data) {
            console.log("entro");
			console.log(data);
			console.log(data.Message);
			if (data.Result == "ERROR") {
				jAlert(data.Message, "Alerta");
			} else {
				$("#divPopUpInstitucion").dialog("close");
				console.log("ejercicio:" + $('#cbEjercicio').val());
				console.log("sector:" + $('#cbSector').val());
				console.log("institucion:" + $('#cbInstitucion').val());
				loadInstitucion();
				load($('#cbEjercicio').val(), $('#cbSector').attr('data-cod'), $('#cbInstitucion').attr('data-cod'));
				$("#divPopUpInstitucion").dialog("close");
				$("#popup_container").dialog("close");
			}







			/*
               var status = JSON.parse(xhr.responseText).resultStr;
               if (status == "ERROR"){

                   jAlert(JSON.parse(xhr.responseText).Message);
                   console.log(JSON.parse(xhr.responseText).Message);

               } if (status == "OK"){

                   $("#divPopUpInstitucion").dialog("close"); jAlert("Se ha creado con éxito");
                   load($('#cbEjercicio').val(),$('#cbSector').val(),$('#cbInstitucion').val());
                   loadInstitucion($("#cbSector option:selected").val());
               }
   */
		},error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('Error');
			//jAlert(data.Message, "Alerta");
			$("#divPopUpInstitucion").dialog("close");
			$("#popup_container").dialog("close");





		}

	});

	console.log("setInstitucion - FIN: ");

}

function crearAT(idAT, institucion, sector){

	console.log("crearAT - idAT: " + idAT);
	console.log("crearAT - codInst: " + institucion);
	//var sector = $('#cbSector option:selected').text();
	//var institucion = $('#cbInstitucion option:selected').text();
	$('#txtSectorAreaT').val(sector);
	$('#txtInstitucionAreaT').val(institucion);
	$("#txtCodigoAreaT").val("");
	$('#txtNombreAreaT').val("");
	$('#txtRutAreaT').val("");
	$('#txtDvAreaT').val("");

	var action = 'mantenedores/getCodigoSectorByIdIns';
	$.ajax({
		type: "POST",
		url: action,
		data: {idAT: idAT, idEjercicio: $('#cbEjercicio').val()},
		datatype: 'text',
		success: function (data) {
		    console.log("ingreso1");
		    console.log("dataxxx: " + data)
			//$('#txtSectorAreaT').val(data);
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log('Error');
		}
	});

	var action2 = 'mantenedores/getCodigoInstitucionById';
	$.ajax({
		type: "POST",
		url: action2,
		data: {idAT: idAT, idEjercicio: $('#cbEjercicio').val()},
		datatype: 'json',
		success: function (data) {
			$('#txtInstitucionAreaT').val(data);
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log('Error');
		}
	});

	var opt = {
		autoOpen: true,
		modal: true,
		width: 505,
		show: "blind",
		title: "Crear Programa"
	};

	try {
		$(".ui-dialog-content").dialog("close");
	} catch (err) {
	}
	$("#divPopUpAreaT").css({'left': '0px'});
	var popUp = $("#divPopUpAreaT").dialog(opt);
	popUp.dialog("open");

	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');
	$("#Btn_crearAreaT").attr("onclick", "setAreaT(\"" + idAT + "\")");
	$("#Btn_CancelarCrearAreaT").attr("onclick", "botonCancelarCrearAT()");
	console.log("crearAT - FIN: ");

}

function putAT(){
	$('#frmCreateAT').find('[type="submit"]').trigger('click');
}

function setAreaT(idAT){

	console.log("tblAreaTransaccional - setAT()" + idAT);

	var codSec = $('#txtSectorAreaT').val();
	var codIns = $('#txtInstitucionAreaT').val();
	var codAT = $('#txtCodigoAreaT').val();
	var nomAT = $('#txtNombreAreaT').val();
	var rutAT = $('#txtRutAreaT').val();
	var dvAT = $('#txtDvAreaT').val();
    var ejercicio = $('#cbEjercicio').val();

	var action = 'mantenedores/addTblArea';
	$.ajax({
		type: "POST",
		url: action,
		data: {
            codigo: codAT,  //ok
            nombre: nomAT, //ok
            rut: rutAT,  //ok
            digitoV: dvAT,  //ok
            codigoSector: codSec,
            codigoInstitucion: codIns,
            institucion: idAT,
            ejercicio: ejercicio,
        },
		datatype: 'json',
		complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		},success: function(data) {

			console.log("ingreso 11");
            console.log(data);
            if (data.Result == "ERROR") {
                jAlert(data.Message, "Alerta");
            } else {
                $("#divPopUpAreaT").dialog("close");
                load($('#cbEjercicio').val(), $('#cbSector').attr('data-cod'), $('#cbInstitucion').attr('data-cod'));
				$("#divPopUpAreaT").dialog("close");
				$("#popup_container").dialog("close");
            }



/*
			var status = JSON.parse(xhr.responseText).resultStr;
			var msn = JSON.parse(xhr.responseText).Message;
			if (status == "ERROR"){

				jAlert(msn + ".");
				console.log(JSON.parse(xhr.responseText).Message);

			} if (status == "OK"){

				jAlert("Área Transaccional creada con éxito");
				$("#divPopUpAreaT").dialog("close");
				load($('#cbEjercicio').val(),$('#cbSector').val(),$('#cbInstitucion').val());
			}
*/
		},error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('Error');
			$("#divPopUpAreaT").dialog("close");
			$("#popup_container").dialog("close");
		}
	});

}


function editarSector(idAT){

	console.log("tblAreaTransaccional - editarSector()");
	console.log("tblAreaTransaccional - id: "+idAT);



	var sector = $('#cbSector option:selected').text();
	console.log("editarSector - NombreSector: " + sector);
	document.getElementById('txtCodSec').style.display = 'none';
	var codSector = sector.split("-");



	var action = 'mantenedores/getSectorById';
	$.ajax({
		type: "POST",
		url: action,
		data: {idAT: idAT},
		datatype: 'text',
		success: function(data){
		    console.log("entroxxx:");
			console.log(data);

			$('#txtDescripSector').val(data);
			$("#txtCodigoSector").val(codSector[0].trim());
			console.log('entro: ');

			var opt = {
				autoOpen: true,
				modal: true,
				width: 505,
				show: "blind",
				title : "Editar Sector"
			};
			console.log('entro 2: ');
			try {
				$(".ui-dialog-content").dialog("close");
			}
			catch(err) {}
			console.log('entro 3: ');

			$("#divPopUpEditarSector").css({'left':'0px'});

			var popUp = $("#divPopUpEditarSector").dialog(opt);
			popUp.dialog("open");
			console.log('entro 4: ');
			$(".ui-icon-closethick").css('background-position', '-32px -192px');
			$(".ui-icon-closethick").css('background-color', '#F2F2F2');
			$(".ui-icon-closethick").css('top', '0px');
			$(".ui-icon-closethick").css('left', '0px');
			$("#Btn_CancelarEditarSector").attr("onclick","botonCancelarEditarSector()");
			console.log("editarSector - FIN ");
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('Error');
		}

	});
}

function postSECT(){
	$('#frmEditSector').find('[type="submit"]').trigger('click');
}


function editarInstitucion(idAT){

	console.log("editarInstitucion - idAT: " + idAT);
	document.getElementById('txtCodSecIns').style.display = 'none';
	var sector = $('#cbSector option:selected').text();
	console.log("editarInstitucion - sector: " + sector);
	var sec = sector.split("-");
	console.log("editarInstitucion - sec[0]: " + sec[0]);
	var institucion = $('#cbInstitucion option:selected').text();
	console.log("editarInstitucion - institucion: " + institucion);
	var ins = institucion.split(" -");
	console.log("editarInstitucion - ins[0]: " + ins[0]);
	$("#txtCodSecIns").val(idAT);
	var action = 'mantenedores/getInstitucionById';
	$.ajax({
		type: "POST",
		url: action,
		data: {idAT: idAT, idEjercicio: $("#cbEjercicio").val()},
		datatype: 'text',
		success: function (data) {
			$('#txtCodigoSectorEditar').val(sec[0].trim());
			$('#txtCodigoInstitucionEditar').val(ins[0].trim());
			$('#txtNombreInstitucionEditar').val(data);
			var opt = {
				autoOpen: true,
				modal: true,
				width: 505,
				show: "blind",
				title: "Editar Institucion"
			};
			try {
				$(".ui-dialog-content").dialog("close");
			} catch (err) {
			}
			$("#divPopUpEditarInstitucion").css({'left': '0px'});
			var popUp = $("#divPopUpEditarInstitucion").dialog(opt);
			popUp.dialog("open");

			$(".ui-icon-closethick").css('background-position', '-32px -192px');
			$(".ui-icon-closethick").css('background-color', '#F2F2F2');
			$(".ui-icon-closethick").css('top', '0px');
			$(".ui-icon-closethick").css('left', '0px');
			$("#Btn_CancelarEditarInstitucion").attr("onclick", "botonCancelarEditarInstitucion()");
			//loadInstitucion();
			//load($('#cbEjercicio').val(), $('#cbSector').attr('data-cod'), $('#cbInstitucion').attr('data-cod'));
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log('Error');
		}
	});
}


function postINST(){
	$('#frmEditInstitucion').find('[type="submit"]').trigger('click');
}


function editarAT(idAT, codAT, codInst, codSec, rutAT){
	
	console.log("tblAreaTransaccional - editarAT()");
	
	console.log("tblAreaTransaccional - id: "+idAT);
	console.log("tblAreaTransaccional - codAT: "+codAT);
	console.log("tblAreaTransaccional - codInst: "+codInst);
	console.log("tblAreaTransaccional - codSec: "+codSec);
	console.log("tblAreaTransaccional - rutAT: "+rutAT);

	var rutdv = rutAT.split("-");
	var rut = rutdv[0];
	var dv = rutdv[1];




	var action = 'mantenedores/getATById';
	
	$.ajax({
		type: "POST",
		url: action,
		data: {idAT:idAT,idEjercicio: $("#cbEjercicio").val()},
		datatype: 'text',
		success: function(data){
			console.log(data);
			$('#txtSectorEditAreaT').val(codSec.trim());
			$('#txtInstitucionEditAreaT').val(codInst.trim());
			$('#txtCodigoAreaTEditar').val(codAT.trim());
			$('#txtRutAreaTEditar').val(rut.trim());
			$('#txtDvAreaTEditar').val(dv.trim());
			$('#txtNombreAreaTEditar').val(data);

			var opt = {
				autoOpen: true,
				modal: true,
				width: 505,
				show: "blind",
				title: "Editar Area"
			};

			try {
				$(".ui-dialog-content").dialog("close");
			} catch (err) {
			}



			//var rutdv = data.rutAT.split("-");
			//var rut = rutdv[0];
			//var dv = rutdv[1];
			
			//console.log("tblAreaTransaccional - data.rutAT: "+rut);
			//console.log("tblAreaTransaccional - data.rutAT dv: "+dv);
			
			$('#txtAT').val(data.nombreAT);
			$('#txtCodAT').val(codAT);
			$('#txtCodInsAT').val(codInst);
			$('#txtCodSecAT').val(codSec);
			//$('#txtRutEAT').val(rut);
			//$('#txtDVEAT').val(dv);
			
			var opt = {
			        autoOpen: true,
			        modal: true,
			        width: 505,
			        show: "blind",
			        title : "Editar Area Transaccional"
			};
			
			try {
				$(".ui-dialog-content").dialog("close");
			}
			catch(err) {}
			
			$("#divPopUpEditarAreaT").css({'left':'0px'});
		 
			var popUp = $("#divPopUpEditarAreaT").dialog(opt);
			popUp.dialog("open");

			$(".ui-icon-closethick").css('background-position', '-32px -192px');
			$(".ui-icon-closethick").css('background-color', '#F2F2F2');
			$(".ui-icon-closethick").css('top', '0px');
			$(".ui-icon-closethick").css('left', '0px');
			
			//$("#frmEditAT").attr("onsubmit","updAT(\""+id+"\"); return false;");
			$("#Btn_editarAreaT").attr("onclick", "updAT(\"" + idAT + "\")");
			$("#Btn_CancelarEditarAreaT").attr("onclick","botonCancelarEditarAreaT()");
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('Error');
		}
		
	});
}



function postAT(){
	$('#frmEditAT').find('[type="submit"]').trigger('click');
}

function eliminarSector(id){

	console.log("tblAreaTransaccional - eliminarSector()");

	var opt = {
		autoOpen: true,
		modal: true,
		width: 505,
		show: "blind",
		title : "Alerta"
	};

	try {
		$(".ui-dialog-content").dialog("close");
	}
	catch(err) {}

	$("#divPopUpEliminarSector").css({'left':'0px'});

	var popUp = $("#divPopUpEliminarSector").dialog(opt);
	popUp.dialog("open");

	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');

	$("#Btn_eliminarSector").attr("onclick","delSector("+id+")");
	$("#Btn_CancelarSector").attr("onclick","botonCancelarDelteSect()");

}


function eliminarInstitucion(id){

	console.log("tblAreaTransaccional - eliminarInstitucion()");
	console.log("id: "+ id);

	var opt = {
		autoOpen: true,
		modal: true,
		width: 505,
		show: "blind",
		title : "Alerta"
	};

	try {
		$(".ui-dialog-content").dialog("close");
	}
	catch(err) {}

	$("#divPopUpEliminarInstitucion").css({'left':'0px'});

	var popUp = $("#divPopUpEliminarInstitucion").dialog(opt);
	popUp.dialog("open");

	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');

	$("#Btn_eliminarInstitucion").attr("onclick","delInstitucion("+id+")");
	$("#Btn_CancelarInstitucion").attr("onclick","botonCancelarDeleteInst()");

}


function eliminarAT(id){

	console.log("tblAreaTransaccional - eliminarAT()");
	console.log("id: "+ id);

	var opt = {
		autoOpen: true,
		modal: true,
		width: 505,
		show: "blind",
		title : "Alerta"
	};
	try {
		$(".ui-dialog-content").dialog("close");
	}
	catch(err) {}

	$("#divPopUpEliminarAT").css({'left':'0px'});

	var popUp = $("#divPopUpEliminarAT").dialog(opt);
	popUp.dialog("open");

	$(".ui-icon-closethick").css('background-position', '-32px -192px');
	$(".ui-icon-closethick").css('background-color', '#F2F2F2');
	$(".ui-icon-closethick").css('top', '0px');
	$(".ui-icon-closethick").css('left', '0px');

	$("#Btn_eliminarAT").attr("onclick","delAT("+id+")");
	$("#Btn_CancelarAT").attr("onclick","botonCancelarDeleteAT()");

}


function updSector(idAT){
	console.log("updSector - idAT: " + idAT);
	idAT = $("#txtCodSec").val();
	console.log("idAT: "+idAT);
	nombre = $("#txtDescripSector").val();






	var action = 'mantenedores/updTblSector';
	$.ajax({
		type: "POST",
		url: action,
		data: {
			idAT: idAT,
			nombre: nombre
		},
		datatype: 'json',
		complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		}, success: function (data) {
			$("#divPopUpEditarSector").dialog("close");
			load($('#cbEjercicio').val(), $('#cbSector').attr('data-cod'), $('#cbInstitucion').attr('data-cod'));
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log('Error');
		}
	});
	console.log("updSector - FIN ");










}


function updInstitucion(idAT){
	console.log("updInstitucion - idAT: " + idAT);
	var codigo = $("#txtCodigoInstitucionEditar").val();
	console.log("updInstitucion - codigo: " + codigo);
	var nombre = $("#txtNombreInstitucionEditar").val();
	console.log("updInstitucion - nombre: " + nombre);
	idAT = $("#txtCodSecIns").val();
	console.log("updInstitucion - idAT(2): " + idAT);
	var idSector = $('#cbSector option:selected').val();

	console.log("updInstitucion - idSector: " + idSector);
	var ejercicio = $('#cbEjercicio option:selected').val();
	console.log("updInstitucion - ejercicio: " + ejercicio);
	document.getElementById('txtCodSecIns').style.display = 'none';
	var action = 'mantenedores/updTblInstitucion';
	$.ajax({
		type: "POST",
		url: action,
		data: {id: idAT, instCodigo: codigo, nombre: nombre, ejercicio: ejercicio},
		datatype: 'json',
		complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		},
		success: function (data) {
			if (data.Result == "ERROR") {
				jAlert(data.Message, "Alerta");
			} else {
				$("#divPopUpEditarInstitucion").dialog("close");
				load($('#cbEjercicio').val(), $('#cbSector').attr('data-cod'), $('#cbInstitucion').attr('data-cod'));
				loadInstitucion();
				//loadInstitucion$('#cbSector').attr('data-cod');
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log('Error');
		}
	});
	console.log("updInstitucion - FIN ");
}


function updAT(idAT){
	console.log("updAreaT - idAT: " + idAT);
	var idInstitucion = $('#cbInstitucion option:selected').val();
	console.log("updAreaT - idInstitucion: " + idInstitucion);
	var nombre = $("#txtNombreAreaTEditar").val();
	console.log("updAreaT - nombre: " + nombre);
	var codigoArea = $("#txtCodigoAreaTEditar").val();
	console.log("updAreaT - codigoArea: " + codigoArea);
	var ejercicio = $('#cbEjercicio option:selected').val();
	console.log("updAreaT - ejercicio: " + ejercicio);
	var rut = $("#txtRutAreaTEditar").val();
	console.log("updAreaT - rut: " + rut);
	var dv = $("#txtDvAreaTEditar").val();
	console.log("updAreaT - dv: " + dv);
	var action = 'mantenedores/updTblArea';
	$.ajax({
		type: "POST",
		url: action,
		data: {ejercicio: ejercicio, id: idAT, idInstitucion: idInstitucion, codigoArea: codigoArea, rut: rut, dv: dv, nombre: nombre},
		datatype: 'json',
		complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		}, success: function (data) {
			$("#divPopUpEditarAreaT").dialog("close");
			//jAlert(data.Message, "Alerta");
			load($('#cbEjercicio').val(), $('#cbSector').attr('data-cod'), $('#cbInstitucion').attr('data-cod'))
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log('Error');
		}
	});
	console.log("updAreaT - FIN: ");


}


function delSector(id){

	console.log("tblAreaTransaccional - delSector()");
	console.log("id :"+ id);
	var ejercicio = $('#cbEjercicio option:selected').val();
	console.log("ejercicio: "+ ejercicio);

	var action = 'mantenedores/delTblSector';
	$.ajax({
		type: "POST",
		url: action,
		data: {
			idSector:id,
		},
		datatype: 'json',
		success: function(data){
			$("#divPopUpEliminarSector").dialog("close");

			console.log("ejercicio:" + $('#cbEjercicio').val());
			console.log("sector:" + $('#cbSector').val());
			console.log("institucion:" + $('#cbInstitucion').val());


			load($('#cbEjercicio').val(),$('#cbSector').val(),$('#cbInstitucion').val());
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('Error');
		}

	});

}


function delInstitucion(id){

	console.log("tblAreaTransaccional - delInstitucion()");

	var ejercicio = $('#cbEjercicio option:selected').val();


	console.log("id "+id);
	console.log("ejercicio: "+ ejercicio);


	var action = 'mantenedores/delTblInstitucion';
	$.ajax({
		type: "POST",
		url: action,
		data: {
			id:id,
			ejercicio: ejercicio
		},
		datatype: 'json',
		success: function(data){
			$("#divPopUpEliminarInstitucion").dialog("close");
			load($('#cbEjercicio').val(),$('#cbSector').val(),$('#cbInstitucion').val());
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('Error');
		}

	});

}


function delAT(id){

	console.log("tblAreaTransaccional - delAT()");

	var ejercicio = $('#cbEjercicio option:selected').val();


	console.log("id "+id);
	console.log("ejercicio: "+ ejercicio);

	var action = 'mantenedores/delTblAreaT';
	$.ajax({
		type: "POST",
		url: action,
		data: {
			id:id,
			ejercicio:ejercicio},
		datatype: 'json',
		success: function(data){
			$("#divPopUpEliminarAT").dialog("close");
			load($('#cbEjercicio').val(),$('#cbSector').val(),$('#cbInstitucion').val());
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log('Error');
		}

	});

}


function botonCancelarEditarSector(){
	console.log("botonCancelarEditarSector");
	$("#divPopUpEditarSector").dialog("close");
	console.log("botonCancelarEditarSector - FIN");
}
function botonCancelarEditarInstitucion(){
	console.log("botonCancelarEditarInstitucion");
	$("#divPopUpEditarInstitucion").dialog("close");
	console.log("botonCancelarEditarInstitucion - FIN");
}
function botonCancelarEditarAreaT(){
	$("#divPopUpEditarAreaT").dialog("close");
}
function botonCancelarCrearIns(){
	console.log("botonCancelarCrearIns");
	$("#divPopUpInstitucion").dialog("close");
	console.log("botonCancelarCrearIns -FIN");
}
function botonCancelarCrearAT(){
	console.log("botonCancelarCrearAT");
	$("#divPopUpAreaT").dialog("close");
	console.log("botonCancelarCrearAT - FIN");
}
function botonCancelarDelteSect(){
	$("#divPopUpEliminarSector").dialog("close");
}
function botonCancelarDeleteInst(){
	console.log("botonCancelarDeleteInst");
	$("#divPopUpEliminarInstitucion").dialog("close");
	console.log("botonCancelarDeleteInst - FIN");
}
function botonCancelarDeleteAT(){
	console.log("botonCancelarDeleteAT");
	$("#divPopUpEliminarAT").dialog("close");
	console.log("botonCancelarDeleteAT");
}




