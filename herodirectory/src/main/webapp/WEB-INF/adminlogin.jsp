<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!-- for JSTL c tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- for special form: tags -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hero Directory Admin Login</title>
<!-- for Bootstrap css -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="w-100 bg-warning">
		<div class="container">
			<h1 class="text-center">Secret Admin Portal</h1>
		</div>
	</div>
	<div class="container">
		<form:form action="/admin" method="post" modelAttribute="newAdminLoginAttempt"
			class="p-3 bg-light">
			<h3 class="bg-success p-2 text-light">Login</h3>
			<table class="w-100">
				<tbody>
					<tr>
						<td><form:label path="adminGUID">GUID:</form:label></td>
						<td><form:input path="adminGUID" class="w-100" /></td>
					</tr>
					<tr>
						<td colspan="2"><form:errors class="text-danger" path="adminGUID" /></td>
					</tr>
					<tr>
						<td><form:label path="password">Password:</form:label></td>
						<td><form:input type="password" path="password" class="w-100" /></td>
					</tr>
					<tr>
						<td colspan="2"><form:errors class="text-danger"
								path="password" /></td>
					</tr>
					<tr>
						<td></td>
						<td><button type="submit"
								class="btn btn-success float-end rounded-0 mt-2">Login</button></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>

</body>
</html>