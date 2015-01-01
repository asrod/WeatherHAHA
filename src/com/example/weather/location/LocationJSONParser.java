package com.example.weather.location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationJSONParser {
	private String longitude;
	private String latitude;
	private String province;
	private String city;
	
	private String jsonString;
	
	
	public LocationJSONParser(String jasonString)
	{
		
		
		String s=
		this.jsonString=jasonString;
		parse();
		
	}


	public void parse() {
		JSONObject dataJson = null;
		JSONObject response_result=null;
		JSONObject response_addr=null;
		JSONObject response_location=null;
		
		
		String s=jsonString.substring(29,jsonString.length()-1 );
		
		System.out.println(s);
		try {
			System.out.println("FUCKING!!!!!!!!!!!!");
			dataJson = new JSONObject(s);
		    System.out.println("FUCKING!!!!!!!!!!!!");
			response_result=dataJson.getJSONObject("result");
     		response_addr=response_result.getJSONObject("addressComponent");
     		response_location=response_result.getJSONObject("location");
			
			this.latitude=response_location.getString("lat");
			this.longitude=response_location.getString("lng");
			this.province=response_addr.getString("province");
			this.city=response_addr.getString("city");
	
			
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getLatitude(){
		return this.latitude;
	}
	
	public String getLongitude(){
		return this.longitude;
	}
	
	public String getProvince(){
		return this.province;
	}
	
	public String getCity(){
		return this.city;
	}
}
