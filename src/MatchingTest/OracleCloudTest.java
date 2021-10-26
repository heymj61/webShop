package MatchingTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleCloudTest {

	String DB_URL="jdbc:oracle:thin:@matching_medium?TNS_ADMIN=C:/kosta226/Wallet_matching";
	String DB_USER = "admin";
	String DB_PASSWORD = "Kosta226good";
	public void test() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		System.out.println(conn);
		String sql = "select * from member";
		Statement st = null;
		ResultSet rs = null;
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
		rs.close();
		st.close();
		conn.close();
	}

}
