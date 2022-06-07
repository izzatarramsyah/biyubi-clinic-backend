package com.clinic.service;

import java.util.List;
import java.util.Map;

import com.clinic.entity.Article;

public interface ArticleService {
	
	List < Article > getListArticle() throws Exception;
	
	Article getArticle (int id) throws Exception;
	
}
