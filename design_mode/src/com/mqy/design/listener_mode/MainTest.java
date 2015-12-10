package com.mqy.design.listener_mode;

import com.mqy.design.listener_mode.listener.AddUserEventListener;
import com.mqy.design.listener_mode.listener.CommonEventListener;
import com.mqy.design.listener_mode.listener.DeleteUserEventListener;
import com.mqy.design.listener_mode.listener.UpdateUserEventListener;
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
		
		System.out.println("=====================");
		uS.updateUser();
		
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
		
		/** 定义事件监听器 */
		AddUserEventListener addUserEventListener = new AddUserEventListener();
		DeleteUserEventListener delUserEventListener = new DeleteUserEventListener();
		/** 定义修改用户的事件监听器，该监听器编写起来更简单，可以查看源码 */
		UpdateUserEventListener updateUserEventListener = new UpdateUserEventListener();
		/** 监听全部事件的监听器 */
		CommonEventListener commonEventListener = new CommonEventListener();
		
		/** 注册事件 */
		ePublisher.addListener(addUserEventListener);
		ePublisher.addListener(delUserEventListener);
		ePublisher.addListener(updateUserEventListener);
		ePublisher.addListener(commonEventListener);
		return ePublisher;
	}

}
