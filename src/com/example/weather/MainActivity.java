package com.example.weather;

import com.example.weather.R;
import com.example.weather.location.Common;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.example.weather.location.*;
import com.utils.DBManager;
import com.utils.HttpDownloadHelpers;
import com.utils.WeatherData;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.weather.location.LocationJSONParser;
import com.example.weather.location.Common;
import com.example.weather.location.LocationSvc;


public class MainActivity extends Activity {

	HttpDownloadHelpers helper = null;
	MyReceiver receiver;

	TextView tv_city = null;
	TextView tv_temp1 = null;
	TextView tv_weather1 = null;
	TextView tv_index_d = null;
	TextView tv_wind1_and_fl1 = null;
	TextView tv_temp2 = null;
	TextView tv_temp3 = null;
	TextView tv_weather2 = null;
	TextView tv_weather3 = null;
	TextView tv_pm=null;
	ButtonFloatSmall btn_locate=null;
	
	private ProgressDialog dialog;
	private double latitude;//纬度
	private double longitude;//经度
    private myThread thread_location;//定位线程
	Handler handler = null;
	String latitude_string=null;//经度的字符串类型
	String longitude_string=null;//纬度的字符串类型
	String city=null;
	String ss;//JSONdata

	// AutoCompleteTextView edt_city = null;
	ImageButton btn_update = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newmain);

		// 初始化
		init();
		setData();

		// 接收mainService的广播
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.example.html.MainService");
		MainActivity.this.registerReceiver(receiver, filter);

		

	}

	// 初始化： findviewbyid 取得当前城市及天气 显示在textview
	private void init() {
		// TODO Auto-generated method stub
		tv_city = (TextView) findViewById(R.id.tv_city);
		tv_weather1 = (TextView) findViewById(R.id.tv_weather1);
		tv_weather2 = (TextView) findViewById(R.id.tv_weather2);
		tv_weather3 = (TextView) findViewById(R.id.tv_weather3);
		tv_temp1 = (TextView) findViewById(R.id.tv_temp1);
		tv_temp2 = (TextView) findViewById(R.id.tv_temp2);
		tv_temp3 = (TextView) findViewById(R.id.tv_temp3);
		tv_wind1_and_fl1 = (TextView) findViewById(R.id.tv_wind1_and_fl1);
		tv_index_d = (TextView) findViewById(R.id.tv_index);
		tv_pm=(TextView) findViewById(R.id.tv_pm);
		// edt_city = (AutoCompleteTextView) findViewById(R.id.edit_city);
		btn_update = (ImageButton) findViewById(R.id.btn_update);
		btn_locate=(ButtonFloatSmall) findViewById(R.id.buttonFloatSmall);
		handler = new MyHandler();
		tv_city.setOnClickListener(new ClickCityListener());
		btn_update.setOnClickListener(new UpdateButtonListener());
		btn_locate.setOnClickListener(new LocationButtonListener());
		// edt_city.setThreshold(1);
		// edt_city.setAdapter(new CityAdapter(MainActivity.this, null, 0));
	}

	private void setData() {
		WeatherData data = new WeatherData(getApplicationContext());
		data.getData();
		// tv_currentcity.setText("当前城市："+ data.getCity());
		// tv_currentweather.setText(data.getTemp1()+"---"+data.getWeather1());
		tv_city.setText(data.getCity());
		tv_weather1.setText(data.getWeather1());
		tv_weather2.setText(data.getWeather2());
		tv_weather3.setText(data.getWeather3());
		tv_temp1.setText(data.getTemp1()+"°");
		tv_temp2.setText(data.getTemp2());
		tv_temp3.setText(data.getTemp3());
		tv_wind1_and_fl1.setText(data.getWind1());
		tv_index_d.setText(data.getDressIndex());
		tv_pm.setText(data.getPm25());

	}

	class ClickCityListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// edt_city.setVisibility(View.VISIBLE);
			showSelectCityDialog();

		}
	}
	
	class LocationButtonListener implements OnClickListener{
		
		

		@Override
		public void onClick(View v) {
			
			
			// 注册广播
			IntentFilter filter = new IntentFilter();
			filter.addAction(Common.LOCATION_ACTION);
			MainActivity.this.registerReceiver(new LocationBroadcastReceiver(), filter);
			
			// 启动服务
			Intent intentLocationService = new Intent();
			intentLocationService.setClass(MainActivity.this, LocationSvc.class);
			startService(intentLocationService);
			
			// 等待提示
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setMessage("正在定位...");
			dialog.setCancelable(true);
			dialog.show();
			
			
		}
		
	}

	class UpdateButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			//检查网络连接
			if(isNetworkConnected(getApplicationContext())){
			
			// 图标变换
			Message msg = handler.obtainMessage();
			msg.arg1 = 1;
			handler.sendMessageDelayed(msg, 200);
			msg = handler.obtainMessage();
			msg.arg1 = 2;
			handler.sendMessageDelayed(msg, 400);
			}else {
				Toast.makeText(getApplicationContext(), "无网络连接", Toast.LENGTH_SHORT).show();;
			}
		}

	}

	class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub

			if (msg.arg1 == 1) {
				btn_update.setImageResource(R.drawable.update2);

				WeatherData data = new WeatherData(getApplicationContext());
				data.getData();
				String cityid = data.getCityid();
				Intent intent = new Intent(getApplicationContext(),
						MainService.class);
				intent.putExtra("cityid", cityid);
				startService(intent);
			}

			if (msg.arg1 == 2)
				btn_update.setImageResource(R.drawable.update1);

			if (msg.arg1 == 3) {
				String city = msg.getData().getString("city");
				String cityid =squeryCityid(city );
				Intent intent = new Intent(getApplicationContext(),
						MainService.class);
				intent.putExtra("cityid", cityid);
				startService(intent);
				
			}

			super.handleMessage(msg);
		}

		private String squeryCityid(String city) {
			// TODO Auto-generated method stub
			
			SQLiteDatabase db=openOrCreateDatabase("city.db", MODE_PRIVATE, null);
			String sql="select code from city where city=\'"+city+"\'";	
			System.out.println("sql==="+sql);
			Cursor c=db.rawQuery(sql, null);		
			if(c.getCount()<=0){
				return null;
			}
			else{			
				c.moveToFirst();
				return c.getString(c.getColumnIndex("code"));
			}		
			
		}
	}

	public class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			setData();
			//广播到widget以更新信息
			
			sendBroadcast2Widget();
		

		}
	}

	public void showSelectCityDialog() {
		// TODO Auto-generated method stub

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(R.layout.select_city_dialog,
				null);
		// builder.setIcon(R.drawable.icon);

		builder.setTitle("更改城市");
		builder.setView(textEntryView);
		final AutoCompleteTextView edit_select_city = (AutoCompleteTextView) textEntryView
				.findViewById(R.id.edit_select_city);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				Bundle data = new Bundle();
				data.putString("city", edit_select_city.getText().toString());
				Message m = handler.obtainMessage();
				m.setData(data);
				m.arg1 = 3;
				handler.sendMessage(m);
				
				
				Toast.makeText(getApplicationContext(),
						"更改城市为 " + edit_select_city.getText().toString(),
						Toast.LENGTH_SHORT).show();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

			}
		});
		builder.create().show();

		edit_select_city.setThreshold(1);
		edit_select_city
				.setAdapter(new CityAdapter(MainActivity.this, null, 0));
		edit_select_city.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				InputMethodManager im;
				im = (InputMethodManager) MainActivity.this
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				im.hideSoftInputFromWindow(edit_select_city.getWindowToken(), 0);
			}
		});
	}

	// autocompletetextview 的 adapter
	class CityAdapter extends CursorAdapter {
		private int columnIndex;

		public CityAdapter(Context context, Cursor c, int col) {
			super(context, c);
			this.columnIndex = col;

			Log.i("CityAdapter", "column index----" + columnIndex);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			final LayoutInflater inflater = LayoutInflater.from(context);
			final TextView view = (TextView) inflater.inflate(
					android.R.layout.simple_list_item_1, parent, false);
			return view;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			((TextView) view).setText(cursor.getString(columnIndex));
		}

		@Override
		public String convertToString(Cursor cursor) {
			Log.i("info", " convertToString ");
			return cursor.getString(columnIndex);
		}

		@Override
		public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
			if (constraint != null) {
				DBManager dbhelper = new DBManager(MainActivity.this);
				dbhelper.openDatabase();
				return dbhelper.queryCity(constraint.toString());
				// 从表city查询
			} else {
				return null;
			}

		}
	}

	
	//检测网络状态
	public boolean isNetworkConnected(Context context) { 
		if (context != null) { 
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
		.getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
		if (mNetworkInfo != null) { 
		return mNetworkInfo.isAvailable(); 
		} 
		} 
		return false; 
	}	

	private class LocationBroadcastReceiver extends BroadcastReceiver {
		


		@Override
		public void onReceive(Context context, Intent intent) {
			if (!intent.getAction().equals(Common.LOCATION_ACTION)) return;
			String locationInfo = intent.getStringExtra(Common.LOCATION);
//			text.setText(locationInfo);
			
			System.out.println("locationinfo="+locationInfo);
			dialog.dismiss();
			latitude_string=intent.getStringExtra("latitude"); //从intent读取经纬度数据
			longitude_string=intent.getStringExtra("longitude");
//			System.out.println("latitude="+lat);
//			System.out.println("longitude="+log);
			
			latitude=Double.valueOf(latitude_string).doubleValue(); //将字符串类型转化为double类型
			longitude=Double.valueOf(longitude_string).doubleValue();
			
			System.out.println("latitude="+latitude);
			System.out.println("longitude="+longitude);
			
			thread_location=new myThread();
			thread_location.start();
			try {
				thread_location.sleep(3000);
//				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ss);

				System.out.println("city="+city);
				tv_pm.setText(city);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			MainActivity.this.unregisterReceiver(this);// 不需要时注销
		}
	}
	
	
	public void sendBroadcast2Widget(){
		 Intent intent=new Intent();
	     intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
	     sendBroadcast(intent);
	}
	
	@Override
	protected void onDestroy() {
	// TODO Auto-generated method stub
	unregisterReceiver(receiver);
	
	super.onDestroy();
}
	
	
	class myThread extends Thread {
		HttpDownloadHelpers down;
		
//
//		public myThread(double longitude, double latitude) {
//				latitude = latitude;
//				longitude = longitude;
//
//		}

		public void run() {

			down = new HttpDownloadHelpers();
//			
//			System.out.println("thread_latitude="+latitude);
//			System.out.println("thread_longitude="+longitude);
			String urlStr = "http://api.map.baidu.com/geocoder/v2/?ak=EEd58a36c014b62013f94be93637c14d&callback=renderReverse&location="
					+ latitude_string + "," + longitude_string + "&output=json&pois=1";
			ss = down.download(urlStr);
			// Log.e("aaa", ss);
	//		System.out.println(ss);
			LocationJSONParser locationJSONParse=new LocationJSONParser(ss);
			city=locationJSONParse.getCity();

			// tv3.setText(ss);
		}

		public String getString() {
			return ss;
		}
	}

	
}
