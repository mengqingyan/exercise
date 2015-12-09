package com.mqy.design.listener_mode.listener;

import com.mqy.design.listener_mode.listener.smart.SmartEventListener;

/**
 * 事件注册者
 * @author mengqingyan
 *
 */
public interface ListenerRegistra {

	public void addListener(SmartEventListener e);
}
