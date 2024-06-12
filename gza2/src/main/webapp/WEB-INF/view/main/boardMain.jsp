<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardMain</title>
 <link rel="stylesheet" href="/gza2/css/home.css"> 
 <link rel="stylesheet" href="/gza2/css/boardMain.css"> 
</head>
<body>

<header>
		<div class="logo">G•za</div>
		<div class="search-container">
			<div class="search-bar">
				<input type="text" placeholder="검색">
			</div>
		</div>
		<div class="register">
			<a href="/gza/product/write.do" class="register-link" id="register">상품등록</a>
		</div>
		<div class="profile-icon" onclick="toggleMenu()">
			<img src="data:image/png;base64,{{base64_profile_image}}"
				alt="Profile Icon">
		</div>
		<div class="toggle-menu">
			<a href="#">프로필</a> <a href="#">설정</a> <a href="#">로그아웃</a>
		</div>
	</header>
	
	<h1>게시판 이용 수칙!</h1>
	<h2>글 쓰는 곳~~  </h2>
    
	<h1>BoardMain</h1>

	<div class="container">
		<div class="test">
			<h2>
				후기게시판 <a href="list.do?category=latterboard" style="float: right;">[더보기]</a>
			</h2>
			<table>
				<c:forEach var="article" items="${latestLatterboardArticles}">
					<tr>
						<td><a href="read.do?no=${article.number}">${article.title}</a>
							<span>${article.writer.name} - ${article.formattedRegDate}</span></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div class="test">
			<h2>
				자유게시판 <a href="list.do?category=freeboard" style="float: right;">[더보기]</a>
			</h2>
			<table>
				<c:forEach var="article" items="${latestFreeboardArticles}">
					<tr>
						<td><a href="read.do?no=${article.number}">${article.title}</a>
							<span>${article.writer.name} - ${article.formattedRegDate}</span></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div class="test">
			<h2>
				동행게시판 <a href="list.do?category=togetherboard" style="float: right;">[더보기]</a>
			</h2>
			<table>
				<c:forEach var="article" items="${latestTogetherboardArticles}">
					<tr>
						<td><a href="read.do?no=${article.number}">${article.title}</a>
							<span>${article.writer.name} - ${article.formattedRegDate}</span></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<button type="button" class="btn-primary" onclick="FirstPage()">메인
			페이지로 이동</button>
	</div>

	<script type="text/javascript">
		function FirstPage() {
			window.location.href = 'home.do';
		}
	</script>
	

	
</body>

</html>
