<%@ page contentType="text/html; charset=iso-8859-1;" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Sistema de Contabilidad General de la Naci&#243;n</title>
	<spring:url value="/resources/css/system.css" var="system"/>
	<spring:url value="/resources/css/login.css" var="login"/>
	<spring:url value="/resources/css/rounded.css" var="rounded"/>
	<spring:url value="/main" var="loginUrl"/>


	<spring:url value="/resources/img/" var="images"/>

	<link type="text/css" href="${system}" rel="stylesheet"/>
	<link type="text/css" href="${login}" rel="stylesheet"/>
	<link type="text/css" href="${rounded}" rel="stylesheet"/>

	<script type="text/javascript" src="/sicogen-mf/resources/js/mootools.js"></script>


	<script language="javascript" type="text/javascript">
		function setFocus() {

			var valor = "${usuario.mensaje}";
			if(valor=="0"){
				//$('userLogin').select();
				//$('userLogin').focus();
				$('system-message').style.display = "none";
			}else {
				document.getElementById('system-message').style.display = "block";
				document.getElementById('system-message').style.visibility = "visible";
				//document.getElementById('ErrInicio').style.display = "block";
				//document.getElementById('ErrInicio').style.visibility = "visible";

				document.getElementById('txtError').innerHTML = valor
				document.getElementById('textoErr').style.display = "block";
				document.getElementById('textoErr').style.visibility = "visible";

			}
		}
		function onLogin(){
			var user= document.getElementById('userLogin').value;
			var pass = document.getElementById('passLogin').value;
			if(user.trim()==""){
				document.getElementById('system-message').style.display = "block";
				document.getElementById('system-message').style.visibility = "visible";

				document.getElementById('txtError').innerHTML = "Debe ingresar un nombre de Usuario";
				document.getElementById('textoErr').style.display = "block";
				document.getElementById('textoErr').style.visibility = "visible";
				$('userLogin').focus();
				return false;
			}else if(pass.trim()==""){
				document.getElementById('system-message').style.display = "block";
				document.getElementById('system-message').style.visibility = "visible";

				document.getElementById('txtError').innerHTML = "Debe ingresar una Contrase&ntilde;a";
				document.getElementById('textoErr').style.display = "block";
				document.getElementById('textoErr').style.visibility = "visible";
				$('passLogin').focus();
				return false;
			}else {
				document.getElementById('login').submit();
			}
		}
		function nobackbutton(){

			window.location.hash="no-back-button";
			window.location.hash="Again-No-back-button" //chrome
			window.onhashchange=function(){window.location.hash="no-back-button";}

		}

	</script>
</head>

<body onload="javascript:setFocus();nobackbutton();">

<div id="content-box">
	<div class="padding">
		<div id="element-box" class="login">
			<div class="t">
				<div class="t">
					<div class="t"></div>
				</div>
			</div>
			<div class="m">
				<h1>Sistema de Contabilidad General de la Naci&oacute;n</h1>
				<dl id="system-message">
					<dt class="notice">Error</dt>
					<dd class="error message fade">
						<ul>
							<li><div id="textoErr"><span id="txtError"></span></div></li>

						</ul>
					</dd>
				</dl>
				<div id="section-box">
					<div class="t">
						<div class="t">
							<div class="t"></div>
						</div>
					</div>
					<div class="m">
						<form:form method="POST" modelAttribute="usuario" id="login" action="${loginUrl}">
							<table>
								<tr>
									<td width="300" align="right"><label for="modlgn_username">Usuario:</label></td>
									<td width="200" align="left"><form:input path="userLogin" id="userLogin" type="text"/></tr>
								<tr>
									<td width="300" align="right"><label for="modlgn_passwd">Contrase&ntilde;a:</label></td>
									<td width="200" align="left"><form:input path="passLogin" id="passLogin" type="password"/></td>
								</tr>
								<tr>
								</tr>
								<tr>
									<td colspan="2">
		<span class="vinculos" style="cursor: pointer; color: #0000FF;" onClick="cambiarClave();">
		<center>&iquest;Ha olvidado su Contrase&ntilde;a?</center>
		</span>
									</td>
								</tr>
								<tr>
									<td width="400">&nbsp;</td>
									<td width="100">
										<div class="button_holder">
											<div class="button1"><div class="next"><a onclick="javascript:onLogin();">Acceder</a></div></div>										</div>
									</td>
								</tr>
							</table>
							<div class="clr"></div>
							<input type="submit" style="border: 0; padding: 0; margin: 0; width: 0px; height: 0px;" value="Acceder">
						</form:form>
						<div class="clr"></div>
					</div>
					<div class="b">
						<div class="b">
							<div class="b"></div>
						</div>
					</div>
				</div>
				<p>Ingrese Usuario y Contrase&ntilde;a v&aacute;lida para acceder al Sistema</p>
				<div id="lock"></div>
				<div class="clr"></div>
			</div>
			<div class="b">
				<div class="b">
					<div class="b"></div>
				</div>
			</div>
		</div>
		<noscript>
			Advertencia! JavaScript debe estar habilitado para un correcto funcionamiento de la Administracin
		</noscript>
		<div class="clr"></div>
	</div>
</div>
<div id="border-bottom">
	<div><div></div></div>
</div>
<div id="footer">
	<p class="copyright">Contraloria General de la Naci&oacute;n 2020 &copy; Todos los derechos reservados.</p>
</div>
</body>
</html>
