function rendertable(div){

	var element = div;
	while(element.childNodes.length){
		element.removeChild(element.lastChild);
	}
}

function buscarDocumentos(){

	var idAnno = document.getElementById('bdAnnos').value;
	var idEntidad = document.getElementById('bdEntidades').value;
	var idTipo = document.getElementById('bdTipo').value;
	var numeroDocumento = document.getElementById('bdDocumento').value;

	window.parent.cargando();
	var url = '../documentos/list?entidad='+idEntidad+"&anno="+idAnno+"&tipo="+idTipo+"&numero="+numeroDocumento;
	var cf  = new net.ContentLoader(url,cargaDocumentos,"",null,null,null);

}

function cargaDocumentos(){
	var data       	= this.req.responseXML;
	var elemento   	= document.getElementById("scrollarea-content");
	var childNodes 	= data.getElementsByTagName("listaDocumentos");
	var gko        	= navigator.userAgent.toLowerCase();

    window.parent.stop();
	if(elemento.hasChildNodes()){
		rendertable(elemento);
	}

	var tablaListado = document.createElement("table");
	tablaListado.id = "tablaMonedas";

	var tbodyListado = document.createElement("tbody");

	if(childNodes.length>0){
		ocultarDiv('registros');

		for(i=0;i<childNodes.length;i++){
			var trListado = document.createElement("tr");


			tablaListado.className = "adminlist";
			trListado.className = childNodes[i].getElementsByTagName("rowClass")[0].childNodes[0].nodeValue;


			var tdAnno = document.createElement("td");
			tdAnno.width ="10%";
			tdAnno.setAttribute("align","center");
			var spanAnno = document.createElement("span");
			spanAnno.className= "editlinktip hasTip";
			spanAnno.setAttribute("title",childNodes[i].getElementsByTagName("anno")[0].childNodes[0].nodeValue);
			var txAnno = document.createTextNode(childNodes[i].getElementsByTagName("anno")[0].childNodes[0].nodeValue);
			spanAnno.appendChild(txAnno);
			tdAnno.appendChild(spanAnno);
			trListado.appendChild(tdAnno);

			var tdEntidad = document.createElement("td");
			tdEntidad.width ="20%";
			tdEntidad.setAttribute("align","center");
			var spanEntidad = document.createElement("span");
			spanEntidad.className= "editlinktip hasTip";
			spanEntidad.setAttribute("title",childNodes[i].getElementsByTagName("entidad")[0].childNodes[0].nodeValue);
			var txtEntidad = document.createTextNode(childNodes[i].getElementsByTagName("entidad")[0].childNodes[0].nodeValue);
			spanEntidad.appendChild(txtEntidad);
			tdEntidad.appendChild(spanEntidad);
			trListado.appendChild(tdEntidad);

			var tdTipo = document.createElement("td");
			tdTipo.width ="10%";
			tdTipo.setAttribute("align","center");
			var spanTipo = document.createElement("span");
			spanTipo.className= "editlinktip hasTip";
			spanTipo.setAttribute("title",childNodes[i].getElementsByTagName("tipo")[0].childNodes[0].nodeValue);
			var txtTipo = document.createTextNode(childNodes[i].getElementsByTagName("tipo")[0].childNodes[0].nodeValue);
			spanTipo.appendChild(txtTipo);
			tdTipo.appendChild(spanTipo);
			trListado.appendChild(tdTipo);

			var tdNumero = document.createElement("td");
			tdNumero.width ="10%";
			tdNumero.setAttribute("align","center");
			var spanNumero = document.createElement("span");
			spanNumero.className= "editlinktip hasTip";
			spanNumero.setAttribute("title",childNodes[i].getElementsByTagName("numero")[0].childNodes[0].nodeValue);
			var txtNumero = document.createTextNode(childNodes[i].getElementsByTagName("numero")[0].childNodes[0].nodeValue);
			spanNumero.appendChild(txtNumero);
			tdNumero.appendChild(spanNumero);
			trListado.appendChild(tdNumero);

			var tdReingreso = document.createElement("td");
			tdReingreso.width ="10%";
			tdReingreso.setAttribute("align","center");
			var spanReingreso = document.createElement("span");
			spanReingreso.className= "editlinktip hasTip";
			spanReingreso.setAttribute("title",childNodes[i].getElementsByTagName("reingreso")[0].childNodes[0].nodeValue);
			var txtReingreso = document.createTextNode(childNodes[i].getElementsByTagName("reingreso")[0].childNodes[0].nodeValue);
			spanReingreso.appendChild(txtReingreso);
			tdReingreso.appendChild(spanReingreso);
			trListado.appendChild(tdReingreso);

			var tdDescarga = document.createElement("td");
			tdDescarga.width = "9.2%";
			tdDescarga.setAttribute("align", "center");

			var spanDescarga = document.createElement("img");
			spanDescarga.src = "/sicogen-mf/resources/img/header/icon-48-install.png";
			spanDescarga.setAttribute('width', '24px;');
			spanDescarga.setAttribute('height', '20px;');
			spanDescarga.setAttribute("onclick", "javascript:DescargaArchivo('" + childNodes[i].getElementsByTagName("idFileUpload")[0].childNodes[0].nodeValue + "');");
			var txtDescarga = document.createTextNode("Descargar");
			spanDescarga.appendChild(txtDescarga);
			tdDescarga.appendChild(spanDescarga);
			trListado.appendChild(tdDescarga)


			tbodyListado.appendChild(trListado);
			tablaListado.appendChild(tbodyListado);
			elemento.appendChild(tablaListado);

		}

	}else{
		mostrarDiv('registros');
	}

}

function DescargaArchivo(idArchivo){

	var url='../documentos/descargaDoc?idArchivo='+idArchivo;
	location.href = url;
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