package com.utils;

import org.json.JSONException;
import org.json.JSONObject;

//{
//    "weatherinfo": {
//        "city": "����",
//        "city_en": "xiamen",
//        "date_y": "2014��8��7��",
//        "date": "����ʮ����ĩ����",
//        "week": "������",
//        "fchh": "11",
//        "cityid": "101230201",
//        "temp1": "34��~26��",
//        "temp2": "34��~26��",
//        "temp3": "34��~26��",
//        "temp4": "32��~26��",
//        "temp5": "32��~26��",
//        "temp6": "33��~26��",
//        "tempF1": "93.2�H~78.8�H",
//        "tempF2": "93.2�H~78.8�H",
//        "tempF3": "93.2�H~78.8�H",
//        "tempF4": "89.6�H~78.8�H",
//        "tempF5": "89.6�H~78.8�H",
//        "tempF6": "91.4�H~78.8�H",
//        "weather1": "����",
//        "weather2": "����",
//        "weather3": "����",
//        "weather4": "����",
//        "weather5": "����",
//        "weather6": "����",
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
//        "img_title1": "����",
//        "img_title2": "����",
//        "img_title3": "����",
//        "img_title4": "����",
//        "img_title5": "����",
//        "img_title6": "����",
//        "img_title7": "����",
//        "img_title8": "����",
//        "img_title9": "����",
//        "img_title10": "����",
//        "img_title11": "����",
//        "img_title12": "����",
//        "img_title_single": "����",
//        "wind1": "΢��",
//        "wind2": "΢��",
//        "wind3": "΢��",
//        "wind4": "΢��",
//        "wind5": "΢��",
//        "wind6": "΢��",
//        "fx1": "΢��",
//        "fx2": "΢��",
//        "fl1": "С��3��",
//        "fl2": "С��3��",
//        "fl3": "С��3��",
//        "fl4": "С��3��",
//        "fl5": "С��3��",
//        "fl6": "С��3��",
//        "index": "����",
//        "index_d": "�������ȣ������Ŷ�������ȹ���̿㡢����T�����������ļ���װ��",
//        "index48": "",
//        "index48_d": "",
//        "index_uv": "�е�",
//        "index48_uv": "",
//        "index_xc": "������",
//        "index_tr": "������",
//        "index_co": "�ܲ�����",
//        "st1": "34",
//        "st2": "26",
//        "st3": "34",
//        "st4": "26",
//        "st5": "34",
//        "st6": "26",
//        "index_cl": "������",
//        "index_ls": "����",
//        "index_ag": "���׷�"
//    }
//}


