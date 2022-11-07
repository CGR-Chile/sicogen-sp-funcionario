// JavaScript Document

function rendertable(div){

    var element = div;
    while(element.childNodes.length){
        element.removeChild(element.lastChild);
    }
}
function buscarValidacionTDR() {

    var entidad = document.getElementById('bdEntidadEmisora').value;
    var anno = document.getElementById('bdAnoDocumento').value;
    var tipo = document.getElementById('bdTipoDocumentoTDR').value;
    var numero = document.getElementById('bdNumDocumento').value;


    if(anno.trim()==""){

        $.confirm({
            title: 'Alerta',
            content: 'Debe Ingresar el a&ntilde;o para ejecutar la b&uacute;squeda',
            type: 'blue',
            typeAnimated: true,
            buttons: {
                tryAgain:{
                    text:'Aceptar',
                    btnClass: 'btn-blue',
                    action: function () {
                    }
                },

            }
        });
        return;
    }else {
        window.parent.cargando();
        var url = '../validacion/list?entidad=' + entidad + "&anno=" + anno + "&tipo=" + tipo + "&numero=" + numero;
        var cf = new net.ContentLoader(url, cargaListaTDR, "", null, null, null);
    }
}

function cargaListaTDR() {

    var data       	= this.req.responseXML;
    var elemento   	= document.getElementById("scrollarea-content");
    var childNodes 	= data.getElementsByTagName("listaDocumentosTDR");
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

            var tdnumeroDocTDR = document.createElement("td");
            tdnumeroDocTDR.width ="8%";
            tdnumeroDocTDR.setAttribute("align","center");
            var spanNumeroDocTDR = document.createElement("span");
            spanNumeroDocTDR.className= "editlinktip hasTip";
            spanNumeroDocTDR.setAttribute("title",childNodes[i].getElementsByTagName("NumeroDoc")[0].childNodes[0].nodeValue);
            var txNumeroDocTDR = document.createTextNode(childNodes[i].getElementsByTagName("NumeroDoc")[0].childNodes[0].nodeValue);
            spanNumeroDocTDR.appendChild(txNumeroDocTDR);
            tdnumeroDocTDR.appendChild(spanNumeroDocTDR);
            trListado.appendChild(tdnumeroDocTDR);

            var tdEntidadEmisoraTDR = document.createElement("td");
            tdEntidadEmisoraTDR.width ="20%";
            tdEntidadEmisoraTDR.setAttribute("align","center");
            var spanEntidadEmisoraTDR = document.createElement("span");
            spanEntidadEmisoraTDR.className= "editlinktip hasTip";
            spanEntidadEmisoraTDR.setAttribute("title",childNodes[i].getElementsByTagName("EntidadEmisora")[0].childNodes[0].nodeValue);
            var txtEntidadEmisoraTDR = document.createTextNode(childNodes[i].getElementsByTagName("EntidadEmisora")[0].childNodes[0].nodeValue);
            spanEntidadEmisoraTDR.appendChild(txtEntidadEmisoraTDR);
            tdEntidadEmisoraTDR.appendChild(spanEntidadEmisoraTDR);
            trListado.appendChild(tdEntidadEmisoraTDR);

            var tdTipoDocTDR = document.createElement("td");
            tdTipoDocTDR.width ="10%";
            tdTipoDocTDR.setAttribute("align","center");
            var spanTipoDocTDR = document.createElement("span");
            spanTipoDocTDR.className= "editlinktip hasTip";
            spanTipoDocTDR.setAttribute("title",childNodes[i].getElementsByTagName("TipoDoc")[0].childNodes[0].nodeValue);
            var txtTipoDocTDR = document.createTextNode(childNodes[i].getElementsByTagName("TipoDoc")[0].childNodes[0].nodeValue);
            spanTipoDocTDR.appendChild(txtTipoDocTDR);
            tdTipoDocTDR.appendChild(spanTipoDocTDR);
            trListado.appendChild(tdTipoDocTDR);

            var tdFechaDocTDR = document.createElement("td");
            tdFechaDocTDR.width ="10%";
            tdFechaDocTDR.setAttribute("align","center");
            var spanFechaDocTDR = document.createElement("span");
            spanFechaDocTDR.className= "editlinktip hasTip";
            spanFechaDocTDR.setAttribute("title",childNodes[i].getElementsByTagName("FechaDoc")[0].childNodes[0].nodeValue);
            var txtFechaDocTDR = document.createTextNode(childNodes[i].getElementsByTagName("FechaDoc")[0].childNodes[0].nodeValue);
            spanFechaDocTDR.appendChild(txtFechaDocTDR);
            tdFechaDocTDR.appendChild(spanFechaDocTDR);
            trListado.appendChild(tdFechaDocTDR);

            var estadoSicogen = childNodes[i].getElementsByTagName("EstadoSicogen")[0].childNodes[0].nodeValue;

            var tdEstadoDocTDR = document.createElement("td");
            tdEstadoDocTDR.width = "10%";
            tdEstadoDocTDR.setAttribute("align", "center");

            var spanEstadoDocTDR = document.createElement("img");
            if(estadoSicogen=="CON ERRORES") {
                spanEstadoDocTDR.src = "/sicogen-mf/resources/img/errorCarga.png";
                spanEstadoDocTDR.setAttribute('title', 'CON ERRORES');
            }else if(estadoSicogen=="VALIDADO"){
                spanEstadoDocTDR.src = "/sicogen-mf/resources/img/header/icon-48-checkin.png";
                spanEstadoDocTDR.setAttribute('title', 'VALIDADO');
            }else if(estadoSicogen=="CARGADO"){
                spanEstadoDocTDR.src = "/sicogen-mf/resources/img/cargado.png";
                spanEstadoDocTDR.setAttribute('title', 'CARGADO');
            }
            spanEstadoDocTDR.setAttribute('width', '24px;');
            spanEstadoDocTDR.setAttribute('height', '20px;');

            tdEstadoDocTDR.appendChild(spanEstadoDocTDR);
            trListado.appendChild(tdEstadoDocTDR);

            var tdFechaValidacionTDR = document.createElement("td");
            tdFechaValidacionTDR.width ="10%";
            tdFechaValidacionTDR.setAttribute("align","center");
            var spanFechaValidacionTDR = document.createElement("span");
            spanFechaValidacionTDR.className= "editlinktip hasTip";

            if(childNodes[i].getElementsByTagName("FechaValidacion")[0].childNodes[0].nodeValue=="null"){
                spanFechaValidacionTDR.setAttribute("title","");
                var txtFechaValidacionTDR = document.createTextNode("");
            }else{
                spanFechaValidacionTDR.setAttribute("title",childNodes[i].getElementsByTagName("FechaValidacion")[0].childNodes[0].nodeValue);
                var txtFechaValidacionTDR = document.createTextNode(childNodes[i].getElementsByTagName("FechaValidacion")[0].childNodes[0].nodeValue);
            }

            spanFechaValidacionTDR.appendChild(txtFechaValidacionTDR);
            tdFechaValidacionTDR.appendChild(spanFechaValidacionTDR);
            trListado.appendChild(tdFechaValidacionTDR);


            var tdValidacionTDR = document.createElement("td");
            tdValidacionTDR.width = "10%";
            tdValidacionTDR.setAttribute("align", "center");

            var spanValidacionTDR = document.createElement("img");
            if(childNodes[i].getElementsByTagName("FechaValidacion")[0].childNodes[0].nodeValue=="null"){
                spanValidacionTDR.src = "/sicogen-mf/resources/img/blanco.png";
            }else{
                spanValidacionTDR.src = "/sicogen-mf/resources/img/header/icon-48-article-add.png";
                spanValidacionTDR.setAttribute('title', 'Reporte Validacion');
                spanValidacionTDR.setAttribute("onclick", "javascript:ReporteValidacionTDR('" + childNodes[i].getElementsByTagName("IdFileUpload")[0].childNodes[0].nodeValue + "');");
            }

            spanValidacionTDR.setAttribute('width', '24px;');
            spanValidacionTDR.setAttribute('height', '20px;');

            tdValidacionTDR.appendChild(spanValidacionTDR);
            trListado.appendChild(tdValidacionTDR);

            var tdValidarTDR = document.createElement("td");
            tdValidarTDR.width = "9.1%";
            tdValidarTDR.setAttribute("align", "center");


            var idFileU = childNodes[i].getElementsByTagName("IdFileUpload")[0].childNodes[0].nodeValue;//IdFileUpload
            var ejercicio = childNodes[i].getElementsByTagName("EjercicioID")[0].childNodes[0].nodeValue;//EjercicioID;
            var idInforme = childNodes[i].getElementsByTagName("IdValidacion")[0].childNodes[0].nodeValue;//IdValidacion??
            var tipoInforme = childNodes[i].getElementsByTagName("TipoInforme")[0].childNodes[0].nodeValue;//NumeroDoc??
            var nroDoc = childNodes[i].getElementsByTagName("NumeroDoc")[0].childNodes[0].nodeValue;//NumeroDoc??

            var spanValidarTDR = document.createElement("input");
            spanValidarTDR.type = "button";
            spanValidarTDR.name = "Validar";
            spanValidarTDR.value = "Validar";
            spanValidacionTDR.setAttribute('width', '24px;');
            spanValidarTDR.setAttribute("onclick", "javascript:reValidarInformeTDR('" + idFileU + "', '" + ejercicio + "', '" + idInforme + "', '" + tipoInforme + "', '" + nroDoc + "');");

            tdValidarTDR.appendChild(spanValidarTDR);
            trListado.appendChild(tdValidarTDR);

            tbodyListado.appendChild(trListado);
            tablaListado.appendChild(tbodyListado);
            elemento.appendChild(tablaListado);

        }

    }else{
        mostrarDiv('registros');
    }
}

