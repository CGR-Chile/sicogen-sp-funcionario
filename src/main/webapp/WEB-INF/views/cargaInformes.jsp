<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <spring:url value="/resources/css/system.css" var="system"/>
    <spring:url value="/resources/css/template.css" var="template"/>
    <spring:url value="/resources/css/rounded.css" var="rounded"/>
    <spring:url value="/resources/css/login.css" var="login"/>
    <spring:url value="/resources/css/pestanas.css" var="pestanas"/>
    <spring:url value="/resources/img" var="images"/>
    <spring:url value="/resources/js" var="js"/>
    <spring:url value="/resources/css/css-loader.css" var="loader"/>
    <spring:url value="/resources/css/jquery-ui/1.12.1/jquery-ui.css" var="ui"/>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/jtable/themes/jqueryui/" var="images"/>
    <spring:url value="/resources/css/cargaInforme.css" var="cargaInforme"/>

    <link type="text/css" href="${system}" rel="stylesheet"/>
    <link type="text/css" href="${template}" rel="stylesheet"/>
    <link type="text/css" href="${rounded}" rel="stylesheet"/>
    <link type="text/css" href="${login}" rel="stylesheet"/>
    <link type="text/css" href="${bootstrap}" rel="stylesheet"/>
    <link type="text/css" href="${loader}" rel="stylesheet"/>
    <link type="text/css" href="${ui}" rel="stylesheet"/>
    <link type="text/css" href="${cargaInforme}" rel="stylesheet"/>

    <script type="text/javascript" src="${js}/ajax.js"></script>
    <script type="text/javascript" src="${js}/net.js"></script>
    <script type="text/javascript" src="${js}/nu.js"></script>
    <script type="text/javascript" src="${js}/functions.js"></script>
    <script type="text/javascript" src="${js}/dom-drag.js"></script>
    <script type="text/javascript" src="${js}/jquery/jquery-3.5.1.min.js"></script>

    <script type="text/javascript" src="${js}/popper.min.js"></script>
    <script type="text/javascript" src="${js}/bootstrap.min.js"></script>
    <script type="text/javascript" src="${js}/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${js}/additional-methods.min.js"></script>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/ju/jszip-2.5.0/dt-1.13.1/af-2.5.1/b-2.3.3/b-colvis-2.3.3/b-html5-2.3.3/b-print-2.3.3/cr-1.6.1/date-1.2.0/fc-4.2.1/fh-3.3.1/kt-2.8.0/r-2.4.0/rg-1.3.0/rr-1.3.1/sc-2.0.7/sb-1.4.0/sp-2.1.0/sl-1.5.0/sr-1.2.0/datatables.min.css"/>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/ju/jszip-2.5.0/dt-1.13.1/af-2.5.1/b-2.3.3/b-colvis-2.3.3/b-html5-2.3.3/b-print-2.3.3/cr-1.6.1/date-1.2.0/fc-4.2.1/fh-3.3.1/kt-2.8.0/r-2.4.0/rg-1.3.0/rr-1.3.1/sc-2.0.7/sb-1.4.0/sp-2.1.0/sl-1.5.0/sr-1.2.0/datatables.min.js"></script>


    <script type="text/javascript" src="${js}/cargaInforme.js"></script>
    <script type="text/javascript" src="${js}/jquery-ui.js"></script>
    <script type="text/javascript" src="${js}/validate.es.js"></script>


    <style type="text/css">
        .icoImage18 {
            width: 18px;
            height: 18px;
        }
    </style>
