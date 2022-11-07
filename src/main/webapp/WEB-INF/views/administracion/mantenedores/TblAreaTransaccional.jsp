<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/css" var="css"/>
<spring:url value="/resources/js" var="js"/>
<spring:url value="/resources/jtable" var="jtable"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${css}/validationEngine.jquery.css"/>
    <link rel="stylesheet" type="text/css" href="${jtable}/themes/lightcolor/blue/jtable.css"/>
    <link rel="stylesheet" type="text/css" href="${css}/mensaje/jquery.alerts.css" />

    <script type="text/javascript" src="${jtable}/jquery.jtable.js"></script>
    <script type="text/javascript" src="${js}/mantenedores/tblAreaTransaccional.js"></script>
    <script type="text/javascript" src="${jtable}/localization/jquery.jtable.es.js"></script>
    <script type="text/javascript" src="${js}/jquery.validationEngine.js"></script>
    <script type="text/javascript" src="${js}/jquery.validationEngine-es.js"></script>
    <script type="text/javascript" src="${js}/mensaje/jquery.alerts.js"></script>
    <script>
        $(document).ready(function () {
            $('.solo-numero').keyup(function () {
                this.value = (this.value + '').replace(/[^0-9]/g, '');
            });
        });
    </script>
</head>

<body>
<div class="filtering" style="margin: 10px auto;">
    Ejercicio:
    <select id="cbEjercicio" class="Selectano" onchange="loadSector(this.value)" style="width: 160px;">
        <option value="-1">Seleccione</option>
        <c:forEach var="ejercc" items="${listaEjercicios}">
            <option value="${ejercc.ejercicioId}">${ejercc.ejercicioNombre}</option>
        </c:forEach>
    </select>
    Sector:
    <select id="cbSector" class="Selectano" onchange="loadInstitucion(this.value)" style="width: 160px;">
        <option value="-1">Seleccione</option>
        <c:forEach var="sector" items="${listaSector}">
            <option value="${sector.idSector}">${sector.nombresector}</option>
        </c:forEach>
    </select>
    Institucion:
    <select id="cbInstitucion" class="Selectano" onchange="load(cbEjercicio.value,cbSector.value,this.value)"
            style="width: 160px;">
        <option value="-1">Seleccione</option>
        <c:forEach var="ins" items="${listaInstitucion}">
            <option value="${ins.idInstitucion}">${ins.nombreInstitucion}</option>
        </c:forEach>
    </select>
</div>

<div id="ExpenseTableContainer" style="width:900px;"></div>

<div id="divPopUpInstitucion" style="display: none;">
    <div id="dialogPopUpInstitucion" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Sector<br> <input id="txtSector" type="text" disabled="true"/><br><br>
                <input id="txtCodSector" type="text"/>
                Código Institución<br> <input id="txtCodigoInstitucion" class="solo-numero numero2" type="text"
                                           required="true" maxlength="2" pattern=".{2,2}"
                                           title="Debe ingresar solo 2 digitos" maxlength="2"/><br><br>
                Descripción<br>
                <input id="txtNombreInstitucion" type="text" required="true" pattern=".{1,255}"
                       title="Debe ingresar solo hasta 256 caracteres"/><br><br>
                <button id="Btn_crearInstitucion" type="button" class="boton" style="cursor:pointer;"
                        onclick="" alt="Crear Institucion" value="crearInstitucion">Aceptar
                </button>
                <button id="Btn_CancelarCrearInstitucion" type="button" class="boton" name="Btn_volverInstitucion"
                        id="Btn_volverInstitucion" style="margin: 0 0 0 5%;cursor:pointer;" onclick="" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>

