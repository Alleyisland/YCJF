package test;

import java.util.Random;

import bean.User;
import dao.UserDao;
import dao.UserDaoImpl;

public class UserTest {
	public static void main(String args[]) {
		User user = new User();
		user.setUsername(getRandomString(10));
		user.setPhone("17321050252");
		UserDao dao = new UserDaoImpl();
		if (dao.insert(user) == true)
			System.out.println("insert success");
		else System.out.println("insert fail");
		if (dao.check("17321050252") == true)
			System.out.println("check success");
		else System.out.println("check fail");
		user.setUsername(getRandomString(10));
		if (dao.update(user) == true)
			System.out.println("update success");
		else System.out.println("update fail");
	}
	
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }   
}
