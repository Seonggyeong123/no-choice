package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ArticlePage;
import board.service.ListArticleService;
import mvc.command.CommandHandler;

public class ListArticleHandler implements CommandHandler {

    private ListArticleService listService = new ListArticleService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) 
            throws Exception {
        String pageNoVal = req.getParameter("pageNo");
        int pageNo = 1;
        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }
        
        String category = req.getParameter("category");
        ArticlePage articlePage = listService.getArticlePageByCategory(category, pageNo);
        req.setAttribute("articlePage", articlePage);
        req.setAttribute("category", category);
        return "/WEB-INF/view/board/BoardList.jsp";
    }
}