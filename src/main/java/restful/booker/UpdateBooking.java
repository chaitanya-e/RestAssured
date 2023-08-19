package restful.booker;

import io.restassured.RestAssured;

public class UpdateBooking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.given()
		.log().all().baseUri("https://restful-booker.herokuapp.com/").basePath("booking/1")
		.contentType("application/json")
		.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
		.body("{\r\n"
				+ "    \"firstname\" : \"Kathy\",\r\n"
				+ "    \"lastname\" : \"Sierra\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}")
		.when()
		.put()
		.then()
		.assertThat()
		.statusCode(200);
		
		
	}

}
