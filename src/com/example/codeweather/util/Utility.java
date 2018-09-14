package com.example.codeweather.util;

import android.text.TextUtils;

import com.example.codeweather.db.CoolWeatherDB;
import com.example.codeweather.model.City;
import com.example.codeweather.model.County;
import com.example.codeweather.model.Province;

public class Utility {
	public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB,
			String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					coolWeatherDB.saveProvince(province);
				}
			}
			return true;
		}
		return false;
	}

	public synchronized static boolean handleCityResponse(CoolWeatherDB coolWeatherDB,
			String response, int provinceID) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities.length > 0 && allCities != null) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceID(provinceID);
					coolWeatherDB.saveCity(city);
				}
			}
			return true;
		}
		return false;
	}

	public synchronized static boolean handleCountyResponse(CoolWeatherDB coolWeatherDB,
			String response, int ctyID) {
		if (!TextUtils.isEmpty(response)) {
			String[] counties = response.split(",");
			if (counties.length > 0 && counties != null) {
				for (String c : counties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCityID(ctyID);
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					coolWeatherDB.saveCounty(county);
				}
			}
			return true;
		}
		return false;
	}
}
