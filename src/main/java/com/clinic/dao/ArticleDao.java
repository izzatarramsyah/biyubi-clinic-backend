package com.clinic.dao;

import java.util.List;
import com.clinic.entity.Article;

public interface ArticleDao {
	List<Article> getListArticle() throws Exception;
	Article getArticle(int id) throws Exception;
	boolean insertArticle(Article article) throws Exception;
}
