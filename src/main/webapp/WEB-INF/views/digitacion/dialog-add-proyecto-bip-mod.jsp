<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-31
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/js" var="js"/>
<script type="text/javascript" src="${js}/jquery.numeric.js"></script>
<div class="py-1">
    <div class="container-fluid">
        <div id="divBusquedaProyMod">
            <div class="row">
                <div class="col-md-10">
                    <h6>Buscar Proyecto BIP</h6>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary" id="btnNuevoProyectoMod">Nuevo</button>
                </div>
            </div>
            <div class="row d-flex justify-content-between">
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Código proyecto</label>
                        <input type="text" class="form-control" id="inptCodProyectoMod">
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="form-group">
                        <label>Resultado búsqueda</label>
                        <input type="text" class="form-control" value="" id="inptResultadoBusquedaMod" readonly>
                    </div>
                </div>
                <div class="col-md-2 align-self-center">
                    <button type="button" class="btn btn-primary" onclick="getProyectoByCodigoMod()">Buscar</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" id="divResultBusqProyMod" style="display: none;">
                    <span class="text-primary"><strong></strong></span>
                </div>
                <div class="col-md-12" id="divResultNOKMod" style="display: none;">
                    <span class="text-danger"><strong>NO ENCONTRADO</strong></span>
                </div>
            </div>
        </div>
        <div id="divNuevoProyMod" style="display: none;">
            <div class="row">
                <div class="col-md-10">
                    <h6>Nuevo Proyecto BIP</h6>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary" id="btnBuscarProyMod">Buscar</button>
                </div>
            </div>
            <div class="row d-flex justify-content-between">
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Código proyecto</label>
                        <input type="text" class="form-control" id="inptCodProyectoNewMod">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Nombre Proyecto</label>
                        <input type="text" class="form-control" value="" id="inptNombreProyMod">
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Estado Proyecto</label>
                        <select class="form-control" id="slctEstadoProyectoMod">
                            <option value="1">SIN APLICACION</option>
                            <option value="2">PREFACTIBILIDAD</option>
                            <option value="3">FACTIBILIDAD</option>
                            <option value="4">DISENO</option>
                            <option value="5">EJECUCION</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-2 align-self-center">
                    <button type="button" class="btn btn-primary" id="btnAceptarProyMod" onclick="addProyectoNuevoMod()">Aceptar</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" id="msnAgregacionMod" style="display: none;">
                    <span class="text-danger"><strong></strong></span>
                </div>
            </div>
        </div>
        <div class="row" style="border-collapse:collapse;">
            <div class="col-md-12">
                <h6>Monto Cuenta Asignación</h6>
            </div>
        </div>
        <c:forEach var="asig" items="${listaAsignaciones}">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>${asig.cuentaAsignacion} ${asig.cuentaNombre}</label>
                        <input type="text" class="form-control dinamicField2Mod" value="0" id="asig_mod_${asig.cuentaAsignacion}" name="asig_mod_${asig.cuentaAsignacion}_${asig.cuentaNombre}">
                    </div>
                </div>
            </div>
            <script>
                $(document).ready(function () {
                    $('#asig_mod_${asig.cuentaAsignacion}').numeric({ negative: false });
                });
            </script>
        </c:forEach>
    </div>
