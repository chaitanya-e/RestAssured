package restful.booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateBooking_MethodChaining {

	public static void main(String[] args) {
		// build request
		RestAssured
		.given()
				.log().all()
				.baseUri("https://restful-booker.herokuapp.com") // prints only request details
				.basePath("/booking")
				.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				.contentType(ContentType.JSON)
				//hit request
		.when()
				.post()
				//validate response
		.then()
				.log().all().statusCode(200);

	}

}
