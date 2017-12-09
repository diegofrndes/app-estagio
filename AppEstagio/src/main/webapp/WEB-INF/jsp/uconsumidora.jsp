<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Taldi App</title>
<link rel="stylesheet" href="/bootstrap-4.0.0/css/bootstrap.min.css" />
</head>
<body>
	<nav class="navbar navbar-light bg-faded"> <a
		class="navbar-brand" href="#"> <img src="images/logo.png"
		width="32" height="32" class="d-inline-block align-top" alt="">
		Unidade Consumidora
	</a> </nav>
	
	<div class="container text-center">
		<hr>
		<h3>Unidades Consumidoras</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered text-center">
				<thead>
					<tr>
						<th class="text-center">Id</th>
						<th class="text-center">Denominação</th>
						<th class="text-center">Usuário</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="uconsumidora" items="${uconsumidoras}">
						<tr>
							<td>${uconsumidora.id}</td>
							<td>${uconsumidora.denominacao}</td>
							<td>${proprietario}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script src="/jquery/jquery-3.2.1.min.js"></script>
	<script src="/bootstrap-4.0.0/js/bootstrap.min.js"></script>

</body>
</html>