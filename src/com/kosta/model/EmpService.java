package com.kosta.model;

import java.util.List;

//사용자요청 --> Controller가 요청을 하면 --> Service --> DAO --> DB
//Service: 하나의 업무 단위 (이체하기(출금, 입금), 상품 입고하기(입고 insert, 재고 update))
public class EmpService {

	EmpDAO dao = new EmpDAO();
	
	//dao에 가지 않을 수도 있다.
	public void test() {
		System.out.println("db에 가지 않아도 되는 Business logic 작성");
	}
	
	//dao에 여러 작업을 호출할 수도 있다.
	public void 이체하기() {	//두개를 합쳐 하나의 작업!
		dao.withdraw();
		dao.deposit();
	}
	
	//dao에 가서 수행하고, 받은 값을 Controller에 보낸다.
	public List<EmployeeVO> selectAll(){
		return dao.selectAll();
	}
	
	public List<EmployeeVO> selectById(int deptId){
		return dao.selectById(deptId);
	}
	
	public List<EmployeeVO> selectBySalary(int sal){
		return dao.selectBySalary(sal);
	}
	
	public List<EmployeeVO> selectByJob(String jobId){
		return dao.selectByJob(jobId);
	}
	
	public List<EmployeeVO> selectByConditon(int deptId, String jobId, int sal, String hdate){
		return dao.selectByConditon(deptId, jobId, deptId, hdate);
	}
	
	public int empInsert(EmployeeVO emp) {
		return dao.empInsert(emp);
	}

	public int empUpdate(EmployeeVO emp) {
		return dao.empUpdate(emp);
	}

	public int empUpdate2(EmployeeVO emp) {
		return dao.empUpdate2(emp);
	}
	
	public int empDelete(int empid) {
		return dao.empDelete(empid);
	}

	public EmployeeVO selectByEmpid(int empid) {
		return dao.selectByEmpid(empid);
		
	}
	
	public List<EmployeeVO> selectAllManager() {
		return dao.selectAllManager();
	}
}
