<div id="popupDesactivarCuenta" style="display: none;">
    <div id="dialogPopUpAmpliado" title="">
        <div style="margin:auto 0;">
            <s:form id="frmDesactivarCuenta2" onsubmit="postDelete2(); return false;">
                ¿Está seguro que desea deshabilitar el registro seleccionado? También se deshabilitarán sus registros dependientes, en caso de existir.
                <div id="divDesactivarCtasCtables" style="font: bold 14px arial;padding:15px 0 5px;"></div>
                <table id="tblDesactivarCtasCtables" class="tabla1" style="width:500px;">
                    <thead class="rw_cabecera">
                    <tr class="rw_cabecera">
                        <th>Cuenta</th>
                        <th>Descripci�n</th>
                    </tr>
                    </thead>
                    <tbody style="font: 12px arial;">
                    </tbody>

                    <tfoot>
                    <tr>
                        <td colspan="2">
                            <div class="botones" style="position: relative;top: 60%;top: 70%;width: 75%;">
                                <button onclick="deleteCuenta2(); javascript: void(0);" class="boton"
                                        style="cursor:pointer;float: right;">Aceptar
                                </button>
                                <div class="boton" onclick="cerrarDesactivacion()" style="height: 15px;">Cancelar</div>
                            </div>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </s:form>
        </div>
    </div>
</div>