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
    <script type="text/javascript" src="${js}/mantenedores/tblProgramaPresupuestario.js"></script>
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
    <select id="cbEjercicio" class="Selectano" onchange="loadPartida(this.value)" style="width: 160px;">
        <option value="-1">Seleccione</option>
        <c:forEach var="ejercc" items="${listaEjercicios}">
            <option value="${ejercc.ejercicioId}">${ejercc.ejercicioNombre}</option>
        </c:forEach>
    </select>
    Partida:
    <select id="cbPartida" class="Selectano" onchange="loadCapitulo(this.value)" style="width: 160px;">
        <option value="-1">Seleccione</option>
        <c:forEach var="partida" items="${listaPartida}">
            <option value="${partida.codPartida}">${partida.nombrePartida}</option>
        </c:forEach>
    </select>
    Capitulo:
    <select id="cbCapitulo" class="Selectano" onchange="load(cbEjercicio.value,cbPartida.value,this.value)"
            style="width: 160px;">
        <option value="-1">Seleccione</option>
        <c:forEach var="cap" items="${listaCapitulo}">
            <option value="${cap.idCapitulo}">${cap.nombreCapitulo}</option>
        </c:forEach>
    </select>
</div>

<div id="ExpenseTableContainer" style="width:900px;"></div>

<div id="divPopUpCapitulo" style="display: none;">
    <div id="dialogPopUpCapitulo" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Partida<br> <input id="txtPartida" type="text" disabled="true"/><br><br>
                <input id="txtCodPartida" type="text"/>
                Codigo Capitulo<br> <input id="txtCodigoCapitulo" class="solo-numero numero2" type="text"
                                           required="true" maxlength="2" pattern=".{2,2}"
                                           title="Debe ingresar s�lo 2 digitos" maxlength="2"/><br><br>
                Nombre<br>
                <input id="txtNombreCapitulo" type="text" required="true" pattern=".{1,255}"
                       title="Debe ingresar s�lo hasta 256 caracteres"/><br><br>
                <button id="Btn_crearCapitulo" type="button" class="boton" style="cursor:pointer;"
                        onclick="setCapitulo(txtCodPartida)" alt="Crear Capitulo" value="crearCapitulo">Aceptar
                </button>
                <button id="Btn_CancelarCrearCapitulo" type="button" class="boton" name="Btn_volverCapitulo"
                        id="Btn_volverCapitulo" style="margin: 0 0 0 5%;cursor:pointer;" onclick="" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>

<div id="divPopUpPrograma" style="display: none;">
    <div id="dialogPopUpPrograma" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Partida<br>
                <input id="txtPartidaPro" type="text" disabled="true"/><br><br>
                Capitulo<br>
                <input id="txtCapituloPro" type="text" disabled="true"/><br><br>
                Codigo Programa<br>
                <input id="txtCodigoPrograma" class="solo-numero" type="text" required="true" pattern=".{2,2}"
                       title="Debe ingresar s�lo 2 digitos" maxlength="2"/><br><br>
                Nombre<br>
                <input id="txtNombrePrograma" type="text" required="true" pattern=".{1,255}"
                       title="Debe ingresar s�lo hasta 256 caracteres"/><br><br>
                <button id="Btn_crearPrograma" type="button" class="boton" style="cursor:pointer;" onclick=""
                        alt="Crear Programa" value="crearPrograma">Aceptar
                </button>
                <button id="Btn_CancelarCrearPrograma" type="button" class="boton" name="Btn_volverPrograma"
                        id="Btn_volverPrograma" style="margin: 0 0 0 5%;cursor:pointer;" onclick="" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>

<div id="divPopUpEditarPartida" style="display: none;">
    <div id="dialogPopUpEditarPartida" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Codigo<br>
                <input type="text" id="txtCodigoPartida" type="text" disabled="true"/><br><br>
                Nombre<br>
                <input type="text" id="txtNombrePartida" required="true" style="width: 100%" pattern=".{1,255}"
                       title="Debe ingresar s�lo hasta 256 caracteres"/><br><br>
                <input id="txtCodPar" type="text"/>
                <button id="Btn_editarPartida" type="button" class="boton" style="cursor:pointer;"
                        onclick="updPartida(txtCodPartida)" alt="Editar Partida" value="editarPartida">Aceptar
                </button>
                <button id="Btn_CancelarEditarPartida" type="button" class="boton" name="Btn_volverPartida"
                        id="Btn_volverPartida" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>

<div id="divPopUpEditarCapitulo" style="display: none;">
    <div id="dialogPopUpEditarCapitulo" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Partida<br>
                <input type="text" id="txtCodigoPartidaEditar" disabled="true" style="width: 100%"/><br><br>
                Codigo Cap�tulo<br>
                <input type="text" id="txtCodigoCapituloEditar" class="solo-numero" required="true" style="width: 100%"
                       pattern=".{2,2}" title="Debe ingresar s�lo 2 digitos"/><br><br>
                Nombre<br>
                <input type="text" id="txtNombreCapituloEditar" required="true" style="width: 100%" pattern=".{1,255}"
                       title="Debe ingresar s�lo hasta 256 caracteres"/><br><br>
                <input id="txtCodParCap" type="text"/>
                <button id="Btn_editarCapitulo" type="button" class="boton" style="cursor:pointer;"
                        onclick="updCapitulo(txtCodParCap)" alt="Editar Capitulo" value="editarCapitulo">Aceptar
                </button>
                <button id="Btn_CancelarEditarCapitulo" type="button" class="boton" name="Btn_volverCapitulo"
                        id="Btn_volverCapitulo" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>


