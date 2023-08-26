package restful.booker;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.HeaderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class HeadersExamples {
	@Test
	public void example1()
	{
		RestAssured.given()
		.header("header1","value1")
		.log().all()
		.when()
		.get()
		.then();
	}
	
	@Test
	public void example2()
	{
		Header header = new Header("header4","value7");
		Header header2 = new Header("header4","value8");
		Header header3 = new Header("header5","value9");
		Header header4 = new Header("header6","value10");
		
		Map<String,String> hm = new HashMap<String, String>();
		hm.put("header7", "value11");
		hm.put("header7", "value12");
		hm.put("header8","value13");
		
		Headers headersCon = Headers.headers(header3, header4);
		
		RestAssured.given()
		.header("header1","value1")
		.header("header2","value2")
		.header("header2","value3")
		.header("header3","value4","value5","value6")
		.header(header)
		.header(header2)
		.headers(headersCon)
		.headers(hm)
		//.headers("header9", "value14", "value16") 
		//java.lang.IllegalArgumentException: You must supply the same number of keys as values.
		.headers("header9", "value14", "header10","value15")
		.log().all()
		.when()
		.get()
		.then();
	}
	
	@Test
	public void overwriteHeaderWithName()
	{
		//All headers are added
		/* 
		 * header1=val2
				header1=val3
				header2=val4
				header2=val5
		 * */
		RestAssured.given()
		.header("header1","val2","val3")
		.header("header2","val4")
		.header("header2","val5")
		.log()
		.all()
		.when()
		.get()
		.then()
		.log()
		.all();
	}
	
	@Test
	public void overwriteHeaderWithName1()
	{
		//Common headers are overwritten with most recent version
		
		//Each time config() is called - it overwrites any previous config
		RestAssured.given()
		.header("header1","val2","val3")
		.header("header2","val4")
		.header("header2","val5")
		/* Comment other configs to get this result
		 * header1=val3
				header2=val5
		 * */
		.config(RestAssuredConfig.config()
				.headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("header1","header2")))
		
		
		
		/* 
		 * This is same as default case refer to above @Test - Merge means it adds all headers
		 * header1=val2
				header1=val3
				header2=val5
				header2=val5
		 * 
		 * */
		.config(RestAssuredConfig.config().headerConfig(HeaderConfig.headerConfig()
				.mergeHeadersWithName("header1")))
		
		

		/* 
		 * 
		 * header1=val2
				header1=val3
				header2=val5
		 * 
		 * */
		.config(RestAssuredConfig.config().headerConfig(HeaderConfig.headerConfig()
				.mergeHeadersWithName("header1").overwriteHeadersWithName("header2")))
		.log()
		.all()
		.when()
		.get()
		.then()
		.log()
		.all();
	}
}
