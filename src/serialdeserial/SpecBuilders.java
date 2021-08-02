package serialdeserial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



public class SpecBuilders 
{

	public static void main(String[] args) 
	{
		
		AddPlacePlayload a = new AddPlacePlayload();
		a.setAccuracy(50);
		a.setAddress("4-6-158/14/A");
		a.setLanguage("French-IN");
		a.setName("mohammed enterprises private limited");
		a.setPhone_number("+91 7702066520");
		a.setWebsite("https://www.rawoofbhai.com");
		
		
		
		List<String> types =new ArrayList<String>();
		types.add("park");
		types.add("shopping complex");
		a.setTypes(types);
		
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.5436453);
		a.setLocation(loc);
		
		// request spec builders
		
		RequestSpecification reqspec= new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.setContentType("application/json")
		.addQueryParam("key", "qaclick")
		.build();
				
		
		//  separating body part as it is common
		RequestSpecification body = given().spec(reqspec).body(a);
		

		// separating response spec builders
		
		ResponseSpecification respspec = 
				new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		//  separating body part as it is common
		
		Response post_response = 
		
		body.when().post("maps/api/place/add/json")
		.then().spec(respspec).assertThat()
		.body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response();
		
		System.out.println(post_response.asString());
		
		
	}
}
