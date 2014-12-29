package com.utils;

import org.json.JSONException;
import org.json.JSONObject;

//{
//    "weatherinfo": {
//        "city": "厦门",
//        "city_en": "xiamen",
//        "date_y": "2014年8月7日",
//        "date": "七月十二（末伏）",
//        "week": "星期四",
//        "fchh": "11",
//        "cityid": "101230201",
//        "temp1": "34℃~26℃",
//        "temp2": "34℃~26℃",
//        "temp3": "34℃~26℃",
//        "temp4": "32℃~26℃",
//        "temp5": "32℃~26℃",
//        "temp6": "33℃~26℃",
//        "tempF1": "93.2℉~78.8℉",
//        "tempF2": "93.2℉~78.8℉",
//        "tempF3": "93.2℉~78.8℉",
//        "tempF4": "89.6℉~78.8℉",
//        "tempF5": "89.6℉~78.8℉",
//        "tempF6": "91.4℉~78.8℉",
//        "weather1": "多云",
//        "weather2": "多云",
//        "weather3": "多云",
//        "weather4": "阵雨",
//        "weather5": "阵雨",
//        "weather6": "阵雨",
//        "img1": "1",
//        "img2": "99",
//        "img3": "1",
//        "img4": "99",
//        "img5": "1",
//        "img6": "99",
//        "img7": "3",
//        "img8": "99",
//        "img9": "3",
//        "img10": "99",
//        "img11": "3",
//        "img12": "99",
//        "img_single": "1",
//        "img_title1": "多云",
//        "img_title2": "多云",
//        "img_title3": "多云",
//        "img_title4": "多云",
//        "img_title5": "多云",
//        "img_title6": "多云",
//        "img_title7": "阵雨",
//        "img_title8": "阵雨",
//        "img_title9": "阵雨",
//        "img_title10": "阵雨",
//        "img_title11": "阵雨",
//        "img_title12": "阵雨",
//        "img_title_single": "多云",
//        "wind1": "微风",
//        "wind2": "微风",
//        "wind3": "微风",
//        "wind4": "微风",
//        "wind5": "微风",
//        "wind6": "微风",
//        "fx1": "微风",
//        "fx2": "微风",
//        "fl1": "小于3级",
//        "fl2": "小于3级",
//        "fl3": "小于3级",
//        "fl4": "小于3级",
//        "fl5": "小于3级",
//        "fl6": "小于3级",
//        "index": "炎热",
//        "index_d": "天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。",
//        "index48": "",
//        "index48_d": "",
//        "index_uv": "中等",
//        "index48_uv": "",
//        "index_xc": "较适宜",
//        "index_tr": "较适宜",
//        "index_co": "很不舒适",
//        "st1": "34",
//        "st2": "26",
//        "st3": "34",
//        "st4": "26",
//        "st5": "34",
//        "st6": "26",
//        "index_cl": "较适宜",
//        "index_ls": "适宜",
//        "index_ag": "不易发"
//    }
//}


