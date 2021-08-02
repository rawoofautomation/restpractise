import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class OAuth2 {

	public static void main(String[] args) 
	{
		String auth_url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g5aJ2TtR1rvwl1XyUB3omN1MQMxKujVn3fj_ycss4TccazeQuJy9A8ZKWFtKyKa9w&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
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
		
		
		String response =
				
				given().urlEncodingEnabled(false)
				.queryParam("access_token",access_token)
				.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(response);
	}

}
