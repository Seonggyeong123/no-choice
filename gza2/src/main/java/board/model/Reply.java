package board.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reply {
	 private int comment_no;
	    private int reply_no;
	    private String replyId;
	    private String replyName;
	    private String content;
	    private Date regDate;
	    private String category;

    public Reply(int comment_no, int reply_no, String replyId, String replyName, String content, Date regDate,
                String category) {
        this.comment_no = comment_no;
        this.reply_no = reply_no;
        this.replyId = replyId;
        this.replyName = replyName;
        this.content = content;
        this.regDate = regDate;
        this.category = category;
    }

    public Reply() {
        this.comment_no = 0;
        this.reply_no = 0;
        this.replyId = null;
        this.replyName = null;
        this.content = null;
        this.regDate = null;
        this.category = null;
    }

    public String getCategory() {
        return category;
    }

    public int getComment_no() {
        return comment_no;
    }

    public int getReply_no() {
        return reply_no;
    }

    public String getReplyId() {
        return replyId;
    }

    public String getReplyName() {
        return replyName;
    }

    public String getContent() {
        return content;
    }

    public Date getRegDate() {
        return regDate;
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

    // 추가: 대댓글 내용 설정 메서드
    public void setReplyContent(String replyId, String replyName, String content) {
        this.replyId = replyId;
        this.replyName = replyName;
        this.content = content;
    }
}
