package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		//execute this code when place_id id null
		//write a code that will give place_id
		
		StepDefination m=new StepDefination();
		
		if(StepDefination.place_id == null) {
			
		
		m.addplace_api_payload_with("Rahul", "English", "Asia");
		m.user_calls_something_with_something_http_request("addPlaceApi", "post");
		m.verify_place_id_created_that_maps_to_using("Rahul", "getPlaceApi");
		}
	}

}
