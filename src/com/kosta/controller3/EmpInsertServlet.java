package com.kosta.controller3;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.model.DeptService;
import com.kosta.model.EmpService;
import com.kosta.model.EmployeeVO;
import com.kosta.model.JobService;
import com.kosta.util.DateUtil;

/**
 * Servlet implementation class EmpInsertServlet
 */
@WebServlet("/emp/empinsert")
public class EmpInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 직원 신규 등록
		String path = getServletContext().getRealPath(".");
		DeptService service = new DeptService(path);
		JobService jservice = new JobService();
		EmpService eservice = new EmpService();
		request.setAttribute("deptlist", service.selectAll());
		request.setAttribute("joblist", jservice.selectAll());
		request.setAttribute("mlist", eservice.selectAllManager());
		
		RequestDispatcher rd = request.getRequestDispatcher("empinsert.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EmployeeVO emp = makeEmp(request);
		EmpService service = new EmpService();
		int result = service.empInsert(emp);
		
		//1. Dispatcher이용...주소창이 변경 없음
		//request.setAttribute("message", result > 0? "입력성공" : "입력실패");
		//RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		//rd.forward(request, response);
		
		//2. redirect...브라우저로 내려가서 다시 서버로 요청. 주소창이 바뀐다.
		response.sendRedirect("emplist?message=insert succcess");
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
