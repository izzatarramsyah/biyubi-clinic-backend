package com.clinic.dao;

import java.util.List;

import com.clinic.entity.Article;
import com.clinic.entity.ArticleComment;

public interface ArticleDao {
	
	List<Article> getListArticle() throws Exception;
	List<ArticleComment> getArticleComment(int id) throws Exception;
	Article getArticle(int id) throws Exception;
	boolean insertArticle(Article article) throws Exception;
	
}
