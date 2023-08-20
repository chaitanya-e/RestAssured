package restful.booker;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAssuredStaticVariables1 {
	@BeforeTest
	public void setExpectations() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RestAssured.basePath = "/booking";
		RestAssured.requestSpecification = RestAssured.given().log().all().contentType(ContentType.JSON);
		RestAssured.responseSpecification = RestAssured.expect().statusCode(200)
				.time(Matchers.lessThan(5000L));
		// long needs L/l as suffix -> 5000l / 5000L
	}

	

	@Test
	public void createBooking1() {
		RestAssured.given()
		.body("{\r\n"
				+ "    \"firstname\" : \"Jim\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}")
				// hit request
				.when().post()
				// validate response
				.then().log().all();
	}

	@Test
	public void overloadedGivenMethod() {
		RestAssured.given()
				.body("{\r\n" + "    \"firstname\" : \"Korim\",\r\n" + "    \"lastname\" : \"Green\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				// hit request
				.when().post()
				// validate response
				.then().log().all();
	}
}
