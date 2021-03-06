<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="\stylesheets\styles.css">
    <link rel="stylesheet" href="\semantic\out\semantic.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="\semantic\out\semantic.min.js"></script>
    <!--<script type="text/javascript" src="/js/funcionesAdministrador.js">
    </script>
    -->
    <script type="text/javascript">
        $(window).on('scroll', function() {
            if ($(window).scrollTop()) {
                $('#divblack').addClass('greysi fixed');
            } else {
                $('#divblack').removeClass('greysi fixed');
            }
        })
    </script>
    <script>
        $(document).ready(function() {

            $('.message .close')
                .on('click', function() {
                    $(this)
                        .closest('.message')
                        .transition('fade')
                    ;
                })
            ;


            $('button[name=btnEliminar]').click(function() {
                $("div[class='ui dimmer modals page transition hidden']").remove();
                var datos = $(this).attr('value').split(".");
                var estaActivo = datos[4];
                var mensaje;
                var mensaje2;
                if(estaActivo=="true")
                {
                    mensaje="Desactivar";
                    mensaje2="Desactivado";
                }
                else
                {
                    mensaje="Activar";
                    mensaje2="Desactivar";
                }

                $('#body').append('<div class="ui basic modal"></div>');
                $("div[class='ui basic modal']").append('<div class="ui icon header"><i class="archive icon"></i>\n' +
                    '¿'+mensaje+' a '+datos[1]+" "+datos[2]+'? </div>');
                $("div[class='ui basic modal']").append('<div class="content">\n' +
                    '        <p>Usted esta a punto de '+mensaje+' al '+datos[3]+" "+datos[1]+" "+datos[2]+', si presiona '+mensaje+' ' +
                    'este empleado será '+mensaje2+' en el sistema </p>\n' +
                    '    </div>');
                $("div[class='ui basic modal']").append('<div class="actions">\n' +
                    '        <div id="iVolverAtras" class="ui red basic cancel inverted button">\n' +
                    '            <i class="remove icon"></i>\n' +
                    '            No\n' +
                    '        </div>\n' +
                    '        <div id="iEliminar" class="ui green ok inverted button">\n' +
                    '            <i class="checkmark icon"></i>\n' +
                    '            '+mensaje+'\n' +
                    '        </div>\n' +
                    '    </div>');
                $('.ui.basic.modal')
                    .modal('show')
                ;
                $("#iEliminar").click(function(){

                    if(estaActivo=="true")
                    {
                        $.ajax({
                            type : 'POST',
                            contentType : 'application/json; charset=utf-8',
                            dataType : 'json',
                            url : "/intranet/administrador/desactivarEmpleado",
                            data : JSON.stringify(datos[0]),
                            success : function (response) {
                                location.reload();
                            },
                            error : function (e) {
                                console.log("Error",e);
                            }
                        })
                    }
                    else
                    {
                        $.ajax({
                            type : 'POST',
                            contentType : 'application/json; charset=utf-8',
                            dataType : 'json',
                            url : "/intranet/administrador/activarEmpleado",
                            data : JSON.stringify(datos[0]),
                            success : function (response) {
                                console.log(response);
                                location.reload();
                            },
                            error : function (e) {
                                console.log("Error",e);
                            }
                        })
                    }
                });
                $("#iVolverAtras").click(function(e){
                    $('.ui.basic.modal')
                        .modal('hide');
                });
            });
        });
    </script>
    <meta charset="UTF-8"/>
    <title>Perfil administrador</title>
    <link rel="shortcut icon" type="image/png" href="/img/fyclogo.png"/>
</head>
<body id="body">
<!-- HEADER -->
	<div class="pusher card">
		<div
			class="ui vertical sc-main-intranet-perfiles center aligned segment">
			<div class="ui container">
				<div id="divblack" class="following bar">
					<div class="ui large secondary inverted pointed fixed menu">
						<a class="item sin-hover" href="/intranet/inventario"><img src="/img/logo-blanco.png" class="ui tiny image"> </a>
						<div class="right item">
							<a class="item" href="/intranet/inventario">Perfil</a> 
							<a href="/intranet/administrador/crearEmpleado" class="item">Crear perfil</a>
							<button type="button" onclick="location.href='/logout'" class="ui inverted olive button">Cerrar Sesion</button>
						</div>
					</div>
				</div>
			</div>


				<div class="bottomleft">
					<h1 class="titulos-perfiles margenes-abajo">${persona.nombre} ${persona.apellido}</h1>
					<p class="ui grey inverted left aligned header perfiles">Bienvenido al perfil de Administrador</p>
				</div>
		</div>
	</div>
	<!-- END HEADER -->

