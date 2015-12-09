package com.mqy.design.listener_mode.publisher;

import java.util.ArrayList;
import java.util.List;

import com.mqy.design.listener_mode.event.Event;
import com.mqy.design.listener_mode.listener.ListenerRegistra;
import com.mqy.design.listener_mode.listener.smart.SmartEventListener;

/**
 * 事件注册和广播实现类
 * @author mengqingyan
 *
 */
public class EventMuticaster implements EventPublisher, ListenerRegistra {
	
	
	public List<SmartEventListener> listeners = new ArrayList<SmartEventListener>();

	public EventMuticaster() {
		
	}
	
	@Override
	public void addListener(SmartEventListener e) {
		listeners.add(e);
	}
	
	@Override
	public void publishEvent(Event e) {
		for (SmartEventListener listener : listeners) {
			if(listener.support(e.getClass())) {
				listener.action(e);
			}
		}
	}
}