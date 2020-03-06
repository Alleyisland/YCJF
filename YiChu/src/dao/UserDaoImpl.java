package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class UserDaoImpl implements UserDao{
	@Override
	public boolean insert(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into users(username,phone) values(?,?)"; // 增，删，改的区别就在这句sql语句
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/YiChu?serverTimezone=UTC", "root",
					"123456");
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} 
		}
		return true;
	}

	@Override
	public boolean check(String phone) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "select * FROM users WHERE phone = ? "; // 增，删，改的区别就在这句sql语句
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/YiChu?serverTimezone=UTC", "root",
					"123456");
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst())
				return false;
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} 
		}
		return true;
	}

	@Override
	public boolean update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update users set username = ?  WHERE phone = ?"; // 增，删，改的区别就在这句sql语句
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://139.196.78.36:3306/yichu?serverTimezone=UTC", "user",
					"Fzj199909!");
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} 
		}
		return true;
	}
}
