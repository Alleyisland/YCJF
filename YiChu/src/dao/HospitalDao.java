package dao;

import java.util.ArrayList;
import bean.Hospital;

public interface HospitalDao {
public ArrayList<Hospital> searchByLevel(String district);
public ArrayList<Hospital> searchByDistance(String position);

}
