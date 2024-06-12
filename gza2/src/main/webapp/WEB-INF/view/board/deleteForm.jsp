<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>
<body>

<form action="delete.do?no=${delReq.productNum}" method="post" enctype="multipart/form-data" accept="image/*">
<p>
번호 : <br/>${delReq.productNum}
</p>
<p>
제목 : <br/><input type="text" name="title" value="${delReq.productTitle}" readonly="readonly">
</p>
    		<br/><input id="file-upload" type="file" name="file" onchange="previewImage()">
    		<div class="image-preview">
    			<h2>이미지 미리보기</h2>
    			<img id="image-preview" src="" alt="Image Preiview">
    		</div>
<p>
내용 : <br/>
<textarea rows="5" cols="30" name="content" readonly="readonly">${delReq.productContent}</textarea>
</p>

<input type="submit" value="글 삭제">
</form>

</body>
</html>