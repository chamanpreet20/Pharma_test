package reading_data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database_pharma {
public static void main(String[] args) throws ClassNotFoundException, SQLException
{
	String url = "jdbc:sqlserver://localhost:1433;";
	String servername="serverName=EVS01TST06;";
	String dbName = "databaseName=PI_HealthEconomics;";
	String username = "PIDBUser";
	String password = "pit1234#";
	//String query = "select * FROM [PI_HealthEconomics].[EPI].[tx_DiseaseOverview];";
	String query= "select TOP 5 indication.[Id],indication.[Name],indication.[ReportCodeName],abbreviation.[FullForm]FROM [PI_HealthEconomics].[EPI].[tm_Indication] as indication inner join [PI_HealthEconomics].[EPI].[tm_Abbreviation] as abbreviation on indication.Id=abbreviation.IndicationId;";
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	Connection conn = DriverManager.getConnection(url+servername+dbName,username,password);
	 Statement stmt = conn.createStatement();
	 ResultSet rs= stmt.executeQuery(query);
	 while (rs.next()){
 		String indication_id = rs.getString("Id");
 		 String reportname = rs.getString("ReportCodeName");	
 		System.out.println(indication_id+ " \n " +reportname);
     }
	 conn.close();
	}
	
}
