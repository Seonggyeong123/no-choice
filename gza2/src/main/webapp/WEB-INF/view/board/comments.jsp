<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comments</title>
</head>
<body>
    <c:forEach var="comment" items="${comments}">
        <div class="comment">
            <p>${comment.commentName}: ${comment.content}</p>
            <p>작성일: ${comment.formattedRegDate}</p>
            <c:forEach var="reply" items="${comment.replies}">
                <div class="reply">
                    <p>${reply.replyName}: ${reply.content}</p>
                    <p>작성일: ${reply.formattedRegDate}</p>
                </div>
            </c:forEach>
        </div>
    </c:forEach>

    <form action="comment.do" method="post">
        <input type="hidden" name="action" value="addComment">
        <input type="hidden" name="articleNo" value="${param.articleNo}">
        <input type="hidden" name="category" value="${param.category}">
        <label for="commentId">ID:</label>
        <input type="text" id="commentId" name="commentId">
        <label for="commentName">Name:</label>
        <input type="text" id="commentName" name="commentName">
        <label for="content">Comment:</label>
        <textarea id="content" name="content"></textarea>
        <button type="submit">댓글 작성</button>
    </form>

    <form action="comment.do" method="post">
        <input type="hidden" name="action" value="addReply">
        <input type="hidden" name="articleNo" value="${param.articleNo}">
        <input type="hidden" name="category" value="${param.category}">
        <label for="commentNo">Comment No:</label>
        <input type="text" id="commentNo" name="commentNo">
        <label for="replyId">ID:</label>
        <input type="text" id="replyId" name="replyId">
        <label for="replyName">Name:</label>
        <input type="text" id="replyName" name="replyName">
        <label for="content">Reply:</label>
        <textarea id="content" name="content"></textarea>
        <button type="submit">대댓글 작성</button>
    </form>
</body>
</html>
