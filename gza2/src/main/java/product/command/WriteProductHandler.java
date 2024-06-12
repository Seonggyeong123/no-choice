package product.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import auth.service.User;
import mvc.command.CommandHandler;
import product.model.Writer;
import product.service.WriteProductService;
import product.service.WriteRequest;
import util.MultiProvider;

public class WriteProductHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/newProductForm.jsp";
	private WriteProductService writeProductService = new WriteProductService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
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
		req.setAttribute("errors", errors);

		User user = (User) req.getSession(false).getAttribute("authUser");

		MultipartRequest multi = MultiProvider.getMulti(req);
		String originalFileName = multi.getOriginalFileName("file");
		String storeFileName = multi.getFilesystemName("file");

		WriteRequest writeReq = createWriteRequest(user, multi, originalFileName, storeFileName, req);
		
		writeReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		int newProductNum = writeProductService.write(writeReq);
		req.setAttribute("newProductNum", newProductNum);

	return"/WEB-INF/view/newProductSuccess.jsp";

	}

	private WriteRequest createWriteRequest(User user, MultipartRequest multi, String originalFileName, String storeFileName, HttpServletRequest req) {
		return new WriteRequest(new Writer(user.getId(), user.getMemberName()), multi.getParameter("title"),
				multi.getParameter("subtitle"), multi.getParameter("content"), multi.getParameter("type"),
				Integer.parseInt(multi.getParameter("price")), originalFileName, storeFileName,
				Integer.parseInt(multi.getParameter("guests")), multi.getParameter("location"));
	}

}