</div>
<div id="dialogInfoMod" title="Información" class="custom-with-dialog">
</div>
<script>
    var divResultNOKMod;
    var divResultBusqProyMod;
    var inptResultadoBusquedaMod;
    var inptCodProyectoMod;
    var inptCodProyectoNewMod;
    var inptNombreProyMod;
    var msnAgregacionMod;
    var cdgProyectSeleccMod = '';
    var dialogInfoMod;

    $(document).ready(function () {

        dialogInfoMod = $( "#dialogInfoMod" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    $( this ).dialog( "close" );
                }
            }
        });

        inptCodProyectoMod = $('#inptCodProyectoMod');
        inptCodProyectoNewMod = $('#inptCodProyectoNewMod');
        inptNombreProyMod = $('#inptNombreProyMod');
        msnAgregacionMod = $('#msnAgregacionMod');
        divResultNOKMod = $('#divResultNOKMod');
        divResultBusqProyMod = $('#divResultBusqProyMod');
        inptResultadoBusquedaMod = $('#inptResultadoBusquedaMod');

        $('#btnNuevoProyectoMod').click(function () {
            $('#divBusquedaProyMod').hide();
            $('#divNuevoProyMod').show();
            inptCodProyectoMod.val('');
            inptResultadoBusquedaMod.val('');
            divResultNOKMod.empty();
            divResultBusqProyMod.empty();
        });

        $('#btnBuscarProyMod').click(function () {
            $('#divBusquedaProyMod').show();
            $('#divNuevoProyMod').hide();
            inptCodProyectoNewMod.val('');
            inptNombreProyMod.val('');
            msnAgregacionMod.empty();
            cdgProyectSeleccMod = '';
        });

        $('#inptCodProyectoMod').keyup(function (){
            this.value = (this.value + '').replace(/([^0-9-])/g, '');
        });
    });

    function getProyectoByCodigoMod() {
        var cdgProyectBuscado = $("#inptCodProyectoMod").val();
        inptResultadoBusquedaMod.val('');

        if(cdgProyectBuscado.length === 0){
            divResultNOKMod.html('<span class="text-danger"><strong>Debe ingresar Código proyecto</strong></span>');
            divResultNOKMod.show();
            divResultBusqProyMod.hide();
        } else {
            var array = cdgProyectBuscado.split('-');

            if(array.length > 0){

                cdgProyectBuscado = array[0];
                var url = '../digitacion/getProyectoByCodigo?cdgProyectBuscado=' + cdgProyectBuscado;

                $.get(url, function (data) {
                    if (data.length === 0) {
                        divResultNOKMod.html('<span class="text-danger"><strong>NO ENCONTRADO</strong></span>');
                        divResultNOKMod.show();
                        divResultBusqProyMod.hide();
                    } else {
                        $.each(data, function (i, item) {
                            cdgProyectSeleccMod = item.proyectoCodigo;
                            inptResultadoBusquedaMod.val(item.proyectoCodigo + ' '+ item.proyectoNombre);
                            divResultBusqProyMod.html('<span class="text-primary"><strong>Encontrado  : '+ item.proyectoCodigo + ' '+ item.proyectoNombre + '</strong></span>');
                            divResultNOKMod.hide();
                            divResultBusqProyMod.show();
                        })
                    }
                });
            } else {
                divResultNOKMod.html('<span class="text-danger"><strong>El código del proyecto debe tener guión</strong></span>');
                divResultNOKMod.show();
                divResultBusqProyMod.hide();
            }
        }
    }

    function addProyectoNuevoMod() {
        var addCodProyecto = inptCodProyectoNewMod.val();
        var addNombreProyecto = inptNombreProyMod.val();
        var addEstadoProyecto = $("#slctEstadoProyectoMod option:selected").val();
        msnAgregacionMod.hide();

        var text = '<ul>';
        var errorFlg = false;

        if(addCodProyecto.length === 0){
            errorFlg = true;
            text += "<li class='text-danger'><strong>Código Proyecto es obligatorio</strong></li>";
        }
        if(addNombreProyecto.length === 0){
            errorFlg = true;
            text += "<li class='text-danger'><strong>Nombre Proyecto es obligatorio</strong></li>";
        }

        if(errorFlg){
            text += "</ul>";
            msnAgregacionMod.html(text);
            msnAgregacionMod.show();
        } else {
            var postData = {
                addCodProyecto: addCodProyecto,
                addNombreProyecto: addNombreProyecto,
                addEstadoProyecto: addEstadoProyecto
            };

            $.post('../digitacion/addProyectoNuevo', postData).done(function (data) {
                msnAgregacionMod.html('<span class="text-primary"><strong>' + data.nombre + '</strong></span>');
                msnAgregacionMod.show();

                if (data.id > 0) {
                    cdgProyectSeleccMod = addCodProyecto;
                }
            });
        }

    }


</script>