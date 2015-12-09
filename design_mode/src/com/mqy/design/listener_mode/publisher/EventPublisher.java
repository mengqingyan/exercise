package com.mqy.design.listener_mode.publisher;

import com.mqy.design.listener_mode.event.Event;

/**
 * 事件广播者
 * @author mengqingyan
 *
 */
public interface EventPublisher {

	public abstract void publishEvent(Event e);

}