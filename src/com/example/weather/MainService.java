package com.example.weather;

import java.util.TimerTask;

import com.example.weather.R;
import com.utils.HttpDownloadHelpers;
import com.utils.WeatherData;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;

public class MainService extends Service {

	HandlerThread handlerThread;
	UpdateHandler updateHandler;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		// 创建服务时 初始化handler和thread

		// 初始化handerThread 和 updateHandler
		handlerThread = new HandlerThread("handler_thread"); // 此处的“handler_thread”为进程名字？
																// // 视频里没有说
		handlerThread.start();
		updateHandler = new UpdateHandler(handlerThread.getLooper());
		Log.i("MainService", "thread starts======");

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	// 点击button 调用此处回调函数
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("MainService", "onStartCommand====");

		if (updateHandler == null) {
			updateHandler = new UpdateHandler(handlerThread.getLooper());
		}

		// 取得intent传递的城市代码
		String cityid = null;
		cityid = intent.getStringExtra("cityid");

		// 城市代码不为null 发送消息给handler处理
		if (cityid != null) {
			Message msg = updateHandler.obtainMessage();
			Bundle data = new Bundle();
			data.putString("cityid", cityid);
			msg.setData(data);
			msg.what = 0x1;// 消息标识
			updateHandler.sendMessage(msg);
			Log.i("MainService", "send message 0x1");
		}

		return super.onStartCommand(intent, flags, startId);
	}

	class UpdateHandler extends Handler {
		public UpdateHandler() {
		}

		// 构造函数
		public UpdateHandler(Looper looper) {
			super(looper); // 使用父类Handler的looper，即把Handler绑定到另外一个线程并从消息队列取数据
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub

			switch (msg.what) {
			// 消息标识
			case 0x1:
				Log.i("MainService", "receive message 0x1");

				// 取得message传递的城市代码字符串
				String citycode = msg.getData().getString("cityid");
				// 取得网页的字符
		
				
				String url="http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId="+citycode;
				
			//	String url = "http://m.weather.com.cn/atad/" + citycode	+ ".html";
				HttpDownloadHelpers httpDownloadHelper = new HttpDownloadHelpers();
				String str = httpDownloadHelper.download(url);
				 System.out.println(str);

				// 更新数据
				WeatherData data = new WeatherData(MainService.this);
				data.edit(str);
				Message m = updateHandler.obtainMessage();
				m.what = 0x2;// 标识
				updateHandler.sendMessageDelayed(m, 300);
				Log.i("MainService", "send message 0x2");
				break;

			case 0x2:
				// 发送广播 （mainActivity接收）
				Log.i("MainService", "receive message 0x2");
				Intent intent = new Intent();
				intent.setAction("com.example.html.MainService");
				sendBroadcast(intent);
				break;

			case 0x3:

				break;
			}
			super.handleMessage(msg);
		}

	}

	class UpdateTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}

	}
	// Runnable updateWeatherThread = new Runnable() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	//
	//
	// }
	// };

}
