<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 목록</title>
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
	
	
<main class="main">
  <aside class="sidebar">
    <nav class="nav">
      <ul>
        <li class="active"><a href="#">Welcome</a></li>
        <li><a href="list.do?category=latterboard" >후기게시판</a></li>
        <li><a href="list.do?category=freeboard">자유게시판 </a></li>
        <li><a href="list.do?category=togetherboard">동행게시판</a></li>
      </ul>
    </nav>
  </aside>

  <section class="twitter">
    <div class="container">
	<c:set var="category" value="${param.category}" />

	<table border="1">
		<tr>
			<td colspan="4"><a href="write.do?category=${category}">[게시글쓰기]</a></td>
		</tr>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>조회수</td>
			<td>등록일</td>
		</tr>

		<c:if test="${articlePage.content.size() == 0}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>

		<c:forEach var="article" items="${articlePage.content}">
			<tr>
				<td>${article.number}</td>
				<td><a
					href="read.do?no=${article.number}&category=${category}&pageNo=${articlePage.currentPage}">${article.title}</a></td>
				<td>${article.writer.name}</td>
				<td>${article.readCount}</td>
				<td>${article.formattedRegDate}</td>
			</tr>
		</c:forEach>

		<c:if test="${articlePage.total > 0}">
			<tr>
				<td colspan="4"><c:if test="${articlePage.currentPage > 1}">
						<a
							href="list.do?category=${category}&pageNo=${articlePage.currentPage - 1}">[이전]</a>
					</c:if> <c:forEach begin="1" end="${articlePage.totalPages}" var="pNo">
						<c:if test="${pNo == articlePage.currentPage}">
							<span>[${pNo}]</span>
						</c:if>
						<c:if test="${pNo != articlePage.currentPage}">
							<a href="list.do?category=${category}&pageNo=${pNo}">[${pNo}]</a>
						</c:if>
					</c:forEach> <c:if test="${articlePage.currentPage < articlePage.totalPages}">
						<a
							href="list.do?category=${category}&pageNo=${articlePage.currentPage + 1}">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>
	</table>

	<div>
		<a href="/gza/MainBoard.do">[메인게시판으로]</a>
	</div>

	<div>
		<button type="button" onclick="goToIndex()">index.jsp로 이동</button>
	</div>
	<script type="text/javascript">
		function goToIndex() {
			window.location.href = 'home.do';
		}
	</script>
	
	  </div>
  </section>
	</main>
</body>
</html>
