package com.clinic.entity;

import java.util.List;

public class Article {

	private int id;
	private String title;
	private String image;
	private String content;
	private int likes;
	private int dislikes;
	private int countArticle;
	private List<ArticleComment> articleComment;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public List<ArticleComment> getArticleComment() {
		return articleComment;
	}
	public void setArticleComment(List<ArticleComment> articleComment) {
		this.articleComment = articleComment;
	}
	public int getCountArticle() {
		return countArticle;
	}
	public void setCountArticle(int countArticle) {
		this.countArticle = countArticle;
	}
		
}
