<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	    <spring:url value="/resources/css/noticias.css" var="noticias"/>
        <spring:url value="/resources/css/carrusel.css" var="carrusel"/>
		<spring:url value="/resources/css/mensaje/jquery.alerts.css" var="jqueryAlerts"/>
		<spring:url value="/resources/img/" var="images"/>
		<link type="text/css" href="${noticias}" rel="stylesheet"/>
		<link type="text/css" href="${carrusel}" rel="stylesheet"/>
		<link type="text/css" href="${jqueryAlerts}" rel="stylesheet"/>

		<!-- JS -->
		<script type="text/javascript" src="/sicogen-mf/resources/js/carrusel/jquery.js"></script>
		<script type="text/javascript" src="/sicogen-mf/resources/js/carrusel/stepcarousel.js"></script>
		<script type="text/javascript" src="/sicogen-mf/resources/js/carrusel/i_carrusel.js"></script>
		<script type="text/javascript" src="/sicogen-mf/resources/js/mensaje/jquery.alerts.js"></script>
		<script type="text/javascript" src="/sicogen-mf/resources/js/jquery.cluetip.js" ></script>
		<script type="text/javascript" src="/sicogen-mf/resources/js/SpryStyles/SpryCollapsiblePanel.js"></script>


<div style="padding: 15px;" class="cuadroNoticias">
		<div style="width: 1000px;" class="stepcarousel" id="noticias">
		    <div class="belt" style="width: 0px;">
		    	
			</div>
		</div>
	</div>