package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.CommentService;
import mvc.command.CommandHandler;

public class CommentReplyUpdateHandler implements CommandHandler {

    private CommentService commentService = new CommentService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));
        String content = req.getParameter("content");
        int articleNo = Integer.parseInt(req.getParameter("articleNo"));
        String category = req.getParameter("category");
        String responseMessage;

        try {
            if ("updateComment".equals(action)) {
                commentService.updateComment(id, content);
                responseMessage = "댓글이 성공적으로 수정되었습니다.";
            } else if ("updateReply".equals(action)) {
                commentService.updateReply(id, content);
                responseMessage = "답글이 성공적으로 수정되었습니다.";
            } else {
                responseMessage = "알 수 없는 작업입니다.";
            }
        } catch (RuntimeException e) {
            responseMessage = "작업에 실패했습니다: " + e.getMessage();
        }

        req.setAttribute("responseMessage", responseMessage);
        res.sendRedirect("read.do?no=" + articleNo + "&category=" + category);
        return null;
    }
}
