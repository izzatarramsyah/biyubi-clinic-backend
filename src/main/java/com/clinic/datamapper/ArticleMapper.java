package com.clinic.datamapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.clinic.entity.Article;

public class ArticleMapper implements RowMapper<Article> {
	@Override
	public Article mapRow(ResultSet rs, int row) throws SQLException {
		Article result = new Article();
		result.setId(rs.getInt("ID"));
		result.setTitle(rs.getString("TITLE"));
		result.setImage(rs.getString("IMAGE"));
		result.setContent(rs.getString("CONTENT"));
		result.setLikes(rs.getInt("LIKES"));
		result.setDislikes(rs.getInt("DISLIKES"));
		result.setCreatedDtm(rs.getTimestamp("CREATED_DTM"));
		result.setCreatedBy(rs.getString("CREATED_BY"));
		result.setUpdatedDtm(rs.getTimestamp("LASTUPD_DTM"));
		result.setUpdatedBy(rs.getString("LASTUPD_BY"));
		return result;
	}
}
