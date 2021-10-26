package com.kosta.controller3;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.model.EmpService;
import com.kosta.model.EmployeeVO;
import com.kosta.util.DateUtil;

/**
 * Servlet implementation class EmpDetailServlet
 */
@WebServlet("/emp/empdetail")
public class EmpDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//직원정보 상세보기
		int empid = Integer.parseInt(request.getParameter("empid"));
		EmpService service = new EmpService();
		request.setAttribute("emp", service.selectByEmpid(empid));
		RequestDispatcher rd = request.getRequestDispatcher("empdetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EmployeeVO emp = makeEmp(request);
		EmpService service = new EmpService();
		int result = service.empUpdate(emp);
//		request.setAttribute("message", result > 0? "수정성공" : "수정실패");
//		response.addHeader("refresh", "1 url=emplist");
//		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
//		rd.forward(request, response);
		response.sendRedirect("emplist");	//다시 요청한다. 요청 재지정하기
		//브라우저로 내려가서 다시 서버에 요청한다. 주소창이 바뀐다.
		//서블릿으로 요청 보내기...jsp에 forward
	}

	private EmployeeVO makeEmp(HttpServletRequest request) {
		int empid = Integer.parseInt(request.getParameter("employee_id"));
		String last_name = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		String job_id = request.getParameter("job_id");
		String phone_number = request.getParameter("phone_number");
		String email = request.getParameter("email");
		int manager_id = Integer.parseInt(request.getParameter("manager_id"));
		double commission_pct = Double.parseDouble(request.getParameter("commission_pct"));
		int salary = Integer.parseInt(request.getParameter("salary"));
		Date hire_date = DateUtil.convertStringtoDate(request.getParameter("hire_date"));
		
		EmployeeVO emp = new EmployeeVO();
		
		emp.setEmployee_id(empid);
		emp.setFirst_name(first_name);
		emp.setLast_name(last_name);
		emp.setDepartment_id(department_id);
		emp.setJob_id(job_id);
		emp.setPhone_number(phone_number);
		emp.setEmail(email);
		emp.setManager_id(manager_id);
		emp.setCommission_pct(commission_pct);
		emp.setSalary(salary);
		emp.setHire_date(hire_date);
		
		return emp;
	}

}
