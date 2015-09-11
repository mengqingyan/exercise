/**
 * 
 */
package com.revencoft.basic_access.listener;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

/**
 * @author mengqingyan
 * @version 
 */
public class HttpSessionEventListener implements HttpSessionListener {

	
	private List<WebApplicationContext> childWebAppCtxs;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		publishHttpSessionEvent(se, true);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		publishHttpSessionEvent(se, false);
	}
	
	private void publishHttpSessionEvent(HttpSessionEvent se, boolean sessionCreated) {
		HttpSession session = se.getSession();
		ServletContext sc = session.getServletContext();
		
		WebApplicationContext rootWebAppCtx = getRequiredWebApplicationContext(sc, WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		
		HttpSessionApplicationEvent event = new HttpSessionApplicationEvent(rootWebAppCtx, sessionCreated);
		
		event.setSessionEvent(se);
		event.setChildWebAppCtxs(getChildWebAppContexts(sc));
		
		rootWebAppCtx.publishEvent(event);
		
	}
	
	private List<WebApplicationContext> getChildWebAppContexts(ServletContext sc) {
		
		if(childWebAppCtxs != null) {
			return childWebAppCtxs;
		}
		
		Enumeration<String> attributeNames = sc.getAttributeNames();
		childWebAppCtxs = new LinkedList<WebApplicationContext>();
		while(attributeNames.hasMoreElements()) {
			String attrName = attributeNames.nextElement();
			if(StringUtils.startsWith(attrName, FrameworkServlet.SERVLET_CONTEXT_PREFIX)) {
				childWebAppCtxs.add(getRequiredWebApplicationContext(sc, attrName));
			}
		}
		childWebAppCtxs = Collections.unmodifiableList(childWebAppCtxs);
		return childWebAppCtxs;
	}
	
	private WebApplicationContext getRequiredWebApplicationContext(ServletContext sc, String attrName) throws IllegalStateException {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc, attrName);
		if (wac == null) {
			throw new IllegalStateException("No WebApplicationContext found: no ContextLoaderListener registered?");
		}
		return wac;
	}
	
}
