package com.kosta.controller3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.model.EmpService;

/**
 * Servlet implementation class EmpListServlet
 */
@WebServlet("/emp/emplist")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.request
		String data1 = (String)request.getAttribute("data1");
		//2.session
		HttpSession session = request.getSession();
		String data2 = (String)session.getAttribute("data2");
		//3.application
		ServletContext application = getServletContext();
		String data3 = (String)application.getAttribute("data3");
		
		System.out.println("request" + data1);
		System.out.println("session" + data2);
		System.out.println("application" + data3);
		
		EmpService service = new EmpService();
		request.setAttribute("emplist", service.selectAll());
		//EL문법 ==> ${emplist}
		//ScriptLet문법 ==> (List<EmployeeVO)request.getAttribute("emplist")
		//JSTL문법 ==> tag이용 <forEach></forEach>
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("emplist.jsp");
		rd.forward(request, response);
	}
	
	/*
	 * 바인딩: 서블릿 관련 객체에 데이터를 저장
	 * application(웹프로그래당)....servletContext 객체...서버가 살아있는 동안 유효하다.
	 * session(Browser당)....httpSession객체...Browser가 살아있는 동안 유효하다.
	 * request(요청문서)....HttpServletRequest...request가 살아있는 동안 유효하다.
	 */

}
