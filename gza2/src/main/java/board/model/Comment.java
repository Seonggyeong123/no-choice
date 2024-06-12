package board.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Comment {
	private int article_no;
	private int comment_no;
	private String commentId;
	private String commentName;
	private String content;
	private Date regDate;
	private String category;
	private List<Reply> replies;

	public Comment(int article_no, int comment_no, String commentId, String commentName, String content, Date regDate,
			String category) {
		this.article_no = article_no;
		this.comment_no = comment_no;
		this.commentId = commentId;
		this.commentName = commentName;
		this.content = content;
		this.regDate = regDate;
		this.category = category;
	}

	public Comment() {
	}

	public String getCategory() {
		return category;
	}

	public int getArticle_no() {
		return article_no;
	}

	public int getComment_no() {
		return comment_no;
	}

	public String getCommentId() {
		return commentId;
	}

	public String getCommentName() {
		return commentName;
	}

	public String getContent() {
		return content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public String getFormattedRegDate() {
		return formatDate(this.regDate);
	}

	public static String formatDate(Date date) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdfFull = new SimpleDateFormat("yyyyMMdd");

		Date currentDate = new Date();

		if (sdfFull.format(currentDate).equals(sdfFull.format(date))) {
			return sdfTime.format(date);
		} else {
			return sdfDate.format(date);
		}
	}

	// 추가: 댓글 내용 설정 메서드
	public void setCommentContent(String commentId, String commentName, String content) {
		this.commentId = commentId;
		this.commentName = commentName;
		this.content = content;
	}
}
