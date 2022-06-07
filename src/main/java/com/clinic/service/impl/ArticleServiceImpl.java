package com.clinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.ArticleDao;
import com.clinic.entity.Article;
import com.clinic.service.ArticleService;

@Service 
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleDao articleDao;

	@Override
	public List < Article > getListArticle() throws Exception {
		return articleDao.getListArticle();
	}

	@Override
	public Article getArticle(int id) throws Exception {
		return articleDao.getArticle(id);
	}

}
