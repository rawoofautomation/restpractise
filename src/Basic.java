import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basic { // 1. add place (POST)  2. update place (PUT) 3. validate place (GET)  

	public static void main(String[] args) {
	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		// 1. add place (POST)
		String post_response = given().log().all()
		.queryParam("key","qaclick123").header("Content-Type","application/json").body(PayLoad.payload())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat()
		.statusCode(200)
		.body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		JsonPath j = new JsonPath(post_response);
		String place_id = j.getString("place_id");
		
		System.out.println("********"+ place_id+"**********8");
		
		// 2. update place (PUT)
		String new_address = "banjarahills, Hyderabad";
		
		 given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+new_address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().body("msg",equalTo("Address successfully updated"));
		 
		 // 3. get place (GET)
		 
		 String getPlace_Response = given().log().all().queryParam("key","qaclick123")
		 .queryParam("place_id",place_id).header("Content-Type","application/json")
		 .when().get("maps/api/place/get/json")
		 .then().log().all().assertThat().statusCode(200).body("address", equalTo(new_address))
		 .extract().response().asString();
			
		 JsonPath jp = new JsonPath(getPlace_Response);
		 String getResponse_address = jp.getString("address");
		 Assert.assertEquals(new_address, getResponse_address);
		 
	}

}
