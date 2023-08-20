package restful.booker;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecification_MultipleBooking {
	ResponseSpecification responseSpec;
	
	@BeforeClass
	public void setExpectations()
	{
		responseSpec = RestAssured.expect().statusCode(200).contentType(ContentType.JSON)
				.time(Matchers.lessThan(5000L));
		//long needs L/l as suffix -> 5000l / 5000L
	}
	
	@Test
	public void createBooking1() {
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
				.log().all().spec(responseSpec);
	}
	
	@Test
	public void createBooking2()
	{
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
				.log().all().spec(responseSpec);
	}
}
