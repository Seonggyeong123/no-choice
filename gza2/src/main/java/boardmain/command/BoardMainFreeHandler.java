package boardmain.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Article;
import boardmain.service.BoardMainService;
import mvc.command.CommandHandler;

public class BoardMainFreeHandler implements CommandHandler {

    private BoardMainService articleService = new BoardMainService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Article> latestLatterboardArticles = articleService.getLatestArticlesByCategory("latterboard");
        List<Article> latestFreeboardArticles = articleService.getLatestArticlesByCategory("freeboard");
        List<Article> latestTogetherboardArticles = articleService.getLatestArticlesByCategory("togetherboard");
        List<Article> AllList = articleService.getAllList();
        
        
        
        req.setAttribute("latestLatterboardArticles", latestLatterboardArticles);
        req.setAttribute("latestFreeboardArticles", latestFreeboardArticles);
        req.setAttribute("latestTogetherboardArticles", latestTogetherboardArticles);
        req.setAttribute("AllList", AllList);
       
        return "/WEB-INF/view/main/boardMain.jsp";
    }
}
