import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.lang.Class;

public class JDBC {

	// DBLocation is local to my computer, theres a way to make remote
	// accessible, ill do later
	static String DBLocation = "jdbc:sqlserver://JUSTIN\\SQLEXPRESS,1433:1433;integratedSecurity=true;";

	// static String DBLocation =
	// "jdbc:sqlserver://JUSTIN:1433;databaseName=MNFLD;integratedSecurity=true"; local, working
	static Connection connection = null;
	
	static String username = "jgreen";
	static String password = "ejgallo";

	// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

	// connects to SQL Server DB
	public static void openSQLConnection() {
		try {
			connection = DriverManager.getConnection(DBLocation, username, password);

			if (connection != null) {
				System.out.println("Connected");
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// disconnects from DB
	private static void closeSQLConnection() {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			System.out.println("Disconnected");
		}

	}

	public static void getOpenConnectionPorts() { // change name of func later,
													// example one
		try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT *" + "FROM MNFLD.dbo.wmgma01_cnct_pt AS cp "
					+ "WHERE cp.ACTV_X =0 AND cp.RESV_IN_USE_N < 2" + "ORDER BY cp.RESV_IN_USE_N ASC, cp.CNCT_I");

			while (result.next()) {
				int i = 1;
				System.out.println(result.getString(i));
				i++;
			}
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		openSQLConnection();

		getOpenConnectionPorts();

		closeSQLConnection();
	}

}
