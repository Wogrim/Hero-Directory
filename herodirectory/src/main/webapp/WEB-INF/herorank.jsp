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
<title>Create Hero Ranks</title>
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
				<h1>All Hero Ranks</h1>
				<table class="table table-light">
					<thead>
						<tr>
							<th>ID</th>
							<th>name</th>
							<th>sorting power</th>
							<th>color</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rank" items="${allRanks}">
							<tr>
								<td><c:out value="${rank.id}"></c:out></td>
								<td><c:out value="${rank.name}"></c:out></td>
								<td><c:out value="${rank.relativePower}"></c:out></td>
								<td style="background-color:${rank.color};" class="fw-bold"><c:out value="${rank.color}"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="col">
				<h1>Create Hero Rank</h1>
				<form:form action="/super-admin/hero-rank" method="post"
					modelAttribute="newHeroRank" class="p-3 bg-light">
					<table class="w-100">
						<tbody>
							<tr>
								<td><form:label path="name">Name:</form:label></td>
								<td><form:input path="name" class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="name" /></td>
							</tr>
							<tr>
								<td><form:label path="relativePower">Sorting Power:</form:label></td>
								<td><form:input type="number" path="relativePower" class="w-100" value="30" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="relativePower" /></td>
							</tr>
							<tr>
								<td><form:label path="color">Color:</form:label></td>
								<td><form:input path="color" type="color" class="w-100" value="#FF3333"/></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="color" /></td>
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