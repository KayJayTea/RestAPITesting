package test_framework;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ReusableMethods {
	public static XmlPath rawToXML(Response r) {
		String raw_resp = r.asString();
		XmlPath xml = new XmlPath(raw_resp);
		
		return xml;
	}
	
	public static JsonPath rawToJSON(Response r) {
		String raw_resp = r.asString();
		JsonPath json = new JsonPath(raw_resp);
		
		return json;
	}

}
