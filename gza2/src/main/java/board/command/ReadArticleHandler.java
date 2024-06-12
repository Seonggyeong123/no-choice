package board.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Comment;
import board.service.ArticleContentNotFoundException;
import board.service.ArticleData;
import board.service.ArticleNotFoundException;
import board.service.CommentService;
import board.service.ReadArticleService;
import mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler {

    private ReadArticleService readService = new ReadArticleService();
    private CommentService commentService = new CommentService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String noVal = req.getParameter("no");
        int articleNum = Integer.parseInt(noVal);

        try {
            ArticleData articleData = readService.getArticle(articleNum, true);
            req.setAttribute("articleData", articleData);

            // 요청에서 카테고리를 읽어오고 없으면 게시글의 카테고리를 사용
            String category = req.getParameter("category");
            if (category == null || category.isEmpty()) {
                category = articleData.getArticle().getCategory();
            }
            req.setAttribute("category", category);

            String pageNoVal = req.getParameter("pageNo");
            int pageNo = pageNoVal != null ? Integer.parseInt(pageNoVal) : 1;
            req.setAttribute("pageNo", pageNo);

            // 댓글 및 대댓글 데이터 가져오기
            List<Comment> comments = commentService.getCommentsByArticle(articleNum);
            req.setAttribute("comments", comments);

            return "/WEB-INF/view/board/boardRead.jsp";
        } catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
            req.getServletContext().log("no article", e);
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
}
