package com.jsptintuc.dao;

import java.util.List;

import com.jsptintuc.mapper.RowMapper;

public interface GenericDao<T> {

	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
	
	Long Insert(String sql, Object... parameters);
	
	void Update(String sql, Object... parameters);
	
	void Delete(String sql, Object... parameters);
	
	int count(String sql, Object... parameters);
	
}
