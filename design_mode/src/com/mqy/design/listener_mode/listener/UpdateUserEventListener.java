package com.mqy.design.listener_mode.listener;

import com.mqy.design.listener_mode.event.UpdateUserEvent;

/**
 * 增加用户的事件监听器
 * @author mengqingyan
 *
 */
public class UpdateUserEventListener implements EventListener<UpdateUserEvent> {



	@Override
	public void action(UpdateUserEvent e) {
		System.out.println("listened UpdateUser! source: " + e.getSource());
	}

}
