<div id="popupEditarPeriodo" style="display: none;">
    <div id="dialogPopUpAmpliado" title="">
        <div style="margin:auto 0;">
            <form id="frmEditarPeriodo" onsubmit="editarCuentaPeriodo();" action="javascript:void(0);">
                <table style="vertical-align: top;">
                    <!-- Fila 5 -->
                    <tr>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Periodos
                                habilitado : </label><br/>
                                <select id="pnPeriodos" multiple="true" class="Selectano"
                                        style="height: 100px"></select>
                            </div>
                        </td>
                    </tr>
                    <!-- Fila 6-->
                    <tr>
                        <td>
                            <!-- <div class="boton" onclick="cerrarPopupEditPeriodos()" style="height: 15px;">Cancelar</div> -->
                            <button class="boton" onclick="actualizarPeriodosHabilitado(); javascript: void(0);"
                                    style="margin-right: 15px">Guardar
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>