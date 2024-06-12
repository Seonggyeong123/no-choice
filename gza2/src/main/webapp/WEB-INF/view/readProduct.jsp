<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 보기</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>작성자</td>
			<td>${productData.product.writer.id}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${productData.product.productTitle}</td>
		</tr>
		<tr>
			<td>서브제목</td>
			<td>${productData.content.productSubTitle}</td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><img src="/gza/imageStorage/${productData.image.storeName}" alt="${prodyctData.image.originalName}" width="100" height="100"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><c:out value="${productData.content.productContent}" /></td>
		</tr>
		<tr>
			<td>유형</td>
			<td>${productData.content.productType}</td>
		</tr>
		<tr>
			<td>가격</td>
			<td><span id="price">${productData.product.price}</span>원</td>
		</tr>
		<tr>
			<td>게스트 수</td>
			<td>${productData.content.guests}</td>
		</tr>
		<tr>
			<td>위치</td>
			<td>${productData.content.location}</td>
		</tr>
		<tr>
			<td>시작 날짜</td>
			<td><input type="date" id="start_date" name="start_date" onchange="calculateTotalPrice()"></td>
		</tr>
		<tr>
			<td>종료 날짜</td>
			<td><input type="date" id="end_date" name="end_date" onchange="calculateTotalPrice()"></td>
		<tr>
			<td>총 가격</td>
			<td><span id="total_price">0원</span></td>
		</tr>

	</table>

	<a href="list.do">목록으로 돌아가기</a>
	<c:if test="${authUser.id == productData.product.writer.id}">
		<c:if test="${not empty productData.product.productNum}">
			<a href="modify.do?no=${productData.product.productNum}">[게시글 수정]</a>
			<a href="delete.do?no=${productData.product.productNum}">[게시글 삭제]</a>
		</c:if>
	</c:if>
	<c:if test="${authUser.id != productData.product.writer.id}">
		<form action="book.do" method="post">
			<input type="hidden" name="productNum" value="${productData.product.productNum}">
			<input type="hidden" name="start_date" id="start_date_input">
			<input type="hidden" name="end_date" id="end_date_input">
			<input type="hidden" name="total_price" id="total_price_input">
			<input type="submit" value="예약하기">
		</form>
	</c:if>

<script type="text/javascript" src="/gza/resources/js/book.js"></script>
</body>
</html>