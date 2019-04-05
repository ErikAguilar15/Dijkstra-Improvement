//import java.sql.*;
//import java.util.Scanner;
//
///*
//	- Getting your connections is bundled with getting the driver so both names are passed as parameters
//	- Closing connection requires you to send the connection to know which one to close
//	- Getting statement returns a statement to be able to execute a query
//*/
//
//
//
//private static Connection getConnection(Sting JDBC_DRIVER, String DB_URL) {
//
//	// Registering the JDBC driver
//	try {
//		// Class.forName("org.sqlite.JDBC");
//		Class.forName(JDBC_DRIVER);
//	}
//	catch (ClassNotFoundException e) {
//		System.out.println("ERROR: Application failed to register Database Driver");
//		e.printStackTrace();
//	}
//
//	// Getting connection to database server
//	try {
//		//System.out.println("Establishing Connection");
//		// String DB_URL = "jdbc:sqlite:/Users/marioortega/CS/sqlite3/CSE111/DatabaseServer/project/hyperWhips.db";
//		return DriverManager.getConnection(DB_URL);
//	}
//	catch (SQLException e) {
//		System.out.println("ERROR: Application failed to open a connection to server");
//		e.printStackTrace();
//		return null;
//	}
//}
//
//private static void closeConnection(Connection conn) {
//
//	if (conn != null) {
//		try {
//			conn.close();
//		}
//		catch (SQLException e) {
//			System.out.println("ERROR: Application failed to close connection.");
//			e.printStackTrace();
//		}
//	}
//	//System.out.println("Closed Connection");
//}
//
//private static Statement getStatement(Connection conn) {
//
//	Statement stmt = null;
//	try {
//		// System.out.println("Connected");
//		stmt = conn.createStatement();
//	}
//	catch (SQLException e) {
//		e.printStackTrace();
//	}
//
//	return stmt;
//}
//
//private static void queryDB(Statement s, String sql) {
//	ResultSet rs = s.executeQuery(sql);
//	System.out.println("Query Results: ");
//        while (rs.next()) {
//            System.out.println("Value 1: " + rs.getString("val1"));
//            System.out.println("Value 2: " + rs.getString("val2"));
//            System.out.println("Value 3: " + rs.getString("val3"));
//            System.out.println("Value 4: " + rs.getString("val4"));
//        }
//}
