<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 쓰기</title>
</head>
<body>
	<form action="write.do" method="post">
		<p>

			<label for="category"> <select id="category" name="category"
				size="1">
					<option value="">카테고리를 선택해주세요.</option>
					<option value="freeboard">자유게시판</option>
					<option value="togetherboard">동행게시판</option>
					<option value="latterboard">후기게시판</option>
			</select>
			</label>

		</p>
		<p>
			제목:<br /> <input type="text" name="title" value="${param.title}">
			<c:if test="${errors.title}">제목을 입력하세요.</c:if>
		</p>
		<p>
			내용:<br />
			<textarea name="content" rows="5" cols="30">${param.title}</textarea>
		</p>
		<input type="submit" value="새 글 등록">
	</form>
	<button onclick="goBackToPreviousPage()">이전 페이지로 돌아가기</button>
</body>
<script>
	const categorySelect = document.getElementById("category");
	const submitButton = document.querySelector("input[type='submit']");

	submitButton.addEventListener("click", function(event) {
		const selectedCategory = categorySelect.value;
		if (selectedCategory === "") {
			event.preventDefault();
			alert("카테고리를 선택해주세요.");
		}
	});
	function goBackToPreviousPage() {

		var isGoBack = confirm("게시글 작성 내용을 저장하지 않았습니다. 이전 페이지로 돌아가시겠습니까?");

		if (isGoBack) {
			history.back();
		}
	}
</script>
</html>