<div id="divPopUpAreaT" style="display: none;">
    <div id="dialogPopUpAreaT" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Sector<br>
                <input id="txtSectorAreaT" type="text" disabled="true"/><br><br>
                Institución<br>
                <input id="txtInstitucionAreaT" type="text" disabled="true"/><br><br>
                Código AT<br>
                <input id="txtCodigoAreaT" class="solo-numero" type="text" required="true" pattern=".{2,2}"
                       title="Debe ingresar sólo 3 digitos" maxlength="3"/><br><br>
                Descripción<br>
                <input id="txtNombreAreaT" type="text" required="true" pattern=".{1,255}"
                       title="Debe ingresar sólo hasta 256 caracteres" maxlength="255"/><br><br>
                RUT<br>
                <input type="text" id="txtRutAreaT" required="true"
                       maxlength="20" title="Debe ingresar sólo hasta 20 caracteres" maxlength="20"/><br><br>
                DV<br>
                <input type="text" id="txtDvAreaT" required="true" pattern=".{2,2}"
                       maxlength="1" title="Debe ingresar sólo 1 caracter"/><br><br>

                <button id="Btn_crearAreaT" type="button" class="boton" style="cursor:pointer;" onclick=""
                        alt="Crear Programa" value="crearAreaT">Aceptar
                </button>
                <button id="Btn_CancelarCrearAreaT" type="button" class="boton" name="Btn_volverAreaT"
                        id="Btn_volverAreaT" style="margin: 0 0 0 5%;cursor:pointer;" onclick="" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>

<div id="divPopUpEditarSector" style="display: none;">
    <div id="dialogPopUpEditarSector" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Código Sector<br>
                <input type="text" id="txtCodigoSector" type="text" disabled="true" maxlength="2"/><br><br>
                Descripción<br>
                <input type="text" id="txtDescripSector" required="true" style="width: 100%" pattern=".{1,255}"
                       title="Debe ingresar solo hasta 256 caracteres" maxlength="255"/><br><br>
                <input type="text" id="txtCodSec" type="text" disabled="true"/>
                <button id="Btn_editarSector" type="button" class="boton" style="cursor:pointer;"
                        onclick="updSector(txtCodSector)" alt="Editar Sector" value="editarSector">Aceptar
                </button>
                <button id="Btn_CancelarEditarSector" type="button" class="boton" name="Btn_volverSector"
                        id="Btn_volverSector" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>

<div id="divPopUpEditarInstitucion" style="display: none;">
    <div id="dialogPopUpEditarInstitucion" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Código Sector<br>
                <input type="text" id="txtCodigoSectorEditar" disabled="true"/><br><br>
                Código Institución<br>
                <input type="text" id="txtCodigoInstitucionEditar" class="solo-numero" required="true" maxlength="2"
                       pattern=".{2,2}" title="Debe ingresar sólo 2 caracteres"/><br><br>
                Descripción<br>
                <input type="text" id="txtNombreInstitucionEditar" required="true" style="width: 100%" pattern=".{1,255}"
                       title="Debe ingresar sólo hasta 256 caracteres" maxlength="255"/><br><br>
                <input id="txtCodSecIns" type="text"/>
                <button id="Btn_editarInstitucion" type="button" class="boton" style="cursor:pointer;"
                        onclick="updInstitucion(txtCodSecIns)" alt="Editar Institucion" value="editarInstitucion">Aceptar
                </button>
                <button id="Btn_CancelarEditarInstitucion" type="button" class="boton" name="Btn_volverInstitucion"
                        id="Btn_volverInstitucion" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>


