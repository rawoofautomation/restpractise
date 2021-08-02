import static io.restassured.RestAssured.given;

import java.util.*;

import org.testng.annotations.Test;

import files.PayLoad;
import files.ReadData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DataDriven {

	
	@Test
	public void addPlace() throws Exception
	{
		//HashMap<String,Object> s =null; //new ReadData().data();
		
		
		HashMap<String,Object> s = new HashMap<String,Object>();
		s.put("name","can be any");
		s.put("isbn","hyderabad bhai");
		s.put("aisle", "urdu language");
		s.put("author", "rawof ahmed urf ahmed");
		
		
		HashMap<String,Object> loc = new HashMap<String,Object>();
		s.put("lat",34.656);
		s.put("lan",-23.545);
		s.put("location", loc); 
		
		
		RestAssured.baseURI = "http://216.10.245.166";
		String addPlace_Response = given().log().all().header("Content-Type","application/json")
				.body(s)
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath jp = new JsonPath(addPlace_Response);
		Map<Object,Object> id = jp.get();
		
		System.out.println("*********"+id+"***************");
	}
	
	
	
}
