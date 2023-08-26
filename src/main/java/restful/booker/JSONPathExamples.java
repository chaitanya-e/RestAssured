package restful.booker;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class JSONPathExamples {
	@Test
	public void test1()
	{
		String json = "{\r\n"
				+ "  \"firstname\" : \"john\",\r\n"
				+ "  \"secondname\" :\"merin\",\r\n"
				+ "  \"salary\" : 10.5\r\n"
				+ "}";
		
		JsonPath jp = new JsonPath(json);
		System.out.println(jp.getString("$.firstname")); // prints null
		//System.out.println(jp.getInt("$.firstname")); // Null pointer exception
		//only for strings println can print null in console
		// for others it leads to null pointer exception
		
		System.out.println(jp.getString("firstname"));
		String s = jp.getString("salary");
		System.out.println(jp.getString("salary"));
		//System.out.println(jp.getInt("firstname")); // Number Format Exception -> reading String as int
		//String s = "amod";
		//int i = Integer.parseInt(s); //Number format exception. Salary is float value here
		System.out.println(jp.get("firstname").toString());
		
		System.out.println(jp.get("$").toString()); //$ prints root node
		System.out.println(jp.getString("$")); //prints node as array type between [ and ]
		System.out.println((Object)jp.get()); //prints as json object
		System.out.println(jp.getString("")); // printing node as Array type between [ and ]
	}
	
	@Test
	public void example2()
	{
		String json = "{\r\n"
				+ "  \"firstname\" : \"john\",\r\n"
				+ "  \"secondname\" :\"merin\",\r\n"
				+ "  \"salary\" : 10.5,\r\n"
				+ "  \"isAthlete\":false,\r\n"
				+ "  \"age\":35,\r\n"
				+ "  \"address\":\r\n"
				+ "  {\r\n"
				+ "    \"city\":\"hyderabad\",\r\n"
				+ "    \"street\":{\r\n"
				+ "      \"lane\":10,\r\n"
				+ "      \"landmark\":\"gouri press\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}";
		
		JsonPath jp = new JsonPath(json);
		
		System.out.println(jp.getString("firstname"));
		
		System.out.println(jp.getFloat("salary"));
		
		System.out.println(jp.getBoolean("isAthlete"));
		
		System.out.println(jp.getInt("age"));
		
		System.out.println(jp.get("address.street.lane").toString());
	}
	
	@Test
	public void jsonArray()
	{
		String json = "[\r\n"
				+ "  1,\r\n"
				+ "  2,\r\n"
				+ "  3,\r\n"
				+ "  4,\r\n"
				+ "  5\r\n"
				+ "]";
		
		JsonPath jp = new JsonPath(json);
		List<Object> li = jp.getList("$");
		System.out.println("Array as list - "+li);
		System.out.println("Size - "+li.size());
		
		int i = jp.getInt("[0]");
		System.out.println(i);
		
		Object o = jp.get("[4]");
		System.out.println(o);
		
		String json2 = "[[\r\n"
				+ "  1,\r\n"
				+ "  2,\r\n"
				+ "  3,\r\n"
				+ "  4,\r\n"
				+ "  5\r\n"
				+ "]]";
		
		jp = new JsonPath(json2);
		List<Object> li2 = jp.getList("$");
		System.out.println("Multi dimension array as list - "+li2);
		System.out.println();
		
		System.out.println("get([0])" + jp.get("[0]"));
		System.out.println("get([0][4])"+jp.get("[0][4]"));
		System.out.println("getList($).get(0) - "+jp.getList("$").get(0));
		System.out.println("array size - "+jp.getList("$").size());
		
		List<Object> internalarr = (List<Object>)(jp.getList("$").get(0));
		System.out.println("Internal arr.size = "+internalarr.size());
	
		
	}
	
	@Test
	public void test4()
	{
		String json="[\r\n"
				+ "  {\r\n"
				+ "    \"name\": \"amod\",\r\n"
				+ "    \"age\": 31,\r\n"
				+ "    \"addresses\": [\r\n"
				+ "      {\r\n"
				+ "        \"city\": \"bhopal\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"city\": \"nepal\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"city1\": \"rohtak\"\r\n"
				+ "      }\r\n"
				+ "    ]\r\n"
				+ "  },\r\n"
				+ "   {\r\n"
				+ "    \"name\": \"krupa\",\r\n"
				+ "    \"age\": 31,\r\n"
				+ "    \"addresses\": [\r\n"
				+ "      {\r\n"
				+ "        \"city\": \"goa\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"city\": \"chennai\"\r\n"
				+ "      }\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "]";
		
		JsonPath jp = new JsonPath(json);
		System.out.println("[0].name=" + jp.getString("[0].name"));
		System.out.println("getlist(name)="+jp.getList("name"));
		
		System.out.println("[1].addresses[1].city="+jp.getString("[1].addresses[1].city"));
		System.out.println("[1].addresses.city="+jp.getList("[1].addresses.city"));
		System.out.println("addresses.city="+jp.getList("addresses.city")); //important
		
		Map<String, String> data = jp.getMap("[1]",String.class,String.class);
		Set<Map.Entry<String,String>> entries = data.entrySet();
		for(Map.Entry<String, String> entry:entries)
		{
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
		
		System.out.println( "complicated = "+(jp.getJsonObject("[1]")));
	}
	
	@Test
	public void filters()
	{
		
	}
}
