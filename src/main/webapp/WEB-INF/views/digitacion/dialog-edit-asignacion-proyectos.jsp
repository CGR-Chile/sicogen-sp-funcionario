<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-11-24
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<div class="py-1">
    <div class="container-fluid">
        <div class="row" style="border-collapse:collapse;">
            <div class="col-md-12">
                <h6>Monto Cuenta Asignación</h6>
            </div>
        </div>
        <c:forEach var="monAsig" items="${montosAsignacion}">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>${monAsig.codigoAsignacion} ${monAsig.nombreAsignacion}</label>
                        <input type="text" class="form-control monto-edit-asig" value="${monAsig.monto}" id="asig_edit_${monAsig.codigoAsignacion}" name="asig_edit_${monAsig.codigoAsignacion}_${monAsig.nombreAsignacion}">
                    </div>
                </div>
            </div>
            <script>
                $(document).ready(function () {
                    $('#asig_edit_${monAsig.codigoAsignacion}').numeric({ negative: false });
                });
            </script>
        </c:forEach>
    </div>
</div>
