//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.*;
import java.util.*;
//import java.lang.Class;

public class JDBC {

	// DBLocation is local to my computer, theres a way to make remote
	// accessible, ill do later
	static String DBLocation = "jdbc:sqlserver://JUSTIN\\SQLEXPRESS,1433:1433;integratedSecurity=true;";
	//static String DBLocation = "jdbc:sqlserver://192.168.99.1:1433;databaseName=MNFLD;";

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
				System.out.println("CONNECTED");
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
	
	public static void findExistingLineUp() {
		openSQLConnection();
		try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT lu.SYS_I AS LineUP_SYS_ID, "
					+ "DATEDIFF(dd, '29-MAR-2005', LU.UN_CLIP_T) AS DateDiff, "
					+ "cp.CNCT_I,"
					+ "lu.TOT_LTH_N,"
					+ "lu.CLIP_CNT_N,"
					+ "lu.HOSE_CNT_N,"
					+ "cn.CNCT_PT_FROM_SYS_I,"
					+ "cn.CNCT_PT_TO_SYS_I,"
					+ "lu.RESV_T, "
					+ "lu.UN_CLIP_T "
					+ "FROM	MNFLD.dbo.wmgma04_ln_up AS lu, "
					+ "MNFLD.DBO.wmgma01_cnct_pt AS cp, "
					+ "MNFLD.dbo.WMGMA05_CNCT AS cn "
					+ "WHERE lu.SYS_I > 200 AND "
					+ "cp.SYS_I = lu.CNCT_PT_TO_SYS_I AND "
					+ "cp.SYS_I < 200 AND "
					+ "lu.SYS_I = cn.LN_UP_SYS_I "
					+ "ORDER BY	DateDiff ASC, "
					+ "lu.TOT_LTH_N ASC, "
					+ "lu.HOSE_CNT_N ASC, "
					+ "lu.CLIP_CNT_N ASC, "
					+ "lu.SYS_I");

