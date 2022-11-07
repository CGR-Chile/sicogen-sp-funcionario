function rendertable(div){

	var element = div;
	while(element.childNodes.length){
		element.removeChild(element.lastChild);
	}
}

function listadoInformeTDR(){

	//window.parent.cargando();
	var url = '../asignacion/list';
	var cf  = new net.ContentLoader(url,cargaDocumentos,"",null,null,null);

}

function cargaDocumentos(){
	var data       	= this.req.responseXML;
	var elemento   	= document.getElementById("scrollarea-content");
	var childNodes 	= data.getElementsByTagName("listaTDR");
	var gko        	= navigator.userAgent.toLowerCase();

   // window.parent.stop();
	if(elemento.hasChildNodes()){
		rendertable(elemento);
	}

	var tablaListado = document.createElement("table");
	tablaListado.id = "tblAsigTDR";

	var tbodyListado = document.createElement("tbody");

	if(childNodes.length>0){
		ocultarDiv('registros');

		for(i=0;i<childNodes.length;i++){
			var trListado = document.createElement("tr");

			tablaListado.className = "adminlist";
			trListado.className = childNodes[i].getElementsByTagName("rowClass")[0].childNodes[0].nodeValue;

			var tdEntidadEmisora = document.createElement("td");
			tdEntidadEmisora.width ="30%";
			tdEntidadEmisora.setAttribute("align","center");
			var spanEntidadEmisora = document.createElement("span");
			spanEntidadEmisora.className= "editlinktip hasTip";
			spanEntidadEmisora.setAttribute("title",childNodes[i].getElementsByTagName("ENTIDAD_EMISORA")[0].childNodes[0].nodeValue);
			var txtEntidadEmisora = document.createTextNode(childNodes[i].getElementsByTagName("ENTIDAD_EMISORA")[0].childNodes[0].nodeValue);
			spanEntidadEmisora.appendChild(txtEntidadEmisora);
			tdEntidadEmisora.appendChild(spanEntidadEmisora);
			trListado.appendChild(tdEntidadEmisora);

			var tdTipoDocumento = document.createElement("td");
			tdTipoDocumento.width ="10%";
			tdTipoDocumento.setAttribute("align","center");
			var spanTipoDocumento = document.createElement("span");
			spanTipoDocumento.className= "editlinktip hasTip";
			spanTipoDocumento.setAttribute("title",childNodes[i].getElementsByTagName("TIPO_DOCUMENTO")[0].childNodes[0].nodeValue);
			var txtTipoDocumento = document.createTextNode(childNodes[i].getElementsByTagName("TIPO_DOCUMENTO")[0].childNodes[0].nodeValue);
			spanTipoDocumento.appendChild(txtTipoDocumento);
			tdTipoDocumento.appendChild(spanTipoDocumento);
			trListado.appendChild(tdTipoDocumento);

			var tdNumeroDocumento = document.createElement("td");
			tdNumeroDocumento.width ="10%";
			tdNumeroDocumento.setAttribute("align","center");
			var spanNumeroDocumento = document.createElement("span");
			spanNumeroDocumento.className= "editlinktip hasTip";
			spanNumeroDocumento.setAttribute("title",childNodes[i].getElementsByTagName("NUMERO_DOCUMENTO")[0].childNodes[0].nodeValue);
			var txtTipo = document.createTextNode(childNodes[i].getElementsByTagName("NUMERO_DOCUMENTO")[0].childNodes[0].nodeValue);
			spanNumeroDocumento.appendChild(txtTipo);
			tdNumeroDocumento.appendChild(spanNumeroDocumento);
			trListado.appendChild(tdNumeroDocumento);

			var tdFechaDocumento = document.createElement("td");
			tdFechaDocumento.width ="10%";
			tdFechaDocumento.setAttribute("align","center");
			var spanFechaDocumento = document.createElement("span");
			spanFechaDocumento.className= "editlinktip hasTip";
			spanFechaDocumento.setAttribute("title",childNodes[i].getElementsByTagName("FECHA_DOCUMENTO")[0].childNodes[0].nodeValue);
			var txtFechaDocumento = document.createTextNode(childNodes[i].getElementsByTagName("FECHA_DOCUMENTO")[0].childNodes[0].nodeValue);
			spanFechaDocumento.appendChild(txtFechaDocumento);
			tdFechaDocumento.appendChild(spanFechaDocumento);
			trListado.appendChild(tdFechaDocumento);

			var tdFechaRecepcion = document.createElement("td");
			tdFechaRecepcion.width ="10%";
			tdFechaRecepcion.setAttribute("align","center");
			var spanFechaRecepcion = document.createElement("span");
			spanFechaRecepcion.className= "editlinktip hasTip";
			spanFechaRecepcion.setAttribute("title",childNodes[i].getElementsByTagName("FECHA_RECEPCION")[0].childNodes[0].nodeValue);
			var txtFechaRecepcion = document.createTextNode(childNodes[i].getElementsByTagName("FECHA_RECEPCION")[0].childNodes[0].nodeValue);
			spanFechaRecepcion.appendChild(txtFechaRecepcion);
			tdFechaRecepcion.appendChild(spanFechaRecepcion);
			trListado.appendChild(tdFechaRecepcion);

			var tdCatalogado = document.createElement("td");
			tdCatalogado.width = "10%";
			tdCatalogado.setAttribute("align", "center");

			var catalogado = childNodes[i].getElementsByTagName("CATALOGADO")[0].childNodes[0].nodeValue;

			var spanCatalogado = document.createElement("img");
			if(catalogado=="1"){
				spanCatalogado.src = "/sicogen-mf/resources/img/error_carga.png";
			}else{
				spanCatalogado.src = "/sicogen-mf/resources/img/header/icon-48-checkin.png";
			}
			//spanDescarga.src = "/sicogen-mf/resources/img/header/icon-48-install.png";
			spanCatalogado.setAttribute('width', '24px;');
			spanCatalogado.setAttribute('height', '20px;');
			//spanDescarga.setAttribute("onclick", "javascript:DescargaArchivo('" + childNodes[i].getElementsByTagName("idFileUpload")[0].childNodes[0].nodeValue + "');");
			var txtCatalogado = document.createTextNode("Catalogado");
			spanCatalogado.appendChild(txtCatalogado);
			tdCatalogado.appendChild(spanCatalogado);
			trListado.appendChild(tdCatalogado)

			var tdcheck = document.createElement("td");
			tdcheck.width ="9.1%";
			tdcheck.setAttribute("align","center");
			var spancheck = document.createElement("input");
			spancheck.type= "checkbox";
			spancheck.id = "chk_asignacion"+"_"+childNodes[i].getElementsByTagName("ID_SISTRADOC")[0].childNodes[0].nodeValue;
			//spancheck.setAttribute("onclick","javascript:asignar('" + childNodes[i].getElementsByTagName("ID_SISTRADOC")[0].childNodes[0].nodeValue + "');");
			var txtcheck = document.createTextNode("Catalogar");
			spancheck.appendChild(txtcheck);
			tdcheck.appendChild(spancheck);
			trListado.appendChild(tdcheck);

			tbodyListado.appendChild(trListado);
			tablaListado.appendChild(tbodyListado);
			elemento.appendChild(tablaListado);

		}

	}else{
		mostrarDiv('registros');
	}

}

