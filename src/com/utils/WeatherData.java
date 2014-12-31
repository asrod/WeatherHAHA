package com.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class WeatherData {

	private Context context;
	private SharedPreferences sp;
	
	private String city;// 城市名
	private String cityid;//城市id
	private String weather1;// 三日天气
	private String weather2;
	private String weather3;
	private String temp1;// 三日气温
	private String temp2;//
	private String temp3;
	private String wind1;// 三日风力
	private String wind2;//
	private String wind3;// ;
	private String fl1;// 三日风力等级
	private String fl2;//
	private String fl3;//
	private String index_d;// 今日穿衣指数
	private String pm25;//pm2.5指数
	//private String index48_d;// 48小时穿衣指数

	public WeatherData(Context context) {
		this.context = context;
		sp=this.context.getSharedPreferences("weather", 0);
		//getData();
	}

	public void getData() {
		
		this.city = sp.getString("city", "成都");
		this.cityid=sp.getString("cityid", "101270101");
		this.temp1 = sp.getString("temp1", null);
		this.temp2 = sp.getString("temp2", null);
		this.temp3 = sp.getString("temp3", null);
		this.weather1 = sp.getString("weather1", null);
		this.weather2 = sp.getString("weather2", null);
		this.weather3 = sp.getString("weather3", null);
		this.wind1=sp.getString("wind1", null);
		this.wind2=sp.getString("wind2", null);
		this.wind3=sp.getString("wind3", null);
		this.index_d=sp.getString("index_d", null);
//		this.index48_d=sp.getString("index48_d", null);
		this.fl1=sp.getString("wl1", null);
		this.fl2=sp.getString("wl3", null);
		this.fl3=sp.getString("wl2", null);
		this.pm25=sp.getString("pm25", null);
	}

	
	public void edit(String jsonstring ){
		 SharedPreferences.Editor editor=sp.edit();
		 JsonParser parser=new JsonParser(jsonstring);
		 
		 editor.putString("city", parser.getCity());
		 editor.putString("cityid", parser.getCityid());
		 editor.putString("temp1", parser.getTemp1());
		 editor.putString("temp2", parser.getTemp2());
		 editor.putString("temp3", parser.getTemp3());
		 editor.putString("weather1", parser.getWeather1());
		 editor.putString("weather2", parser.getWeather2());
		 editor.putString("weather3", parser.getWeather3());
		 editor.putString("wind1", parser.getWind1());
		 editor.putString("wind2", parser.getWind2());
		 editor.putString("wind3", parser.getWind3());
		 editor.putString("wl1", parser.getWindLevel1());
		 editor.putString("wl2", parser.getWindLevel2());
		 editor.putString("wl3", parser.getWindLevel3());
		 editor.putString("index_d", parser.getDressIndex());
		 editor.putString("pm25", parser.getPm());


		 editor.commit();
		
		 getData();
	}

	public String getCity() {
		return this.city;
	}
	public String getCityid(){
		return this.cityid;
	}

	public String getTemp1() {
		return this.temp1;
	}

	public String getTemp2() {
		return this.temp2;
	}

	public String getTemp3() {
		return this.temp3;
	}

	public String getWeather1() {
		return this.weather1;
	}

	public String getWeather2() {
		return this.weather2;
	}

	public String getWeather3() {
		return this.weather3;
	}

	public String getWind1() {
		return this.wind1;
	}

	public String getWind2() {
		return this.wind2;
	}

	public String getWind3() {
		return this.wind3;
	}

	public String getWindLevel1() {
		return this.fl1;
	}

	public String getWindLevel2() {
		return this.fl2;
	}

	public String getWindLevel3() {
		return this.fl3;
	}
	
	public String getDressIndex(){
		return this.index_d;
	}
	public String getPm25(){
		return this.pm25;
	}
//	public String getDressIndex48(){
//		return this.index48_d;
//	}
	

//	private String city2code(String cityname) {
//		// TODO Auto-generated method stub
//		SQLiteDatabase db = SQLiteDatabase.openDatabase("city.db", null, 0);
//		String sql = "select citycode from city where city=\'" + cityname
//				+ "\'";
//		Cursor c = db.rawQuery(sql, null);
//
//		Log.i("WeatherData", "query----" + sql);
//
//		if (c.moveToNext()) {
//			return c.getString(c.getColumnIndex("citycode"));
//		} else
//			return null;
//	}

}
