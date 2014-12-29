package com.android.example.test2.widget;

import com.example.weather.R;
import com.example.weather.*;
import com.utils.WeatherData;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidget extends AppWidgetProvider {
	@SuppressLint("NewApi")
	@Override
	public void onAppWidgetOptionsChanged(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			Bundle newOptions) {
		// TODO Auto-generated method stub
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId,
				newOptions);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		Log.i("widget", "receive broadcast....");

		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.widget_main);
		
		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		
		WeatherData data = new WeatherData(context);
		 data.getData();
		 views.setTextViewText(R.id.widget_city, data.getCity());
		 views.setTextViewText(R.id.widget_temperature,
		 data.getTemp1());
		 views.setImageViewResource(R.id.imageView1,
		 getImageid(data.getWeather1()));
		
		appWidgetManager.updateAppWidget(new ComponentName(context,
				MyWidget.class), views);

	
	}

	private int getImageid(String weather1) {
		 // TODO Auto-generated method stub
		 if (weather1 == null) {
		 return R.drawable.w1;
		 }
		
		 String weather[] = new String[] { "«Á", "“ı", "∂‡‘∆", "–°”Í", "÷–”Í", "¥Û”Í",
		 "¿◊’Û”Í", "’Û”Í" };
		 int id = -1;
		 for (int i = 0; i < weather.length; i++) {
		 if (weather1.indexOf(weather[i]) == 0) {
		 id = i;
		 break;
		 }
		 }
		 Log.i("weather", id + "");
		 switch (id) {
		 case 0:// «Á
		 return R.drawable.w0;
		 case 1:// “ı
		 return R.drawable.w2;
		 case 2:// ∂‡‘∆
		 return R.drawable.w1;
		 case 3:// –°”Í
		 return R.drawable.w8;
		 case 4:// ÷–”Í
		 return R.drawable.w9;
		 case 5:// ¥Û”Í
		 return R.drawable.w10;
		 case 6:// ¿◊’Û”Í
		 return R.drawable.w4;
		 case 7:// ’Û”Í
		 return R.drawable.w7;
		 case 8://
		 break;
		 default:
		 return R.drawable.w1;
		 }
		 return R.drawable.w1;
		
		 }

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		Intent intent11 = new Intent(context, MainActivity.class);
		PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0,
				intent11, 0);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_main);
		remoteViews.setOnClickPendingIntent(R.id.widget_imageButton1,
				pendingIntent1);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

//		// ∏¸–¬ ˝æ›
//		Intent updateIntent = new Intent(context, MainService.class);
//		updateIntent.addFlags(111);
//
//		updateIntent.putExtra("package name", context.getPackageName());
//		Log.i("package name", context.getPackageName());

//		context.startService(updateIntent);

		// Timer timer = new Timer();
		// timer.scheduleAtFixedRate(new MyTime(context, appWidgetManager), 1,
		// 60000);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	// private class MyTime extends TimerTask {
	// RemoteViews remoteViews;
	// AppWidgetManager appWidgetManager;
	// ComponentName thisWidget;
	// Context context;
	//
	// public MyTime(Context context, AppWidgetManager appWidgetManager) {
	// this.appWidgetManager = appWidgetManager;
	// remoteViews = new RemoteViews(context.getPackageName(),
	// R.layout.widget_main);
	// thisWidget = new ComponentName(context, MyWidget.class);
	// this.context = context;
	// }
	//
	// public void run() {
	//
	// if (!isNetworkConnected(context)) {
	// Log.i("widget", "no network connection...");
	// } else {
	// WeatherData data = new WeatherData(context);
	// data.getData();
	// remoteViews.setTextViewText(R.id.widget_city, data.getCity());
	// remoteViews.setTextViewText(R.id.widget_temperature,
	// data.getTemp1());
	// remoteViews.setImageViewResource(R.id.imageView1,
	// getImageid(data.getWeather1()));
	// appWidgetManager.updateAppWidget(thisWidget, remoteViews);
	// }
	// }
	//
	// private int getImageid(String weather1) {
	// // TODO Auto-generated method stub
	// if (weather1 == null) {
	// return R.drawable.w1;
	// }
	//
	// String weather[] = new String[] { "«Á", "“ı", "∂‡‘∆", "–°”Í", "÷–”Í", "¥Û”Í",
	// "¿◊’Û”Í", "’Û”Í" };
	// int id = -1;
	// for (int i = 0; i < weather.length; i++) {
	// if (weather1.indexOf(weather[i]) == 0) {
	// id = i;
	// break;
	// }
	// }
	// Log.i("weather", id + "");
	// switch (id) {
	// case 0:// «Á
	// return R.drawable.w0;
	// case 1:// “ı
	// return R.drawable.w2;
	// case 2:// ∂‡‘∆
	// return R.drawable.w1;
	// case 3:// –°”Í
	// return R.drawable.w8;
	// case 4:// ÷–”Í
	// return R.drawable.w9;
	// case 5:// ¥Û”Í
	// return R.drawable.w10;
	// case 6:// ¿◊’Û”Í
	// return R.drawable.w4;
	// case 7:// ’Û”Í
	// return R.drawable.w7;
	// case 8://
	// break;
	// default:
	// return R.drawable.w1;
	// }
	// return R.drawable.w1;
	//
	// }
	//
	// public boolean isNetworkConnected(Context context) {
	// if (context != null) {
	// ConnectivityManager mConnectivityManager = (ConnectivityManager) context
	// .getSystemService(Context.CONNECTIVITY_SERVICE);
	// NetworkInfo mNetworkInfo = mConnectivityManager
	// .getActiveNetworkInfo();
	// if (mNetworkInfo != null) {
	// return mNetworkInfo.isAvailable();
	// }
	// }
	// return false;
	// }
	//
	// }

}
