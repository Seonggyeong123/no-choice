<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>정보 수정</title>
</head>
<body>
<form action="modifyMem.do" method="post">
    <p>
        아이디 :<br/>
        <input type="text" name="id" placeholder="아이디" value="${member.id}" readonly>
        <c:if test="${errors.id}">ID를 입력하세요.</c:if>
        <c:if test="${errors.duplicateId}">이미 사용중인 아이디입니다.</c:if>
    </p>
    <p>
        닉네임 :<br/>
        <input type="text" name="newMemberName" placeholder="닉네임을(를) 입력하세요" value="${member.memberName}">
        <c:if test="${errors.memberName}">닉네임을 입력하세요.</c:if>
    </p>
    <p>
        현재 비밀번호 :<br/>
        <input type="password" placeholder="기존 비밀번호 입력" name="curPwd">
        <c:if test="${errors.curPwd}">현재 비밀번호를 입력하세요.</c:if>
        <c:if test="${errors.badCurPwd}">현재 비밀번호가 일치하지 않습니다.</c:if>
    </p>
    <p>
        새 비밀번호 :<br/>
        <input type="password" placeholder="새 비밀번호 입력" name="newPwd">
        <c:if test="${errors.newPwd}">새 비밀번호를 입력하세요.</c:if>
    </p>
    <p>
        휴대폰 번호 :<br/>
        <input type="text" name="newPhoneNum" value="${member.phoneNum}">
        <c:if test="${errors.phoneNum}">핸드폰 번호 11자리를 입력하세요.</c:if>
    </p>
    <p>
        생일 :<br/>
        <input type="text" name="newBirthday" value="${member.birthday}">
        <c:if test="${errors.birthday}">생년월일 8자리를 입력하세요.</c:if>
    </p>
    <input type="submit" value="확인">
</form>
</body>
</html>