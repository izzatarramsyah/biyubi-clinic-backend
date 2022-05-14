package com.clinic.service;

import java.util.Map;

import com.clinic.entity.Article;

public interface ArticleService {

	Map<String, Object> getListArticle() throws Exception;
	Map<String, Object> getArticle(int id) throws Exception;
	Map<String, Object> insertArticle(Article article) throws Exception;
	
}
