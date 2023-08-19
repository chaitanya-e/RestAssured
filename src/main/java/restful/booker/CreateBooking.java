package restful.booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
public class CreateBooking {
	
	public static void main(String[] args) {
		//build request
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.log().all(); //prints only request details in console
		requestSpec.baseUri("https://restful-booker.herokuapp.com");
		requestSpec.basePath("/booking");
		requestSpec.body("{\r\n"
				+ "    \"firstname\" : \"Jim\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}");
		requestSpec.contentType(ContentType.JSON);
		
		 
		//hit and get response
		Response response = requestSpec.post();
		
		
		//validate response
		ValidatableResponse validatableResp = response.then();
		validatableResp.log().all(); //prints response details in console
		validatableResp.statusCode(200);
		//validatableResp.statusCode(400);
		
		//this is another dummy comment to test git
	}

}
