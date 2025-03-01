package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDBByMySQL {
	private static Connection con = null;
	private static ConnectDBByMySQL instance = new ConnectDBByMySQL();
	
	public static ConnectDBByMySQL getInstance() {
		return instance;
	}
	public void connect() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/PTUD";
		String user = "root";
		String pw = "111111";
		con = DriverManager.getConnection(url, user, pw);
	}
	public void disconnect() throws SQLException {
		if(con != null)
			con.close();
		//hihi
	}
	public static Connection getConnection() {
		return con;
	}
}
