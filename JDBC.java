import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.lang.Class;

public class JDBC {
	
	//static String DBLocation = "jdbc:microsoft:sqlserver://HOST:1433;databaseName=MNFLD.dbo";
	
	//DBLocation is local to my computer, theres a way to make remote accessible, ill do later
	static String DBLocation = "jdbc:sqlserver://JUSTIN:1433;databaseName=MNFLD;integratedSecurity=true";
	static Connection connection = null;
	
	//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	
	//connects to SQL Server DB
	public static void openSQLConnection() {
		try {
			connection = DriverManager.getConnection(DBLocation);
	
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			}	
	}
	
	//disconnects from DB
	private static void closeSQLConnection() {

		if (connection != null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		System.out.println("Disconnected");
	}
	/*
	private static Statement getStatement(Connection conn) {

		Statement stat = null;
		try {
		// System.out.println("Connected");
			stat = conn.createStatement();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return stat;
	}*/
	
	public static void getOpenConnectionPorts() { //change name of func later, example one
		try {	
		        if (connection != null) {
		        	System.out.println("Connected");
		        }
		        
		            Statement stat = connection.createStatement();
		            ResultSet result = stat.executeQuery("SELECT *"
		            		+ "FROM MNFLD.dbo.wmgma01_cnct_pt AS cp "
		            		+ "WHERE cp.ACTV_X =0 AND cp.RESV_IN_USE_N < 2"
		            		+ "ORDER BY cp.RESV_IN_USE_N ASC, cp.CNCT_I");
		            
		            while (result.next()) {
		            	int i = 1;
		            	System.out.println(result.getString(i));
		            	i++;
		            }
		        }
	
		        catch(SQLException e) {
		        System.err.println(e.getMessage());
		        }
		}

		
		public static void main(String[] args) {
			openSQLConnection();
			
			getOpenConnectionPorts();
			
			closeSQLConnection();
		}



}
