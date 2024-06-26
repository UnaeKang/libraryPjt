<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 폰트 -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700"
	rel="stylesheet">
<!-- Bootstrap -->
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" />
<!-- Slick : carousel -->
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/slick.css'/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/slick-theme.css'/>" />
<!-- nouislider -->
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/nouislider.min.css'/>" />
<!-- Font Awesome Icon -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" />
<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/style.css'/>" />
<!-- HEADER -->
<header>
	<!-- TOP HEADER -->
	<div id="top-header">
		<div class="container">
			<ul class="header-links pull-right">
				<c:choose>
					<c:when test="${empty loginedMember}">
						<li><a href="/join"><i class="fa fa-user-o"></i>Sign Up</a></li>
						<li><a href="/login"><i class="fa fa-sign-in"></i>Login</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/member/${loginedMember.m_no}"><i
								class="fa fa-user-o"></i>Edit</a></li>
						<li><a href="/logout"><i class="fa fa-sign-out"></i>Logout</a></li>
					</c:otherwise>
				</c:choose>
			</ul>

		</div>
	</div>
	<!-- /TOP HEADER -->
	<!-- MAIN HEADER -->
	<div id="header">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-3"></div>
				<!-- SEARCH BAR -->
				<div class="col-md-6">
					<div class="header-search">
						<form name="searchFrm" method="get" action="/book">
							<select class="input-select">
								<option value="0">Book</option>
							</select> 
							<input class="input" name="b_name" placeholder="Search here">
							<button class="search-btn">Search</button>
						</form>
					</div>
				</div>
				<!-- /SEARCH BAR -->
				<div class="col-md-3"></div>
			</div>
			<!-- row -->
		</div>
		<!-- container -->
	</div>
	<!-- /MAIN HEADER -->
</header>
<!-- /HEADER -->