package product.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import product.service.ProductContentNotFoundException;
import product.service.ProductData;
import product.service.ProductNotFoundException;
import product.service.ReadProductService;

public class ReadProductHandler implements CommandHandler {

	private ReadProductService readService = new ReadProductService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int productNum = Integer.parseInt(noVal);
		try {
			ProductData productData = readService.getProduct(productNum);
			req.setAttribute("productData", productData);
			return "/WEB-INF/view/readProduct.jsp";
		} catch (ProductNotFoundException | ProductContentNotFoundException e) {
			req.getServletContext().log("no product", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}