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
        <div id="divBusquedaProyIdent">
            <div class="row">
                <div class="col-md-10">
                    <h6>Buscar Proyecto BIP</h6>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary" id="btnNuevoProyectoIdent">Nuevo</button>
                </div>
            </div>
            <div class="row d-flex justify-content-between">
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Código proyecto</label>
                        <input type="text" class="form-control" id="inptCodProyectoIdent">
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="form-group">
                        <label>Resultado búsqueda</label>
                        <input type="text" class="form-control" value="" id="inptResultadoBusquedaIdent" readonly>
                    </div>
                </div>
                <div class="col-md-2 align-self-center">
                    <button type="button" class="btn btn-primary" onclick="getProyectoByCodigoIdent()">Buscar</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" id="divResultBusqProyIdent" style="display: none;">
                    <span class="text-primary"><strong></strong></span>
                </div>
                <div class="col-md-12" id="divResultNOKIdent" style="display: none;">
                    <span class="text-danger"><strong>NO ENCONTRADO</strong></span>
                </div>
            </div>
        </div>
        <div id="divNuevoProyIdent" style="display: none;">
            <div class="row">
                <div class="col-md-10">
                    <h6>Nuevo Proyecto BIP</h6>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary" id="btnBuscarProyIdent">Buscar</button>
                </div>
            </div>
            <div class="row d-flex justify-content-between">
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Código proyecto</label>
                        <input type="text" class="form-control" id="inptCodProyectoNewIdent">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Nombre Proyecto</label>
                        <input type="text" class="form-control" value="" id="inptNombreProyIdent">
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Estado Proyecto</label>
                        <select class="form-control" id="slctEstadoProyectoIdent">
                            <option value="1">SIN APLICACION</option>
                            <option value="2">PREFACTIBILIDAD</option>
                            <option value="3">FACTIBILIDAD</option>
                            <option value="4">DISENO</option>
                            <option value="5">EJECUCION</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-2 align-self-center">
                    <button type="button" class="btn btn-primary" id="btnAceptarProyIdent" onclick="addProyectoNuevoIdent()">Aceptar</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" id="msnAgregacionIdent" style="display: none;">
                    <span class="text-danger"><strong></strong></span>
                </div>
            </div>
        </div>
        <form id="form_dinamicField2Ident">
            <input type="hidden"  value="0" id="codProyecto" name="codProyecto" readonly="readonly"/>
            <div class="row" style="border-collapse:collapse;">
                <div class="col-md-12">
                    <h6>Monto Cuenta Asignación</h6>
                </div>
            </div>
            <div class="row d-flex justify-content-between">
                <div class="col-md-3">
                    <div class="form-check">
                        <label class="form-check-label"> Informaci&oacute;n</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="radioInfoMCA_ident_agre" value="agregada" id="radioInfoMCA_ident_agre">
                        <label class="form-check-label" for="radioInfoMCA_ident_agre"> Agregada </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="radioInfoMCA_ident_agre" value="desagregada" id="radioInfoMCA_ident_desagre">
                        <label class="form-check-label" for="radioInfoMCA_ident_desagre">Desagregada</label>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Total</label>
                        <input type="text" class="form-control" name ="textInfoMCA_ident_total" value="0" id="textInfoMCA_ident_total"/>
                    </div>
                </div>
                <div class="col-md-2">&nbsp;</div>
            </div>
            <div id="divContentAsigNuevoProyIdent">
                <c:forEach var="asig" items="${listaAsignaciones}">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>${asig.cuentaAsignacion} ${asig.cuentaNombre}</label>
                                <input type="text" class="form-control dinamicField2Ident" value="0" id="asig_ident_${asig.cuentaAsignacion}" name="asig_ident_${asig.cuentaAsignacion}_${asig.cuentaNombre}"/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </form>
    </div>
