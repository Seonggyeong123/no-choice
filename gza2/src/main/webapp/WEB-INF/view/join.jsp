<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>가입</title>
</head>
<body>
<form action="join.do" method="post">
<p>
	아이디 :<br/><input type="text" name="id" placeholder="아이디" value="${param.id}">
	<c:if test="${errors.id}">ID를 입력하세요.</c:if>
	<c:if test="${errors.duplicateId}">이미 사용중인 아이디입니다.</c:if>
</p>
<p>
	닉네임 :<br/><input type="text" name="memberName" placeholder="닉네임을(를) 입력하세요" value="${param.memberName}">
	<c:if test="${errors.memberName}">닉네임을 입력하세요.</c:if>
</p>
<p>
	비밀번호 :<br/><input type="password" placeholder="비밀번호" name="password">
	<c:if test="${errors.password}">비밀번호를 입력하세요.</c:if>
</p>
<p>
	비밀번호 확인 :<br/><input type="password" placeholder="비밀번호 확인" name="confirmPassword">
	<c:if test="${errors.confirmPassword}">비밀번호 확인을 입력하세요.</c:if>
	<c:if test="${errors.notMatch}">비밀번호와 비밀번호 확인이 일치하지 않습니다.</c:if>
</p>
<p>
	휴대폰 번호 :<br/><input type="text" name="phoneNum" value="${param.phoneNum}">
	<c:if test="${errors.phoneNum}">핸드폰 번호 11자리를 입력하세요.</c:if>
</p>
<p>
	생일 :<br/><input type="text" name="birthday" value="${param.birthday}">
	<c:if test="${errors.birthday}">생년월일 8자리를 입력하세요.</c:if>
</p>
<input type="submit" value="가입하기">
</form>
</body>
</html>