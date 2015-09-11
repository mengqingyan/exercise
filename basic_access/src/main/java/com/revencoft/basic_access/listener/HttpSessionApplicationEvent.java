/**
 * 
 */
package com.revencoft.basic_access.listener;

import java.util.List;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author mengqingyan
 * @version 
 */
public class HttpSessionApplicationEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final boolean sessionCreated;
	
	private HttpSessionEvent sessionEvent;
	
	private List<WebApplicationContext> childWebAppCtxs;
	

	/**
	 * @param source
	 */
	public HttpSessionApplicationEvent(Object source, boolean sessionCreated) {
		super(source);
		this.sessionCreated = sessionCreated;
	}

	public boolean isSessionCreated() {
		return sessionCreated;
	}

	

	public HttpSessionEvent getSessionEvent() {
		return sessionEvent;
	}

	public void setSessionEvent(HttpSessionEvent sessionEvent) {
		this.sessionEvent = sessionEvent;
	}

	public List<WebApplicationContext> getChildWebAppCtxs() {
		return childWebAppCtxs;
	}

	public void setChildWebAppCtxs(List<WebApplicationContext> childWebAppCtxs) {
		this.childWebAppCtxs = childWebAppCtxs;
	}
	
	

}
