package com.mqy.design.listener_mode.listener;

import com.mqy.design.listener_mode.event.AddUserEvent;
import com.mqy.design.listener_mode.event.Event;
import com.mqy.design.listener_mode.listener.smart.SmartEventListener;

/**
 * 增加用户的事件监听器
 * @author mengqingyan
 *
 */
public class AddUserEventListener implements SmartEventListener {

	/**
	 * 该方法，可以支持处理AddUserEvent类型和AddUserEvent子类类型的事件
	 */
	@Override
	public boolean support(Class<? extends Event> eventType) {
		/** 判断eventType是否是AddUserEvent类或它的子类 */
		if(AddUserEvent.class.isAssignableFrom(eventType)) {
			return true;
		}
		return false;
	}

	/**
	 * 该方法，只能处理AddUserEvent类型的事件，却不能处理AddUserEvent子类类型的事件
	 * @param e
	 * @return
	 */
	@Deprecated
	public boolean support(Event e) {
		if(e instanceof AddUserEvent) {
			return true;
		}
		return false;
	}

	@Override
	public void action(Event e) {
		System.out.println("listened addUser! source: " + e.getSource());
	}

}
