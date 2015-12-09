package com.mqy.design.listener_mode.listener.smart;

import com.mqy.design.listener_mode.event.Event;
import com.mqy.design.listener_mode.listener.EventListener;

/**
 * 聪明的事件监听器，可以判断对哪些事件进行处理
 * @author mengqingyan
 *
 */
public interface SmartEventListener extends EventListener<Event>{

	
	/**
	 * 是否支持该事件
	 * @param e
	 * @return
	 */
	boolean support(Class<? extends Event> eventType);
	
}
