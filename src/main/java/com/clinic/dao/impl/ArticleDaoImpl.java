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
	
	public static final String ADD_ARTICLE = "INSERT INTO TBL_ARTICLE "
			+ " (TITLE, IMAGE, CONTENT, LIKES, DISLIKES, STATUS, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,0,0,?,?,?) ";
	
	public static final String GET_LIST_ARTICLE = "SELECT ID, TITLE, IMAGE, CONTENT, LIKES, DISLIKES, STATUS, "
			+ " CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ " FROM TBL_ARTICLE ";
	
	public static final String GET_ARTICLE = "SELECT ID, TITLE, IMAGE, CONTENT, LIKES, DISLIKES, STATUS, "
			+ " CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ " FROM TBL_ARTICLE "
			+ " WHERE ID = ? ";
	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean insertArticle(Article article) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_ARTICLE );
		int result = 0;
		try{ 
			result = jdbcTemplate.update(ADD_ARTICLE, 
					new Object[] {article.getTitle(), article.getImage(), article.getContent(), "Active",
							new Timestamp(new Date().getTime()), "SYSTEM"});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public List<Article> getListArticle() throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_LIST_ARTICLE);
		List<Article> result = new ArrayList <Article>();
		try{
			result = jdbcTemplate.query(GET_LIST_ARTICLE, 
					new Object[] {}, new ArticleMapper());
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
