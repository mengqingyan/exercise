package com.mqy.design.listener_mode.listener;

import com.mqy.design.listener_mode.event.Event;

/**
 * @author mengqingyan
 * 
 */
public class CommonEventListener implements EventListener<Event> {

	@Override
	public void action(Event e) {
		System.out.println("listened notified!");
	}

}
