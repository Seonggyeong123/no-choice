package board.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ArticleData;
import board.service.ArticleNotFoundException;
import board.service.ModifyArticleService;
import board.service.ModifyRequest;
import board.service.PermissionDeniedException;
import board.service.ReadArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class ModifyArticleHandler implements CommandHandler {
    private static final String FORM_VIEW = "/WEB-INF/view/board/Boardmodify.jsp";

    private ReadArticleService readService = new ReadArticleService();
    private ModifyArticleService modifyService = new ModifyArticleService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processForm(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        try {
            String noVal = req.getParameter("no");
            int no = Integer.parseInt(noVal);
            ArticleData articleData = readService.getArticle(no, false);
            User authUser = (User) req.getSession().getAttribute("authUser");
            if (!canModify(authUser, articleData)) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
            ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
                    articleData.getArticle().getTitle(),
                    articleData.getContent());
            req.setAttribute("modReq", modReq);

            // 카테고리 값을 설정합니다.
            String category = req.getParameter("category");
            if (category == null || category.isEmpty()) {
                category = articleData.getArticle().getCategory();
            }
            req.setAttribute("category", category);

            return FORM_VIEW;
        } catch (ArticleNotFoundException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    private boolean canModify(User authUser, ArticleData articleData) {
        String writerId = articleData.getArticle().getWriter().getId();
        return authUser.getId().equals(writerId);
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        User authUser = (User) req.getSession().getAttribute("authUser");
        String noVal = req.getParameter("no");
        int no = Integer.parseInt(noVal);

        ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
                req.getParameter("title"),
                req.getParameter("content"));
        req.setAttribute("modReq", modReq);

        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        modReq.validate(errors);
        if (!errors.isEmpty()) {
            return FORM_VIEW;
        }
        try {
            modifyService.modify(modReq);

            // 카테고리 값을 설정합니다.
            String category = req.getParameter("category");
            if (category == null || category.isEmpty()) {
                ArticleData articleData = readService.getArticle(no, false);
                category = articleData.getArticle().getCategory();
            }
            req.setAttribute("category", category);

            return "/WEB-INF/view/board/BoardmodifySuccess.jsp";
        } catch (ArticleNotFoundException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } catch (PermissionDeniedException e) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }
}

