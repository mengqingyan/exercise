/**
 * 
 */
package com.revencoft.basic_access.listener;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author mengqingyan
 * @version 
 */
public class HttpSessionRefreshorApplicationListener implements
		ApplicationListener<HttpSessionApplicationEvent> {

	
	@Override
	public void onApplicationEvent(HttpSessionApplicationEvent event) {
		if(!event.isSessionCreated()) {
			
			List<WebApplicationContext> childWebAppCtxs = event.getChildWebAppCtxs();
			for(WebApplicationContext appCtx : childWebAppCtxs){
				executeRefreshor(appCtx, event);
			}
			
			WebApplicationContext rootAppCtx = (WebApplicationContext) event.getSource();
			executeRefreshor(rootAppCtx, event);
			
		}
	}
	
	private void executeRefreshor(WebApplicationContext appCtx, HttpSessionApplicationEvent event) {
		SessionContextRefreshor refreshor;
		try {
			refreshor = appCtx.getBean(SessionContextRefreshor.class);
		} catch (Exception e) {
			return;
		}
		refreshor.refresh(event.getSessionEvent().getSession());
	}

}
