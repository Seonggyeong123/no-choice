package product.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import auth.service.User;
import mvc.command.CommandHandler;
import product.service.DeleteProductService;
import product.service.DeleteRequest;
import product.service.PermissionDeninedException;
import product.service.ProductData;
import product.service.ProductNotFoundException;
import product.service.ReadProductService;
import util.MultiProvider;

public class DeleteProductHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/deleteForm.jsp";

	private ReadProductService productService = new ReadProductService();
	private DeleteProductService deleteProductService = new DeleteProductService();

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

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);

			ProductData data = productService.getProduct(no);
			User authUser = (User) req.getSession().getAttribute("authUser");

			if (!canDelete(authUser, data)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}

			DeleteRequest delReq = new DeleteRequest(authUser.getId(), no, data.getProduct().getProductTitle(),
					data.getImage().getStoreName(), data.getContent().getProductContent());

			req.setAttribute("delReq", delReq);
			return FORM_VIEW;

		} catch (ProductNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	public boolean canDelete(User authUser, ProductData data) {
		String writerid = data.getProduct().getWriter().getId();
		return authUser.getId().equals(writerid);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");

		MultipartRequest multi = MultiProvider.getMulti(req);
		String storeFileName = multi.getFilesystemName("file");

		String noVal = multi.getParameter("no");
		int no = Integer.parseInt(noVal);

		DeleteRequest delReq = new DeleteRequest(authUser.getId(), no, multi.getParameter("title"), storeFileName,
				multi.getParameter("content"));
		req.setAttribute("delReq", delReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		delReq.validate(errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			deleteProductService.delete(delReq);
			return "/WEB-INF/view/deleteSuccess.jsp";
		} catch (ProductNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeninedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

	}

}