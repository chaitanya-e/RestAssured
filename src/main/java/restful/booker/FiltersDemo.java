package restful.booker;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class FiltersDemo {
	@Test
	public void findAll() {
		File jsonFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\JsonSamples\\Json1.json");
		JsonPath jp = new JsonPath(jsonFile);

		// labs available today
		System.out.println("all lab details - " + jp.getList("labs"));
		System.out.println("Open today labs - " + jp.getList("labs.findAll{it.time == 'Today' }.name"));
		
		System.out.println("Imaging - "+jp.get("labs.find{it.name=='BNP'}.location"));

		System.out.println("Name - " + jp.getList("name")); // null

		System.out.println("labs.name - " + jp.getList("labs.name"));

		System.out.println("medications.aceinhibitors.name - " + jp.getList("medications.aceInhibitors.name"));
		/*
		 * { "medications":[{ "aceInhibitors":[{ "name":"lisinopril",
		 * "strength":"10 mg Tab", "dose":"1 tab", "route":"PO", "sig":"daily",
		 * "pillCount":"#90", "refills":"Refill 3" }, { "name":"lisinopril2",
		 * "strength":"10 mg Tab2", "dose":"1 tab2", "route":"PO2", "sig":"weekly",
		 * "pillCount":"#90", "refills":"Refill 4" }],
		 * 
		 * 
		 * [{aceInhibitors=[{name=lisinopril, strength=10 mg Tab, dose=1 tab, route=PO,
		 * sig=daily, pillCount=#90, refills=Refill 3}, {name=lisinopril2, strength=10
		 * mg Tab2, dose=1 tab2, route=PO2, sig=weekly, pillCount=#90, refills=Refill
		 * 4}], antianginal=[{name=nitroglycerin, strength=0.4 mg Sublingual Tab, dose=1
		 */

		// "medications.findAll{it.}{findAll{it.sig=='daily'}.name" //ace:[],ace2:[]

		Map<String, Object> medications1 = jp.getMap("medications[0]");
		List<Object> medications = jp.getList("medications");
		medications1.forEach((key, value) -> {
			System.out.println("value.tostring  ="+value.toString());
			JsonPath temp = new JsonPath(value.toString());
			
			//System.out.println(temp.getList("findAll{it.sig=='daily'}.name").toString());
		});
		
		String str = "[{name=lisinopril, strength=10 mg Tab, dose=1 tab, route=PO, sig=daily, pillCount=#90, refills=Refill 3},{name=lisinopril2, strength=10 mg Tab2, dose=1 tab2, route=PO2, sig=weekly, pillCount=#90, refills=Refill 4}]";
		JsonPath t = new JsonPath(str);
		System.out.println(t.get("[0]").toString());
		// Collection c = medications.values();

		// System.out.println("sig daily names - " +
		// jp.getList("medications.findAll(it.)"));
	}
}
