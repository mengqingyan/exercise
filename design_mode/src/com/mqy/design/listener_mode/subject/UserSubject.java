package com.mqy.design.listener_mode.subject;

import com.mqy.design.listener_mode.event.AddUserEvent;
import com.mqy.design.listener_mode.event.DeleteUserEvent;
import com.mqy.design.listener_mode.event.Event;
import com.mqy.design.listener_mode.publisher.EventPublisher;
import com.mqy.design.listener_mode.publisher.EventPublisherAware;

/**
 * User 操作实体
 * @author mengqingyan
 *
 */
public class UserSubject implements EventPublisherAware {

	private EventPublisher eventPublisher;

	public void addUser() {
		System.out.println("add User over!");

		notifyListener(new AddUserEvent(this));
	}

	public void deleteUser() {
		System.out.println("delete User over!");

		notifyListener(new DeleteUserEvent(this));
	}
	
	
	private void notifyListener(Event e) {
		eventPublisher.publishEvent(e);
	}

	@Override
	public void setEventPublisher(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

}
