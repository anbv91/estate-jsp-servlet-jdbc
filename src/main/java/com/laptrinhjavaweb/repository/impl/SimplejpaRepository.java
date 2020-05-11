package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.repository.JpaRepository;

public class SimplejpaRepository<T> implements JpaRepository<T> {

	private Class<T> zClass;

	@SuppressWarnings("unchecked")
	public SimplejpaRepository() {
		zClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estate32020module1part1",
					"root", "1234");
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public List<T> findById(long id) {
		return null;

	}

	public List<T> findAll() {
		List<T> results = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = getConnection().createStatement();

			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}

			String sql = "select * from " + tableName;
			rs = stmt.executeQuery(sql);

			if (zClass.isAnnotationPresent(Entity.class)) {

				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				Field[] fields = zClass.getDeclaredFields();
				while (rs.next()) {
					T object = zClass.newInstance();

					for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						String columnName = resultSetMetaData.getColumnName(i + 1);
						Object columnValue = rs.getObject(i + 1);
						for (Field field : fields) {

							if (field.isAnnotationPresent(Column.class)) {
								Column column = field.getAnnotation(Column.class);
								if (column.name().equals(columnName) && columnValue != null) {
									BeanUtils.setProperty(object, field.getName(), columnValue);

									break;
								}
							}
						}

					}
					results.add(object);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return results;
	}

	@Override
	public void insert(Object object) {
		String sql = buildSqlInsert();
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(sql);
			Class<?> aClass = object.getClass();
			int index = 1;
			for (Field field : aClass.getDeclaredFields()) {
				field.setAccessible(true);
				stmt.setObject(index, field.get(object));
				index++;
			}
			
			stmt.executeUpdate();
			connection.commit();
			System.out.println("insert success");

		} catch (Exception e) {
			System.out.println(e);
			try {
				connection.rollback();
			} catch (Exception e1) {
				System.out.println(e1);
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private String buildSqlInsert() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}

		StringBuffer fields = new StringBuffer("");
		StringBuffer params = new StringBuffer("");

		for (Field field : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}
		}
		String sql = "INSERT INTO " + tableName + "(" + fields.toString() + ") VALUES(" + params.toString() + ")";
		return sql;
	}

	@Override
	public void update(Object object) {
		String sql = buildSqlUpdate();
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(sql);
			Class<?> aClass = object.getClass();
			int index = 1;
			for (Field field : aClass.getDeclaredFields()) {
				field.setAccessible(true);
				stmt.setObject(index, field.get(object));
				index++;
			}
			stmt.executeUpdate();
			connection.commit();
			System.out.println("update success");

		} catch (Exception e) {
			System.out.println(e);
			try {
				connection.rollback();
			} catch (Exception e1) {
				System.out.println(e1);
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private String buildSqlUpdate() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuffer fields = new StringBuffer("");
		StringBuffer params = new StringBuffer("");
		StringBuffer paramId = new StringBuffer("");

		for (Field field : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				params.append(",");
				paramId.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
				paramId.append("?");
			}
		}
		String sql = "UPDATE " + tableName + " SET " + fields.toString() + "= " + params.toString() + " WHERE id= "
				+ paramId.toString() + "";

		return sql;
	}

	@Override
	public void delete(Object object) {
		String sql = buildSqlDelete();
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(sql);

			stmt.executeUpdate();
			connection.commit();
			System.out.println("delete success");

		} catch (Exception e) {
			System.out.println(e);
			try {
				connection.rollback();
			} catch (Exception e1) {
				System.out.println(e1);
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private String buildSqlDelete() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuffer fields= new StringBuffer("");
		StringBuffer params = new StringBuffer("");
		
		for (Field field : zClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
				
			}
		}
	
		String sql = "DELETE FORM " + tableName + "  WHERE "+ fields.toString()+" = "+params.toString()+" ";

		return sql;
	}

}
