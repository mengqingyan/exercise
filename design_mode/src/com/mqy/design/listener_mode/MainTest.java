package com.mqy.design.listener_mode;

import com.mqy.design.listener_mode.listener.AddUserEventListener;
import com.mqy.design.listener_mode.listener.DeleteUserEventListener;
import com.mqy.design.listener_mode.listener.smart.SmartEventListener;
import com.mqy.design.listener_mode.publisher.EventMuticaster;
import com.mqy.design.listener_mode.publisher.EventPublisher;
import com.mqy.design.listener_mode.publisher.EventPublisherAware;
import com.mqy.design.listener_mode.subject.UserSubject;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		UserSubject uS = new UserSubject();
		
		postProcessObject(uS);
		
		uS.addUser();
		System.out.println("=====================");
		uS.deleteUser();
		
	}

	/**
	 * 对obj进行后处理
	 * @param obj
	 */
	private static void postProcessObject(Object obj) {
		
		EventPublisher ePublisher = obtainEventPublisher();
		
		/** 将事件广播者注入目标对象 */
		if(obj instanceof EventPublisherAware) {
			EventPublisherAware ePublishAware = (EventPublisherAware) obj;
			ePublishAware.setEventPublisher(ePublisher);
		}
		
	}

	/**
	 * @return
	 */
	private static EventPublisher obtainEventPublisher() {
		/** 事件广播者 */
		EventMuticaster ePublisher = new EventMuticaster();
		
		/** 定义事件 */
		SmartEventListener addUserEvent = new AddUserEventListener();
		SmartEventListener delUserEvent = new DeleteUserEventListener();
		
		/** 注册事件 */
		ePublisher.addListener(addUserEvent);
		ePublisher.addListener(delUserEvent);
		return ePublisher;
	}

}