//{
//    "forecast": {
//        "city": "����",
//        "city_en": "weihai",
//        "cityid": "101121301",
//        "date": "",
//        "date_y": "2014��09��02��",
//        "fchh": "18",
//        "fl1": "4-5��ת5-6��",
//        "fl2": "4-5��",
//        "fl3": "3-4��",
//        "fl4": "3-4��",
//        "fl5": "3-4��",
//        "fl6": "΢��",
//        "fx1": "���Ϸ�ת������",
//        "fx2": "����",
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
//        "img_title1": "�е�����",
//        "img_title10": "����",
//        "img_title11": "��",
//        "img_title12": "��",
//        "img_title2": "С��",
//        "img_title3": "��",
//        "img_title4": "����",
//        "img_title5": "��",
//        "img_title6": "��",
//        "img_title7": "����",
//        "img_title8": "����",
//        "img_title9": "����",
//        "img_title_single": "",
//        "index": "������",
//        "index48": "",
//        "index48_d": "",
//        "index48_uv": "",
//        "index_ag": "���׷�",
//        "index_cl": "�ϲ���",
//        "index_co": "����",
//        "index_d": "",
//        "index_ls": "����",
//        "index_tr": "һ��",
//        "index_uv": "����",
//        "index_xc": "����",
//        "st1": "",
//        "st2": "",
//        "st3": "",
//        "st4": "",
//        "st5": "",
//        "st6": "",
//        "temp1": "20��~23��",
//        "temp2": "20��~27��",
//        "temp3": "22��~28��",
//        "temp4": "22��~28��",
//        "temp5": "22��~28��",
//        "temp6": "0��~0��",
//        "tempF1": "",
//        "tempF2": "",
//        "tempF3": "",
//        "tempF4": "",
//        "tempF5": "",
//        "tempF6": "",
//        "weather1": "�е�����תС��",
//        "weather2": "��ת����",
//        "weather3": "��",
//        "weather4": "����",
//        "weather5": "����",
//        "weather6": "��",
//        "week": "���ڶ�",
//        "wind1": "���Ϸ�ת������",
//        "wind2": "����",
//        "wind3": "����ת�Ϸ�",
//        "wind4": "�Ϸ�",
//        "wind5": "�Ϸ�",
//        "wind6": "΢��"
//    },
//    "realtime": {
//        "SD": "93%",
//        "WD": "���Ϸ�",
//        "WS": "1��",
//        "WSE": "",
//        "city": "",
//        "cityid": "101121301",
//        "isRadar": "1",
//        "radar": "JC_RADAR_AZ9010_JB",
//        "temp": "20",
//        "time": "20:50",
//        "weather": "�е�����"
//    },
//    "alert": [],
//    "aqi": {
//        "city": "����",
//        "city_id": "101121301",
//        "pub_time": "2014-09-02 20:00",
//        "aqi": "27",
//        "pm25": "10",
//        "pm10": "19",
//        "so2": "13",
//        "no2": "32",
//        "src": "�й����������վ",
//        "spot": ""
//    },
//    "index": [
//        {
//            "code": "fs",
//            "details": "����������������������ڻ��⣬����Ϳ��SPF��8-12֮��ķ�ɹ����Ʒ��",
//            "index": "��",
//            "name": "��ɹָ��",
//            "otherName": ""
//        },
//        {
//            "code": "ct",
//            "details": "�����ű����ס�����ţ������ȷ�װ������������Ӧ�ʵ����������żп�������ë�µȡ�",
//            "index": "������",
//            "name": "����ָ��",
//            "otherName": ""
//        },
//        {
//            "code": "yd",
//            "details": "�н�ˮ���ҷ�����ǿ���Ƽ��������ڽ��е�ǿ���˶��������ʵ�ֹͣ�����˶�������ֻ����˶�����ע�������硣",
//            "index": "�ϲ���",
//            "name": "�˶�ָ��",
//            "otherName": ""
//        },
//        {
//            "code": "xc",
//            "details": "����ϴ����δ��24Сʱ�����꣬����ڴ��ڼ�ϴ������ˮ��·�ϵ���ˮ���ܻ��ٴ�Ū�����İ�����",
//            "index": "����",
//            "name": "ϴ��ָ��",
//            "otherName": ""
//        },
//        {
//            "code": "ls",
//            "details": "�н�ˮ����������ɹ������Ҫ��ɹ����������׼��������Ŀռ䡣",
//            "index": "����",
//            "name": "��ɹָ��",
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
//        "weatherEnd": "�е�����",
//        "weatherStart": "����",
//        "windDirectionEnd": "���Ϸ�",
//        "windDirectionStart": "�Ϸ�",
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
//        "weatherEnd": "����",
//        "weatherStart": "����",
//        "windDirectionEnd": "�Ϸ�",
//        "windDirectionStart": "���Ϸ�",
//        "windMax": 3,
//        "windMin": 0
//    }
//}


public class JsonParser {

	private String city;// ������
	private String cityid;//����id
	private String weather1;// ��������
	private String weather2;
	private String weather3;
	private String temp1;// ��������
	private String temp2;//
	private String temp3;
	private String wind1;// ���շ���
	private String wind2;//
	private String wind3;// ;
	private String fl1;// ���շ����ȼ�
	private String fl2;//
	private String fl3;//
	private String index_d;// ���մ���ָ��

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
