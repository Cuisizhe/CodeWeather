package com.example.codeweather.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.codeweather.model.City;
import com.example.codeweather.model.CoolWeatherOpenHelper;
import com.example.codeweather.model.County;
import com.example.codeweather.model.Province;

public class CoolWeatherDB {
	private CoolWeatherOpenHelper coolWeatherOpenHelper;

	private static final String DB_NAME = "cool_weather";
	private static final int VERSION = 1;
	private SQLiteDatabase db;
	private static CoolWeatherDB coolWeatherDB;

	/**
	 * 将构造器私有化
	 * 
	 * @param context
	 */
	private CoolWeatherDB(Context context) {
		coolWeatherOpenHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = coolWeatherOpenHelper.getWritableDatabase();
	}

	/**
	 * 获取CoolWeatherDB实例
	 */
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	/**
	 * 将Province实例数据存储到数据库
	 */
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("Province_code", province.getProvinceCode());
			values.put("province_name", province.getProvinceName());
			db.insert("Province", null, values);
		}
	}

	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				list.add(province);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return list;
	}

	public void saveCity(City city) {
		ContentValues values = new ContentValues();
		if (city != null) {
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("provinceID", city.getProvinceID());
			db.insert("City", null, values);
		}
	}

	public List<City> loadCities(int provinceID) {
		List<City> allCities = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "provinceID = ?",
				new String[] { String.valueOf(provinceID) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setProvinceID(provinceID);
				allCities.add(city);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return allCities;
	}

	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("county_code", county.getCountyCode());
			values.put("county_name", county.getCountyName());
			values.put("cityID", county.getCityID());
			db.insert("County", null, values);
		}
	}

	public List<County> loadCounties(int countyID) {
		List<County> allCounties = new ArrayList<County>();
		Cursor cursor = db.query("County", null, "cityID = ?",
				new String[] { String.valueOf(countyID) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County county = new County();
				county.setCityID(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCityID(countyID);
				allCounties.add(county);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return allCounties;
	}
}
