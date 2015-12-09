package com.mqy.design.listener_mode.event;


/**
 * 定义事件
 * @author mengqingyan
 *
 */
public abstract class Event {

	/** 事件源 */
	private final Object source;
	
	public Event(Object source) {
		this.source = source;
	}

	public Object getSource() {
		return source;
	}
	
	
}