<div id="divPopUpEditarAreaT" style="display: none;">
    <div id="dialogPopUpEditarAreaT" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Código Sector<br>
                <input id="txtSectorEditAreaT" type="text" disabled="true"/><br><br>
                Código Institución<br>
                <input id="txtInstitucionEditAreaT" type="text" disabled="true"/><br><br>
                Código AT<br>
                <input type="text" id="txtCodigoAreaTEditar" required="true" style="width: 100%"
                       maxlength="3" title="Debe ingresar sólo 3 caracteres"/><br><br>
                RUT<br>
                <input type="text" id="txtRutAreaTEditar" required="true" style="width: 100%"
                       maxlength="20" title="Debe ingresar sólo hasta 20 caracteres"/><br><br>
                DV<br>
                <input type="text" id="txtDvAreaTEditar" required="true" pattern=".{2,2}"
                       maxlength="1" title="Debe ingresar sólo 1 caracter"/><br><br>
                Descripción<br>
                <input type="text" id="txtNombreAreaTEditar" required="true" style="width: 100%" pattern=".{1,255}"
                       title="Debe ingresar solo hasta 256 caracteres" maxlength="255"/><br><br>
                <button id="Btn_editarAreaT" type="button" class="boton" style="cursor:pointer;" onclick=""
                        alt="Editar AreaT" value="editarAreaT">Aceptar
                </button>
                <button id="Btn_CancelarEditarAreaT" type="button" class="boton" name="Btn_volverAreaT"
                        id="Btn_volverAreaT" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>

<div id="divPopUpEliminarSector" style="display: none;">
    <div id="dialogPopUpEkiminarSector" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>¿Desea deshabilitar este Sector?</p><br></br>
            <button id="Btn_eliminarSector" type="button" class="boton" style="cursor:pointer;" alt="Eliminar Sector"
                    value="eliminarSector">Aceptar
            </button>
            <button id="Btn_CancelarSector" type="button" class="boton" name="Btn_volverSector" id="Btn_volverSector"
                    style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up" value="cerrarPopup"> Cancelar
            </button>
        </div>
    </div>
</div>

<div id="divPopUpEliminarInstitucion" style="display: none;">
    <div id="dialogPopUpEkiminarInstitucion" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>¿Desea deshabilitar esta Institución?</p><br></br>
            <button id="Btn_eliminarInstitucion" type="button" class="boton" style="cursor:pointer;"
                    alt="Eliminar Institucion" value="eliminarIns">Aceptar
            </button>
            <button id="Btn_CancelarInstitucion" type="button" class="boton" name="Btn_volverInstitucion"
                    id="Btn_volverInstitucion" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Cancelar
            </button>
        </div>
    </div>
</div>
<!-- </div>  -->

<div id="divPopUpEliminarAT" style="display: none;">
    <div id="dialogPopUpEkiminarAreaT" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>¿Desea deshabilitar esta Área Transaccional?</p><br></br>
            <button id="Btn_eliminarAT" type="button" class="boton" style="cursor:pointer;"
                    alt="Eliminar AreaT" value="eliminarAT">Aceptar
            </button>
            <button id="Btn_CancelarAT" type="button" class="boton" name="Btn_volverAreaT"
                    id="Btn_volverAreaT" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Cancelar
            </button>
        </div>
    </div>
</div>

<div id="divPopUpEliminarSectorYaEliminado" style="display: none;">
    <div id="dialogPopUpEkiminarSectorYaEliminado" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>Este Sector ya fue eliminada</p><br></br>
            <button id="Btn_CancelarSector2" type="button" class="boton" name="Btn_volverSector"
                    id="Btn_volverSector" style="margin: 0 20% 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Aceptar
            </button>
        </div>
    </div>
</div>

<div id="divPopUpEliminarInstitucionYaEliminado" style="display: none;">
    <div id="dialogPopUpEkiminarInstitucionYaEliminado" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>Esta Institucion ya fue eliminada</p><br></br>
            <button id="Btn_CancelarInstitucion2" type="button" class="boton" name="Btn_volverInstitucion"
                    id="Btn_volverInstitucion" style="margin: 0 20% 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Aceptar
            </button>
        </div>
    </div>
</div>

<div id="divPopUpEliminarAreaTYaEliminado" style="display: none;">
    <div id="dialogPopUpEkiminarAreaTYaEliminado" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>Esta AreaT ya fue eliminada.</p><br></br>
            <button id="Btn_CancelarAreaT2" type="button" class="boton" name="Btn_volverAreaT"
                    id="Btn_volverAreaT" style="margin: 0 20% 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Aceptar
            </button>
        </div>
    </div>
</div>
<%@ include file="bitacoraAT.jsp" %>
</body>
</html>