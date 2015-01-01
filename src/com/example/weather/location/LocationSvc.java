package com.example.weather.location;


import com.example.weather.location.Common;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author SunnyCoffee
 * @date 2014-1-19
 * @version 1.0
 * @desc ��λ����
 * 
 */
public class LocationSvc extends Service implements LocationListener {

	private static final String TAG = "LocationSvc";
	private LocationManager locationManager;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) locationManager
				.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
						this);
		else if (locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) locationManager
				.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
						this);
		else Toast.makeText(this, "�޷���λ", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean stopService(Intent name) {
		return super.stopService(name);
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d(TAG, "Get the current position \n" + location);

		//֪ͨActivity
		Intent intent = new Intent();
		intent.setAction(Common.LOCATION_ACTION);
		intent.putExtra(Common.LOCATION, location.toString());
	//	System.out.println(location.getLatitude());
		intent.putExtra("latitude",String.valueOf(location.getLatitude()));
		intent.putExtra("longitude",String.valueOf(location.getLongitude()));
		sendBroadcast(intent);

		// ���ֻ����Ҫ��λһ�Σ�������Ƴ�������ͣ���������Ҫ����ʵʱ��λ���������˳�Ӧ�û�������ʱ��ͣ����λ����
		locationManager.removeUpdates(this);
		stopSelf();
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
