package restful.booker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetBooking {
	public static void main(String[] args) {
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://restful-booker.herokuapp.com");
		reqSpec.basePath("/booking/{id}");
		
		reqSpec.pathParam("id", 1);
		
		Response resp = reqSpec.when().get();
		ValidatableResponse validateResp = resp.then().log().all();
		validateResp.statusCode(200);
	}
}
