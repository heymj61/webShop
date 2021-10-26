package com.kosta.model;

import java.util.List;

public class DeptService {
	DeptDAO dao;
	
	public DeptService(String path) {
		dao = new DeptDAO(path);
	}
	
	public List<DeptDTO> selectAll() {
		return dao.selectAll();
	}
	
	public DeptDTO selectById(int deptId){
		return dao.selectById(deptId);
	}
	
	public List<DeptDTO> selectByName(String dname){
		return dao.selectByName(dname);		
	}
	
	public List<DeptDTO> selectByLocation(int locid) {
		return dao.selectByLocation(locid);
	}

	public int updateService(DeptDTO dept) {
		return dao.updateService(dept);
	}
	
	public int insertDept(DeptDTO dept) {
		return dao.insertDept(dept);
	}
	
	public int deleteDept(int deptid) {
		return dao.deleteDept(deptid);
	}
	
}
