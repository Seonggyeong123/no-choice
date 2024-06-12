<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 수정</title>
</head>
<body>

게시글을 수정했습니다.
<br>

<a href="/gza/list.do?category=${category}">[게시글목록보기]</a>
<a href="/gza/read.do?no=${modReq.articleNumber}&category=${category}">[게시글내용보기]</a>
</body>
</html>
