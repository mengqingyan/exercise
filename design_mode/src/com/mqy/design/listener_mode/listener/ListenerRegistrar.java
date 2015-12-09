package com.mqy.design.listener_mode.listener;

/**
 * 事件注册者
 * @author mengqingyan
 *
 */
public interface ListenerRegistrar {

	public void addListener(EventListener<?> e);
}
