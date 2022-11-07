<div id="popupEditarCtasGastos" style="display: none;">
    <div id="dialogPopUpAmpliado" title="">
        <div style="margin:auto 0;">
            <form id="frmEditarGastos" action="javascript:void(0);" onsubmit="editarCuentaGastos();">
                <table style="vertical-align: top;">
                    <tr>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Cuentas de
                                Gastos : </label><br/>
                                <select id="pnCtasGastos" name="pnCtasGastos" multiple="true" class="Selectano"
                                        style="height: 100px"></select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <!-- <div class="boton" onclick="cerrarEditCtaPresup()" style="height: 15px;">Cancelar</div> -->
                            <button class="boton" onclick="actualizarAsocPresupGasto(); javascript: void(0);"
                                    style="margin-right: 15px">Guardar
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<div id="popupEditarCtasIngresos" style="display: none;">
    <div id="dialogPopUpAmpliado" title="">
        <div style="margin:auto 0;">
            <form id="frmEditarIngresos" action="javascript:void(0);" onsubmit="editarCuentaIngresos();">
                <table style="vertical-align: top;">
                    <tr>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Cuentas de
                                Ingreso : </label><br/>
                                <select id="pnCtasIngresos" multiple="true" class="Selectano"
                                        style="height: 100px"></select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="boton" onclick="actualizarAsocPresupIngre(); javascript: void(0);"
                                    style="margin-right: 15px">Guardar
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>