package test;

import java.util.ArrayList;

import com.google.gson.Gson;

import bean.Hospital;
import dao.HospitalDao;
import dao.HospitalDaoImpl;

public class HospitalTest2 {
	public static void main(String args[]) {
		HospitalDao dao = new HospitalDaoImpl();
		ArrayList<Hospital> hos = dao.searchByDistance("(121,30)");
		if (hos != null) {
			System.out.println("success");
			String result = new Gson().toJson(hos); 
			System.out.println(result);
		} else
			System.out.println("fail");
	}
}