//{
//    "forecast": {
//        "city": "威海",
//        "city_en": "weihai",
//        "cityid": "101121301",
//        "date": "",
//        "date_y": "2014年09月02日",
//        "fchh": "18",
//        "fl1": "4-5级转5-6级",
//        "fl2": "4-5级",
//        "fl3": "3-4级",
//        "fl4": "3-4级",
//        "fl5": "3-4级",
//        "fl6": "微风",
//        "fx1": "东南风转东北风",
//        "fx2": "北风",
//        "img1": "",
//        "img10": "",
//        "img11": "",
//        "img12": "",
//        "img2": "",
//        "img3": "",
//        "img4": "",
//        "img5": "",
//        "img6": "",
//        "img7": "",
//        "img8": "",
//        "img9": "",
//        "img_single": "",
//        "img_title1": "中到大雨",
//        "img_title10": "多云",
//        "img_title11": "晴",
//        "img_title12": "晴",
//        "img_title2": "小雨",
//        "img_title3": "阴",
//        "img_title4": "多云",
//        "img_title5": "晴",
//        "img_title6": "晴",
//        "img_title7": "多云",
//        "img_title8": "多云",
//        "img_title9": "多云",
//        "img_title_single": "",
//        "index": "较舒适",
//        "index48": "",
//        "index48_d": "",
//        "index48_uv": "",
//        "index_ag": "极易发",
//        "index_cl": "较不宜",
//        "index_co": "舒适",
//        "index_d": "",
//        "index_ls": "不宜",
//        "index_tr": "一般",
//        "index_uv": "最弱",
//        "index_xc": "不宜",
//        "st1": "",
//        "st2": "",
//        "st3": "",
//        "st4": "",
//        "st5": "",
//        "st6": "",
//        "temp1": "20℃~23℃",
//        "temp2": "20℃~27℃",
//        "temp3": "22℃~28℃",
//        "temp4": "22℃~28℃",
//        "temp5": "22℃~28℃",
//        "temp6": "0℃~0℃",
//        "tempF1": "",
//        "tempF2": "",
//        "tempF3": "",
//        "tempF4": "",
//        "tempF5": "",
//        "tempF6": "",
//        "weather1": "中到大雨转小雨",
//        "weather2": "阴转多云",
//        "weather3": "晴",
//        "weather4": "多云",
//        "weather5": "多云",
//        "weather6": "晴",
//        "week": "星期二",
//        "wind1": "东南风转东北风",
//        "wind2": "北风",
//        "wind3": "北风转南风",
//        "wind4": "南风",
//        "wind5": "南风",
//        "wind6": "微风"
//    },
//    "realtime": {
//        "SD": "93%",
//        "WD": "东南风",
//        "WS": "1级",
//        "WSE": "",
//        "city": "",
//        "cityid": "101121301",
//        "isRadar": "1",
//        "radar": "JC_RADAR_AZ9010_JB",
//        "temp": "20",
//        "time": "20:50",
//        "weather": "中到大雨"
//    },
//    "alert": [],
//    "aqi": {
//        "city": "威海",
//        "city_id": "101121301",
//        "pub_time": "2014-09-02 20:00",
//        "aqi": "27",
//        "pm25": "10",
//        "pm10": "19",
//        "so2": "13",
//        "no2": "32",
//        "src": "中国环境监测总站",
//        "spot": ""
//    },
//    "index": [
//        {
//            "code": "fs",
//            "details": "属弱紫外辐射天气，长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。",
//            "index": "弱",
//            "name": "防晒指数",
//            "otherName": ""
//        },
//        {
//            "code": "ct",
//            "details": "建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。",
//            "index": "较舒适",
//            "name": "穿衣指数",
//            "otherName": ""
//        },
//        {
//            "code": "yd",
//            "details": "有降水，且风力很强，推荐您在室内进行低强度运动，建议适当停止户外运动；若坚持户外运动，须注意避雨防风。",
//            "index": "较不宜",
//            "name": "运动指数",
//            "otherName": ""
//        },
//        {
//            "code": "xc",
//            "details": "不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。",
//            "index": "不宜",
//            "name": "洗车指数",
//            "otherName": ""
//        },
//        {
//            "code": "ls",
//            "details": "有降水，不适宜晾晒。若需要晾晒，请在室内准备出充足的空间。",
//            "index": "不宜",
//            "name": "晾晒指数",
//            "otherName": ""
//        }
//    ],
//    "accu_cc": {
//        "EpochTime": "1409661300",
//        "Pressure": "1007.1",
//        "RealFeelTemperature": "22.1",
//        "RelativeHumidity": "78",
//        "UVIndex": "0",
//        "Visibility": "16.1",
//        "WindDirectionDegrees": "135",
//        "WindSpeed": "19.6"
//    },
//    "accu_f5": {
//        "EffectiveEpochDate": "1409655600",
//        "DailyForecasts": [
//            {
//                "EpochDate": "1409612400",
//                "Sun_EpochRise": "1409606520",
//                "Sun_EpochSet": "1409653200",
//                "PrecipitationProbability": "25"
//            },
//            {
//                "EpochDate": "1409698800",
//                "Sun_EpochRise": "1409692980",
//                "Sun_EpochSet": "1409739540",
//                "PrecipitationProbability": "61"
//            },
//            {
//                "EpochDate": "1409785200",
//                "Sun_EpochRise": "1409779440",
//                "Sun_EpochSet": "1409825820",
//                "PrecipitationProbability": "0"
//            },
//            {
//                "EpochDate": "1409871600",
//                "Sun_EpochRise": "1409865840",
//                "Sun_EpochSet": "1409912160",
//                "PrecipitationProbability": "0"
//            },
//            {
//                "EpochDate": "1409958000",
//                "Sun_EpochRise": "1409952300",
//                "Sun_EpochSet": "1409998440",
//                "PrecipitationProbability": "1"
//            }
//        ]
//    },
//    "today": {
//        "cityCode": "101121301",
//        "date": "2014-09-02",
//        "humidityMax": 94,
//        "humidityMin": 64,
//        "precipitationMax": 1.4,
//        "precipitationMin": 0,
//        "tempMax": 24,
//        "tempMin": 20,
//        "weatherEnd": "中到大雨",
//        "weatherStart": "多云",
//        "windDirectionEnd": "东南风",
//        "windDirectionStart": "南风",
//        "windMax": 3,
//        "windMin": 0
//    },
//    "yestoday": {
//        "cityCode": "101121301",
//        "date": "2014-09-01",
//        "humidityMax": 92,
//        "humidityMin": 70,
//        "precipitationMax": 0,
//        "precipitationMin": 0,
//        "tempMax": 26,
//        "tempMin": 20,
//        "weatherEnd": "多云",
//        "weatherStart": "多云",
//        "windDirectionEnd": "南风",
//        "windDirectionStart": "西南风",
//        "windMax": 3,
//        "windMin": 0
//    }
//}


public class JsonParser {

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

	private String jsonstring;

	public JsonParser(String jsonstring) {
		this.jsonstring = jsonstring;
		parse();
	}

	private void parse() {
		JSONObject dataJson = null;
		JSONObject response = null;

		try {
			dataJson = new JSONObject(jsonstring);
			response = dataJson.getJSONObject("forecast");

			this.city = response.getString("city");
			this.cityid=response.getString("cityid");
			this.temp1 = response.getString("temp1");
			this.temp2 = response.getString("temp2");
			this.temp3 = response.getString("temp3");
			this.weather1 = response.getString("weather1");
			this.weather2 = response.getString("weather1");
			this.weather3 = response.getString("weather1");
			this.wind1 = response.getString("wind1");
			this.wind2 = response.getString("wind2");
			this.wind3 = response.getString("wind3");
			this.fl1 = response.getString("fl1");
			this.fl2 = response.getString("fl2");
			this.fl3 = response.getString("fl3");
			this.index_d = response.getString("index_d");


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(city + temp1 + weather1);
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

}
