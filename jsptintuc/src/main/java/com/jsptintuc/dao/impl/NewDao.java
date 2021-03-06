package com.jsptintuc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.jsptintuc.dao.INewDao;
import com.jsptintuc.mapper.NewMapper;
import com.jsptintuc.model.NewModel;

public class NewDao extends AbstractDao<NewModel> implements INewDao {

	@Override
	public List<NewModel> findAll(Integer offset, Integer limit, String sortName, String sortBy) {
//		String sql = "Select * From news LIMIT ?, ?";
		
		StringBuilder sql = new StringBuilder("Select * From news");
		if(sortName != null && sortBy != null) {
			sql.append(" Order By " + sortName + " " + sortBy + "");
		}
		if(offset != null && limit != null) {
			sql.append(" Limit " + offset + ", " + limit + "");
			return query(sql.toString(), new NewMapper(), offset, limit);
		}
		return query(sql.toString(), new NewMapper());
		
//		return query(sql, new NewMapper(), offset, limit);
	}
	
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "Select * From news Where categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	// Thuần
	@Override
	public Long save(NewModel insertNEW) {
		ResultSet resultSet = null;
		Long id = null;
		Connection connection = null;
		PreparedStatement statment = null;
		try {
			String sql = "Insert Into news(title, content, categoryid) Values(?,?,?)";
			connection = getConnection();
			connection.setAutoCommit(false);
			statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statment.setString(1, insertNEW.getTitle());
			statment.setString(2, insertNEW.getContent());
			statment.setLong(3, insertNEW.getCategoryId());
			statment.executeUpdate();
			resultSet = statment.getGeneratedKeys();
			if(resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statment != null) {
					statment.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				return null;
			}
		}
	}

	@Override
	public void update(NewModel updateNEW) {
		Connection connection = null;
		PreparedStatement statment = null;
		try {
			String sql = "Update news Set title = ?, content = ? Where categoryid = ?";
			connection = getConnection();
			connection.setAutoCommit(false);
			statment = connection.prepareStatement(sql);
			statment.setString(1, updateNEW.getTitle());
			statment.setString(2, updateNEW.getContent());
			statment.setLong(3, updateNEW.getCategoryId());
			statment.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statment != null) {
					statment.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void delete(NewModel deleteNEW) {
		Connection connection = null;
		PreparedStatement statment = null;
		try {
			String sql = "Delete From news Where id = ?";
			connection = getConnection();
			connection.setAutoCommit(false);
			statment = connection.prepareStatement(sql);
			statment.setLong(1, deleteNEW.getId());
			statment.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statment != null) {
					statment.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	// Hàm chung
	@Override
	public Long insert1(NewModel insertNew1) {
		String sql = "Insert Into news(title, thumbnail, shortdescription, content, categoryid, createddate, createdby) Values(?,?,?,?,?,?,?)";
		return Insert(sql, insertNew1.getTitle(), insertNew1.getThumbnail(), insertNew1.getShortDescription(), insertNew1.getContent(), insertNew1.getCategoryId(), insertNew1.getCreatedDate(), insertNew1.getCreatedBy());
	}

	@Override
	public void update1(NewModel updateNew1) {
		String sql = "Update news Set title = ?, thumbnail = ?, shortdescription = ?, content = ?, categoryid = ?, createddate =?, createdby =? Where id = ?";
		this.Update(sql, updateNew1.getTitle(), updateNew1.getThumbnail(), updateNew1.getShortDescription(), updateNew1.getContent(), updateNew1.getCategoryId(), updateNew1.getCreatedDate(), updateNew1.getCreatedBy(), updateNew1.getId());
	}
	
	@Override
	public void delete1(long id) {
		String sql = "Delete From news Where id = ?";
		Update(sql, id);
	}

	// API
	@Override
	public NewModel findOne(long id) {
		String sql = "Select * From news Where id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
		// giá trị rỗng thì check isEnpty
	}

	@Override
	public void delete(long id) {
		String sql = "Delete From news Where id = ?";
		Update(sql, id);
	}

	@Override
	public int getTotalItem() {
		String sql = "Select count(*) From news";
		return count(sql);
	}

}
