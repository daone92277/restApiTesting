package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


public class Basics {

	public static void main(String[] args) {
		
		//addPlace
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		 String getResponse = given().log().all()
		.queryParam("key", "qaclick123")
		.header("Content-type", "application/json")
		.body(Payload1.addPlace()).when()
		.post("maps/api/place/add/json")
		.then().log().all().assertThat()
		.statusCode(200)
		.body("scope",equalTo("APP"))
		.header("server", equalTo("Apache/2.4.18 (Ubuntu)"))
		.extract().response().asString();
		
		
		JsonPath js = new JsonPath(getResponse);
		String placeId = js.getString("place_id");
		
		
		
		
		//E2E test  Update Place with new address  -> 
		
		String address = "2430 Bayswater Ave. Far Rockaway NY 11691";
		
		 given().log().all().queryParam("key", "qaclick123")
		.header("Content-type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+address+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("maps/api/place/update/json")
		.then().assertThat()
		.log().all()
		.statusCode(200)
		.body("msg", equalTo("Address successfully updated"))
		.header("server", equalTo("Apache/2.4.18 (Ubuntu)"));
		
		

		
		// Get place to validate new address has been added.
		 
				  String getPlaceResponse = given().log().all()
				 .queryParam("key", "qaclick123")
				 .queryParam("place_id", placeId)
				 .when().get("maps/api/place/get/json")
					.then().assertThat()
					.log().all()
					.statusCode(200)
					.body("address", equalTo(address))
					.header("server", equalTo("Apache/2.4.18 (Ubuntu)"))
					.extract().response().asString();
			
				  JsonPath js1 = new JsonPath(getPlaceResponse);
				  String actualAddress = js1.getString("address");
				  System.out.println("My updated address is " + actualAddress);
				  Assert.assertEquals(actualAddress, address);
				  System.out.println("Program has completed!");
				  
				  
		
		


		
		

	}

}
