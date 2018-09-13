package com.example.codeweather.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	private static final String CREATE_PROVINCE = "create table Province("
			+ "id integer primary key autoincrement," + "province_code text,"
			+ "province_name text)";

	private static final String CREATE_CITY = "create table City("
			+ "id integer primary key autoincrement," + "city_code text," + "city_name text,"
			+ "provinceID integer)";

	private static final String CREATE_COUNTY = "create table County("
			+ "in integer primary key autoincrement," + "county_code text," + "county_name text,"
			+ "cityID integer)";

	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
	}

}
