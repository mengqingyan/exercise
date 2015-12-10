package com.mqy.design.listener_mode.publisher;

import java.util.ArrayList;
import java.util.List;

import com.mqy.design.listener_mode.event.Event;
import com.mqy.design.listener_mode.listener.EventListener;
import com.mqy.design.listener_mode.listener.ListenerRegistrar;
import com.mqy.design.listener_mode.listener.smart.SmartEventListener;
import com.mqy.design.listener_mode.listener.smart.adaptor.GenericEventListenerAdaptor;

/**
 * 事件注册和广播实现类
 * @author mengqingyan
 *
 */
public class EventMuticaster implements EventPublisher, ListenerRegistrar {
	
	
	private final List<EventListener<?>> listeners = new ArrayList<EventListener<?>>();

	public EventMuticaster() {
		
	}
	
	@Override
	public void addListener(EventListener<?> e) {
		listeners.add(e);
	}
	
	@Override
	public void publishEvent(Event e) {
		
		for (EventListener<?> listener : listeners) {
			
			SmartEventListener smartListener = null;
			
			if(listener instanceof SmartEventListener) {
				smartListener = (SmartEventListener) listener;
			} else {
				smartListener = new GenericEventListenerAdaptor(listener);
			}
			if(smartListener.support(e.getClass())) {
				smartListener.action(e);
			}
		}
	}
}