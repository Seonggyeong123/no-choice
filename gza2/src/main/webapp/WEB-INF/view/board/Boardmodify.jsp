<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 수정</title>
</head>
<body>
<form action="modify.do" method="post">
    <input type="hidden" name="no" value="${modReq.articleNumber}">
    <input type="hidden" name="category" value="${category}">
    <p>
        번호:<br/>${modReq.articleNumber}
    </p>
    <p>
        제목:<br/><input type="text" name="title" value="${modReq.title}">
        <c:if test="${errors.title}">제목을 입력하세요.</c:if>
    </p>
    <p>
        내용:<br/>
        <textarea name="content" rows="5" cols="30">${modReq.content}</textarea>
    </p>
    <input type="submit" value="글 수정">
</form>
	<button onclick="goBackToPreviousPage()">취소</button>
</body>
<script>
	function goBackToPreviousPage() {

		var isGoBack = confirm("게시글 수정을 완료하지 않았습니다. 이전 페이지로 돌아가시겠습니까?");

		if (isGoBack) {
			history.back();
		}
	}
</script>

</html>
