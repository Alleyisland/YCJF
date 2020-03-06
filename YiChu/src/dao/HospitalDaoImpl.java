package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Hospital;

public class HospitalDaoImpl implements HospitalDao {

	@Override
	public ArrayList<Hospital> searchByLevel(String district) {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Hospital> result=new ArrayList<>();
		String sql = "select * FROM hospitals WHERE 区 = ? order by field (医院等级,'三级甲等','三级乙等','三级','二级甲等','二级乙等','二级丙等') "
				+ "limit 0,25"; // 增，删，改的区别就在这句sql语句
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://139.196.78.36:3306/yichu?serverTimezone=UTC", "user",
					"Fzj199909!");
			ps = conn.prepareStatement(sql);
			ps.setString(1, district);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst())
				return null;
			while(rs.next())
			{
				Hospital hos=new Hospital();
				hos.setId(rs.getInt("id"));
				hos.setName(rs.getString("医院名称"));
				hos.setLevel(rs.getString("医院等级"));
				hos.setProvince(rs.getString("省"));
				hos.setCity(rs.getString("市"));
				hos.setDistrict(rs.getString("区"));
				hos.setAddress(rs.getString("医院地址"));
				hos.setLon(rs.getDouble("经度"));
				hos.setLat(rs.getDouble("纬度"));
				result.add(hos);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
				return null;
			} 
		}
		return result;
	}

	@Override
	public ArrayList<Hospital> searchByDistance(String position) {
		double lon;
		double lat;
		int p1,p2,p3;
		p1=position.indexOf("(");
		p2=position.indexOf(",");
		p3=position.indexOf(")");
		lon=Double.parseDouble(position.substring(p1+1, p2));
		lat=Double.parseDouble(position.substring(p2+1, p3));
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Hospital> result=new ArrayList<>();
		String sql = "SELECT*," +
				"	(6371 * acos (cos ( radians( ? ) ) * cos( radians( 纬度 ) ) * cos( radians( 经度 ) - radians( ? ) ) + sin ( radians( ? ) ) * sin( radians( 纬度 ) )))" + 
				"	AS distance" + 
				"	FROM hospitals" + 
				"	HAVING distance < 100" + 
				"	ORDER BY distance" + 
				"	LIMIT 25;"; // 增，删，改的区别就在这句sql语句
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://139.196.78.36:3306/yichu?serverTimezone=UTC", "user",
					"Fzj199909!");
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, lat);
			ps.setDouble(2, lon);
			ps.setDouble(3, lat);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst())
				return null;
			while(rs.next())
			{
				Hospital hos=new Hospital();
				hos.setId(rs.getInt("id"));
				hos.setName(rs.getString("医院名称"));
				hos.setLevel(rs.getString("医院等级"));
				hos.setProvince(rs.getString("省"));
				hos.setCity(rs.getString("市"));
				hos.setDistrict(rs.getString("区"));
				hos.setAddress(rs.getString("医院地址"));
				hos.setLon(rs.getDouble("经度"));
				hos.setLat(rs.getDouble("纬度"));
				result.add(hos);
			}
			for(Hospital h : result)
			{
				double distance=(6371 * Math.acos (Math.cos ( Math.toRadians( lat ) ) * Math.cos( Math.toRadians( h.getLat() ) ) * Math.cos( Math.toRadians( lon ) - Math.toRadians( h.getLon() ) ) + Math.sin ( Math.toRadians( lat ) ) * Math.sin( Math.toRadians( h.getLat() ) )));
				h.setDistance(Double.parseDouble(String.format("%.1f", distance)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
				return null;
			} 
		}
		return result;
	}
}
