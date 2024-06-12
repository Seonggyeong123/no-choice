package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.DeleteArticleService;
import mvc.command.CommandHandler;

public class DeleteHandler implements CommandHandler {

    private DeleteArticleService deleteService = new DeleteArticleService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) 
            throws Exception {
        String noVal = req.getParameter("no");
        if (noVal == null || noVal.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid article id");
            return null;
        }

        int articleNum;
        try {
            articleNum = Integer.parseInt(noVal);
        } catch (NumberFormatException e) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid article id format");
            return null;
        }

        String category = req.getParameter("category");
        if (category == null) {
            category = "";
        }

        String pageNoVal = req.getParameter("pageNo");
        int pageNo = pageNoVal != null ? Integer.parseInt(pageNoVal) : 1;

        try {
            deleteService.delete(articleNum);
            res.sendRedirect(req.getContextPath() + "/list.do?category=" + category + "&pageNo=" + pageNo);
            return null;
        } catch (Exception e) {
            req.getServletContext().log("delete error", e);
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }
}
