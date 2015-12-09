package com.mqy.design.listener_mode.publisher;

/**
 * 若某类需要广播事件，需要继承该接口
 * @author mengqingyan
 *
 */
public interface EventPublisherAware {

	public void setEventPublisher(EventPublisher eventPublisher);
}
