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
		Taldi Engenharia
	</a> </nav>
	
	<div class="container text-center">
		<hr>
		<h3>Usuários</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered text-center">
				<thead>
					<tr>
						<th class="text-center">Id</th>
						<th class="text-center">Nome</th>
						<th class="text-center">E-mail</th>
						<th class="text-center">UC</th>
						<th class="text-center">Relatório</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="usuario" items="${usuarios}">
						<tr>
							<td>${usuario.id}</td>
							<td>${usuario.pessoa.nome}</td>
							<td>${usuario.login}</td>
							<td>
								<a href="uconsumidora/${usuario.id}">
								<img src="/images/glyphicons/png/glyphicons-21-home.png"
									 width="22" height="20" ></img>
								</a>
							</td>
							<td>
								<a href="solar/pdf/${usuario.id}">
								<img src="/images/glyphicons/png/glyphicons-30-notes-2.png"
									 width="15" height="20" ></img>
								</a>
							</td>
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