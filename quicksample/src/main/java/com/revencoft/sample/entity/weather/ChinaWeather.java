/**
 * 
 */
package com.revencoft.sample.entity.weather;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 中国天气网数据VO
 * @author mengqingyan
 * @version 
 */
public class ChinaWeather implements Weather{


	private WeatherInfo weatherinfo;
	
	public WeatherInfo getWeatherinfo() {
		return weatherinfo;
	}

	public void setWeatherinfo(WeatherInfo weatherinfo) {
		this.weatherinfo = weatherinfo;
	}
	
	@Override
	public Map<String, Object> getFutureTempList() {
		
		WeatherInfo wInfo = getWeatherinfo();
		
		Map<String, Object> temps = new HashMap<String, Object>();
		List<Object> maxTemps = new ArrayList<Object>();
		List<Object> minTemps = new ArrayList<Object>();
		
		maxTemps.add(StringUtils.substringBefore(wInfo.temp1, WeatherInfo.TEMP_SEP));
		minTemps.add(StringUtils.substringAfter(wInfo.temp1, WeatherInfo.TEMP_SEP));
		
		maxTemps.add(StringUtils.substringBefore(wInfo.temp2, WeatherInfo.TEMP_SEP));
		minTemps.add(StringUtils.substringAfter(wInfo.temp2, WeatherInfo.TEMP_SEP));
		
		maxTemps.add(StringUtils.substringBefore(wInfo.temp3, WeatherInfo.TEMP_SEP));
		minTemps.add(StringUtils.substringAfter(wInfo.temp3, WeatherInfo.TEMP_SEP));
		
		maxTemps.add(StringUtils.substringBefore(wInfo.temp4, WeatherInfo.TEMP_SEP));
		minTemps.add(StringUtils.substringAfter(wInfo.temp4, WeatherInfo.TEMP_SEP));
		
		maxTemps.add(StringUtils.substringBefore(wInfo.temp5, WeatherInfo.TEMP_SEP));
		minTemps.add(StringUtils.substringAfter(wInfo.temp5, WeatherInfo.TEMP_SEP));
		
		maxTemps.add(StringUtils.substringBefore(wInfo.temp6, WeatherInfo.TEMP_SEP));
		minTemps.add(StringUtils.substringAfter(wInfo.temp6, WeatherInfo.TEMP_SEP));
		
		temps.put(HIGHT_TEMPS, maxTemps);
		temps.put(LOW_TEMPS, maxTemps);
		
		return temps;
	}

	public static final class WeatherInfo {
		
		private static final String TEMP_SEP = "~";
		
		private String city;//zh_cn
		private String city_en;//en_us
		private Date date_y;//当前日期
		private Date date;
		private String week;
		private String cityid;
		private String temp1;
		private String temp2;
		private String temp3;
		private String temp4;
		private String temp5;
		private String temp6;
		
		private String weather1;
		private String weather2;
		private String weather3;
		private String weather4;
		private String weather5;
		private String weather6;
		
		private String img1;
		private String img2;
		private String img3;
		private String img4;
		private String img5;
		private String img6;
		private String img7;
		private String img8;
		private String img9;
		private String img10;
		private String img11;
		private String img12;
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCity_en() {
			return city_en;
		}
		public void setCity_en(String city_en) {
			this.city_en = city_en;
		}
		
		@JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+08:00")
		public Date getDate_y() {
			return date_y;
		}
		public void setDate_y(Date date_y) {
			this.date_y = date_y;
		}
		
		@JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+08:00")
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getWeek() {
			return week;
		}
		public void setWeek(String week) {
			this.week = week;
		}
		public String getCityid() {
			return cityid;
		}
		public void setCityid(String cityid) {
			this.cityid = cityid;
		}
		public String getTemp1() {
			return temp1;
		}
		public void setTemp1(String temp1) {
			this.temp1 = temp1;
		}
		public String getTemp2() {
			return temp2;
		}
		public void setTemp2(String temp2) {
			this.temp2 = temp2;
		}
		public String getTemp3() {
			return temp3;
		}
		public void setTemp3(String temp3) {
			this.temp3 = temp3;
		}
		public String getTemp4() {
			return temp4;
		}
		public void setTemp4(String temp4) {
			this.temp4 = temp4;
		}
		public String getTemp5() {
			return temp5;
		}
		public void setTemp5(String temp5) {
			this.temp5 = temp5;
		}
		public String getTemp6() {
			return temp6;
		}
		public void setTemp6(String temp6) {
			this.temp6 = temp6;
		}
		public String getWeather1() {
			return weather1;
		}
		public void setWeather1(String weather1) {
			this.weather1 = weather1;
		}
		public String getWeather2() {
			return weather2;
		}
		public void setWeather2(String weather2) {
			this.weather2 = weather2;
		}
		public String getWeather3() {
			return weather3;
		}
		public void setWeather3(String weather3) {
			this.weather3 = weather3;
		}
		public String getWeather4() {
			return weather4;
		}
		public void setWeather4(String weather4) {
			this.weather4 = weather4;
		}
		public String getWeather5() {
			return weather5;
		}
		public void setWeather5(String weather5) {
			this.weather5 = weather5;
		}
		public String getWeather6() {
			return weather6;
		}
		public void setWeather6(String weather6) {
			this.weather6 = weather6;
		}
		public String getImg1() {
			return img1;
		}
		public void setImg1(String img1) {
			this.img1 = img1;
		}
		public String getImg2() {
			return img2;
		}
		public void setImg2(String img2) {
			this.img2 = img2;
		}
		public String getImg3() {
			return img3;
		}
		public void setImg3(String img3) {
			this.img3 = img3;
		}
		public String getImg4() {
			return img4;
		}
		public void setImg4(String img4) {
			this.img4 = img4;
		}
		public String getImg5() {
			return img5;
		}
		public void setImg5(String img5) {
			this.img5 = img5;
		}
		public String getImg6() {
			return img6;
		}
		public void setImg6(String img6) {
			this.img6 = img6;
		}
		public String getImg7() {
			return img7;
		}
		public void setImg7(String img7) {
			this.img7 = img7;
		}
		public String getImg8() {
			return img8;
		}
		public void setImg8(String img8) {
			this.img8 = img8;
		}
		public String getImg9() {
			return img9;
		}
		public void setImg9(String img9) {
			this.img9 = img9;
		}
		public String getImg10() {
			return img10;
		}
		public void setImg10(String img10) {
			this.img10 = img10;
		}
		public String getImg11() {
			return img11;
		}
		public void setImg11(String img11) {
			this.img11 = img11;
		}
		public String getImg12() {
			return img12;
		}
		public void setImg12(String img12) {
			this.img12 = img12;
		}
		
	}
}
