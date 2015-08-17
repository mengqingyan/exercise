/**
 * 
 */
package com.revencoft.sample.support.resolver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.revencoft.sample.support.QueryCondition;
import com.revencoft.sample.utils.Servlets;

/**
 * @author mengqingyan
 * @version 
 */
public class QueryParamResolver implements HandlerMethodArgumentResolver {

	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(QueryParamCombine.class) != null;
	}

	
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, QueryCondition.SEARCH_PREFIX);
		
		
		
		return null;
	}

}
