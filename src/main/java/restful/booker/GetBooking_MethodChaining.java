package restful.booker;

import io.restassured.RestAssured;

public class GetBooking_MethodChaining {
	public static void main(String[] args) {
		RestAssured
		.given().baseUri("https://restful-booker.herokuapp.com").basePath("/booking/{id}").pathParam("id", 1)
		.when().get()
		.then().log().all().statusCode(200);
	}
}
