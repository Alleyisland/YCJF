package dao;

import java.sql.SQLException;

import bean.User;

public interface UserDao {
	public boolean insert(User user);
	
	public boolean check(String phone);
	
	public boolean update(User user);
}
