package board.command;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import auth.service.User;
import board.model.Comment;
import board.model.Reply;
import board.service.CommentService;
import mvc.command.CommandHandler;

public class CommentHandler implements CommandHandler {

    private CommentService commentService = new CommentService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String action = req.getParameter("action");
        String type = req.getParameter("type");

        HttpSession session = req.getSession();
        User authUser = (User) session.getAttribute("authUser");

        if (authUser == null) {
            res.sendRedirect("login.do");
            return null;
        }

        int articleNo = parseParameter(req, "articleNo");
        if (articleNo == -1) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid article number");
            return null;
        }

       
        if ("list".equals(action) || action == null) {
            List<Comment> comments = commentService.getCommentsByArticle(articleNo);
            req.setAttribute("comments", comments);
            return "/WEB-INF/view/board/boardRead.jsp";
        } else if ("addComment".equals(action)) {
            Comment comment = new Comment(
                    articleNo,
                    0,
                    authUser.getId(),
                    authUser.getMemberName(),
                    req.getParameter("content"),
                    new Date(),
                    req.getParameter("category")
            );
            commentService.addComment(comment);
            res.sendRedirect("read.do?no=" + articleNo);
            return null;
        } else if ("addReply".equals(action)) {
            int commentNo = parseParameter(req, "commentNo");
            if (commentNo == -1) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid comment number");
                return null;
            }
            Reply reply = new Reply(
                    commentNo,
                    0,
                    authUser.getId(),
                    authUser.getMemberName(),
                    req.getParameter("content"),
                    new Date(),
                    req.getParameter("category")
            );
            commentService.addReply(reply);
            res.sendRedirect("read.do?no=" + articleNo);
            return null;
        } else if ("delete".equals(action)) {
            try {
                int id = parseParameter(req, "commentNo");
                int id1 = parseParameter(req, "replyNo");
                if (id == -1) {
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID");
                    return null;
                }

             
                if ("comment".equals(type)) {
                    commentService.deleteComment(id);
                } else if ("reply".equals(type)) {
                    commentService.deleteReply(id1);
                } else {
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid type");
                    return null;
                }
              
                res.sendRedirect("read.do?no=" + articleNo);
            } catch (Exception e) {
                e.printStackTrace();
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while deleting");
            }
            return null;
        } else {
            return "/WEB-INF/view/error.jsp";
        }
    }

    private int parseParameter(HttpServletRequest req, String paramName) {
        String paramValue = req.getParameter(paramName);
        if (paramValue == null || paramValue.isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

