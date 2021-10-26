package com.kosta.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

	
	public static void dbClose(Connection conn, Statement st, ResultSet rs) {
		//자원반납
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection dbConnet(String path) {
		//DB연결
		
		Connection conn = null;
		String url, userid, password;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Properties p = new Properties();
			path = path + "\\WEB-INF\\dbinfo.properties";
			p.load(new FileReader(path));
			url = (String) p.get("url");
			userid = (String) p.get("userid");
			password = (String) p.get("password");
			conn = DriverManager.getConnection(url, userid, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

}
