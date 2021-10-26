package com.kosta.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kosta.util.DBUtil2;
import com.kosta.util.DateUtil;

//DAO(Data Access Object)
//Repository
public class EmpDAO {
	public int empDelete(int empid) {
		int result = 0; // delete 건수
		String sql = "delete from employees where employee_id = ?";

		Connection con = DBUtil2.dbConnet();
		// 여러개를 할 때는 prepared statement가 안됨
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, empid);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(con, st, null);
		}

		return result;
	}

	public int empUpdate2(EmployeeVO emp) {
		int result = 0; // update 건수
		String sql = "update employees set employee_id = " + emp.getEmployee_id();
		String whereQL = " where employee_id = " + emp.getEmployee_id();
		String fnameSQL = " ", lnameSQL = " ", emailSQL = " ", phoneSQL = " ", jobSQL = " ", salSQL = " ",
				commSQL = " ";

		if (emp.getFirst_name() != null)
			fnameSQL = ", first_name= \'" + emp.getFirst_name() + "\'";
		if (emp.getLast_name() != null)
			lnameSQL = ",last_name = \'" + emp.getLast_name() + "\'";
		if (emp.getEmail() != null)
			emailSQL = ", email = \'" + emp.getEmail() + "\'";
		if (emp.getPhone_number() != null)
			phoneSQL = ", phone_number = \'" + emp.getPhone_number() + "\'";
		if (emp.getJob_id() != null)
			jobSQL = ",job_id= \'" + emp.getJob_id() + "\'";
		if (emp.getSalary() >= 0)
			salSQL = ", salary=" + emp.getSalary();
		if (emp.getCommission_pct() >= 0)
			commSQL = ", comm" + emp.getCommission_pct();

		sql += fnameSQL + lnameSQL + emailSQL + phoneSQL + jobSQL + salSQL + commSQL + whereQL;
		Connection con = DBUtil2.dbConnet();
		// 여러개를 할 때는 prepared statement가 안됨
		Statement st = null;
		try {
			st = con.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(con, st, null);
		}

		return result;
	}

	public int empUpdate(EmployeeVO emp) {
		int result = 0; // update 건수
		String sql = "update employees" + " set FIRST_NAME = ?" + ", LAST_NAME = ?" + ", EMAIL = ?"
				+ ", PHONE_NUMBER = ?" + ", HIRE_DATE = ?" + ", JOB_ID = ?" + ", SALARY = ?" + ", COMMISSION_PCT = ?"
				+ ", MANAGER_ID = ?" + ", DEPARTMENT_ID = ?" + " where EMPLOYEE_ID = ?";

		Connection con = DBUtil2.dbConnet();
		// 여러개를 할 때는 prepared statement가 안됨
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setInt(11, emp.getEmployee_id());
			st.setString(1, emp.getFirst_name());
			st.setString(2, emp.getLast_name());
			st.setString(3, emp.getEmail());
			st.setString(4, emp.getPhone_number());
			st.setDate(5, emp.getHire_date());
			st.setString(6, emp.getJob_id());
			st.setInt(7, emp.getSalary());
			st.setDouble(8, emp.getCommission_pct());
			st.setInt(9, emp.getManager_id());
			st.setInt(10, emp.getDepartment_id());

			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(con, st, null);
		}

		return result;
	}

	// DML (Data Manipulation Language) - insert, update, delete
	public int empInsert(EmployeeVO emp) {
		int result = 0; // insert 건수
		String sql = "insert into employees values (?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = DBUtil2.dbConnet();
		// 여러개를 할 때는 prepared statement가 안됨
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, emp.getEmployee_id());
			st.setString(2, emp.getFirst_name());
			st.setString(3, emp.getLast_name());
			st.setString(4, emp.getEmail());
			st.setString(5, emp.getPhone_number());
			st.setDate(6, emp.getHire_date());
			st.setString(7, emp.getJob_id());
			st.setInt(8, emp.getSalary());
			st.setDouble(9, emp.getCommission_pct());
			st.setInt(10, emp.getManager_id());
			st.setInt(11, emp.getDepartment_id());

			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(con, st, null);
		}

		return result;
	}

	// 이체하기 서비스: (계좌에서 출금하기 기능, 계좌에서 입금하기 기능)
	public void withdraw() {
		System.out.println("출금하다");
	}

	public void deposit() {
		System.out.println("입금한다");
	}

	// 모두 조회
	public List<EmployeeVO> selectAll() {
		// 지역변수는 초기화 하지 않으면 사용할 수 없다.
		List<EmployeeVO> emplist = new ArrayList<EmployeeVO>();
		String sql = "select * from employees order by 1";

		Connection conn = DBUtil2.dbConnet();
		;
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement(); // conn은 길을 알고 있음. 나 통로 좀 만들어줘
			rs = st.executeQuery(sql);

			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(conn, st, rs);
		}

		return emplist;
	}

	// 특정 부의 직원들 조회
	public List<EmployeeVO> selectById(int deptId) {
		// 지역변수는 초기화 하지 않으면 사용할 수 없다.
		List<EmployeeVO> emplist = new ArrayList<EmployeeVO>();
		String sql = "select * from employees where department_id=?"; // casting 안해도 되나봄

		PreparedStatement st = null; // ?를 활용하면 PreparedStatement!
		Connection conn = DBUtil2.dbConnet();
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(sql); // sql문을 준비한다.
			st.setInt(1, deptId); // 첫번째(1) 물음표에 부서번호를(deptId)를 넣어라
			rs = st.executeQuery(); // 실행만 한다. (sql 넣지 않음)

			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(conn, st, rs);
		}

		return emplist;
	}

	public List<EmployeeVO> selectBySalary(int sal) {
		// 지역변수는 초기화 하지 않으면 사용할 수 없다.
		List<EmployeeVO> emplist = new ArrayList<EmployeeVO>();
		String sql = "select * from employees where salary >= ?"; // casting 안해도 되나봄

		Connection conn = DBUtil2.dbConnet();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, sal);
			rs = st.executeQuery();

			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(conn, st, rs);
		}

		return emplist;
	}

	public List<EmployeeVO> selectByJob(String jobId) {
		List<EmployeeVO> emplist = new ArrayList<EmployeeVO>();
		String sql = "select * from employees where job_id = ?"; // 싱글쿼트!!!

		Connection conn = DBUtil2.dbConnet();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(sql);
			st.setString(1, jobId);
			rs = st.executeQuery();

			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(conn, st, rs);
		}

		return emplist;
	}

	public List<EmployeeVO> selectByConditon(int deptId, String jobId, int sal, String hdate) {
		List<EmployeeVO> emplist = new ArrayList<EmployeeVO>();
		String sql = "select * from employees" + " where department_id = ?" + " and job_id = ?" + " and salary >= ?"
				+ " and hire_date >= ?"; // 싱글쿼트!!!

		Connection conn = DBUtil2.dbConnet();
		;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, deptId);
			st.setString(2, jobId);
			st.setInt(3, sal);
			st.setDate(4, DateUtil.convertStringtoDate(hdate));
			rs = st.executeQuery();

			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(conn, st, rs);
		}

		return emplist;
	}

	
	public EmployeeVO selectByEmpid(int empid) {
		// 지역변수는 초기화 하지 않으면 사용할 수 없다.
		EmployeeVO emp = null;
		String sql = "select * from employees where employee_id = ?"; // casting 안해도 되나봄
		
		PreparedStatement st = null; // ?를 활용하면 PreparedStatement!
		Connection conn = DBUtil2.dbConnet();
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(sql); // sql문을 준비한다.
			st.setInt(1, empid); // 첫번째(1) 물음표에 부서번호를(deptId)를 넣어라
			rs = st.executeQuery(); // 실행만 한다. (sql 넣지 않음)

			while (rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(conn, st, rs);
		}

		return emp;
	}
	
	private EmployeeVO makeEmp(ResultSet rs) throws SQLException {
		EmployeeVO emp = new EmployeeVO();

		emp.setCommission_pct(rs.getDouble("commission_pct"));
		emp.setDepartment_id(rs.getInt("department_id"));
		emp.setEmail(rs.getString("email"));
		emp.setEmployee_id(rs.getInt("employee_id"));
		emp.setFirst_name(rs.getString("first_name"));
		emp.setHire_date(rs.getDate("hire_date"));
		emp.setJob_id(rs.getString("job_id"));
		emp.setLast_name(rs.getString("last_name"));
		emp.setManager_id(rs.getInt("manager_id"));
		emp.setPhone_number(rs.getString("phone_number"));
		emp.setSalary(rs.getInt("salary"));

		return emp;
	}

	
	public List<EmployeeVO> selectAllManager() {
		// 지역변수는 초기화 하지 않으면 사용할 수 없다.
		List<EmployeeVO> emplist = new ArrayList<EmployeeVO>();
		String sql = "select distinct emp.manager_id, man.first_name" 
				+ " from employees emp join employees man"
				+ " on (emp.manager_id = man.employee_id)"
				+ " where emp.manager_id is not null"
				+ " order by 1";

		Connection conn = DBUtil2.dbConnet();
		;
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement(); // conn은 길을 알고 있음. 나 통로 좀 만들어줘
			rs = st.executeQuery(sql);

			while (rs.next()) {
				EmployeeVO emp = new EmployeeVO();
				emp.setEmployee_id(rs.getInt(1));
				emp.setFirst_name(rs.getString(2));
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(conn, st, rs);
		}

		return emplist;
	}
	
}
