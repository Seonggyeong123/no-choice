package board.service;

import board.model.Article;
import board.model.ArticleContent;

public class ArticleData {

	private Article article;
	private ArticleContent content;
	private String category;

	public ArticleData(Article article, ArticleContent content) {
		this.article = article;
		this.content = content;
	}

	public Article getArticle() {
		return article;
	}

	public String getContent() {
		return content.getContent();
	}
	public String getCategoty() {
		return category;
	}

}
