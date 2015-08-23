package com.revencoft.sample.entity.weather;

import java.util.Map;
/**
 * 
 * @author mengqingyan
 * @version
 */
public interface Weather {

	String HIGHT_TEMPS = "high_temps";
	String LOW_TEMPS = "low_temps";
	
	Map<String, Object> getFutureTempList();
}
