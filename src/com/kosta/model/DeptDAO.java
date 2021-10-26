package com.kosta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kosta.util.DBUtil;

public class DeptDAO {

	static final String SQL_SELECT_ALL = "select * from departments";
	static final String SQL_SELECT_BYID = "select * from departments where department_name = ?";
	static final String SQL_SELECT_BYNAME = "select * from departments where department_name = ?";
	static final String SQL_SELECT_BYLOC = "select * from departments where location_id = ?";

	String path;

	public DeptDAO(String path) {
		this.path = path;
	}

	public List<DeptDTO> selectAll() {
		List<DeptDTO> deplist = new ArrayList<DeptDTO>();
		String sql = "select * from departments order by 1";

		Connection con = DBUtil.dbConnet(path);
		Statement st = null;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				int department_id = rs.getInt(1);
				String department_name = rs.getString(2);
				int manager_id = rs.getInt(3);
				int location_id = rs.getInt(4);

				DeptDTO d = new DeptDTO(department_id, department_name, manager_id, location_id);
				deplist.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, st, rs);
		}
		return deplist;
	}

	// pk니까 한건!
	public DeptDTO selectById(int deptId) {

		DeptDTO dep = null;
		String sql = "select * from departments where department_id = ?";
		Connection con = DBUtil.dbConnet(path);
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement(sql);
			st.setInt(1, deptId);
			rs = st.executeQuery();

			while (rs.next()) {
				int department_id = rs.getInt(1);
				String department_name = rs.getString(2);
				int manager_id = rs.getInt(3);
				int location_id = rs.getInt(4);
				dep = new DeptDTO(department_id, department_name, manager_id, location_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, st, rs);
		}
		return dep;

	}

	public List<DeptDTO> selectByName(String dname) {

		List<DeptDTO> deplist = new ArrayList<DeptDTO>();
		String sql = "select * from departments where department_name = ?";
		Connection con = DBUtil.dbConnet(path);
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement(sql);
			st.setString(1, dname);
			rs = st.executeQuery();

			while (rs.next()) {
				int department_id = rs.getInt(1);
				String department_name = rs.getString(2);
				int manager_id = rs.getInt(3);
				int location_id = rs.getInt(4);
				DeptDTO d = new DeptDTO(department_id, department_name, manager_id, location_id);
				deplist.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, st, rs);
		}
		return deplist;

	}

	public List<DeptDTO> selectByLocation(int locid) {

		List<DeptDTO> deplist = new ArrayList<DeptDTO>();
		String sql = "select * from departments where location_id = ?";
		Connection con = DBUtil.dbConnet(path);
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement(sql);
			st.setInt(1, locid);
			rs = st.executeQuery();

			while (rs.next()) {
				int department_id = rs.getInt(1);
				String department_name = rs.getString(2);
				int manager_id = rs.getInt(3);
				int location_id = rs.getInt(4);
				DeptDTO d = new DeptDTO(department_id, department_name, manager_id, location_id);
				deplist.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, st, rs);
		}
		return deplist;

	}

	public int updateService(DeptDTO dept) {
		int result = 0; // update 건수
		String sql = "update departments" + " set department_name = ?" + ", manager_id = ?" + ", location_id = ?"
				+ " where department_id = ?";

		Connection con = DBUtil.dbConnet(path);
		// 여러개를 할 때는 prepared statement가 안됨
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, dept.getDepartment_name());

			if (dept.getManager_id() == 0) {
				//타입은 Integer인데, null이다.
				st.setNull(2, java.sql.Types.INTEGER);
			} else {
				st.setInt(2, dept.getManager_id());
			}
			st.setInt(3, dept.getLocation_id());
			st.setInt(4, dept.getDepartment_id());

			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, st, null);
		}
		return result;
	}

	public int insertDept(DeptDTO dept) {
		int result = 0; // update 건수
		String sql = "insert into departments" + " values (?,?,?,?)";

		Connection con = DBUtil.dbConnet(path);
		// 여러개를 할 때는 prepared statement가 안됨
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, dept.getDepartment_id());
			st.setString(2, dept.getDepartment_name());
			st.setInt(3, dept.getManager_id());
			st.setInt(4, dept.getLocation_id());

			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, st, null);
		}
		return result;
	}

	public int deleteDept(int deptid) {
		int result = 0; // update 건수
		String sql = "delete departments where department_id = ?";

		Connection con = DBUtil.dbConnet(path);
		// 여러개를 할 때는 prepared statement가 안됨
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, deptid);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, st, null);
		}
		return result;
	}

}
