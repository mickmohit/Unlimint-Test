package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseFiles {

	 static void parse(JSONArray jsonArrayNew, String filename) {
		// TODO Auto-generated method stub
		List<JSONObject> res=  new ArrayList<>();
		 for (int i = 0; i < jsonArrayNew.length(); i++) {
			 JSONObject ji = jsonArrayNew.getJSONObject(i);
			 JSONObject jo = new JSONObject();
			 jo.put("result", "OK");
				jo.put("line", 1);
				jo.put("filename", filename);
				jo.put("comment", ji.get("comment"));
				jo.put("amount", ji.getFloat("amount"));
				jo.put("orderId", ji.getInt("orderId"));
				jo.put("id", ji.getInt("orderId"));
				res.add(jo);
		 }
		res.forEach(System.out::println);
	}

	static void parse(String contents,String filename) throws Exception {
		JSONObject ji = new JSONObject(contents);		
		JSONObject jo = new JSONObject();
		
		jo.put("result", "OK");
		jo.put("line", 1);
		jo.put("filename", filename);
		jo.put("comment", ji.get("comment"));
		jo.put("amount", ji.getInt("amount"));
		jo.put("orderId", ji.getInt("orderId"));
		jo.put("id", ji.getInt("orderId"));
	
		System.out.println(jo.toString());
	}
}
