<div id="popupCrearAgregacion" style="display: none;">
    <div id="dialogPopUpAmpliado" title="">
        <div style="margin:auto 0;">
            <form onsubmit="crearAgregacion(); return false;" action="javascript:void(0);" id="frmCrearAgregacion">
                <table style="vertical-align: top;">
                    <tr><!-- Fila 2 -->
                        <td colspan="3">
                            <table>
                                <tr>
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">Ejercicio </label><br/>
                                            <input id="agrEjericio" style="width: 40px" type="text" disabled/>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left:12px; font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">Título </label></br>
                                            <input type="text" name="agrTit" value=""
                                                   id="agrTit" pattern="[1-9]{1,1}" required="true"
                                                   title="Debe ingresar 1 digito n�merico mayor a cero"
                                                   style="width:30px"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left:10px; font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">Grupo </label></br>
                                            <input type="text" name="agrGru" value=""
                                                   id="agrGru" pattern="[0-9]{1,1}" required="true"
                                                   title="Debe ingresar 1 digito n�merico" style="width:30px"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left:10px; font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">SubG </label></br>
                                            <input type="text" name="agrSub" value=""
                                                   id="agrSub" pattern="[0-9]{1,1}" required="true"
                                                   title="Debe ingresar 1 digito n�merico" style="width:30px"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left:10px;font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">Descripción</label></br>
                                            <input type="text" name="agrDesc" value=""
                                                   id="agrDesc" pattern=".{1,255}" required="true" style="width:260px"
                                                   title="Debe ingresar solo hasta 255 caracteres"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <!-- Fila 3 -->
                    <tr>
                        <td>
                            <div style="margin-top: 15px; font: bold 12px sans-serif; float:left;"><label
                                    style="color:black;">Tipo Saldo : </label><br/>
                                <input type="checkbox" id="pntipoSaldoDeudor" name="pntipoSaldoDeudor"/>
                                <label>Saldo Deudor</label></br>
                                <input type="checkbox" id="pntipoSaldoAcreedor" name="pntipoSaldoAcreedor"/>
                                <label>Saldo Acreedor</label></br>
                            </div>
                            </br>
                            <div style="margin-top: 5px; font: bold 12px sans-serif; float:left;"><label
                                    style="color:black;">Naturales de la cartera : </label> <br/>
                                <input type="radio" name="pnNaturaleza" value="Financiera"> Financiera<br>
                                <!--  required  -->
                                <input type="radio" name="pnNaturaleza" value="Bancaria"> Bancaria<br>
                                <input type="radio" name="pnNaturaleza" value="Bienes"> Bienes
                            </div>
                        </td>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Clasificación
                                : </label><br/>
                                <input type="radio" name="pnClasificacion" value="Activo"> Activo<br>
                                <!--  required  -->
                                <input type="radio" name="pnClasificacion" value="Pasivo"> Pasivo<br>
                                <input type="radio" name="pnClasificacion" value="Patrimonio"> Patrimonio<br>
                                <input type="radio" name="pnClasificacion" value="Ingreso Patromonial"> Ingreso
                                Patromonial<br>
                                <input type="radio" name="pnClasificacion" value="Gasto Patrimonial"> Gasto Patrimonial
                            </div>
                        </td>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;margin-top: 10px"><label
                                    style="color:black;">Uso Auxiliar : </label><br/>
                                <input type="radio" name="pnAux" value="SI"> Si<br>  <!--  required  -->
                                <input type="radio" name="pnAux" value="NO"> No
                            </div>
                            </br>
                            <div style="font: bold 12px sans-serif; float:left;margin-top: 10px"><label
                                    style="color:black;">Imputación : </label><br/>
                                <input type="radio" name="pnImp" value="SI"> Si<br>  <!--  required  -->
                                <input type="radio" name="pnImp" value="NO"> No
                            </div>
                        </td>
                    </tr>
                    <!-- Fila 4 -->
                    <tr>
                        <td rowspan="2" style=" width: 175px">
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <!-- Fila 5 -->
                    <tr>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <!-- Fila 6-->
                    <tr>
                        <td colspan="3">
                            <div class="botones" style="position: relative;top: 60%;top: 70%;width: 75%;">
                                <button class="boton" onclick="actualizarCuentaAgregacion(); javascript: void(0);"
                                        style="margin-right: 15px">Aceptar
                                </button>
                                <div class="boton" onclick="cerrarCreacion()" style="height: 15px;">Cancelar</div>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>