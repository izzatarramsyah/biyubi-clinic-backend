package com.clinic.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clinic.config.variable.ApplicationConstant;
import com.clinic.dao.ArticleDao;
import com.clinic.datamapper.ArticleMapper;
import com.clinic.entity.Article;

@Repository
public class ArticleDaoImpl implements ArticleDao{

	private static final Logger LOG = LogManager.getLogger(ArticleDaoImpl.class);
	
	public static final String GET_LIST_ARTICLE = "SELECT ID, TITLE, IMAGE, CONTENT, STATUS, "
			+ " CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ " FROM TBL_ARTICLE "
			+ " WHERE STATUS = 'ACTIVE' ";
	
	public static final String GET_ARTICLE = "SELECT ID, TITLE, IMAGE, CONTENT, STATUS, "
			+ " CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ " FROM TBL_ARTICLE "
			+ " WHERE ID = ? ";
	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Article> getListArticle() throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_LIST_ARTICLE);
		List<Article> result = new ArrayList <Article>();
		try{
			result = jdbcTemplate.query(GET_LIST_ARTICLE, new Object[] {}, new ArticleMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}

	@Override
	public Article getArticle(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_ARTICLE);
		List<Article> result = new ArrayList <Article>();
		try{
			result = jdbcTemplate.query(GET_ARTICLE, new Object[] { id }, new ArticleMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() == 0 ? null : result.get(0);
	}

}
