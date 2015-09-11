/**
 * 
 */
package com.revencoft.sample.controller.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revencoft.httpconnection.JDKConnection;
import com.revencoft.httpconnection.JDKConnection.ActionWithInputStream;
import com.revencoft.httpconnection.JDKConnection.ConnectionPreProcess;
import com.revencoft.sample.entity.weather.K780Weather;
import com.revencoft.sample.entity.weather.Weather;
import com.revencoft.sample.utils.JsonMapper;

/**
 * @author mengqingyan
 * @version
 */
@Controller
@CacheConfig(cacheNames="data")
@RequestMapping("/weather")
public class WeatherController {
	
//	private static final String WEATHER_URL = "http://m.weather.com.cn/atad/%s.html";
	private static final String WEATHER_URL = "http://api.k780.com:88/?app=weather.future&weaid=%s&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
	
	private static final Logger log = Logger.getLogger(WeatherController.class);
	
	private ConnectionPreProcess preConnect;
	
	private ActionWithInputStream<String> action;
	
	private JsonMapper mapper = JsonMapper.nonEmptyMapper();
	

	@RequestMapping
	@ResponseBody
	@Cacheable
	public Map<String, Object> getWeahter(String cityCode) {
		
		log.info("begin getting weather info ...");
		
		final String url = String.format(WEATHER_URL, cityCode);

		try {
			String result = JDKConnection.openUrl(url, preConnect, action);
			return getWeatherMap(result, K780Weather.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param result
	 * @param weatherClazz
	 * @return
	 */
	private Map<String, Object> getWeatherMap(String result, Class<?> weatherClazz) {
		Weather weather = (Weather) mapper.fromJson(result, weatherClazz);
		return weather.getFutureTempList();
	}

	@PostConstruct
	public void initConnection() {
		preConnect = new ConnectionPreProcess() {

			@Override
			public void preProcess(HttpURLConnection connection) throws Exception {
				connection.setRequestMethod("GET");
				connection.setReadTimeout(30000);
				connection.setConnectTimeout(30000);
				String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// 模拟浏览器
				connection.setRequestProperty("User-agent", userAgent);
			}
		};
		
		action = new ActionWithInputStream<String>() {

			@Override
			public String action(InputStream in) throws IOException {
				return IOUtils.toString(in);
			}
		};
	}
}