			while (result.next()) {
				int i = 1;
				System.out.println(result.getString(i));
				i++;
			}
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
	}

	public static void findNearestPanel() {
		openSQLConnection();
		try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT "
					+ "CP.CNCT_I AS CP_FROM, "
					+ "CP2.CNCT_I AS CP_TO, "
					+ "'MAX' AS CLIP_COST "
					+ "FROM MNFLD.dbo.wmgma01_cnct_pt AS CP, "
					+ "MNFLD.dbo.wmgma01_cnct_pt AS CP2, "
					+ "MNFLD.DBO.WMGMA05_CNCT AS C "
					+ "WHERE "
					+ "CP.ACTV_X = 0 "
					+ "AND CP.RESV_IN_USE_N < 2 "
					+ "AND C.CNCT_PT_FROM_SYS_I = CP.SYS_I "
					+ "AND C.CNCT_PT_TO_SYS_I = CP2.SYS_I "
					+ "AND ( "
					+ "SUBSTRING(CP.CNCT_I, 4,1) <> '-' "
					+ "OR SUBSTRING(CP2.CNCT_I, 4,1) <> '-') "
					+ "ORDER BY  CP.CNCT_I, CP2.CNCT_I");

			while (result.next()) {
				int i = 1;
				System.out.println(result.getString(i));
				i++;
			}
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
	}
	
	
	
	public static void findNearestPort() {
		openSQLConnection();
		try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT "
					+ "SUBSTRING(CP.CNCT_I, 1,3) AS FROM_PANNEL, "
					+ "SUBSTRING(CP.CNCT_I, 5, 3) AS FROM_PORT, "
					+ "SUBSTRING(CP2.CNCT_I, 1,3) AS TO_PANNEL, "
					+ "SUBSTRING(CP2.CNCT_I, 5, 3) AS TO_PORT, "
					+ "'MAX' AS CLIP_COST "
					+ "FROM MNFLD.dbo.wmgma01_cnct_pt AS CP, "
					+ "MNFLD.dbo.wmgma01_cnct_pt AS CP2, "
					+ "MNFLD.DBO.WMGMA05_CNCT AS C "
					+ "WHERE "
					+ "CP.ACTV_X = 0 "
					+ "AND CP.RESV_IN_USE_N < 2 "
					+ "AND C.CNCT_PT_FROM_SYS_I = CP.SYS_I "
					+ "AND C.CNCT_PT_TO_SYS_I = CP2.SYS_I "
					+ "AND SUBSTRING(CP.CNCT_I, 4,1) = '-' "
					+ "AND SUBSTRING(CP2.CNCT_I, 4,1) = '-' "
					+ "ORDER BY FROM_PANNEL ASC, "
					+ "FROM_PORT ASC");

			while (result.next()) {
				int i = 1;
				System.out.println(result.getString(i));
				i++;
			}
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
	}
	
	
	public static void getLineUpFrom_LineUpSYS_I() {
		openSQLConnection();
		try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT C.LN_UP_SYS_I AS LINE_UP_ID, "
					+ "C.SEQ_N AS SEQ, "
					+ "CPF.CNCT_I AS FROM_CP, "
					+ "CPT.CNCT_I AS TO_CP, "
					+ "CPF.CNCT_PT_TYP_SYS_I AS CONNECTION, "
					+ "C.CNCT_PT_FROM_SYS_I AS C_FROM_ID, "
					+ "C.CNCT_PT_TO_SYS_I AS C_TO_ID, "
					+ "lu.TOT_LTH_N AS LINE_UP_LEN, "
					+ "LU.CLIP_CNT_N AS CLIPS, "
					+ "LU.HOSE_CNT_N AS HOSES "
					+ "FROM	MNFLD.dbo.WMGMA05_CNCT AS C, "
					+ "MNFLD.dbo.wmgma04_ln_up AS LU, "
					+ "MNFLD.dbo.wmgma01_cnct_pt AS CPF, "
					+ "MNFLD.dbo.wmgma01_cnct_pt AS CPT "
					+ "WHERE LU.TOT_LTH_N > 2500 "
					+ "AND C.LN_UP_SYS_I = lu.SYS_I "
					+ "AND CPF.SYS_I = C.CNCT_PT_FROM_SYS_I "
					+ "AND CPT.SYS_I = C.CNCT_PT_TO_SYS_I "
					+ "AND LU.HOSE_CNT_N < 100 "
					+ "AND LU.CLIP_CNT_N < 100 "
					+ "ORDER BY LU.TOT_LTH_N DESC, C.LN_UP_SYS_I, C.SEQ_N");

			while (result.next()) {
				int i = 1;
				System.out.println(result.getString(i));
				i++;
			}
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
	}
	

	
	public static void getOpenConnectionPorts() {
		openSQLConnection();
		try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT *" + "FROM MNFLD.dbo.wmgma01_cnct_pt AS cp "
					+ "WHERE cp.ACTV_X =0 AND cp.RESV_IN_USE_N < 2 " + "ORDER BY cp.RESV_IN_USE_N ASC, cp.CNCT_I");

			while (result.next()) {
				int i = 1;
				System.out.println(result.getString(i));
				i++;
			}
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
	}
	
	
	public static void getOpenPossibleConnections() {
		openSQLConnection();
		try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT *"
					+ "FROM MNFLD.dbo.wmgma02_posbl_cnct AS pc, (SELECT cp.SYS_I FROM MNFLD.dbo.wmgma01_cnct_pt AS cp WHERE cp.ACTV_X = 0 AND cp.RESV_IN_USE_N < 2) AS oc "
					+ "WHERE pc.ACTV_X = 0 AND (oc.SYS_I = pc.CNCT_PT_SIDE1_SYS_I OR oc.SYS_I = pc.CNCT_PT_SIDE2_SYS_I) "
					+ "ORDER BY HOSE_CNT_N, CLIP_SZ--SYS_I");

			while (result.next()) {
				int i = 1;
				System.out.println(result.getString(i));
				i++;
			}
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
	}
	

	public static void getPipes() {
		openSQLConnection();
		try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT SYS_I, "
					+ "SITE_SYS_I, "
					+ "CNCT_I, "
					+ "ALT_CNCT_I, "
					+ "ACTV_X, "
					+ "RESV_IN_USE_N,"
					+ "CNCT_PT_TYP_SYS_I "
					+ "FROM MNFLD.dbo.wmgma01_cnct_pt "
					+ "WHERE SITE_SYS_I = 10");

			while (result.next()) {
				int i = 1;
				System.out.println(result.getString(i));
				i++;
			}
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
	}

	
	public static List<Node> graphInformation() {
		List<Node> newnode = new ArrayList<Node>();
		openSQLConnection();
		try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT CP1.SYS_I AS CP1_ID, "
					+ "CP1.CNCT_I AS CP1_CN, "
					+ "CP2.SYS_I AS CP2_ID, "
					+ "CP2.CNCT_I AS CP2_CN, "
					+ "PIPE.LTH_N AS PipeLength, "
					+ "* "
					+ "FROM "
					+ "MNFLD.DBO.WMGMA08_PIPE AS PIPE, "
					+ "MNFLD.DBO.wmgma01_cnct_pt AS CP1, "
					+ "MNFLD.DBO.wmgma01_cnct_pt AS CP2 "
					+ "WHERE "
					+ "CP1.SITE_SYS_I = 10 "
					+ "AND CP2.SITE_SYS_I = 10 "
					+ "AND PIPE.CNCT_PT_SIDE1_SYS_I = CP1.SYS_I "
					+ "AND PIPE.CNCT_PT_SIDE2_SYS_I = CP2.SYS_I "
					+ "AND PIPE.LTH_N IS NOT NULL "
					+ "ORDER BY PIPE.LTH_N, CP1.CNCT_I");
			
			System.out.println("CP1_ID\tCP1_CN\tCP2_ID\tCP2_CN\tpipelength");
			float cp1_id, cp2_id, pipelength;
			String cp1_cn, cp2_cn;
			
			
			while (result.next()) {
				int i = 0;
				cp1_id = result.getFloat("CP1_ID");
				String cp1 = String.valueOf(cp1_id); //temporary
				cp1_cn = result.getString("CP1_CN");
				
				cp2_id = result.getFloat("CP2_ID");
				cp2_cn = result.getString("CP2_CN");
				
				pipelength = result.getFloat("PipeLength");
				boolean vis = false;
				//Node ports = new Node(cp1_cn, cp2_cn, pipelength, vis);
				Node ports = new Node(cp1, cp1_cn, cp2_cn, pipelength);
				//info.add(i, new Node(src, dest, cst, vis));
				newnode.add(i, ports);
				
				i++;
				
				System.out.println(cp1_id + "\t" + cp1_cn + "\t" + cp2_id + "\t" + cp2_cn + "\t" + pipelength);
			}
			
			
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
		return newnode;
	}
	
	public static void insertPipes(Graph g) {	
		try {	
			openSQLConnection();	
			Statement stat = connection.createStatement();	
			ResultSet result = stat.executeQuery (	
					"SELECT CP1.SYS_I AS CP1_ID, "	
							+ "CP2.SYS_I AS CP2_ID, "	
							+ "PIPE.LTH_N AS PipeLength, "	
							+ "* "	
					+ "FROM "	
							+ "MNFLD.DBO.WMGMA08_PIPE AS PIPE, "	
							+ "MNFLD.DBO.wmgma01_cnct_pt AS CP1, "	
							+ "MNFLD.DBO.wmgma01_cnct_pt AS CP2 "	
					+ "WHERE "	
							+ "CP1.SITE_SYS_I = 10 "	
							+ "AND CP2.SITE_SYS_I = 10 "	
							+ "AND PIPE.CNCT_PT_SIDE1_SYS_I = CP1.SYS_I "	
							+ "AND PIPE.CNCT_PT_SIDE2_SYS_I = CP2.SYS_I "	
							+ "AND PIPE.LTH_N IS NOT NULL "	
							+ "ORDER BY" +	
							" PIPE.LTH_N, CP1.CNCT_I" );	

 			System.out.println ( "CP1_ID\tCP2_ID\tpipelength" );	
			float cp1_id, cp2_id, pipelength;	
			String cp1_cn, cp2_cn;	
			while (result.next()) {	
				cp1_id = result.getFloat("CP1_ID");	
//				cp1_cn = result.getString("CP1_CN");	

 				cp2_id = result.getFloat ( "CP2_ID" );	
//				cp2_cn = result.getString("CP2_CN");	

 				pipelength = result.getFloat ( "PipeLength" );	
				//g.addPipe ( new Node ( String.valueOf ( cp1_id ), String.valueOf ( cp2_id ), pipelength ) );	


 				System.out.println ( cp1_id + "\t" + cp1_id + "\t" + cp2_id + "\t" + pipelength );	
			}	
		} catch (SQLException e) {	
			System.err.println ( e.getMessage () );	
		}	
	}	


 	public static void insertPipes_w_Names(Graph g) {	
		try {	
			openSQLConnection ();	
			Statement stat = connection.createStatement ();	
			ResultSet result = stat.executeQuery (	
					"SELECT\t\n" +	
							"\t\tCP1.CNCT_I AS CP1_CN,\n" +	
							"\t\tCP2.CNCT_I AS CP2_CN,\n" +	
							"\t\tPIPE.LTH_N AS 'Pipe Length'--,*\n" +	
							"FROM \n" +	
							"\tMNFLD.DBO.WMGMA08_PIPE AS PIPE,\n" +	
							"\tMNFLD.DBO.wmgma01_cnct_pt AS CP1,\n" +	
							"\tMNFLD.DBO.wmgma01_cnct_pt AS CP2\n" +	
							"WHERE \n" +	
							"\tCP1.SITE_SYS_I = 10\n" +	
							"\tAND CP2.SITE_SYS_I = 10\n" +	
							"\tAND PIPE.CNCT_PT_SIDE1_SYS_I = CP1.SYS_I\n" +	
							"\tAND PIPE.CNCT_PT_SIDE2_SYS_I = CP2.SYS_I\n" +	
							"\tAND PIPE.LTH_N IS NOT NULL\n" +	
							"\tORDER BY PIPE.LTH_N, CP1.CNCT_I"	
			);	

 			System.out.println ( "CP1_CN\tCP2_CN\tpipelength" );	
			float cp1_id, cp2_id, pipelength;	
			String cp1_cn, cp2_cn;	
			while (result.next ()) {	
				cp1_cn = result.getString ( "CP1_CN" );	
				cp2_cn = result.getString ( "CP2_CN" );	
				pipelength = result.getFloat ( "PipeLength" );	
				//g.addPipe ( new Node ( cp1_cn, cp2_cn, pipelength ) );	

 				System.out.println ( cp1_cn + "\t" + cp2_cn + "\t" + pipelength );	
			}	
		} catch (SQLException e) {	
			System.err.println ( e.getMessage () );	
		}	
	}	

