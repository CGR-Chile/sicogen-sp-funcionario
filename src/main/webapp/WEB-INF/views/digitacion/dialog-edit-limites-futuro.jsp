<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-11-25
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="py-1">
    <div class="container-fluid">
        <div class="row" style="border-collapse:collapse;">
            <div class="col-md-12">
                <h6>${limiteTemp.denominacion}</h6>
            </div>
        </div>
        <c:forEach var="monAnio" items="${limiteTemp.montosAnio}">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>${monAnio.anio}</label>
                        <input type="text" class="form-control edit-lim-fut" value="${monAnio.monto}" id="edit_lim_fut_${monAnio.anio}" name="edit_lim_fut_${monAnio.anio}"/>
                    </div>
                </div>
            </div>
            <script>
                $(document).ready(function () {
                    $('#edit_lim_fut_${monAnio.anio}').numeric({ negative: false });
                });
            </script>
        </c:forEach>
    </div>
</div>
