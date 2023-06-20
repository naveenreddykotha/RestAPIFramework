
Feature: Validating place APIs

@AddPlace
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
	Given AddplaceAPI payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceApi" with "post" http request
	Then the API call got success and status code should be 200
	And the "status" in response body is "OK"
	And the "scope" in response body is "APP"
	And verify place_id created that maps to "<name>" using "getPlaceApi"
	
	Examples: 
			|name         | language |address|
			|shivajiHouse | English  |Delhi  |
	#		|williamsHouse| French   |City Street,France|

@DeletePlace	
Scenario: Verify if delete place functionality is working
	Given deletePlace payLoad
	When user calls "deletePlaceApi" with "post" http request
	Then the API call got success and status code should be 200
	And the "status" in response body is "OK"
	
	