package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ModifyMemberService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import member.model.Member;
import mvc.command.CommandHandler;

public class ModifyMemberHandler implements CommandHandler {
    private static final String FORM_VIEW = "/WEB-INF/view/modifyMem.jsp";
    private ModifyMemberService modifyMemSvc = new ModifyMemberService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    // Process the GET request to display the form with existing member data
    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        User user = (User) req.getSession().getAttribute("authUser");

        Member member = modifyMemSvc.getMemberById(user.getId());

        req.setAttribute("member", member); // Set the member object in the request scope
        return FORM_VIEW;
    }

    // Process the POST request to handle the form submission
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        User user = (User) req.getSession().getAttribute("authUser");
        
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        
        String curPwd = req.getParameter("curPwd");
        String newPwd = req.getParameter("newPwd");
        String newMemberName = req.getParameter("newMemberName");
        String newPhoneNum = req.getParameter("newPhoneNum");
        String newBirthday = req.getParameter("newBirthday");
        
        // Validate form inputs
        if (curPwd == null || curPwd.isEmpty()) {
            errors.put("curPwd", Boolean.TRUE);
        }
        if (newPwd == null || newPwd.isEmpty()) {
            errors.put("newPwd", Boolean.TRUE);
        }
        if (!errors.isEmpty()) {
            Member member = modifyMemSvc.getMemberById(user.getId());
            req.setAttribute("member", member);
            return FORM_VIEW;
        }
        
        try {
            // Call service method to update member information
            modifyMemSvc.modifyMember(user.getId(), newMemberName, curPwd, newPwd, newPhoneNum, newBirthday);
            return "/WEB-INF/view/my.jsp";
        } catch (InvalidPasswordException e) {
            errors.put("badCurPwd", Boolean.TRUE);
            Member member = modifyMemSvc.getMemberById(user.getId());
            req.setAttribute("member", member); // Set the member object back in the request scope
            return FORM_VIEW;
        } catch (MemberNotFoundException e) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}