package restful.booker;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecification_MultipleBookings {
	//Identify common request related code in both methods
	//put them inside request specification to make it reusable
	RequestSpecification requestSpec;
	RequestSpecification requestSpec2;
	
	@BeforeClass
	public void createRequestSpec()
	{
		requestSpec = 
				RestAssured.given().log().all();
	}
	
	@BeforeClass
	public void RequestSpecExtension()
	{
		requestSpec2 = RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
				.contentType(ContentType.JSON);
	}
	
	@Test
	public void createBooking() {
		RestAssured
		.given()
				.spec(requestSpec)
				.spec(requestSpec2)
				.basePath("/booking")
				.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				//hit request
		.when()
				.post()
				//validate response
		.then()
				.log().all().statusCode(200);
	}
	
	@Test
	public void updateBooking()
	{
		RestAssured.given()
		.spec(requestSpec) // the request spec can be single or multiple
		.spec(requestSpec2)
		.basePath("booking/1")
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
