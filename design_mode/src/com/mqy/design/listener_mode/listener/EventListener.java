package com.mqy.design.listener_mode.listener;

import com.mqy.design.listener_mode.event.Event;

/**
 * 事件监听器
 * @author mengqingyan
 *
 */
public interface EventListener<E extends Event> {

	
	/**
	 * 对相应的事件作出具体的动作
	 */
	void action(E e);
	
}
