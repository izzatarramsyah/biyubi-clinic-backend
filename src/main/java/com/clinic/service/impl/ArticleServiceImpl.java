package com.clinic.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.ArticleDao;
import com.clinic.entity.Article;
import com.clinic.entity.ArticleComment;
import com.clinic.service.ArticleService;
import com.clinic.util.Util;

@Service 
public class ArticleServiceImpl implements ArticleService{
	
	private static final Logger LOG = LogManager.getLogger(ArticleServiceImpl.class);

	@Autowired
	ArticleDao articleDao;

	@Override
	public Map<String, Object> insertArticle(Article article) throws Exception {
		LOG.info("param insertArticle::{}:{}:{}:{}:{}:{}:{}", 
				article.getTitle(), article.getImage(), article.getContent(), new Timestamp(new Date().getTime()), "SYSTEM");
		if (!articleDao.insertArticle(article)){
			return Util.setMapResponse("failed", false); 
		}
		return Util.setMapResponse("success", true); 
	} 

	@Override
	public Map<String, Object> getListArticle() throws Exception {
		List<Article> list = articleDao.getListArticle();
		for (Article article : list) {
			List <ArticleComment> articleComment = articleDao.getArticleComment(article.getId());
			article.setArticleComment(articleComment);
			article.setCountArticle(articleComment.size());
		}
		return Util.setMapResponse("success", true, list);
	}

	@Override
	public Map<String, Object> getArticle(int id) throws Exception {
		LOG.info("param getArticle::{}", id);
		Article article = articleDao.getArticle(id);
		List <ArticleComment> articleComment = articleDao.getArticleComment(article.getId());
		article.setArticleComment(articleComment);
		article.setCountArticle(articleComment.size());
		return Util.setMapResponse("success", true, article);
	}

}
