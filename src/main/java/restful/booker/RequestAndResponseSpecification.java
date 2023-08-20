package restful.booker;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestAndResponseSpecification {
	ResponseSpecification responseSpec;
	RequestSpecification requestSpec;

	// multiple before class is possible - run in alphabetical order
	// in this case order doesnt matter
	@BeforeClass
	public void setExpectations() {
		responseSpec = RestAssured.expect().statusCode(200).contentType(ContentType.JSON)
				.time(Matchers.lessThan(5000L));
		// long needs L/l as suffix -> 5000l / 5000L
	}

	@BeforeClass
	public void zpreRequisite() {
		requestSpec = RestAssured.given().log().all().baseUri("https://restful-booker.herokuapp.com")
				.contentType(ContentType.JSON)
				.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}").basePath("/booking");
	}

	@Test
	public void createBooking1() {
		RestAssured.given().spec(requestSpec).basePath("/booking")
				.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				// hit request
				.when().post()
				// validate response
				.then().log().all().spec(responseSpec);
	}

	@Test
	public void overloadedGivenMethod() {
		RestAssured.given(requestSpec)
				// .spec(requestSpec)
				.basePath("/booking")
				.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				// hit request
				.when().post()
				// validate response
				.then().log().all().spec(responseSpec);
	}

	@Test
	public void overloadedGiven() {
		 RestAssured
		 .given(requestSpec, responseSpec)
		
				// hit request
				.post()
				// validate response
				.then().log().all();
	}

}
