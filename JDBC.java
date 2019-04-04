import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.lang.Class;

public class JDBC {
	
	//static String DBLocation = "jdbc:microsoft:sqlserver://HOST:1433;databaseName=MNFLD.dbo";
	static String DBLocation = "jdbc:sqlserver://JUSTIN\\SQLEXPRESS:1433;databaseName=MNFLD;integratedSecurity=true;";
	static Connection connection = null;
	
	//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	public static void SelectQuery() { //change name of func later, example one
		try {	
			
		        connection = DriverManager.getConnection(DBLocation, "jgreen", "ejgallo");
		        if (connection != null) {
		        	System.out.println("Connected");
		        }
		            Statement stat = connection.createStatement();
		            ResultSet result = stat.executeQuery("SELECT TOP (10) [SYS_I] "
		            		+ ",[SITE_SYS_I]"
		            		+ ",[CNCT_I]"
		            		+ ",[ALT_CNCT_I]"
		            		+ ",[ACTV_X]"
		            		+ ",[RESV_IN_USE_N]"
		            		+ ",[CNCT_PT_TYP_SYS_I]"
		            		+ "FROM [MNFLD].[dbo].[wmgma01_cnct_pt] ");
		            /*ResultSet result = stat.executeQuery("select count(c_custkey) as cno "
		                + "from customer ");
		            if (result.next()) {
		            int resultint = result.getInt("cno");
		            custkey = resultint + 1;
		            }
		            
		            result = stat.executeQuery("select c_custkey "
		                + "from customer "
		                + "where c_custkey = '" + custkey +"'");
		            */    
		            //check custkey doesn't exist yet
		            if (result.next()) {
		            	int resultInt = result.getInt("[SYS_I]");
		            	System.out.println(resultInt);
		            }
		        }
		        catch(SQLException e) {
		        System.err.println(e.getMessage());
		        }
		}

		
		public static void main(String[] args) {
			try {
				connection = DriverManager.getConnection(DBLocation);
		
			} catch(SQLException e) {
				System.err.println(e.getMessage());
				}
			
			//SelectQuery();
		}



}