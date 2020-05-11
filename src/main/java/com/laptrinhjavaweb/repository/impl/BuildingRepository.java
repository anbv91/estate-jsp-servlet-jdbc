package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends SimplejpaRepository<BuildingEntity> implements IBuildingRepository {
	@Override
	public void update(BuildingEntity buildingEntity) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = this.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement("update building set name=? where id=?");

			stmt.setString(1, buildingEntity.getName());
			stmt.setLong(2, buildingEntity.getId());

			stmt.executeUpdate();
			connection.commit();
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


	@Override
	public void delete(Long id) {
		StringBuffer sql= new StringBuffer("DELETE FORM building WHERE id=?");
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = this.getConnection();
			connection.setAutoCommit(false);
			stmt= connection.prepareStatement(sql.toString());
			stmt.setLong(1, id);
			stmt.executeUpdate();
			connection.commit();
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
	
}
