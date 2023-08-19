package restful.booker;

import io.restassured.RestAssured;

public class DeleteBooking {
	public static void main(String[] args) {
		RestAssured.given().log().all().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=").pathParam("id", 2)
				.baseUri("https://restful-booker.herokuapp.com/").basePath("booking/{id}")
				.when().delete().then().log()
				.all().assertThat().statusCode(201);
		
		//this may give 405 method not allowed - this error is due to calling delete on non-existent booking ID
		// change ID of resource to be deleted and it works fine
	}
}
