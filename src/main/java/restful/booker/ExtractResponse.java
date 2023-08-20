package restful.booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ExtractResponse {

	public static void main(String[] args) {
		// build request
		Response response = RestAssured
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
				.post();
				//validate response
		String responseAsString = response.then()
				.extract().response().asString();
		
		System.out.println("String : "+responseAsString);
		
		String responseAsPrettyString = response.then().extract().response().asPrettyString();
		System.out.println("PrettyString : "+responseAsPrettyString);

	}

}
