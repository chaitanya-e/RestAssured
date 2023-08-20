package restful.booker;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MeasureResponseTime {
public static void main(String[] args) {
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
	
	long responseTime = response.time();
	System.out.println("response.time() = "+responseTime);
	
	long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);
	System.out.println("response.timeIn(TimeUnit.SECONDS) = "+responseTimeInSeconds);
	
	long responseTime2  = response.getTime();
	System.out.println("response.getTime() = "+responseTime2);
	
	long responseTimeInSeconds2 = response.getTimeIn(TimeUnit.SECONDS);
	System.out.println("response.getTimeIn(TimeUnit.SECONDS) = "+responseTimeInSeconds2);
	
	response.then().time(Matchers.lessThan(5000L));
	System.out.println("Response is less than 5000 ms");
	
	response.then().time(Matchers.greaterThan(3000L));
	System.out.println("Response is greater than 3000 ms");
	
	response.then().time(Matchers.both(Matchers.lessThan(5000L)).and(Matchers.greaterThan(3000L)));
	System.out.println("Response time is greater than 3000ms and less than 5000 ms");
	
	// assertion failure does not execute any other statements below
	//response.then().time(Matchers.lessThan(1L),TimeUnit.SECONDS);
	//System.out.println("Failure case assert response < 1s");
	
	response.then().time(Matchers.lessThan(5L),TimeUnit.SECONDS);
	System.out.println("Response is less than 5 Seconds");
}
}
