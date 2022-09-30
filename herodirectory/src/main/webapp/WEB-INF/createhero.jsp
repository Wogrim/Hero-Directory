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
<title>Create Heroes</title>
<!-- for Bootstrap css -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="w-100 bg-warning">
		<div class="container">
			<h1 class="text-center">Secret Admin Portal</h1>

			<div class="d-flex justify-content-center fs-2">
				<a href="/admin/logout">logout</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Recently Created Heroes</h1>
				<table class="table table-light">
					<thead>
						<tr>
						<th>License</th>
							<th>Name</th>
							<th>Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="recentHero" items="${recentHeroes}">
							<tr>
							<td><c:out value="${recentHero.heroLicenseNumber}"></c:out></td>
								<td><c:out value="${recentHero.heroName}"></c:out></td>
								<td><c:out value="${recentHero.createdAt}"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col">
				<h1>Create Hero</h1>
				<form:form action="/admin/home" method="post"
					enctype="multipart/form-data" modelAttribute="newHero"
					class="p-3 bg-light">
					<table class="w-100">
						<tbody>
							<tr>
								<td><form:label path="heroLicenseNumber">Hero License Number:</form:label></td>
								<td><form:input path="heroLicenseNumber" class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="heroLicenseNumber" /></td>
							</tr>
							<tr>
								<td><form:label path="heroName">Hero Name:</form:label></td>
								<td><form:input path="heroName" class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="heroName" /></td>
							</tr>
							<tr>
								<td><form:label path="gender">Gender:</form:label></td>
								<td><form:select path="gender" class="w-100">
										<form:options items="${allGenders}" itemLabel="name" />
									</form:select></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="gender" /></td>
							</tr>
							<tr>
								<td><form:label path="isActive">Active:</form:label></td>
								<td><form:checkbox path="isActive" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="isActive" /></td>
							</tr>
							<tr>
								<td><form:label path="heroRank">Rank:</form:label></td>
								<td><form:select path="heroRank" class="w-100">
										<form:options items="${allRanks}" itemLabel="name" />
									</form:select></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="heroRank" /></td>
							</tr>
							<tr>
								<td><form:label path="heroSpecialty">Specialty:</form:label></td>
								<td><form:select path="heroSpecialty" class="w-100">
										<form:options items="${allSpecialties}" itemLabel="name" />
									</form:select></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="heroSpecialty" /></td>
							</tr>
							<tr>
								<td><form:label path="philosophy">Philosophy</form:label></td>
								<td><form:textarea path="philosophy" class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="philosophy" /></td>
							</tr>
							<tr>
								<td><label for="pic">Profile Image:</label></td>
								<td><input type="file" name="pic" accept="image/*" required /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="profileImage" /></td>
							</tr>




							<tr class="border-top border-dark border-3">
								<td><form:label path="heroPower">Hero Power:</form:label></td>
								<td><form:input path="heroPower" class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="heroPower" /></td>
							</tr>
							<tr>
								<td><form:label path="location">Location:</form:label></td>
								<td><form:input path="location" class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="location" /></td>
							</tr>

							<tr>
								<td><form:label path="isAvailable">Available:</form:label></td>
								<td><form:checkbox path="isAvailable" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="isAvailable" /></td>
							</tr>
							<tr>
								<td><form:label path="statusMessage">Hero-Only Message</form:label></td>
								<td><form:textarea path="statusMessage" class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="statusMessage" /></td>
							</tr>




							<tr class="border-top border-dark border-3">
								<td><form:label path="password">Password:</form:label></td>
								<td><form:input type="password" path="password"
										class="w-100" /></td>
							</tr>
							<tr>
								<td colspan="2"><form:errors class="text-danger"
										path="password" /></td>
							</tr>
							<tr>
								<td><form:label path="confirm">Confirm Password:</form:label></td>
								<td><form:input type="password" path="confirm"
										class="w-100" /></td>
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