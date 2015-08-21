/**
 * 
 */
package com.revencoft.sample.controller.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revencoft.httpconnection.JDKConnection;
import com.revencoft.httpconnection.JDKConnection.ActionWithInputStream;
import com.revencoft.httpconnection.JDKConnection.ConnectionPreProcess;
import com.revencoft.sample.entity.Weather;
import com.revencoft.sample.entity.Weather.WeatherInfo;
import com.revencoft.sample.utils.JsonMapper;

/**
 * @author mengqingyan
 * @version
 */
@Controller
@RequestMapping("/weather")
public class WeatherController {
	
	private static final String WEATHER_URL = "http://m.weather.com.cn/atad/%s.html";
	
	
	private ConnectionPreProcess preConnect;
	
	private ActionWithInputStream<String> action;
	
	private JsonMapper mapper = JsonMapper.nonEmptyMapper();
	

	@ResponseBody
	public Map<String, List<String>> getWeahter(String cityCode) {

		final String url = String.format(WEATHER_URL, cityCode);

		Map<String, List<String>> resultMap = null;
		
		try {
			String result = JDKConnection.openUrl(url, preConnect, action);
			return getWeatherMap(result);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param result
	 * @return
	 */
	private Map<String, List<String>> getWeatherMap(String result) {
		Weather weather = mapper.fromJson(result, Weather.class);
		WeatherInfo weatherinfo = weather.getWeatherinfo();
		return weatherinfo.getTempListOf6();
	}

	@PostConstruct
	public void initConnection() {
		preConnect = new ConnectionPreProcess() {

			@Override
			public void process(HttpURLConnection connection) throws Exception {
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
