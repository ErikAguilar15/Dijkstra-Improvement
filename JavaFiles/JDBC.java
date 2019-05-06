//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import java.lang.Class;

public class JDBC {

	// DBLocation is local to my computer, theres a way to make remote
	// accessible, ill do later
	//static String DBLocation = "jdbc:sqlserver://JUSTIN\\SQLEXPRESS,1433:1433;integratedSecurity=true;";
    //	static String DBLocation = "jdbc:sqlserver://192.168.99.1:1433;databaseName=MNFLD;";
    //static String DBLocation = "jdbc:sqlserver://172.17.0.2;databaseName=MNFLD;integratedSecurity=true;";
//    static String DBLocation = "jdbc:sqlserver://localhost;databaseName=MNFLD;";


	 static String DBLocation = "jdbc:sqlserver://JUSTIN:1433;databaseName=MNFLD;integratedSecurity=true"; //local, working
	static Connection connection = null;

//    static String username = "jgreen";
//    static String password = "ejgallo";

    static String username = "sa";
	static String password = "ejgallo";

	// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

	// connects to SQL Server DB
	public static void openSQLConnection() {
		try {
			connection = DriverManager.getConnection(DBLocation, username, password);

			if (connection != null) {
                System.out.println("CONNECTION: SUCESS");
            } else {
                System.out.println("CONNECTION: FAILED");
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


	public static ArrayList<Node> graphInformation() {
		ArrayList<Node> newnode = new ArrayList<Node>();
		openSQLConnection();
		try {
			Statement pipe = connection.createStatement();
			ResultSet result = pipe.executeQuery("SELECT CP1.SYS_I AS CP1_ID, " //might be wrong
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
					+ "AND PIPE.LTH_N IS NOT NULL ");
					//+ "ORDER BY PIPE.LTH_N, CP1.CNCT_I");
			

			//System.out.println("CP1_ID\tCP1_CN\tCP2_ID\tCP2_CN\tpipelength");
			float cp1_id, cp2_id, pipelength;
			String cp1_cn, cp2_cn;
			int i = 0;

			while (result.next()) {
				
				//cp1_id = result.getFloat("CP1_ID");
				//String cp1 = String.valueOf(cp1_id); //temporary
				cp1_cn = result.getString("CP1_CN");
				

				//cp2_id = result.getFloat("CP2_ID");
				cp2_cn = result.getString("CP2_CN");
				

				pipelength = result.getFloat("PipeLength");
				
				
				Node port = new Node(cp1_cn, cp2_cn, pipelength);
				newnode.add(i, port);
				i++;
				//System.out.println(cp1_cn + "\t" + cp2_cn);
				//System.out.println(cp1_id + "\t" + cp1_cn + "\t" + cp2_id + "\t" + cp2_cn + "\t" + pipelength);
			}
			closeSQLConnection();
			return newnode;
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
		return newnode;
	}
	
	public static ArrayList<Node> getTanks (ArrayList<Node> tankList){
		
		String tanks;
		openSQLConnection();
		try {
			
			Statement tank = connection.createStatement();
			ResultSet tankResult = tank.executeQuery("SELECT CNCT_I AS tanks "
					+ "FROM MNFLD.dbo.wmgma01_cnct_pt "
					+ "WHERE CNCT_I LIKE ('____')");
			
			//System.out.println("tanks");
			
			
			int i = tankList.size();
			while (tankResult.next()){
				tanks = tankResult.getString("tanks");
				Node newtank = new Node(tanks, 1.0f);
				//newnode.add(i, port);
				tankList.add(i, newtank); //might overlap by 1, maybe i+1
				//System.out.println(tanks);
				i++;
			}

		}
		catch (SQLException e) {
			
			System.err.println(e.getMessage());
		}
		closeSQLConnection();
		
		return tankList;
		
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
			//String cp1_cn, cp2_cn;
			while (result.next()) {
				cp1_id = result.getFloat("CP1_ID");
 				cp2_id = result.getFloat("CP2_ID");
 				pipelength = result.getFloat ("PipeLength");

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
 	public static ArrayList<Edge> insertConnections(Graph g) { //add order by to sort by clip to save runtime
 		ArrayList<Edge> newedges = new ArrayList<Edge>();
 		openSQLConnection ();
		try {
			//1 is clip, 2 is hose for cnct_type_sys_i
			Statement stat = connection.createStatement ();
			ResultSet result = stat.executeQuery ( "Select CP.CNCT_I AS Side1, " //currently doesnt get tanks
					+ "CP2.CNCT_I AS Side2, "
					+ "CLIP_SZ AS SIZE, "
					+ "HOSE_CNT_N AS HOSECNT "
					+ "FROM MNFLD.dbo.wmgma01_cnct_pt AS CP, "
					+ "MNFLD.dbo.wmgma01_cnct_pt AS CP2, "
					+ "MNFLD.dbo.wmgma02_posbl_cnct AS PC "
					+ "WHERE CP.SITE_SYS_I = 10 "
					+ "AND CP2.SITE_SYS_I = 10 "
					+ "AND CP.SYS_I = PC.CNCT_PT_SIDE1_SYS_I "
					+ "AND CP2.SYS_I = PC.CNCT_PT_SIDE2_SYS_I ");

 			//System.out.println ( "Side1\tSide2\tSIZE\tHOSECNT" );
 			
			//float size, hosecnt;
			String side1, side2;
			Node pipe1 = new Node();
			Node pipe2 = new Node();
			int i = 0;
			while (result.next ()) {
				side1 = result.getString( "Side1" );
				side2 = result.getString( "Side2" );
				//size = result.getFloat ( "SIZE" );
				//hosecnt = result.getFloat ( "HOSECNT" );
				
				pipe1 = g.getPipe(side1);
				pipe2 = g.getPipe(side2);
				Float pipelength = 0f;
				if (pipe1 != null && pipe2 != null) {
					pipelength = pipe1.getWeight() + pipe2.getWeight();
					Edge newedge = new Edge(pipe1, pipe2, pipelength);
					newedges.add(i, newedge);
					i++;
				}
				
				
				//Edge newedge = new Edge(pipe1, pipe2, pipelength);
				//newedges.add(i, newedge);
 				//System.out.println (side1+ "\t" + side2 + "\t" + size + "\t" + hosecnt);
			}
			closeSQLConnection();
			return newedges;
		} catch (SQLException e) {
			System.err.println ( e.getMessage () );
		}
		closeSQLConnection();
		return null;
 	}
 	
	public static void test() { //add order by to sort by clip to save runtime
 		openSQLConnection();
		try {
			//1 is clip, 2 is hose for cnct_type_sys_i
			Statement stat = connection.createStatement ();
			ResultSet result = stat.executeQuery("SELECT CP.CNCT_I AS Side1, CP2.CNCT_I AS Side2 "
					+ "FROM MNFLD.dbo.wmgma01_cnct_pt AS CP, "
					+ "MNFLD.dbo.wmgma01_cnct_pt AS CP2, "
					+ "MNFLD.dbo.WMGMA08_PIPE PIPE "
					+ "WHERE CP.SITE_SYS_I = 10 "
					+ "AND PIPE.CNCT_PT_SIDE1_SYS_I = CP.SYS_I "
					+ "AND  PIPE.CNCT_PT_SIDE2_SYS_I = CP2.SYS_I");

 			System.out.println ( "Side1\tSide2\tSIZE\tHOSECNT" );
 			
 			String side1 = "";
 			String side2 = "";
			
 			ArrayList<Node> newnode = new ArrayList<Node>();
			int i = 0;
			while (result.next ()) {
				side1 = result.getString("Side1");
				side2 = result.getString("Side2");
				Node pipe = new Node(side1, side2, 50f);
				newnode.add(i, pipe);
				//if (i == 1)
				break;
				
				//i++;
			}
			//035-045==060-065
			
			//closeSQLConnection();
			Graph g = new Graph();
			
			
			g.setPipes(newnode);
			Node currentpipe = new Node();
			currentpipe = g.getPipe(side1);
			String nodeID = currentpipe.getID();
			String nodePortIn = currentpipe.getPortIn();
			String nodePortOut = currentpipe.getPortOut();
			System.out.println(nodeID + "\t" + nodePortIn + "\t" + nodePortOut);
			
			//gets entire node of one adjacent edge of '035-045' side2 of node which is the 1st node in this case
			// --side2 of left node connected by edge
			//--is side1 of right node
			ResultSet getedge= stat.executeQuery("SELECT CP1.CNCT_I AS leftNode, "
					+ "CP2.CNCT_I AS rightNode "
					+ "FROM "
						+ "(SELECT CNCT_PT_SIDE1_SYS_I as side1, "
						+ "CNCT_PT_SIDE2_SYS_I as side2 "
						+ "FROM MNFLD.dbo.WMGMA08_PIPE "
						+ "WHERE CNCT_PT_SIDE2_SYS_I = ( "
							+ "SELECT CNCT_PT_SIDE1_SYS_I "
							+ "FROM MNFLD.dbo.wmgma02_posbl_cnct "
							+ "WHERE CNCT_PT_SIDE2_SYS_I = "
								+ "(SELECT DISTINCT SYS_I "
								+ "FROM MNFLD.dbo.wmgma01_cnct_pt "
									+ "WHERE CNCT_I = ('035-045') "
								+ ") "
							+ "AND CNCT_PT_SIDE1_SYS_I = 1368 "
							+ ") "
						+ ") AS node, "
					+ "MNFLD.dbo.wmgma01_cnct_pt as CP1, "
					+ "MNFLD.dbo.wmgma01_cnct_pt as CP2 "
					+ "WHERE CP1.SYS_I = node.side1 "
					+ "AND CP2.SYS_I = node.side2");
			
			String leftID = "";
			String rightID = "";
			ArrayList<Edge> newedge = new ArrayList<Edge>();
			while (getedge.next())  {	
				leftID = getedge.getString("leftNode");
				rightID = getedge.getString("rightNode");
				break;
			}
			
			Node adjpipe = new Node(leftID, rightID, 5f);
			Edge edge = new Edge(adjpipe, currentpipe, 100f);

			newedge.add(edge);
			g.setEdges(newedge);
			
			Node leftpipe = new Node();
			Node rightpipe = new Node();
			Edge testedge = new Edge();
			String side1ID, side2ID;
			testedge = g.getEdge(adjpipe, currentpipe);
			leftpipe = testedge.getSide1();
			rightpipe = testedge.getSide2();
			side1ID = leftpipe.getID();
			side2ID = rightpipe.getID();
			System.out.println(side1ID + "\t" + side2ID);
			String edgeID = testedge.getEdgeID();
			System.out.println(edgeID);
			
			
			
		} catch (SQLException e) {
			System.err.println ( e.getMessage () );
		}
		closeSQLConnection();
		
 	}


}