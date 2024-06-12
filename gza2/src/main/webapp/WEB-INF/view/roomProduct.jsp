<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="/gza/css/listproduct.css">
<style>
.wrapper {
	display: flex;
	overflow-x: auto; /* 가로 스크롤바 추가 */
	white-space: nowrap;
	margin-bottom: 20px;
}

.container {
	width: 300px;
	border: 1px solid transparent; /* 투명 외곽선 */
	padding: 10px;
	margin: 10px;
	text-align: center;
	display: inline-block; /* 옆으로 정렬 */
	box-sizing: border-box;
}

.img-box {
	width: 180px;
	height: 200px;
	border: 2px dashed black;
	margin-bottom: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.img-box img {
	max-width: 180px;
	max-height: 100%;
}

.title, .price {
	font-size: 16px;
	margin: 5px 0;
	text-align: left; /* 왼쪽 정렬 */
}
</style>
</head>
<body>
	<header>
		<div class="logo">G•za</div>
		<div class="search-container">
			<div class="search-bar">
				<input type="text" placeholder="검색">
			</div>
		</div>
		<a href="/gza/product/write.do" class="register-link">상품등록</a>
		<div class="profile-icon" onclick="toggleMenu()">
			<img src="data:image/png;base64,{{base64_profile_image}}"
				alt="Profile Icon">
		</div>
		<div class="toggle-menu">
			<a href="#">프로필</a> <a href="#">설정</a> <a href="#">로그아웃</a>
		</div>
	</header>
	<div class="categories">
		<a href="#">캘리포니아</a> <a href="#">와이파이 가능</a> <a href="#">휴양지</a> <a
			href="#">체험</a> <a href="#">로맨틱</a> <a href="#">독특한 숙소</a>
	</div>
	<main>

		<div class="room">
			<h2>숙박</h2>
		</div>
		<div class="wrapper" id="container-wrapper-1">
			<c:forEach var="product" items="${productPage.content}">
				<div class="container">
					<div class="img-box">
						<img alt="img" src="#">
					</div>
					<div class="title">
						<a
							href="read.do?no=${product.productNum}&pageNo=${productPage.currentPage}">
							<c:out value="${product.productTitle}" />
						</a>
					</div>
					<div class="price">${product.price}</div>
				</div>
			</c:forEach>
		</div>

		<div>
			<h2>체험</h2>
		</div>
		<div class="wrapper" id="container-wrapper-2">
			<c:forEach var="product" items="${productPage.content}">
				<div class="container">
					<div class="img-box">
						<img alt="img" src="#">
					</div>
					<div class="title">
						<a
							href="read.do?no=${product.productNum}&pageNo=${productPage.currentPage}">
							<c:out value="${product.productTitle}" />
						</a>
					</div>
					<div class="price">${product.price}</div>
				</div>
			</c:forEach>
		</div>

		<c:forEach var="product" items="${productPage.content}">
			<tr>
				<td>${product.productNum}</td>
				<td><a
					href="read.do?no=${product.productNum}&pageNo=${productPage.currentPage}">
						<c:out value="${product.productTitle}" />
				</a></td>
				<td>${product.price}</td>
				<td>${product.writer.id}</td>
			</tr>
		</c:forEach>
		<c:if test="${productPage.hasProduct()}">
			<tr>
				<td colspan="4"><c:if test="${productPage.startPage>5}">
						<a href="list.do?pageNo=${productPage.startPage-5}">[이전]</a>
					</c:if> <c:forEach var="pNo" begin="${productPage.startPage}"
						end="${productPage.endPage}">
						<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
					</c:forEach> <c:if test="${productPage.endPage<productPage.totalPages}">
						<a href="list.do?pageNo=${productPage.startPage+5}">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>
	</main>
	<footer> Footer Content </footer>
	<script src="/gza/script/index.js"></script>
</body>
</html>
