package com.mqy.design.listener_mode.listener;

import com.mqy.design.listener_mode.event.DeleteUserEvent;
import com.mqy.design.listener_mode.event.Event;
import com.mqy.design.listener_mode.listener.smart.SmartEventListener;

/**
 * 删除用户的事件监听器
 * @author mengqingyan
 *
 */
public class DeleteUserEventListener implements SmartEventListener {
	
	/**
	 * 该方法，可以支持处理DeleteUserEvent类型和DeleteUserEvent子类类型的事件
	 */
	@Override
	public boolean support(Class<? extends Event> eventType) {
		/** 判断eventType是否是DeleteUserEvent类或它的子类 */
		if(DeleteUserEvent.class.isAssignableFrom(eventType)) {
			return true;
		}
		return false;
	}

	/**
	 * 该方法，只能处理DeleteUserEvent类型的事件，却不能处理DeleteUserEvent子类类型的事件
	 * @param e
	 * @return
	 */
	@Deprecated
	public boolean support(Event e) {
		if(e instanceof DeleteUserEvent) {
			return true;
		}
		return false;
	}

	
	@Override
	public void action(Event e) {
		System.out.println("listened deleteUser! source: " + e.getSource());
	}

}
