<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-11-25
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<div class="py-1">
    <div class="container-fluid">
        <c:forEach var="monAnio" items="${limiteTemp.montosAnio}">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>${monAnio.anio}</label>
                        <input type="text" class="form-control add-lim-fut-mod" value="${monAnio.monto}" id="add_lim_fut_${monAnio.anio}" name="add_lim_fut_${monAnio.anio}"/>
                    </div>
                </div>
            </div>
            <script>
                $(document).ready(function () {
                    $('#add_lim_fut_${monAnio.anio}').numeric({ negative: false });
                });
            </script>
        </c:forEach>
    </div>
</div>
