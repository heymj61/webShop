package com.kosta.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kosta.util.DBUtil2;

public class JobDAO {

	public List<JobVO> selectAll() {
		// 지역변수는 초기화 하지 않으면 사용할 수 없다.
		List<JobVO> joblist = new ArrayList<JobVO>();
		String sql = "select job_id, job_title from jobs order by 1";

		Connection conn = DBUtil2.dbConnet();
		;
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement(); // conn은 길을 알고 있음. 나 통로 좀 만들어줘
			rs = st.executeQuery(sql);

			while (rs.next()) {
				joblist.add(new JobVO(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil2.dbClose(conn, st, rs);
		}

		return joblist;
	}

}