function mostrarDiv(div){

	document.getElementById(div).style.display = "block";
	document.getElementById(div).style.visibility = "visible";

}

function ocultarDiv(div){

	document.getElementById(div).style.display = "none";
	document.getElementById(div).style.visibility = "hidden";
}
// JavaScript Document

function cargaPeriodoTDR(idEjercicio){

	var idEjercicio = idEjercicio;
	var idInforme = document.getElementById('contTipoInformes').value;

	if(idInforme=="-1"){
		var texto = "Debe Seleccionar un Tipo de Informe";
		var titulo = "Asignacion TDR";
		document.getElementById("textoMsg").innerHTML = texto;
		document.getElementById("tituloMsg").innerHTML = titulo;
		document.getElementById("imagenMsg").src = "/sicogen-mf/resources/img/notice-note.png";
		mostrarDiv("root");
		return;
	}else {
		var url = '../cargacombos/getPeriodosByInformes?idEjercicio=' + idEjercicio + "&idInforme=" + idInforme;
		var cf = new net.ContentLoader(url, comboPeriodoTDR, "", null, null, null);
	}
}


function comboPeriodoTDR(){

	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PERIODO")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("NOMBRE_PERIODO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaMensajeTablaTDR();

}

function cargaMensajeTablaTDR() {
	var objSelect1 =  document.getElementById("contPeriodosTDR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Periodo.",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

function asignarTDR(){

	var informe = $('#contTipoInformes option:selected').val();
	var ejercicio = $('#cbEjercicioAsigTDR option:selected').val();
	var idPeriodo = $('#contPeriodosTDR option:selected').val();
	var selected = [];
	$('#tblAsigTDR input:checked').each(function() {
		selected.push($(this).attr('id'));
	});

	if(informe=="-1"){
		var texto = "Debe Seleccionar un tipo de Informe";
		var titulo = "Informacion";
		document.getElementById("textoMsg").innerHTML = texto;
		document.getElementById("tituloMsg").innerHTML = titulo;
		document.getElementById("imagenMsg").src = "/sicogen-mf/resources/img/notice-note.png";
		mostrarDiv("root");
		return;
	}else if(ejercicio=="-1"){
		var texto = "Debe Seleccionar un Ejercicio";
		var titulo = "Informacion";
		document.getElementById("textoMsg").innerHTML = texto;
		document.getElementById("tituloMsg").innerHTML = titulo;
		document.getElementById("imagenMsg").src = "/sicogen-mf/resources/img/notice-note.png";
		mostrarDiv("root");
		return;
	}else if(idPeriodo=="-1"){
		var texto = "Debe Seleccionar un Periodo";
		var titulo = "Información";
		document.getElementById("textoMsg").innerHTML = texto;
		document.getElementById("tituloMsg").innerHTML = titulo;
		document.getElementById("imagenMsg").src = "/sicogen-mf/resources/img/notice-note.png";
		mostrarDiv("root");
		return;
	}else if(selected.length == 0){

		var texto = "Debe Seleccionar al menos un Documento";
		var titulo = "Informacion";
		document.getElementById("textoMsg").innerHTML = texto;
		document.getElementById("tituloMsg").innerHTML = titulo;
		document.getElementById("imagenMsg").src = "/sicogen-mf/resources/img/notice-note.png";
		mostrarDiv("root");
		return;
	}else {

		var documentos = "";

		for (var i = 0, len = selected.length; i < len; i++) {
			console.log("Check: " + selected[i]);
			documentos = documentos + "," +selected[i];
			console.log("documentos: " + documentos);
		}

		var idocs = documentos.split("chk_asignacion_").join("");
		console.log("idocs: " + idocs);

		var url = '../asignacion/asignarDocumentosTDR?idInforme=' + informe + "&idEjercicio=" + ejercicio + "&idPeriodo=" + idPeriodo + "&idocs=" + idocs;
		var cf = new net.ContentLoader(url, cargaDialogo, "", null, null, null);
	}
}

function cargaDialogo() {

	var dato = this.req.responseXML;
	var msjNodes = dato.getElementsByTagName("mensaje");

	if(msjNodes.length > 0) {
		nodo = msjNodes[0].getElementsByTagName("estado")[0].childNodes[0].nodeValue;

		if (nodo == "-2") {
			var texto = msjNodes[0].getElementsByTagName("texto")[0].childNodes[0].nodeValue;
			var titulo = "Error";
			document.getElementById("textoMsg").innerHTML = texto;
			document.getElementById("tituloMsg").innerHTML = titulo;
			document.getElementById("imagenMsg").src = "/sicogen-mf/resources/img/notice-alert.png";
			mostrarDiv("root");
		}else if(nodo == "0"){
			var texto = msjNodes[0].getElementsByTagName("texto")[0].childNodes[0].nodeValue;
			var titulo = "Asignacion TDR";
			document.getElementById("textoMsg").innerHTML = texto;
			document.getElementById("tituloMsg").innerHTML = titulo;
			document.getElementById("imagenMsg").src = "/sicogen-mf/resources/img/NotMovProc.png";
			mostrarDiv("root");
			listadoInformeTDR();
		}

	}
	
}