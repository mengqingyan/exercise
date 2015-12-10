package com.mqy.design.listener_mode.listener;

import com.mqy.design.listener_mode.event.UpdateUserEvent;

/**
 * 增加用户的事件监听器，继承的接口里的泛型 {@code UpdateUserEvent}，表示该监听器只监听
 *  {@code UpdateUserEvent}类型和其子类型的事件
 * @author mengqingyan
 *
 */
public class UpdateUserEventListener implements EventListener<UpdateUserEvent> {



	@Override
	public void action(UpdateUserEvent e) {
		System.out.println("listened UpdateUser! source: " + e.getSource());
	}

}
