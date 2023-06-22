package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.beans.User;

public class UserDao {
	
	
	public int saveUser(User user) {
		int rows=0;
		Connection conn=DBConnector.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("insert into user values(?,?,?)");
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			rows=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return rows;
	}

}
