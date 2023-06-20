package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.LatLon;
import pojo.Pojo;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;



public class StepDefination extends Utils {
	
	RequestSpecification request;
	Response response;
	TestDataBuild data=new TestDataBuild();
	static String place_id;
	
	
	@Given("AddplaceAPI payload with {string} {string} {string}")
	public void addplace_api_payload_with(String name, String language, String address) throws IOException {
        
		
		request=given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name,language,address));
    }

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void user_calls_something_with_something_http_request(String resource, String method) throws Throwable {
        
    	
    	ResponseSpecification resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectHeader("Server","Apache/2.4.41 (Ubuntu)")
				.expectBody("scope",equalTo("APP"))
				.build();
    	
    	ApiResources resourceApi= ApiResources.valueOf(resource);
    	System.out.println(resourceApi.getResource());
    	
    	if(method.equalsIgnoreCase("post")) {
    		response= request.when().post(resourceApi.getResource());
    	}
    	else if(method.equalsIgnoreCase("get")) {
    		response=request.when().get(resourceApi.getResource());
    	}
    	
    	
				//response= request.when().post(resourceApi.getResource())
				//.then().spec(resspec).extract().response();
				
				
		
		//System.out.println(response);
    	
    }

    @Then("^the API call got success and status code should be 200$")
    public void the_api_call_got_success_and_status_code_should_be_200() throws Throwable {
        assertEquals(response.getStatusCode(),200);
    	
    	
    }

    @And("^the \"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void the_something_in_response_body_is_something(String keyValue, String expectValue) throws Throwable {
        
    	//String resp=response.asString();
    	
    	//js=new JsonPath(resp);
    	//place_id= js.get("place_id");
    	
    	assertEquals(getJsonPath(response,keyValue),expectValue);
    }
    

    
    
    @Then("verify place_id created that maps to {string} using {string}")
    public void verify_place_id_created_that_maps_to_using(String expectedName, String resource) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        
    	place_id=getJsonPath(response,"place_id");
    	request=given().spec(requestSpecification()).queryParam("place_id",place_id);
    	
    	user_calls_something_with_something_http_request(resource,"get");
    	String actualName=getJsonPath(response,"name");
    	assertEquals(actualName,expectedName);
    }
    
    //second scenario code
    @Given("deletePlace payLoad")
    public void delete_place_pay_load() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        request= given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));
    }
	
}
