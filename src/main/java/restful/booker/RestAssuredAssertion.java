package restful.booker;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RestAssuredAssertion {
	@Test
	public void withoutInlineAssertions() {
		String response = RestAssured.given()
		.baseUri("https://restful-booker.herokuapp.com")
		.basePath("/auth")
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
		.when()
		.post()
		.then()
		.extract()
		.response()
		.asString();
		System.out.println("Response = "+response);
		JsonPath jp = new JsonPath(response);
		Assert.assertNull(jp.get("reason"));
		/* java.lang.AssertionError: expected [null] but found [Bad credentials] */
	}
	
	@Test
	public void withInlineAssertion()
	{
		RestAssured.given()
		.baseUri("https://restful-booker.herokuapp.com")
		.basePath("/auth")
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
		.when()
		.post()
		.then()
		//.body("reason", Matchers.nullValue());
		
		/*JSON path reason doesn't match.
Expected: null
  Actual: Bad credentials*/
		.body("reason",Matchers.is("Bad credentials"))
		.body("reason.length()", Matchers.equalTo(15))
	
		/*
		 * java.lang.AssertionError: 1 expectation failed.
JSON path reason.length() doesn't match.
Expected: <12>
  Actual: <15>
		 */
		
		.body("$", Matchers.anything("kad"))
		;
		
	}
	
	@Test
	public void MatchersForArray()
	{
		RestAssured.given()
		.baseUri("https://restful-booker.herokuapp.com")
		.basePath("/booking")
		.when()
		.get()
		.then()
		//.body("bookingid", Matchers.contains(1,2)) //checks if elements match resultant collection - 
		//order should also be matching - use containsAnyOrder(1,2) to match any order
		/*
		 * JSON path bookingid doesn't match.
Expected: iterable containing [<1>, <2>]
  Actual: <[3480, 3478, 2146, 
		 */
		.body("bookingid", Matchers.hasItems(1,2)) //checks if elements are present
		.body("bookingid", Matchers.hasItems(50))
		/*
		 * JSON path bookingid doesn't match.
Expected: (a collection containing <50>)
  Actual: <[455, 1277, 
		 */
		;
	}
}
