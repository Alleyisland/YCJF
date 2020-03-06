package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Hospital;
import dao.HospitalDao;
import dao.HospitalDaoImpl;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchByDistrictServlet")
public class SearchByDistrictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchByDistrictServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html£»charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String district = request.getParameter("district");
		String result = "";
		PrintWriter out = response.getWriter();
		HospitalDao dao = new HospitalDaoImpl();
		ArrayList<Hospital> hos = dao.searchByLevel(district);
		if (hos != null) 
			result = new Gson().toJson(hos); 
		else result = "none"; 
		out.write(result);
		out.flush();
		out.close();
	}

}
