package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBManager {

	public static final int GET_TYPE = 0;
	public static final int GET_START = 1;
	public static final int GET_TERMINAL = 2;
	public static final int GET_STATE = 3;
	public static final int GET_HOST = 4;
	
	
	public static final int DISTANCE=0;
	public static final int TICKET_2=1;
	public static final int TICKET_1=2;
	public static final int TICKET_SUP=3;
	public static final int TICKET_BUS=4;
	public static final int TICKET_STU=5;


	private final int BUFFER_SIZE = 1024;
	public static final String DB_NAME = "city.db";
	public static final String PACKAGE_NAME = "com.example.weather";
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME + "/databases";
	private SQLiteDatabase database;
	private Context context;

	public DBManager(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void openDatabase() {
		this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
	}

	private SQLiteDatabase openDatabase(String dbfile) {
		try {
			if (!(new File(dbfile).exists())) {

				File path = new File(DB_PATH);
				path.mkdirs();

				InputStream is = this.context.getAssets().open("city.db");
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
					null);
			return db;
		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}
		return null;

	}

	public void closeDatabase() {
		this.database.close();
	}

	

	public Cursor queryCity(String city) {
		String sql = "select city,_id  from city where firstletter like \'"
				+ city + "%\' or city like '" + city
				+ "%\' or pinyin like \'" + city + "%\' or english like \'"+city+"%\'";
		System.out.println(sql);
		return this.database.rawQuery(sql, null);
	}

}
