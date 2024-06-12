package board.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	private Integer number;
	private Writer writer;
	private String title;
	private Date regDate;
	private Date modifiedDate;
	private int readCount;
	private String category;

	// 기존 생성자 유지
	public Article(Integer number, String title, Writer writer, Date regDate, String category) {
		this.number = number;
		this.title = title;
		this.writer = writer;
		this.regDate = regDate;
		this.category = category;
	}

	// 모든 필드를 포함한 새로운 생성자 추가
	public Article(Integer number, Writer writer, String title, Date regDate, Date modifiedDate, int readCount,
			String category) {
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
		this.category = category;
	}

	public Integer getNumber() {
		return number;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public String getCategory() {
		return category;
	}

	public String getFormattedRegDate() {
		return formatDate(this.regDate);
	}

	public Article() {
		// TODO Auto-generated constructor stub
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

	public Article(int number, String title, Writer writer, Timestamp regDate, String category) {
		this.number = number;
		this.title = title;
		this.writer = writer;
		this.regDate = regDate;
		this.category = category;
		this.readCount = 0; // 기본 조회수는 0으로 설정
	}
}