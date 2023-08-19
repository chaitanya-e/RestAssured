package restful.booker;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;

public class PathParameterExample {
	public static void main(String[] args) {
		RestAssured.given().log().all().baseUri("https://restful-booker.herokuapp.com/")
				.basePath("{basepath}/{bookingId}").pathParam("basepath", "booking").pathParam("bookingId", 2)

				.when().get()

				.then().log().all().statusCode(200);
		System.out.println("1-----------------------------------------");

		RestAssured.given().log().all().when()
				.get("https://restful-booker.herokuapp.com/{basepath}/{bookingid}", "booking", 2).then().log().all();
		System.out.println("2-----------------------------------------");

		RestAssured.given().log().all().pathParam("basepath", "booking").when()
				.get("https://restful-booker.herokuapp.com/{basepath}/{bookingid}", "token", 2).then().log().all();
		// https://restful-booker.herokuapp.com/booking/token ---> pathParam() is given
		// priority over inline params
		// 2 is ignored
		System.out.println("3-----------------------------------------");

		RestAssured.given().log().all().when()
				// .get("https://restful-booker.herokuapp.com/{basepath}/{bookingid}","token",2,3)
				.then().log().all();
		// java.lang.IllegalArgumentException: Invalid number of path parameters.
		// Expected 2, was 3.
		// Redundant path parameters are: 3.
		System.out.println("4-----------------------------------------");

		RestAssured.given().log().all().when()
				// .get("https://{baseURL}-booker.herokuapp.com/{basepath}/{bookingid}","token",2,3)
				.then().log().all();
		// java.net.URISyntaxException: Illegal character in authority at
		// index 8: https://{baseURL}-booker.herokuapp.com/token/2
		System.out.println("5-----------------------------------------");

		RestAssured.given().log().all().pathParam("baseURL", "restful").when()
				// .get("https://{baseURL}-booker.herokuapp.com/{basepath}/{bookingid}","token",2,3)
				.then().log().all();
		// java.net.URISyntaxException: Illegal character in authority at
		// index 8: https://{baseURL}-booker.herokuapp.com/token/2
		System.out.println("6-----------------------------------------");

		RestAssured.given().log().all().pathParam("baseURL", "restful").when()
				// .get("https:/{baseURL}-booker.herokuapp.com/{basepath}/{bookingid}","token",2)
				.then().log().all();
		System.out.println("7-----------------------------------------");
		// http://localhost:8080/https%3A/restful-booker.herokuapp.com/token/2 -->
		// pathParams get added only after single /

		Map<String, String> pathParams = new HashMap<String, String>();
		pathParams.put("basepath", "booking");
		pathParams.put("bookingId", "2");

		RestAssured.given().log().all().baseUri("https://restful-booker.herokuapp.com/")
				.basePath("{basepath}/{bookingId}").pathParams(pathParams)
				.when().get()
				.then().log().all().statusCode(200);
		System.out.println("8-----------------------------------------");
		
		
		Map<String, String> pathParams2 = new HashMap<String, String>();
		pathParams.put("basepath1", "booking");
		pathParams.put("bookingId", "2");

		RestAssured.given().log().all().baseUri("https://restful-booker.herokuapp.com/")
				.basePath("{basepath}/{bookingId}").pathParams(pathParams2)
				.when().get()
				.then().log().all().statusCode(200);
		//java.lang.IllegalArgumentException: Invalid number of path parameters. 
		// Expected 2, was 0. Undefined path parameters are: basepath, bookingId
		System.out.println("9-----------------------------------------");
	}
}
