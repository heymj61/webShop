package com.kosta.controller3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.model.EmpService;

/**
 * Servlet implementation class EmpDeleteServlet
 */
@WebServlet("/emp/empdelete")
public class EmpDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int empid = Integer.parseInt(request.getParameter("empid"));
		EmpService service = new EmpService();
		int result = service.empDelete(empid);
		
		String message = result>0? "삭제성공" : "삭제실패";
		
		//2. sendRedirect
		//한글 인코딩 필요
		response.sendRedirect("emplist?message=" + message);
		
		
//		1. Dispatcher
//		request.setAttribute("message", result > 0? "삭제성공" : "삭제실패");
//		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
