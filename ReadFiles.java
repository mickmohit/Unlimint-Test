package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReadFiles {

	static String readJSON() throws Exception {
		Resource resource1 = new ClassPathResource("/order2.json");
		InputStream inputStream1 = resource1.getInputStream();
		String contentsJson = null;
		
		try(inputStream1) {
			// Read Json
			BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream1));
			contentsJson = reader1.lines()
					.collect(Collectors.joining(System.lineSeparator()));
			return contentsJson;
		}
		catch (IOException e) {
			System.out.println("IOException in try with resource block =>" + e.getMessage());
		}
		return contentsJson;
	}
	
	static JSONArray readCSV() throws Exception {
		InputStream inputStream2 = DemoTestApplication.class.getClassLoader().getResourceAsStream("order1.csv");
		String csvAsString = new BufferedReader(new InputStreamReader(inputStream2)).lines().collect(Collectors.joining("\n"));
		 JSONArray jsonArray = CDL.toJSONArray(csvAsString);
		 System.out.println(jsonArray.toString());
		    int length = jsonArray.length();
		    JSONArray jsonArrayNew = new JSONArray();
		    for (int i = 0; i < length; i++) {
		        JSONObject jsonObject = jsonArray.getJSONObject(i);
		      
		        JSONObject jsonObjectNew = new JSONObject();

		        jsonObjectNew.put("orderId", jsonObject.get("orderId"));
		        jsonObjectNew.put("amount", jsonObject.get("amount"));
		        jsonObjectNew.put("currency", jsonObject.get("currency"));
		        jsonObjectNew.put("comment", jsonObject.get("comment"));
		       
		        jsonArrayNew.put(jsonObjectNew);
		    }
		    return jsonArrayNew;
	}
}