</head>
<body>
    <div class="py-3">
         <div class="container-fluid">
             <div class="card bg-secondary">
                 <div class="card-body">
                     <div class="row">
                         <div class="col-md-2">
                             <div class="form-group">
                                 <label>Documento a Cargar</label>
                                 <select class="form-control" id="selectFilterPanel">
                                     <option value="" selected="selected">Seleccione Opción</option>
                                     <option value="decreto-resolicion-ley">Decreto, Resolución o Ley</option>
                                     <option value="caratula-decreto-resolucion">Caratula de Decreto o Resolución</option>
                                 </select>
                             </div>
                         </div>
                     </div>
                     <div class="row" id="filters-decreto-resolicion-ley">
                         <div class="col-md-2">
                             <div class="form-group">
                                 <label>Tipo Informe</label>
                                 <select class="form-control" id="selectTipoInforme">
                                     <option value="-1">Presupuestario-Contable</option>
                                 </select>
                             </div>
                         </div>
                         <div class="col-md-2">
                             <div class="form-group">
                                 <label>Informe</label>
                                 <select class="form-control" id="selectInforme">
                                     <option value="-1">Seleccione Informe</option>
                                     <c:forEach var="informe" items="${informesPresupuestarios}">
                                         <option value="${informe.id}">${informe.nombre}</option>
                                     </c:forEach>
                                 </select>
                             </div>
                         </div>
                         <div class="col-md-8" id="divFiltrosSelect">

                         </div>
                     </div>
                     <div class="row" id="filters-caratula-decreto-resolucion">
                         <div class="col-md-2">
                             <div class="form-group">
                                 <label>Tipo Informe</label>
                                 <select class="form-control" id="selectPresupuestoContable">
                                     <option value="-1">Presupuestario-Contable</option>
                                 </select>
                             </div>
                         </div>
                         <div class="col-md-2">
                             <div class="form-group">
                                 <label>Tipo Documento</label>
                                 <select class="form-control" id="selectTipoDocumento">
                                     <option value="">Todos</option>
                                     <option value="DECRETO">DECRETO</option>
                                     <option value="RESOLUCION">RESOLUCIÓN</option>
                                 </select>
                             </div>
                         </div>
                         <div class="col-md-2">
                             <div class="form-group">
                                 <label>Ejericicio</label>
                                 <select class="form-control" id="selectEjercicioFilter">
                                 </select>
                             </div>
                         </div>
                         <div class="col-md-2 align-self-center mt-06rem">
                             <button type="button" class="btn btn-primary" id="searchCaratulas">Buscar</button>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
     </div>
    <div id="divCargaDocumento">
         <div class="py-3">
             <div class="container-fluid">
                 <div class="row">
                     <div class="col-md-12">
                         <table class="adminlist">
                             <thead>
                                 <tr>
                                     <td class="title-table"><span class="Estilo11"><b>Carga de Documentos</b></span></td>
                                 </tr>
                                 <tr class="title-grid" colspan="2">
                                     <td></td>
                                 </tr>
                             </thead>
                         </table>
                     </div>
                 </div>
             </div>
         </div>
     </div>
    <div id="divCaratulaDocumento">
        <div class="py-3">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <table class="adminlist">
                            <thead>
                            <tr>
                                <td class="title-table"><span class="Estilo11"><b>Carátulas de Documentos</b></span></td>
                                <td align= "right" class="title-table padding-0">
                                    <a id="openFormCaratula" class="text-decoration-none background-3F87C1 cursor-pointer padding-04-rem">
                                        <img src="${images}add.png" alt="Crear Carátula"/>
                                        Crear Carátula
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td class="title-grid"></td>
                                <td class="title-grid"></td>
                            </tr>
                            </thead>
                        </table>
                        <table id="example" class="display" style="width:100%">
                            <thead>
                            <tr>
                                <th>Entidad emisora</th>
                                <th>Tipo de documento</th>
                                <th>N° Documento</th>
                                <th>Año</th>
                                <th>Fecha de documento</th>
                                <th>Fecha Recepción CGR</th>
                                <th>Analista</th>
                                <th style="text-align: center;">Bitácora</th>
                                <th style="text-align: center;">Digitación</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="divContenido">
    </div>
    <div id="dialogValidacion" title="Información">
    </div>
    <div id="loading-spinner" style="z-index: 9999; position: absolute;" class="loader loader-default"
         data-text="Cargando Informaci&oacute;n..."></div>

    <div id="dialog-form-caratula">
        <form id="caratula-form" >
            <div class="form-row">
                <div class="form-group col-md-12">
                    <label>Los campos con (*) son requeridos </label>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Tipo de documento</label>
                    <select class="form-control" id="selectDocumentType" name="tipoDocumento">
                        <option value="">Seleccione</option>
                        <option value="DECRETO">DECRETO</option>
                        <option value="RESOLUCION">RESOLUCIÓN</option>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label>* Ejercicio</label>
                    <select class="form-control" id="selectEjercicio" name="ejercicioId">
                        <option value="">Seleccione</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Número de documento</label>
                    <input type="number" name="nroDocumento" class="form-control" maxlength="5">
                </div>
                <div class="form-group col-md-6">
                    <label>* Fecha de documento</label>
                    <input type="text" class="form-control" id="document-date" name="fechaDocumento" readonly>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Entidad Emisora</label>
                    <select class="form-control" id="entidadEmisora" name="entidadEmisora">
                        <option value="">Seleccione</option>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label>* Fecha Recepción CGR</label>
                    <input type="text" class="form-control" id="reception-date-cgr" name="fechaRecepcionCGR" readonly>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Analista</label>
                    <input type="text" class="form-control" name="analista">
                </div>
                <div class="form-group col-md-6">
                    <label>* Materia General</label>
                    <input type="text" class="form-control" name="materiaGeneral">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Materia Específica</label>
                    <input type="text" class="form-control" name="materiaEspecifica">
                </div>
            </div>
        </form>
    </div>
    <div id="dialog-delete-caratula" style="display: none">
        <p>¿Desea eliminar esta carátula?</p>
    </div>
    <div id="dialog-form-caratula-edit" style="display: none">
        <form id="caratula-form-edit">
            <div class="form-row">
                <div class="form-group col-md-12">
                    <label>Los campos con (*) son requeridos </label>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Tipo de documento</label>
                    <select class="form-control" id="selectDocumentTypeEdit" name="tipoDocumento">
                        <option value="">Seleccione</option>
                        <option value="DECRETO">DECRETO</option>
                        <option value="RESOLUCION">RESOLUCIÓN</option>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label>* Ejercicio</label>
                    <select class="form-control" id="selectEjercicioEdit" name="ejercicioId">
                        <option value="">Seleccione</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Número de documento</label>
                    <input type="number" name="nroDocumento" id="number-document-edit" class="form-control" maxlength="5">
                </div>
                <div class="form-group col-md-6">
                    <label>* Fecha de documento</label>
                    <input type="text" class="form-control" id="document-date-edit" name="fechaDocumento" readonly>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Entidad Emisora</label>
                    <select class="form-control" id="entidadEmisoraEdit" name="entidadEmisora">
                        <option value="">Seleccione</option>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label>* Fecha Recepción CGR</label>
                    <input type="text" class="form-control" id="reception-date-cgr-edit" name="fechaRecepcionCGR" readonly>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Analista</label>
                    <input type="text" class="form-control" name="analista" id="analistaEdit">
                </div>
                <div class="form-group col-md-6">
                    <label>* Materia General</label>
                    <input type="text" class="form-control" name="materiaGeneral" id="materiaGeneralEdit">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>* Materia Específica</label>
                    <input type="text" class="form-control" name="materiaEspecifica" id="materiaEspecificaEdit">
                </div>
            </div>
        </form>
    </div>
    <div id="dialog-bitacora-caratula" style="display: none">
        <table id="table-bitacora" class="display" style="width:100%">
            <thead>
            <tr>
                <th>Usuario</th>
                <th>Acción</th>
                <th style="text-align: center">Fecha Hora</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>

</body>
</html>