<div id="divPopUpEditarPrograma" style="display: none;">
    <div id="dialogPopUpEditarPrograma" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <form>
                Partida<br>
                <input id="txtPartidaEditPro" type="text" disabled="true" style="width: 100%"/><br><br>
                Cap�tulo<br>
                <input id="txtCapituloEditPro" type="text" disabled="true" style="width: 100%"/><br><br>
                C�digo Programa<br>
                <input type="text" id="txtCodigoProgramaEditar" required="true" pattern=".{2,2}" class="numero2"
                       maxlength="2" title="Debe ingresar s�lo 2 digitos"/><br><br>
                Nombre<br>
                <input type="text" id="txtNombreProgramaEditar" required="true" pattern=".{1,255}"
                       title="Debe ingresar s�lo hasta 256 caracteres"/><br><br>
                <button id="Btn_editarPrograma" type="button" class="boton" style="cursor:pointer;" onclick=""
                        alt="Editar Programa" value="editarPrograma">Aceptar
                </button>
                <button id="Btn_CancelarEditarPrograma" type="button" class="boton" name="Btn_volverPrograma"
                        id="Btn_volverPrograma" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                        value="cerrarPopup"> Cancelar
                </button>
            </form>
        </div>
    </div>
</div>

<div id="divPopUpEliminarPartida" style="display: none;">
    <div id="dialogPopUpEkiminarPartida" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>�Desea Eliminar esta Partida?</p><br></br>
            <button id="Btn_eliminarPartida" type="button" class="boton" style="cursor:pointer;" alt="Eliminar Partida"
                    value="eliminarPartida">Aceptar
            </button>
            <button id="Btn_CancelarPartida" type="button" class="boton" name="Btn_volverPartida" id="Btn_volverPartida"
                    style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up" value="cerrarPopup"> Cancelar
            </button>
        </div>
    </div>
</div>

<div id="divPopUpEliminarCapitulo" style="display: none;">
    <div id="dialogPopUpEkiminarCapitulo" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>�Desea Eliminar este Capitulo?</p><br></br>
            <button id="Btn_eliminarCapitulo" type="button" class="boton" style="cursor:pointer;"
                    alt="Eliminar Capitulo" value="eliminarIns">Aceptar
            </button>
            <button id="Btn_CancelarCapitulo" type="button" class="boton" name="Btn_volverCapitulo"
                    id="Btn_volverCapitulo" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Cancelar
            </button>
        </div>
    </div>
</div>
<!-- </div>  -->

<div id="divPopUpEliminarPrograma" style="display: none;">
    <div id="dialogPopUpEkiminarPrograma" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>�Desea Eliminar este Programa?</p><br></br>
            <button id="Btn_eliminarPrograma" type="button" class="boton" style="cursor:pointer;"
                    alt="Eliminar Programa" value="eliminarAT">Aceptar
            </button>
            <button id="Btn_CancelarPrograma" type="button" class="boton" name="Btn_volverPrograma"
                    id="Btn_volverPrograma" style="margin: 0 0 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Cancelar
            </button>
        </div>
    </div>
</div>

<div id="divPopUpEliminarPartidaYaEliminado" style="display: none;">
    <div id="dialogPopUpEkiminarPartidaYaEliminado" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>Esta Partida ya fue eliminada</p><br></br>
            <button id="Btn_CancelarPartida2" type="button" class="boton" name="Btn_volverPartida"
                    id="Btn_volverPartida" style="margin: 0 20% 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Aceptar
            </button>
        </div>
    </div>
</div>

<div id="divPopUpEliminarCapituloYaEliminado" style="display: none;">
    <div id="dialogPopUpEkiminarCapituloYaEliminado" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>Este Capitulo ya fue eliminado</p><br></br>
            <button id="Btn_CancelarCapitulo2" type="button" class="boton" name="Btn_volverCapitulo"
                    id="Btn_volverCapitulo" style="margin: 0 20% 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Aceptar
            </button>
        </div>
    </div>
</div>

<div id="divPopUpEliminarProgramaYaEliminado" style="display: none;">
    <div id="dialogPopUpEkiminarProgramaYaEliminado" title="" style="width:490px;">
        <div style="width:390px; margin:auto 0;">
            <p>Este programa ya fue eliminado.</p><br></br>
            <button id="Btn_CancelarPrograma2" type="button" class="boton" name="Btn_volverPrograma"
                    id="Btn_volverPrograma" style="margin: 0 20% 0 5%;cursor:pointer;" alt="cerrar pop-up"
                    value="cerrarPopup"> Aceptar
            </button>
        </div>
    </div>
</div>
<%@ include file="bitacoraPP.jsp" %>
</body>
</html>