<div id="popupCrearNivel" style="display: none;">
    <div id="dialogPopUpAmpliado" title="" style="width:700px;">
        <div style="width:690px; margin:auto 0;">
            <s:form id="frmCrearNivel" onsubmit="completeAndRedirect(); return false;">
                <table style="width: 600px; vertical-align: top;">
                    <tr><!-- Fila 1 -->
                        <td width="600px" colspan="3">
                            <div style="width:100px; font: bold 12px sans-serif; float:left;"><label
                                    style="color:black;">Ejercicio : </label><br/>
                                <input style="width: 60px" type="text" id="nnEjercicio" disabled/>
                            </div>

                            <div style="width: 50%; font: bold 12px sans-serif; float:left;"><label
                                    style="color:black;">Cuenta Padre : </label><br/>
                                <input id="pnDesc" style="width: 310px" type="text" disabled
                                       value="100.00.00.000 - Activo"/>
                            </div>
                        </td>
                    </tr>
                    <tr><!-- Fila 2 -->
                        <td colspan="2">
                            <table>
                                <tr>
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">N1 : </label></br>
                                                <%-- <s:textfield id="pnN1" style="width:30px" name="nvlPrimero" label="N1" labelposition="top" theme="simple"/> --%>
                                            <input type="text" maxlength="2" name="nvlPrimero"
                                                   value="<s:property value='nvlPrimero'/>" id="pnN1"
                                                   pattern="[0-9]{2,2}" required="true"
                                                   title="Debe ingresar 2 digitos númericos" style="width:30px"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">N2 : </label></br>
                                                <%-- <s:textfield id="pnN2" style="width:30px;" name="nvlSegundo" theme="simple"/> --%>
                                            <input type="text" maxlength="2" name="nvlSegundo"
                                                   value="<s:property value='nvlSegundo'/>" id="pnN2"
                                                   pattern="[0-9]{2,2}" required="true"
                                                   title="Debe ingresar 2 digitos númericos" style="width:30px"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">N3 : </label></br>
                                                <%-- <s:textfield id="pnN3" style="width:30px" name="nvlTercero" theme="simple"/> --%>
                                            <input type="text" maxlength="2" name="nvlTercero"
                                                   value="<s:property value='nvlTercero'/>" id="pnN3"
                                                   pattern="[0-9]{2,2}" required="true"
                                                   title="Debe ingresar 2 digitos númericos" style="width:30px"/>
                                        </div>
                                    </td>
                                    <!--  		<td>
				   							<div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">N4 : </label></br>

												<input type="text" name="nvlCuarto" value="<s:property value='nvlCuarto'/>" id="pnN4" required="true" style="width:30px"/>
											</div>
				   						</td>
				   						-->
                                    <td>
                                        <div style="font: bold 12px sans-serif; float:left;"><label
                                                style="color:black;">Descripción nueva cuenta: </label></br>

                                            <input type="text" name="descripcion"
                                                   value="<s:property value='descripcion'/>" id="descripcion"
                                                   required="true" pattern=".{1,255}" style="width:260px"
                                                   title="Debe ingresar solo hasta 255 caracteres"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Tipo Saldo
                                : </label><br/>

                                <s:checkbox id="pntipoSaldoDeudor" name="pntipoSaldoDeudor" fieldValue="true"
                                            theme="simple" label="Saldo Deudor" labelposition="right"/>Saldo Deudor</br>
                                <s:checkbox id="pntipoSaldoAcreedor" name="pntipoSaldoAcreedor" fieldValue="true"
                                            theme="simple" label="Saldo Acreedor" labelposition="right"/>Saldo
                                Acreedor</br>
                            </div>
                        </td>
                    </tr>
                    <!-- Fila 3 -->
                    <tr>
                        <td>
                            <div style="font: bold 12px sans-serif; float:left;"><label style="color:black;">Naturales
                                de la cartera : </label> <br/>
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
                                <input type="radio" name="pnImp" value="1"> Si<br>  <!--  required  -->
                                <input type="radio" name="pnImp" value="0"> No
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
                            <button class="boton" onclick="login(); javascript: void(0);" style="margin-right: 15px">
                                Aceptar
                            </button>
                            <div class="boton" onclick="cerrar()" style="height: 15px;">Cancelar</div>
                        </td>
                        <td>
                        </td>
                    </tr>
                </table>
            </s:form>
        </div>
    </div>
</div>