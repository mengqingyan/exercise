/**
 * 
 */
package com.revencoft.basic_access.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.revencoft.basic_access.utils.CollectionUtil;

/**
 * @author mengqingyan
 * @version 
 */
public abstract class RequestMethodHandlerInterceptor implements HandlerInterceptor, InitializingBean {

	protected Set<String> allowedRequestMethods;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(!isAllowedRequestMethod(request)) {
			return true;
		}
		return doPreHandle(request, response, handler);
	}
	
	protected abstract boolean doPreHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(!isAllowedRequestMethod(request)) {
			return;
		}
		
		doPostHandle(request, response, handler, modelAndView);

	}
	
	protected abstract void doPostHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception; 

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(!isAllowedRequestMethod(request)) {
			return;
		}
		doAfterCompletion(request, response, handler, ex);
	}
	
	protected void doAfterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {};
	
	private boolean isAllowedRequestMethod(HttpServletRequest request) {
		String method = request.getMethod();
		return allowedRequestMethods.contains(method);
	}
	
	/**
	 * 请求方式大写，字符串以逗号分隔
	 * @param allowedRequestMethods
	 */
	public void setAllowedRequestMethods(String allowedRequestMethods) {
		this.allowedRequestMethods = CollectionUtil.commaDelimitedStringToSet(allowedRequestMethods);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(allowedRequestMethods == null) {
			allowedRequestMethods = new HashSet<String>();
			allowedRequestMethods.add("GET");
			allowedRequestMethods.add("POST");
		}
	}

}
