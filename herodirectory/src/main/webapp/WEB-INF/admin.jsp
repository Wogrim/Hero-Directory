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
<title>Create Admins</title>
<!-- for Bootstrap css -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="w-100 bg-warning">
		<div class="container">
			<h1 class="text-center">Super-Secret Offline Super-Admin Portal</h1>

			<div class="w-100 d-flex justify-content-between fs-2">
			<a href="/super-admin/admin">admins</a> <a
				href="/super-admin/hero-specialty">hero specialties</a> <a
				href="/super-admin/hero-rank">hero ranks</a> <a
				href="/super-admin/gender">genders</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>All Admins</h1>
				<table class="table table-light">
					<thead>
						<tr>
							<th>ID</th>
							<th>GUID</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="admin" items="${allAdmins}">
							<tr>
								<td><c:out value="${admin.id}"></c:out></td>
								<td><c:out value="${admin.adminGUID}"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="col">
				<h1>Create Admin</h1>
				<form:form action="/super-admin/admin" method="post"
					modelAttribute="newAdmin" class="p-3 bg-light">
					<table class="w-100">
						<tbody>
							<tr>
								<td><form:label path="adminGUID">GUID:</form:label></td>
								<td><form:input path="adminGUID" class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="adminGUID" /></td>
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
								<td><form:label path="confirm">Confirm Password:</form:label></td>
								<td><form:input type="password" path="confirm" class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="confirm" /></td>
							</tr>
							<tr>
								<td colspan="2"><button type="submit"
										class="btn btn-success float-end rounded-0 mt-2">Submit</button></td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>