package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	


	
	
	public RequestSpecification requestSpecification() throws IOException {
		
		
		if(req == null)
		{
			PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setBaseUri(getGlobalValue("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key","qaclick123").build();


		return req;
		}
		return req;
		
	}
	//D:\Download\FrameworkCopy\FrameworkCopy\src\test\java\resources\global.properties
	//C:\\Users\\Naveen\\Desktop\\FrameworkCopy\\src\\test\\java\\resources\\global.properties
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream("D:\\Download\\FrameworkCopy\\FrameworkCopy\\src\\test\\java\\resources\\global.properties");
		prop.load(file);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response,String key) {
		
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
		
		
	}

}
