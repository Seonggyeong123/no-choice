<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>

게시글을 수정했습니다.
<br>
${ctxPath=pageContext.request.contextPath;''}
<a href="${ctxPath}/product/list.do">[게시글 목록 보기]</a>
<a href="${ctxPath}/product/read.do?no=${modReq.productNum}">[게시글 내용 보기]</a>
</body>
</html>