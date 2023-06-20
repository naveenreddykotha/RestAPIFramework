package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pojo.LatLon;
import pojo.Pojo;

public class TestDataBuild  {
	
	
	public Pojo addPlacePayLoad(String name, String language, String address)   {
		
		//DbConnection obj1=new DbConnection();
	    //DbConnection.conn();
		ArrayList arr= DbConnection.conn();
		System.out.println(arr);
		
		Pojo p=new Pojo();
		p.setAccuracy((int) arr.get(0));
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		LatLon l=new LatLon(); 
		l.setLat((double) arr.get(1));
		l.setLng((double) arr.get(2));
		p.setLocation(l);
		p.setPhone_number((String) arr.get(3));
		p.setWebsite((String) arr.get(4));
		List<String> mylist=new ArrayList<String>();
		mylist.add((String) arr.get(5));
		mylist.add((String) arr.get(6));
		p.setTypes(mylist);
		return p;
	}
	
	public String deletePlacePayLoad(String placeId) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
	}

}