<c:if test="${mensaje != null}">
    <div class="ui success message">
        <i class="close icon"></i>
        <div class="header">
            ${mensaje}
        </div>
    </div>
</c:if>
<div class="column margen-arriba margen-abajo">
		<h2 class="ui center aligned header">Empleados<div class="sub header">En la siguiente tabla se podrán visualizar todos los empleados<br>También puede desactivar a estos y editar sus perfiles</div></h2>
	</div>
<div class="margenes-tabla">
<table class="ui celled structured olive table">
    <thead>
    <tr>
        <th rowspan="2" class="center aligned">Nombre</th>
        <th rowspan="2" class="center aligned">Apellido</th>
        <th rowspan="2" class="center aligned">Email</th>
        <th rowspan="2" class="center aligned">Sueldo</th>
        <th rowspan="2" class="center aligned">Cargo</th>
        <th colspan="2" class="center aligned">Realizar Accion</th>
    </tr>
    <tr>
        <th class="center aligned">Editar</th>
        <th class="center aligned">Desactivar</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach begin="0" var="i" end="${fn:length(empleados)-1}">
        <tr>
            <td>${empleados[i].persona.nombre}</td>
            <td>${empleados[i].persona.apellido}</td>
            <td>${empleados[i].emailEmpleado}</td>
            <td class="right aligned">$${sueldos[i]}</td>
            <td>${empleados[i].tipoEmpleado.descripcion}</td>
            <c:choose>
                <c:when test="${empleados[i].estaActivo==true}">
                    <td class="center aligned"><a href="/intranet/administrador/editarEmpleado/${empleados[i].idEmpleado}">Editar</a></td>
                </c:when>
                <c:otherwise>
                    <td class="center aligned"></td>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${empleados[i].estaActivo==true}">
                    <td class="center aligned"><button class="ui red button" name="btnEliminar" value="${empleados[i].idEmpleado}.${empleados[i].persona.nombre}.${empleados[i].persona.apellido}.${empleados[i].tipoEmpleado.descripcion}.${empleados[i].estaActivo}">Desactivar</button></td>
                </c:when>
                <c:otherwise>
                    <td class="center aligned"><button class="ui green button" name="btnEliminar" value="${empleados[i].idEmpleado}.${empleados[i].persona.nombre}.${empleados[i].persona.apellido}.${empleados[i].tipoEmpleado.descripcion}.${empleados[i].estaActivo}">Activar</button></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>

<div style="height: 50px;"></div>
	
<!-- FOOTER -->
<footer class="ui inverted vertical footer segment" id="footer">
    <div class="ui center aligned container">
        <div class="ui stackable inverted divided grid pad-footer">
            <div class="eleven wide column">
                <h4 class="ui inverted header">Fresh & Clean</h4>
                <div class="ui inverted link list">
                    <a href="#" class="item">Barbería Fresh & Clean</a> 
                    <a href="#" class="item">Servicios de barbería y belleza</a> 
                    <a href="#" class="item">Teléfono: 225050050</a> 
                    <a href="#" class="item">freshandclean@gmail.cl</a>
                </div>
            </div>
            <div class="five wide column">
                <h4 class="ui inverted header">Redes Sociales</h4>
                <div class="ui inverted link list">
                    <a href="#" class="item"><i class="facebook outline icon"></i>Facebook</a> 
                    <a href="#" class="item"><i class="twitter outline icon"></i>Twitter</a> 
                    <a href="#" class="item"><i class="instagram outline icon"></i>Instagram</a>
                    <a href="#" class="item"><i class="pinterest outline icon"></i>Pinterest</a>
                </div>
            </div>
        </div>
        <div class="ui inverted section divider"></div>
        <img src="/img/logo-blanco.png" class="ui small centered image">
        <div class="ui horizontal inverted small divided link list">
            <a class="item" href="#">Fresh&Clean</a> 
            <a class="item" href="#">Contactanos</a>
            <a class="item" href="#">Nosotros</a> 
            <a class="item" href="#">Privacy Policy</a>
        </div>
    </div>
</footer>
<!-- END FOOTER -->
</body>
</html>