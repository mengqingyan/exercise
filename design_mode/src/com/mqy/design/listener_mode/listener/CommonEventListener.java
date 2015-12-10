package com.mqy.design.listener_mode.listener;

import com.mqy.design.listener_mode.event.Event;

/**
 * 监听{@code Event}类型及其子类型的事件，即监听全部事件
 * @author mengqingyan
 * 
 */
public class CommonEventListener implements EventListener<Event> {

	@Override
	public void action(Event e) {
		System.out.println("listened notified!");
	}

}
