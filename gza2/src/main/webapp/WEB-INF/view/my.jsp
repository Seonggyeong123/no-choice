<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
</head>
<body>

	${authUser.memberName}님, 안녕하세요 :)
	<a href="order.do">[여행 조회]</a>
	<a href="modifyMem.do">[정보 수정]</a>
	<a href="cancelID.do">[회원 탈퇴]</a>

</body>
</html>