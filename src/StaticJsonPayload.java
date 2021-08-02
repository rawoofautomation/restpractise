import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJsonPayload {

	
	public static String staticJsonPayload() throws IOException
	{
		String path = "C:\\Users\\Admin\\OneDrive\\Desktop\\API course\\Postman_APIContract\\addPlace.json";
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	@Test
	public String post() throws Exception
	{
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
		
		return place_id;
	}
	
}
