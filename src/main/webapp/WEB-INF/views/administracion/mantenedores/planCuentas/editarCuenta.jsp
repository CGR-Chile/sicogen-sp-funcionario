<div id="popupEditarCuenta" style="display: none;">
    <div id="dialogPopUpAmpliado" title="" style="width:700px;">
        <div style="width:690px; margin:auto 0;">
            <form id="frmEditarCuenta" onsubmit="editarCuenta();" action="javascript:void(0);">
                <table style="width: 600px; vertical-align: top;">
                    <tr><!-- Fila 1 -->
                        <td width="600px" colspan="3">
                            <div style="width:100px; font: bold 12px sans-serif; float:left;"><label
                                    style="color:black;">Ejercicio : </label><br/>
                                <input id="edEjercicio" name="edEjercicio" value=""
                                       style="width: 60px" type="text" disabled/>
                            </div>

                            <div style="width: 50%; font: bold 12px sans-serif; float:left;"><label
                                    style="color:black;"></label><br/>
                            </div>
                        </td>
                    </tr>
                    <tr><!-- Fila 2 -->
                        <td colspan="2">
                            <table>
                                <tr>
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">Agr : </label></br>
                                            <input disabled="true" type="text" name="nvlAgr"
                                                   value="" id="nvlAgr" pattern=".{2,2}"
                                                   required="true" title="Debe ingresar 2 dígitos" style="width:30px"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">N1 : </label></br>
                                            <input disabled="true" type="text" name="editN1"
                                                   value="" id="editN1" pattern=".{2,2}"
                                                   required="true" title="Debe ingresar 2 dígitos" style="width:30px"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">N2 : </label></br>
                                            <input disabled="true" type="text" name="editN2"
                                                   value="" id="editN2" pattern=".{2,2}"
                                                   required="true" title="Debe ingresar 2 dígitos" style="width:30px"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">N3 : </label></br>
                                            <input disabled="true" type="text" name="editN3"
                                                   value="" id="editN3" pattern=".{3,3}"
                                                   required="true" title="Debe ingresar 3 dígitos" style="width:30px"/>
                                        </div>
                                    </td>
                                    <!--  		<td>
				   							<div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">N4 : </label></br>
												<input disabled="true" type="text" name="nvlCuarto" value="<s:property value='pnN4'/>" id="pnN4" required="true" style="width:30px"/>
											</div>
				   						</td>
				   					-->
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">Descripción cuenta: </label></br>

                                            <input type="text" name="editDescripcion"
                                                   value="" id="editDescripcion"
                                                   required="true" style="width:260px"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>

                        </td>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Tipo Saldo
                                : </label><br/>
                                <input type="checkbox" id="sdo-deudor" name="pntipoSaldoDeudor">
                                <label>Saldo Deudor</label></br>
                                <input type="checkbox" id="sdo-acreedor" name="pntipoSaldoAcreedor">
                                <label>Saldo Acreedor</label></br>
                            </div>
                        </td>
                    </tr>
                    <!-- Fila 3 -->
                    <tr>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Naturales
                                de la cartera : </label> <br/>
                                <input type="radio" id="cart-fin" name="pnNaturaleza" value="Financiera"> Financiera<br>
                                <input type="radio" id="cart-ban" name="pnNaturaleza" value="Bancaria"> Bancaria<br>
                                <input type="radio" id="cart-bie" name="pnNaturaleza" value="Bienes"> Bienes
                            </div>
                        </td>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Clasificación
                                : </label><br/>
                                <input type="radio" id="clas-act" name="pnClasificacion" value="Activo"> Activo<br>
                                <!--  required  -->
                                <input type="radio" id="clas-pas" name="pnClasificacion" value="Pasivo"> Pasivo<br>
                                <input type="radio" id="clas-pat" name="pnClasificacion" value="Patrimonio">
                                Patrimonio<br>
                                <input type="radio" id="clas-ing" name="pnClasificacion" value="Ingreso Patromonial">
                                Ingreso Patromonial<br>
                                <input type="radio" id="clas-gas" name="pnClasificacion" value="Gasto Patrimonial">
                                Gasto Patrimonial
                            </div>
                        </td>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;margin-top: 10px"><label
                                    style="color:black;">Uso Auxiliar : </label><br/>
                                <input id="aux-si" type="radio" name="pnAux" value="SI"> Si<br>  <!--  required  -->
                                <input id="aux-no" type="radio" name="pnAux" value="NO"> No
                            </div>
                            </br>
                            <div style="font: bold 12px sans-serif; float:left;margin-top: 10px"><label
                                    style="color:black;">Imputación : </label><br/>
                                <input id="imp-si" type="radio" name="editImputacion" value="1"> Si<br>
                                <!--  required  -->
                                <input id="imp-no" type="radio" name="editImputacion" value="0"> No
                            </div>
                        </td>
                    </tr>
                    <!-- Fila 4 -->
                    <tr>
                        <td rowspan="2" style=" width: 175px">
                            <%-- <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Periodos habilitados : </label> <br />
                                <input id="pnPeriodos" class="demo-default" width="48" height="48" value="Ape,Ene,Feb,Mar,Abr,May,Jun,Jul,Ago,Sep,Oct,Nov,Dic,Cie"/>
                            </div>
                            <script>
                                $('#pnPeriodos').selectize({
                                    persist: false,
                                    createOnBlur: true,
                                    create: true
                                });
                            </script> --%>
                        </td>
                        <td>
                            <%-- <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Gastos : </label><br />

                                <select id="pnGasto" multiple="true" class="Selectano"></select>
                            </div>	 --%>
                        </td>
                        <td>
                            <%-- <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Ingreso : </label><br />
                                <select id="pnIngreso" multiple="true" class="Selectano"></select>
                            </div>

                            <script>

                                $.get( "getCatalogoPresup" )
                                  .done(function( data ) {

                                    data.listCatalogoPresupuestario.forEach(function (item) {

                                        $("#pnIngreso").get(0).options[$("#pnIngreso").get(0).options.length]
                                             = new Option(item.cuenta , item.cuenta );

                                         $("#pnGasto").get(0).options[$("#pnGasto").get(0).options.length]
                                             = new Option(item.cuenta , item.cuenta );
                                    });

                                });

                            </script> --%>
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
                        <td>
                            <!--  <img src="images/loader.gif" align="midle" alt="Certificado Envio" onclick="certEnvio();" width="17px" height="18px"/> -->
                            <!-- <input id="btnCrearNivel" value="Buscar" style="background-color: #464749;
                                   border: 1px solid #E0E0E2;
                                   color: #FFFFFF;
                                  border-radius: 10px 10px 10px 10px;
                                 text-align:center;
                                  padding-top: 7px;
                                  padding-bottom: 7px;
                                  width: 70px;
                                  align: center;
                                  height: 30px;" type="button"/> -->

                        </td>
                        <td>
                            <button class="boton" onclick="actualizarCuenta(); javascript: void(0);"
                                    style="margin-right: 15px">Aceptar
                            </button>
                            <div class="boton" onclick="cerrarEdicion()" style="height: 15px;">Cancelar</div>
                        </td>
                        <td>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>