</div>
<div id="dialogInfoIdent" title="Información" class="custom-with-dialog">
</div>
<script>
    var divResultNOKIdent;
    var divResultBusqProyIdent;
    var inptResultadoBusquedaIdent;
    var inptCodProyectoIdent;
    var inptCodProyectoNewIdent;
    var inptNombreProyIdent;
    var msnAgregacionIdent;
    var cdgProyectSeleccIdent = '';
    var dialogInfoIdent;

    $(document).ready(function () {

        $('#radioInfoMCA_ident_agre').trigger( "click" );
        $("#textInfoMCA_ident_total").numeric({ negative: false });

        $(".dinamicField2Ident").each(function(){
            $(this).numeric({ negative: false });
            $(this).prop('disabled', true);
            $(this).keyup(function (){
                let sumTot= 0;
                $(".dinamicField2Ident").each(function(){
                    sumTot = (parseInt(this.value) + sumTot);
                });
                $("#textInfoMCA_ident_total").val(sumTot);
            });
        });

        $('#radioInfoMCA_ident_agre').click(function () {
            let sum = 0;
            $("#divContentAsigNuevoProyIdent").find(':input').each(function() {
                let elemento= this;
                sum = (sum + parseInt(elemento.value));
                $("#"+elemento.id).val(0);
                $("#"+elemento.id).prop('disabled', true);
            });
            $("#textInfoMCA_ident_total").val(sum);
            $("#textInfoMCA_ident_total").prop('readOnly', false);
        });

        $('#radioInfoMCA_ident_desagre').click(function () {
            let sum = 0;
            $("#divContentAsigNuevoProyIdent").find(':input').each(function() {
                let elemento= this;
                sum = (sum + parseInt(elemento.value));
                $("#"+elemento.id).prop('disabled', false);
            });
            $("#textInfoMCA_ident_total").val(sum);
            $("#textInfoMCA_ident_total").prop('readOnly', true);
        });

        dialogInfoIdent = $( "#dialogInfoIdent" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    $( this ).dialog( "close" );
                }
            }
        });

        inptCodProyectoIdent = $('#inptCodProyectoIdent');
        inptCodProyectoNewIdent = $('#inptCodProyectoNewIdent');
        inptNombreProyIdent = $('#inptNombreProyIdent');
        msnAgregacionIdent = $('#msnAgregacionIdent');
        divResultNOKIdent = $('#divResultNOKIdent');
        divResultBusqProyIdent = $('#divResultBusqProyIdent');
        inptResultadoBusquedaIdent = $('#inptResultadoBusquedaIdent');

        $('#btnNuevoProyectoIdent').click(function () {
            $('#divBusquedaProyIdent').hide();
            $('#divNuevoProyIdent').show();
            inptCodProyectoIdent.val('');
            inptResultadoBusquedaIdent.val('');
            divResultNOKIdent.empty();
            divResultBusqProyIdent.empty();
        });

        $('#btnBuscarProyIdent').click(function () {
            $('#divBusquedaProyIdent').show();
            $('#divNuevoProyIdent').hide();
            inptCodProyectoNewIdent.val('');
            inptNombreProyIdent.val('');
            msnAgregacionIdent.empty();
            cdgProyectSeleccIdent = '';
        });

        $('#inptCodProyectoIdent').keyup(function (){
            this.value = (this.value + '').replace(/([^0-9-])/g, '');
        });
    });

    function getProyectoByCodigoIdent() {
        var cdgProyectBuscado = $("#inptCodProyectoIdent").val();
        inptResultadoBusquedaIdent.val('');

        if(cdgProyectBuscado.length === 0){
            divResultNOKIdent.html('<span class="text-danger"><strong>Debe ingresar Código proyecto</strong></span>');
            divResultNOKIdent.show();
            divResultBusqProyIdent.hide();
        } else {
            var array = cdgProyectBuscado.split('-');

            if(array.length > 0){

                cdgProyectBuscado = array[0];
                var url = '../digitacion/getProyectoByCodigo?cdgProyectBuscado=' + cdgProyectBuscado;

                $.get(url, function (data) {
                    if (data.length === 0) {
                        divResultNOKIdent.html('<span class="text-danger"><strong>NO ENCONTRADO</strong></span>');
                        divResultNOKIdent.show();
                        divResultBusqProyIdent.hide();
                    } else {
                        $.each(data, function (i, item) {
                            cdgProyectSeleccIdent = item.proyectoCodigo;
                            $('#codProyecto').val(item.proyectoCodigo);
                            inptResultadoBusquedaIdent.val(item.proyectoCodigo + ' '+ item.proyectoNombre);
                            divResultBusqProyIdent.html('<span class="text-primary"><strong>Encontrado  : '+ item.proyectoCodigo + ' '+ item.proyectoNombre + '</strong></span>');
                            divResultNOKIdent.hide();
                            divResultBusqProyIdent.show();
                        })
                    }
                });
            } else {
                divResultNOKIdent.html('<span class="text-danger"><strong>El código del proyecto debe tener guión</strong></span>');
                divResultNOKIdent.show();
                divResultBusqProyIdent.hide();
            }
        }
    }

    function addProyectoNuevoIdent() {
        var addCodProyecto = inptCodProyectoNewIdent.val();
        var addNombreProyecto = inptNombreProyIdent.val();
        var addEstadoProyecto = $("#slctEstadoProyectoIdent option:selected").val();
        msnAgregacionIdent.hide();

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
            msnAgregacionIdent.html(text);
            msnAgregacionIdent.show();
        } else {
            var postData = {
                addCodProyecto: addCodProyecto,
                addNombreProyecto: addNombreProyecto,
                addEstadoProyecto: addEstadoProyecto
            };

            $.post('../digitacion/addProyectoNuevo', postData).done(function (data) {
                msnAgregacionIdent.html('<span class="text-primary"><strong>' + data.nombre + '</strong></span>');
                msnAgregacionIdent.show();

                if (data.id > 0) {
                    cdgProyectSeleccIdent = addCodProyecto;
                    $('#codProyecto').val(addCodProyecto);
                }
            });
        }

    }


</script>
