package restful.booker;

import io.restassured.RestAssured;

public class PATCH_PartialBookingUpdate {
	public static void main(String[] args) {
		RestAssured.given()
		.log()
		.all()
		.baseUri("https://restful-booker.herokuapp.com/")
		.pathParam("bookingId",2)
		.basePath("booking/{bookingId}")
		.body("{\r\n"
				+ "    \"firstname\" : \"Karthik\",\r\n"
				+ "    \"lastname\" : \"Bo\"\r\n"
				+ "}")
		.header("content-type","application/json")
		.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
		.when()
		.patch()
		.then()
		.log()
		.all();
	}
}
