package com.mqy.design.listener_mode.listener.smart.adaptor;

import org.springframework.core.GenericTypeResolver;

import com.mqy.design.listener_mode.event.Event;
import com.mqy.design.listener_mode.listener.EventListener;
import com.mqy.design.listener_mode.listener.smart.SmartEventListener;

/**
 * @author mengqingyan
 * 
 */
public class GenericEventListenerAdaptor implements SmartEventListener {
	
	@SuppressWarnings("rawtypes")
	private EventListener delegate;
	
	
	@SuppressWarnings("rawtypes")
	public GenericEventListenerAdaptor(EventListener eventLitener) {
		this.delegate = eventLitener;
	}
	
	
	@Override
	public boolean support(Class<? extends Event> eventType) {
		Class<?> typeArg = GenericTypeResolver.resolveTypeArgument(this.delegate.getClass(), EventListener.class);
		return (typeArg == null || typeArg.isAssignableFrom(eventType));
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public void action(Event e) {
		delegate.action(e);
	}

	

}
