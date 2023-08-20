package restful.booker;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestAssuredStaticVariables2 {
	//RestAssured Static Variables are set in RestAssuredStaticVariables1 class
	@Test
	public void createAuth()
	{
		RestAssured.given().basePath("/auth")
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
		.when().post().then().log().all();
	}
}
