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

		// ��������ʱ ��ʼ��handler��thread

		// ��ʼ��handerThread �� updateHandler
		handlerThread = new HandlerThread("handler_thread"); // �˴��ġ�handler_thread��Ϊ�������֣�
																// // ��Ƶ��û��˵
		handlerThread.start();
		updateHandler = new UpdateHandler(handlerThread.getLooper());
		Log.i("MainService", "thread starts======");

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	// ���button ���ô˴��ص�����
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("MainService", "onStartCommand====");

		if (updateHandler == null) {
			updateHandler = new UpdateHandler(handlerThread.getLooper());
		}

		// ȡ��intent���ݵĳ��д���
		String cityid = null;
		cityid = intent.getStringExtra("cityid");

		// ���д��벻Ϊnull ������Ϣ��handler����
		if (cityid != null) {
			Message msg = updateHandler.obtainMessage();
			Bundle data = new Bundle();
			data.putString("cityid", cityid);
			msg.setData(data);
			msg.what = 0x1;// ��Ϣ��ʶ
			updateHandler.sendMessage(msg);
			Log.i("MainService", "send message 0x1");
		}

		return super.onStartCommand(intent, flags, startId);
	}

	class UpdateHandler extends Handler {
		public UpdateHandler() {
		}

		// ���캯��
		public UpdateHandler(Looper looper) {
			super(looper); // ʹ�ø���Handler��looper������Handler�󶨵�����һ���̲߳�����Ϣ����ȡ����
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub

			switch (msg.what) {
			// ��Ϣ��ʶ
			case 0x1:
				Log.i("MainService", "receive message 0x1");

				// ȡ��message���ݵĳ��д����ַ���
				String citycode = msg.getData().getString("cityid");
				// ȡ����ҳ���ַ�
		
				
				String url="http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId="+citycode;
				
			//	String url = "http://m.weather.com.cn/atad/" + citycode	+ ".html";
				HttpDownloadHelpers httpDownloadHelper = new HttpDownloadHelpers();
				String str = httpDownloadHelper.download(url);
				 System.out.println(str);

				// ��������
				WeatherData data = new WeatherData(MainService.this);
				data.edit(str);
				Message m = updateHandler.obtainMessage();
				m.what = 0x2;// ��ʶ
				updateHandler.sendMessageDelayed(m, 300);
				Log.i("MainService", "send message 0x2");
				break;

			case 0x2:
				// ���͹㲥 ��mainActivity���գ�
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
