package serialdeserial;

import static io.restassured.RestAssured.given;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class DeserializationTest {

	public static void main(String[] args) 
	{
		
		/*
		 * https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
		 */
		
		String auth_url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g75nYbhBmPrbxI8lTgzLxh9ikoHo4fOk4EwSmWxCwx5uZ7DQ8WFuqQbJLjdZ7j9hQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		
		
		String partialcode=auth_url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		
		String access_token_response = 
		given().urlEncodingEnabled(false).queryParams("code",code)
		 .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
         .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
         .queryParams("grant_type", "authorization_code")
         .queryParams("state", "verifyfjdss")
         .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
         .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
         .when()
         .post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		
		JsonPath js = new JsonPath(access_token_response);
		String access_token = js.getString("access_token");
		
		System.out.println(access_token);
		
		// POJO classes
		/*
		 *  create one POJO class plane old java object for response (GetCourses)
		 *  In this scenario courses have again nested JSON with arrays
		 *  courses has nested JSON webautomation, api, mobile
		 *  each webautomation, api, mobile has again arrays and json
		 */
		GetCourse getcourse_response =
				
				given().urlEncodingEnabled(false)
				.queryParam("access_token",access_token).expect().defaultParser(Parser.JSON) 
				// we need to say that respone wil be in JSON so expect().defaultParser(Parser.JSON)
				// as in our test we don't have content_type as applciationn/json
				// O.W we can ignore.
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		
		System.out.println(getcourse_response.getCourses().getWebAutomation().get(0).getCourseTitle()) ;
		
		
		for(int i=0;i<getcourse_response.getCourses().getApi().size();i++)
		{
			if(getcourse_response.getCourses().getApi().get(i).getCourseTitle().contains("SoapUI Webservices testing"))
			{
				int price = getcourse_response.getCourses().getApi().get(i).getPrice();
				System.out.println(price);
			}
		}
		// print all course title in web automation courses
		for(int i=0;i<getcourse_response.getCourses().getWebAutomation().size();i++)
		{
			System.out.println(getcourse_response.getCourses().getWebAutomation().get(i).getCourseTitle());
			
		}
		
		
	}

}
