package board.service;

import java.util.Map;

import board.model.Writer;





public class WriteRequest {

	public String getCategory() {
		return category;
	}

	

	private Writer writer;
	private String title;
	private String content;
	private String category;

	public WriteRequest(Writer writer, String title, String content, String category) {
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
