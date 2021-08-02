import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicPayloads {

	
	
	
	
	@Test(dataProvider="bookData")
	public void addPlace(String isbn,String alise)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String addPlace_Response = given().log().all().header("Content-Type","application/json")
				.body(PayLoad.addBook_payload(isbn,alise))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath jp = new JsonPath(addPlace_Response);
		String id = jp.getString("ID");
		
		System.out.println("*********"+id+"***************");
	}
	
	@DataProvider(name="bookData")
	public Object[][] getData()
	{ /*
		Object o[][] = new Object[3][2];
		
		o[0][0] = "wsxa";
		o[0][1] = "0987uio0";
		

		o[1][0] = "wsxatgv";
		o[1][1] = "0987uio02e";

		o[2][0] = "wsxaujn";
		o[2][1] = "0987uio04t";
		
		return o;
		*/
		return new Object[][]  {  {"1stuser","1stpass"},{"2nduser","2ndpass"} ,{"3rduser","3rdpass"}};
	}
	
	
	
}
