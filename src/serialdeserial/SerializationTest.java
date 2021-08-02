package serialdeserial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import files.PayLoad;
import io.restassured.RestAssured;



public class SerializationTest 
{

	public static void main(String[] args) 
	{
		
		AddPlacePlayload a = new AddPlacePlayload();
		a.setAccuracy(50);
		a.setAddress("4-6-158/14/A");
		a.setLanguage("French-IN");
		a.setName("mohammed enterprises");
		a.setPhone_number("7702066520");
		a.setWebsite("https://www.rawoofbhai.com");
		
		
		
		List<String> types =new ArrayList<String>();
		types.add("park");
		types.add("shopping complex");
		a.setTypes(types);
		
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.5436453);
		a.setLocation(loc);
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		// 1. add place (POST)
		String post_response = given().log().all()
		.queryParam("key","qaclick123").header("Content-Type","application/json").body(a)
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat()
		.statusCode(200)
		.body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(post_response);
		
	}
}
