package com.revencoft.sample.entity.weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * K780网站数据VO
 * @author mengqingyan
 * @version
 */
public class K780Weather implements Weather{

	
	private String success;//标识成功或失败
	
	private List<K780WeatherInfo> result;
	
	@Override
	public Map<String, Object> getFutureTempList() {
		
		if(result == null) {
			return null;
		}
		Map<String, Object> temps = new HashMap<String, Object>();
		List<Object> maxTemps = new ArrayList<Object>();
		List<Object> minTemps = new ArrayList<Object>();
		List<Object> dates = new ArrayList<Object>();
		
		temps.put(HIGHT_TEMPS, maxTemps);
		temps.put(LOW_TEMPS, minTemps);
		temps.put("dates", dates);
		StringBuilder weatherInfox = new StringBuilder();
		for (K780WeatherInfo weatherInfo : result) {
			maxTemps.add(weatherInfo.getTemp_high());
			minTemps.add(weatherInfo.getTemp_low());
			weatherInfox.append(weatherInfo.getDays()).append("[")
					.append(weatherInfo.week).append("]");
			dates.add(weatherInfox.toString());
			
			temps.put(weatherInfox.toString(), weatherInfo.weather_icon);
			
			weatherInfox.delete(0, weatherInfox.length());
		}
		return temps;
	}
	
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public List<K780WeatherInfo> getResult() {
		return result;
	}

	public void setResult(List<K780WeatherInfo> result) {
		this.result = result;
	}

	public static final class K780WeatherInfo {
		private String cityid;
		private String citynm;//城市名
		private String cityno;//城市拼音
		private String days;//日期
		private float temp_high;
		private float temp_low;
		private String weather_icon;//白天天气图标url
		private String weather_icon1;//晚上。。。
		private String week;
		private String weather;//天气
		
		public String getCityid() {
			return cityid;
		}
		public void setCityid(String cityid) {
			this.cityid = cityid;
		}
		public String getCitynm() {
			return citynm;
		}
		public void setCitynm(String citynm) {
			this.citynm = citynm;
		}
		public String getCityno() {
			return cityno;
		}
		public void setCityno(String cityno) {
			this.cityno = cityno;
		}
		
//		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
		public String getDays() {
			return days;
		}
		public void setDays(String days) {
			this.days = days;
		}
		public float getTemp_high() {
			return temp_high;
		}
		public void setTemp_high(float temp_high) {
			this.temp_high = temp_high;
		}
		public float getTemp_low() {
			return temp_low;
		}
		public void setTemp_low(float temp_low) {
			this.temp_low = temp_low;
		}
		public String getWeather_icon() {
			return weather_icon;
		}
		public void setWeather_icon(String weather_icon) {
			this.weather_icon = weather_icon;
		}
		public String getWeather_icon1() {
			return weather_icon1;
		}
		public void setWeather_icon1(String weather_icon1) {
			this.weather_icon1 = weather_icon1;
		}
		public String getWeek() {
			return week;
		}
		public void setWeek(String week) {
			this.week = week;
		}
		public String getWeather() {
			return weather;
		}
		public void setWeather(String weather) {
			this.weather = weather;
		}
		
		
	}

	
}
