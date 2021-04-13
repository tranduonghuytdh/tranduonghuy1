package com.jsptintuc.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jsptintuc.dao.GenericDao;
import com.jsptintuc.mapper.RowMapper;

public class AbstractDao<T> implements GenericDao<T> {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3360/jsp";
			String user = "root";
			String password = "123456";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statment = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statment = connection.prepareStatement(sql);
			setparameter(statment, parameters);
			resultSet = statment.executeQuery();
			while(resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
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

	private void setparameter(PreparedStatement statment, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if(parameter instanceof Long) {
					statment.setLong(index, (Long) parameter);
				} else if(parameter instanceof String) {
					statment.setString(index, (String) parameter);
				} else if(parameter instanceof Integer) {
					statment.setInt(index, (Integer) parameter);
				} else if(parameter instanceof Timestamp) {
					statment.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Long Insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statment = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = getConnection();
			connection.setAutoCommit(false);
			statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setparameter(statment, parameters);
			statment.executeUpdate();
			resultSet = statment.getGeneratedKeys();
			while(resultSet.next()) {
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
	public void Update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statment = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statment = connection.prepareStatement(sql);
			setparameter(statment, parameters);
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
	public void Delete(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statment = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statment = connection.prepareStatement(sql);
			setparameter(statment, parameters);
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
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statment = null;
		ResultSet resultSet = null;
		try {
			int count = 0;
			connection = getConnection();
			statment = connection.prepareStatement(sql);
			setparameter(statment, parameters);
			resultSet = statment.executeQuery();
			while(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			return 0;
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
				return 0;
			}
		}
	}

}