function ReporteValidacionTDR(id) {
    alert(id);
}

function reValidarInformeTDR(idFileU, ejercicio, idInforme, tipoInforme, nroDoc) {


    var tipoDocumento = document.getElementById('bdTipoDocumentoTDR').value;
    var url = '../validacion/validarNegocioTDR?idFileUpload='+idFileU+"&ejercicio="+ejercicio+"&idInforme="+tipoInforme+"&tipoInforme="+tipoDocumento +"&nroSistradoc="+nroDoc +"&informe="+tipoInforme;
    window.parent.cargando();
    var cf  = new net.ContentLoader(url,ValidacionTDR2,"",null,null,null);


}

function ValidacionTDR2() {

    var entidad = document.getElementById('bdEntidadEmisora').value;
    var anno = document.getElementById('bdAnoDocumento').value;
    var tipo = document.getElementById('bdTipoDocumentoTDR').value;
    var numero = document.getElementById('bdNumDocumento').value;


    var url = '../validacion/list?entidad='+entidad+"&anno="+anno+"&tipo="+tipo+"&numero="+numero;
    var cf  = new net.ContentLoader(url,cargaListaTDR,"",null,null,null);
}

function mostrarDiv(div){

    document.getElementById(div).style.display = "block";
    document.getElementById(div).style.visibility = "visible";

}

function ocultarDiv(div){

    document.getElementById(div).style.display = "none";
    document.getElementById(div).style.visibility = "hidden";
}