/*new Edge is taking in float, float, float, 
 * constructor is Node Node Int
 */
 	public static void insertConnections(Graph g) {	
		try {	
			openSQLConnection ();	
			Statement stat = connection.createStatement ();	
			ResultSet result = stat.executeQuery (	
					"SELECT [CNCT_PT_SIDE1_SYS_I] AS CP1_ID\n" +	
							"      ,[CNCT_PT_SIDE2_SYS_I] AS CP2_ID\n" +	
							"      ,[CLIP_SZ] AS SIZE\n" +	
							"      ,[HOSE_CNT_N] AS 'COUNT'\n" +	
							"  FROM [MNFLD].[dbo].[wmgma02_posbl_cnct]"	
			);	

 			System.out.println ( "CP1_ID\tCP2_ID\tSIZE\tCOUNT" );	
			float cp1_id, cp2_id, pipelength;	
			while (result.next ()) {	
				cp1_id = result.getFloat ( "CP1_ID" );	
				cp2_id = result.getFloat ( "CP2_ID" );	
				pipelength = result.getFloat ( "PipeLength" );	
				//g.insertConnection ( new Edge (cp1_id, cp2_id, pipelength ));	

 				System.out.println ( cp1_id + "\t" + cp2_id + "\t" + pipelength );	
			}	
		} catch (SQLException e) {	
			System.err.println ( e.getMessage () );	
		}	


 	}
	
}