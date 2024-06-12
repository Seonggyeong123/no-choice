<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>

	<form action="modify.do?no=${modReq.productNum}" method="post"
		enctype="multipart/form-data" accept="image/*">
		<p>
			번호 : <br />${modReq.productNum}
		</p>
		<p>
			제목 : <br />
			<input type="text" name="title" value="${modReq.productTitle}">
			<c:if test="${errors.title}">제목을 입력하세요</c:if>
		</p>
		<p>
			서브제목 : <br />
			<input type="text" name="subtitle" value="${modReq.productSubtitle }">
		</p>
		<p>
			가격 : <br />
			<input type="number" name="price" value="${modReq.price }">
		</p>
		<div>
			이미지 파일 선택 <br />
			<input id="file-upload" type="file" name="file"
				onchange="previewImage()">
			<div class="image-preview">
				<h2>이미지 미리보기</h2>
				<img id="image-preview" src="" alt="Image Preiview">
			</div>
		</div>
		<p>
			내용 : <br />
			<textarea rows="5" cols="30" name="content">${modReq.productContent}</textarea>
		</p>

		<input type="submit" value="글 수정">
	</form>

<script type="text/javascript" src="/gza_test/resources/js/preview.js"></script>
</body>
</html>