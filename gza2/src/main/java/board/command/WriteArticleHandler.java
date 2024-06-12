package board.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Writer;
import auth.service.User;
import board.service.WriteArticleService;
import board.service.WriteRequest;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/board/boardNewArticleForm.jsp";// 경로 설정을 form_view로 설정
	private WriteArticleService writeService = new WriteArticleService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET")) {// form은 무조건 get방식! req=요청 이것을 get Method로 메소드로 변경함.
														// equalsIgnoercase는 대소문자를 구분하지 않겠다는 뜻
			return processForm(req, res);// form 태그로 사용
		} else if (req.getMethod().equalsIgnoreCase("POST")) {// 데이터를 입력하고 전송하는 과정에서 post를 사용.
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);// 로그인을 하지 않으면 접근하지 못하게 막음 Filter역할.web에서 filter 설정

		User user = (User) req.getSession(false).getAttribute("authUser");// getsession 객체가 생성되어 있을때만 사용. 없으면 null
																			// auth.service안의 user에 속성 메소드가 선언되어있음!
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		int newArticleNo = writeService.write(writeReq);
		req.setAttribute("newArticleNo", newArticleNo);
		req.setAttribute("category", writeReq.getCategory());
		return "/WEB-INF/view/board/boardNewArticleSuccess.jsp";
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(new Writer(user.getId(), user.getMemberName()), req.getParameter("title"),
				req.getParameter("content"), req.getParameter("category"));
	}
}
