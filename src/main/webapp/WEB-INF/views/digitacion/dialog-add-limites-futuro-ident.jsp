<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-11-25
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<div class="py-1">
    <div class="container-fluid">
        <div id="cont_add_lim_fut_ident">
        <c:forEach var="monAnio" items="${limiteTemp.montosAnio}">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>${monAnio.anio}</label>
                        <input type="text" class="form-control add-lim-fut-ident" value="${monAnio.monto}" id="add_lim_fut_${monAnio.anio}" name="add_lim_fut_${monAnio.anio}"/>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
        <div class="row float-right">
            <button type="button" class="btn btn-primary" id="btnAgregarEjercicioIdent">Agregar Ejercicio</button>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $(".add-lim-fut-ident").each(function(){
            $(this).numeric({ negative: false });
        });

        $('#btnAgregarEjercicioIdent').click(function () {
            let anio = 0;
            $("#cont_add_lim_fut_ident").find(':input').each(function() {
                anio=parseInt((this.id).replace('add_lim_fut_', ''));
                //alert(anio+"---"+this.name+"-"+this.id+"--"+this.value);
            });
            anio = anio + 1;
            let htmlString ="<div class= \"row \"><div class= \"col-md-12 \"><div class= \"form-group \">" +
                "<label>"+anio+"</label>" +
                "<input type= \"text \" class= \"form-control add-lim-fut-ident \" value= \"0\" id= \"add_lim_fut_"+anio+"\" name=\"add_lim_fut_"+anio+"\"/>" +
                "</div></div></div>";

            $('#cont_add_lim_fut_ident').append(htmlString);
            $("#add_lim_fut_"+anio).numeric({ negative: false });
        });
    });
</script>
