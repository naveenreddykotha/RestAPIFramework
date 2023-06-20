package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbConnection  {
	
	
	static ArrayList conn() {
		ArrayList a=new ArrayList();
		try {
//			ArrayList a=new ArrayList();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rsamaps","root","master#123");
			//once you have a connection to the database,you can create a statenment object to execute sql commands
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from payload");
			//DbConnection obj;
			
			while(rs.next()) {
				a.add(0, rs.getInt(1));
				a.add(rs.getDouble(2));
				a.add(rs.getDouble(3));
				a.add(rs.getString(4));
				a.add(rs.getString(5));
				a.add(rs.getString(6));
				a.add(rs.getString(7));		
			    
			}
			//System.out.println(a);
			con.close();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return a;
	}
		
		
	


	
}

