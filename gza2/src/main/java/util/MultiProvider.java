package util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MultiProvider {

	//여기는 컴퓨터 절대경로 주소입니다!! 여기를 고쳐주세요!!
	private static final String path = "C:\\Users\\USER\\git\\travel_project\\src\\main\\webapp\\imageStorage";

	public static MultipartRequest getMulti(HttpServletRequest req) {

		int size = 1024 * 1024 * 10;
		MultipartRequest multi = null;

		try {
			multi = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy());

			return multi;
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
	}
}
