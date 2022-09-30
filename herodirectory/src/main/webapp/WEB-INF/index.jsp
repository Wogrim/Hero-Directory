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
<title>Hero Directory</title>
<!-- for Bootstrap css -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- for static css -->
<link rel="stylesheet" href="/css/style.css" />
</head>
<body>
	<div class="w-100 bg-dark">
		<div class="container py-3">
			<h1 class="text-warning">Hero Directory</h1>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Check Out the Newest Heroes!</h1>

				<div class="d-flex justify-content-between flex-wrap">

					<c:forEach var="recentHero" items="${recentHeroes}">
						<div class="heroCard"
							style="background-color:${recentHero.heroRank.color};">
							<div class="imgContainer overflow-hidden">
							<img
								src="${recentHero.profileImage.foldername}${recentHero.profileImage.filename}"
								alt="profile picture" class="d-block mx-auto"/>
							</div>
							<div class="bg-dark nameplate">
								<h4 class="text-center text-warning mb-0">
									<c:out value="${recentHero.heroName}"></c:out>
								</h4>
							</div>
							<div class="philosophy">
							<p class="text-center fst-italic mb-0 px-1"><c:out value="${recentHero.philosophy}"></c:out></p>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
		</div>
	</div>
</body>
</html>